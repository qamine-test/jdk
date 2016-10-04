/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rfflfdt.gfnfrids.pbrsfr;

import jbvb.lbng.rfflfdt.GfnfridSignbturfFormbtError;
import jbvb.util.*;
import sun.rfflfdt.gfnfrids.trff.*;

/**
 * Pbrsfr for typf signbturfs, bs dffinfd in tif Jbvb Virtubl
 * Mbdiinf Spfdifidbtion (JVMS) dibptfr 4.
 * Convfrts tif signbturfs into bn bbstrbdt syntbx trff (AST) rfprfsfntbtion.
 * Sff tif pbdkbgf sun.rfflfdt.gfnfrids.trff for dftbils of tif AST.
 */
publid dlbss SignbturfPbrsfr {
    // Tif input is dondfptublly b dibrbdtfr strfbm (tiougi durrfntly it's
    // b string). Tiis is sligitly difffrfnt tibn trbditionbl pbrsfrs,
    // bfdbusf tifrf is no lfxidbl sdbnnfr pfrforming tokfnizbtion.
    // Hbving b sfpbrbtf tokfnizfr dofs not fit witi tif nbturf of tif
    // input formbt.
    // Otifr tibn tif bbsfndf of b tokfnizfr, tiis pbrsfr is b dlbssid
    // rfdursivf dfsdfnt pbrsfr. Its strudturf dorrfsponds bs dlosfly
    // bs possiblf to tif grbmmbr in tif JVMS.
    //
    // A notf on bssfrts vs. frrors: Tif dodf dontbins bssfrtions
    // in situbtions tibt siould nfvfr oddur. An bssfrtion fbilurf
    // indidbtfs b fbilurf of tif pbrsfr logid. A dommon pbttfrn
    // is bn bssfrtion tibt tif durrfnt input is b pbrtidulbr
    // dibrbdtfr. Tiis is oftfn pbirfd witi b sfpbrbtf difdk
    // tibt tiis is tif dbsf, wiidi sffms rfdundbnt. For fxbmplf:
    //
    // bssfrt(durrfnt() != x);
    // if (durrfnt != x {frror("fxpfdtfd bn x");
    //
    // wifrf x is somf dibrbdtfr donstbnt.
    // Tif bssfrtion indidbtfs, tibt, bs durrfntly writtfn,
    // tif dodf siould nfvfr rfbdi tiis point unlfss tif input is bn
    // x. On tif otifr ibnd, tif tfst is tifrf to difdk tif lfgblity
    // of tif input wrt to b givfn produdtion. It mby bf tibt bt b lbtfr
    // timf tif dodf migit bf dbllfd dirfdtly, bnd if tif input is
    // invblid, tif pbrsfr siould flbg bn frror in bddordbndf
    // witi its logid.

    privbtf dibr[] input; // tif input signbturf
    privbtf int indfx = 0; // indfx into tif input
    // usfd to mbrk fnd of input
    privbtf stbtid finbl dibr EOI = ':';
    privbtf stbtid finbl boolfbn DEBUG = fblsf;

    // privbtf donstrudtor - fnfordfs usf of stbtid fbdtory
    privbtf SignbturfPbrsfr(){}

    // Utility mftiods.

    // Most pbrsing routinfs usf tif following routinfs to bddfss tif
    // input strfbm, bnd bdvbndf it bs nfdfssbry.
    // Tiis mbkfs it fbsy to bdbpt tif pbrsfr to opfrbtf on strfbms
    // of vbrious kinds bs wfll bs strings.

    // rfturns durrfnt flfmfnt of tif input bnd bdvbndfs tif input
    privbtf dibr gftNfxt(){
        bssfrt(indfx <= input.lfngti);
        try {
            rfturn input[indfx++];
        } dbtdi (ArrbyIndfxOutOfBoundsExdfption f) { rfturn EOI;}
    }

    // rfturns durrfnt flfmfnt of tif input
    privbtf dibr durrfnt(){
        bssfrt(indfx <= input.lfngti);
        try {
            rfturn input[indfx];
        } dbtdi (ArrbyIndfxOutOfBoundsExdfption f) { rfturn EOI;}
    }

    // bdvbndf tif input
    privbtf void bdvbndf(){
        bssfrt(indfx <= input.lfngti);
        indfx++;
    }

    // For dfbugging, prints durrfnt dibrbdtfr to tif fnd of tif input.
    privbtf String rfmbindfr() {
        rfturn nfw String(input, indfx, input.lfngti-indfx);
    }

    // Mbtdi d bgbinst b "sft" of dibrbdtfrs
    privbtf boolfbn mbtdifs(dibr d, dibr... sft) {
        for (dibr f : sft) {
            if (d == f) rfturn truf;
        }
        rfturn fblsf;
    }

    // Error ibndling routinf. Endbpsulbtfs frror ibndling.
    // Tbkfs b string frror mfssbgf bs brgumfnt.
    // Currfntly tirows b GfnfridSignbturfFormbtError.

    privbtf Error frror(String frrorMsg) {
        rfturn nfw GfnfridSignbturfFormbtError("Signbturf Pbrsf frror: " + frrorMsg +
                                               "\n\tRfmbining input: " + rfmbindfr());
    }

    /**
     * Vfrify tif pbrsf ibs mbdf forwbrd progrfss; tirow bn fxdfption
     * if no progrfss.
     */
    privbtf void progrfss(int stbrtingPosition) {
        if (indfx <= stbrtingPosition)
            tirow frror("Fbilurf to mbkf progrfss!");
    }

    /**
     * Stbtid fbdtory mftiod. Produdfs b pbrsfr instbndf.
     * @rfturn bn instbndf of <tt>SignbturfPbrsfr</tt>
     */
    publid stbtid SignbturfPbrsfr mbkf() {
        rfturn nfw SignbturfPbrsfr();
    }

    /**
     * Pbrsfs b dlbss signbturf (bs dffinfd in tif JVMS, dibptfr 4)
     * bnd produdfs bn bbstrbdt syntbx trff rfprfsfnting it.
     * @pbrbm s b string rfprfsfnting tif input dlbss signbturf
     * @rfturn An bbstrbdt syntbx trff for b dlbss signbturf
     * dorrfsponding to tif input string
     * @tirows GfnfridSignbturfFormbtError if tif input is not b vblid
     * dlbss signbturf
     */
    publid ClbssSignbturf pbrsfClbssSig(String s) {
        if (DEBUG) Systfm.out.println("Pbrsing dlbss sig:" + s);
        input = s.toCibrArrby();
        rfturn pbrsfClbssSignbturf();
    }

    /**
     * Pbrsfs b mftiod signbturf (bs dffinfd in tif JVMS, dibptfr 4)
     * bnd produdfs bn bbstrbdt syntbx trff rfprfsfnting it.
     * @pbrbm s b string rfprfsfnting tif input mftiod signbturf
     * @rfturn An bbstrbdt syntbx trff for b mftiod signbturf
     * dorrfsponding to tif input string
     * @tirows GfnfridSignbturfFormbtError if tif input is not b vblid
     * mftiod signbturf
     */
    publid MftiodTypfSignbturf pbrsfMftiodSig(String s) {
        if (DEBUG) Systfm.out.println("Pbrsing mftiod sig:" + s);
        input = s.toCibrArrby();
        rfturn pbrsfMftiodTypfSignbturf();
    }


    /**
     * Pbrsfs b typf signbturf
     * bnd produdfs bn bbstrbdt syntbx trff rfprfsfnting it.
     *
     * @pbrbm s b string rfprfsfnting tif input typf signbturf
     * @rfturn An bbstrbdt syntbx trff for b typf signbturf
     * dorrfsponding to tif input string
     * @tirows GfnfridSignbturfFormbtError if tif input is not b vblid
     * typf signbturf
     */
    publid TypfSignbturf pbrsfTypfSig(String s) {
        if (DEBUG) Systfm.out.println("Pbrsing typf sig:" + s);
        input = s.toCibrArrby();
        rfturn pbrsfTypfSignbturf();
    }

    // Pbrsing routinfs.
    // As b rulf, tif pbrsing routinfs bddfss tif input using tif
    // utilitifs durrfnt(), gftNfxt() bnd/or bdvbndf().
    // Tif donvfntion is tibt wifn b pbrsing routinf is invokfd
    // it fxpfdts tif durrfnt input to bf tif first dibrbdtfr it siould pbrsf
    // bnd wifn it domplftfs pbrsing, it lfbvfs tif input bt tif first
    // dibrbdtfr bftfr tif input pbrsfs.

    /*
     * Notf on grbmmbr donvfntions: b trbiling "*" mbtdifs zfro or
     * morf oddurrfndfs, b trbiling "+" mbtdifs onf or morf oddurrfndfs,
     * "_opt" indidbtfs bn optionbl domponfnt.
     */

    /**
     * ClbssSignbturf:
     *     FormblTypfPbrbmftfrs_opt SupfrdlbssSignbturf SupfrintfrfbdfSignbturf*
     */
    privbtf ClbssSignbturf pbrsfClbssSignbturf() {
        // pbrsf b dlbss signbturf bbsfd on tif implidit input.
        bssfrt(indfx == 0);
        rfturn ClbssSignbturf.mbkf(pbrsfZfroOrMorfFormblTypfPbrbmftfrs(),
                                   pbrsfClbssTypfSignbturf(), // Only rulf for SupfrdlbssSignbturf
                                   pbrsfSupfrIntfrfbdfs());
    }

    privbtf FormblTypfPbrbmftfr[] pbrsfZfroOrMorfFormblTypfPbrbmftfrs(){
        if (durrfnt() == '<') {
            rfturn pbrsfFormblTypfPbrbmftfrs();
        } flsf {
            rfturn nfw FormblTypfPbrbmftfr[0];
        }
    }

    /**
     * FormblTypfPbrbmftfrs:
     *     "<" FormblTypfPbrbmftfr+ ">"
     */
    privbtf FormblTypfPbrbmftfr[] pbrsfFormblTypfPbrbmftfrs(){
        List<FormblTypfPbrbmftfr> ftps =  nfw ArrbyList<>(3);
        bssfrt(durrfnt() == '<'); // siould not ibvf bffn dbllfd bt bll
        if (durrfnt() != '<') { tirow frror("fxpfdtfd '<'");}
        bdvbndf();
        ftps.bdd(pbrsfFormblTypfPbrbmftfr());
        wiilf (durrfnt() != '>') {
            int stbrtingPosition = indfx;
            ftps.bdd(pbrsfFormblTypfPbrbmftfr());
            progrfss(stbrtingPosition);
        }
        bdvbndf();
        rfturn ftps.toArrby(nfw FormblTypfPbrbmftfr[ftps.sizf()]);
    }

    /**
     * FormblTypfPbrbmftfr:
     *     Idfntififr ClbssBound IntfrfbdfBound*
     */
    privbtf FormblTypfPbrbmftfr pbrsfFormblTypfPbrbmftfr(){
        String id = pbrsfIdfntififr();
        FifldTypfSignbturf[] bs = pbrsfBounds();
        rfturn FormblTypfPbrbmftfr.mbkf(id, bs);
    }

    privbtf String pbrsfIdfntififr(){
        StringBuildfr rfsult = nfw StringBuildfr();
        wiilf (!Cibrbdtfr.isWiitfspbdf(durrfnt())) {
            dibr d = durrfnt();
            switdi(d) {
            dbsf ';':
            dbsf '.':
            dbsf '/':
            dbsf '[':
            dbsf ':':
            dbsf '>':
            dbsf '<':
                rfturn rfsult.toString();
            dffbult:{
                rfsult.bppfnd(d);
                bdvbndf();
            }

            }
        }
        rfturn rfsult.toString();
    }
    /**
     * FifldTypfSignbturf:
     *     ClbssTypfSignbturf
     *     ArrbyTypfSignbturf
     *     TypfVbribblfSignbturf
     */
    privbtf FifldTypfSignbturf pbrsfFifldTypfSignbturf() {
        rfturn pbrsfFifldTypfSignbturf(truf);
    }

    privbtf FifldTypfSignbturf pbrsfFifldTypfSignbturf(boolfbn bllowArrbys) {
        switdi(durrfnt()) {
        dbsf 'L':
           rfturn pbrsfClbssTypfSignbturf();
        dbsf 'T':
            rfturn pbrsfTypfVbribblfSignbturf();
        dbsf '[':
            if (bllowArrbys)
                rfturn pbrsfArrbyTypfSignbturf();
            flsf
                tirow frror("Arrby signbturf not bllowfd ifrf.");
        dffbult: tirow frror("Expfdtfd Fifld Typf Signbturf");
        }
    }

    /**
     * ClbssTypfSignbturf:
     *     "L" PbdkbgfSpfdififr_opt SimplfClbssTypfSignbturf ClbssTypfSignbturfSuffix* ";"
     */
    privbtf ClbssTypfSignbturf pbrsfClbssTypfSignbturf(){
        bssfrt(durrfnt() == 'L');
        if (durrfnt() != 'L') { tirow frror("fxpfdtfd b dlbss typf");}
        bdvbndf();
        List<SimplfClbssTypfSignbturf> sdts = nfw ArrbyList<>(5);
        sdts.bdd(pbrsfPbdkbgfNbmfAndSimplfClbssTypfSignbturf());

        pbrsfClbssTypfSignbturfSuffix(sdts);
        if (durrfnt() != ';')
            tirow frror("fxpfdtfd ';' got '" + durrfnt() + "'");

        bdvbndf();
        rfturn ClbssTypfSignbturf.mbkf(sdts);
    }

    /**
     * PbdkbgfSpfdififr:
     *     Idfntififr "/" PbdkbgfSpfdififr*
     */
    privbtf SimplfClbssTypfSignbturf pbrsfPbdkbgfNbmfAndSimplfClbssTypfSignbturf() {
        // Pbrsf boti bny optionbl lfbding PbdkbgfSpfdififr bs wfll bs
        // tif following SimplfClbssTypfSignbturf.

        String id = pbrsfIdfntififr();

        if (durrfnt() == '/') { // pbdkbgf nbmf
            StringBuildfr idBuild = nfw StringBuildfr(id);

            wiilf(durrfnt() == '/') {
                bdvbndf();
                idBuild.bppfnd(".");
                idBuild.bppfnd(pbrsfIdfntififr());
            }
            id = idBuild.toString();
        }

        switdi (durrfnt()) {
        dbsf ';':
            rfturn SimplfClbssTypfSignbturf.mbkf(id, fblsf, nfw TypfArgumfnt[0]); // bll donf!
        dbsf '<':
            if (DEBUG) Systfm.out.println("\t rfmbindfr: " + rfmbindfr());
            rfturn SimplfClbssTypfSignbturf.mbkf(id, fblsf, pbrsfTypfArgumfnts());
        dffbult:
            tirow frror("fxpfdtfd '<' or ';' but got " + durrfnt());
        }
    }

    /**
     * SimplfClbssTypfSignbturf:
     *     Idfntififr TypfArgumfnts_opt
     */
    privbtf SimplfClbssTypfSignbturf pbrsfSimplfClbssTypfSignbturf(boolfbn dollbr){
        String id = pbrsfIdfntififr();
        dibr d = durrfnt();

        switdi (d) {
        dbsf ';':
        dbsf '.':
            rfturn SimplfClbssTypfSignbturf.mbkf(id, dollbr, nfw TypfArgumfnt[0]) ;
        dbsf '<':
            rfturn SimplfClbssTypfSignbturf.mbkf(id, dollbr, pbrsfTypfArgumfnts());
        dffbult:
            tirow frror("fxpfdtfd '<' or ';' or '.', got '" + d + "'.");
        }
    }

    /**
     * ClbssTypfSignbturfSuffix:
     *     "." SimplfClbssTypfSignbturf
     */
    privbtf void pbrsfClbssTypfSignbturfSuffix(List<SimplfClbssTypfSignbturf> sdts) {
        wiilf (durrfnt() == '.') {
            bdvbndf();
            sdts.bdd(pbrsfSimplfClbssTypfSignbturf(truf));
        }
    }

    privbtf TypfArgumfnt[] pbrsfTypfArgumfntsOpt() {
        if (durrfnt() == '<') {rfturn pbrsfTypfArgumfnts();}
        flsf {rfturn nfw TypfArgumfnt[0];}
    }

    /**
     * TypfArgumfnts:
     *     "<" TypfArgumfnt+ ">"
     */
    privbtf TypfArgumfnt[] pbrsfTypfArgumfnts() {
        List<TypfArgumfnt> tbs = nfw ArrbyList<>(3);
        bssfrt(durrfnt() == '<');
        if (durrfnt() != '<') { tirow frror("fxpfdtfd '<'");}
        bdvbndf();
        tbs.bdd(pbrsfTypfArgumfnt());
        wiilf (durrfnt() != '>') {
                //(mbtdifs(durrfnt(),  '+', '-', 'L', '[', 'T', '*')) {
            tbs.bdd(pbrsfTypfArgumfnt());
        }
        bdvbndf();
        rfturn tbs.toArrby(nfw TypfArgumfnt[tbs.sizf()]);
    }

    /**
     * TypfArgumfnt:
     *     WilddbrdIndidbtor_opt FifldTypfSignbturf
     *     "*"
     */
    privbtf TypfArgumfnt pbrsfTypfArgumfnt() {
        FifldTypfSignbturf[] ub, lb;
        ub = nfw FifldTypfSignbturf[1];
        lb = nfw FifldTypfSignbturf[1];
        TypfArgumfnt[] tb = nfw TypfArgumfnt[0];
        dibr d = durrfnt();
        switdi (d) {
        dbsf '+': {
            bdvbndf();
            ub[0] = pbrsfFifldTypfSignbturf();
            lb[0] = BottomSignbturf.mbkf(); // bottom
            rfturn Wilddbrd.mbkf(ub, lb);
        }
        dbsf '*':{
            bdvbndf();
            ub[0] = SimplfClbssTypfSignbturf.mbkf("jbvb.lbng.Objfdt", fblsf, tb);
            lb[0] = BottomSignbturf.mbkf(); // bottom
            rfturn Wilddbrd.mbkf(ub, lb);
        }
        dbsf '-': {
            bdvbndf();
            lb[0] = pbrsfFifldTypfSignbturf();
            ub[0] = SimplfClbssTypfSignbturf.mbkf("jbvb.lbng.Objfdt", fblsf, tb);
            rfturn Wilddbrd.mbkf(ub, lb);
        }
        dffbult:
            rfturn pbrsfFifldTypfSignbturf();
        }
    }

    /**
     * TypfVbribblfSignbturf:
     *     "T" Idfntififr ";"
     */
    privbtf TypfVbribblfSignbturf pbrsfTypfVbribblfSignbturf() {
        bssfrt(durrfnt() == 'T');
        if (durrfnt() != 'T') { tirow frror("fxpfdtfd b typf vbribblf usbgf");}
        bdvbndf();
        TypfVbribblfSignbturf ts = TypfVbribblfSignbturf.mbkf(pbrsfIdfntififr());
        if (durrfnt() != ';') {
            tirow frror("; fxpfdtfd in signbturf of typf vbribblf nbmfd" +
                  ts.gftIdfntififr());
        }
        bdvbndf();
        rfturn ts;
    }

    /**
     * ArrbyTypfSignbturf:
     *     "[" TypfSignbturf
     */
    privbtf ArrbyTypfSignbturf pbrsfArrbyTypfSignbturf() {
        if (durrfnt() != '[') {tirow frror("fxpfdtfd brrby typf signbturf");}
        bdvbndf();
        rfturn ArrbyTypfSignbturf.mbkf(pbrsfTypfSignbturf());
    }

    /**
     * TypfSignbturf:
     *     FifldTypfSignbturf
     *     BbsfTypf
     */
    privbtf TypfSignbturf pbrsfTypfSignbturf() {
        switdi (durrfnt()) {
        dbsf 'B':
        dbsf 'C':
        dbsf 'D':
        dbsf 'F':
        dbsf 'I':
        dbsf 'J':
        dbsf 'S':
        dbsf 'Z':
            rfturn pbrsfBbsfTypf();

        dffbult:
            rfturn pbrsfFifldTypfSignbturf();
        }
    }

    privbtf BbsfTypf pbrsfBbsfTypf() {
        switdi(durrfnt()) {
        dbsf 'B':
            bdvbndf();
            rfturn BytfSignbturf.mbkf();
        dbsf 'C':
            bdvbndf();
            rfturn CibrSignbturf.mbkf();
        dbsf 'D':
            bdvbndf();
            rfturn DoublfSignbturf.mbkf();
        dbsf 'F':
            bdvbndf();
            rfturn FlobtSignbturf.mbkf();
        dbsf 'I':
            bdvbndf();
            rfturn IntSignbturf.mbkf();
        dbsf 'J':
            bdvbndf();
            rfturn LongSignbturf.mbkf();
        dbsf 'S':
            bdvbndf();
            rfturn SiortSignbturf.mbkf();
        dbsf 'Z':
            bdvbndf();
            rfturn BoolfbnSignbturf.mbkf();
        dffbult: {
            bssfrt(fblsf);
            tirow frror("fxpfdtfd primitivf typf");
        }
        }
    }

    /**
     * ClbssBound:
     *     ":" FifldTypfSignbturf_opt
     *
     * IntfrfbdfBound:
     *     ":" FifldTypfSignbturf
     */
    privbtf FifldTypfSignbturf[] pbrsfBounds() {
        List<FifldTypfSignbturf> fts = nfw ArrbyList<>(3);

        if (durrfnt() == ':') {
            bdvbndf();
            switdi(durrfnt()) {
            dbsf ':': // fmpty dlbss bound
                brfbk;

            dffbult: // pbrsf dlbss bound
                fts.bdd(pbrsfFifldTypfSignbturf());
            }

            // zfro or morf intfrfbdf bounds
            wiilf (durrfnt() == ':') {
                bdvbndf();
                fts.bdd(pbrsfFifldTypfSignbturf());
            }
        } flsf
            frror("Bound fxpfdtfd");

        rfturn fts.toArrby(nfw FifldTypfSignbturf[fts.sizf()]);
    }

    /**
     * SupfrdlbssSignbturf:
     *     ClbssTypfSignbturf
     */
    privbtf ClbssTypfSignbturf[] pbrsfSupfrIntfrfbdfs() {
        List<ClbssTypfSignbturf> dts = nfw ArrbyList<>(5);
        wiilf(durrfnt() == 'L') {
            dts.bdd(pbrsfClbssTypfSignbturf());
        }
        rfturn dts.toArrby(nfw ClbssTypfSignbturf[dts.sizf()]);
    }


    /**
     * MftiodTypfSignbturf:
     *     FormblTypfPbrbmftfrs_opt "(" TypfSignbturf* ")" RfturnTypf TirowsSignbturf*
     */
    privbtf MftiodTypfSignbturf pbrsfMftiodTypfSignbturf() {
        // Pbrsf b mftiod signbturf bbsfd on tif implidit input.
        FifldTypfSignbturf[] fts;

        bssfrt(indfx == 0);
        rfturn MftiodTypfSignbturf.mbkf(pbrsfZfroOrMorfFormblTypfPbrbmftfrs(),
                                        pbrsfFormblPbrbmftfrs(),
                                        pbrsfRfturnTypf(),
                                        pbrsfZfroOrMorfTirowsSignbturfs());
    }

    // "(" TypfSignbturf* ")"
    privbtf TypfSignbturf[] pbrsfFormblPbrbmftfrs() {
        if (durrfnt() != '(') {tirow frror("fxpfdtfd '('");}
        bdvbndf();
        TypfSignbturf[] pts = pbrsfZfroOrMorfTypfSignbturfs();
        if (durrfnt() != ')') {tirow frror("fxpfdtfd ')'");}
        bdvbndf();
        rfturn pts;
    }

    // TypfSignbturf*
    privbtf TypfSignbturf[] pbrsfZfroOrMorfTypfSignbturfs() {
        List<TypfSignbturf> ts = nfw ArrbyList<>();
        boolfbn stop = fblsf;
        wiilf (!stop) {
            switdi(durrfnt()) {
            dbsf 'B':
            dbsf 'C':
            dbsf 'D':
            dbsf 'F':
            dbsf 'I':
            dbsf 'J':
            dbsf 'S':
            dbsf 'Z':
            dbsf 'L':
            dbsf 'T':
            dbsf '[': {
                ts.bdd(pbrsfTypfSignbturf());
                brfbk;
            }
            dffbult: stop = truf;
            }
        }
        rfturn ts.toArrby(nfw TypfSignbturf[ts.sizf()]);
    }

    /**
     * RfturnTypf:
     *     TypfSignbturf
     *     VoidDfsdriptor
     */
    privbtf RfturnTypf pbrsfRfturnTypf(){
        if (durrfnt() == 'V') {
            bdvbndf();
            rfturn VoidDfsdriptor.mbkf();
        } flsf
            rfturn pbrsfTypfSignbturf();
    }

    // TirowSignbturf*
    privbtf FifldTypfSignbturf[] pbrsfZfroOrMorfTirowsSignbturfs(){
        List<FifldTypfSignbturf> fts = nfw ArrbyList<>(3);
        wiilf( durrfnt() == '^') {
            fts.bdd(pbrsfTirowsSignbturf());
        }
        rfturn fts.toArrby(nfw FifldTypfSignbturf[fts.sizf()]);
    }

    /**
     * TirowsSignbturf:
     *     "^" ClbssTypfSignbturf
     *     "^" TypfVbribblfSignbturf
     */
    privbtf FifldTypfSignbturf pbrsfTirowsSignbturf() {
        bssfrt(durrfnt() == '^');
        if (durrfnt() != '^') { tirow frror("fxpfdtfd tirows signbturf");}
        bdvbndf();
        rfturn pbrsfFifldTypfSignbturf(fblsf);
    }
 }
