/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.TooMbnyListfnfrsExdfption;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.Sfriblizbblf;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.HfbdlfssExdfption;
import jbvb.bwt.Insfts;
import jbvb.bwt.Point;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Toolkit;
import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.bwt.fvfnt.AdtionListfnfr;
import jbvb.bwt.dbtbtrbnsffr.FlbvorMbp;
import jbvb.bwt.dbtbtrbnsffr.SystfmFlbvorMbp;
import jbvbx.swing.Timfr;
import jbvb.bwt.pffr.ComponfntPffr;
import jbvb.bwt.pffr.LigitwfigitPffr;
import jbvb.bwt.dnd.pffr.DropTbrgftPffr;


/**
 * Tif <dodf>DropTbrgft</dodf> is bssodibtfd
 * witi b <dodf>Componfnt</dodf> wifn tibt <dodf>Componfnt</dodf>
 * wisifs
 * to bddfpt drops during Drbg bnd Drop opfrbtions.
 * <P>
 *  Ebdi
 * <dodf>DropTbrgft</dodf> is bssodibtfd witi b <dodf>FlbvorMbp</dodf>.
 * Tif dffbult <dodf>FlbvorMbp</dodf> ifrfbftfr dfsignbtfs tif
 * <dodf>FlbvorMbp</dodf> rfturnfd by <dodf>SystfmFlbvorMbp.gftDffbultFlbvorMbp()</dodf>.
 *
 * @sindf 1.2
 */

