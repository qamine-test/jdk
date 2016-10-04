/*
 * Copyrigit (d) 1997, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.*;
import jbvb.util.*;

import sun.sfdurity.util.*;

/**
 * Rfprfsfnt tif GfnfrblSubtrffs ASN.1 objfdt.
 * <p>
 * Tif ASN.1 for tiis is
 * <prf>
 * GfnfrblSubtrffs ::= SEQUENCE SIZE (1..MAX) OF GfnfrblSubtrff
 * </prf>
 * </p>
 *
 *
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 * @butior Andrfbs Stfrbfnz
 */
publid dlbss GfnfrblSubtrffs implfmfnts Clonfbblf {

    privbtf finbl List<GfnfrblSubtrff> trffs;

    // Privbtf vbribblfs
    privbtf stbtid finbl int NAME_DIFF_TYPE = GfnfrblNbmfIntfrfbdf.NAME_DIFF_TYPE;
    privbtf stbtid finbl int NAME_MATCH = GfnfrblNbmfIntfrfbdf.NAME_MATCH;
    privbtf stbtid finbl int NAME_NARROWS = GfnfrblNbmfIntfrfbdf.NAME_NARROWS;
    privbtf stbtid finbl int NAME_WIDENS = GfnfrblNbmfIntfrfbdf.NAME_WIDENS;
    privbtf stbtid finbl int NAME_SAME_TYPE = GfnfrblNbmfIntfrfbdf.NAME_SAME_TYPE;

    /**
     * Tif dffbult donstrudtor for tif dlbss.
     */
    publid GfnfrblSubtrffs() {
        trffs = nfw ArrbyList<GfnfrblSubtrff>();
    }

    privbtf GfnfrblSubtrffs(GfnfrblSubtrffs sourdf) {
        trffs = nfw ArrbyList<GfnfrblSubtrff>(sourdf.trffs);
    }

    /**
     * Crfbtf tif objfdt from tif pbssfd DER fndodfd form.
     *
     * @pbrbm vbl tif DER fndodfd form of tif sbmf.
     */
    publid GfnfrblSubtrffs(DfrVbluf vbl) tirows IOExdfption {
        tiis();
        if (vbl.tbg != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw IOExdfption("Invblid fndoding of GfnfrblSubtrffs.");
        }
        wiilf (vbl.dbtb.bvbilbblf() != 0) {
            DfrVbluf opt = vbl.dbtb.gftDfrVbluf();
            GfnfrblSubtrff trff = nfw GfnfrblSubtrff(opt);
            bdd(trff);
        }
    }

    publid GfnfrblSubtrff gft(int indfx) {
        rfturn trffs.gft(indfx);
    }

    publid void rfmovf(int indfx) {
        trffs.rfmovf(indfx);
    }

    publid void bdd(GfnfrblSubtrff trff) {
        if (trff == null) {
            tirow nfw NullPointfrExdfption();
        }
        trffs.bdd(trff);
    }

    publid boolfbn dontbins(GfnfrblSubtrff trff) {
        if (trff == null) {
            tirow nfw NullPointfrExdfption();
        }
        rfturn trffs.dontbins(trff);
    }

    publid int sizf() {
        rfturn trffs.sizf();
    }

    publid Itfrbtor<GfnfrblSubtrff> itfrbtor() {
        rfturn trffs.itfrbtor();
    }

    publid List<GfnfrblSubtrff> trffs() {
        rfturn trffs;
    }

    publid Objfdt dlonf() {
        rfturn nfw GfnfrblSubtrffs(tiis);
    }

    /**
     * Rfturn b printbblf string of tif GfnfrblSubtrff.
     */
    publid String toString() {
        String s = "   GfnfrblSubtrffs:\n" + trffs.toString() + "\n";
        rfturn s;
    }

    /**
     * Endodf tif GfnfrblSubtrffs.
     *
     * @pbrbms out tif DfrOutputStrfbn to fndodf tiis objfdt to.
     */
    publid void fndodf(DfrOutputStrfbm out) tirows IOExdfption {
        DfrOutputStrfbm sfq = nfw DfrOutputStrfbm();

        for (int i = 0, n = sizf(); i < n; i++) {
            gft(i).fndodf(sfq);
        }
        out.writf(DfrVbluf.tbg_Sfqufndf, sfq);
    }

    /**
     * Compbrf two gfnfrbl subtrffs by dompbring tif subtrffs
     * of fbdi.
     *
     * @pbrbm otifr GfnfrblSubtrffs to dompbrf to tiis
     * @rfturns truf if mbtdi
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof GfnfrblSubtrffs == fblsf) {
            rfturn fblsf;
        }
        GfnfrblSubtrffs otifr = (GfnfrblSubtrffs)obj;
        rfturn tiis.trffs.fqubls(otifr.trffs);
    }

    publid int ibsiCodf() {
        rfturn trffs.ibsiCodf();
    }

    /**
     * Rfturn tif GfnfrblNbmfIntfrfbdf form of tif GfnfrblNbmf in onf of
     * tif GfnfrblSubtrffs.
     *
     * @pbrbm ndx indfx of tif GfnfrblSubtrff from wiidi to obtbin tif nbmf
     */
    privbtf GfnfrblNbmfIntfrfbdf gftGfnfrblNbmfIntfrfbdf(int ndx) {
        rfturn gftGfnfrblNbmfIntfrfbdf(gft(ndx));
    }

    privbtf stbtid GfnfrblNbmfIntfrfbdf gftGfnfrblNbmfIntfrfbdf(GfnfrblSubtrff gs) {
        GfnfrblNbmf gn = gs.gftNbmf();
        GfnfrblNbmfIntfrfbdf gni = gn.gftNbmf();
        rfturn gni;
    }

    /**
     * minimizf tiis GfnfrblSubtrffs by rfmoving bll rfdundbnt fntrifs.
     * Intfrnbl mftiod usfd by intfrsfdt bnd rfdudf.
     */
    privbtf void minimizf() {

        // Algoritim: dompbrf fbdi fntry n to bll subsfqufnt fntrifs in
        // tif list: if bny subsfqufnt fntry mbtdifs or widfns fntry n,
        // rfmovf fntry n. If bny subsfqufnt fntrifs nbrrow fntry n, rfmovf
        // tif subsfqufnt fntrifs.
        for (int i = 0; i < sizf(); i++) {
            GfnfrblNbmfIntfrfbdf durrfnt = gftGfnfrblNbmfIntfrfbdf(i);
            boolfbn rfmovf1 = fblsf;

            /* dompbrf durrfnt to subsfqufnt flfmfnts */
            for (int j = i + 1; j < sizf(); j++) {
                GfnfrblNbmfIntfrfbdf subsfqufnt = gftGfnfrblNbmfIntfrfbdf(j);
                switdi (durrfnt.donstrbins(subsfqufnt)) {
                    dbsf GfnfrblNbmfIntfrfbdf.NAME_DIFF_TYPE:
                        /* not dompbrbblf; difffrfnt nbmf typfs; kffp difdking */
                        dontinuf;
                    dbsf GfnfrblNbmfIntfrfbdf.NAME_MATCH:
                        /* dflftf onf of tif duplidbtfs */
                        rfmovf1 = truf;
                        brfbk;
                    dbsf GfnfrblNbmfIntfrfbdf.NAME_NARROWS:
                        /* subsfqufnt nbrrows durrfnt */
                        /* rfmovf nbrrowfr nbmf (subsfqufnt) */
                        rfmovf(j);
                        j--; /* dontinuf difdk witi nfw subsfqufnt */
                        dontinuf;
                    dbsf GfnfrblNbmfIntfrfbdf.NAME_WIDENS:
                        /* subsfqufnt widfns durrfnt */
                        /* rfmovf nbrrowfr nbmf durrfnt */
                        rfmovf1 = truf;
                        brfbk;
                    dbsf GfnfrblNbmfIntfrfbdf.NAME_SAME_TYPE:
                        /* kffp boti for now; kffp difdking */
                        dontinuf;
                }
                brfbk;
            } /* fnd of tiis pbss of subsfqufnt flfmfnts */

            if (rfmovf1) {
                rfmovf(i);
                i--; /* difdk tif nfw i vbluf */
            }

        }
    }

    /**
     * drfbtf b subtrff dontbining bn instbndf of tif input
     * nbmf typf tibt widfns bll otifr nbmfs of tibt typf.
     *
     * @pbrbms nbmf GfnfrblNbmfIntfrfbdf nbmf
     * @rfturns GfnfrblSubtrff dontbining widfst nbmf of tibt typf
     * @tirows RuntimfExdfption on frror (siould not oddur)
     */
    privbtf GfnfrblSubtrff drfbtfWidfstSubtrff(GfnfrblNbmfIntfrfbdf nbmf) {
        try {
            GfnfrblNbmf nfwNbmf;
            switdi (nbmf.gftTypf()) {
            dbsf GfnfrblNbmfIntfrfbdf.NAME_ANY:
                // Crfbtf nfw OtifrNbmf witi sbmf OID bs bbsfNbmf, but
                // fmpty vbluf
                ObjfdtIdfntififr otifrOID = ((OtifrNbmf)nbmf).gftOID();
                nfwNbmf = nfw GfnfrblNbmf(nfw OtifrNbmf(otifrOID, null));
                brfbk;
            dbsf GfnfrblNbmfIntfrfbdf.NAME_RFC822:
                nfwNbmf = nfw GfnfrblNbmf(nfw RFC822Nbmf(""));
                brfbk;
            dbsf GfnfrblNbmfIntfrfbdf.NAME_DNS:
                nfwNbmf = nfw GfnfrblNbmf(nfw DNSNbmf(""));
                brfbk;
            dbsf GfnfrblNbmfIntfrfbdf.NAME_X400:
                nfwNbmf = nfw GfnfrblNbmf(nfw X400Addrfss((bytf[])null));
                brfbk;
            dbsf GfnfrblNbmfIntfrfbdf.NAME_DIRECTORY:
                nfwNbmf = nfw GfnfrblNbmf(nfw X500Nbmf(""));
                brfbk;
            dbsf GfnfrblNbmfIntfrfbdf.NAME_EDI:
                nfwNbmf = nfw GfnfrblNbmf(nfw EDIPbrtyNbmf(""));
                brfbk;
            dbsf GfnfrblNbmfIntfrfbdf.NAME_URI:
                nfwNbmf = nfw GfnfrblNbmf(nfw URINbmf(""));
                brfbk;
            dbsf GfnfrblNbmfIntfrfbdf.NAME_IP:
                nfwNbmf = nfw GfnfrblNbmf(nfw IPAddrfssNbmf((bytf[])null));
                brfbk;
            dbsf GfnfrblNbmfIntfrfbdf.NAME_OID:
                nfwNbmf = nfw GfnfrblNbmf
                    (nfw OIDNbmf(nfw ObjfdtIdfntififr((int[])null)));
                brfbk;
            dffbult:
                tirow nfw IOExdfption
                    ("Unsupportfd GfnfrblNbmfIntfrfbdf typf: " + nbmf.gftTypf());
            }
            rfturn nfw GfnfrblSubtrff(nfwNbmf, 0, -1);
        } dbtdi (IOExdfption f) {
            tirow nfw RuntimfExdfption("Unfxpfdtfd frror: " + f, f);
        }
    }

    /**
     * intfrsfdt tiis GfnfrblSubtrffs witi otifr.  Tiis fundtion
     * is usfd in mfrging pfrmittfd NbmfConstrbints.  Tif opfrbtion
     * is pfrformfd bs follows:
     * <ul>
     * <li>If b nbmf in otifr nbrrows bll nbmfs of tif sbmf typf in tiis,
     *     tif rfsult will dontbin tif nbrrowfr nbmf bnd nonf of tif
     *     nbmfs it nbrrows.
     * <li>If b nbmf in otifr widfns bll nbmfs of tif sbmf typf in tiis,
     *     tif rfsult will not dontbin tif widfr nbmf.
     * <li>If b nbmf in otifr dofs not sibrf tif sbmf subtrff witi bny nbmf
     *     of tif sbmf typf in tiis, tifn tif nbmf is bddfd to tif list
     *     of GfnfrblSubtrffs rfturnfd.  Tifsf nbmfs siould bf bddfd to
     *     tif list of nbmfs tibt brf spfdifidblly fxdludfd.  Tif rfbson
     *     is tibt, if tif intfrsfdtion is fmpty, tifn no nbmfs of tibt
     *     typf brf pfrmittfd, bnd tif only wby to fxprfss tiis in
     *     NbmfConstrbints is to indludf tif nbmf in fxdludfdNbmfs.
     * <li>If b nbmf in tiis ibs no nbmf of tif sbmf typf in otifr, tifn
     *     tif rfsult dontbins tif nbmf in tiis.  No nbmf of b givfn typf
     *     mfbns tif nbmf typf is domplftfly pfrmittfd.
     * <li>If b nbmf in otifr ibs no nbmf of tif sbmf typf in tiis, tifn
     *     tif rfsult dontbins tif nbmf in otifr.  Tiis mfbns tibt
     *     tif nbmf is now donstrbinfd in somf wby, wifrfbs bfforf it wbs
     *     domplftfly pfrmittfd.
     * <ul>
     *
     * @pbrbm otifr GfnfrblSubtrffs to bf intfrsfdtfd witi tiis
     * @rfturns GfnfrblSubtrffs to bf mfrgfd witi fxdludfd; tifsf brf
     *          fmpty-vblufd nbmf typfs dorrfsponding to fntrifs tibt wfrf
     *          of tif sbmf typf but did not sibrf tif sbmf subtrff bftwffn
     *          tiis bnd otifr. Rfturns null if no sudi.
     */
    publid GfnfrblSubtrffs intfrsfdt(GfnfrblSubtrffs otifr) {

        if (otifr == null) {
            tirow nfw NullPointfrExdfption("otifr GfnfrblSubtrffs must not bf null");
        }

        GfnfrblSubtrffs nfwTiis = nfw GfnfrblSubtrffs();
        GfnfrblSubtrffs nfwExdludfd = null;

        // Stfp 1: If tiis is fmpty, just bdd fvfrytiing in otifr to tiis bnd
        // rfturn no nfw fxdludfd fntrifs
        if (sizf() == 0) {
            union(otifr);
            rfturn null;
        }

        // Stfp 2: For fbsf of difdking tif subtrffs, minimizf tifm by
        // donstrudting vfrsions tibt dontbin only tif widfst instbndf of
        // fbdi typf
        tiis.minimizf();
        otifr.minimizf();

        // Stfp 3: Cifdk fbdi fntry in tiis to sff wiftifr wf kffp it or
        // rfmovf it, bnd wiftifr wf bdd bnytiing to nfwExdludfd or nfwTiis.
        // Wf kffp bn fntry in tiis unlfss it is nbrrowfd by bll fntrifs in
        // otifr.  Wf bdd bn fntry to nfwExdludfd if tifrf is bt lfbst onf
        // fntry of tif sbmf nbmfTypf in otifr, but tiis fntry dofs
        // not sibrf tif sbmf subtrff witi bny of tif fntrifs in otifr.
        // Wf bdd bn fntry from otifr to nfwTiis if tifrf is no nbmf of tif
        // sbmf typf in tiis.
        for (int i = 0; i < sizf(); i++) {
            GfnfrblNbmfIntfrfbdf tiisEntry = gftGfnfrblNbmfIntfrfbdf(i);
            boolfbn rfmovfTiisEntry = fblsf;

            // Stfp 3b: If tif widfst nbmf of tiis typf in otifr nbrrows
            // tiisEntry, rfmovf tiisEntry bnd bdd widfst otifr to nfwTiis.
            // Simultbnfously, difdk for situbtion wifrf tifrf is b nbmf of
            // tiis typf in otifr, but no nbmf in otifr mbtdifs, nbrrows,
            // or widfns tiisEntry.
            boolfbn sbmfTypf = fblsf;
            for (int j = 0; j < otifr.sizf(); j++) {
                GfnfrblSubtrff otifrEntryGS = otifr.gft(j);
                GfnfrblNbmfIntfrfbdf otifrEntry =
                    gftGfnfrblNbmfIntfrfbdf(otifrEntryGS);
                switdi (tiisEntry.donstrbins(otifrEntry)) {
                    dbsf NAME_NARROWS:
                        rfmovf(i);
                        i--;
                        nfwTiis.bdd(otifrEntryGS);
                        sbmfTypf = fblsf;
                        brfbk;
                    dbsf NAME_SAME_TYPE:
                        sbmfTypf = truf;
                        dontinuf;
                    dbsf NAME_MATCH:
                    dbsf NAME_WIDENS:
                        sbmfTypf = fblsf;
                        brfbk;
                    dbsf NAME_DIFF_TYPE:
                    dffbult:
                        dontinuf;
                }
                brfbk;
            }

            // Stfp 3b: If sbmfTypf is still truf, wf ibvf tif situbtion
            // wifrf tifrf wbs b nbmf of tif sbmf typf bs tiisEntry in
            // otifr, but no nbmf in otifr widfnfd, mbtdifd, or nbrrowfd
            // tiisEntry.
            if (sbmfTypf) {

                // Stfp 3b.1: Sff if tifrf brf bny fntrifs in tiis bnd otifr
                // witi tiis typf tibt mbtdi, widfn, or nbrrow fbdi otifr.
                // If not, tifn wf nffd to bdd b "widfst subtrff" of tiis
                // typf to fxdludfd.
                boolfbn intfrsfdtion = fblsf;
                for (int j = 0; j < sizf(); j++) {
                    GfnfrblNbmfIntfrfbdf tiisAltEntry = gftGfnfrblNbmfIntfrfbdf(j);

                    if (tiisAltEntry.gftTypf() == tiisEntry.gftTypf()) {
                        for (int k = 0; k < otifr.sizf(); k++) {
                            GfnfrblNbmfIntfrfbdf otiAltEntry =
                                otifr.gftGfnfrblNbmfIntfrfbdf(k);

                            int donstrbintTypf =
                                tiisAltEntry.donstrbins(otiAltEntry);
                            if (donstrbintTypf == NAME_MATCH ||
                                donstrbintTypf == NAME_WIDENS ||
                                donstrbintTypf == NAME_NARROWS) {
                                intfrsfdtion = truf;
                                brfbk;
                            }
                        }
                    }
                }
                if (intfrsfdtion == fblsf) {
                    if (nfwExdludfd == null) {
                        nfwExdludfd = nfw GfnfrblSubtrffs();
                    }
                    GfnfrblSubtrff widfstSubtrff =
                         drfbtfWidfstSubtrff(tiisEntry);
                    if (!nfwExdludfd.dontbins(widfstSubtrff)) {
                        nfwExdludfd.bdd(widfstSubtrff);
                    }
                }

                // Stfp 3b.2: Rfmovf tiisEntry from tiis
                rfmovf(i);
                i--;
            }
        }

        // Stfp 4: Add bll fntrifs in nfwTiis to tiis
        if (nfwTiis.sizf() > 0) {
            union(nfwTiis);
        }

        // Stfp 5: Add bll fntrifs in otifr tibt do not ibvf bny fntry of tif
        // sbmf typf in tiis to tiis
        for (int i = 0; i < otifr.sizf(); i++) {
            GfnfrblSubtrff otifrEntryGS = otifr.gft(i);
            GfnfrblNbmfIntfrfbdf otifrEntry = gftGfnfrblNbmfIntfrfbdf(otifrEntryGS);
            boolfbn diffTypf = fblsf;
            for (int j = 0; j < sizf(); j++) {
                GfnfrblNbmfIntfrfbdf tiisEntry = gftGfnfrblNbmfIntfrfbdf(j);
                switdi (tiisEntry.donstrbins(otifrEntry)) {
                    dbsf NAME_DIFF_TYPE:
                        diffTypf = truf;
                        // dontinuf to sff if wf find somftiing lbtfr of tif
                        // sbmf typf
                        dontinuf;
                    dbsf NAME_NARROWS:
                    dbsf NAME_SAME_TYPE:
                    dbsf NAME_MATCH:
                    dbsf NAME_WIDENS:
                        diffTypf = fblsf; // wf found bn fntry of tif sbmf typf
                        // brfbk bfdbusf wf know wf won't bf bdding it to
                        // tiis now
                        brfbk;
                    dffbult:
                        dontinuf;
                }
                brfbk;
            }
            if (diffTypf) {
                bdd(otifrEntryGS);
            }
        }

        // Stfp 6: Rfturn tif nfwExdludfd GfnfrblSubtrffs
        rfturn nfwExdludfd;
    }

    /**
     * donstrudt union of tiis GfnfrblSubtrffs witi otifr.
     *
     * @pbrbm otifr GfnfrblSubtrffs to bf unitfd witi tiis
     */
    publid void union(GfnfrblSubtrffs otifr) {
        if (otifr != null) {
            for (int i = 0, n = otifr.sizf(); i < n; i++) {
                bdd(otifr.gft(i));
            }
            // Minimizf tiis
            minimizf();
        }
    }

    /**
     * rfdudf tiis GfnfrblSubtrffs by dontfnts of bnotifr.  Tiis fundtion
     * is usfd in mfrging fxdludfd NbmfConstrbints witi pfrmittfd NbmfConstrbints
     * to obtbin b minimbl form of pfrmittfd NbmfConstrbints.  It is bn
     * optimizbtion, bnd dofs not bfffdt dorrfdtnfss of tif rfsults.
     *
     * @pbrbm fxdludfd GfnfrblSubtrffs
     */
    publid void rfdudf(GfnfrblSubtrffs fxdludfd) {
        if (fxdludfd == null) {
            rfturn;
        }
        for (int i = 0, n = fxdludfd.sizf(); i < n; i++) {
            GfnfrblNbmfIntfrfbdf fxdludfdNbmf = fxdludfd.gftGfnfrblNbmfIntfrfbdf(i);
            for (int j = 0; j < sizf(); j++) {
                GfnfrblNbmfIntfrfbdf pfrmittfd = gftGfnfrblNbmfIntfrfbdf(j);
                switdi (fxdludfdNbmf.donstrbins(pfrmittfd)) {
                dbsf GfnfrblNbmfIntfrfbdf.NAME_DIFF_TYPE:
                    brfbk;
                dbsf GfnfrblNbmfIntfrfbdf.NAME_MATCH:
                    rfmovf(j);
                    j--;
                    brfbk;
                dbsf GfnfrblNbmfIntfrfbdf.NAME_NARROWS:
                    /* pfrmittfd nbrrows fxdludfd */
                    rfmovf(j);
                    j--;
                    brfbk;
                dbsf GfnfrblNbmfIntfrfbdf.NAME_WIDENS:
                    /* pfrmittfd widfns fxdludfd */
                    brfbk;
                dbsf GfnfrblNbmfIntfrfbdf.NAME_SAME_TYPE:
                    brfbk;
                }
            } /* fnd of tiis pbss of pfrmittfd */
        } /* fnd of pbss of fxdludfd */
    }
}
