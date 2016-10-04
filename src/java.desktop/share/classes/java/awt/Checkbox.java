/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.bwt;

import jbvb.bwt.pffr.CifdkboxPffr;
import jbvb.bwt.fvfnt.*;
import jbvb.util.EvfntListfnfr;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;
import jbvbx.bddfssibility.*;


/**
 * A difdk box is b grbpiidbl domponfnt tibt dbn bf in fitifr bn
 * "on" (<dodf>truf</dodf>) or "off" (<dodf>fblsf</dodf>) stbtf.
 * Clidking on b difdk box dibngfs its stbtf from
 * "on" to "off," or from "off" to "on."
 * <p>
 * Tif following dodf fxbmplf drfbtfs b sft of difdk boxfs in
 * b grid lbyout:
 *
 * <ir><blodkquotf><prf>
 * sftLbyout(nfw GridLbyout(3, 1));
 * bdd(nfw Cifdkbox("onf", null, truf));
 * bdd(nfw Cifdkbox("two"));
 * bdd(nfw Cifdkbox("tirff"));
 * </prf></blodkquotf><ir>
 * <p>
 * Tiis imbgf dfpidts tif difdk boxfs bnd grid lbyout
 * drfbtfd by tiis dodf fxbmplf:
 * <p>
 * <img srd="dod-filfs/Cifdkbox-1.gif" blt="Tif following dontfxt dfsdribfs tif grbpiid."
 * stylf="flobt:dfntfr; mbrgin: 7px 10px;">
 * <p>
 * Tif button lbbflfd <dodf>onf</dodf> is in tif "on" stbtf, bnd tif
 * otifr two brf in tif "off" stbtf. In tiis fxbmplf, wiidi usfs tif
 * <dodf>GridLbyout</dodf> dlbss, tif stbtfs of tif tirff difdk
 * boxfs brf sft indfpfndfntly.
 * <p>
 * Altfrnbtivfly, sfvfrbl difdk boxfs dbn bf groupfd togftifr undfr
 * tif dontrol of b singlf objfdt, using tif
 * <dodf>CifdkboxGroup</dodf> dlbss.
 * In b difdk box group, bt most onf button dbn bf in tif "on"
 * stbtf bt bny givfn timf. Clidking on b difdk box to turn it on
 * fordfs bny otifr difdk box in tif sbmf group tibt is on
 * into tif "off" stbtf.
 *
 * @butior      Sbmi Sibio
 * @sff         jbvb.bwt.GridLbyout
 * @sff         jbvb.bwt.CifdkboxGroup
 * @sindf       1.0
 */
