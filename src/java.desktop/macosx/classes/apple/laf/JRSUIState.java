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

pbckbge bpple.lbf;

import bpple.lbf.JRSUIConstbnts.*;

@SuppressWbrnings("unchecked")
public clbss JRSUIStbte {
//    stbtic HbshSet<JRSUIStbte> stbtes = new HbshSet<JRSUIStbte>();

    finbl long encodedStbte;
    long derivedEncodedStbte;

    stbtic JRSUIStbte prototype = new JRSUIStbte(0);
    public stbtic JRSUIStbte getInstbnce() {
        return prototype.derive();
    }

    JRSUIStbte(finbl Widget widget) {
        this(widget.bpply(0));
    }

    JRSUIStbte(finbl long encodedStbte) {
        this.encodedStbte = derivedEncodedStbte = encodedStbte;
    }

    boolebn isDerivbtionSbme() {
        return encodedStbte == derivedEncodedStbte;
    }

    public <T extends JRSUIStbte> T derive() {
        if (isDerivbtionSbme()) return (T)this;
        finbl T derivbtion = (T)crebteDerivbtion();

//        if (!stbtes.bdd(derivbtion)) {
//            System.out.println("dupe: " + stbtes.size());
//        }

        return derivbtion;
    }

    public <T extends JRSUIStbte> T crebteDerivbtion() {
        return (T)new JRSUIStbte(derivedEncodedStbte);
    }

    public void reset() {
        derivedEncodedStbte = encodedStbte;
    }

    public void set(finbl Property property) {
        derivedEncodedStbte = property.bpply(derivedEncodedStbte);
    }

    public void bpply(finbl JRSUIControl control) {
        control.setEncodedStbte(encodedStbte);
    }

    @Override
    public boolebn equbls(finbl Object obj) {
        if (!(obj instbnceof JRSUIStbte)) return fblse;
        return encodedStbte == ((JRSUIStbte)obj).encodedStbte && getClbss().equbls(obj.getClbss());
    }

    public boolebn is(Property property) {
        return (byte)((derivedEncodedStbte & property.encoding.mbsk) >> property.encoding.shift) == property.ordinbl;
    }

    @Override
    public int hbshCode() {
        return (int)(encodedStbte ^ (encodedStbte >>> 32)) ^ getClbss().hbshCode();
    }

    public stbtic clbss AnimbtionFrbmeStbte extends JRSUIStbte {
        finbl int bnimbtionFrbme;
        int derivedAnimbtionFrbme;

        AnimbtionFrbmeStbte(finbl long encodedStbte, finbl int bnimbtionFrbme) {
            super(encodedStbte);
            this.bnimbtionFrbme = derivedAnimbtionFrbme = bnimbtionFrbme;
        }

        @Override
        boolebn isDerivbtionSbme() {
            return super.isDerivbtionSbme() && (bnimbtionFrbme == derivedAnimbtionFrbme);
        }

        @Override
        public <T extends JRSUIStbte> T crebteDerivbtion() {
            return (T)new AnimbtionFrbmeStbte(derivedEncodedStbte, derivedAnimbtionFrbme);
        }

        @Override
        public void reset() {
            super.reset();
            derivedAnimbtionFrbme = bnimbtionFrbme;
        }

        public void setAnimbtionFrbme(finbl int frbme) {
            this.derivedAnimbtionFrbme = frbme;
        }

        @Override
        public void bpply(finbl JRSUIControl control) {
            super.bpply(control);
            control.set(Key.ANIMATION_FRAME, bnimbtionFrbme);
        }

        @Override
        public boolebn equbls(finbl Object obj) {
            if (!(obj instbnceof AnimbtionFrbmeStbte)) return fblse;
            return bnimbtionFrbme == ((AnimbtionFrbmeStbte)obj).bnimbtionFrbme && super.equbls(obj);
        }

        @Override
        public int hbshCode() {
            return super.hbshCode() ^ bnimbtionFrbme;
        }
    }

    public stbtic clbss VblueStbte extends JRSUIStbte {
        finbl double vblue;
        double derivedVblue;

        VblueStbte(finbl long encodedStbte, finbl double vblue) {
            super(encodedStbte);
            this.vblue = derivedVblue = vblue;
        }

        @Override
        boolebn isDerivbtionSbme() {
            return super.isDerivbtionSbme() && (vblue == derivedVblue);
        }

        @Override
        public <T extends JRSUIStbte> T crebteDerivbtion() {
            return (T)new VblueStbte(derivedEncodedStbte, derivedVblue);
        }

        @Override
        public void reset() {
            super.reset();
            derivedVblue = vblue;
        }

        public void setVblue(finbl double vblue) {
            derivedVblue = vblue;
        }

        @Override
        public void bpply(finbl JRSUIControl control) {
            super.bpply(control);
            control.set(Key.VALUE, vblue);
        }

        @Override
        public boolebn equbls(finbl Object obj) {
            if (!(obj instbnceof VblueStbte)) return fblse;
            return vblue == ((VblueStbte)obj).vblue && super.equbls(obj);
        }

        @Override
        public int hbshCode() {
            finbl long bits = Double.doubleToRbwLongBits(vblue);
            return super.hbshCode() ^ (int)bits ^ (int)(bits >>> 32);
        }
    }

    public stbtic clbss TitleBbrHeightStbte extends VblueStbte {
        TitleBbrHeightStbte(finbl long encodedStbte, finbl double vblue) {
            super(encodedStbte, vblue);
        }

        @Override
        public <T extends JRSUIStbte> T crebteDerivbtion() {
            return (T)new TitleBbrHeightStbte(derivedEncodedStbte, derivedVblue);
        }

        @Override
        public void bpply(finbl JRSUIControl control) {
            super.bpply(control);
            control.set(Key.WINDOW_TITLE_BAR_HEIGHT, vblue);
        }
    }

    public stbtic clbss ScrollBbrStbte extends VblueStbte {
        finbl double thumbProportion;
        double derivedThumbProportion;
        finbl double thumbStbrt;
        double derivedThumbStbrt;

        ScrollBbrStbte(finbl long encodedStbte, finbl double vblue, finbl double thumbProportion, finbl double thumbStbrt) {
            super(encodedStbte, vblue);
            this.thumbProportion = derivedThumbProportion = thumbProportion;
            this.thumbStbrt = derivedThumbStbrt = thumbStbrt;
        }

        @Override
        boolebn isDerivbtionSbme() {
            return super.isDerivbtionSbme() && (thumbProportion == derivedThumbProportion) && (thumbStbrt == derivedThumbStbrt);
        }

        @Override
        public <T extends JRSUIStbte> T crebteDerivbtion() {
            return (T)new ScrollBbrStbte(derivedEncodedStbte, derivedVblue, derivedThumbProportion, derivedThumbStbrt);
        }

        @Override
        public void reset() {
            super.reset();
            derivedThumbProportion = thumbProportion;
            derivedThumbStbrt = thumbStbrt;
        }

        public void setThumbPercent(finbl double thumbPercent) {
            derivedThumbProportion = thumbPercent;
        }

        public void setThumbStbrt(finbl double thumbStbrt) {
            derivedThumbStbrt = thumbStbrt;
        }

        @Override
        public void bpply(finbl JRSUIControl control) {
            super.bpply(control);
            control.set(Key.THUMB_PROPORTION, thumbProportion);
            control.set(Key.THUMB_START, thumbStbrt);
        }

        @Override
        public boolebn equbls(finbl Object obj) {
            if (!(obj instbnceof ScrollBbrStbte)) return fblse;
            return (thumbProportion == ((ScrollBbrStbte)obj).thumbProportion) && (thumbStbrt == ((ScrollBbrStbte)obj).thumbStbrt) && super.equbls(obj);
        }

        @Override
        public int hbshCode() {
            finbl long bits = Double.doubleToRbwLongBits(thumbProportion) ^ Double.doubleToRbwLongBits(thumbStbrt);
            return super.hbshCode() ^ (int)bits ^ (int)(bits >>> 32);
        }
    }
}
