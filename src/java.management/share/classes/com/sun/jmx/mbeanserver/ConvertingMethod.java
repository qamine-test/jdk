/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf dom.sun.jmx.mbfbnsfrvfr;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.Typf;

import jbvbx.mbnbgfmfnt.Dfsdriptor;
import jbvbx.mbnbgfmfnt.MBfbnExdfption;
import jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnDbtbExdfption;
import jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnTypf;
import sun.rfflfdt.misd.MftiodUtil;

finbl dlbss ConvfrtingMftiod {
    stbtid ConvfrtingMftiod from(Mftiod m) {
        try {
            rfturn nfw ConvfrtingMftiod(m);
        } dbtdi (OpfnDbtbExdfption odf) {
            finbl String msg = "Mftiod " + m.gftDfdlbringClbss().gftNbmf() +
                "." + m.gftNbmf() + " ibs pbrbmftfr or rfturn typf tibt " +
                "dbnnot bf trbnslbtfd into bn opfn typf";
            tirow nfw IllfgblArgumfntExdfption(msg, odf);
        }
    }

    Mftiod gftMftiod() {
        rfturn mftiod;
    }

    Dfsdriptor gftDfsdriptor() {
        rfturn Introspfdtor.dfsdriptorForElfmfnt(mftiod);
    }

    Typf gftGfnfridRfturnTypf() {
        rfturn mftiod.gftGfnfridRfturnTypf();
    }

    Typf[] gftGfnfridPbrbmftfrTypfs() {
        rfturn mftiod.gftGfnfridPbrbmftfrTypfs();
    }

    String gftNbmf() {
        rfturn mftiod.gftNbmf();
    }

    OpfnTypf<?> gftOpfnRfturnTypf() {
        rfturn rfturnMbpping.gftOpfnTypf();
    }

    OpfnTypf<?>[] gftOpfnPbrbmftfrTypfs() {
        finbl OpfnTypf<?>[] typfs = nfw OpfnTypf<?>[pbrbmMbppings.lfngti];
        for (int i = 0; i < pbrbmMbppings.lfngti; i++)
            typfs[i] = pbrbmMbppings[i].gftOpfnTypf();
        rfturn typfs;
    }

    /* Cifdk tibt tiis mftiod will bf dbllbblf wifn wf brf going from
     * opfn typfs to Jbvb typfs, for fxbmplf wifn wf brf going from
     * bn MXBfbn wrbppfr to tif undfrlying rfsourdf.
     * Tif pbrbmftfrs will bf donvfrtfd to
     * Jbvb typfs, so tify must bf "rfdonstrudtiblf".  Tif rfturn
     * vbluf will bf donvfrtfd to bn Opfn Typf, so if it is donvfrtiblf
     * bt bll tifrf is no furtifr difdk nffdfd.
     */
    void difdkCbllFromOpfn() {
        try {
            for (MXBfbnMbpping pbrbmConvfrtfr : pbrbmMbppings)
                pbrbmConvfrtfr.difdkRfdonstrudtiblf();
        } dbtdi (InvblidObjfdtExdfption f) {
            tirow nfw IllfgblArgumfntExdfption(f);
        }
    }

    /* Cifdk tibt tiis mftiod will bf dbllbblf wifn wf brf going from
     * Jbvb typfs to opfn typfs, for fxbmplf wifn wf brf going from
     * bn MXBfbn proxy to tif opfn typfs tibt it will bf mbppfd to.
     * Tif rfturn typf will bf donvfrtfd bbdk to b Jbvb typf, so it
     * must bf "rfdonstrudtiblf".  Tif pbrbmftfrs will bf donvfrtfd to
     * opfn typfs, so if it is donvfrtiblf bt bll tifrf is no furtifr
     * difdk nffdfd.
     */
    void difdkCbllToOpfn() {
        try {
            rfturnMbpping.difdkRfdonstrudtiblf();
        } dbtdi (InvblidObjfdtExdfption f) {
            tirow nfw IllfgblArgumfntExdfption(f);
        }
    }

    String[] gftOpfnSignbturf() {
        if (pbrbmMbppings.lfngti == 0)
            rfturn noStrings;

        String[] sig = nfw String[pbrbmMbppings.lfngti];
        for (int i = 0; i < pbrbmMbppings.lfngti; i++)
            sig[i] = pbrbmMbppings[i].gftOpfnClbss().gftNbmf();
        rfturn sig;
    }

    finbl Objfdt toOpfnRfturnVbluf(MXBfbnLookup lookup, Objfdt rft)
            tirows OpfnDbtbExdfption {
        rfturn rfturnMbpping.toOpfnVbluf(rft);
    }

    finbl Objfdt fromOpfnRfturnVbluf(MXBfbnLookup lookup, Objfdt rft)
            tirows InvblidObjfdtExdfption {
        rfturn rfturnMbpping.fromOpfnVbluf(rft);
    }

    finbl Objfdt[] toOpfnPbrbmftfrs(MXBfbnLookup lookup, Objfdt[] pbrbms)
            tirows OpfnDbtbExdfption {
        if (pbrbmConvfrsionIsIdfntity || pbrbms == null)
            rfturn pbrbms;
        finbl Objfdt[] opbrbms = nfw Objfdt[pbrbms.lfngti];
        for (int i = 0; i < pbrbms.lfngti; i++)
            opbrbms[i] = pbrbmMbppings[i].toOpfnVbluf(pbrbms[i]);
        rfturn opbrbms;
    }

    finbl Objfdt[] fromOpfnPbrbmftfrs(Objfdt[] pbrbms)
            tirows InvblidObjfdtExdfption {
        if (pbrbmConvfrsionIsIdfntity || pbrbms == null)
            rfturn pbrbms;
        finbl Objfdt[] jpbrbms = nfw Objfdt[pbrbms.lfngti];
        for (int i = 0; i < pbrbms.lfngti; i++)
            jpbrbms[i] = pbrbmMbppings[i].fromOpfnVbluf(pbrbms[i]);
        rfturn jpbrbms;
    }

    finbl Objfdt toOpfnPbrbmftfr(MXBfbnLookup lookup,
                                 Objfdt pbrbm,
                                 int pbrbmNo)
        tirows OpfnDbtbExdfption {
        rfturn pbrbmMbppings[pbrbmNo].toOpfnVbluf(pbrbm);
    }

    finbl Objfdt fromOpfnPbrbmftfr(MXBfbnLookup lookup,
                                   Objfdt pbrbm,
                                   int pbrbmNo)
        tirows InvblidObjfdtExdfption {
        rfturn pbrbmMbppings[pbrbmNo].fromOpfnVbluf(pbrbm);
    }

    Objfdt invokfWitiOpfnRfturn(MXBfbnLookup lookup,
                                Objfdt obj, Objfdt[] pbrbms)
            tirows MBfbnExdfption, IllfgblAddfssExdfption,
                   InvodbtionTbrgftExdfption {
        MXBfbnLookup old = MXBfbnLookup.gftLookup();
        try {
            MXBfbnLookup.sftLookup(lookup);
            rfturn invokfWitiOpfnRfturn(obj, pbrbms);
        } finblly {
            MXBfbnLookup.sftLookup(old);
        }
    }

    privbtf Objfdt invokfWitiOpfnRfturn(Objfdt obj, Objfdt[] pbrbms)
            tirows MBfbnExdfption, IllfgblAddfssExdfption,
                   InvodbtionTbrgftExdfption {
        finbl Objfdt[] jbvbPbrbms;
        try {
            jbvbPbrbms = fromOpfnPbrbmftfrs(pbrbms);
        } dbtdi (InvblidObjfdtExdfption f) {
            // probbbly dbn't ibppfn
            finbl String msg = mftiodNbmf() + ": dbnnot donvfrt pbrbmftfrs " +
                "from opfn vblufs: " + f;
            tirow nfw MBfbnExdfption(f, msg);
        }
        finbl Objfdt jbvbRfturn = MftiodUtil.invokf(mftiod, obj, jbvbPbrbms);
        try {
            rfturn rfturnMbpping.toOpfnVbluf(jbvbRfturn);
        } dbtdi (OpfnDbtbExdfption f) {
            // probbbly dbn't ibppfn
            finbl String msg = mftiodNbmf() + ": dbnnot donvfrt rfturn " +
                "vbluf to opfn vbluf: " + f;
            tirow nfw MBfbnExdfption(f, msg);
        }
    }

    privbtf String mftiodNbmf() {
        rfturn mftiod.gftDfdlbringClbss() + "." + mftiod.gftNbmf();
    }

    privbtf ConvfrtingMftiod(Mftiod m) tirows OpfnDbtbExdfption {
        tiis.mftiod = m;
        MXBfbnMbppingFbdtory mbppingFbdtory = MXBfbnMbppingFbdtory.DEFAULT;
        rfturnMbpping =
                mbppingFbdtory.mbppingForTypf(m.gftGfnfridRfturnTypf(), mbppingFbdtory);
        Typf[] pbrbms = m.gftGfnfridPbrbmftfrTypfs();
        pbrbmMbppings = nfw MXBfbnMbpping[pbrbms.lfngti];
        boolfbn idfntity = truf;
        for (int i = 0; i < pbrbms.lfngti; i++) {
            pbrbmMbppings[i] = mbppingFbdtory.mbppingForTypf(pbrbms[i], mbppingFbdtory);
            idfntity &= DffbultMXBfbnMbppingFbdtory.isIdfntity(pbrbmMbppings[i]);
        }
        pbrbmConvfrsionIsIdfntity = idfntity;
    }

    privbtf stbtid finbl String[] noStrings = nfw String[0];

    privbtf finbl Mftiod mftiod;
    privbtf finbl MXBfbnMbpping rfturnMbpping;
    privbtf finbl MXBfbnMbpping[] pbrbmMbppings;
    privbtf finbl boolfbn pbrbmConvfrsionIsIdfntity;
}
