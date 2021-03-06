package com.mangofactory.swagger.models;

import com.fasterxml.classmate.ResolvedType;

import java.util.List;
import java.util.Set;

public class ResolvedCollection {

    public static boolean isList(MemberInfoSource member) {
        return member.isAssignableFrom(List.class);
    }

    public static ResolvedType listElementType(MemberInfoSource member) {
        ResolvedType resolvedList = member.getResolvedType();
        List<ResolvedType> resolvedTypes = resolvedList.typeParametersFor(List.class);
        if(resolvedTypes == null) {
            resolvedTypes = resolvedList.typeParametersFor(Iterable.class);
        }
        if(resolvedTypes == null || resolvedTypes.isEmpty())
            throw new IllegalArgumentException("Unknown List derivative "+member);
        return resolvedTypes.get(0);
    }

    public static boolean isSet(MemberInfoSource member) {
        return member.isAssignableFrom(Set.class);
    }

    public static ResolvedType setElementType(MemberInfoSource member) {
        ResolvedType resolvedList = member.getResolvedType();
        return resolvedList.typeParametersFor(Set.class).get(0);
    }
}
