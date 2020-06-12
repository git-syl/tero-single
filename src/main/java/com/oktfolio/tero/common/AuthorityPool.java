package com.oktfolio.tero.common;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.oktfolio.tero.common.enums.AuthorityGroupEnum;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/08
 */
public class AuthorityPool {

    private final static ConcurrentMap<String, ConcurrentMap<String, Authority>> AUTHORITIES = Maps.newConcurrentMap();

    public static void put(Authority authority) {
        ConcurrentMap<String, Authority> map = AUTHORITIES.get(authority.getGroup().name());
        map.put(authority.getCode(), authority);
    }

    public static void put(String code, String name, AuthorityGroupEnum group) {
        ConcurrentMap<String, Authority> authorityMap = Maps.newConcurrentMap();
        authorityMap.put(code, new Authority(code, name, group));
        AUTHORITIES.put(group.name(), authorityMap);
    }

    public static Authority get(Authority authority) {
        return AUTHORITIES.get(authority.getGroup().name()).get(authority.getCode());
    }

    public static List<Authority> getAuthorityList() {
        List<Authority> list = Lists.newArrayList();
        AUTHORITIES.entrySet()
                .forEach(((it)
                        -> it.getValue()
                        .forEach((key, value)
                                -> list.add(value))));
        return list;
    }

    public static List<String> getAuthorityCodeList() {
        return getAuthorityList().stream()
                .map(Authority::getCode)
                .collect(Collectors.toList());
    }
}
