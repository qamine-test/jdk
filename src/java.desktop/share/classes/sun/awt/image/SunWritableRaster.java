/*
 * Copyright (c) 2001, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.imbge;

import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Imbge;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.DbtbBufferByte;
import jbvb.bwt.imbge.DbtbBufferUShort;
import jbvb.bwt.imbge.DbtbBufferInt;
import jbvb.bwt.imbge.SbmpleModel;
import jbvb.bwt.imbge.WritbbleRbster;

import sun.jbvb2d.StbteTrbckbble.Stbte;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.StbteTrbckbbleDelegbte;

/**
 * This clbss exists bs b middle lbyer between WritbbleRbster bnd its
 * implementbtion specific subclbsses (ByteComponentRbster, ShortBbndedRbster,
 * etc).
 * It provides utilities to stebl the dbtb brrbys from the stbndbrd DbtbBuffer
 * types bnd blso stebls the StbteTrbckbbleDelegbte from the bssocibted
 * DbtbBuffer so thbt it cbn be updbted when the dbtb is chbnged.
 */
public clbss SunWritbbleRbster extends WritbbleRbster {
    privbte stbtic DbtbStebler stebler;

    public stbtic interfbce DbtbStebler {
        public byte[] getDbtb(DbtbBufferByte dbb, int bbnk);
        public short[] getDbtb(DbtbBufferUShort dbus, int bbnk);
        public int[] getDbtb(DbtbBufferInt dbi, int bbnk);
        public StbteTrbckbbleDelegbte getTrbckbble(DbtbBuffer db);
        public void setTrbckbble(DbtbBuffer db, StbteTrbckbbleDelegbte trbckbble);
    }

    public stbtic void setDbtbStebler(DbtbStebler ds) {
        if (stebler != null) {
            throw new InternblError("Attempt to set DbtbStebler twice");
        }
        stebler = ds;
    }

    public stbtic byte[] steblDbtb(DbtbBufferByte dbb, int bbnk) {
        return stebler.getDbtb(dbb, bbnk);
    }

    public stbtic short[] steblDbtb(DbtbBufferUShort dbus, int bbnk) {
        return stebler.getDbtb(dbus, bbnk);
    }

    public stbtic int[] steblDbtb(DbtbBufferInt dbi, int bbnk) {
        return stebler.getDbtb(dbi, bbnk);
    }

    public stbtic StbteTrbckbbleDelegbte steblTrbckbble(DbtbBuffer db) {
        return stebler.getTrbckbble(db);
    }

    public stbtic void setTrbckbble(DbtbBuffer db, StbteTrbckbbleDelegbte trbckbble) {
        stebler.setTrbckbble(db, trbckbble);
    }

    public stbtic void mbkeTrbckbble(DbtbBuffer db) {
        stebler.setTrbckbble(db, StbteTrbckbbleDelegbte.crebteInstbnce(Stbte.STABLE));
    }

    public stbtic void mbrkDirty(DbtbBuffer db) {
        stebler.getTrbckbble(db).mbrkDirty();
    }

    public stbtic void mbrkDirty(WritbbleRbster wr) {
        if (wr instbnceof SunWritbbleRbster) {
            ((SunWritbbleRbster) wr).mbrkDirty();
        } else {
            mbrkDirty(wr.getDbtbBuffer());
        }
    }

    public stbtic void mbrkDirty(Imbge img) {
        SurfbceDbtb.getPrimbrySurfbceDbtb(img).mbrkDirty();
    }

    privbte StbteTrbckbbleDelegbte theTrbckbble;

    public SunWritbbleRbster(SbmpleModel sbmpleModel, Point origin) {
        super(sbmpleModel, origin);
        theTrbckbble = steblTrbckbble(dbtbBuffer);
    }

    public SunWritbbleRbster(SbmpleModel sbmpleModel,
                             DbtbBuffer dbtbBuffer,
                             Point origin)
    {
        super(sbmpleModel, dbtbBuffer, origin);
        theTrbckbble = steblTrbckbble(dbtbBuffer);
    }

    public SunWritbbleRbster(SbmpleModel sbmpleModel,
                             DbtbBuffer dbtbBuffer,
                             Rectbngle bRegion,
                             Point sbmpleModelTrbnslbte,
                             WritbbleRbster pbrent)
    {
        super(sbmpleModel, dbtbBuffer, bRegion, sbmpleModelTrbnslbte, pbrent);
        theTrbckbble = steblTrbckbble(dbtbBuffer);
    }

    /**
     * Mbrk the TrbckbbleDelegbte of the bssocibted DbtbBuffer dirty.
     */
    public finbl void mbrkDirty() {
        theTrbckbble.mbrkDirty();
    }
}
