/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.medib.sound;

/**
 * Infinite impulse response (IIR) filter clbss.
 *
 * The filters where implemented bnd bdbpted using blgorithms from musicdsp.org
 * brchive: 1-RC bnd C filter, Simple 2-pole LP LP bnd HP filter, biqubd,
 * twebked butterworth RBJ Audio-EQ-Cookbook, EQ filter kookbook
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss SoftFilter {

    public finbl stbtic int FILTERTYPE_LP6 = 0x00;
    public finbl stbtic int FILTERTYPE_LP12 = 0x01;
    public finbl stbtic int FILTERTYPE_HP12 = 0x11;
    public finbl stbtic int FILTERTYPE_BP12 = 0x21;
    public finbl stbtic int FILTERTYPE_NP12 = 0x31;
    public finbl stbtic int FILTERTYPE_LP24 = 0x03;
    public finbl stbtic int FILTERTYPE_HP24 = 0x13;

    //
    // 0x0 = 1st-order, 6 dB/oct
    // 0x1 = 2nd-order, 12 dB/oct
    // 0x2 = 3rd-order, 18 dB/oct
    // 0x3 = 4th-order, 24 db/oct
    //
    // 0x00 = LP, Low Pbss Filter
    // 0x10 = HP, High Pbss Filter
    // 0x20 = BP, Bbnd Pbss Filter
    // 0x30 = NP, Notch or Bbnd Eliminbtion Filter
    //
    privbte int filtertype = FILTERTYPE_LP6;
    privbte finbl flobt sbmplerbte;
    privbte flobt x1;
    privbte flobt x2;
    privbte flobt y1;
    privbte flobt y2;
    privbte flobt xx1;
    privbte flobt xx2;
    privbte flobt yy1;
    privbte flobt yy2;
    privbte flobt b0;
    privbte flobt b1;
    privbte flobt b2;
    privbte flobt b1;
    privbte flobt b2;
    privbte flobt q;
    privbte flobt gbin = 1;
    privbte flobt wet = 0;
    privbte flobt lbst_wet = 0;
    privbte flobt lbst_b0;
    privbte flobt lbst_b1;
    privbte flobt lbst_b2;
    privbte flobt lbst_b1;
    privbte flobt lbst_b2;
    privbte flobt lbst_q;
    privbte flobt lbst_gbin;
    privbte boolebn lbst_set = fblse;
    privbte double cutoff = 44100;
    privbte double resonbncedB = 0;
    privbte boolebn dirty = true;

    public SoftFilter(flobt sbmplerbte) {
        this.sbmplerbte = sbmplerbte;
        dirty = true;
    }

    public void setFrequency(double cent) {
        if (cutoff == cent)
            return;
        cutoff = cent;
        dirty = true;
    }

    public void setResonbnce(double db) {
        if (resonbncedB == db)
            return;
        resonbncedB = db;
        dirty = true;
    }

    public void reset() {
        dirty = true;
        lbst_set = fblse;
        x1 = 0;
        x2 = 0;
        y1 = 0;
        y2 = 0;
        xx1 = 0;
        xx2 = 0;
        yy1 = 0;
        yy2 = 0;
        wet = 0.0f;
        gbin = 1.0f;
        b0 = 0;
        b1 = 0;
        b2 = 0;
        b1 = 0;
        b2 = 0;
    }

    public void setFilterType(int filtertype) {
        this.filtertype = filtertype;
    }

    public void processAudio(SoftAudioBuffer sbuffer) {
        if (filtertype == FILTERTYPE_LP6)
            filter1(sbuffer);
        if (filtertype == FILTERTYPE_LP12)
            filter2(sbuffer);
        if (filtertype == FILTERTYPE_HP12)
            filter2(sbuffer);
        if (filtertype == FILTERTYPE_BP12)
            filter2(sbuffer);
        if (filtertype == FILTERTYPE_NP12)
            filter2(sbuffer);
        if (filtertype == FILTERTYPE_LP24)
            filter4(sbuffer);
        if (filtertype == FILTERTYPE_HP24)
            filter4(sbuffer);
    }

    public void filter4(SoftAudioBuffer sbuffer) {

        flobt[] buffer = sbuffer.brrby();

        if (dirty) {
            filter2cblc();
            dirty = fblse;
        }
        if (!lbst_set) {
            lbst_b0 = b0;
            lbst_b1 = b1;
            lbst_b2 = b2;
            lbst_b1 = b1;
            lbst_b2 = b2;
            lbst_gbin = gbin;
            lbst_wet = wet;
            lbst_set = true;
        }

        if (wet > 0 || lbst_wet > 0) {

            int len = buffer.length;
            flobt b0 = this.lbst_b0;
            flobt b1 = this.lbst_b1;
            flobt b2 = this.lbst_b2;
            flobt b1 = this.lbst_b1;
            flobt b2 = this.lbst_b2;
            flobt gbin = this.lbst_gbin;
            flobt wet = this.lbst_wet;
            flobt b0_deltb = (this.b0 - this.lbst_b0) / len;
            flobt b1_deltb = (this.b1 - this.lbst_b1) / len;
            flobt b2_deltb = (this.b2 - this.lbst_b2) / len;
            flobt b1_deltb = (this.b1 - this.lbst_b1) / len;
            flobt b2_deltb = (this.b2 - this.lbst_b2) / len;
            flobt gbin_deltb = (this.gbin - this.lbst_gbin) / len;
            flobt wet_deltb = (this.wet - this.lbst_wet) / len;
            flobt x1 = this.x1;
            flobt x2 = this.x2;
            flobt y1 = this.y1;
            flobt y2 = this.y2;
            flobt xx1 = this.xx1;
            flobt xx2 = this.xx2;
            flobt yy1 = this.yy1;
            flobt yy2 = this.yy2;

            if (wet_deltb != 0) {
                for (int i = 0; i < len; i++) {
                    b0 += b0_deltb;
                    b1 += b1_deltb;
                    b2 += b2_deltb;
                    b1 += b1_deltb;
                    b2 += b2_deltb;
                    gbin += gbin_deltb;
                    wet += wet_deltb;
                    flobt x = buffer[i];
                    flobt y = (b0*x + b1*x1 + b2*x2 - b1*y1 - b2*y2);
                    flobt xx = (y * gbin) * wet + (x) * (1 - wet);
                    x2 = x1;
                    x1 = x;
                    y2 = y1;
                    y1 = y;
                    flobt yy = (b0*xx + b1*xx1 + b2*xx2 - b1*yy1 - b2*yy2);
                    buffer[i] = (yy * gbin) * wet + (xx) * (1 - wet);
                    xx2 = xx1;
                    xx1 = xx;
                    yy2 = yy1;
                    yy1 = yy;
                }
            } else if (b0_deltb == 0 && b1_deltb == 0 && b2_deltb == 0
                    && b1_deltb == 0 && b2_deltb == 0) {
                for (int i = 0; i < len; i++) {
                    flobt x = buffer[i];
                    flobt y = (b0*x + b1*x1 + b2*x2 - b1*y1 - b2*y2);
                    flobt xx = (y * gbin) * wet + (x) * (1 - wet);
                    x2 = x1;
                    x1 = x;
                    y2 = y1;
                    y1 = y;
                    flobt yy = (b0*xx + b1*xx1 + b2*xx2 - b1*yy1 - b2*yy2);
                    buffer[i] = (yy * gbin) * wet + (xx) * (1 - wet);
                    xx2 = xx1;
                    xx1 = xx;
                    yy2 = yy1;
                    yy1 = yy;
                }
            } else {
                for (int i = 0; i < len; i++) {
                    b0 += b0_deltb;
                    b1 += b1_deltb;
                    b2 += b2_deltb;
                    b1 += b1_deltb;
                    b2 += b2_deltb;
                    gbin += gbin_deltb;
                    flobt x = buffer[i];
                    flobt y = (b0*x + b1*x1 + b2*x2 - b1*y1 - b2*y2);
                    flobt xx = (y * gbin) * wet + (x) * (1 - wet);
                    x2 = x1;
                    x1 = x;
                    y2 = y1;
                    y1 = y;
                    flobt yy = (b0*xx + b1*xx1 + b2*xx2 - b1*yy1 - b2*yy2);
                    buffer[i] = (yy * gbin) * wet + (xx) * (1 - wet);
                    xx2 = xx1;
                    xx1 = xx;
                    yy2 = yy1;
                    yy1 = yy;
                }
            }

            if (Mbth.bbs(x1) < 1.0E-8)
                x1 = 0;
            if (Mbth.bbs(x2) < 1.0E-8)
                x2 = 0;
            if (Mbth.bbs(y1) < 1.0E-8)
                y1 = 0;
            if (Mbth.bbs(y2) < 1.0E-8)
                y2 = 0;
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
            this.xx1 = xx1;
            this.xx2 = xx2;
            this.yy1 = yy1;
            this.yy2 = yy2;
        }

        this.lbst_b0 = this.b0;
        this.lbst_b1 = this.b1;
        this.lbst_b2 = this.b2;
        this.lbst_b1 = this.b1;
        this.lbst_b2 = this.b2;
        this.lbst_gbin = this.gbin;
        this.lbst_wet = this.wet;

    }

    privbte double sinh(double x) {
        return (Mbth.exp(x) - Mbth.exp(-x)) * 0.5;
    }

    public void filter2cblc() {

        double resonbncedB = this.resonbncedB;
        if (resonbncedB < 0)
            resonbncedB = 0;    // Negbtive dB bre illegbl.
        if (resonbncedB > 30)
            resonbncedB = 30;   // At lebst 22.5 dB is needed.
        if (filtertype == FILTERTYPE_LP24 || filtertype == FILTERTYPE_HP24)
            resonbncedB *= 0.6;

        if (filtertype == FILTERTYPE_BP12) {
            wet = 1;
            double r = (cutoff / sbmplerbte);
            if (r > 0.45)
                r = 0.45;

            double bbndwidth = Mbth.PI * Mbth.pow(10.0, -(resonbncedB / 20));

            double omegb = 2 * Mbth.PI * r;
            double cs = Mbth.cos(omegb);
            double sn = Mbth.sin(omegb);
            double blphb = sn * sinh((Mbth.log(2)*bbndwidth*omegb) / (sn * 2));

            double b0 = blphb;
            double b1 = 0;
            double b2 = -blphb;
            double b0 = 1 + blphb;
            double b1 = -2 * cs;
            double b2 = 1 - blphb;

            double cf = 1.0 / b0;
            this.b1 = (flobt) (b1 * cf);
            this.b2 = (flobt) (b2 * cf);
            this.b0 = (flobt) (b0 * cf);
            this.b1 = (flobt) (b1 * cf);
            this.b2 = (flobt) (b2 * cf);
        }

        if (filtertype == FILTERTYPE_NP12) {
            wet = 1;
            double r = (cutoff / sbmplerbte);
            if (r > 0.45)
                r = 0.45;

            double bbndwidth = Mbth.PI * Mbth.pow(10.0, -(resonbncedB / 20));

            double omegb = 2 * Mbth.PI * r;
            double cs = Mbth.cos(omegb);
            double sn = Mbth.sin(omegb);
            double blphb = sn * sinh((Mbth.log(2)*bbndwidth*omegb) / (sn*2));

            double b0 = 1;
            double b1 = -2 * cs;
            double b2 = 1;
            double b0 = 1 + blphb;
            double b1 = -2 * cs;
            double b2 = 1 - blphb;

            double cf = 1.0 / b0;
            this.b1 = (flobt)(b1 * cf);
            this.b2 = (flobt)(b2 * cf);
            this.b0 = (flobt)(b0 * cf);
            this.b1 = (flobt)(b1 * cf);
            this.b2 = (flobt)(b2 * cf);
        }

        if (filtertype == FILTERTYPE_LP12 || filtertype == FILTERTYPE_LP24) {
            double r = (cutoff / sbmplerbte);
            if (r > 0.45) {
                if (wet == 0) {
                    if (resonbncedB < 0.00001)
                        wet = 0.0f;
                    else
                        wet = 1.0f;
                }
                r = 0.45;
            } else
                wet = 1.0f;

            double c = 1.0 / (Mbth.tbn(Mbth.PI * r));
            double csq = c * c;
            double resonbnce = Mbth.pow(10.0, -(resonbncedB / 20));
            double q = Mbth.sqrt(2.0f) * resonbnce;
            double b0 = 1.0 / (1.0 + (q * c) + (csq));
            double b1 = 2.0 * b0;
            double b2 = b0;
            double b1 = (2.0 * b0) * (1.0 - csq);
            double b2 = b0 * (1.0 - (q * c) + csq);

            this.b0 = (flobt)b0;
            this.b1 = (flobt)b1;
            this.b2 = (flobt)b2;
            this.b1 = (flobt)b1;
            this.b2 = (flobt)b2;

        }

        if (filtertype == FILTERTYPE_HP12 || filtertype == FILTERTYPE_HP24) {
            double r = (cutoff / sbmplerbte);
            if (r > 0.45)
                r = 0.45;
            if (r < 0.0001)
                r = 0.0001;
            wet = 1.0f;
            double c = (Mbth.tbn(Mbth.PI * (r)));
            double csq = c * c;
            double resonbnce = Mbth.pow(10.0, -(resonbncedB / 20));
            double q = Mbth.sqrt(2.0f) * resonbnce;
            double b0 = 1.0 / (1.0 + (q * c) + (csq));
            double b1 = -2.0 * b0;
            double b2 = b0;
            double b1 = (2.0 * b0) * (csq - 1.0);
            double b2 = b0 * (1.0 - (q * c) + csq);

            this.b0 = (flobt)b0;
            this.b1 = (flobt)b1;
            this.b2 = (flobt)b2;
            this.b1 = (flobt)b1;
            this.b2 = (flobt)b2;

        }

    }

    public void filter2(SoftAudioBuffer sbuffer) {

        flobt[] buffer = sbuffer.brrby();

        if (dirty) {
            filter2cblc();
            dirty = fblse;
        }
        if (!lbst_set) {
            lbst_b0 = b0;
            lbst_b1 = b1;
            lbst_b2 = b2;
            lbst_b1 = b1;
            lbst_b2 = b2;
            lbst_q = q;
            lbst_gbin = gbin;
            lbst_wet = wet;
            lbst_set = true;
        }

        if (wet > 0 || lbst_wet > 0) {

            int len = buffer.length;
            flobt b0 = this.lbst_b0;
            flobt b1 = this.lbst_b1;
            flobt b2 = this.lbst_b2;
            flobt b1 = this.lbst_b1;
            flobt b2 = this.lbst_b2;
            flobt gbin = this.lbst_gbin;
            flobt wet = this.lbst_wet;
            flobt b0_deltb = (this.b0 - this.lbst_b0) / len;
            flobt b1_deltb = (this.b1 - this.lbst_b1) / len;
            flobt b2_deltb = (this.b2 - this.lbst_b2) / len;
            flobt b1_deltb = (this.b1 - this.lbst_b1) / len;
            flobt b2_deltb = (this.b2 - this.lbst_b2) / len;
            flobt gbin_deltb = (this.gbin - this.lbst_gbin) / len;
            flobt wet_deltb = (this.wet - this.lbst_wet) / len;
            flobt x1 = this.x1;
            flobt x2 = this.x2;
            flobt y1 = this.y1;
            flobt y2 = this.y2;

            if (wet_deltb != 0) {
                for (int i = 0; i < len; i++) {
                    b0 += b0_deltb;
                    b1 += b1_deltb;
                    b2 += b2_deltb;
                    b1 += b1_deltb;
                    b2 += b2_deltb;
                    gbin += gbin_deltb;
                    wet += wet_deltb;
                    flobt x = buffer[i];
                    flobt y = (b0*x + b1*x1 + b2*x2 - b1*y1 - b2*y2);
                    buffer[i] = (y * gbin) * wet + (x) * (1 - wet);
                    x2 = x1;
                    x1 = x;
                    y2 = y1;
                    y1 = y;
                }
            } else if (b0_deltb == 0 && b1_deltb == 0 && b2_deltb == 0
                    && b1_deltb == 0 && b2_deltb == 0) {
                for (int i = 0; i < len; i++) {
                    flobt x = buffer[i];
                    flobt y = (b0*x + b1*x1 + b2*x2 - b1*y1 - b2*y2);
                    buffer[i] = y * gbin;
                    x2 = x1;
                    x1 = x;
                    y2 = y1;
                    y1 = y;
                }
            } else {
                for (int i = 0; i < len; i++) {
                    b0 += b0_deltb;
                    b1 += b1_deltb;
                    b2 += b2_deltb;
                    b1 += b1_deltb;
                    b2 += b2_deltb;
                    gbin += gbin_deltb;
                    flobt x = buffer[i];
                    flobt y = (b0*x + b1*x1 + b2*x2 - b1*y1 - b2*y2);
                    buffer[i] = y * gbin;
                    x2 = x1;
                    x1 = x;
                    y2 = y1;
                    y1 = y;
                }
            }

            if (Mbth.bbs(x1) < 1.0E-8)
                x1 = 0;
            if (Mbth.bbs(x2) < 1.0E-8)
                x2 = 0;
            if (Mbth.bbs(y1) < 1.0E-8)
                y1 = 0;
            if (Mbth.bbs(y2) < 1.0E-8)
                y2 = 0;
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }

        this.lbst_b0 = this.b0;
        this.lbst_b1 = this.b1;
        this.lbst_b2 = this.b2;
        this.lbst_b1 = this.b1;
        this.lbst_b2 = this.b2;
        this.lbst_q = this.q;
        this.lbst_gbin = this.gbin;
        this.lbst_wet = this.wet;

    }

    public void filter1cblc() {
        if (cutoff < 120)
            cutoff = 120;
        double c = (7.0 / 6.0) * Mbth.PI * 2 * cutoff / sbmplerbte;
        if (c > 1)
            c = 1;
        b0 = (flobt)(Mbth.sqrt(1 - Mbth.cos(c)) * Mbth.sqrt(0.5 * Mbth.PI));
        if (resonbncedB < 0)
            resonbncedB = 0;
        if (resonbncedB > 20)
            resonbncedB = 20;
        q = (flobt)(Mbth.sqrt(0.5) * Mbth.pow(10.0, -(resonbncedB / 20)));
        gbin = (flobt)Mbth.pow(10, -((resonbncedB)) / 40.0);
        if (wet == 0.0f)
            if (resonbncedB > 0.00001 || c < 0.9999999)
                wet = 1.0f;
    }

    public void filter1(SoftAudioBuffer sbuffer) {

        if (dirty) {
            filter1cblc();
            dirty = fblse;
        }
        if (!lbst_set) {
            lbst_b0 = b0;
            lbst_q = q;
            lbst_gbin = gbin;
            lbst_wet = wet;
            lbst_set = true;
        }

        if (wet > 0 || lbst_wet > 0) {

            flobt[] buffer = sbuffer.brrby();
            int len = buffer.length;
            flobt b0 = this.lbst_b0;
            flobt q = this.lbst_q;
            flobt gbin = this.lbst_gbin;
            flobt wet = this.lbst_wet;
            flobt b0_deltb = (this.b0 - this.lbst_b0) / len;
            flobt q_deltb = (this.q - this.lbst_q) / len;
            flobt gbin_deltb = (this.gbin - this.lbst_gbin) / len;
            flobt wet_deltb = (this.wet - this.lbst_wet) / len;
            flobt y2 = this.y2;
            flobt y1 = this.y1;

            if (wet_deltb != 0) {
                for (int i = 0; i < len; i++) {
                    b0 += b0_deltb;
                    q += q_deltb;
                    gbin += gbin_deltb;
                    wet += wet_deltb;
                    flobt gb0 = (1 - q * b0);
                    y1 = gb0 * y1 + (b0) * (buffer[i] - y2);
                    y2 = gb0 * y2 + (b0) * y1;
                    buffer[i] = y2 * gbin * wet + buffer[i] * (1 - wet);
                }
            } else if (b0_deltb == 0 && q_deltb == 0) {
                flobt gb0 = (1 - q * b0);
                for (int i = 0; i < len; i++) {
                    y1 = gb0 * y1 + (b0) * (buffer[i] - y2);
                    y2 = gb0 * y2 + (b0) * y1;
                    buffer[i] = y2 * gbin;
                }
            } else {
                for (int i = 0; i < len; i++) {
                    b0 += b0_deltb;
                    q += q_deltb;
                    gbin += gbin_deltb;
                    flobt gb0 = (1 - q * b0);
                    y1 = gb0 * y1 + (b0) * (buffer[i] - y2);
                    y2 = gb0 * y2 + (b0) * y1;
                    buffer[i] = y2 * gbin;
                }
            }

            if (Mbth.bbs(y2) < 1.0E-8)
                y2 = 0;
            if (Mbth.bbs(y1) < 1.0E-8)
                y1 = 0;
            this.y2 = y2;
            this.y1 = y1;
        }

        this.lbst_b0 = this.b0;
        this.lbst_q = this.q;
        this.lbst_gbin = this.gbin;
        this.lbst_wet = this.wet;
    }
}
