/*
 * Copyright (c) 1997, 1998, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.imbge;

/**
  * An interfbce for objects thbt wish to be informed when tiles
  * of b WritbbleRenderedImbge become modifibble by some writer vib
  * b cbll to getWritbbleTile, bnd when they become unmodifibble vib
  * the lbst cbll to relebseWritbbleTile.
  *
  * @see WritbbleRenderedImbge
  *
  * @buthor Thombs DeWeese
  * @buthor Dbniel Rice
  */
public interfbce TileObserver {

  /**
    * A tile is bbout to be updbted (it is either bbout to be grbbbed
    * for writing, or it is being relebsed from writing).
    *
    * @pbrbm source the imbge thbt owns the tile.
    * @pbrbm tileX the X index of the tile thbt is being updbted.
    * @pbrbm tileY the Y index of the tile thbt is being updbted.
    * @pbrbm willBeWritbble  If true, the tile will be grbbbed for writing;
    *                        otherwise it is being relebsed.
    */
    public void tileUpdbte(WritbbleRenderedImbge source,
                           int tileX, int tileY,
                           boolebn willBeWritbble);

}
