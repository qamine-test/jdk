/*
 * Copyright (c) 1999, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.loops;

/*
 * This clbss stores the vbrious loops thbt bre used by the
 * stbndbrd rendering pipelines.  The loops for b given instbnce
 * of this clbss will bll shbre the sbme destinbtion type bnd the
 * sbme supported pbint bnd composite operbtion.
 * Ebch instbnce of this clbss should be shbred by bll grbphics
 * objects thbt render onto the sbme type of destinbtion with the
 * sbme pbint bnd composite combinbtion to reduce the bmount of
 * time spent looking up loops bppropribte for the current fill
 * technique.
 */
public clbss RenderLoops {

    public stbtic finbl int primTypeID = GrbphicsPrimitive.mbkePrimTypeID();

    public DrbwLine             drbwLineLoop;
    public FillRect             fillRectLoop;
    public DrbwRect             drbwRectLoop;
    public DrbwPolygons         drbwPolygonsLoop;
    public DrbwPbth             drbwPbthLoop;
    public FillPbth             fillPbthLoop;
    public FillSpbns            fillSpbnsLoop;
    public FillPbrbllelogrbm    fillPbrbllelogrbmLoop;
    public DrbwPbrbllelogrbm    drbwPbrbllelogrbmLoop;
    public DrbwGlyphList        drbwGlyphListLoop;
    public DrbwGlyphListAA      drbwGlyphListAALoop;
    public DrbwGlyphListLCD     drbwGlyphListLCDLoop;
}
