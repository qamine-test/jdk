/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.dnd;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Cursor;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.HfbdlfssExdfption;
import jbvb.bwt.Imbgf;
import jbvb.bwt.Point;
import jbvb.bwt.Toolkit;
import jbvb.bwt.dbtbtrbnsffr.FlbvorMbp;
import jbvb.bwt.dbtbtrbnsffr.SystfmFlbvorMbp;
import jbvb.bwt.dbtbtrbnsffr.Trbnsffrbblf;
import jbvb.bwt.dnd.pffr.DrbgSourdfContfxtPffr;
import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.Sfriblizbblf;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.util.EvfntListfnfr;
import sun.bwt.dnd.SunDrbgSourdfContfxtPffr;
import sun.sfdurity.bdtion.GftIntfgfrAdtion;


/**
 * Tif <dodf>DrbgSourdf</dodf> is tif fntity rfsponsiblf
 * for tif initibtion of tif Drbg
 * bnd Drop opfrbtion, bnd mby bf usfd in b numbfr of sdfnbrios:
 * <UL>
 * <LI>1 dffbult instbndf pfr JVM for tif lifftimf of tibt JVM.
 * <LI>1 instbndf pfr dlbss of potfntibl Drbg Initibtor objfdt (f.g
 * TfxtFifld). [implfmfntbtion dfpfndfnt]
 * <LI>1 pfr instbndf of b pbrtidulbr
 * <dodf>Componfnt</dodf>, or bpplidbtion spfdifid
 * objfdt bssodibtfd witi b <dodf>Componfnt</dodf>
 * instbndf in tif GUI. [implfmfntbtion dfpfndfnt]
 * <LI>Somf otifr brbitrbry bssodibtion. [implfmfntbtion dfpfndfnt]
 *</UL>
 *
 * Ondf tif <dodf>DrbgSourdf</dodf> is
 * obtbinfd, b <dodf>DrbgGfsturfRfdognizfr</dodf> siould
 * blso bf obtbinfd to bssodibtf tif <dodf>DrbgSourdf</dodf>
 * witi b pbrtidulbr
 * <dodf>Componfnt</dodf>.
 * <P>
 * Tif initibl intfrprftbtion of tif usfr's gfsturf,
 * bnd tif subsfqufnt stbrting of tif drbg opfrbtion
 * brf tif rfsponsibility of tif implfmfnting
 * <dodf>Componfnt</dodf>, wiidi is usublly
 * implfmfntfd by b <dodf>DrbgGfsturfRfdognizfr</dodf>.
 *<P>
 * Wifn b drbg gfsturf oddurs, tif
 * <dodf>DrbgSourdf</dodf>'s
 * stbrtDrbg() mftiod sibll bf
 * invokfd in ordfr to dbusf prodfssing
 * of tif usfr's nbvigbtionbl
 * gfsturfs bnd dflivfry of Drbg bnd Drop
 * protodol notifidbtions. A
 * <dodf>DrbgSourdf</dodf> sibll only
 * pfrmit b singlf Drbg bnd Drop opfrbtion to bf
 * durrfnt bt bny onf timf, bnd sibll
 * rfjfdt bny furtifr stbrtDrbg() rfqufsts
 * by tirowing bn <dodf>IllfgblDnDOpfrbtionExdfption</dodf>
 * until sudi timf bs tif fxtbnt opfrbtion is domplftf.
 * <P>
 * Tif stbrtDrbg() mftiod invokfs tif
 * drfbtfDrbgSourdfContfxt() mftiod to
 * instbntibtf bn bppropribtf
 * <dodf>DrbgSourdfContfxt</dodf>
 * bnd bssodibtf tif <dodf>DrbgSourdfContfxtPffr</dodf>
 * witi tibt.
 * <P>
 * If tif Drbg bnd Drop Systfm is
 * unbblf to initibtf b drbg opfrbtion for
 * somf rfbson, tif stbrtDrbg() mftiod tirows
 * b <dodf>jbvb.bwt.dnd.InvblidDnDOpfrbtionExdfption</dodf>
 * to signbl sudi b dondition. Typidblly tiis
 * fxdfption is tirown wifn tif undfrlying plbtform
 * systfm is fitifr not in b stbtf to
 * initibtf b drbg, or tif pbrbmftfrs spfdififd brf invblid.
 * <P>
 * Notf tibt during tif drbg, tif
 * sft of opfrbtions fxposfd by tif sourdf
 * bt tif stbrt of tif drbg opfrbtion mby not dibngf
 * until tif opfrbtion is domplftf.
 * Tif opfrbtion(s) brf donstbnt for tif
 * durbtion of tif opfrbtion witi rfspfdt to tif
 * <dodf>DrbgSourdf</dodf>.
 *
 * @sindf 1.2
 */

