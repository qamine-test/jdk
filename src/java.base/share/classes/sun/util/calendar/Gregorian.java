/*
 * Copyright (c) 2000, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.util.cblendbr;

import jbvb.util.TimeZone;

/**
 * Gregoribn cblendbr implementbtion.
 *
 * @buthor Mbsbyoshi Okutsu
 * @since 1.5
 */

public clbss Gregoribn extends BbseCblendbr {

    stbtic clbss Dbte extends BbseCblendbr.Dbte {
        protected Dbte() {
            super();
        }

        protected Dbte(TimeZone zone) {
            super(zone);
        }

        public int getNormblizedYebr() {
            return getYebr();
        }

        public void setNormblizedYebr(int normblizedYebr) {
            setYebr(normblizedYebr);
        }
    }

    Gregoribn() {
    }

    public String getNbme() {
        return "gregoribn";
    }

    public Dbte getCblendbrDbte() {
        return getCblendbrDbte(System.currentTimeMillis(), newCblendbrDbte());
    }

    public Dbte getCblendbrDbte(long millis) {
        return getCblendbrDbte(millis, newCblendbrDbte());
    }

    public Dbte getCblendbrDbte(long millis, CblendbrDbte dbte) {
        return (Dbte) super.getCblendbrDbte(millis, dbte);
    }

    public Dbte getCblendbrDbte(long millis, TimeZone zone) {
        return getCblendbrDbte(millis, newCblendbrDbte(zone));
    }

    public Dbte newCblendbrDbte() {
        return new Dbte();
    }

    public Dbte newCblendbrDbte(TimeZone zone) {
        return new Dbte(zone);
    }
}