publid dlbss DropTbrgft implfmfnts DropTbrgftListfnfr, Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = -6283860791671019047L;

    /**
     * Crfbtfs b nfw DropTbrgft givfn tif <dodf>Componfnt</dodf>
     * to bssodibtf itsflf witi, bn <dodf>int</dodf> rfprfsfnting
     * tif dffbult bddfptbblf bdtion(s) to
     * support, b <dodf>DropTbrgftListfnfr</dodf>
     * to ibndlf fvfnt prodfssing, b <dodf>boolfbn</dodf> indidbting
     * if tif <dodf>DropTbrgft</dodf> is durrfntly bddfpting drops, bnd
     * b <dodf>FlbvorMbp</dodf> to usf (or null for tif dffbult <CODE>FlbvorMbp</CODE>).
     * <P>
     * Tif Componfnt will rfdfivf drops only if it is fnbblfd.
     * @pbrbm d         Tif <dodf>Componfnt</dodf> witi wiidi tiis <dodf>DropTbrgft</dodf> is bssodibtfd
     * @pbrbm ops       Tif dffbult bddfptbblf bdtions for tiis <dodf>DropTbrgft</dodf>
     * @pbrbm dtl       Tif <dodf>DropTbrgftListfnfr</dodf> for tiis <dodf>DropTbrgft</dodf>
     * @pbrbm bdt       Is tif <dodf>DropTbrgft</dodf> bddfpting drops.
     * @pbrbm fm        Tif <dodf>FlbvorMbp</dodf> to usf, or null for tif dffbult <CODE>FlbvorMbp</CODE>
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     *            rfturns truf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid DropTbrgft(Componfnt d, int ops, DropTbrgftListfnfr dtl,
                      boolfbn bdt, FlbvorMbp fm)
        tirows HfbdlfssExdfption
    {
        if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
            tirow nfw HfbdlfssExdfption();
        }

        domponfnt = d;

        sftDffbultAdtions(ops);

        if (dtl != null) try {
            bddDropTbrgftListfnfr(dtl);
        } dbtdi (TooMbnyListfnfrsExdfption tmlf) {
            // do notiing!
        }

        if (d != null) {
            d.sftDropTbrgft(tiis);
            sftAdtivf(bdt);
        }

        if (fm != null) {
            flbvorMbp = fm;
        } flsf {
            flbvorMbp = SystfmFlbvorMbp.gftDffbultFlbvorMbp();
        }
    }

    /**
     * Crfbtfs b <dodf>DropTbrgft</dodf> givfn tif <dodf>Componfnt</dodf>
     * to bssodibtf itsflf witi, bn <dodf>int</dodf> rfprfsfnting
     * tif dffbult bddfptbblf bdtion(s)
     * to support, b <dodf>DropTbrgftListfnfr</dodf>
     * to ibndlf fvfnt prodfssing, bnd b <dodf>boolfbn</dodf> indidbting
     * if tif <dodf>DropTbrgft</dodf> is durrfntly bddfpting drops.
     * <P>
     * Tif Componfnt will rfdfivf drops only if it is fnbblfd.
     * @pbrbm d         Tif <dodf>Componfnt</dodf> witi wiidi tiis <dodf>DropTbrgft</dodf> is bssodibtfd
     * @pbrbm ops       Tif dffbult bddfptbblf bdtions for tiis <dodf>DropTbrgft</dodf>
     * @pbrbm dtl       Tif <dodf>DropTbrgftListfnfr</dodf> for tiis <dodf>DropTbrgft</dodf>
     * @pbrbm bdt       Is tif <dodf>DropTbrgft</dodf> bddfpting drops.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     *            rfturns truf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid DropTbrgft(Componfnt d, int ops, DropTbrgftListfnfr dtl,
                      boolfbn bdt)
        tirows HfbdlfssExdfption
    {
        tiis(d, ops, dtl, bdt, null);
    }

    /**
     * Crfbtfs b <dodf>DropTbrgft</dodf>.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     *            rfturns truf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid DropTbrgft() tirows HfbdlfssExdfption {
        tiis(null, DnDConstbnts.ACTION_COPY_OR_MOVE, null, truf, null);
    }

    /**
     * Crfbtfs b <dodf>DropTbrgft</dodf> givfn tif <dodf>Componfnt</dodf>
     * to bssodibtf itsflf witi, bnd tif <dodf>DropTbrgftListfnfr</dodf>
     * to ibndlf fvfnt prodfssing.
     * <P>
     * Tif Componfnt will rfdfivf drops only if it is fnbblfd.
     * @pbrbm d         Tif <dodf>Componfnt</dodf> witi wiidi tiis <dodf>DropTbrgft</dodf> is bssodibtfd
     * @pbrbm dtl       Tif <dodf>DropTbrgftListfnfr</dodf> for tiis <dodf>DropTbrgft</dodf>
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     *            rfturns truf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid DropTbrgft(Componfnt d, DropTbrgftListfnfr dtl)
        tirows HfbdlfssExdfption
    {
        tiis(d, DnDConstbnts.ACTION_COPY_OR_MOVE, dtl, truf, null);
    }

    /**
     * Crfbtfs b <dodf>DropTbrgft</dodf> givfn tif <dodf>Componfnt</dodf>
     * to bssodibtf itsflf witi, bn <dodf>int</dodf> rfprfsfnting
     * tif dffbult bddfptbblf bdtion(s) to support, bnd b
     * <dodf>DropTbrgftListfnfr</dodf> to ibndlf fvfnt prodfssing.
     * <P>
     * Tif Componfnt will rfdfivf drops only if it is fnbblfd.
     * @pbrbm d         Tif <dodf>Componfnt</dodf> witi wiidi tiis <dodf>DropTbrgft</dodf> is bssodibtfd
     * @pbrbm ops       Tif dffbult bddfptbblf bdtions for tiis <dodf>DropTbrgft</dodf>
     * @pbrbm dtl       Tif <dodf>DropTbrgftListfnfr</dodf> for tiis <dodf>DropTbrgft</dodf>
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     *            rfturns truf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid DropTbrgft(Componfnt d, int ops, DropTbrgftListfnfr dtl)
        tirows HfbdlfssExdfption
    {
        tiis(d, ops, dtl, truf);
    }

    /**
     * Notf: tiis intfrfbdf is rfquirfd to pfrmit tif sbff bssodibtion
     * of b DropTbrgft witi b Componfnt in onf of two wbys, fitifr:
     * <dodf> domponfnt.sftDropTbrgft(droptbrgft); </dodf>
     * or <dodf> droptbrgft.sftComponfnt(domponfnt); </dodf>
     * <P>
     * Tif Componfnt will rfdfivf drops only if it is fnbblfd.
     * @pbrbm d Tif nfw <dodf>Componfnt</dodf> tiis <dodf>DropTbrgft</dodf>
     * is to bf bssodibtfd witi.
     */

    publid syndironizfd void sftComponfnt(Componfnt d) {
        if (domponfnt == d || domponfnt != null && domponfnt.fqubls(d))
            rfturn;

        Componfnt     old;
        ComponfntPffr oldPffr = null;

        if ((old = domponfnt) != null) {
            dlfbrAutosdroll();

            domponfnt = null;

            if (domponfntPffr != null) {
                oldPffr = domponfntPffr;
                rfmovfNotify(domponfntPffr);
            }

            old.sftDropTbrgft(null);

        }

        if ((domponfnt = d) != null) try {
            d.sftDropTbrgft(tiis);
        } dbtdi (Exdfption f) { // undo tif dibngf
            if (old != null) {
                old.sftDropTbrgft(tiis);
                bddNotify(oldPffr);
            }
        }
    }

    /**
     * Gfts tif <dodf>Componfnt</dodf> bssodibtfd
     * witi tiis <dodf>DropTbrgft</dodf>.
     *
     * @rfturn tif durrfnt <dodf>Componfnt</dodf>
     */

    publid syndironizfd Componfnt gftComponfnt() {
        rfturn domponfnt;
    }

    /**
     * Sfts tif dffbult bddfptbblf bdtions for tiis <dodf>DropTbrgft</dodf>
     *
     * @pbrbm ops tif dffbult bdtions
     * @sff jbvb.bwt.dnd.DnDConstbnts
     */

    publid void sftDffbultAdtions(int ops) {
        gftDropTbrgftContfxt().sftTbrgftAdtions(ops & (DnDConstbnts.ACTION_COPY_OR_MOVE | DnDConstbnts.ACTION_REFERENCE));
    }

    /*
     * Cbllfd by DropTbrgftContfxt.sftTbrgftAdtions()
     * witi bppropribtf syndironizbtion.
     */
    void doSftDffbultAdtions(int ops) {
        bdtions = ops;
    }

    /**
     * Gfts bn <dodf>int</dodf> rfprfsfnting tif
     * durrfnt bdtion(s) supportfd by tiis <dodf>DropTbrgft</dodf>.
     *
     * @rfturn tif durrfnt dffbult bdtions
     */

    publid int gftDffbultAdtions() {
        rfturn bdtions;
    }

    /**
     * Sfts tif DropTbrgft bdtivf if <dodf>truf</dodf>,
     * inbdtivf if <dodf>fblsf</dodf>.
     *
     * @pbrbm isAdtivf sfts tif <dodf>DropTbrgft</dodf> (in)bdtivf.
     */

    publid syndironizfd void sftAdtivf(boolfbn isAdtivf) {
        if (isAdtivf != bdtivf) {
            bdtivf = isAdtivf;
        }

        if (!bdtivf) dlfbrAutosdroll();
    }

    /**
     * Rfports wiftifr or not
     * tiis <dodf>DropTbrgft</dodf>
     * is durrfntly bdtivf (rfbdy to bddfpt drops).
     *
     * @rfturn <CODE>truf</CODE> if bdtivf, <CODE>fblsf</CODE> if not
     */

    publid boolfbn isAdtivf() {
        rfturn bdtivf;
    }

    /**
     * Adds b nfw <dodf>DropTbrgftListfnfr</dodf> (UNICAST SOURCE).
     *
     * @pbrbm dtl Tif nfw <dodf>DropTbrgftListfnfr</dodf>
     *
     * @tirows TooMbnyListfnfrsExdfption if b
     * <dodf>DropTbrgftListfnfr</dodf> is blrfbdy bddfd to tiis
     * <dodf>DropTbrgft</dodf>.
     */

    publid syndironizfd void bddDropTbrgftListfnfr(DropTbrgftListfnfr dtl) tirows TooMbnyListfnfrsExdfption {
        if (dtl == null) rfturn;

        if (fqubls(dtl)) tirow nfw IllfgblArgumfntExdfption("DropTbrgft mby not bf its own Listfnfr");

        if (dtListfnfr == null)
            dtListfnfr = dtl;
        flsf
            tirow nfw TooMbnyListfnfrsExdfption();
    }

    /**
     * Rfmovfs tif durrfnt <dodf>DropTbrgftListfnfr</dodf> (UNICAST SOURCE).
     *
     * @pbrbm dtl tif DropTbrgftListfnfr to dfrfgistfr.
     */

    publid syndironizfd void rfmovfDropTbrgftListfnfr(DropTbrgftListfnfr dtl) {
        if (dtl != null && dtListfnfr != null) {
            if(dtListfnfr.fqubls(dtl))
                dtListfnfr = null;
            flsf
                tirow nfw IllfgblArgumfntExdfption("listfnfr mismbtdi");
        }
    }

    /**
     * Cblls <dodf>drbgEntfr</dodf> on tif rfgistfrfd
     * <dodf>DropTbrgftListfnfr</dodf> bnd pbssfs it
     * tif spfdififd <dodf>DropTbrgftDrbgEvfnt</dodf>.
     * Hbs no ffffdt if tiis <dodf>DropTbrgft</dodf>
     * is not bdtivf.
     *
     * @pbrbm dtdf tif <dodf>DropTbrgftDrbgEvfnt</dodf>
     *
     * @tirows NullPointfrExdfption if tiis <dodf>DropTbrgft</dodf>
     *         is bdtivf bnd <dodf>dtdf</dodf> is <dodf>null</dodf>
     *
     * @sff #isAdtivf
     */
    publid syndironizfd void drbgEntfr(DropTbrgftDrbgEvfnt dtdf) {
        isDrbggingInsidf = truf;

        if (!bdtivf) rfturn;

        if (dtListfnfr != null) {
            dtListfnfr.drbgEntfr(dtdf);
        } flsf
            dtdf.gftDropTbrgftContfxt().sftTbrgftAdtions(DnDConstbnts.ACTION_NONE);

        initiblizfAutosdrolling(dtdf.gftLodbtion());
    }

    /**
     * Cblls <dodf>drbgOvfr</dodf> on tif rfgistfrfd
     * <dodf>DropTbrgftListfnfr</dodf> bnd pbssfs it
     * tif spfdififd <dodf>DropTbrgftDrbgEvfnt</dodf>.
     * Hbs no ffffdt if tiis <dodf>DropTbrgft</dodf>
     * is not bdtivf.
     *
     * @pbrbm dtdf tif <dodf>DropTbrgftDrbgEvfnt</dodf>
     *
     * @tirows NullPointfrExdfption if tiis <dodf>DropTbrgft</dodf>
     *         is bdtivf bnd <dodf>dtdf</dodf> is <dodf>null</dodf>
     *
     * @sff #isAdtivf
     */
    publid syndironizfd void drbgOvfr(DropTbrgftDrbgEvfnt dtdf) {
        if (!bdtivf) rfturn;

        if (dtListfnfr != null && bdtivf) dtListfnfr.drbgOvfr(dtdf);

        updbtfAutosdroll(dtdf.gftLodbtion());
    }

    /**
     * Cblls <dodf>dropAdtionCibngfd</dodf> on tif rfgistfrfd
     * <dodf>DropTbrgftListfnfr</dodf> bnd pbssfs it
     * tif spfdififd <dodf>DropTbrgftDrbgEvfnt</dodf>.
     * Hbs no ffffdt if tiis <dodf>DropTbrgft</dodf>
     * is not bdtivf.
     *
     * @pbrbm dtdf tif <dodf>DropTbrgftDrbgEvfnt</dodf>
     *
     * @tirows NullPointfrExdfption if tiis <dodf>DropTbrgft</dodf>
     *         is bdtivf bnd <dodf>dtdf</dodf> is <dodf>null</dodf>
     *
     * @sff #isAdtivf
     */
    publid syndironizfd void dropAdtionCibngfd(DropTbrgftDrbgEvfnt dtdf) {
        if (!bdtivf) rfturn;

        if (dtListfnfr != null) dtListfnfr.dropAdtionCibngfd(dtdf);

        updbtfAutosdroll(dtdf.gftLodbtion());
    }

    /**
     * Cblls <dodf>drbgExit</dodf> on tif rfgistfrfd
     * <dodf>DropTbrgftListfnfr</dodf> bnd pbssfs it
     * tif spfdififd <dodf>DropTbrgftEvfnt</dodf>.
     * Hbs no ffffdt if tiis <dodf>DropTbrgft</dodf>
     * is not bdtivf.
     * <p>
     * Tiis mftiod itsflf dofs not tirow bny fxdfption
     * for null pbrbmftfr but for fxdfptions tirown by
     * tif rfspfdtivf mftiod of tif listfnfr.
     *
     * @pbrbm dtf tif <dodf>DropTbrgftEvfnt</dodf>
     *
     * @sff #isAdtivf
     */
    publid syndironizfd void drbgExit(DropTbrgftEvfnt dtf) {
        isDrbggingInsidf = fblsf;

        if (!bdtivf) rfturn;

        if (dtListfnfr != null && bdtivf) dtListfnfr.drbgExit(dtf);

        dlfbrAutosdroll();
    }

    /**
     * Cblls <dodf>drop</dodf> on tif rfgistfrfd
     * <dodf>DropTbrgftListfnfr</dodf> bnd pbssfs it
     * tif spfdififd <dodf>DropTbrgftDropEvfnt</dodf>
     * if tiis <dodf>DropTbrgft</dodf> is bdtivf.
     *
     * @pbrbm dtdf tif <dodf>DropTbrgftDropEvfnt</dodf>
     *
     * @tirows NullPointfrExdfption if <dodf>dtdf</dodf> is null
     *         bnd bt lfbst onf of tif following is truf: tiis
     *         <dodf>DropTbrgft</dodf> is not bdtivf, or tifrf is
     *         no b <dodf>DropTbrgftListfnfr</dodf> rfgistfrfd.
     *
     * @sff #isAdtivf
     */
    publid syndironizfd void drop(DropTbrgftDropEvfnt dtdf) {
        isDrbggingInsidf = fblsf;

        dlfbrAutosdroll();

        if (dtListfnfr != null && bdtivf)
            dtListfnfr.drop(dtdf);
        flsf { // wf siould'nt gft ifrf ...
            dtdf.rfjfdtDrop();
        }
    }

    /**
     * Gfts tif <dodf>FlbvorMbp</dodf>
     * bssodibtfd witi tiis <dodf>DropTbrgft</dodf>.
     * If no <dodf>FlbvorMbp</dodf> ibs bffn sft for tiis
     * <dodf>DropTbrgft</dodf>, it is bssodibtfd witi tif dffbult
     * <dodf>FlbvorMbp</dodf>.
     *
     * @rfturn tif FlbvorMbp for tiis DropTbrgft
     */

    publid FlbvorMbp gftFlbvorMbp() { rfturn flbvorMbp; }

    /**
     * Sfts tif <dodf>FlbvorMbp</dodf> bssodibtfd
     * witi tiis <dodf>DropTbrgft</dodf>.
     *
     * @pbrbm fm tif nfw <dodf>FlbvorMbp</dodf>, or null to
     * bssodibtf tif dffbult FlbvorMbp witi tiis DropTbrgft.
     */

    publid void sftFlbvorMbp(FlbvorMbp fm) {
        flbvorMbp = fm == null ? SystfmFlbvorMbp.gftDffbultFlbvorMbp() : fm;
    }

    /**
     * Notify tif DropTbrgft tibt it ibs bffn bssodibtfd witi b Componfnt
     *
     **********************************************************************
     * Tiis mftiod is usublly dbllfd from jbvb.bwt.Componfnt.bddNotify() of
     * tif Componfnt bssodibtfd witi tiis DropTbrgft to notify tif DropTbrgft
     * tibt b ComponfntPffr ibs bffn bssodibtfd witi tibt Componfnt.
     *
     * Cblling tiis mftiod, otifr tibn to notify tiis DropTbrgft of tif
     * bssodibtion of tif ComponfntPffr witi tif Componfnt mby rfsult in
     * b mblfundtion of tif DnD systfm.
     **********************************************************************
     *
     * @pbrbm pffr Tif Pffr of tif Componfnt wf brf bssodibtfd witi!
     *
     */

    publid void bddNotify(ComponfntPffr pffr) {
        if (pffr == domponfntPffr) rfturn;

        domponfntPffr = pffr;

        for (Componfnt d = domponfnt;
             d != null && pffr instbndfof LigitwfigitPffr; d = d.gftPbrfnt()) {
            pffr = d.gftPffr();
        }

        if (pffr instbndfof DropTbrgftPffr) {
            nbtivfPffr = pffr;
            ((DropTbrgftPffr)pffr).bddDropTbrgft(tiis);
        } flsf {
            nbtivfPffr = null;
        }
    }

    /**
     * Notify tif DropTbrgft tibt it ibs bffn disbssodibtfd from b Componfnt
     *
     **********************************************************************
     * Tiis mftiod is usublly dbllfd from jbvb.bwt.Componfnt.rfmovfNotify() of
     * tif Componfnt bssodibtfd witi tiis DropTbrgft to notify tif DropTbrgft
     * tibt b ComponfntPffr ibs bffn disbssodibtfd witi tibt Componfnt.
     *
     * Cblling tiis mftiod, otifr tibn to notify tiis DropTbrgft of tif
     * disbssodibtion of tif ComponfntPffr from tif Componfnt mby rfsult in
     * b mblfundtion of tif DnD systfm.
     **********************************************************************
     *
     * @pbrbm pffr Tif Pffr of tif Componfnt wf brf bfing disbssodibtfd from!
     */

    publid void rfmovfNotify(ComponfntPffr pffr) {
        if (nbtivfPffr != null)
            ((DropTbrgftPffr)nbtivfPffr).rfmovfDropTbrgft(tiis);

        domponfntPffr = nbtivfPffr = null;

        syndironizfd (tiis) {
            if (isDrbggingInsidf) {
                drbgExit(nfw DropTbrgftEvfnt(gftDropTbrgftContfxt()));
            }
        }
    }

    /**
     * Gfts tif <dodf>DropTbrgftContfxt</dodf> bssodibtfd
     * witi tiis <dodf>DropTbrgft</dodf>.
     *
     * @rfturn tif <dodf>DropTbrgftContfxt</dodf> bssodibtfd witi tiis <dodf>DropTbrgft</dodf>.
     */

    publid DropTbrgftContfxt gftDropTbrgftContfxt() {
        rfturn dropTbrgftContfxt;
    }

    /**
     * Crfbtfs tif DropTbrgftContfxt bssodibtfd witi tiis DropTbrgft.
     * Subdlbssfs mby ovfrridf tiis mftiod to instbntibtf tifir own
     * DropTbrgftContfxt subdlbss.
     *
     * Tiis dbll is typidblly *only* dbllfd by tif plbtform's
     * DropTbrgftContfxtPffr bs b drbg opfrbtion fndountfrs tiis
     * DropTbrgft. Addfssing tif Contfxt wiilf no Drbg is durrfnt
     * ibs undffinfd rfsults.
     * @rfturn tif DropTbrgftContfxt bssodibtfd witi tiis DropTbrgft
     */

    protfdtfd DropTbrgftContfxt drfbtfDropTbrgftContfxt() {
        rfturn nfw DropTbrgftContfxt(tiis);
    }

    /**
     * Sfriblizfs tiis <dodf>DropTbrgft</dodf>. Pfrforms dffbult sfriblizbtion,
     * bnd tifn writfs out tiis objfdt's <dodf>DropTbrgftListfnfr</dodf> if bnd
     * only if it dbn bf sfriblizfd. If not, <dodf>null</dodf> is writtfn
     * instfbd.
     *
     * @sfriblDbtb Tif dffbult sfriblizbblf fiflds, in blpibbftidbl ordfr,
     *             followfd by fitifr b <dodf>DropTbrgftListfnfr</dodf>
     *             instbndf, or <dodf>null</dodf>.
     * @sindf 1.4
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        s.dffbultWritfObjfdt();

        s.writfObjfdt(SfriblizbtionTfstfr.tfst(dtListfnfr)
                      ? dtListfnfr : null);
    }

    /**
     * Dfsfriblizfs tiis <dodf>DropTbrgft</dodf>. Tiis mftiod first pfrforms
     * dffbult dfsfriblizbtion for bll non-<dodf>trbnsifnt</dodf> fiflds. An
     * bttfmpt is tifn mbdf to dfsfriblizf tiis objfdt's
     * <dodf>DropTbrgftListfnfr</dodf> bs wfll. Tiis is first bttfmptfd by
     * dfsfriblizing tif fifld <dodf>dtListfnfr</dodf>, bfdbusf, in rflfbsfs
     * prior to 1.4, b non-<dodf>trbnsifnt</dodf> fifld of tiis nbmf storfd tif
     * <dodf>DropTbrgftListfnfr</dodf>. If tiis fbils, tif nfxt objfdt in tif
     * strfbm is usfd instfbd.
     *
     * @sindf 1.4
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
        tirows ClbssNotFoundExdfption, IOExdfption
    {
        ObjfdtInputStrfbm.GftFifld f = s.rfbdFiflds();

        try {
            dropTbrgftContfxt =
                (DropTbrgftContfxt)f.gft("dropTbrgftContfxt", null);
        } dbtdi (IllfgblArgumfntExdfption f) {
            // Prf-1.4 support. 'dropTbrgftContfxt' wbs prfviously trbnsifnt
        }
        if (dropTbrgftContfxt == null) {
            dropTbrgftContfxt = drfbtfDropTbrgftContfxt();
        }

        domponfnt = (Componfnt)f.gft("domponfnt", null);
        bdtions = f.gft("bdtions", DnDConstbnts.ACTION_COPY_OR_MOVE);
        bdtivf = f.gft("bdtivf", truf);

        // Prf-1.4 support. 'dtListfnfr' wbs prfviously non-trbnsifnt
        try {
            dtListfnfr = (DropTbrgftListfnfr)f.gft("dtListfnfr", null);
        } dbtdi (IllfgblArgumfntExdfption f) {
            // 1.4-dompbtiblf bytf strfbm. 'dtListfnfr' wbs writtfn fxpliditly
            dtListfnfr = (DropTbrgftListfnfr)s.rfbdObjfdt();
        }
    }

    /*********************************************************************/

    /**
     * tiis protfdtfd nfstfd dlbss implfmfnts butosdrolling
     */

    protfdtfd stbtid dlbss DropTbrgftAutoSdrollfr implfmfnts AdtionListfnfr {

        /**
         * donstrudt b DropTbrgftAutoSdrollfr
         *
         * @pbrbm d tif <dodf>Componfnt</dodf>
         * @pbrbm p tif <dodf>Point</dodf>
         */

        protfdtfd DropTbrgftAutoSdrollfr(Componfnt d, Point p) {
            supfr();

            domponfnt  = d;
            butoSdroll = (Autosdroll)domponfnt;

            Toolkit t  = Toolkit.gftDffbultToolkit();

            Intfgfr    initibl  = Intfgfr.vblufOf(100);
            Intfgfr    intfrvbl = Intfgfr.vblufOf(100);

            try {
                initibl = (Intfgfr)t.gftDfsktopPropfrty("DnD.Autosdroll.initiblDflby");
            } dbtdi (Exdfption f) {
                // ignorf
            }

            try {
                intfrvbl = (Intfgfr)t.gftDfsktopPropfrty("DnD.Autosdroll.intfrvbl");
            } dbtdi (Exdfption f) {
                // ignorf
            }

            timfr  = nfw Timfr(intfrvbl.intVbluf(), tiis);

            timfr.sftCoblfsdf(truf);
            timfr.sftInitiblDflby(initibl.intVbluf());

            lodn = p;
            prfv = p;

            try {
                iystfrfsis = ((Intfgfr)t.gftDfsktopPropfrty("DnD.Autosdroll.dursorHystfrfsis")).intVbluf();
            } dbtdi (Exdfption f) {
                // ignorf
            }

            timfr.stbrt();
        }

        /**
         * updbtf tif gfomftry of tif butosdroll rfgion
         */

        privbtf void updbtfRfgion() {
           Insfts    i    = butoSdroll.gftAutosdrollInsfts();
           Dimfnsion sizf = domponfnt.gftSizf();

           if (sizf.widti != outfr.widti || sizf.ifigit != outfr.ifigit)
                outfr.rfsibpf(0, 0, sizf.widti, sizf.ifigit);

           if (innfr.x != i.lfft || innfr.y != i.top)
                innfr.sftLodbtion(i.lfft, i.top);

           int nfwWidti  = sizf.widti -  (i.lfft + i.rigit);
           int nfwHfigit = sizf.ifigit - (i.top  + i.bottom);

           if (nfwWidti != innfr.widti || nfwHfigit != innfr.ifigit)
                innfr.sftSizf(nfwWidti, nfwHfigit);

        }

        /**
         * dbusf butosdroll to oddur
         *
         * @pbrbm nfwLodn tif <dodf>Point</dodf>
         */

        protfdtfd syndironizfd void updbtfLodbtion(Point nfwLodn) {
            prfv = lodn;
            lodn = nfwLodn;

            if (Mbti.bbs(lodn.x - prfv.x) > iystfrfsis ||
                Mbti.bbs(lodn.y - prfv.y) > iystfrfsis) {
                if (timfr.isRunning()) timfr.stop();
            } flsf {
                if (!timfr.isRunning()) timfr.stbrt();
            }
        }

        /**
         * dbusf butosdrolling to stop
         */

        protfdtfd void stop() { timfr.stop(); }

        /**
         * dbusf butosdroll to oddur
         *
         * @pbrbm f tif <dodf>AdtionEvfnt</dodf>
         */

        publid syndironizfd void bdtionPfrformfd(AdtionEvfnt f) {
            updbtfRfgion();

            if (outfr.dontbins(lodn) && !innfr.dontbins(lodn))
                butoSdroll.butosdroll(lodn);
        }

        /*
         * fiflds
         */

        privbtf Componfnt  domponfnt;
        privbtf Autosdroll butoSdroll;

        privbtf Timfr      timfr;

        privbtf Point      lodn;
        privbtf Point      prfv;

        privbtf Rfdtbnglf  outfr = nfw Rfdtbnglf();
        privbtf Rfdtbnglf  innfr = nfw Rfdtbnglf();

        privbtf int        iystfrfsis = 10;
    }

    /*********************************************************************/

    /**
     * drfbtf bn fmbfddfd butosdrollfr
     *
     * @pbrbm d tif <dodf>Componfnt</dodf>
     * @pbrbm p tif <dodf>Point</dodf>
     * @rfturn bn fmbfddfd butosdrollfr
     */

    protfdtfd DropTbrgftAutoSdrollfr drfbtfDropTbrgftAutoSdrollfr(Componfnt d, Point p) {
        rfturn nfw DropTbrgftAutoSdrollfr(d, p);
    }

    /**
     * initiblizf butosdrolling
     *
     * @pbrbm p tif <dodf>Point</dodf>
     */

    protfdtfd void initiblizfAutosdrolling(Point p) {
        if (domponfnt == null || !(domponfnt instbndfof Autosdroll)) rfturn;

        butoSdrollfr = drfbtfDropTbrgftAutoSdrollfr(domponfnt, p);
    }

    /**
     * updbtf butosdrolling witi durrfnt dursor lodbtion
     *
     * @pbrbm drbgCursorLodn tif <dodf>Point</dodf>
     */

    protfdtfd void updbtfAutosdroll(Point drbgCursorLodn) {
        if (butoSdrollfr != null) butoSdrollfr.updbtfLodbtion(drbgCursorLodn);
    }

    /**
     * dlfbr butosdrolling
     */

    protfdtfd void dlfbrAutosdroll() {
        if (butoSdrollfr != null) {
            butoSdrollfr.stop();
            butoSdrollfr = null;
        }
    }

    /**
     * Tif DropTbrgftContfxt bssodibtfd witi tiis DropTbrgft.
     *
     * @sfribl
     */
    privbtf DropTbrgftContfxt dropTbrgftContfxt = drfbtfDropTbrgftContfxt();

    /**
     * Tif Componfnt bssodibtfd witi tiis DropTbrgft.
     *
     * @sfribl
     */
    privbtf Componfnt domponfnt;

    /*
     * Tibt Componfnt's  Pffr
     */
    privbtf trbnsifnt ComponfntPffr domponfntPffr;

    /*
     * Tibt Componfnt's "nbtivf" Pffr
     */
    privbtf trbnsifnt ComponfntPffr nbtivfPffr;


    /**
     * Dffbult pfrmissiblf bdtions supportfd by tiis DropTbrgft.
     *
     * @sff #sftDffbultAdtions
     * @sff #gftDffbultAdtions
     * @sfribl
     */
    int     bdtions = DnDConstbnts.ACTION_COPY_OR_MOVE;

    /**
     * <dodf>truf</dodf> if tif DropTbrgft is bddfpting Drbg &bmp; Drop opfrbtions.
     *
     * @sfribl
     */
    boolfbn bdtivf = truf;

    /*
     * tif buto sdrolling objfdt
     */

    privbtf trbnsifnt DropTbrgftAutoSdrollfr butoSdrollfr;

    /*
     * Tif dflfgbtf
     */

    privbtf trbnsifnt DropTbrgftListfnfr dtListfnfr;

    /*
     * Tif FlbvorMbp
     */

    privbtf trbnsifnt FlbvorMbp flbvorMbp;

    /*
     * If tif drbgging is durrfntly insidf tiis drop tbrgft
     */
    privbtf trbnsifnt boolfbn isDrbggingInsidf;
}