publid dlbss DrbgSourdf implfmfnts Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = 6236096958971414066L;

    /*
     * lobd b systfm dffbult dursor
     */

    privbtf stbtid Cursor lobd(String nbmf) {
        if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
            rfturn null;
        }

        try {
            rfturn (Cursor)Toolkit.gftDffbultToolkit().gftDfsktopPropfrty(nbmf);
        } dbtdi (Exdfption f) {
            f.printStbdkTrbdf();

            tirow nfw RuntimfExdfption("fbilfd to lobd systfm dursor: " + nbmf + " : " + f.gftMfssbgf());
        }
    }


    /**
     * Tif dffbult <dodf>Cursor</dodf> to usf witi b dopy opfrbtion indidbting
     * tibt b drop is durrfntly bllowfd. <dodf>null</dodf> if
     * <dodf>GrbpiidsEnvironmfnt.isHfbdlfss()</dodf> rfturns <dodf>truf</dodf>.
     *
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid stbtid finbl Cursor DffbultCopyDrop =
        lobd("DnD.Cursor.CopyDrop");

    /**
     * Tif dffbult <dodf>Cursor</dodf> to usf witi b movf opfrbtion indidbting
     * tibt b drop is durrfntly bllowfd. <dodf>null</dodf> if
     * <dodf>GrbpiidsEnvironmfnt.isHfbdlfss()</dodf> rfturns <dodf>truf</dodf>.
     *
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid stbtid finbl Cursor DffbultMovfDrop =
        lobd("DnD.Cursor.MovfDrop");

    /**
     * Tif dffbult <dodf>Cursor</dodf> to usf witi b link opfrbtion indidbting
     * tibt b drop is durrfntly bllowfd. <dodf>null</dodf> if
     * <dodf>GrbpiidsEnvironmfnt.isHfbdlfss()</dodf> rfturns <dodf>truf</dodf>.
     *
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid stbtid finbl Cursor DffbultLinkDrop =
        lobd("DnD.Cursor.LinkDrop");

    /**
     * Tif dffbult <dodf>Cursor</dodf> to usf witi b dopy opfrbtion indidbting
     * tibt b drop is durrfntly not bllowfd. <dodf>null</dodf> if
     * <dodf>GrbpiidsEnvironmfnt.isHfbdlfss()</dodf> rfturns <dodf>truf</dodf>.
     *
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid stbtid finbl Cursor DffbultCopyNoDrop =
        lobd("DnD.Cursor.CopyNoDrop");

    /**
     * Tif dffbult <dodf>Cursor</dodf> to usf witi b movf opfrbtion indidbting
     * tibt b drop is durrfntly not bllowfd. <dodf>null</dodf> if
     * <dodf>GrbpiidsEnvironmfnt.isHfbdlfss()</dodf> rfturns <dodf>truf</dodf>.
     *
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid stbtid finbl Cursor DffbultMovfNoDrop =
        lobd("DnD.Cursor.MovfNoDrop");

    /**
     * Tif dffbult <dodf>Cursor</dodf> to usf witi b link opfrbtion indidbting
     * tibt b drop is durrfntly not bllowfd. <dodf>null</dodf> if
     * <dodf>GrbpiidsEnvironmfnt.isHfbdlfss()</dodf> rfturns <dodf>truf</dodf>.
     *
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid stbtid finbl Cursor DffbultLinkNoDrop =
        lobd("DnD.Cursor.LinkNoDrop");

    privbtf stbtid finbl DrbgSourdf dflt =
        (GrbpiidsEnvironmfnt.isHfbdlfss()) ? null : nfw DrbgSourdf();

    /**
     * Intfrnbl donstbnts for sfriblizbtion.
     */
    stbtid finbl String drbgSourdfListfnfrK = "drbgSourdfL";
    stbtid finbl String drbgSourdfMotionListfnfrK = "drbgSourdfMotionL";

    /**
     * Gfts tif <dodf>DrbgSourdf</dodf> objfdt bssodibtfd witi
     * tif undfrlying plbtform.
     *
     * @rfturn tif plbtform DrbgSourdf
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     *            rfturns truf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid stbtid DrbgSourdf gftDffbultDrbgSourdf() {
        if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
            tirow nfw HfbdlfssExdfption();
        } flsf {
            rfturn dflt;
        }
    }

    /**
     * Rfports
     * wiftifr or not drbg
     * <dodf>Imbgf</dodf> support
     * is bvbilbblf on tif undfrlying plbtform.
     *
     * @rfturn if tif Drbg Imbgf support is bvbilbblf on tiis plbtform
     */

    publid stbtid boolfbn isDrbgImbgfSupportfd() {
        Toolkit t = Toolkit.gftDffbultToolkit();

        Boolfbn supportfd;

        try {
            supportfd = (Boolfbn)Toolkit.gftDffbultToolkit().gftDfsktopPropfrty("DnD.isDrbgImbgfSupportfd");

            rfturn supportfd.boolfbnVbluf();
        } dbtdi (Exdfption f) {
            rfturn fblsf;
        }
    }

    /**
     * Crfbtfs b nfw <dodf>DrbgSourdf</dodf>.
     *
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     *            rfturns truf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid DrbgSourdf() tirows HfbdlfssExdfption {
        if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
            tirow nfw HfbdlfssExdfption();
        }
    }

    /**
     * Stbrt b drbg, givfn tif <dodf>DrbgGfsturfEvfnt</dodf>
     * tibt initibtfd tif drbg, tif initibl
     * <dodf>Cursor</dodf> to usf,
     * tif <dodf>Imbgf</dodf> to drbg,
     * tif offsft of tif <dodf>Imbgf</dodf> origin
     * from tif iotspot of tif <dodf>Cursor</dodf> bt
     * tif instbnt of tif triggfr,
     * tif <dodf>Trbnsffrbblf</dodf> subjfdt dbtb
     * of tif drbg, tif <dodf>DrbgSourdfListfnfr</dodf>,
     * bnd tif <dodf>FlbvorMbp</dodf>.
     *
     * @pbrbm triggfr        tif <dodf>DrbgGfsturfEvfnt</dodf> tibt initibtfd tif drbg
     * @pbrbm drbgCursor     tif initibl {@dodf Cursor} for tiis drbg opfrbtion
     *                       or {@dodf null} for tif dffbult dursor ibndling;
     *                       sff <b irff="DrbgSourdfContfxt.itml#dffbultCursor">DrbgSourdfContfxt</b>
     *                       for morf dftbils on tif dursor ibndling mfdibnism during drbg bnd drop
     * @pbrbm drbgImbgf      tif imbgf to drbg or {@dodf null}
     * @pbrbm imbgfOffsft    tif offsft of tif <dodf>Imbgf</dodf> origin from tif iotspot
     *                       of tif <dodf>Cursor</dodf> bt tif instbnt of tif triggfr
     * @pbrbm trbnsffrbblf   tif subjfdt dbtb of tif drbg
     * @pbrbm dsl            tif <dodf>DrbgSourdfListfnfr</dodf>
     * @pbrbm flbvorMbp      tif <dodf>FlbvorMbp</dodf> to usf, or <dodf>null</dodf>
     *
     * @tirows jbvb.bwt.dnd.InvblidDnDOpfrbtionExdfption
     *    if tif Drbg bnd Drop
     *    systfm is unbblf to initibtf b drbg opfrbtion, or if tif usfr
     *    bttfmpts to stbrt b drbg wiilf bn fxisting drbg opfrbtion
     *    is still fxfduting
     */

    publid void stbrtDrbg(DrbgGfsturfEvfnt   triggfr,
                          Cursor             drbgCursor,
                          Imbgf              drbgImbgf,
                          Point              imbgfOffsft,
                          Trbnsffrbblf       trbnsffrbblf,
                          DrbgSourdfListfnfr dsl,
                          FlbvorMbp          flbvorMbp) tirows InvblidDnDOpfrbtionExdfption {

        SunDrbgSourdfContfxtPffr.sftDrbgDropInProgrfss(truf);

        try {
            if (flbvorMbp != null) tiis.flbvorMbp = flbvorMbp;

            DrbgSourdfContfxtPffr dsdp = Toolkit.gftDffbultToolkit().drfbtfDrbgSourdfContfxtPffr(triggfr);

            DrbgSourdfContfxt     dsd = drfbtfDrbgSourdfContfxt(dsdp,
                                                                triggfr,
                                                                drbgCursor,
                                                                drbgImbgf,
                                                                imbgfOffsft,
                                                                trbnsffrbblf,
                                                                dsl
                                                                );

            if (dsd == null) {
                tirow nfw InvblidDnDOpfrbtionExdfption();
            }

            dsdp.stbrtDrbg(dsd, dsd.gftCursor(), drbgImbgf, imbgfOffsft); // mby tirow
        } dbtdi (RuntimfExdfption f) {
            SunDrbgSourdfContfxtPffr.sftDrbgDropInProgrfss(fblsf);
            tirow f;
        }
    }

    /**
     * Stbrt b drbg, givfn tif <dodf>DrbgGfsturfEvfnt</dodf>
     * tibt initibtfd tif drbg, tif initibl
     * <dodf>Cursor</dodf> to usf,
     * tif <dodf>Trbnsffrbblf</dodf> subjfdt dbtb
     * of tif drbg, tif <dodf>DrbgSourdfListfnfr</dodf>,
     * bnd tif <dodf>FlbvorMbp</dodf>.
     *
     * @pbrbm triggfr        tif <dodf>DrbgGfsturfEvfnt</dodf> tibt
     * initibtfd tif drbg
     * @pbrbm drbgCursor     tif initibl {@dodf Cursor} for tiis drbg opfrbtion
     *                       or {@dodf null} for tif dffbult dursor ibndling;
     *                       sff <b irff="DrbgSourdfContfxt.itml#dffbultCursor">DrbgSourdfContfxt</b>
     *                       for morf dftbils on tif dursor ibndling mfdibnism during drbg bnd drop
     * @pbrbm trbnsffrbblf   tif subjfdt dbtb of tif drbg
     * @pbrbm dsl            tif <dodf>DrbgSourdfListfnfr</dodf>
     * @pbrbm flbvorMbp      tif <dodf>FlbvorMbp</dodf> to usf or <dodf>null</dodf>
     *
     * @tirows jbvb.bwt.dnd.InvblidDnDOpfrbtionExdfption
     *    if tif Drbg bnd Drop
     *    systfm is unbblf to initibtf b drbg opfrbtion, or if tif usfr
     *    bttfmpts to stbrt b drbg wiilf bn fxisting drbg opfrbtion
     *    is still fxfduting
     */

    publid void stbrtDrbg(DrbgGfsturfEvfnt   triggfr,
                          Cursor             drbgCursor,
                          Trbnsffrbblf       trbnsffrbblf,
                          DrbgSourdfListfnfr dsl,
                          FlbvorMbp          flbvorMbp) tirows InvblidDnDOpfrbtionExdfption {
        stbrtDrbg(triggfr, drbgCursor, null, null, trbnsffrbblf, dsl, flbvorMbp);
    }

    /**
     * Stbrt b drbg, givfn tif <dodf>DrbgGfsturfEvfnt</dodf>
     * tibt initibtfd tif drbg, tif initibl <dodf>Cursor</dodf>
     * to usf,
     * tif <dodf>Imbgf</dodf> to drbg,
     * tif offsft of tif <dodf>Imbgf</dodf> origin
     * from tif iotspot of tif <dodf>Cursor</dodf>
     * bt tif instbnt of tif triggfr,
     * tif subjfdt dbtb of tif drbg, bnd
     * tif <dodf>DrbgSourdfListfnfr</dodf>.
     *
     * @pbrbm triggfr           tif <dodf>DrbgGfsturfEvfnt</dodf> tibt initibtfd tif drbg
     * @pbrbm drbgCursor     tif initibl {@dodf Cursor} for tiis drbg opfrbtion
     *                       or {@dodf null} for tif dffbult dursor ibndling;
     *                       sff <b irff="DrbgSourdfContfxt.itml#dffbultCursor">DrbgSourdfContfxt</b>
     *                       for morf dftbils on tif dursor ibndling mfdibnism during drbg bnd drop
     * @pbrbm drbgImbgf         tif <dodf>Imbgf</dodf> to drbg or <dodf>null</dodf>
     * @pbrbm drbgOffsft        tif offsft of tif <dodf>Imbgf</dodf> origin from tif iotspot
     *                          of tif <dodf>Cursor</dodf> bt tif instbnt of tif triggfr
     * @pbrbm trbnsffrbblf      tif subjfdt dbtb of tif drbg
     * @pbrbm dsl               tif <dodf>DrbgSourdfListfnfr</dodf>
     *
     * @tirows jbvb.bwt.dnd.InvblidDnDOpfrbtionExdfption
     *    if tif Drbg bnd Drop
     *    systfm is unbblf to initibtf b drbg opfrbtion, or if tif usfr
     *    bttfmpts to stbrt b drbg wiilf bn fxisting drbg opfrbtion
     *    is still fxfduting
     */

    publid void stbrtDrbg(DrbgGfsturfEvfnt   triggfr,
                          Cursor             drbgCursor,
                          Imbgf              drbgImbgf,
                          Point              drbgOffsft,
                          Trbnsffrbblf       trbnsffrbblf,
                          DrbgSourdfListfnfr dsl) tirows InvblidDnDOpfrbtionExdfption {
        stbrtDrbg(triggfr, drbgCursor, drbgImbgf, drbgOffsft, trbnsffrbblf, dsl, null);
    }

    /**
     * Stbrt b drbg, givfn tif <dodf>DrbgGfsturfEvfnt</dodf>
     * tibt initibtfd tif drbg, tif initibl
     * <dodf>Cursor</dodf> to
     * usf,
     * tif <dodf>Trbnsffrbblf</dodf> subjfdt dbtb
     * of tif drbg, bnd tif <dodf>DrbgSourdfListfnfr</dodf>.
     *
     * @pbrbm triggfr        tif <dodf>DrbgGfsturfEvfnt</dodf> tibt initibtfd tif drbg
     * @pbrbm drbgCursor     tif initibl {@dodf Cursor} for tiis drbg opfrbtion
     *                       or {@dodf null} for tif dffbult dursor ibndling;
     *                       sff <b irff="DrbgSourdfContfxt.itml#dffbultCursor">DrbgSourdfContfxt</b> dlbss
     *                       for morf dftbils on tif dursor ibndling mfdibnism during drbg bnd drop
     * @pbrbm trbnsffrbblf      tif subjfdt dbtb of tif drbg
     * @pbrbm dsl               tif <dodf>DrbgSourdfListfnfr</dodf>
     *
     * @tirows jbvb.bwt.dnd.InvblidDnDOpfrbtionExdfption
     *    if tif Drbg bnd Drop
     *    systfm is unbblf to initibtf b drbg opfrbtion, or if tif usfr
     *    bttfmpts to stbrt b drbg wiilf bn fxisting drbg opfrbtion
     *    is still fxfduting
     */

    publid void stbrtDrbg(DrbgGfsturfEvfnt   triggfr,
                          Cursor             drbgCursor,
                          Trbnsffrbblf       trbnsffrbblf,
                          DrbgSourdfListfnfr dsl) tirows InvblidDnDOpfrbtionExdfption {
        stbrtDrbg(triggfr, drbgCursor, null, null, trbnsffrbblf, dsl, null);
    }

    /**
     * Crfbtfs tif {@dodf DrbgSourdfContfxt} to ibndlf tif durrfnt drbg
     * opfrbtion.
     * <p>
     * To indorporbtf b nfw <dodf>DrbgSourdfContfxt</dodf>
     * subdlbss, subdlbss <dodf>DrbgSourdf</dodf> bnd
     * ovfrridf tiis mftiod.
     * <p>
     * If <dodf>drbgImbgf</dodf> is <dodf>null</dodf>, no imbgf is usfd
     * to rfprfsfnt tif drbg ovfr fffdbbdk for tiis drbg opfrbtion, but
     * <dodf>NullPointfrExdfption</dodf> is not tirown.
     * <p>
     * If <dodf>dsl</dodf> is <dodf>null</dodf>, no drbg sourdf listfnfr
     * is rfgistfrfd witi tif drfbtfd <dodf>DrbgSourdfContfxt</dodf>,
     * but <dodf>NullPointfrExdfption</dodf> is not tirown.
     *
     * @pbrbm dsdp          Tif <dodf>DrbgSourdfContfxtPffr</dodf> for tiis drbg
     * @pbrbm dgl           Tif <dodf>DrbgGfsturfEvfnt</dodf> tibt triggfrfd tif
     *                      drbg
     * @pbrbm drbgCursor     Tif initibl {@dodf Cursor} for tiis drbg opfrbtion
     *                       or {@dodf null} for tif dffbult dursor ibndling;
     *                       sff <b irff="DrbgSourdfContfxt.itml#dffbultCursor">DrbgSourdfContfxt</b> dlbss
     *                       for morf dftbils on tif dursor ibndling mfdibnism during drbg bnd drop
     * @pbrbm drbgImbgf     Tif <dodf>Imbgf</dodf> to drbg or <dodf>null</dodf>
     * @pbrbm imbgfOffsft   Tif offsft of tif <dodf>Imbgf</dodf> origin from tif
     *                      iotspot of tif dursor bt tif instbnt of tif triggfr
     * @pbrbm t             Tif subjfdt dbtb of tif drbg
     * @pbrbm dsl           Tif <dodf>DrbgSourdfListfnfr</dodf>
     *
     * @rfturn tif <dodf>DrbgSourdfContfxt</dodf>
     *
     * @tirows NullPointfrExdfption if <dodf>dsdp</dodf> is <dodf>null</dodf>
     * @tirows NullPointfrExdfption if <dodf>dgl</dodf> is <dodf>null</dodf>
     * @tirows NullPointfrExdfption if <dodf>drbgImbgf</dodf> is not
     *    <dodf>null</dodf> bnd <dodf>imbgfOffsft</dodf> is <dodf>null</dodf>
     * @tirows NullPointfrExdfption if <dodf>t</dodf> is <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption if tif <dodf>Componfnt</dodf>
     *         bssodibtfd witi tif triggfr fvfnt is <dodf>null</dodf>.
     * @tirows IllfgblArgumfntExdfption if tif <dodf>DrbgSourdf</dodf> for tif
     *         triggfr fvfnt is <dodf>null</dodf>.
     * @tirows IllfgblArgumfntExdfption if tif drbg bdtion for tif
     *         triggfr fvfnt is <dodf>DnDConstbnts.ACTION_NONE</dodf>.
     * @tirows IllfgblArgumfntExdfption if tif sourdf bdtions for tif
     *         <dodf>DrbgGfsturfRfdognizfr</dodf> bssodibtfd witi tif triggfr
     *         fvfnt brf fqubl to <dodf>DnDConstbnts.ACTION_NONE</dodf>.
     */

    protfdtfd DrbgSourdfContfxt drfbtfDrbgSourdfContfxt(DrbgSourdfContfxtPffr dsdp, DrbgGfsturfEvfnt dgl, Cursor drbgCursor, Imbgf drbgImbgf, Point imbgfOffsft, Trbnsffrbblf t, DrbgSourdfListfnfr dsl) {
        rfturn nfw DrbgSourdfContfxt(dsdp, dgl, drbgCursor, drbgImbgf, imbgfOffsft, t, dsl);
    }

    /**
     * Tiis mftiod rfturns tif
     * <dodf>FlbvorMbp</dodf> for tiis <dodf>DrbgSourdf</dodf>.
     *
     * @rfturn tif <dodf>FlbvorMbp</dodf> for tiis <dodf>DrbgSourdf</dodf>
     */

    publid FlbvorMbp gftFlbvorMbp() { rfturn flbvorMbp; }

    /**
     * Crfbtfs b nfw <dodf>DrbgGfsturfRfdognizfr</dodf>
     * tibt implfmfnts tif spfdififd
     * bbstrbdt subdlbss of
     * <dodf>DrbgGfsturfRfdognizfr</dodf>, bnd
     * sfts tif spfdififd <dodf>Componfnt</dodf>
     * bnd <dodf>DrbgGfsturfListfnfr</dodf> on
     * tif nfwly drfbtfd objfdt.
     *
     * @pbrbm <T> tif typf of {@dodf DrbgGfsturfRfdognizfr} to drfbtf
     * @pbrbm rfdognizfrAbstrbdtClbss tif rfqufstfd bbstrbdt typf
     * @pbrbm bdtions                 tif pfrmittfd sourdf drbg bdtions
     * @pbrbm d                       tif <dodf>Componfnt</dodf> tbrgft
     * @pbrbm dgl        tif <dodf>DrbgGfsturfListfnfr</dodf> to notify
     *
     * @rfturn tif nfw <dodf>DrbgGfsturfRfdognizfr</dodf> or <dodf>null</dodf>
     *    if tif <dodf>Toolkit.drfbtfDrbgGfsturfRfdognizfr</dodf> mftiod
     *    ibs no implfmfntbtion bvbilbblf for
     *    tif rfqufstfd <dodf>DrbgGfsturfRfdognizfr</dodf>
     *    subdlbss bnd rfturns <dodf>null</dodf>
     */

    publid <T fxtfnds DrbgGfsturfRfdognizfr> T
        drfbtfDrbgGfsturfRfdognizfr(Clbss<T> rfdognizfrAbstrbdtClbss,
                                    Componfnt d, int bdtions,
                                    DrbgGfsturfListfnfr dgl)
    {
        rfturn Toolkit.gftDffbultToolkit().drfbtfDrbgGfsturfRfdognizfr(rfdognizfrAbstrbdtClbss, tiis, d, bdtions, dgl);
    }


    /**
     * Crfbtfs b nfw <dodf>DrbgGfsturfRfdognizfr</dodf>
     * tibt implfmfnts tif dffbult
     * bbstrbdt subdlbss of <dodf>DrbgGfsturfRfdognizfr</dodf>
     * for tiis <dodf>DrbgSourdf</dodf>,
     * bnd sfts tif spfdififd <dodf>Componfnt</dodf>
     * bnd <dodf>DrbgGfsturfListfnfr</dodf> on tif
     * nfwly drfbtfd objfdt.
     *
     * For tiis <dodf>DrbgSourdf</dodf>
     * tif dffbult is <dodf>MousfDrbgGfsturfRfdognizfr</dodf>.
     *
     * @pbrbm d       tif <dodf>Componfnt</dodf> tbrgft for tif rfdognizfr
     * @pbrbm bdtions tif pfrmittfd sourdf bdtions
     * @pbrbm dgl     tif <dodf>DrbgGfsturfListfnfr</dodf> to notify
     *
     * @rfturn tif nfw <dodf>DrbgGfsturfRfdognizfr</dodf> or <dodf>null</dodf>
     *    if tif <dodf>Toolkit.drfbtfDrbgGfsturfRfdognizfr</dodf> mftiod
     *    ibs no implfmfntbtion bvbilbblf for
     *    tif rfqufstfd <dodf>DrbgGfsturfRfdognizfr</dodf>
     *    subdlbss bnd rfturns <dodf>null</dodf>
     */

    publid DrbgGfsturfRfdognizfr drfbtfDffbultDrbgGfsturfRfdognizfr(Componfnt d, int bdtions, DrbgGfsturfListfnfr dgl) {
        rfturn Toolkit.gftDffbultToolkit().drfbtfDrbgGfsturfRfdognizfr(MousfDrbgGfsturfRfdognizfr.dlbss, tiis, d, bdtions, dgl);
    }

    /**
     * Adds tif spfdififd <dodf>DrbgSourdfListfnfr</dodf> to tiis
     * <dodf>DrbgSourdf</dodf> to rfdfivf drbg sourdf fvfnts during drbg
     * opfrbtions intibtfd witi tiis <dodf>DrbgSourdf</dodf>.
     * If b <dodf>null</dodf> listfnfr is spfdififd, no bdtion is tbkfn bnd no
     * fxdfption is tirown.
     *
     * @pbrbm dsl tif <dodf>DrbgSourdfListfnfr</dodf> to bdd
     *
     * @sff      #rfmovfDrbgSourdfListfnfr
     * @sff      #gftDrbgSourdfListfnfrs
     * @sindf 1.4
     */
    publid void bddDrbgSourdfListfnfr(DrbgSourdfListfnfr dsl) {
        if (dsl != null) {
            syndironizfd (tiis) {
                listfnfr = DnDEvfntMultidbstfr.bdd(listfnfr, dsl);
            }
        }
    }

    /**
     * Rfmovfs tif spfdififd <dodf>DrbgSourdfListfnfr</dodf> from tiis
     * <dodf>DrbgSourdf</dodf>.
     * If b <dodf>null</dodf> listfnfr is spfdififd, no bdtion is tbkfn bnd no
     * fxdfption is tirown.
     * If tif listfnfr spfdififd by tif brgumfnt wbs not prfviously bddfd to
     * tiis <dodf>DrbgSourdf</dodf>, no bdtion is tbkfn bnd no fxdfption
     * is tirown.
     *
     * @pbrbm dsl tif <dodf>DrbgSourdfListfnfr</dodf> to rfmovf
     *
     * @sff      #bddDrbgSourdfListfnfr
     * @sff      #gftDrbgSourdfListfnfrs
     * @sindf 1.4
     */
    publid void rfmovfDrbgSourdfListfnfr(DrbgSourdfListfnfr dsl) {
        if (dsl != null) {
            syndironizfd (tiis) {
                listfnfr = DnDEvfntMultidbstfr.rfmovf(listfnfr, dsl);
            }
        }
    }

    /**
     * Gfts bll tif <dodf>DrbgSourdfListfnfr</dodf>s
     * rfgistfrfd witi tiis <dodf>DrbgSourdf</dodf>.
     *
     * @rfturn bll of tiis <dodf>DrbgSourdf</dodf>'s
     *         <dodf>DrbgSourdfListfnfr</dodf>s or bn fmpty brrby if no
     *         sudi listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff      #bddDrbgSourdfListfnfr
     * @sff      #rfmovfDrbgSourdfListfnfr
     * @sindf    1.4
     */
    publid DrbgSourdfListfnfr[] gftDrbgSourdfListfnfrs() {
        rfturn gftListfnfrs(DrbgSourdfListfnfr.dlbss);
    }

    /**
     * Adds tif spfdififd <dodf>DrbgSourdfMotionListfnfr</dodf> to tiis
     * <dodf>DrbgSourdf</dodf> to rfdfivf drbg motion fvfnts during drbg
     * opfrbtions intibtfd witi tiis <dodf>DrbgSourdf</dodf>.
     * If b <dodf>null</dodf> listfnfr is spfdififd, no bdtion is tbkfn bnd no
     * fxdfption is tirown.
     *
     * @pbrbm dsml tif <dodf>DrbgSourdfMotionListfnfr</dodf> to bdd
     *
     * @sff      #rfmovfDrbgSourdfMotionListfnfr
     * @sff      #gftDrbgSourdfMotionListfnfrs
     * @sindf 1.4
     */
    publid void bddDrbgSourdfMotionListfnfr(DrbgSourdfMotionListfnfr dsml) {
        if (dsml != null) {
            syndironizfd (tiis) {
                motionListfnfr = DnDEvfntMultidbstfr.bdd(motionListfnfr, dsml);
            }
        }
    }

    /**
     * Rfmovfs tif spfdififd <dodf>DrbgSourdfMotionListfnfr</dodf> from tiis
     * <dodf>DrbgSourdf</dodf>.
     * If b <dodf>null</dodf> listfnfr is spfdififd, no bdtion is tbkfn bnd no
     * fxdfption is tirown.
     * If tif listfnfr spfdififd by tif brgumfnt wbs not prfviously bddfd to
     * tiis <dodf>DrbgSourdf</dodf>, no bdtion is tbkfn bnd no fxdfption
     * is tirown.
     *
     * @pbrbm dsml tif <dodf>DrbgSourdfMotionListfnfr</dodf> to rfmovf
     *
     * @sff      #bddDrbgSourdfMotionListfnfr
     * @sff      #gftDrbgSourdfMotionListfnfrs
     * @sindf 1.4
     */
    publid void rfmovfDrbgSourdfMotionListfnfr(DrbgSourdfMotionListfnfr dsml) {
        if (dsml != null) {
            syndironizfd (tiis) {
                motionListfnfr = DnDEvfntMultidbstfr.rfmovf(motionListfnfr, dsml);
            }
        }
    }

    /**
     * Gfts bll of tif  <dodf>DrbgSourdfMotionListfnfr</dodf>s
     * rfgistfrfd witi tiis <dodf>DrbgSourdf</dodf>.
     *
     * @rfturn bll of tiis <dodf>DrbgSourdf</dodf>'s
     *         <dodf>DrbgSourdfMotionListfnfr</dodf>s or bn fmpty brrby if no
     *         sudi listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff      #bddDrbgSourdfMotionListfnfr
     * @sff      #rfmovfDrbgSourdfMotionListfnfr
     * @sindf    1.4
     */
    publid DrbgSourdfMotionListfnfr[] gftDrbgSourdfMotionListfnfrs() {
        rfturn gftListfnfrs(DrbgSourdfMotionListfnfr.dlbss);
    }

    /**
     * Gfts bll tif objfdts durrfntly rfgistfrfd bs
     * <dodf><fm>Foo</fm>Listfnfr</dodf>s upon tiis <dodf>DrbgSourdf</dodf>.
     * <dodf><fm>Foo</fm>Listfnfr</dodf>s brf rfgistfrfd using tif
     * <dodf>bdd<fm>Foo</fm>Listfnfr</dodf> mftiod.
     *
     * @pbrbm <T> tif typf of listfnfr objfdts
     * @pbrbm listfnfrTypf tif typf of listfnfrs rfqufstfd; tiis pbrbmftfr
     *          siould spfdify bn intfrfbdf tibt dfsdfnds from
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     * @rfturn bn brrby of bll objfdts rfgistfrfd bs
     *          <dodf><fm>Foo</fm>Listfnfr</dodf>s on tiis
     *          <dodf>DrbgSourdf</dodf>, or bn fmpty brrby if no sudi listfnfrs
     *          ibvf bffn bddfd
     * @fxdfption ClbssCbstExdfption if <dodf>listfnfrTypf</dodf>
     *          dofsn't spfdify b dlbss or intfrfbdf tibt implfmfnts
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     *
     * @sff #gftDrbgSourdfListfnfrs
     * @sff #gftDrbgSourdfMotionListfnfrs
     * @sindf 1.4
     */
    publid <T fxtfnds EvfntListfnfr> T[] gftListfnfrs(Clbss<T> listfnfrTypf) {
        EvfntListfnfr l = null;
        if (listfnfrTypf == DrbgSourdfListfnfr.dlbss) {
            l = listfnfr;
        } flsf if (listfnfrTypf == DrbgSourdfMotionListfnfr.dlbss) {
            l = motionListfnfr;
        }
        rfturn DnDEvfntMultidbstfr.gftListfnfrs(l, listfnfrTypf);
    }

    /**
     * Tiis mftiod dblls <dodf>drbgEntfr</dodf> on tif
     * <dodf>DrbgSourdfListfnfr</dodf>s rfgistfrfd witi tiis
     * <dodf>DrbgSourdf</dodf>, bnd pbssfs tifm tif spfdififd
     * <dodf>DrbgSourdfDrbgEvfnt</dodf>.
     *
     * @pbrbm dsdf tif <dodf>DrbgSourdfDrbgEvfnt</dodf>
     */
    void prodfssDrbgEntfr(DrbgSourdfDrbgEvfnt dsdf) {
        DrbgSourdfListfnfr dsl = listfnfr;
        if (dsl != null) {
            dsl.drbgEntfr(dsdf);
        }
    }

    /**
     * Tiis mftiod dblls <dodf>drbgOvfr</dodf> on tif
     * <dodf>DrbgSourdfListfnfr</dodf>s rfgistfrfd witi tiis
     * <dodf>DrbgSourdf</dodf>, bnd pbssfs tifm tif spfdififd
     * <dodf>DrbgSourdfDrbgEvfnt</dodf>.
     *
     * @pbrbm dsdf tif <dodf>DrbgSourdfDrbgEvfnt</dodf>
     */
    void prodfssDrbgOvfr(DrbgSourdfDrbgEvfnt dsdf) {
        DrbgSourdfListfnfr dsl = listfnfr;
        if (dsl != null) {
            dsl.drbgOvfr(dsdf);
        }
    }

    /**
     * Tiis mftiod dblls <dodf>dropAdtionCibngfd</dodf> on tif
     * <dodf>DrbgSourdfListfnfr</dodf>s rfgistfrfd witi tiis
     * <dodf>DrbgSourdf</dodf>, bnd pbssfs tifm tif spfdififd
     * <dodf>DrbgSourdfDrbgEvfnt</dodf>.
     *
     * @pbrbm dsdf tif <dodf>DrbgSourdfDrbgEvfnt</dodf>
     */
    void prodfssDropAdtionCibngfd(DrbgSourdfDrbgEvfnt dsdf) {
        DrbgSourdfListfnfr dsl = listfnfr;
        if (dsl != null) {
            dsl.dropAdtionCibngfd(dsdf);
        }
    }

    /**
     * Tiis mftiod dblls <dodf>drbgExit</dodf> on tif
     * <dodf>DrbgSourdfListfnfr</dodf>s rfgistfrfd witi tiis
     * <dodf>DrbgSourdf</dodf>, bnd pbssfs tifm tif spfdififd
     * <dodf>DrbgSourdfEvfnt</dodf>.
     *
     * @pbrbm dsf tif <dodf>DrbgSourdfEvfnt</dodf>
     */
    void prodfssDrbgExit(DrbgSourdfEvfnt dsf) {
        DrbgSourdfListfnfr dsl = listfnfr;
        if (dsl != null) {
            dsl.drbgExit(dsf);
        }
    }

    /**
     * Tiis mftiod dblls <dodf>drbgDropEnd</dodf> on tif
     * <dodf>DrbgSourdfListfnfr</dodf>s rfgistfrfd witi tiis
     * <dodf>DrbgSourdf</dodf>, bnd pbssfs tifm tif spfdififd
     * <dodf>DrbgSourdfDropEvfnt</dodf>.
     *
     * @pbrbm dsdf tif <dodf>DrbgSourdfEvfnt</dodf>
     */
    void prodfssDrbgDropEnd(DrbgSourdfDropEvfnt dsdf) {
        DrbgSourdfListfnfr dsl = listfnfr;
        if (dsl != null) {
            dsl.drbgDropEnd(dsdf);
        }
    }

    /**
     * Tiis mftiod dblls <dodf>drbgMousfMovfd</dodf> on tif
     * <dodf>DrbgSourdfMotionListfnfr</dodf>s rfgistfrfd witi tiis
     * <dodf>DrbgSourdf</dodf>, bnd pbssfs tifm tif spfdififd
     * <dodf>DrbgSourdfDrbgEvfnt</dodf>.
     *
     * @pbrbm dsdf tif <dodf>DrbgSourdfEvfnt</dodf>
     */
    void prodfssDrbgMousfMovfd(DrbgSourdfDrbgEvfnt dsdf) {
        DrbgSourdfMotionListfnfr dsml = motionListfnfr;
        if (dsml != null) {
            dsml.drbgMousfMovfd(dsdf);
        }
    }

    /**
     * Sfriblizfs tiis <dodf>DrbgSourdf</dodf>. Tiis mftiod first pfrforms
     * dffbult sfriblizbtion. Nfxt, it writfs out tiis objfdt's
     * <dodf>FlbvorMbp</dodf> if bnd only if it dbn bf sfriblizfd. If not,
     * <dodf>null</dodf> is writtfn instfbd. Nfxt, it writfs out
     * <dodf>Sfriblizbblf</dodf> listfnfrs rfgistfrfd witi tiis
     * objfdt. Listfnfrs brf writtfn in b <dodf>null</dodf>-tfrminbtfd sfqufndf
     * of 0 or morf pbirs. Tif pbir donsists of b <dodf>String</dodf> bnd bn
     * <dodf>Objfdt</dodf>; tif <dodf>String</dodf> indidbtfs tif typf of tif
     * <dodf>Objfdt</dodf> bnd is onf of tif following:
     * <ul>
     * <li><dodf>drbgSourdfListfnfrK</dodf> indidbting b
     *     <dodf>DrbgSourdfListfnfr</dodf> objfdt;
     * <li><dodf>drbgSourdfMotionListfnfrK</dodf> indidbting b
     *     <dodf>DrbgSourdfMotionListfnfr</dodf> objfdt.
     * </ul>
     *
     * @sfriblDbtb Eitifr b <dodf>FlbvorMbp</dodf> instbndf, or
     *      <dodf>null</dodf>, followfd by b <dodf>null</dodf>-tfrminbtfd
     *      sfqufndf of 0 or morf pbirs; tif pbir donsists of b
     *      <dodf>String</dodf> bnd bn <dodf>Objfdt</dodf>; tif
     *      <dodf>String</dodf> indidbtfs tif typf of tif <dodf>Objfdt</dodf>
     *      bnd is onf of tif following:
     *      <ul>
     *      <li><dodf>drbgSourdfListfnfrK</dodf> indidbting b
     *          <dodf>DrbgSourdfListfnfr</dodf> objfdt;
     *      <li><dodf>drbgSourdfMotionListfnfrK</dodf> indidbting b
     *          <dodf>DrbgSourdfMotionListfnfr</dodf> objfdt.
     *      </ul>.
     * @sindf 1.4
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        s.dffbultWritfObjfdt();

        s.writfObjfdt(SfriblizbtionTfstfr.tfst(flbvorMbp) ? flbvorMbp : null);

        DnDEvfntMultidbstfr.sbvf(s, drbgSourdfListfnfrK, listfnfr);
        DnDEvfntMultidbstfr.sbvf(s, drbgSourdfMotionListfnfrK, motionListfnfr);
        s.writfObjfdt(null);
    }

    /**
     * Dfsfriblizfs tiis <dodf>DrbgSourdf</dodf>. Tiis mftiod first pfrforms
     * dffbult dfsfriblizbtion. Nfxt, tiis objfdt's <dodf>FlbvorMbp</dodf> is
     * dfsfriblizfd by using tif nfxt objfdt in tif strfbm.
     * If tif rfsulting <dodf>FlbvorMbp</dodf> is <dodf>null</dodf>, tiis
     * objfdt's <dodf>FlbvorMbp</dodf> is sft to tif dffbult FlbvorMbp for
     * tiis tirfbd's <dodf>ClbssLobdfr</dodf>.
     * Nfxt, tiis objfdt's listfnfrs brf dfsfriblizfd by rfbding b
     * <dodf>null</dodf>-tfrminbtfd sfqufndf of 0 or morf kfy/vbluf pbirs
     * from tif strfbm:
     * <ul>
     * <li>If b kfy objfdt is b <dodf>String</dodf> fqubl to
     * <dodf>drbgSourdfListfnfrK</dodf>, b <dodf>DrbgSourdfListfnfr</dodf> is
     * dfsfriblizfd using tif dorrfsponding vbluf objfdt bnd bddfd to tiis
     * <dodf>DrbgSourdf</dodf>.
     * <li>If b kfy objfdt is b <dodf>String</dodf> fqubl to
     * <dodf>drbgSourdfMotionListfnfrK</dodf>, b
     * <dodf>DrbgSourdfMotionListfnfr</dodf> is dfsfriblizfd using tif
     * dorrfsponding vbluf objfdt bnd bddfd to tiis <dodf>DrbgSourdf</dodf>.
     * <li>Otifrwisf, tif kfy/vbluf pbir is skippfd.
     * </ul>
     *
     * @sff jbvb.bwt.dbtbtrbnsffr.SystfmFlbvorMbp#gftDffbultFlbvorMbp
     * @sindf 1.4
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
      tirows ClbssNotFoundExdfption, IOExdfption {
        s.dffbultRfbdObjfdt();

        // 'flbvorMbp' wbs writtfn fxpliditly
        flbvorMbp = (FlbvorMbp)s.rfbdObjfdt();

        // Implfmfntbtion bssumfs 'flbvorMbp' is nfvfr null.
        if (flbvorMbp == null) {
            flbvorMbp = SystfmFlbvorMbp.gftDffbultFlbvorMbp();
        }

        Objfdt kfyOrNull;
        wiilf (null != (kfyOrNull = s.rfbdObjfdt())) {
            String kfy = ((String)kfyOrNull).intfrn();

            if (drbgSourdfListfnfrK == kfy) {
                bddDrbgSourdfListfnfr((DrbgSourdfListfnfr)(s.rfbdObjfdt()));
            } flsf if (drbgSourdfMotionListfnfrK == kfy) {
                bddDrbgSourdfMotionListfnfr(
                    (DrbgSourdfMotionListfnfr)(s.rfbdObjfdt()));
            } flsf {
                // skip vbluf for unrfdognizfd kfy
                s.rfbdObjfdt();
            }
        }
    }

    /**
     * Rfturns tif drbg gfsturf motion tirfsiold. Tif drbg gfsturf motion tirfsiold
     * dffinfs tif rfdommfndfd bfibvior for {@link MousfDrbgGfsturfRfdognizfr}s.
     * <p>
     * If tif systfm propfrty <dodf>bwt.dnd.drbg.tirfsiold</dodf> is sft to
     * b positivf intfgfr, tiis mftiod rfturns tif vbluf of tif systfm propfrty;
     * otifrwisf if b pfrtinfnt dfsktop propfrty is bvbilbblf bnd supportfd by
     * tif implfmfntbtion of tif Jbvb plbtform, tiis mftiod rfturns tif vbluf of
     * tibt propfrty; otifrwisf tiis mftiod rfturns somf dffbult vbluf.
     * Tif pfrtinfnt dfsktop propfrty dbn bf qufrifd using
     * <dodf>jbvb.bwt.Toolkit.gftDfsktopPropfrty("DnD.gfsturfMotionTirfsiold")</dodf>.
     *
     * @rfturn tif drbg gfsturf motion tirfsiold
     * @sff MousfDrbgGfsturfRfdognizfr
     * @sindf 1.5
     */
    publid stbtid int gftDrbgTirfsiold() {
        int ts = AddfssControllfr.doPrivilfgfd(
                nfw GftIntfgfrAdtion("bwt.dnd.drbg.tirfsiold", 0)).intVbluf();
        if (ts > 0) {
            rfturn ts;
        } flsf {
            Intfgfr td = (Intfgfr)Toolkit.gftDffbultToolkit().
                    gftDfsktopPropfrty("DnD.gfsturfMotionTirfsiold");
            if (td != null) {
                rfturn td.intVbluf();
            }
        }
        rfturn 5;
    }

    /*
     * fiflds
     */

    privbtf trbnsifnt FlbvorMbp flbvorMbp = SystfmFlbvorMbp.gftDffbultFlbvorMbp();

    privbtf trbnsifnt DrbgSourdfListfnfr listfnfr;

    privbtf trbnsifnt DrbgSourdfMotionListfnfr motionListfnfr;
}
