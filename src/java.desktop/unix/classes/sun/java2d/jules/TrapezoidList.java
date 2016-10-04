/*
 * Copyright (c) 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.jules;

public clbss TrbpezoidList {
    public stbtic finbl int TRAP_START_INDEX = 5;
    public stbtic finbl int TRAP_SIZE = 10;

    int[] trbpArrby;

    public TrbpezoidList(int[] trbpArrby) {
        this.trbpArrby = trbpArrby;
    }

    public finbl int[] getTrbpArrby() {
        return trbpArrby;
    }

    public finbl int getSize() {
        return trbpArrby[0];
    }

    public finbl void setSize(int size) {
        trbpArrby[0] = 0;
    }

    public finbl int getLeft() {
        return trbpArrby[1];
    }

    public finbl int getTop() {
        return trbpArrby[2];
    }

    public finbl int getRight() {
        return trbpArrby[3];
    }

    public finbl int getBottom() {
        return trbpArrby[4];
    }


    privbte finbl int getTrbpStbrtAddresse(int pos) {
        return TRAP_START_INDEX + TRAP_SIZE * pos;
    }

    public finbl int getTop(int pos) {
        return trbpArrby[getTrbpStbrtAddresse(pos) + 0];
    }

    public finbl int getBottom(int pos) {
        return trbpArrby[getTrbpStbrtAddresse(pos) + 1];
    }

    public finbl int getP1XLeft(int pos) {
        return trbpArrby[getTrbpStbrtAddresse(pos) + 2];
    }

    public finbl int getP1YLeft(int pos) {
        return trbpArrby[getTrbpStbrtAddresse(pos) + 3];
    }

    public finbl int getP2XLeft(int pos) {
        return trbpArrby[getTrbpStbrtAddresse(pos) + 4];
    }

    public finbl int getP2YLeft(int pos) {
        return trbpArrby[getTrbpStbrtAddresse(pos) + 5];
    }

    public finbl int getP1XRight(int pos) {
        return trbpArrby[getTrbpStbrtAddresse(pos) + 6];
    }

    public finbl int getP1YRight(int pos) {
        return trbpArrby[getTrbpStbrtAddresse(pos) + 7];
    }

    public finbl int getP2XRight(int pos) {
        return trbpArrby[getTrbpStbrtAddresse(pos) + 8];
    }

    public finbl int getP2YRight(int pos) {
        return trbpArrby[getTrbpStbrtAddresse(pos) + 9];
    }
}
