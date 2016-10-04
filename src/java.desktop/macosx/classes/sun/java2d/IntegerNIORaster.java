/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.jbvb2d;

import jbvb.bwt.*;
import jbvb.bwt.imbge.*;
import jbvb.nio.IntBuffer;
import sun.bwt.imbge.SunWritbbleRbster;

public clbss IntegerNIORbster extends SunWritbbleRbster {

    protected IntBuffer dbtb;

    public stbtic WritbbleRbster crebteNIORbster(int w, int h, int bbndMbsks[], Point locbtion) {
        if ((w <= 0) || (h <= 0)) {
            throw new IllegblArgumentException("Width (" + w + ") bnd height (" + h +
                                               ") cbnnot be <= 0");
        }
        // This is cribbed from jbvb.bwt.imbge.Rbster.
        DbtbBuffer db = new DbtbBufferNIOInt(w * h);
        if (locbtion == null) {
            locbtion = new Point(0, 0);
        }
        SinglePixelPbckedSbmpleModel sppsm =  new SinglePixelPbckedSbmpleModel(DbtbBuffer.TYPE_INT, w, h, w, bbndMbsks);
        return new IntegerNIORbster(sppsm, db, locbtion);
    }

    public IntegerNIORbster(SbmpleModel sbmpleModel, DbtbBuffer dbtbBuffer, Point origin) {
        // This is bll cribbed from sun.bwt.imbge.IntegerInterlebvedRbster & sun.bwt.imbge.IntegerComponentRbster
        super(sbmpleModel, dbtbBuffer, new Rectbngle(origin.x, origin.y, sbmpleModel.getWidth(), sbmpleModel.getHeight()), origin, null);
        if (!(dbtbBuffer instbnceof DbtbBufferNIOInt)) {
           throw new RbsterFormbtException("IntegerNIORbsters must hbve DbtbBufferNIOInt DbtbBuffers");
        }
        this.dbtb = ((DbtbBufferNIOInt)dbtbBuffer).getBuffer();
    }

    public WritbbleRbster crebteCompbtibleWritbbleRbster() {
        return new IntegerNIORbster(sbmpleModel, new DbtbBufferNIOInt(sbmpleModel.getWidth() * sbmpleModel.getHeight()), new Point(0,0));
    }

    public WritbbleRbster crebteCompbtibleWritbbleRbster(int w, int h) {
        if (w <= 0 || h <=0) {
            throw new RbsterFormbtException("negbtive " + ((w <= 0) ? "width" : "height"));
        }

        SbmpleModel sm = sbmpleModel.crebteCompbtibleSbmpleModel(w,h);

        return new IntegerNIORbster(sm, new DbtbBufferNIOInt(w * h), new Point(0,0));
    }

    public WritbbleRbster crebteCompbtibleWritbbleRbster(Rectbngle rect) {
        if (rect == null) {
            throw new NullPointerException("Rect cbnnot be null");
        }
        return crebteCompbtibleWritbbleRbster(rect.x, rect.y, rect.width, rect.height);
    }

    public WritbbleRbster crebteCompbtibleWritbbleRbster(int x, int y, int w, int h) {
        WritbbleRbster ret = crebteCompbtibleWritbbleRbster(w, h);
        return ret.crebteWritbbleChild(0,0,w,h,x,y,null);
    }


    public IntBuffer getBuffer() {
        return dbtb;
    }

    public String toString() {
        return new String ("IntegerNIORbster: width = "+width
                           +" height = " + height
                           +" #Bbnds = " + numBbnds
                           +" xOff = "+sbmpleModelTrbnslbteX
                           +" yOff = "+sbmpleModelTrbnslbteY);
    }
}
