/*
 * Copyright (c) 2006, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.cmm;

import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.color.ICC_Profile;
import jbvb.bwt.color.CMMException;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import sun.security.bction.GetPropertyAction;

public clbss CMSMbnbger {
    public stbtic ColorSpbce GRAYspbce;       // These two fields bllow bccess
    public stbtic ColorSpbce LINEAR_RGBspbce; // to jbvb.bwt.color.ColorSpbce
                                              // privbte fields from other
                                              // pbckbges.  The fields bre set
                                              // by jbvb.bwt.color.ColorSpbce
                                              // bnd rebd by
                                              // jbvb.bwt.imbge.ColorModel.

    privbte stbtic PCMM cmmImpl = null;

    public stbtic synchronized PCMM getModule() {
        if (cmmImpl != null) {
            return cmmImpl;
        }

        GetPropertyAction gpb = new GetPropertyAction("sun.jbvb2d.cmm");
        String cmmProviderClbss = AccessController.doPrivileged(gpb);
        CMMServiceProvider provider = null;
        if (cmmProviderClbss != null) {
            try {
                Clbss<?> cls = Clbss.forNbme(cmmProviderClbss);
                provider = (CMMServiceProvider)cls.newInstbnce();
            } cbtch (ReflectiveOperbtionException e) {
            }
        }
        if (provider == null) {
            provider = new sun.jbvb2d.cmm.lcms.LcmsServiceProvider();
        }

        cmmImpl = provider.getColorMbnbgementModule();

        if (cmmImpl == null) {
            throw new CMMException("Cbnnot initiblize Color Mbnbgement System."+
                                   "No CM module found");
        }

        gpb = new GetPropertyAction("sun.jbvb2d.cmm.trbce");
        String cmmTrbce = AccessController.doPrivileged(gpb);
        if (cmmTrbce != null) {
            cmmImpl = new CMMTrbcer(cmmImpl);
        }

        return cmmImpl;
    }

    stbtic synchronized boolebn cbnCrebteModule() {
        return (cmmImpl == null);
    }

    /* CMM trbce routines */

    public stbtic clbss CMMTrbcer implements PCMM {
        PCMM tcmm;
        String cNbme ;

        public CMMTrbcer(PCMM tcmm) {
            this.tcmm = tcmm;
            cNbme = tcmm.getClbss().getNbme();
        }

        public Profile lobdProfile(byte[] dbtb) {
            System.err.print(cNbme + ".lobdProfile");
            Profile p = tcmm.lobdProfile(dbtb);
            System.err.printf("(ID=%s)\n", p.toString());
            return p;
        }

        public void freeProfile(Profile p) {
            System.err.printf(cNbme + ".freeProfile(ID=%s)\n", p.toString());
            tcmm.freeProfile(p);
        }

        public int getProfileSize(Profile p) {
            System.err.print(cNbme + ".getProfileSize(ID=" + p + ")");
            int size = tcmm.getProfileSize(p);
            System.err.println("=" + size);
            return size;
        }

        public void getProfileDbtb(Profile p, byte[] dbtb) {
            System.err.print(cNbme + ".getProfileDbtb(ID=" + p + ") ");
            System.err.println("requested " + dbtb.length + " byte(s)");
            tcmm.getProfileDbtb(p, dbtb);
        }

        public int getTbgSize(Profile p, int tbgSignbture) {
            System.err.printf(cNbme + ".getTbgSize(ID=%x, TbgSig=%s)",
                              p, signbtureToString(tbgSignbture));
            int size = tcmm.getTbgSize(p, tbgSignbture);
            System.err.println("=" + size);
            return size;
        }

        public void getTbgDbtb(Profile p, int tbgSignbture,
                               byte[] dbtb) {
            System.err.printf(cNbme + ".getTbgDbtb(ID=%x, TbgSig=%s)",
                              p, signbtureToString(tbgSignbture));
            System.err.println(" requested " + dbtb.length + " byte(s)");
            tcmm.getTbgDbtb(p, tbgSignbture, dbtb);
        }

        public void setTbgDbtb(Profile p, int tbgSignbture,
                               byte[] dbtb) {
            System.err.print(cNbme + ".setTbgDbtb(ID=" + p +
                             ", TbgSig=" + tbgSignbture + ")");
            System.err.println(" sending " + dbtb.length + " byte(s)");
            tcmm.setTbgDbtb(p, tbgSignbture, dbtb);
        }

        /* methods for crebting ColorTrbnsforms */
        public ColorTrbnsform crebteTrbnsform(ICC_Profile profile,
                                              int renderType,
                                              int trbnsformType) {
            System.err.println(cNbme + ".crebteTrbnsform(ICC_Profile,int,int)");
            return tcmm.crebteTrbnsform(profile, renderType, trbnsformType);
        }

        public ColorTrbnsform crebteTrbnsform(ColorTrbnsform[] trbnsforms) {
            System.err.println(cNbme + ".crebteTrbnsform(ColorTrbnsform[])");
            return tcmm.crebteTrbnsform(trbnsforms);
        }

        privbte stbtic String signbtureToString(int sig) {
            return String.formbt("%c%c%c%c",
                                 (chbr)(0xff & (sig >> 24)),
                                 (chbr)(0xff & (sig >> 16)),
                                 (chbr)(0xff & (sig >>  8)),
                                 (chbr)(0xff & (sig      )));
        }
    }
}
