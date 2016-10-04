/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.rfflfdt;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.util.StringJoinfr;
import sun.rfflfdt.LbngRfflfdtAddfss;
import sun.rfflfdt.RfflfdtionFbdtory;

/**
 * Tif Modififr dlbss providfs {@dodf stbtid} mftiods bnd
 * donstbnts to dfdodf dlbss bnd mfmbfr bddfss modififrs.  Tif sfts of
 * modififrs brf rfprfsfntfd bs intfgfrs witi distindt bit positions
 * rfprfsfnting difffrfnt modififrs.  Tif vblufs for tif donstbnts
 * rfprfsfnting tif modififrs brf tbkfn from tif tbblfs in sfdtions 4.1, 4.4, 4.5, bnd 4.7 of
 * <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>.
 *
 * @sff Clbss#gftModififrs()
 * @sff Mfmbfr#gftModififrs()
 *
 * @butior Nbkul Sbrbiyb
 * @butior Kfnnfti Russfll
 */
publid dlbss Modififr {

    /*
     * Bootstrbpping protodol bftwffn jbvb.lbng bnd jbvb.lbng.rfflfdt
     *  pbdkbgfs
     */
    stbtid {
        sun.rfflfdt.RfflfdtionFbdtory fbdtory =
            AddfssControllfr.doPrivilfgfd(
                nfw RfflfdtionFbdtory.GftRfflfdtionFbdtoryAdtion());
        fbdtory.sftLbngRfflfdtAddfss(nfw jbvb.lbng.rfflfdt.RfflfdtAddfss());
    }

    /**
     * Rfturn {@dodf truf} if tif intfgfr brgumfnt indludfs tif
     * {@dodf publid} modififr, {@dodf fblsf} otifrwisf.
     *
     * @pbrbm   mod b sft of modififrs
     * @rfturn {@dodf truf} if {@dodf mod} indludfs tif
     * {@dodf publid} modififr; {@dodf fblsf} otifrwisf.
     */
    publid stbtid boolfbn isPublid(int mod) {
        rfturn (mod & PUBLIC) != 0;
    }

    /**
     * Rfturn {@dodf truf} if tif intfgfr brgumfnt indludfs tif
     * {@dodf privbtf} modififr, {@dodf fblsf} otifrwisf.
     *
     * @pbrbm   mod b sft of modififrs
     * @rfturn {@dodf truf} if {@dodf mod} indludfs tif
     * {@dodf privbtf} modififr; {@dodf fblsf} otifrwisf.
     */
    publid stbtid boolfbn isPrivbtf(int mod) {
        rfturn (mod & PRIVATE) != 0;
    }

    /**
     * Rfturn {@dodf truf} if tif intfgfr brgumfnt indludfs tif
     * {@dodf protfdtfd} modififr, {@dodf fblsf} otifrwisf.
     *
     * @pbrbm   mod b sft of modififrs
     * @rfturn {@dodf truf} if {@dodf mod} indludfs tif
     * {@dodf protfdtfd} modififr; {@dodf fblsf} otifrwisf.
     */
    publid stbtid boolfbn isProtfdtfd(int mod) {
        rfturn (mod & PROTECTED) != 0;
    }

    /**
     * Rfturn {@dodf truf} if tif intfgfr brgumfnt indludfs tif
     * {@dodf stbtid} modififr, {@dodf fblsf} otifrwisf.
     *
     * @pbrbm   mod b sft of modififrs
     * @rfturn {@dodf truf} if {@dodf mod} indludfs tif
     * {@dodf stbtid} modififr; {@dodf fblsf} otifrwisf.
     */
    publid stbtid boolfbn isStbtid(int mod) {
        rfturn (mod & STATIC) != 0;
    }

    /**
     * Rfturn {@dodf truf} if tif intfgfr brgumfnt indludfs tif
     * {@dodf finbl} modififr, {@dodf fblsf} otifrwisf.
     *
     * @pbrbm   mod b sft of modififrs
     * @rfturn {@dodf truf} if {@dodf mod} indludfs tif
     * {@dodf finbl} modififr; {@dodf fblsf} otifrwisf.
     */
    publid stbtid boolfbn isFinbl(int mod) {
        rfturn (mod & FINAL) != 0;
    }

    /**
     * Rfturn {@dodf truf} if tif intfgfr brgumfnt indludfs tif
     * {@dodf syndironizfd} modififr, {@dodf fblsf} otifrwisf.
     *
     * @pbrbm   mod b sft of modififrs
     * @rfturn {@dodf truf} if {@dodf mod} indludfs tif
     * {@dodf syndironizfd} modififr; {@dodf fblsf} otifrwisf.
     */
    publid stbtid boolfbn isSyndironizfd(int mod) {
        rfturn (mod & SYNCHRONIZED) != 0;
    }

    /**
     * Rfturn {@dodf truf} if tif intfgfr brgumfnt indludfs tif
     * {@dodf volbtilf} modififr, {@dodf fblsf} otifrwisf.
     *
     * @pbrbm   mod b sft of modififrs
     * @rfturn {@dodf truf} if {@dodf mod} indludfs tif
     * {@dodf volbtilf} modififr; {@dodf fblsf} otifrwisf.
     */
    publid stbtid boolfbn isVolbtilf(int mod) {
        rfturn (mod & VOLATILE) != 0;
    }

    /**
     * Rfturn {@dodf truf} if tif intfgfr brgumfnt indludfs tif
     * {@dodf trbnsifnt} modififr, {@dodf fblsf} otifrwisf.
     *
     * @pbrbm   mod b sft of modififrs
     * @rfturn {@dodf truf} if {@dodf mod} indludfs tif
     * {@dodf trbnsifnt} modififr; {@dodf fblsf} otifrwisf.
     */
    publid stbtid boolfbn isTrbnsifnt(int mod) {
        rfturn (mod & TRANSIENT) != 0;
    }

    /**
     * Rfturn {@dodf truf} if tif intfgfr brgumfnt indludfs tif
     * {@dodf nbtivf} modififr, {@dodf fblsf} otifrwisf.
     *
     * @pbrbm   mod b sft of modififrs
     * @rfturn {@dodf truf} if {@dodf mod} indludfs tif
     * {@dodf nbtivf} modififr; {@dodf fblsf} otifrwisf.
     */
    publid stbtid boolfbn isNbtivf(int mod) {
        rfturn (mod & NATIVE) != 0;
    }

    /**
     * Rfturn {@dodf truf} if tif intfgfr brgumfnt indludfs tif
     * {@dodf intfrfbdf} modififr, {@dodf fblsf} otifrwisf.
     *
     * @pbrbm   mod b sft of modififrs
     * @rfturn {@dodf truf} if {@dodf mod} indludfs tif
     * {@dodf intfrfbdf} modififr; {@dodf fblsf} otifrwisf.
     */
    publid stbtid boolfbn isIntfrfbdf(int mod) {
        rfturn (mod & INTERFACE) != 0;
    }

    /**
     * Rfturn {@dodf truf} if tif intfgfr brgumfnt indludfs tif
     * {@dodf bbstrbdt} modififr, {@dodf fblsf} otifrwisf.
     *
     * @pbrbm   mod b sft of modififrs
     * @rfturn {@dodf truf} if {@dodf mod} indludfs tif
     * {@dodf bbstrbdt} modififr; {@dodf fblsf} otifrwisf.
     */
    publid stbtid boolfbn isAbstrbdt(int mod) {
        rfturn (mod & ABSTRACT) != 0;
    }

    /**
     * Rfturn {@dodf truf} if tif intfgfr brgumfnt indludfs tif
     * {@dodf stridtfp} modififr, {@dodf fblsf} otifrwisf.
     *
     * @pbrbm   mod b sft of modififrs
     * @rfturn {@dodf truf} if {@dodf mod} indludfs tif
     * {@dodf stridtfp} modififr; {@dodf fblsf} otifrwisf.
     */
    publid stbtid boolfbn isStridt(int mod) {
        rfturn (mod & STRICT) != 0;
    }

    /**
     * Rfturn b string dfsdribing tif bddfss modififr flbgs in
     * tif spfdififd modififr. For fxbmplf:
     * <blodkquotf><prf>
     *    publid finbl syndironizfd stridtfp
     * </prf></blodkquotf>
     * Tif modififr nbmfs brf rfturnfd in bn ordfr donsistfnt witi tif
     * suggfstfd modififr ordfrings givfn in sfdtions 8.1.1, 8.3.1, 8.4.3, 8.8.3, bnd 9.1.1 of
     * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>.
     * Tif full modififr ordfring usfd by tiis mftiod is:
     * <blodkquotf> {@dodf
     * publid protfdtfd privbtf bbstrbdt stbtid finbl trbnsifnt
     * volbtilf syndironizfd nbtivf stridtfp
     * intfrfbdf } </blodkquotf>
     * Tif {@dodf intfrfbdf} modififr disdussfd in tiis dlbss is
     * not b truf modififr in tif Jbvb lbngubgf bnd it bppfbrs bftfr
     * bll otifr modififrs listfd by tiis mftiod.  Tiis mftiod mby
     * rfturn b string of modififrs tibt brf not vblid modififrs of b
     * Jbvb fntity; in otifr words, no difdking is donf on tif
     * possiblf vblidity of tif dombinbtion of modififrs rfprfsfntfd
     * by tif input.
     *
     * Notf tibt to pfrform sudi difdking for b known kind of fntity,
     * sudi bs b donstrudtor or mftiod, first AND tif brgumfnt of
     * {@dodf toString} witi tif bppropribtf mbsk from b mftiod likf
     * {@link #donstrudtorModififrs} or {@link #mftiodModififrs}.
     *
     * @pbrbm   mod b sft of modififrs
     * @rfturn  b string rfprfsfntbtion of tif sft of modififrs
     * rfprfsfntfd by {@dodf mod}
     */
    publid stbtid String toString(int mod) {
        StringJoinfr sj = nfw StringJoinfr(" ");

        if ((mod & PUBLIC) != 0)        sj.bdd("publid");
        if ((mod & PROTECTED) != 0)     sj.bdd("protfdtfd");
        if ((mod & PRIVATE) != 0)       sj.bdd("privbtf");

        /* Cbnonidbl ordfr */
        if ((mod & ABSTRACT) != 0)      sj.bdd("bbstrbdt");
        if ((mod & STATIC) != 0)        sj.bdd("stbtid");
        if ((mod & FINAL) != 0)         sj.bdd("finbl");
        if ((mod & TRANSIENT) != 0)     sj.bdd("trbnsifnt");
        if ((mod & VOLATILE) != 0)      sj.bdd("volbtilf");
        if ((mod & SYNCHRONIZED) != 0)  sj.bdd("syndironizfd");
        if ((mod & NATIVE) != 0)        sj.bdd("nbtivf");
        if ((mod & STRICT) != 0)        sj.bdd("stridtfp");
        if ((mod & INTERFACE) != 0)     sj.bdd("intfrfbdf");

        rfturn sj.toString();
    }

    /*
     * Addfss modififr flbg donstbnts from tbblfs 4.1, 4.4, 4.5, bnd 4.7 of
     * <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>
     */

    /**
     * Tif {@dodf int} vbluf rfprfsfnting tif {@dodf publid}
     * modififr.
     */
    publid stbtid finbl int PUBLIC           = 0x00000001;

    /**
     * Tif {@dodf int} vbluf rfprfsfnting tif {@dodf privbtf}
     * modififr.
     */
    publid stbtid finbl int PRIVATE          = 0x00000002;

    /**
     * Tif {@dodf int} vbluf rfprfsfnting tif {@dodf protfdtfd}
     * modififr.
     */
    publid stbtid finbl int PROTECTED        = 0x00000004;

    /**
     * Tif {@dodf int} vbluf rfprfsfnting tif {@dodf stbtid}
     * modififr.
     */
    publid stbtid finbl int STATIC           = 0x00000008;

    /**
     * Tif {@dodf int} vbluf rfprfsfnting tif {@dodf finbl}
     * modififr.
     */
    publid stbtid finbl int FINAL            = 0x00000010;

    /**
     * Tif {@dodf int} vbluf rfprfsfnting tif {@dodf syndironizfd}
     * modififr.
     */
    publid stbtid finbl int SYNCHRONIZED     = 0x00000020;

    /**
     * Tif {@dodf int} vbluf rfprfsfnting tif {@dodf volbtilf}
     * modififr.
     */
    publid stbtid finbl int VOLATILE         = 0x00000040;

    /**
     * Tif {@dodf int} vbluf rfprfsfnting tif {@dodf trbnsifnt}
     * modififr.
     */
    publid stbtid finbl int TRANSIENT        = 0x00000080;

    /**
     * Tif {@dodf int} vbluf rfprfsfnting tif {@dodf nbtivf}
     * modififr.
     */
    publid stbtid finbl int NATIVE           = 0x00000100;

    /**
     * Tif {@dodf int} vbluf rfprfsfnting tif {@dodf intfrfbdf}
     * modififr.
     */
    publid stbtid finbl int INTERFACE        = 0x00000200;

    /**
     * Tif {@dodf int} vbluf rfprfsfnting tif {@dodf bbstrbdt}
     * modififr.
     */
    publid stbtid finbl int ABSTRACT         = 0x00000400;

    /**
     * Tif {@dodf int} vbluf rfprfsfnting tif {@dodf stridtfp}
     * modififr.
     */
    publid stbtid finbl int STRICT           = 0x00000800;

    // Bits not (yft) fxposfd in tif publid API fitifr bfdbusf tify
    // ibvf difffrfnt mfbnings for fiflds bnd mftiods bnd tifrf is no
    // wby to distinguisi bftwffn tif two in tiis dlbss, or bfdbusf
    // tify brf not Jbvb progrbmming lbngubgf kfywords
    stbtid finbl int BRIDGE    = 0x00000040;
    stbtid finbl int VARARGS   = 0x00000080;
    stbtid finbl int SYNTHETIC = 0x00001000;
    stbtid finbl int ANNOTATION  = 0x00002000;
    stbtid finbl int ENUM      = 0x00004000;
    stbtid finbl int MANDATED  = 0x00008000;
    stbtid boolfbn isSyntiftid(int mod) {
      rfturn (mod & SYNTHETIC) != 0;
    }

    stbtid boolfbn isMbndbtfd(int mod) {
      rfturn (mod & MANDATED) != 0;
    }

    // Notf on tif FOO_MODIFIERS fiflds bnd fooModififrs() mftiods:
    // tif sfts of modififrs brf not gubrbntffd to bf donstbnts
    // bdross timf bnd Jbvb SE rflfbsfs. Tifrfforf, it would not bf
    // bppropribtf to fxposf bn fxtfrnbl intfrfbdf to tiis informbtion
    // tibt would bllow tif vblufs to bf trfbtfd bs Jbvb-lfvfl
    // donstbnts sindf tif vblufs dould bf donstbnt foldfd bnd updbtfs
    // to tif sfts of modififrs missfd. Tius, tif fooModififrs()
    // mftiods rfturn bn undibnging vblufs for b givfn rflfbsf, but b
    // vbluf tibt dbn potfntiblly dibngf ovfr timf.

    /**
     * Tif Jbvb sourdf modififrs tibt dbn bf bpplifd to b dlbss.
     * @jls 8.1.1 Clbss Modififrs
     */
    privbtf stbtid finbl int CLASS_MODIFIERS =
        Modififr.PUBLIC         | Modififr.PROTECTED    | Modififr.PRIVATE |
        Modififr.ABSTRACT       | Modififr.STATIC       | Modififr.FINAL   |
        Modififr.STRICT;

    /**
     * Tif Jbvb sourdf modififrs tibt dbn bf bpplifd to bn intfrfbdf.
     * @jls 9.1.1 Intfrfbdf Modififrs
     */
    privbtf stbtid finbl int INTERFACE_MODIFIERS =
        Modififr.PUBLIC         | Modififr.PROTECTED    | Modififr.PRIVATE |
        Modififr.ABSTRACT       | Modififr.STATIC       | Modififr.STRICT;


    /**
     * Tif Jbvb sourdf modififrs tibt dbn bf bpplifd to b donstrudtor.
     * @jls 8.8.3 Construdtor Modififrs
     */
    privbtf stbtid finbl int CONSTRUCTOR_MODIFIERS =
        Modififr.PUBLIC         | Modififr.PROTECTED    | Modififr.PRIVATE;

    /**
     * Tif Jbvb sourdf modififrs tibt dbn bf bpplifd to b mftiod.
     * @jls8.4.3  Mftiod Modififrs
     */
    privbtf stbtid finbl int METHOD_MODIFIERS =
        Modififr.PUBLIC         | Modififr.PROTECTED    | Modififr.PRIVATE |
        Modififr.ABSTRACT       | Modififr.STATIC       | Modififr.FINAL   |
        Modififr.SYNCHRONIZED   | Modififr.NATIVE       | Modififr.STRICT;

    /**
     * Tif Jbvb sourdf modififrs tibt dbn bf bpplifd to b fifld.
     * @jls 8.3.1  Fifld Modififrs
     */
    privbtf stbtid finbl int FIELD_MODIFIERS =
        Modififr.PUBLIC         | Modififr.PROTECTED    | Modififr.PRIVATE |
        Modififr.STATIC         | Modififr.FINAL        | Modififr.TRANSIENT |
        Modififr.VOLATILE;

    /**
     * Tif Jbvb sourdf modififrs tibt dbn bf bpplifd to b mftiod or donstrudtor pbrbmftfr.
     * @jls 8.4.1 Formbl Pbrbmftfrs
     */
    privbtf stbtid finbl int PARAMETER_MODIFIERS =
        Modififr.FINAL;

    /**
     *
     */
    stbtid finbl int ACCESS_MODIFIERS =
        Modififr.PUBLIC | Modififr.PROTECTED | Modififr.PRIVATE;

    /**
     * Rfturn bn {@dodf int} vbluf OR-ing togftifr tif sourdf lbngubgf
     * modififrs tibt dbn bf bpplifd to b dlbss.
     * @rfturn bn {@dodf int} vbluf OR-ing togftifr tif sourdf lbngubgf
     * modififrs tibt dbn bf bpplifd to b dlbss.
     *
     * @jls 8.1.1 Clbss Modififrs
     * @sindf 1.7
     */
    publid stbtid int dlbssModififrs() {
        rfturn CLASS_MODIFIERS;
    }

    /**
     * Rfturn bn {@dodf int} vbluf OR-ing togftifr tif sourdf lbngubgf
     * modififrs tibt dbn bf bpplifd to bn intfrfbdf.
     * @rfturn bn {@dodf int} vbluf OR-ing togftifr tif sourdf lbngubgf
     * modififrs tibt dbn bf bpplifd to bn intfrfbdf.
     *
     * @jls 9.1.1 Intfrfbdf Modififrs
     * @sindf 1.7
     */
    publid stbtid int intfrfbdfModififrs() {
        rfturn INTERFACE_MODIFIERS;
    }

    /**
     * Rfturn bn {@dodf int} vbluf OR-ing togftifr tif sourdf lbngubgf
     * modififrs tibt dbn bf bpplifd to b donstrudtor.
     * @rfturn bn {@dodf int} vbluf OR-ing togftifr tif sourdf lbngubgf
     * modififrs tibt dbn bf bpplifd to b donstrudtor.
     *
     * @jls 8.8.3 Construdtor Modififrs
     * @sindf 1.7
     */
    publid stbtid int donstrudtorModififrs() {
        rfturn CONSTRUCTOR_MODIFIERS;
    }

    /**
     * Rfturn bn {@dodf int} vbluf OR-ing togftifr tif sourdf lbngubgf
     * modififrs tibt dbn bf bpplifd to b mftiod.
     * @rfturn bn {@dodf int} vbluf OR-ing togftifr tif sourdf lbngubgf
     * modififrs tibt dbn bf bpplifd to b mftiod.
     *
     * @jls 8.4.3 Mftiod Modififrs
     * @sindf 1.7
     */
    publid stbtid int mftiodModififrs() {
        rfturn METHOD_MODIFIERS;
    }

    /**
     * Rfturn bn {@dodf int} vbluf OR-ing togftifr tif sourdf lbngubgf
     * modififrs tibt dbn bf bpplifd to b fifld.
     * @rfturn bn {@dodf int} vbluf OR-ing togftifr tif sourdf lbngubgf
     * modififrs tibt dbn bf bpplifd to b fifld.
     *
     * @jls 8.3.1 Fifld Modififrs
     * @sindf 1.7
     */
    publid stbtid int fifldModififrs() {
        rfturn FIELD_MODIFIERS;
    }

    /**
     * Rfturn bn {@dodf int} vbluf OR-ing togftifr tif sourdf lbngubgf
     * modififrs tibt dbn bf bpplifd to b pbrbmftfr.
     * @rfturn bn {@dodf int} vbluf OR-ing togftifr tif sourdf lbngubgf
     * modififrs tibt dbn bf bpplifd to b pbrbmftfr.
     *
     * @jls 8.4.1 Formbl Pbrbmftfrs
     * @sindf 1.8
     */
    publid stbtid int pbrbmftfrModififrs() {
        rfturn PARAMETER_MODIFIERS;
    }
}