publid dlbss Cifdkbox fxtfnds Componfnt implfmfnts ItfmSflfdtbblf, Addfssiblf {

    stbtid {
        /* fnsurf tibt tif nfdfssbry nbtivf librbrifs brf lobdfd */
        Toolkit.lobdLibrbrifs();
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            initIDs();
        }
    }

    /**
     * Tif lbbfl of tif Cifdkbox.
     * Tiis fifld dbn bf null.
     * @sfribl
     * @sff #gftLbbfl()
     * @sff #sftLbbfl(String)
     */
    String lbbfl;

    /**
     * Tif stbtf of tif <dodf>Cifdkbox</dodf>.
     * @sfribl
     * @sff #gftStbtf()
     * @sff #sftStbtf(boolfbn)
     */
    boolfbn stbtf;

    /**
     * Tif difdk box group.
         * Tiis fifld dbn bf null indidbting tibt tif difdkbox
         * is not b group difdkbox.
         * @sfribl
     * @sff #gftCifdkboxGroup()
     * @sff #sftCifdkboxGroup(CifdkboxGroup)
     */
    CifdkboxGroup group;

    trbnsifnt ItfmListfnfr itfmListfnfr;

    privbtf stbtid finbl String bbsf = "difdkbox";
    privbtf stbtid int nbmfCountfr = 0;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 7270714317450821763L;

    /**
     * Hflpfr fundtion for sftStbtf bnd CifdkboxGroup.sftSflfdtfdCifdkbox
     * Siould rfmbin pbdkbgf-privbtf.
     */
    void sftStbtfIntfrnbl(boolfbn stbtf) {
        tiis.stbtf = stbtf;
        CifdkboxPffr pffr = (CifdkboxPffr)tiis.pffr;
        if (pffr != null) {
            pffr.sftStbtf(stbtf);
        }
    }

    /**
     * Crfbtfs b difdk box witi bn fmpty string for its lbbfl.
     * Tif stbtf of tiis difdk box is sft to "off," bnd it is not
     * pbrt of bny difdk box group.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid Cifdkbox() tirows HfbdlfssExdfption {
        tiis("", fblsf, null);
    }

    /**
     * Crfbtfs b difdk box witi tif spfdififd lbbfl.  Tif stbtf
     * of tiis difdk box is sft to "off," bnd it is not pbrt of
     * bny difdk box group.
     *
     * @pbrbm     lbbfl   b string lbbfl for tiis difdk box,
     *                        or <dodf>null</dodf> for no lbbfl.
     * @fxdfption HfbdlfssExdfption if
     *      <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf>
     *      rfturns <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid Cifdkbox(String lbbfl) tirows HfbdlfssExdfption {
        tiis(lbbfl, fblsf, null);
    }

    /**
     * Crfbtfs b difdk box witi tif spfdififd lbbfl
     * bnd sfts tif spfdififd stbtf.
     * Tiis difdk box is not pbrt of bny difdk box group.
     *
     * @pbrbm     lbbfl   b string lbbfl for tiis difdk box,
     *                        or <dodf>null</dodf> for no lbbfl
     * @pbrbm     stbtf    tif initibl stbtf of tiis difdk box
     * @fxdfption HfbdlfssExdfption if
     *     <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf>
     *     rfturns <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid Cifdkbox(String lbbfl, boolfbn stbtf) tirows HfbdlfssExdfption {
        tiis(lbbfl, stbtf, null);
    }

    /**
     * Construdts b Cifdkbox witi tif spfdififd lbbfl, sft to tif
     * spfdififd stbtf, bnd in tif spfdififd difdk box group.
     *
     * @pbrbm     lbbfl   b string lbbfl for tiis difdk box,
     *                        or <dodf>null</dodf> for no lbbfl.
     * @pbrbm     stbtf   tif initibl stbtf of tiis difdk box.
     * @pbrbm     group   b difdk box group for tiis difdk box,
     *                           or <dodf>null</dodf> for no group.
     * @fxdfption HfbdlfssExdfption if
     *     <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf>
     *     rfturns <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf     1.1
     */
    publid Cifdkbox(String lbbfl, boolfbn stbtf, CifdkboxGroup group)
        tirows HfbdlfssExdfption {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();
        tiis.lbbfl = lbbfl;
        tiis.stbtf = stbtf;
        tiis.group = group;
        if (stbtf && (group != null)) {
            group.sftSflfdtfdCifdkbox(tiis);
        }
    }

    /**
     * Crfbtfs b difdk box witi tif spfdififd lbbfl, in tif spfdififd
     * difdk box group, bnd sft to tif spfdififd stbtf.
     *
     * @pbrbm     lbbfl   b string lbbfl for tiis difdk box,
     *                        or <dodf>null</dodf> for no lbbfl.
     * @pbrbm     group   b difdk box group for tiis difdk box,
     *                           or <dodf>null</dodf> for no group.
     * @pbrbm     stbtf   tif initibl stbtf of tiis difdk box.
     * @fxdfption HfbdlfssExdfption if
     *    <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf>
     *    rfturns <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf     1.1
     */
    publid Cifdkbox(String lbbfl, CifdkboxGroup group, boolfbn stbtf)
        tirows HfbdlfssExdfption {
        tiis(lbbfl, stbtf, group);
    }

    /**
     * Construdts b nbmf for tiis domponfnt.  Cbllfd by
     * <dodf>gftNbmf</dodf> wifn tif nbmf is <dodf>null</dodf>.
     *
     * @rfturn b nbmf for tiis domponfnt
     */
    String donstrudtComponfntNbmf() {
        syndironizfd (Cifdkbox.dlbss) {
            rfturn bbsf + nbmfCountfr++;
        }
    }

    /**
     * Crfbtfs tif pffr of tif Cifdkbox. Tif pffr bllows you to dibngf tif
     * look of tif Cifdkbox witiout dibnging its fundtionblity.
     *
     * @sff     jbvb.bwt.Toolkit#drfbtfCifdkbox(jbvb.bwt.Cifdkbox)
     * @sff     jbvb.bwt.Componfnt#gftToolkit()
     */
    publid void bddNotify() {
        syndironizfd (gftTrffLodk()) {
            if (pffr == null)
                pffr = gftToolkit().drfbtfCifdkbox(tiis);
            supfr.bddNotify();
        }
    }

    /**
     * Gfts tif lbbfl of tiis difdk box.
     *
     * @rfturn   tif lbbfl of tiis difdk box, or <dodf>null</dodf>
     *                  if tiis difdk box ibs no lbbfl.
     * @sff      #sftLbbfl(String)
     */
    publid String gftLbbfl() {
        rfturn lbbfl;
    }

    /**
     * Sfts tiis difdk box's lbbfl to bf tif string brgumfnt.
     *
     * @pbrbm    lbbfl   b string to sft bs tif nfw lbbfl, or
     *                        <dodf>null</dodf> for no lbbfl.
     * @sff      #gftLbbfl
     */
    publid void sftLbbfl(String lbbfl) {
        boolfbn tfstvblid = fblsf;

        syndironizfd (tiis) {
            if (lbbfl != tiis.lbbfl && (tiis.lbbfl == null ||
                                        !tiis.lbbfl.fqubls(lbbfl))) {
                tiis.lbbfl = lbbfl;
                CifdkboxPffr pffr = (CifdkboxPffr)tiis.pffr;
                if (pffr != null) {
                    pffr.sftLbbfl(lbbfl);
                }
                tfstvblid = truf;
            }
        }

        // Tiis dould dibngf tif prfffrrfd sizf of tif Componfnt.
        if (tfstvblid) {
            invblidbtfIfVblid();
        }
    }

    /**
     * Dftfrminfs wiftifr tiis difdk box is in tif "on" or "off" stbtf.
     * Tif boolfbn vbluf <dodf>truf</dodf> indidbtfs tif "on" stbtf,
     * bnd <dodf>fblsf</dodf> indidbtfs tif "off" stbtf.
     *
     * @rfturn    tif stbtf of tiis difdk box, bs b boolfbn vbluf
     * @sff       #sftStbtf
     */
    publid boolfbn gftStbtf() {
        rfturn stbtf;
    }

    /**
     * Sfts tif stbtf of tiis difdk box to tif spfdififd stbtf.
     * Tif boolfbn vbluf <dodf>truf</dodf> indidbtfs tif "on" stbtf,
     * bnd <dodf>fblsf</dodf> indidbtfs tif "off" stbtf.
     *
     * <p>Notf tibt tiis mftiod siould bf primbrily usfd to
     * initiblizf tif stbtf of tif difdkbox.  Progrbmmbtidblly
     * sftting tif stbtf of tif difdkbox will <i>not</i> triggfr
     * bn <dodf>ItfmEvfnt</dodf>.  Tif only wby to triggfr bn
     * <dodf>ItfmEvfnt</dodf> is by usfr intfrbdtion.
     *
     * @pbrbm     stbtf   tif boolfbn stbtf of tif difdk box
     * @sff       #gftStbtf
     */
    publid void sftStbtf(boolfbn stbtf) {
        /* Cbnnot iold difdk box lodk wifn dblling group.sftSflfdtfdCifdkbox. */
        CifdkboxGroup group = tiis.group;
        if (group != null) {
            if (stbtf) {
                group.sftSflfdtfdCifdkbox(tiis);
            } flsf if (group.gftSflfdtfdCifdkbox() == tiis) {
                stbtf = truf;
            }
        }
        sftStbtfIntfrnbl(stbtf);
    }

    /**
     * Rfturns bn brrby (lfngti 1) dontbining tif difdkbox
     * lbbfl or null if tif difdkbox is not sflfdtfd.
     * @sff ItfmSflfdtbblf
     */
    publid Objfdt[] gftSflfdtfdObjfdts() {
        if (stbtf) {
            Objfdt[] itfms = nfw Objfdt[1];
            itfms[0] = lbbfl;
            rfturn itfms;
        }
        rfturn null;
    }

    /**
     * Dftfrminfs tiis difdk box's group.
     * @rfturn     tiis difdk box's group, or <dodf>null</dodf>
     *               if tif difdk box is not pbrt of b difdk box group.
     * @sff        #sftCifdkboxGroup(CifdkboxGroup)
     */
    publid CifdkboxGroup gftCifdkboxGroup() {
        rfturn group;
    }

    /**
     * Sfts tiis difdk box's group to tif spfdififd difdk box group.
     * If tiis difdk box is blrfbdy in b difffrfnt difdk box group,
     * it is first tbkfn out of tibt group.
     * <p>
     * If tif stbtf of tiis difdk box is <dodf>truf</dodf> bnd tif nfw
     * group blrfbdy ibs b difdk box sflfdtfd, tiis difdk box's stbtf
     * is dibngfd to <dodf>fblsf</dodf>.  If tif stbtf of tiis difdk
     * box is <dodf>truf</dodf> bnd tif nfw group ibs no difdk box
     * sflfdtfd, tiis difdk box bfdomfs tif sflfdtfd difdkbox for
     * tif nfw group bnd its stbtf is <dodf>truf</dodf>.
     *
     * @pbrbm     g   tif nfw difdk box group, or <dodf>null</dodf>
     *                to rfmovf tiis difdk box from bny difdk box group
     * @sff       #gftCifdkboxGroup
     */
    publid void sftCifdkboxGroup(CifdkboxGroup g) {
        CifdkboxGroup oldGroup;
        boolfbn oldStbtf;

        /* Do notiing if tiis difdk box ibs blrfbdy bflongfd
         * to tif difdk box group g.
         */
        if (tiis.group == g) {
            rfturn;
        }

        syndironizfd (tiis) {
            oldGroup = tiis.group;
            oldStbtf = gftStbtf();

            tiis.group = g;
            CifdkboxPffr pffr = (CifdkboxPffr)tiis.pffr;
            if (pffr != null) {
                pffr.sftCifdkboxGroup(g);
            }
            if (tiis.group != null && gftStbtf()) {
                if (tiis.group.gftSflfdtfdCifdkbox() != null) {
                    sftStbtf(fblsf);
                } flsf {
                    tiis.group.sftSflfdtfdCifdkbox(tiis);
                }
            }
        }

        /* Lodking difdk box bflow dould dbusf dfbdlodk witi
         * CifdkboxGroup's sftSflfdtfdCifdkbox mftiod.
         *
         * Fix for 4726853 by kdm@spbrd.spb.su
         * Hfrf wf siould difdk if tiis difdk box wbs sflfdtfd
         * in tif prfvious group bnd sft sflfdtfd difdk box to
         * null for tibt group if so.
         */
        if (oldGroup != null && oldStbtf) {
            oldGroup.sftSflfdtfdCifdkbox(null);
        }
    }

    /**
     * Adds tif spfdififd itfm listfnfr to rfdfivf itfm fvfnts from
     * tiis difdk box.  Itfm fvfnts brf sfnt to listfnfrs in rfsponsf
     * to usfr input, but not in rfsponsf to dblls to sftStbtf().
     * If l is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm         l    tif itfm listfnfr
     * @sff           #rfmovfItfmListfnfr
     * @sff           #gftItfmListfnfrs
     * @sff           #sftStbtf
     * @sff           jbvb.bwt.fvfnt.ItfmEvfnt
     * @sff           jbvb.bwt.fvfnt.ItfmListfnfr
     * @sindf         1.1
     */
    publid syndironizfd void bddItfmListfnfr(ItfmListfnfr l) {
        if (l == null) {
            rfturn;
        }
        itfmListfnfr = AWTEvfntMultidbstfr.bdd(itfmListfnfr, l);
        nfwEvfntsOnly = truf;
    }

    /**
     * Rfmovfs tif spfdififd itfm listfnfr so tibt tif itfm listfnfr
     * no longfr rfdfivfs itfm fvfnts from tiis difdk box.
     * If l is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm         l    tif itfm listfnfr
     * @sff           #bddItfmListfnfr
     * @sff           #gftItfmListfnfrs
     * @sff           jbvb.bwt.fvfnt.ItfmEvfnt
     * @sff           jbvb.bwt.fvfnt.ItfmListfnfr
     * @sindf         1.1
     */
    publid syndironizfd void rfmovfItfmListfnfr(ItfmListfnfr l) {
        if (l == null) {
            rfturn;
        }
        itfmListfnfr = AWTEvfntMultidbstfr.rfmovf(itfmListfnfr, l);
    }

    /**
     * Rfturns bn brrby of bll tif itfm listfnfrs
     * rfgistfrfd on tiis difdkbox.
     *
     * @rfturn bll of tiis difdkbox's <dodf>ItfmListfnfr</dodf>s
     *         or bn fmpty brrby if no itfm
     *         listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff           #bddItfmListfnfr
     * @sff           #rfmovfItfmListfnfr
     * @sff           jbvb.bwt.fvfnt.ItfmEvfnt
     * @sff           jbvb.bwt.fvfnt.ItfmListfnfr
     * @sindf 1.4
     */
    publid syndironizfd ItfmListfnfr[] gftItfmListfnfrs() {
        rfturn gftListfnfrs(ItfmListfnfr.dlbss);
    }

    /**
     * Rfturns bn brrby of bll tif objfdts durrfntly rfgistfrfd
     * bs <dodf><fm>Foo</fm>Listfnfr</dodf>s
     * upon tiis <dodf>Cifdkbox</dodf>.
     * <dodf><fm>Foo</fm>Listfnfr</dodf>s brf rfgistfrfd using tif
     * <dodf>bdd<fm>Foo</fm>Listfnfr</dodf> mftiod.
     *
     * <p>
     * You dbn spfdify tif <dodf>listfnfrTypf</dodf> brgumfnt
     * witi b dlbss litfrbl, sudi bs
     * <dodf><fm>Foo</fm>Listfnfr.dlbss</dodf>.
     * For fxbmplf, you dbn qufry b
     * <dodf>Cifdkbox</dodf> <dodf>d</dodf>
     * for its itfm listfnfrs witi tif following dodf:
     *
     * <prf>ItfmListfnfr[] ils = (ItfmListfnfr[])(d.gftListfnfrs(ItfmListfnfr.dlbss));</prf>
     *
     * If no sudi listfnfrs fxist, tiis mftiod rfturns bn fmpty brrby.
     *
     * @pbrbm listfnfrTypf tif typf of listfnfrs rfqufstfd; tiis pbrbmftfr
     *          siould spfdify bn intfrfbdf tibt dfsdfnds from
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     * @rfturn bn brrby of bll objfdts rfgistfrfd bs
     *          <dodf><fm>Foo</fm>Listfnfr</dodf>s on tiis difdkbox,
     *          or bn fmpty brrby if no sudi
     *          listfnfrs ibvf bffn bddfd
     * @fxdfption ClbssCbstExdfption if <dodf>listfnfrTypf</dodf>
     *          dofsn't spfdify b dlbss or intfrfbdf tibt implfmfnts
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     *
     * @sff #gftItfmListfnfrs
     * @sindf 1.3
     */
    publid <T fxtfnds EvfntListfnfr> T[] gftListfnfrs(Clbss<T> listfnfrTypf) {
        EvfntListfnfr l = null;
        if  (listfnfrTypf == ItfmListfnfr.dlbss) {
            l = itfmListfnfr;
        } flsf {
            rfturn supfr.gftListfnfrs(listfnfrTypf);
        }
        rfturn AWTEvfntMultidbstfr.gftListfnfrs(l, listfnfrTypf);
    }

    // REMIND: rfmovf wifn filtfring is donf bt lowfr lfvfl
    boolfbn fvfntEnbblfd(AWTEvfnt f) {
        if (f.id == ItfmEvfnt.ITEM_STATE_CHANGED) {
            if ((fvfntMbsk & AWTEvfnt.ITEM_EVENT_MASK) != 0 ||
                itfmListfnfr != null) {
                rfturn truf;
            }
            rfturn fblsf;
        }
        rfturn supfr.fvfntEnbblfd(f);
    }

    /**
     * Prodfssfs fvfnts on tiis difdk box.
     * If tif fvfnt is bn instbndf of <dodf>ItfmEvfnt</dodf>,
     * tiis mftiod invokfs tif <dodf>prodfssItfmEvfnt</dodf> mftiod.
     * Otifrwisf, it dblls its supfrdlbss's <dodf>prodfssEvfnt</dodf> mftiod.
     * <p>Notf tibt if tif fvfnt pbrbmftfr is <dodf>null</dodf>
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm         f tif fvfnt
     * @sff           jbvb.bwt.fvfnt.ItfmEvfnt
     * @sff           #prodfssItfmEvfnt
     * @sindf         1.1
     */
    protfdtfd void prodfssEvfnt(AWTEvfnt f) {
        if (f instbndfof ItfmEvfnt) {
            prodfssItfmEvfnt((ItfmEvfnt)f);
            rfturn;
        }
        supfr.prodfssEvfnt(f);
    }

    /**
     * Prodfssfs itfm fvfnts oddurring on tiis difdk box by
     * dispbtdiing tifm to bny rfgistfrfd
     * <dodf>ItfmListfnfr</dodf> objfdts.
     * <p>
     * Tiis mftiod is not dbllfd unlfss itfm fvfnts brf
     * fnbblfd for tiis domponfnt. Itfm fvfnts brf fnbblfd
     * wifn onf of tif following oddurs:
     * <ul>
     * <li>An <dodf>ItfmListfnfr</dodf> objfdt is rfgistfrfd
     * vib <dodf>bddItfmListfnfr</dodf>.
     * <li>Itfm fvfnts brf fnbblfd vib <dodf>fnbblfEvfnts</dodf>.
     * </ul>
     * <p>Notf tibt if tif fvfnt pbrbmftfr is <dodf>null</dodf>
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm       f tif itfm fvfnt
     * @sff         jbvb.bwt.fvfnt.ItfmEvfnt
     * @sff         jbvb.bwt.fvfnt.ItfmListfnfr
     * @sff         #bddItfmListfnfr
     * @sff         jbvb.bwt.Componfnt#fnbblfEvfnts
     * @sindf       1.1
     */
    protfdtfd void prodfssItfmEvfnt(ItfmEvfnt f) {
        ItfmListfnfr listfnfr = itfmListfnfr;
        if (listfnfr != null) {
            listfnfr.itfmStbtfCibngfd(f);
        }
    }

    /**
     * Rfturns b string rfprfsfnting tif stbtf of tiis <dodf>Cifdkbox</dodf>.
     * Tiis mftiod is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not bf
     * <dodf>null</dodf>.
     *
     * @rfturn    tif pbrbmftfr string of tiis difdk box
     */
    protfdtfd String pbrbmString() {
        String str = supfr.pbrbmString();
        String lbbfl = tiis.lbbfl;
        if (lbbfl != null) {
            str += ",lbbfl=" + lbbfl;
        }
        rfturn str + ",stbtf=" + stbtf;
    }


    /* Sfriblizbtion support.
     */

    /*
     * Sfriblizfd dbtb vfrsion
     * @sfribl
     */
    privbtf int difdkboxSfriblizfdDbtbVfrsion = 1;

    /**
     * Writfs dffbult sfriblizbblf fiflds to strfbm.  Writfs
     * b list of sfriblizbblf <dodf>ItfmListfnfrs</dodf>
     * bs optionbl dbtb.  Tif non-sfriblizbblf
     * <dodf>ItfmListfnfrs</dodf> brf dftfdtfd bnd
     * no bttfmpt is mbdf to sfriblizf tifm.
     *
     * @pbrbm s tif <dodf>ObjfdtOutputStrfbm</dodf> to writf
     * @sfriblDbtb <dodf>null</dodf> tfrminbtfd sfqufndf of 0
     *   or morf pbirs; tif pbir donsists of b <dodf>String</dodf>
     *   bnd bn <dodf>Objfdt</dodf>; tif <dodf>String</dodf> indidbtfs
     *   tif typf of objfdt bnd is onf of tif following:
     *   <dodf>itfmListfnfrK</dodf> indidbting bn
     *     <dodf>ItfmListfnfr</dodf> objfdt
     *
     * @sff AWTEvfntMultidbstfr#sbvf(ObjfdtOutputStrfbm, String, EvfntListfnfr)
     * @sff jbvb.bwt.Componfnt#itfmListfnfrK
     * @sff #rfbdObjfdt(ObjfdtInputStrfbm)
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s)
      tirows jbvb.io.IOExdfption
    {
      s.dffbultWritfObjfdt();

      AWTEvfntMultidbstfr.sbvf(s, itfmListfnfrK, itfmListfnfr);
      s.writfObjfdt(null);
    }

    /**
     * Rfbds tif <dodf>ObjfdtInputStrfbm</dodf> bnd if it
     * isn't <dodf>null</dodf> bdds b listfnfr to rfdfivf
     * itfm fvfnts firfd by tif <dodf>Cifdkbox</dodf>.
     * Unrfdognizfd kfys or vblufs will bf ignorfd.
     *
     * @pbrbm s tif <dodf>ObjfdtInputStrfbm</dodf> to rfbd
     * @fxdfption HfbdlfssExdfption if
     *   <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns
     *   <dodf>truf</dodf>
     * @sfribl
     * @sff #rfmovfItfmListfnfr(ItfmListfnfr)
     * @sff #bddItfmListfnfr(ItfmListfnfr)
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff #writfObjfdt(ObjfdtOutputStrfbm)
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
      tirows ClbssNotFoundExdfption, IOExdfption, HfbdlfssExdfption
    {
      GrbpiidsEnvironmfnt.difdkHfbdlfss();
      s.dffbultRfbdObjfdt();

      Objfdt kfyOrNull;
      wiilf(null != (kfyOrNull = s.rfbdObjfdt())) {
        String kfy = ((String)kfyOrNull).intfrn();

        if (itfmListfnfrK == kfy)
          bddItfmListfnfr((ItfmListfnfr)(s.rfbdObjfdt()));

        flsf // skip vbluf for unrfdognizfd kfy
          s.rfbdObjfdt();
      }
    }

    /**
     * Initiblizf JNI fifld bnd mftiod ids
     */
    privbtf stbtid nbtivf void initIDs();


