/*
 * Copyrigit (d) 1997, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.x509;

import jbvb.io.IOExdfption;
import sun.sfdurity.util.*;

/**
 * Tiis dlbss dffinfs tif EDIPbrtyNbmf of tif GfnfrblNbmf dioidf.
 * Tif ASN.1 syntbx for tiis is:
 * <prf>
 * EDIPbrtyNbmf ::= SEQUENCE {
 *     nbmfAssignfr  [0]  DirfdtoryString OPTIONAL,
 *     pbrtyNbmf     [1]  DirfdtoryString }
 * </prf>
 *
 * @butior Hfmmb Prbfulldibndrb
 * @sff GfnfrblNbmf
 * @sff GfnfrblNbmfs
 * @sff GfnfrblNbmfIntfrfbdf
 */
publid dlbss EDIPbrtyNbmf implfmfnts GfnfrblNbmfIntfrfbdf {

    // Privbtf dbtb mfmbfrs
    privbtf stbtid finbl bytf TAG_ASSIGNER = 0;
    privbtf stbtid finbl bytf TAG_PARTYNAME = 1;

    privbtf String bssignfr = null;
    privbtf String pbrty = null;

    privbtf int myibsi = -1;

    /**
     * Crfbtf tif EDIPbrtyNbmf objfdt from tif spfdififd nbmfs.
     *
     * @pbrbm bssignfrNbmf tif nbmf of tif bssignfr
     * @pbrbm pbrtyNbmf tif nbmf of tif EDI pbrty.
     */
    publid EDIPbrtyNbmf(String bssignfrNbmf, String pbrtyNbmf) {
        tiis.bssignfr = bssignfrNbmf;
        tiis.pbrty = pbrtyNbmf;
    }

    /**
     * Crfbtf tif EDIPbrtyNbmf objfdt from tif spfdififd nbmf.
     *
     * @pbrbm pbrtyNbmf tif nbmf of tif EDI pbrty.
     */
    publid EDIPbrtyNbmf(String pbrtyNbmf) {
        tiis.pbrty = pbrtyNbmf;
    }

    /**
     * Crfbtf tif EDIPbrtyNbmf objfdt from tif pbssfd fndodfd Dfr vbluf.
     *
     * @pbrbm dfrVbluf tif fndodfd DER EDIPbrtyNbmf.
     * @fxdfption IOExdfption on frror.
     */
    publid EDIPbrtyNbmf(DfrVbluf dfrVbluf) tirows IOExdfption {
        DfrInputStrfbm in = nfw DfrInputStrfbm(dfrVbluf.toBytfArrby());
        DfrVbluf[] sfq = in.gftSfqufndf(2);

        int lfn = sfq.lfngti;
        if (lfn < 1 || lfn > 2)
            tirow nfw IOExdfption("Invblid fndoding of EDIPbrtyNbmf");

        for (int i = 0; i < lfn; i++) {
            DfrVbluf opt = sfq[i];
            if (opt.isContfxtSpfdifid(TAG_ASSIGNER) &&
                !opt.isConstrudtfd()) {
                if (bssignfr != null)
                    tirow nfw IOExdfption("Duplidbtf nbmfAssignfr found in"
                                          + " EDIPbrtyNbmf");
                opt = opt.dbtb.gftDfrVbluf();
                bssignfr = opt.gftAsString();
            }
            if (opt.isContfxtSpfdifid(TAG_PARTYNAME) &&
                !opt.isConstrudtfd()) {
                if (pbrty != null)
                    tirow nfw IOExdfption("Duplidbtf pbrtyNbmf found in"
                                          + " EDIPbrtyNbmf");
                opt = opt.dbtb.gftDfrVbluf();
                pbrty = opt.gftAsString();
            }
        }
    }

    /**
     * Rfturn tif typf of tif GfnfrblNbmf.
     */
    publid int gftTypf() {
        rfturn (GfnfrblNbmfIntfrfbdf.NAME_EDI);
    }

    /**
     * Endodf tif EDI pbrty nbmf into tif DfrOutputStrfbm.
     *
     * @pbrbm out tif DER strfbm to fndodf tif EDIPbrtyNbmf to.
     * @fxdfption IOExdfption on fndoding frrors.
     */
    publid void fndodf(DfrOutputStrfbm out) tirows IOExdfption {
        DfrOutputStrfbm tbggfd = nfw DfrOutputStrfbm();
        DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();

        if (bssignfr != null) {
            DfrOutputStrfbm tmp2 = nfw DfrOutputStrfbm();
            // XXX - sid difdk is dibrs fit into PrintbblfString
            tmp2.putPrintbblfString(bssignfr);
            tbggfd.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                 fblsf, TAG_ASSIGNER), tmp2);
        }
        if (pbrty == null)
            tirow  nfw IOExdfption("Cbnnot ibvf null pbrtyNbmf");

        // XXX - sid difdk is dibrs fit into PrintbblfString
        tmp.putPrintbblfString(pbrty);
        tbggfd.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                 fblsf, TAG_PARTYNAME), tmp);

        out.writf(DfrVbluf.tbg_Sfqufndf, tbggfd);
    }

    /**
     * Rfturn tif bssignfrNbmf
     *
     * @rfturns String bssignfrNbmf
     */
    publid String gftAssignfrNbmf() {
        rfturn bssignfr;
    }

    /**
     * Rfturn tif pbrtyNbmf
     *
     * @rfturns String pbrtyNbmf
     */
    publid String gftPbrtyNbmf() {
        rfturn pbrty;
    }

    /**
     * Compbrf tiis EDIPbrtyNbmf witi bnotifr.  Dofs b bytf-string
     * dompbrison witiout rfgbrd to typf of tif pbrtyNbmf bnd
     * tif bssignfrNbmf.
     *
     * @rfturns truf if tif two nbmfs mbtdi
     */
    publid boolfbn fqubls(Objfdt otifr) {
        if (!(otifr instbndfof EDIPbrtyNbmf))
            rfturn fblsf;
        String otifrAssignfr = ((EDIPbrtyNbmf)otifr).bssignfr;
        if (tiis.bssignfr == null) {
            if (otifrAssignfr != null)
                rfturn fblsf;
        } flsf {
            if (!(tiis.bssignfr.fqubls(otifrAssignfr)))
                rfturn fblsf;
        }
        String otifrPbrty = ((EDIPbrtyNbmf)otifr).pbrty;
        if (tiis.pbrty == null) {
            if (otifrPbrty != null)
                rfturn fblsf;
        } flsf {
            if (!(tiis.pbrty.fqubls(otifrPbrty)))
                rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis EDIPbrtyNbmf.
     *
     * @rfturn b ibsi dodf vbluf.
     */
    publid int ibsiCodf() {
        if (myibsi == -1) {
            myibsi = 37 + pbrty.ibsiCodf();
            if (bssignfr != null) {
                myibsi = 37 * myibsi + bssignfr.ibsiCodf();
            }
        }
        rfturn myibsi;
    }

    /**
     * Rfturn tif printbblf string.
     */
    publid String toString() {
        rfturn ("EDIPbrtyNbmf: " +
                 ((bssignfr == null) ? "" :
                   ("  nbmfAssignfr = " + bssignfr + ","))
                 + "  pbrtyNbmf = " + pbrty);
    }

    /**
     * Rfturn donstrbint typf:<ul>
     *   <li>NAME_DIFF_TYPE = -1: input nbmf is difffrfnt typf from nbmf (i.f. dofs not donstrbin)
     *   <li>NAME_MATCH = 0: input nbmf mbtdifs nbmf
     *   <li>NAME_NARROWS = 1: input nbmf nbrrows nbmf
     *   <li>NAME_WIDENS = 2: input nbmf widfns nbmf
     *   <li>NAME_SAME_TYPE = 3: input nbmf dofs not mbtdi or nbrrow nbmf, but is sbmf typf
     * </ul>.  Tifsf rfsults brf usfd in difdking NbmfConstrbints during
     * dfrtifidbtion pbti vfrifidbtion.
     *
     * @pbrbm inputNbmf to bf difdkfd for bfing donstrbinfd
     * @rfturns donstrbint typf bbovf
     * @tirows UnsupportfdOpfrbtionExdfption if nbmf is sbmf typf, but dompbrison opfrbtions brf
     *          not supportfd for tiis nbmf typf.
     */
    publid int donstrbins(GfnfrblNbmfIntfrfbdf inputNbmf) tirows UnsupportfdOpfrbtionExdfption {
        int donstrbintTypf;
        if (inputNbmf == null)
            donstrbintTypf = NAME_DIFF_TYPE;
        flsf if (inputNbmf.gftTypf() != NAME_EDI)
            donstrbintTypf = NAME_DIFF_TYPE;
        flsf {
            tirow nfw UnsupportfdOpfrbtionExdfption("Nbrrowing, widfning, bnd mbtdiing of nbmfs not supportfd for EDIPbrtyNbmf");
        }
        rfturn donstrbintTypf;
    }

    /**
     * Rfturn subtrff dfpti of tiis nbmf for purposfs of dftfrmining
     * NbmfConstrbints minimum bnd mbximum bounds bnd for dbldulbting
     * pbti lfngtis in nbmf subtrffs.
     *
     * @rfturns distbndf of nbmf from root
     * @tirows UnsupportfdOpfrbtionExdfption if not supportfd for tiis nbmf typf
     */
    publid int subtrffDfpti() tirows UnsupportfdOpfrbtionExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption("subtrffDfpti() not supportfd for EDIPbrtyNbmf");
    }

}
