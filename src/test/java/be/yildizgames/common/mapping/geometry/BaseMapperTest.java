/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2018 Grégory Van den Borre
 *
 *  More infos available: https://www.yildiz-games.be
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 *  documentation files (the "Software"), to deal in the Software without restriction, including without
 *  limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 *  of the Software, and to permit persons to whom the Software is furnished to do so,
 *  subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 *  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 */

package be.yildizgames.common.mapping.geometry;

import be.yildizgames.common.mapping.ObjectMapper;
import be.yildizgames.common.mapping.Separator;
import be.yildizgames.common.mapping.exception.MappingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * @author Grégory Van den Borre
 */
public abstract class BaseMapperTest<T>{

    private final ObjectMapper<T> mapper;
    private final T baseObject;

    protected BaseMapperTest(ObjectMapper<T> mapper, T baseObject) {
        this.mapper = mapper;
        this.baseObject = baseObject;
    }

    @Test
    void happyFlow() throws MappingException {
        String to = mapper.to(baseObject);
        T from = mapper.from(to);
        Assertions.assertEquals(baseObject, from);
    }

    @Test
    void tooShort() throws MappingException {
        String to = mapper.to(baseObject);
        if (to.contains(Separator.OBJECTS_SEPARATOR)) {
            Assertions.assertThrows(MappingException.class, () -> mapper.from(to.substring(0, to.indexOf(Separator.OBJECTS_SEPARATOR))));
        } else if (to.contains(Separator.VAR_SEPARATOR)) {
            Assertions.assertThrows(MappingException.class, () -> mapper.from(to.substring(0, to.indexOf(Separator.VAR_SEPARATOR))));
        } else {
            Assertions.assertThrows(MappingException.class, () -> mapper.from(""));
        }
    }

    @Test
    void fromNull() throws MappingException {
        Assertions.assertThrows(AssertionError.class, () -> mapper.from(null));
    }

    @Test
    void toNull() {
        Assertions.assertThrows(AssertionError.class, () -> mapper.to(null));
    }

}