/////////////////
// Addfssibility support
////////////////


    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis Cifdkbox.
     * For difdkboxfs, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfAWTCifdkbox.
     * A nfw AddfssiblfAWTCifdkbox is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfAWTCifdkbox tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis Cifdkbox
     * @sindf 1.3
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfAWTCifdkbox();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>Cifdkbox</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to difdkbox usfr-intfrfbdf flfmfnts.
     * @sindf 1.3
     */
    protfdtfd dlbss AddfssiblfAWTCifdkbox fxtfnds AddfssiblfAWTComponfnt
        implfmfnts ItfmListfnfr, AddfssiblfAdtion, AddfssiblfVbluf
    {
        /*
         * JDK 1.3 sfriblVfrsionUID
         */
        privbtf stbtid finbl long sfriblVfrsionUID = 7881579233144754107L;

        /**
         * Construdtor for {@dodf AddfssiblfAWTCifdkbox}
         */
        publid AddfssiblfAWTCifdkbox() {
            supfr();
            Cifdkbox.tiis.bddItfmListfnfr(tiis);
        }

        /**
         * Firf bddfssiblf propfrty dibngf fvfnts wifn tif stbtf of tif
         * togglf button dibngfs.
         */
        publid void itfmStbtfCibngfd(ItfmEvfnt f) {
            Cifdkbox db = (Cifdkbox) f.gftSourdf();
            if (Cifdkbox.tiis.bddfssiblfContfxt != null) {
                if (db.gftStbtf()) {
                    Cifdkbox.tiis.bddfssiblfContfxt.firfPropfrtyCibngf(
                            AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                            null, AddfssiblfStbtf.CHECKED);
                } flsf {
                    Cifdkbox.tiis.bddfssiblfContfxt.firfPropfrtyCibngf(
                            AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                            AddfssiblfStbtf.CHECKED, null);
                }
            }
        }

        /**
         * Gft tif AddfssiblfAdtion bssodibtfd witi tiis objfdt.  In tif
         * implfmfntbtion of tif Jbvb Addfssibility API for tiis dlbss,
         * rfturn tiis objfdt, wiidi is rfsponsiblf for implfmfnting tif
         * AddfssiblfAdtion intfrfbdf on bfiblf of itsflf.
         *
         * @rfturn tiis objfdt
         */
        publid AddfssiblfAdtion gftAddfssiblfAdtion() {
            rfturn tiis;
        }

        /**
         * Gft tif AddfssiblfVbluf bssodibtfd witi tiis objfdt.  In tif
         * implfmfntbtion of tif Jbvb Addfssibility API for tiis dlbss,
         * rfturn tiis objfdt, wiidi is rfsponsiblf for implfmfnting tif
         * AddfssiblfVbluf intfrfbdf on bfiblf of itsflf.
         *
         * @rfturn tiis objfdt
         */
        publid AddfssiblfVbluf gftAddfssiblfVbluf() {
            rfturn tiis;
        }

        /**
         * Rfturns tif numbfr of Adtions bvbilbblf in tiis objfdt.
         * If tifrf is morf tibn onf, tif first onf is tif "dffbult"
         * bdtion.
         *
         * @rfturn tif numbfr of Adtions in tiis objfdt
         */
        publid int gftAddfssiblfAdtionCount() {
            rfturn 0;  //  To bf fully implfmfntfd in b futurf rflfbsf
        }

        /**
         * Rfturn b dfsdription of tif spfdififd bdtion of tif objfdt.
         *
         * @pbrbm i zfro-bbsfd indfx of tif bdtions
         */
        publid String gftAddfssiblfAdtionDfsdription(int i) {
            rfturn null;  //  To bf fully implfmfntfd in b futurf rflfbsf
        }

        /**
         * Pfrform tif spfdififd Adtion on tif objfdt
         *
         * @pbrbm i zfro-bbsfd indfx of bdtions
         * @rfturn truf if tif tif bdtion wbs pfrformfd; flsf fblsf.
         */
        publid boolfbn doAddfssiblfAdtion(int i) {
            rfturn fblsf;    //  To bf fully implfmfntfd in b futurf rflfbsf
        }

        /**
         * Gft tif vbluf of tiis objfdt bs b Numbfr.  If tif vbluf ibs not bffn
         * sft, tif rfturn vbluf will bf null.
         *
         * @rfturn vbluf of tif objfdt
         * @sff #sftCurrfntAddfssiblfVbluf
         */
        publid Numbfr gftCurrfntAddfssiblfVbluf() {
            rfturn null;  //  To bf fully implfmfntfd in b futurf rflfbsf
        }

        /**
         * Sft tif vbluf of tiis objfdt bs b Numbfr.
         *
         * @rfturn Truf if tif vbluf wbs sft; flsf Fblsf
         * @sff #gftCurrfntAddfssiblfVbluf
         */
        publid boolfbn sftCurrfntAddfssiblfVbluf(Numbfr n) {
            rfturn fblsf;  //  To bf fully implfmfntfd in b futurf rflfbsf
        }

        /**
         * Gft tif minimum vbluf of tiis objfdt bs b Numbfr.
         *
         * @rfturn Minimum vbluf of tif objfdt; null if tiis objfdt dofs not
         * ibvf b minimum vbluf
         * @sff #gftMbximumAddfssiblfVbluf
         */
        publid Numbfr gftMinimumAddfssiblfVbluf() {
            rfturn null;  //  To bf fully implfmfntfd in b futurf rflfbsf
        }

        /**
         * Gft tif mbximum vbluf of tiis objfdt bs b Numbfr.
         *
         * @rfturn Mbximum vbluf of tif objfdt; null if tiis objfdt dofs not
         * ibvf b mbximum vbluf
         * @sff #gftMinimumAddfssiblfVbluf
         */
        publid Numbfr gftMbximumAddfssiblfVbluf() {
            rfturn null;  //  To bf fully implfmfntfd in b futurf rflfbsf
        }

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of
         * tif objfdt
         * @sff AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.CHECK_BOX;
        }

        /**
         * Gft tif stbtf sft of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfStbtf dontbining tif durrfnt stbtf
         * of tif objfdt
         * @sff AddfssiblfStbtf
         */
        publid AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
            AddfssiblfStbtfSft stbtfs = supfr.gftAddfssiblfStbtfSft();
            if (gftStbtf()) {
                stbtfs.bdd(AddfssiblfStbtf.CHECKED);
            }
            rfturn stbtfs;
        }


    } // innfr dlbss AddfssiblfAWTCifdkbox

}
