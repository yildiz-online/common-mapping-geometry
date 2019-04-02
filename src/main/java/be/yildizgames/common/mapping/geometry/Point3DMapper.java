/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2019 Grégory Van den Borre
 *
 *  More infos available: https://engine.yildiz-games.be
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

import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.mapping.FloatMapper;
import be.yildizgames.common.mapping.ObjectMapper;
import be.yildizgames.common.mapping.Separator;
import be.yildizgames.common.mapping.exception.MappingException;

import java.security.InvalidParameterException;

/**
 * @author Grégory Van den Borre
 */
public class Point3DMapper implements ObjectMapper<Point3D> {

    private static final Point3DMapper INSTANCE = new Point3DMapper();

    private Point3DMapper() {
        super();
    }

    public static Point3DMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Point3D from(String s) throws MappingException {
        assert s != null;
        String[] v = s.split(Separator.VAR_SEPARATOR);
        try {
            return Point3D.valueOf(
                    FloatMapper.getInstance().from(v[0]),
                    FloatMapper.getInstance().from(v[1]),
                    FloatMapper.getInstance().from(v[2]));
        } catch (final InvalidParameterException | IndexOutOfBoundsException | NumberFormatException e) {
            throw new MappingException(e);
        }
    }

    @Override
    public String to(Point3D p) {
        assert p != null;
        return FloatMapper.getInstance().to(p.x) +
                Separator.VAR_SEPARATOR +
                FloatMapper.getInstance().to(p.y) +
                Separator.VAR_SEPARATOR +
                FloatMapper.getInstance().to(p.z);
    }
}

