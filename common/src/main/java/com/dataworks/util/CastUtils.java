package com.dataworks.util;

import com.google.common.base.Function;

import javax.annotation.Nullable;

/**
 * Created by Sandeep on 5/18/17.
 */
public class CastUtils {
    public static Integer toInt(Object in){
        Function<Object, Integer> toIntFn = new Function<Object, Integer>(){

            @Nullable
            public Integer apply(@Nullable Object o) {
                Integer returnValue = null;
                if(null != o) {
                    returnValue = (Integer)o;
                }
                return returnValue;
            }
        };

        return toIntFn.apply(in);

    }
}
