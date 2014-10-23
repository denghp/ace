/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.console.common.support;

/**
 * GenericService 的工具类
 * Created with ace.
 * @User: denghp
 * @Date: 11/1/13
 * @Time: 11:23 PM
 */
import com.ace.console.annotation.BaseComponent;
import com.ace.console.controller.BaseController;
import com.ace.console.service.GenericService;
import com.ace.core.persistence.sys.mapper.GenericeMapper;
import com.google.common.collect.Sets;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

public class InjectBaseDependencyHelper {

    public static void findAndInjectBaseRepositoryDependency(GenericService<?,?> genericService) {
        final Set<Object> candidates =
                findDependencies(genericService, BaseComponent.class);

        if (candidates.size() == 0 || candidates.size() > 1) {
            throw new IllegalStateException(
                    "expect 1 @BaseComponent anntation BaseRepository subclass bean, but found " + candidates.size() +
                            ", please check class [" + genericService.getClass() + "] @BaseComponent annotation.");
        }

        Object genericeMapper = candidates.iterator().next();

        if (genericeMapper.getClass().isAssignableFrom(BaseComponent.class)) {
            throw new IllegalStateException("[" + genericService.getClass() + "] @BaseComponent annotation bean " +
                    "must be BaseRepository subclass");
        }
        genericService.setGenericeMapper((GenericeMapper) genericeMapper);
    }


    public static void findAndInjectGenericServiceDependency(BaseController<?, ?> baseController) {
        final Set<Object> candidates =
                findDependencies(baseController, BaseComponent.class);

        if (candidates.size() > 1) {
            throw new IllegalStateException(
                    "expect  @BaseComponent anntation GenericeServiceImpl subclass bean, but found " + candidates.size() +
                            ", please check class [" + baseController.getClass() + "] @BaseComponent annotation.");
        }

        Object genericService = candidates.iterator().next();

        if (genericService.getClass().isAssignableFrom(BaseComponent.class)) {
            throw new IllegalStateException("[" + baseController.getClass() + "] @BaseComponent annotation bean " +
                    "must be GenericeServiceImpl subclass");
        }

        baseController.setGenericService((GenericService) genericService);
    }


    /**
     * 根据注解在目标对象上的字段上查找依赖
     *
     * @param target
     * @param annotation
     */
    private static Set<Object> findDependencies(final Object target, final Class<? extends Annotation> annotation) {

        final Set<Object> candidates = Sets.newHashSet();

        ReflectionUtils.doWithFields(
                target.getClass(),
                new ReflectionUtils.FieldCallback() {
                    @Override
                    public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                        ReflectionUtils.makeAccessible(field);
                        Object obj = ReflectionUtils.getField(field, target);
                        candidates.add(obj);
                    }
                },
                new ReflectionUtils.FieldFilter() {
                    @Override
                    public boolean matches(Field field) {
                        return field.isAnnotationPresent(annotation);
                    }
                }
        );

        ReflectionUtils.doWithMethods(
                target.getClass(),
                new ReflectionUtils.MethodCallback() {
                    @Override
                    public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                        ReflectionUtils.makeAccessible(method);
                        PropertyDescriptor descriptor = BeanUtils.findPropertyForMethod(method);
                        candidates.add(ReflectionUtils.invokeMethod(descriptor.getReadMethod(), target));
                    }
                },
                new ReflectionUtils.MethodFilter() {
                    @Override
                    public boolean matches(Method method) {
                        boolean hasAnnotation = false;
                        hasAnnotation = method.isAnnotationPresent(annotation);
                        if (!hasAnnotation) {
                            return false;
                        }

                        boolean hasReadMethod = false;
                        PropertyDescriptor descriptor = BeanUtils.findPropertyForMethod(method);
                        hasReadMethod = descriptor != null && descriptor.getReadMethod() != null;

                        if (!hasReadMethod) {
                            return false;
                        }

                        return true;
                    }
                }
        );

        return candidates;
    }

}
