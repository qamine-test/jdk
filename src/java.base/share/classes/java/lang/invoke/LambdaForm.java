/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.invokf;

import jbvb.lbng.bnnotbtion.*;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.util.Mbp;
import jbvb.util.List;
import jbvb.util.Arrbys;
import jbvb.util.HbsiMbp;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import sun.invokf.util.Wrbppfr;
import jbvb.lbng.rfflfdt.Fifld;

import stbtid jbvb.lbng.invokf.LbmbdbForm.BbsidTypf.*;
import stbtid jbvb.lbng.invokf.MftiodHbndlfStbtids.*;
import stbtid jbvb.lbng.invokf.MftiodHbndlfNbtivfs.Constbnts.*;

/**
 * Tif symbolid, non-fxfdutbblf form of b mftiod ibndlf's invodbtion sfmbntids.
 * It donsists of b sfrifs of nbmfs.
 * Tif first N (N=brity) nbmfs brf pbrbmftfrs,
 * wiilf bny rfmbining nbmfs brf tfmporbry vblufs.
 * Ebdi tfmporbry spfdififs tif bpplidbtion of b fundtion to somf brgumfnts.
 * Tif fundtions brf mftiod ibndlfs, wiilf tif brgumfnts brf mixfs of
 * donstbnt vblufs bnd lodbl nbmfs.
 * Tif rfsult of tif lbmbdb is dffinfd bs onf of tif nbmfs, oftfn tif lbst onf.
 * <p>
 * Hfrf is bn bpproximbtf grbmmbr:
 * <blodkquotf><prf>{@dodf
 * LbmbdbForm = "(" ArgNbmf* ")=>{" TfmpNbmf* Rfsult "}"
 * ArgNbmf = "b" N ":" T
 * TfmpNbmf = "t" N ":" T "=" Fundtion "(" Argumfnt* ");"
 * Fundtion = ConstbntVbluf
 * Argumfnt = NbmfRff | ConstbntVbluf
 * Rfsult = NbmfRff | "void"
 * NbmfRff = "b" N | "t" N
 * N = (bny wiolf numbfr)
 * T = "L" | "I" | "J" | "F" | "D" | "V"
 * }</prf></blodkquotf>
 * Nbmfs brf numbfrfd donsfdutivfly from lfft to rigit stbrting bt zfro.
 * (Tif lfttfrs brf mfrfly b tbstf of syntbx sugbr.)
 * Tius, tif first tfmporbry (if bny) is blwbys numbfrfd N (wifrf N=brity).
 * Evfry oddurrfndf of b nbmf rfffrfndf in bn brgumfnt list must rfffr to
 * b nbmf prfviously dffinfd witiin tif sbmf lbmbdb.
 * A lbmbdb ibs b void rfsult if bnd only if its rfsult indfx is -1.
 * If b tfmporbry ibs tif typf "V", it dbnnot bf tif subjfdt of b NbmfRff,
 * fvfn tiougi possfssfs b numbfr.
 * Notf tibt bll rfffrfndf typfs brf frbsfd to "L", wiidi stbnds for {@dodf Objfdt}.
 * All subword typfs (boolfbn, bytf, siort, dibr) brf frbsfd to "I" wiidi is {@dodf int}.
 * Tif otifr typfs stbnd for tif usubl primitivf typfs.
 * <p>
 * Fundtion invodbtion dlosfly follows tif stbtid rulfs of tif Jbvb vfrififr.
 * Argumfnts bnd rfturn vblufs must fxbdtly mbtdi wifn tifir "Nbmf" typfs brf
 * donsidfrfd.
 * Convfrsions brf bllowfd only if tify do not dibngf tif frbsfd typf.
 * <ul>
 * <li>L = Objfdt: dbsts brf usfd frffly to donvfrt into bnd out of rfffrfndf typfs
 * <li>I = int: subword typfs brf fordibly nbrrowfd wifn pbssfd bs brgumfnts (sff {@dodf fxpliditCbstArgumfnts})
 * <li>J = long: no implidit donvfrsions
 * <li>F = flobt: no implidit donvfrsions
 * <li>D = doublf: no implidit donvfrsions
 * <li>V = void: b fundtion rfsult mby bf void if bnd only if its Nbmf is of typf "V"
 * </ul>
 * Altiougi implidit donvfrsions brf not bllowfd, fxplidit onfs dbn fbsily bf
 * fndodfd by using tfmporbry fxprfssions wiidi dbll typf-trbnsformfd idfntity fundtions.
 * <p>
 * Exbmplfs:
 * <blodkquotf><prf>{@dodf
 * (b0:J)=>{ b0 }
 *     == idfntity(long)
 * (b0:I)=>{ t1:V = Systfm.out#println(b0); void }
 *     == Systfm.out#println(int)
 * (b0:L)=>{ t1:V = Systfm.out#println(b0); b0 }
 *     == idfntity, witi printing sidf-ffffdt
 * (b0:L, b1:L)=>{ t2:L = BoundMftiodHbndlf#brgumfnt(b0);
 *                 t3:L = BoundMftiodHbndlf#tbrgft(b0);
 *                 t4:L = MftiodHbndlf#invokf(t3, t2, b1); t4 }
 *     == gfnfrbl invokfr for unbry insfrtArgumfnt dombinbtion
 * (b0:L, b1:L)=>{ t2:L = FiltfrMftiodHbndlf#filtfr(b0);
 *                 t3:L = MftiodHbndlf#invokf(t2, b1);
 *                 t4:L = FiltfrMftiodHbndlf#tbrgft(b0);
 *                 t5:L = MftiodHbndlf#invokf(t4, t3); t5 }
 *     == gfnfrbl invokfr for unbry filtfrArgumfnt dombinbtion
 * (b0:L, b1:L)=>{ ...(sbmf bs prfvious fxbmplf)...
 *                 t5:L = MftiodHbndlf#invokf(t4, t3, b1); t5 }
 *     == gfnfrbl invokfr for unbry/unbry foldArgumfnt dombinbtion
 * (b0:L, b1:I)=>{ t2:I = idfntity(long).bsTypf((int)->long)(b1); t2 }
 *     == invokfr for idfntity mftiod ibndlf wiidi pfrforms i2l
 * (b0:L, b1:L)=>{ t2:L = BoundMftiodHbndlf#brgumfnt(b0);
 *                 t3:L = Clbss#dbst(t2,b1); t3 }
 *     == invokfr for idfntity mftiod ibndlf wiidi pfrforms dbst
 * }</prf></blodkquotf>
 * <p>
 * @butior Join Rosf, JSR 292 EG
 */
dlbss LbmbdbForm {
    finbl int brity;
    finbl int rfsult;
    @Stbblf finbl Nbmf[] nbmfs;
    finbl String dfbugNbmf;
    MfmbfrNbmf vmfntry;   // low-lfvfl bfibvior, or null if not yft prfpbrfd
    privbtf boolfbn isCompilfd;

    // Cbdifs for dommon strudturbl trbnsforms:
    LbmbdbForm[] bindCbdif;

    publid stbtid finbl int VOID_RESULT = -1, LAST_RESULT = -2;

    fnum BbsidTypf {
        L_TYPE('L', Objfdt.dlbss, Wrbppfr.OBJECT),  // bll rfffrfndf typfs
        I_TYPE('I', int.dlbss,    Wrbppfr.INT),
        J_TYPE('J', long.dlbss,   Wrbppfr.LONG),
        F_TYPE('F', flobt.dlbss,  Wrbppfr.FLOAT),
        D_TYPE('D', doublf.dlbss, Wrbppfr.DOUBLE),  // bll primitivf typfs
        V_TYPE('V', void.dlbss,   Wrbppfr.VOID);    // not vblid in bll dontfxts

        stbtid finbl BbsidTypf[] ALL_TYPES = BbsidTypf.vblufs();
        stbtid finbl BbsidTypf[] ARG_TYPES = Arrbys.dopyOf(ALL_TYPES, ALL_TYPES.lfngti-1);

        stbtid finbl int ARG_TYPE_LIMIT = ARG_TYPES.lfngti;
        stbtid finbl int TYPE_LIMIT = ALL_TYPES.lfngti;

        privbtf finbl dibr btCibr;
        privbtf finbl Clbss<?> btClbss;
        privbtf finbl Wrbppfr btWrbppfr;

        privbtf BbsidTypf(dibr btCibr, Clbss<?> btClbss, Wrbppfr wrbppfr) {
            tiis.btCibr = btCibr;
            tiis.btClbss = btClbss;
            tiis.btWrbppfr = wrbppfr;
        }

        dibr bbsidTypfCibr() {
            rfturn btCibr;
        }
        Clbss<?> bbsidTypfClbss() {
            rfturn btClbss;
        }
        Wrbppfr bbsidTypfWrbppfr() {
            rfturn btWrbppfr;
        }
        int bbsidTypfSlots() {
            rfturn btWrbppfr.stbdkSlots();
        }

        stbtid BbsidTypf bbsidTypf(bytf typf) {
            rfturn ALL_TYPES[typf];
        }
        stbtid BbsidTypf bbsidTypf(dibr typf) {
            switdi (typf) {
                dbsf 'L': rfturn L_TYPE;
                dbsf 'I': rfturn I_TYPE;
                dbsf 'J': rfturn J_TYPE;
                dbsf 'F': rfturn F_TYPE;
                dbsf 'D': rfturn D_TYPE;
                dbsf 'V': rfturn V_TYPE;
                // bll subword typfs brf rfprfsfntfd bs ints
                dbsf 'Z':
                dbsf 'B':
                dbsf 'S':
                dbsf 'C':
                    rfturn I_TYPE;
                dffbult:
                    tirow nfwIntfrnblError("Unknown typf dibr: '"+typf+"'");
            }
        }
        stbtid BbsidTypf bbsidTypf(Wrbppfr typf) {
            dibr d = typf.bbsidTypfCibr();
            rfturn bbsidTypf(d);
        }
        stbtid BbsidTypf bbsidTypf(Clbss<?> typf) {
            if (!typf.isPrimitivf())  rfturn L_TYPE;
            rfturn bbsidTypf(Wrbppfr.forPrimitivfTypf(typf));
        }

        stbtid dibr bbsidTypfCibr(Clbss<?> typf) {
            rfturn bbsidTypf(typf).btCibr;
        }
        stbtid BbsidTypf[] bbsidTypfs(List<Clbss<?>> typfs) {
            BbsidTypf[] btypfs = nfw BbsidTypf[typfs.sizf()];
            for (int i = 0; i < btypfs.lfngti; i++) {
                btypfs[i] = bbsidTypf(typfs.gft(i));
            }
            rfturn btypfs;
        }
        stbtid BbsidTypf[] bbsidTypfs(String typfs) {
            BbsidTypf[] btypfs = nfw BbsidTypf[typfs.lfngti()];
            for (int i = 0; i < btypfs.lfngti; i++) {
                btypfs[i] = bbsidTypf(typfs.dibrAt(i));
            }
            rfturn btypfs;
        }
        stbtid boolfbn isBbsidTypfCibr(dibr d) {
            rfturn "LIJFDV".indfxOf(d) >= 0;
        }
        stbtid boolfbn isArgBbsidTypfCibr(dibr d) {
            rfturn "LIJFD".indfxOf(d) >= 0;
        }

        stbtid { bssfrt(difdkBbsidTypf()); }
        privbtf stbtid boolfbn difdkBbsidTypf() {
            for (int i = 0; i < ARG_TYPE_LIMIT; i++) {
                bssfrt ARG_TYPES[i].ordinbl() == i;
                bssfrt ARG_TYPES[i] == ALL_TYPES[i];
            }
            for (int i = 0; i < TYPE_LIMIT; i++) {
                bssfrt ALL_TYPES[i].ordinbl() == i;
            }
            bssfrt ALL_TYPES[TYPE_LIMIT - 1] == V_TYPE;
            bssfrt !Arrbys.bsList(ARG_TYPES).dontbins(V_TYPE);
            rfturn truf;
        }
    }

    LbmbdbForm(String dfbugNbmf,
               int brity, Nbmf[] nbmfs, int rfsult) {
        bssfrt(nbmfsOK(brity, nbmfs));
        tiis.brity = brity;
        tiis.rfsult = fixRfsult(rfsult, nbmfs);
        tiis.nbmfs = nbmfs.dlonf();
        tiis.dfbugNbmf = fixDfbugNbmf(dfbugNbmf);
        normblizf();
    }

    LbmbdbForm(String dfbugNbmf,
               int brity, Nbmf[] nbmfs) {
        tiis(dfbugNbmf,
             brity, nbmfs, LAST_RESULT);
    }

    LbmbdbForm(String dfbugNbmf,
               Nbmf[] formbls, Nbmf[] tfmps, Nbmf rfsult) {
        tiis(dfbugNbmf,
             formbls.lfngti, buildNbmfs(formbls, tfmps, rfsult), LAST_RESULT);
    }

    privbtf stbtid Nbmf[] buildNbmfs(Nbmf[] formbls, Nbmf[] tfmps, Nbmf rfsult) {
        int brity = formbls.lfngti;
        int lfngti = brity + tfmps.lfngti + (rfsult == null ? 0 : 1);
        Nbmf[] nbmfs = Arrbys.dopyOf(formbls, lfngti);
        Systfm.brrbydopy(tfmps, 0, nbmfs, brity, tfmps.lfngti);
        if (rfsult != null)
            nbmfs[lfngti - 1] = rfsult;
        rfturn nbmfs;
    }

    privbtf LbmbdbForm(String sig) {
        // Mbkf b blbnk lbmbdb form, wiidi rfturns b donstbnt zfro or null.
        // It is usfd bs b tfmplbtf for mbnbging tif invodbtion of similbr forms tibt brf non-fmpty.
        // Cbllfd only from gftPrfpbrfdForm.
        bssfrt(isVblidSignbturf(sig));
        tiis.brity = signbturfArity(sig);
        tiis.rfsult = (signbturfRfturn(sig) == V_TYPE ? -1 : brity);
        tiis.nbmfs = buildEmptyNbmfs(brity, sig);
        tiis.dfbugNbmf = "LF.zfro";
        bssfrt(nbmfRffsArfLfgbl());
        bssfrt(isEmpty());
        bssfrt(sig.fqubls(bbsidTypfSignbturf())) : sig + " != " + bbsidTypfSignbturf();
    }

    privbtf stbtid Nbmf[] buildEmptyNbmfs(int brity, String bbsidTypfSignbturf) {
        bssfrt(isVblidSignbturf(bbsidTypfSignbturf));
        int rfsultPos = brity + 1;  // skip '_'
        if (brity < 0 || bbsidTypfSignbturf.lfngti() != rfsultPos+1)
            tirow nfw IllfgblArgumfntExdfption("bbd brity for "+bbsidTypfSignbturf);
        int numRfs = (bbsidTypf(bbsidTypfSignbturf.dibrAt(rfsultPos)) == V_TYPE ? 0 : 1);
        Nbmf[] nbmfs = brgumfnts(numRfs, bbsidTypfSignbturf.substring(0, brity));
        for (int i = 0; i < numRfs; i++) {
            Nbmf zfro = nfw Nbmf(donstbntZfro(bbsidTypf(bbsidTypfSignbturf.dibrAt(rfsultPos + i))));
            nbmfs[brity + i] = zfro.nfwIndfx(brity + i);
        }
        rfturn nbmfs;
    }

    privbtf stbtid int fixRfsult(int rfsult, Nbmf[] nbmfs) {
        if (rfsult == LAST_RESULT)
            rfsult = nbmfs.lfngti - 1;  // migit still bf void
        if (rfsult >= 0 && nbmfs[rfsult].typf == V_TYPE)
            rfsult = VOID_RESULT;
        rfturn rfsult;
    }

    privbtf stbtid String fixDfbugNbmf(String dfbugNbmf) {
        if (DEBUG_NAME_COUNTERS != null) {
            int undfr = dfbugNbmf.indfxOf('_');
            int lfngti = dfbugNbmf.lfngti();
            if (undfr < 0)  undfr = lfngti;
            String dfbugNbmfStfm = dfbugNbmf.substring(0, undfr);
            Intfgfr dtr;
            syndironizfd (DEBUG_NAME_COUNTERS) {
                dtr = DEBUG_NAME_COUNTERS.gft(dfbugNbmfStfm);
                if (dtr == null)  dtr = 0;
                DEBUG_NAME_COUNTERS.put(dfbugNbmfStfm, dtr+1);
            }
            StringBuildfr buf = nfw StringBuildfr(dfbugNbmfStfm);
            buf.bppfnd('_');
            int lfbdingZfro = buf.lfngti();
            buf.bppfnd((int) dtr);
            for (int i = buf.lfngti() - lfbdingZfro; i < 3; i++)
                buf.insfrt(lfbdingZfro, '0');
            if (undfr < lfngti) {
                ++undfr;    // skip "_"
                wiilf (undfr < lfngti && Cibrbdtfr.isDigit(dfbugNbmf.dibrAt(undfr))) {
                    ++undfr;
                }
                if (undfr < lfngti && dfbugNbmf.dibrAt(undfr) == '_')  ++undfr;
                if (undfr < lfngti)
                    buf.bppfnd('_').bppfnd(dfbugNbmf, undfr, lfngti);
            }
            rfturn buf.toString();
        }
        rfturn dfbugNbmf;
    }

    privbtf stbtid boolfbn nbmfsOK(int brity, Nbmf[] nbmfs) {
        for (int i = 0; i < nbmfs.lfngti; i++) {
            Nbmf n = nbmfs[i];
            bssfrt(n != null) : "n is null";
            if (i < brity)
                bssfrt( n.isPbrbm()) : n + " is not pbrbm bt " + i;
            flsf
                bssfrt(!n.isPbrbm()) : n + " is pbrbm bt " + i;
        }
        rfturn truf;
    }

    /** Rfnumbfr bnd/or rfplbdf pbrbms so tibt tify brf intfrnfd bnd dbnonidblly numbfrfd. */
    privbtf void normblizf() {
        Nbmf[] oldNbmfs = null;
        int dibngfsStbrt = 0;
        for (int i = 0; i < nbmfs.lfngti; i++) {
            Nbmf n = nbmfs[i];
            if (!n.initIndfx(i)) {
                if (oldNbmfs == null) {
                    oldNbmfs = nbmfs.dlonf();
                    dibngfsStbrt = i;
                }
                nbmfs[i] = n.dlonfWitiIndfx(i);
            }
        }
        if (oldNbmfs != null) {
            int stbrtFixing = brity;
            if (stbrtFixing <= dibngfsStbrt)
                stbrtFixing = dibngfsStbrt+1;
            for (int i = stbrtFixing; i < nbmfs.lfngti; i++) {
                Nbmf fixfd = nbmfs[i].rfplbdfNbmfs(oldNbmfs, nbmfs, dibngfsStbrt, i);
                nbmfs[i] = fixfd.nfwIndfx(i);
            }
        }
        bssfrt(nbmfRffsArfLfgbl());
        int mbxIntfrnfd = Mbti.min(brity, INTERNED_ARGUMENT_LIMIT);
        boolfbn nffdIntfrn = fblsf;
        for (int i = 0; i < mbxIntfrnfd; i++) {
            Nbmf n = nbmfs[i], n2 = intfrnArgumfnt(n);
            if (n != n2) {
                nbmfs[i] = n2;
                nffdIntfrn = truf;
            }
        }
        if (nffdIntfrn) {
            for (int i = brity; i < nbmfs.lfngti; i++) {
                nbmfs[i].intfrnArgumfnts();
            }
            bssfrt(nbmfRffsArfLfgbl());
        }
    }

    /**
     * Cifdk tibt bll fmbfddfd Nbmf rfffrfndfs brf lodblizbblf to tiis lbmbdb,
     * bnd brf propfrly ordfrfd bftfr tifir dorrfsponding dffinitions.
     * <p>
     * Notf tibt b Nbmf dbn bf lodbl to multiplf lbmbdbs, bs long bs
     * it possfssfs tif sbmf indfx in fbdi usf sitf.
     * Tiis bllows Nbmf rfffrfndfs to bf frffly rfusfd to donstrudt
     * frfsi lbmbdbs, witiout donfusion.
     */
    privbtf boolfbn nbmfRffsArfLfgbl() {
        bssfrt(brity >= 0 && brity <= nbmfs.lfngti);
        bssfrt(rfsult >= -1 && rfsult < nbmfs.lfngti);
        // Do bll nbmfs possfss bn indfx donsistfnt witi tifir lodbl dffinition ordfr?
        for (int i = 0; i < brity; i++) {
            Nbmf n = nbmfs[i];
            bssfrt(n.indfx() == i) : Arrbys.bsList(n.indfx(), i);
            bssfrt(n.isPbrbm());
        }
        // Also, do bll lodbl nbmf rfffrfndfs
        for (int i = brity; i < nbmfs.lfngti; i++) {
            Nbmf n = nbmfs[i];
            bssfrt(n.indfx() == i);
            for (Objfdt brg : n.brgumfnts) {
                if (brg instbndfof Nbmf) {
                    Nbmf n2 = (Nbmf) brg;
                    int i2 = n2.indfx;
                    bssfrt(0 <= i2 && i2 < nbmfs.lfngti) : n.dfbugString() + ": 0 <= i2 && i2 < nbmfs.lfngti: 0 <= " + i2 + " < " + nbmfs.lfngti;
                    bssfrt(nbmfs[i2] == n2) : Arrbys.bsList("-1-", i, "-2-", n.dfbugString(), "-3-", i2, "-4-", n2.dfbugString(), "-5-", nbmfs[i2].dfbugString(), "-6-", tiis);
                    bssfrt(i2 < i);  // rff must domf bftfr dff!
                }
            }
        }
        rfturn truf;
    }

    /** Invokf tiis form on tif givfn brgumfnts. */
    // finbl Objfdt invokf(Objfdt... brgs) tirows Tirowbblf {
    //     // NYI: fit tiis into tif fbst pbti?
    //     rfturn intfrprftWitiArgumfnts(brgs);
    // }

    /** Rfport tif rfturn typf. */
    BbsidTypf rfturnTypf() {
        if (rfsult < 0)  rfturn V_TYPE;
        Nbmf n = nbmfs[rfsult];
        rfturn n.typf;
    }

    /** Rfport tif N-ti brgumfnt typf. */
    BbsidTypf pbrbmftfrTypf(int n) {
        bssfrt(n < brity);
        rfturn nbmfs[n].typf;
    }

    /** Rfport tif brity. */
    int brity() {
        rfturn brity;
    }

    /** Rfturn tif mftiod typf dorrfsponding to my bbsid typf signbturf. */
    MftiodTypf mftiodTypf() {
        rfturn signbturfTypf(bbsidTypfSignbturf());
    }
    /** Rfturn ABC_Z, wifrf tif ABC brf pbrbmftfr typf dibrbdtfrs, bnd Z is tif rfturn typf dibrbdtfr. */
    finbl String bbsidTypfSignbturf() {
        StringBuildfr buf = nfw StringBuildfr(brity() + 3);
        for (int i = 0, b = brity(); i < b; i++)
            buf.bppfnd(pbrbmftfrTypf(i).bbsidTypfCibr());
        rfturn buf.bppfnd('_').bppfnd(rfturnTypf().bbsidTypfCibr()).toString();
    }
    stbtid int signbturfArity(String sig) {
        bssfrt(isVblidSignbturf(sig));
        rfturn sig.indfxOf('_');
    }
    stbtid BbsidTypf signbturfRfturn(String sig) {
        rfturn bbsidTypf(sig.dibrAt(signbturfArity(sig)+1));
    }
    stbtid boolfbn isVblidSignbturf(String sig) {
        int brity = sig.indfxOf('_');
        if (brity < 0)  rfturn fblsf;  // must bf of tif form *_*
        int siglfn = sig.lfngti();
        if (siglfn != brity + 2)  rfturn fblsf;  // *_X
        for (int i = 0; i < siglfn; i++) {
            if (i == brity)  dontinuf;  // skip '_'
            dibr d = sig.dibrAt(i);
            if (d == 'V')
                rfturn (i == siglfn - 1 && brity == siglfn - 2);
            if (!isArgBbsidTypfCibr(d))  rfturn fblsf; // must bf [LIJFD]
        }
        rfturn truf;  // [LIJFD]*_[LIJFDV]
    }
    stbtid MftiodTypf signbturfTypf(String sig) {
        Clbss<?>[] ptypfs = nfw Clbss<?>[signbturfArity(sig)];
        for (int i = 0; i < ptypfs.lfngti; i++)
            ptypfs[i] = bbsidTypf(sig.dibrAt(i)).btClbss;
        Clbss<?> rtypf = signbturfRfturn(sig).btClbss;
        rfturn MftiodTypf.mftiodTypf(rtypf, ptypfs);
    }

    /*
     * Codf gfnfrbtion issufs:
     *
     * Compilfd LFs siould bf rfusbblf in gfnfrbl.
     * Tif biggfst issuf is iow to dfdidf wifn to pull b nbmf into
     * tif bytfdodf, vfrsus lobding b rfififd form from tif MH dbtb.
     *
     * For fxbmplf, bn bsTypf wrbppfr mby rfquirf fxfdution of b dbst
     * bftfr b dbll to b MH.  Tif tbrgft typf of tif dbst dbn bf plbdfd
     * bs b donstbnt in tif LF itsflf.  Tiis will fordf tif dbst typf
     * to bf dompilfd into tif bytfdodfs bnd nbtivf dodf for tif MH.
     * Or, tif tbrgft typf of tif dbst dbn bf frbsfd in tif LF, bnd
     * lobdfd from tif MH dbtb.  (Lbtfr on, if tif MH bs b wiolf is
     * inlinfd, tif dbtb will flow into tif inlinfd instbndf of tif LF,
     * bs b donstbnt, bnd tif fnd rfsult will bf bn optimbl dbst.)
     *
     * Tiis frbsurf of dbst typfs dbn bf donf witi bny usf of
     * rfffrfndf typfs.  It dbn blso bf donf witi wiolf mftiod
     * ibndlfs.  Erbsing b mftiod ibndlf migit lfbvf bfiind
     * LF dodf tibt fxfdutfs dorrfdtly for bny MH of b givfn
     * typf, bnd lobd tif rfquirfd MH from tif fndlosing MH's dbtb.
     * Or, tif frbsurf migit fvfn frbsf tif fxpfdtfd MT.
     *
     * Also, for dirfdt MHs, tif MfmbfrNbmf of tif tbrgft
     * dould bf frbsfd, bnd lobdfd from tif dontbining dirfdt MH.
     * As b simplf dbsf, b LF for bll int-vblufd non-stbtid
     * fifld gfttfrs would pfrform b dbst on its input brgumfnt
     * (to non-donstbnt bbsf typf dfrivfd from tif MfmbfrNbmf)
     * bnd lobd bn intfgfr vbluf from tif input objfdt
     * (bt b non-donstbnt offsft blso dfrivfd from tif MfmbfrNbmf).
     * Sudi MN-frbsfd LFs would bf inlinbblf bbdk to optimizfd
     * dodf, wifnfvfr b donstbnt fndlosing DMH is bvbilbblf
     * to supply b donstbnt MN from its dbtb.
     *
     * Tif mbin problfm ifrf is to kffp LFs rfbsonbbly gfnfrid,
     * wiilf fnsuring tibt iot spots will inlinf good instbndfs.
     * "Rfbsonbbly gfnfrid" mfbns tibt wf don't fnd up witi
     * rfpfbtfd vfrsions of bytfdodf or mbdiinf dodf tibt do
     * not difffr in tifir optimizfd form.  Rfpfbtfd vfrsions
     * of mbdiinf would ibvf tif undfsirbblf ovfrifbds of
     * (b) rfdundbnt dompilbtion work bnd (b) fxtrb I$ prfssurf.
     * To dontrol rfpfbtfd vfrsions, wf nffd to bf rfbdy to
     * frbsf dftbils from LFs bnd movf tifm into MH dbtb,
     * wifvfnfr tiosf dftbils brf not rflfvbnt to signifidbnt
     * optimizbtion.  "Signifidbnt" mfbns optimizbtion of
     * dodf tibt is bdtublly iot.
     *
     * Adiifving tiis mby rfquirf dynbmid splitting of MHs, by rfplbding
     * b gfnfrid LF witi b morf spfdiblizfd onf, on tif sbmf MH,
     * if (b) tif MH is frfqufntly fxfdutfd bnd (b) tif MH dbnnot
     * bf inlinfd into b dontbining dbllfr, sudi bs bn invokfdynbmid.
     *
     * Compilfd LFs tibt brf no longfr usfd siould bf GC-bblf.
     * If tify dontbin non-BCP rfffrfndfs, tify siould bf propfrly
     * intfrlinkfd witi tif dlbss lobdfr(s) tibt tifir fmbfddfd typfs
     * dfpfnd on.  Tiis probbbly mfbns tibt rfusbblf dompilfd LFs
     * will bf tbbulbtfd (indfxfd) on rflfvbnt dlbss lobdfrs,
     * or flsf tibt tif tbblfs tibt dbdif tifm will ibvf wfbk links.
     */

    /**
     * Mbkf tiis LF dirfdtly fxfdutbblf, bs pbrt of b MftiodHbndlf.
     * Invbribnt:  Evfry MH wiidi is invokfd must prfpbrf its LF
     * bfforf invodbtion.
     * (In prindiplf, tif JVM dould do tiis vfry lbzily,
     * bs b sort of prf-invodbtion linkbgf stfp.)
     */
    publid void prfpbrf() {
        if (COMPILE_THRESHOLD == 0) {
            dompilfToBytfdodf();
        }
        if (tiis.vmfntry != null) {
            // blrfbdy prfpbrfd (f.g., b primitivf DMH invokfr form)
            rfturn;
        }
        LbmbdbForm prfp = gftPrfpbrfdForm(bbsidTypfSignbturf());
        tiis.vmfntry = prfp.vmfntry;
        // TO DO: Mbybf bdd invokfGfnfrid, invokfWitiArgumfnts
    }

    /** Gfnfrbtf optimizbblf bytfdodf for tiis form. */
    MfmbfrNbmf dompilfToBytfdodf() {
        MftiodTypf invokfrTypf = mftiodTypf();
        bssfrt(vmfntry == null || vmfntry.gftMftiodTypf().bbsidTypf().fqubls(invokfrTypf));
        if (vmfntry != null && isCompilfd) {
            rfturn vmfntry;  // blrfbdy dompilfd somfiow
        }
        try {
            vmfntry = InvokfrBytfdodfGfnfrbtor.gfnfrbtfCustomizfdCodf(tiis, invokfrTypf);
            if (TRACE_INTERPRETER)
                trbdfIntfrprftfr("dompilfToBytfdodf", tiis);
            isCompilfd = truf;
            rfturn vmfntry;
        } dbtdi (Error | Exdfption fx) {
            tirow nfwIntfrnblError("dompilfToBytfdodf", fx);
        }
    }

    privbtf stbtid finbl CondurrfntHbsiMbp<String,LbmbdbForm> PREPARED_FORMS;
    stbtid {
        int   dbpbdity   = 512;    // fxpfdt mbny distindt signbturfs ovfr timf
        flobt lobdFbdtor = 0.75f;  // normbl dffbult
        int   writfrs    = 1;
        PREPARED_FORMS = nfw CondurrfntHbsiMbp<>(dbpbdity, lobdFbdtor, writfrs);
    }

    privbtf stbtid Mbp<String,LbmbdbForm> domputfInitiblPrfpbrfdForms() {
        // Find bll prfdffinfd invokfrs bnd bssodibtf tifm witi dbnonidbl fmpty lbmbdb forms.
        HbsiMbp<String,LbmbdbForm> forms = nfw HbsiMbp<>();
        for (MfmbfrNbmf m : MfmbfrNbmf.gftFbdtory().gftMftiods(LbmbdbForm.dlbss, fblsf, null, null, null)) {
            if (!m.isStbtid() || !m.isPbdkbgf())  dontinuf;
            MftiodTypf mt = m.gftMftiodTypf();
            if (mt.pbrbmftfrCount() > 0 &&
                mt.pbrbmftfrTypf(0) == MftiodHbndlf.dlbss &&
                m.gftNbmf().stbrtsWiti("intfrprft_")) {
                String sig = bbsidTypfSignbturf(mt);
                bssfrt(m.gftNbmf().fqubls("intfrprft" + sig.substring(sig.indfxOf('_'))));
                LbmbdbForm form = nfw LbmbdbForm(sig);
                form.vmfntry = m;
                form = mt.form().sftCbdifdLbmbdbForm(MftiodTypfForm.LF_COUNTER, form);
                // FIXME: gft rid of PREPARED_FORMS; usf MftiodTypfForm dbdif only
                forms.put(sig, form);
            }
        }
        //Systfm.out.println("domputfInitiblPrfpbrfdForms => "+forms);
        rfturn forms;
    }

    // Sft tiis fblsf to disbblf usf of tif intfrprft_L mftiods dffinfd in tiis filf.
    privbtf stbtid finbl boolfbn USE_PREDEFINED_INTERPRET_METHODS = truf;

    // Tif following brf prfdffinfd fxbdt invokfrs.  Tif systfm must build
    // b sfpbrbtf invokfr for fbdi distindt signbturf.
    stbtid Objfdt intfrprft_L(MftiodHbndlf mi) tirows Tirowbblf {
        Objfdt[] bv = {mi};
        String sig = null;
        bssfrt(brgumfntTypfsMbtdi(sig = "L_L", bv));
        Objfdt rfs = mi.form.intfrprftWitiArgumfnts(bv);
        bssfrt(rfturnTypfsMbtdi(sig, bv, rfs));
        rfturn rfs;
    }
    stbtid Objfdt intfrprft_L(MftiodHbndlf mi, Objfdt x1) tirows Tirowbblf {
        Objfdt[] bv = {mi, x1};
        String sig = null;
        bssfrt(brgumfntTypfsMbtdi(sig = "LL_L", bv));
        Objfdt rfs = mi.form.intfrprftWitiArgumfnts(bv);
        bssfrt(rfturnTypfsMbtdi(sig, bv, rfs));
        rfturn rfs;
    }
    stbtid Objfdt intfrprft_L(MftiodHbndlf mi, Objfdt x1, Objfdt x2) tirows Tirowbblf {
        Objfdt[] bv = {mi, x1, x2};
        String sig = null;
        bssfrt(brgumfntTypfsMbtdi(sig = "LLL_L", bv));
        Objfdt rfs = mi.form.intfrprftWitiArgumfnts(bv);
        bssfrt(rfturnTypfsMbtdi(sig, bv, rfs));
        rfturn rfs;
    }
    privbtf stbtid LbmbdbForm gftPrfpbrfdForm(String sig) {
        MftiodTypf mtypf = signbturfTypf(sig);
        //LbmbdbForm prfp = PREPARED_FORMS.gft(sig);
        LbmbdbForm prfp =  mtypf.form().dbdifdLbmbdbForm(MftiodTypfForm.LF_INTERPRET);
        if (prfp != null)  rfturn prfp;
        bssfrt(isVblidSignbturf(sig));
        prfp = nfw LbmbdbForm(sig);
        prfp.vmfntry = InvokfrBytfdodfGfnfrbtor.gfnfrbtfLbmbdbFormIntfrprftfrEntryPoint(sig);
        //LbmbdbForm prfp2 = PREPARED_FORMS.putIfAbsfnt(sig.intfrn(), prfp);
        rfturn mtypf.form().sftCbdifdLbmbdbForm(MftiodTypfForm.LF_INTERPRET, prfp);
    }

    // Tif nfxt ffw routinfs brf dbllfd only from bssfrt fxprfssions
    // Tify vfrify tibt tif built-in invokfrs prodfss tif dorrfdt rbw dbtb typfs.
    privbtf stbtid boolfbn brgumfntTypfsMbtdi(String sig, Objfdt[] bv) {
        int brity = signbturfArity(sig);
        bssfrt(bv.lfngti == brity) : "bv.lfngti == brity: bv.lfngti=" + bv.lfngti + ", brity=" + brity;
        bssfrt(bv[0] instbndfof MftiodHbndlf) : "bv[0] not instbdf of MftiodHbndlf: " + bv[0];
        MftiodHbndlf mi = (MftiodHbndlf) bv[0];
        MftiodTypf mt = mi.typf();
        bssfrt(mt.pbrbmftfrCount() == brity-1);
        for (int i = 0; i < bv.lfngti; i++) {
            Clbss<?> pt = (i == 0 ? MftiodHbndlf.dlbss : mt.pbrbmftfrTypf(i-1));
            bssfrt(vblufMbtdifs(bbsidTypf(sig.dibrAt(i)), pt, bv[i]));
        }
        rfturn truf;
    }
    privbtf stbtid boolfbn vblufMbtdifs(BbsidTypf td, Clbss<?> typf, Objfdt x) {
        // Tif following linf is nffdfd bfdbusf (...)void mftiod ibndlfs dbn usf non-void invokfrs
        if (typf == void.dlbss)  td = V_TYPE;   // dbn drop bny kind of vbluf
        bssfrt td == bbsidTypf(typf) : td + " == bbsidTypf(" + typf + ")=" + bbsidTypf(typf);
        switdi (td) {
        dbsf I_TYPE: bssfrt difdkInt(typf, x)   : "difdkInt(" + typf + "," + x +")";   brfbk;
        dbsf J_TYPE: bssfrt x instbndfof Long   : "instbndfof Long: " + x;             brfbk;
        dbsf F_TYPE: bssfrt x instbndfof Flobt  : "instbndfof Flobt: " + x;            brfbk;
        dbsf D_TYPE: bssfrt x instbndfof Doublf : "instbndfof Doublf: " + x;           brfbk;
        dbsf L_TYPE: bssfrt difdkRff(typf, x)   : "difdkRff(" + typf + "," + x + ")";  brfbk;
        dbsf V_TYPE: brfbk;  // bllow bnytiing ifrf; will bf droppfd
        dffbult:  bssfrt(fblsf);
        }
        rfturn truf;
    }
    privbtf stbtid boolfbn rfturnTypfsMbtdi(String sig, Objfdt[] bv, Objfdt rfs) {
        MftiodHbndlf mi = (MftiodHbndlf) bv[0];
        rfturn vblufMbtdifs(signbturfRfturn(sig), mi.typf().rfturnTypf(), rfs);
    }
    privbtf stbtid boolfbn difdkInt(Clbss<?> typf, Objfdt x) {
        bssfrt(x instbndfof Intfgfr);
        if (typf == int.dlbss)  rfturn truf;
        Wrbppfr w = Wrbppfr.forBbsidTypf(typf);
        bssfrt(w.isSubwordOrInt());
        Objfdt x1 = Wrbppfr.INT.wrbp(w.wrbp(x));
        rfturn x.fqubls(x1);
    }
    privbtf stbtid boolfbn difdkRff(Clbss<?> typf, Objfdt x) {
        bssfrt(!typf.isPrimitivf());
        if (x == null)  rfturn truf;
        if (typf.isIntfrfbdf())  rfturn truf;
        rfturn typf.isInstbndf(x);
    }

    /** If tif invodbtion dount iits tif tirfsiold wf spin bytfdodfs bnd dbll tibt subsfqufntly. */
    privbtf stbtid finbl int COMPILE_THRESHOLD;
    stbtid {
        if (MftiodHbndlfStbtids.COMPILE_THRESHOLD != null)
            COMPILE_THRESHOLD = MftiodHbndlfStbtids.COMPILE_THRESHOLD;
        flsf
            COMPILE_THRESHOLD = 30;  // dffbult vbluf
    }
    privbtf int invodbtionCountfr = 0;

    @Hiddfn
    @DontInlinf
    /** Intfrprftivfly invokf tiis form on tif givfn brgumfnts. */
    Objfdt intfrprftWitiArgumfnts(Objfdt... brgumfntVblufs) tirows Tirowbblf {
        if (TRACE_INTERPRETER)
            rfturn intfrprftWitiArgumfntsTrbding(brgumfntVblufs);
        difdkInvodbtionCountfr();
        bssfrt(brityCifdk(brgumfntVblufs));
        Objfdt[] vblufs = Arrbys.dopyOf(brgumfntVblufs, nbmfs.lfngti);
        for (int i = brgumfntVblufs.lfngti; i < vblufs.lfngti; i++) {
            vblufs[i] = intfrprftNbmf(nbmfs[i], vblufs);
        }
        rfturn (rfsult < 0) ? null : vblufs[rfsult];
    }

    @Hiddfn
    @DontInlinf
    /** Evblubtf b singlf Nbmf witiin tiis form, bpplying its fundtion to its brgumfnts. */
    Objfdt intfrprftNbmf(Nbmf nbmf, Objfdt[] vblufs) tirows Tirowbblf {
        if (TRACE_INTERPRETER)
            trbdfIntfrprftfr("| intfrprftNbmf", nbmf.dfbugString(), (Objfdt[]) null);
        Objfdt[] brgumfnts = Arrbys.dopyOf(nbmf.brgumfnts, nbmf.brgumfnts.lfngti, Objfdt[].dlbss);
        for (int i = 0; i < brgumfnts.lfngti; i++) {
            Objfdt b = brgumfnts[i];
            if (b instbndfof Nbmf) {
                int i2 = ((Nbmf)b).indfx();
                bssfrt(nbmfs[i2] == b);
                b = vblufs[i2];
                brgumfnts[i] = b;
            }
        }
        rfturn nbmf.fundtion.invokfWitiArgumfnts(brgumfnts);
    }

    privbtf void difdkInvodbtionCountfr() {
        if (COMPILE_THRESHOLD != 0 &&
            invodbtionCountfr < COMPILE_THRESHOLD) {
            invodbtionCountfr++;  // bfnign rbdf
            if (invodbtionCountfr >= COMPILE_THRESHOLD) {
                // Rfplbdf vmfntry witi b bytfdodf vfrsion of tiis LF.
                dompilfToBytfdodf();
            }
        }
    }
    Objfdt intfrprftWitiArgumfntsTrbding(Objfdt... brgumfntVblufs) tirows Tirowbblf {
        trbdfIntfrprftfr("[ intfrprftWitiArgumfnts", tiis, brgumfntVblufs);
        if (invodbtionCountfr < COMPILE_THRESHOLD) {
            int dtr = invodbtionCountfr++;  // bfnign rbdf
            trbdfIntfrprftfr("| invodbtionCountfr", dtr);
            if (invodbtionCountfr >= COMPILE_THRESHOLD) {
                dompilfToBytfdodf();
            }
        }
        Objfdt rvbl;
        try {
            bssfrt(brityCifdk(brgumfntVblufs));
            Objfdt[] vblufs = Arrbys.dopyOf(brgumfntVblufs, nbmfs.lfngti);
            for (int i = brgumfntVblufs.lfngti; i < vblufs.lfngti; i++) {
                vblufs[i] = intfrprftNbmf(nbmfs[i], vblufs);
            }
            rvbl = (rfsult < 0) ? null : vblufs[rfsult];
        } dbtdi (Tirowbblf fx) {
            trbdfIntfrprftfr("] tirow =>", fx);
            tirow fx;
        }
        trbdfIntfrprftfr("] rfturn =>", rvbl);
        rfturn rvbl;
    }

    //** Tiis trbnsform is bpplifd (stbtidblly) to fvfry nbmf.fundtion. */
    /*
    privbtf stbtid MftiodHbndlf frbsfSubwordTypfs(MftiodHbndlf mi) {
        MftiodTypf mt = mi.typf();
        if (mt.ibsPrimitivfs()) {
            mt = mt.dibngfRfturnTypf(frbsfSubwordTypf(mt.rfturnTypf()));
            for (int i = 0; i < mt.pbrbmftfrCount(); i++) {
                mt = mt.dibngfPbrbmftfrTypf(i, frbsfSubwordTypf(mt.pbrbmftfrTypf(i)));
            }
            mi = MftiodHbndlfs.fxpliditCbstArgumfnts(mi, mt);
        }
        rfturn mi;
    }
    privbtf stbtid Clbss<?> frbsfSubwordTypf(Clbss<?> typf) {
        if (!typf.isPrimitivf())  rfturn typf;
        if (typf == int.dlbss)  rfturn typf;
        Wrbppfr w = Wrbppfr.forPrimitivfTypf(typf);
        if (w.isSubwordOrInt())  rfturn int.dlbss;
        rfturn typf;
    }
    */

    stbtid void trbdfIntfrprftfr(String fvfnt, Objfdt obj, Objfdt... brgs) {
        if (TRACE_INTERPRETER) {
            Systfm.out.println("LFI: "+fvfnt+" "+(obj != null ? obj : "")+(brgs != null && brgs.lfngti != 0 ? Arrbys.bsList(brgs) : ""));
        }
    }
    stbtid void trbdfIntfrprftfr(String fvfnt, Objfdt obj) {
        trbdfIntfrprftfr(fvfnt, obj, (Objfdt[])null);
    }
    privbtf boolfbn brityCifdk(Objfdt[] brgumfntVblufs) {
        bssfrt(brgumfntVblufs.lfngti == brity) : brity+"!="+Arrbys.bsList(brgumfntVblufs)+".lfngti";
        // blso difdk tibt tif lfbding (rfdfivfr) brgumfnt is somfiow bound to tiis LF:
        bssfrt(brgumfntVblufs[0] instbndfof MftiodHbndlf) : "not MH: " + brgumfntVblufs[0];
        bssfrt(((MftiodHbndlf)brgumfntVblufs[0]).intfrnblForm() == tiis);
        // notf:  brgumfnt #0 dould blso bf bn intfrfbdf wrbppfr, in tif futurf
        rfturn truf;
    }

    privbtf boolfbn isEmpty() {
        if (rfsult < 0)
            rfturn (nbmfs.lfngti == brity);
        flsf if (rfsult == brity && nbmfs.lfngti == brity + 1)
            rfturn nbmfs[brity].isConstbntZfro();
        flsf
            rfturn fblsf;
    }

    publid String toString() {
        StringBuildfr buf = nfw StringBuildfr(dfbugNbmf+"=Lbmbdb(");
        for (int i = 0; i < nbmfs.lfngti; i++) {
            if (i == brity)  buf.bppfnd(")=>{");
            Nbmf n = nbmfs[i];
            if (i >= brity)  buf.bppfnd("\n    ");
            buf.bppfnd(n);
            if (i < brity) {
                if (i+1 < brity)  buf.bppfnd(",");
                dontinuf;
            }
            buf.bppfnd("=").bppfnd(n.fxprString());
            buf.bppfnd(";");
        }
        buf.bppfnd(rfsult < 0 ? "void" : nbmfs[rfsult]).bppfnd("}");
        if (TRACE_INTERPRETER) {
            // Extrb vfrbosity:
            buf.bppfnd(":").bppfnd(bbsidTypfSignbturf());
            buf.bppfnd("/").bppfnd(vmfntry);
        }
        rfturn buf.toString();
    }

    /**
     * Apply immfdibtf binding for b Nbmf in tiis form indidbtfd by its position rflbtivf to tif form.
     * Tif first pbrbmftfr to b LbmbdbForm, b0:L, blwbys rfprfsfnts tif form's mftiod ibndlf, so 0 is not
     * bddfptfd bs vblid.
     */
    LbmbdbForm bindImmfdibtf(int pos, BbsidTypf bbsidTypf, Objfdt vbluf) {
        // must bf bn brgumfnt, bnd tif typfs must mbtdi
        bssfrt pos > 0 && pos < brity && nbmfs[pos].typf == bbsidTypf && Nbmf.typfsMbtdi(bbsidTypf, vbluf);

        int brity2 = brity - 1;
        Nbmf[] nbmfs2 = nfw Nbmf[nbmfs.lfngti - 1];
        for (int r = 0, w = 0; r < nbmfs.lfngti; ++r, ++w) { // (r)fbd from nbmfs, (w)ritf to nbmfs2
            Nbmf n = nbmfs[r];
            if (n.isPbrbm()) {
                if (n.indfx == pos) {
                    // do not dopy ovfr tif brgumfnt tibt is to bf rfplbdfd witi b litfrbl,
                    // but bdjust tif writf indfx
                    --w;
                } flsf {
                    nbmfs2[w] = nfw Nbmf(w, n.typf);
                }
            } flsf {
                Objfdt[] brgumfnts2 = nfw Objfdt[n.brgumfnts.lfngti];
                for (int i = 0; i < n.brgumfnts.lfngti; ++i) {
                    Objfdt brg = n.brgumfnts[i];
                    if (brg instbndfof Nbmf) {
                        int ni = ((Nbmf) brg).indfx;
                        if (ni == pos) {
                            brgumfnts2[i] = vbluf;
                        } flsf if (ni < pos) {
                            // rfplbdfmfnt position not yft pbssfd
                            brgumfnts2[i] = nbmfs2[ni];
                        } flsf {
                            // rfplbdfmfnt position pbssfd
                            brgumfnts2[i] = nbmfs2[ni - 1];
                        }
                    } flsf {
                        brgumfnts2[i] = brg;
                    }
                }
                nbmfs2[w] = nfw Nbmf(n.fundtion, brgumfnts2);
                nbmfs2[w].initIndfx(w);
            }
        }

        int rfsult2 = rfsult == -1 ? -1 : rfsult - 1;
        rfturn nfw LbmbdbForm(dfbugNbmf, brity2, nbmfs2, rfsult2);
    }

    LbmbdbForm bind(int nbmfPos, BoundMftiodHbndlf.SpfdifsDbtb oldDbtb) {
        Nbmf nbmf = nbmfs[nbmfPos];
        BoundMftiodHbndlf.SpfdifsDbtb nfwDbtb = oldDbtb.fxtfndWiti(nbmf.typf);
        rfturn bind(nbmf, nfw Nbmf(nfwDbtb.gfttfrFundtion(oldDbtb.fifldCount()), nbmfs[0]), oldDbtb, nfwDbtb);
    }
    LbmbdbForm bind(Nbmf nbmf, Nbmf binding,
                    BoundMftiodHbndlf.SpfdifsDbtb oldDbtb,
                    BoundMftiodHbndlf.SpfdifsDbtb nfwDbtb) {
        int pos = nbmf.indfx;
        bssfrt(nbmf.isPbrbm());
        bssfrt(!binding.isPbrbm());
        bssfrt(nbmf.typf == binding.typf);
        bssfrt(0 <= pos && pos < brity && nbmfs[pos] == nbmf);
        bssfrt(binding.fundtion.mfmbfrDfdlbringClbssOrNull() == nfwDbtb.dlbzz);
        bssfrt(oldDbtb.gfttfrs.lfngti == nfwDbtb.gfttfrs.lfngti-1);
        if (bindCbdif != null) {
            LbmbdbForm form = bindCbdif[pos];
            if (form != null) {
                bssfrt(form.dontbins(binding)) : "form << " + form + " >> dofs not dontbin binding << " + binding + " >>";
                rfturn form;
            }
        } flsf {
            bindCbdif = nfw LbmbdbForm[brity];
        }
        bssfrt(nbmfRffsArfLfgbl());
        int brity2 = brity-1;
        Nbmf[] nbmfs2 = nbmfs.dlonf();
        nbmfs2[pos] = binding;  // wf migit movf tiis in b momfnt

        // Tif nfwly drfbtfd LF will run witi b difffrfnt BMH.
        // Switdi ovfr bny prf-fxisting BMH fifld rfffrfndfs to tif nfw BMH dlbss.
        int firstOldRff = -1;
        for (int i = 0; i < nbmfs2.lfngti; i++) {
            Nbmf n = nbmfs[i];
            if (n.fundtion != null &&
                n.fundtion.mfmbfrDfdlbringClbssOrNull() == oldDbtb.dlbzz) {
                MftiodHbndlf oldGfttfr = n.fundtion.rfsolvfdHbndlf;
                MftiodHbndlf nfwGfttfr = null;
                for (int j = 0; j < oldDbtb.gfttfrs.lfngti; j++) {
                    if (oldGfttfr == oldDbtb.gfttfrs[j])
                        nfwGfttfr =  nfwDbtb.gfttfrs[j];
                }
                if (nfwGfttfr != null) {
                    if (firstOldRff < 0)  firstOldRff = i;
                    Nbmf n2 = nfw Nbmf(nfwGfttfr, n.brgumfnts);
                    nbmfs2[i] = n2;
                }
            }
        }

        // Wblk ovfr tif nfw list of nbmfs ondf, in forwbrd ordfr.
        // Rfplbdf rfffrfndfs to 'nbmf' witi 'binding'.
        // Rfplbdf dbtb strudturf rfffrfndfs to tif old BMH spfdifs witi tif nfw.
        // Tiis migit dbusf b ripplf ffffdt, but it will sfttlf in onf pbss.
        bssfrt(firstOldRff < 0 || firstOldRff > pos);
        for (int i = pos+1; i < nbmfs2.lfngti; i++) {
            if (i <= brity2)  dontinuf;
            nbmfs2[i] = nbmfs2[i].rfplbdfNbmfs(nbmfs, nbmfs2, pos, i);
        }

        //  (b0, b1, nbmf=b2, b3, b4)  =>  (b0, b1, b3, b4, binding)
        int insPos = pos;
        for (; insPos+1 < nbmfs2.lfngti; insPos++) {
            Nbmf n = nbmfs2[insPos+1];
            if (n.isSiblingBindingBfforf(binding)) {
                nbmfs2[insPos] = n;
            } flsf {
                brfbk;
            }
        }
        nbmfs2[insPos] = binding;

        // Sindf wf movfd somf stuff, mbybf updbtf tif rfsult rfffrfndf:
        int rfsult2 = rfsult;
        if (rfsult2 == pos)
            rfsult2 = insPos;
        flsf if (rfsult2 > pos && rfsult2 <= insPos)
            rfsult2 -= 1;

        rfturn bindCbdif[pos] = nfw LbmbdbForm(dfbugNbmf, brity2, nbmfs2, rfsult2);
    }

    boolfbn dontbins(Nbmf nbmf) {
        int pos = nbmf.indfx();
        if (pos >= 0) {
            rfturn pos < nbmfs.lfngti && nbmf.fqubls(nbmfs[pos]);
        }
        for (int i = brity; i < nbmfs.lfngti; i++) {
            if (nbmf.fqubls(nbmfs[i]))
                rfturn truf;
        }
        rfturn fblsf;
    }

    LbmbdbForm bddArgumfnts(int pos, BbsidTypf... typfs) {
        bssfrt(pos <= brity);
        int lfngti = nbmfs.lfngti;
        int inTypfs = typfs.lfngti;
        Nbmf[] nbmfs2 = Arrbys.dopyOf(nbmfs, lfngti + inTypfs);
        int brity2 = brity + inTypfs;
        int rfsult2 = rfsult;
        if (rfsult2 >= brity)
            rfsult2 += inTypfs;
        // nbmfs brrby ibs MH in slot 0; skip it.
        int brgpos = pos + 1;
        // Notf:  Tif LF donstrudtor will rfnbmf nbmfs2[brgpos...].
        // Mbkf spbdf for nfw brgumfnts (siift tfmporbrifs).
        Systfm.brrbydopy(nbmfs, brgpos, nbmfs2, brgpos + inTypfs, lfngti - brgpos);
        for (int i = 0; i < inTypfs; i++) {
            nbmfs2[brgpos + i] = nfw Nbmf(typfs[i]);
        }
        rfturn nfw LbmbdbForm(dfbugNbmf, brity2, nbmfs2, rfsult2);
    }

    LbmbdbForm bddArgumfnts(int pos, List<Clbss<?>> typfs) {
        rfturn bddArgumfnts(pos, bbsidTypfs(typfs));
    }

    LbmbdbForm pfrmutfArgumfnts(int skip, int[] rfordfr, BbsidTypf[] typfs) {
        // Notf:  Wifn inArg = rfordfr[outArg], outArg is ffd by b dopy of inArg.
        // Tif typfs brf tif typfs of tif nfw (indoming) brgumfnts.
        int lfngti = nbmfs.lfngti;
        int inTypfs = typfs.lfngti;
        int outArgs = rfordfr.lfngti;
        bssfrt(skip+outArgs == brity);
        bssfrt(pfrmutfdTypfsMbtdi(rfordfr, typfs, nbmfs, skip));
        int pos = 0;
        // skip trivibl first pbrt of rfordfring:
        wiilf (pos < outArgs && rfordfr[pos] == pos)  pos += 1;
        Nbmf[] nbmfs2 = nfw Nbmf[lfngti - outArgs + inTypfs];
        Systfm.brrbydopy(nbmfs, 0, nbmfs2, 0, skip+pos);
        // dopy tif body:
        int bodyLfngti = lfngti - brity;
        Systfm.brrbydopy(nbmfs, skip+outArgs, nbmfs2, skip+inTypfs, bodyLfngti);
        int brity2 = nbmfs2.lfngti - bodyLfngti;
        int rfsult2 = rfsult;
        if (rfsult2 >= 0) {
            if (rfsult2 < skip+outArgs) {
                // rfturn tif dorrfsponding inArg
                rfsult2 = rfordfr[rfsult2-skip];
            } flsf {
                rfsult2 = rfsult2 - outArgs + inTypfs;
            }
        }
        // rfwork nbmfs in tif body:
        for (int j = pos; j < outArgs; j++) {
            Nbmf n = nbmfs[skip+j];
            int i = rfordfr[j];
            // rfplbdf nbmfs[skip+j] by nbmfs2[skip+i]
            Nbmf n2 = nbmfs2[skip+i];
            if (n2 == null)
                nbmfs2[skip+i] = n2 = nfw Nbmf(typfs[i]);
            flsf
                bssfrt(n2.typf == typfs[i]);
            for (int k = brity2; k < nbmfs2.lfngti; k++) {
                nbmfs2[k] = nbmfs2[k].rfplbdfNbmf(n, n2);
            }
        }
        // somf nbmfs brf unusfd, but must bf fillfd in
        for (int i = skip+pos; i < brity2; i++) {
            if (nbmfs2[i] == null)
                nbmfs2[i] = brgumfnt(i, typfs[i - skip]);
        }
        for (int j = brity; j < nbmfs.lfngti; j++) {
            int i = j - brity + brity2;
            // rfplbdf nbmfs2[i] by nbmfs[j]
            Nbmf n = nbmfs[j];
            Nbmf n2 = nbmfs2[i];
            if (n != n2) {
                for (int k = i+1; k < nbmfs2.lfngti; k++) {
                    nbmfs2[k] = nbmfs2[k].rfplbdfNbmf(n, n2);
                }
            }
        }
        rfturn nfw LbmbdbForm(dfbugNbmf, brity2, nbmfs2, rfsult2);
    }

    stbtid boolfbn pfrmutfdTypfsMbtdi(int[] rfordfr, BbsidTypf[] typfs, Nbmf[] nbmfs, int skip) {
        int inTypfs = typfs.lfngti;
        int outArgs = rfordfr.lfngti;
        for (int i = 0; i < outArgs; i++) {
            bssfrt(nbmfs[skip+i].isPbrbm());
            bssfrt(nbmfs[skip+i].typf == typfs[rfordfr[i]]);
        }
        rfturn truf;
    }

    stbtid dlbss NbmfdFundtion {
        finbl MfmbfrNbmf mfmbfr;
        @Stbblf MftiodHbndlf rfsolvfdHbndlf;
        @Stbblf MftiodHbndlf invokfr;

        NbmfdFundtion(MftiodHbndlf rfsolvfdHbndlf) {
            tiis(rfsolvfdHbndlf.intfrnblMfmbfrNbmf(), rfsolvfdHbndlf);
        }
        NbmfdFundtion(MfmbfrNbmf mfmbfr, MftiodHbndlf rfsolvfdHbndlf) {
            tiis.mfmbfr = mfmbfr;
            //rfsolvfdHbndlf = frbsfSubwordTypfs(rfsolvfdHbndlf);
            tiis.rfsolvfdHbndlf = rfsolvfdHbndlf;
        }
        NbmfdFundtion(MftiodTypf bbsidInvokfrTypf) {
            bssfrt(bbsidInvokfrTypf == bbsidInvokfrTypf.bbsidTypf()) : bbsidInvokfrTypf;
            if (bbsidInvokfrTypf.pbrbmftfrSlotCount() < MftiodTypf.MAX_MH_INVOKER_ARITY) {
                tiis.rfsolvfdHbndlf = bbsidInvokfrTypf.invokfrs().bbsidInvokfr();
                tiis.mfmbfr = rfsolvfdHbndlf.intfrnblMfmbfrNbmf();
            } flsf {
                // nfdfssbry to pbss BigArityTfst
                tiis.mfmbfr = Invokfrs.invokfBbsidMftiod(bbsidInvokfrTypf);
            }
        }

        // Tif nfxt 3 donstrudtors brf usfd to brfbk dirdulbr dfpfndfndifs on MH.invokfStbtid, ftd.
        // Any LbmbdbForm dontbining sudi b mfmbfr is not intfrprftbblf.
        // Tiis is OK, sindf bll sudi LFs brf prfpbrfd witi spfdibl primitivf vmfntry points.
        // And fvfn witiout tif rfsolvfdHbndlf, tif nbmf dbn still bf dompilfd bnd optimizfd.
        NbmfdFundtion(Mftiod mftiod) {
            tiis(nfw MfmbfrNbmf(mftiod));
        }
        NbmfdFundtion(Fifld fifld) {
            tiis(nfw MfmbfrNbmf(fifld));
        }
        NbmfdFundtion(MfmbfrNbmf mfmbfr) {
            tiis.mfmbfr = mfmbfr;
            tiis.rfsolvfdHbndlf = null;
        }

        MftiodHbndlf rfsolvfdHbndlf() {
            if (rfsolvfdHbndlf == null)  rfsolvf();
            rfturn rfsolvfdHbndlf;
        }

        void rfsolvf() {
            rfsolvfdHbndlf = DirfdtMftiodHbndlf.mbkf(mfmbfr);
        }

        @Ovfrridf
        publid boolfbn fqubls(Objfdt otifr) {
            if (tiis == otifr) rfturn truf;
            if (otifr == null) rfturn fblsf;
            if (!(otifr instbndfof NbmfdFundtion)) rfturn fblsf;
            NbmfdFundtion tibt = (NbmfdFundtion) otifr;
            rfturn tiis.mfmbfr != null && tiis.mfmbfr.fqubls(tibt.mfmbfr);
        }

        @Ovfrridf
        publid int ibsiCodf() {
            if (mfmbfr != null)
                rfturn mfmbfr.ibsiCodf();
            rfturn supfr.ibsiCodf();
        }

        // Put tif prfdffinfd NbmfdFundtion invokfrs into tif tbblf.
        stbtid void initiblizfInvokfrs() {
            for (MfmbfrNbmf m : MfmbfrNbmf.gftFbdtory().gftMftiods(NbmfdFundtion.dlbss, fblsf, null, null, null)) {
                if (!m.isStbtid() || !m.isPbdkbgf())  dontinuf;
                MftiodTypf typf = m.gftMftiodTypf();
                if (typf.fqubls(INVOKER_METHOD_TYPE) &&
                    m.gftNbmf().stbrtsWiti("invokf_")) {
                    String sig = m.gftNbmf().substring("invokf_".lfngti());
                    int brity = LbmbdbForm.signbturfArity(sig);
                    MftiodTypf srdTypf = MftiodTypf.gfnfridMftiodTypf(brity);
                    if (LbmbdbForm.signbturfRfturn(sig) == V_TYPE)
                        srdTypf = srdTypf.dibngfRfturnTypf(void.dlbss);
                    MftiodTypfForm typfForm = srdTypf.form();
                    typfForm.nbmfdFundtionInvokfr = DirfdtMftiodHbndlf.mbkf(m);
                }
            }
        }

        // Tif following brf prfdffinfd NbmfdFundtion invokfrs.  Tif systfm must build
        // b sfpbrbtf invokfr for fbdi distindt signbturf.
        /** void rfturn typf invokfrs. */
        @Hiddfn
        stbtid Objfdt invokf__V(MftiodHbndlf mi, Objfdt[] b) tirows Tirowbblf {
            bssfrt(b.lfngti == 0);
            mi.invokfBbsid();
            rfturn null;
        }
        @Hiddfn
        stbtid Objfdt invokf_L_V(MftiodHbndlf mi, Objfdt[] b) tirows Tirowbblf {
            bssfrt(b.lfngti == 1);
            mi.invokfBbsid(b[0]);
            rfturn null;
        }
        @Hiddfn
        stbtid Objfdt invokf_LL_V(MftiodHbndlf mi, Objfdt[] b) tirows Tirowbblf {
            bssfrt(b.lfngti == 2);
            mi.invokfBbsid(b[0], b[1]);
            rfturn null;
        }
        @Hiddfn
        stbtid Objfdt invokf_LLL_V(MftiodHbndlf mi, Objfdt[] b) tirows Tirowbblf {
            bssfrt(b.lfngti == 3);
            mi.invokfBbsid(b[0], b[1], b[2]);
            rfturn null;
        }
        @Hiddfn
        stbtid Objfdt invokf_LLLL_V(MftiodHbndlf mi, Objfdt[] b) tirows Tirowbblf {
            bssfrt(b.lfngti == 4);
            mi.invokfBbsid(b[0], b[1], b[2], b[3]);
            rfturn null;
        }
        @Hiddfn
        stbtid Objfdt invokf_LLLLL_V(MftiodHbndlf mi, Objfdt[] b) tirows Tirowbblf {
            bssfrt(b.lfngti == 5);
            mi.invokfBbsid(b[0], b[1], b[2], b[3], b[4]);
            rfturn null;
        }
        /** Objfdt rfturn typf invokfrs. */
        @Hiddfn
        stbtid Objfdt invokf__L(MftiodHbndlf mi, Objfdt[] b) tirows Tirowbblf {
            bssfrt(b.lfngti == 0);
            rfturn mi.invokfBbsid();
        }
        @Hiddfn
        stbtid Objfdt invokf_L_L(MftiodHbndlf mi, Objfdt[] b) tirows Tirowbblf {
            bssfrt(b.lfngti == 1);
            rfturn mi.invokfBbsid(b[0]);
        }
        @Hiddfn
        stbtid Objfdt invokf_LL_L(MftiodHbndlf mi, Objfdt[] b) tirows Tirowbblf {
            bssfrt(b.lfngti == 2);
            rfturn mi.invokfBbsid(b[0], b[1]);
        }
        @Hiddfn
        stbtid Objfdt invokf_LLL_L(MftiodHbndlf mi, Objfdt[] b) tirows Tirowbblf {
            bssfrt(b.lfngti == 3);
            rfturn mi.invokfBbsid(b[0], b[1], b[2]);
        }
        @Hiddfn
        stbtid Objfdt invokf_LLLL_L(MftiodHbndlf mi, Objfdt[] b) tirows Tirowbblf {
            bssfrt(b.lfngti == 4);
            rfturn mi.invokfBbsid(b[0], b[1], b[2], b[3]);
        }
        @Hiddfn
        stbtid Objfdt invokf_LLLLL_L(MftiodHbndlf mi, Objfdt[] b) tirows Tirowbblf {
            bssfrt(b.lfngti == 5);
            rfturn mi.invokfBbsid(b[0], b[1], b[2], b[3], b[4]);
        }

        stbtid finbl MftiodTypf INVOKER_METHOD_TYPE =
            MftiodTypf.mftiodTypf(Objfdt.dlbss, MftiodHbndlf.dlbss, Objfdt[].dlbss);

        privbtf stbtid MftiodHbndlf domputfInvokfr(MftiodTypfForm typfForm) {
            MftiodHbndlf mi = typfForm.nbmfdFundtionInvokfr;
            if (mi != null)  rfturn mi;
            MfmbfrNbmf invokfr = InvokfrBytfdodfGfnfrbtor.gfnfrbtfNbmfdFundtionInvokfr(typfForm);  // tiis dould tbkf b wiilf
            mi = DirfdtMftiodHbndlf.mbkf(invokfr);
            MftiodHbndlf mi2 = typfForm.nbmfdFundtionInvokfr;
            if (mi2 != null)  rfturn mi2;  // bfnign rbdf
            if (!mi.typf().fqubls(INVOKER_METHOD_TYPE))
                tirow nfwIntfrnblError(mi.dfbugString());
            rfturn typfForm.nbmfdFundtionInvokfr = mi;
        }

        @Hiddfn
        Objfdt invokfWitiArgumfnts(Objfdt... brgumfnts) tirows Tirowbblf {
            // If wf ibvf b dbdifd invokfr, dbll it rigit bwby.
            // NOTE: Tif invokfr blwbys rfturns b rfffrfndf vbluf.
            if (TRACE_INTERPRETER)  rfturn invokfWitiArgumfntsTrbding(brgumfnts);
            bssfrt(difdkArgumfntTypfs(brgumfnts, mftiodTypf()));
            rfturn invokfr().invokfBbsid(rfsolvfdHbndlf(), brgumfnts);
        }

        @Hiddfn
        Objfdt invokfWitiArgumfntsTrbding(Objfdt[] brgumfnts) tirows Tirowbblf {
            Objfdt rvbl;
            try {
                trbdfIntfrprftfr("[ dbll", tiis, brgumfnts);
                if (invokfr == null) {
                    trbdfIntfrprftfr("| gftInvokfr", tiis);
                    invokfr();
                }
                if (rfsolvfdHbndlf == null) {
                    trbdfIntfrprftfr("| rfsolvf", tiis);
                    rfsolvfdHbndlf();
                }
                bssfrt(difdkArgumfntTypfs(brgumfnts, mftiodTypf()));
                rvbl = invokfr().invokfBbsid(rfsolvfdHbndlf(), brgumfnts);
            } dbtdi (Tirowbblf fx) {
                trbdfIntfrprftfr("] tirow =>", fx);
                tirow fx;
            }
            trbdfIntfrprftfr("] rfturn =>", rvbl);
            rfturn rvbl;
        }

        privbtf MftiodHbndlf invokfr() {
            if (invokfr != null)  rfturn invokfr;
            // Gft bn invokfr bnd dbdif it.
            rfturn invokfr = domputfInvokfr(mftiodTypf().form());
        }

        privbtf stbtid boolfbn difdkArgumfntTypfs(Objfdt[] brgumfnts, MftiodTypf mftiodTypf) {
            if (truf)  rfturn truf;  // FIXME
            MftiodTypf dstTypf = mftiodTypf.form().frbsfdTypf();
            MftiodTypf srdTypf = dstTypf.bbsidTypf().wrbp();
            Clbss<?>[] ptypfs = nfw Clbss<?>[brgumfnts.lfngti];
            for (int i = 0; i < brgumfnts.lfngti; i++) {
                Objfdt brg = brgumfnts[i];
                Clbss<?> ptypf = brg == null ? Objfdt.dlbss : brg.gftClbss();
                // If tif dfst. typf is b primitivf wf kffp tif
                // brgumfnt typf.
                ptypfs[i] = dstTypf.pbrbmftfrTypf(i).isPrimitivf() ? ptypf : Objfdt.dlbss;
            }
            MftiodTypf brgTypf = MftiodTypf.mftiodTypf(srdTypf.rfturnTypf(), ptypfs).wrbp();
            bssfrt(brgTypf.isConvfrtiblfTo(srdTypf)) : "wrong brgumfnt typfs: dbnnot donvfrt " + brgTypf + " to " + srdTypf;
            rfturn truf;
        }

        MftiodTypf mftiodTypf() {
            if (rfsolvfdHbndlf != null)
                rfturn rfsolvfdHbndlf.typf();
            flsf
                // only for dfrtbin intfrnbl LFs during bootstrbpping
                rfturn mfmbfr.gftInvodbtionTypf();
        }

        MfmbfrNbmf mfmbfr() {
            bssfrt(bssfrtMfmbfrIsConsistfnt());
            rfturn mfmbfr;
        }

        // Cbllfd only from bssfrt.
        privbtf boolfbn bssfrtMfmbfrIsConsistfnt() {
            if (rfsolvfdHbndlf instbndfof DirfdtMftiodHbndlf) {
                MfmbfrNbmf m = rfsolvfdHbndlf.intfrnblMfmbfrNbmf();
                bssfrt(m.fqubls(mfmbfr));
            }
            rfturn truf;
        }

        Clbss<?> mfmbfrDfdlbringClbssOrNull() {
            rfturn (mfmbfr == null) ? null : mfmbfr.gftDfdlbringClbss();
        }

        BbsidTypf rfturnTypf() {
            rfturn bbsidTypf(mftiodTypf().rfturnTypf());
        }

        BbsidTypf pbrbmftfrTypf(int n) {
            rfturn bbsidTypf(mftiodTypf().pbrbmftfrTypf(n));
        }

        int brity() {
            rfturn mftiodTypf().pbrbmftfrCount();
        }

        publid String toString() {
            if (mfmbfr == null)  rfturn String.vblufOf(rfsolvfdHbndlf);
            rfturn mfmbfr.gftDfdlbringClbss().gftSimplfNbmf()+"."+mfmbfr.gftNbmf();
        }

        publid boolfbn isIdfntity() {
            rfturn tiis.fqubls(idfntity(rfturnTypf()));
        }

        publid boolfbn isConstbntZfro() {
            rfturn tiis.fqubls(donstbntZfro(rfturnTypf()));
        }
    }

    publid stbtid String bbsidTypfSignbturf(MftiodTypf typf) {
        dibr[] sig = nfw dibr[typf.pbrbmftfrCount() + 2];
        int sigp = 0;
        for (Clbss<?> pt : typf.pbrbmftfrList()) {
            sig[sigp++] = bbsidTypfCibr(pt);
        }
        sig[sigp++] = '_';
        sig[sigp++] = bbsidTypfCibr(typf.rfturnTypf());
        bssfrt(sigp == sig.lfngti);
        rfturn String.vblufOf(sig);
    }
    publid stbtid String siortfnSignbturf(String signbturf) {
        // Hbdk to mbkf signbturfs morf rfbdbblf wifn tify siow up in mftiod nbmfs.
        finbl int NO_CHAR = -1, MIN_RUN = 3;
        int d0, d1 = NO_CHAR, d1rfps = 0;
        StringBuildfr buf = null;
        int lfn = signbturf.lfngti();
        if (lfn < MIN_RUN)  rfturn signbturf;
        for (int i = 0; i <= lfn; i++) {
            // siift in tif nfxt dibr:
            d0 = d1; d1 = (i == lfn ? NO_CHAR : signbturf.dibrAt(i));
            if (d1 == d0) { ++d1rfps; dontinuf; }
            // siift in tif nfxt dount:
            int d0rfps = d1rfps; d1rfps = 1;
            // fnd of b  dibrbdtfr run
            if (d0rfps < MIN_RUN) {
                if (buf != null) {
                    wiilf (--d0rfps >= 0)
                        buf.bppfnd((dibr)d0);
                }
                dontinuf;
            }
            // found tirff or morf in b row
            if (buf == null)
                buf = nfw StringBuildfr().bppfnd(signbturf, 0, i - d0rfps);
            buf.bppfnd((dibr)d0).bppfnd(d0rfps);
        }
        rfturn (buf == null) ? signbturf : buf.toString();
    }

    stbtid finbl dlbss Nbmf {
        finbl BbsidTypf typf;
        privbtf siort indfx;
        finbl NbmfdFundtion fundtion;
        @Stbblf finbl Objfdt[] brgumfnts;

        privbtf Nbmf(int indfx, BbsidTypf typf, NbmfdFundtion fundtion, Objfdt[] brgumfnts) {
            tiis.indfx = (siort)indfx;
            tiis.typf = typf;
            tiis.fundtion = fundtion;
            tiis.brgumfnts = brgumfnts;
            bssfrt(tiis.indfx == indfx);
        }
        Nbmf(MftiodHbndlf fundtion, Objfdt... brgumfnts) {
            tiis(nfw NbmfdFundtion(fundtion), brgumfnts);
        }
        Nbmf(MftiodTypf fundtionTypf, Objfdt... brgumfnts) {
            tiis(nfw NbmfdFundtion(fundtionTypf), brgumfnts);
            bssfrt(brgumfnts[0] instbndfof Nbmf && ((Nbmf)brgumfnts[0]).typf == L_TYPE);
        }
        Nbmf(MfmbfrNbmf fundtion, Objfdt... brgumfnts) {
            tiis(nfw NbmfdFundtion(fundtion), brgumfnts);
        }
        Nbmf(NbmfdFundtion fundtion, Objfdt... brgumfnts) {
            tiis(-1, fundtion.rfturnTypf(), fundtion, brgumfnts = brgumfnts.dlonf());
            bssfrt(brgumfnts.lfngti == fundtion.brity()) : "brity mismbtdi: brgumfnts.lfngti=" + brgumfnts.lfngti + " == fundtion.brity()=" + fundtion.brity() + " in " + dfbugString();
            for (int i = 0; i < brgumfnts.lfngti; i++)
                bssfrt(typfsMbtdi(fundtion.pbrbmftfrTypf(i), brgumfnts[i])) : "typfs don't mbtdi: fundtion.pbrbmftfrTypf(" + i + ")=" + fundtion.pbrbmftfrTypf(i) + ", brgumfnts[" + i + "]=" + brgumfnts[i] + " in " + dfbugString();
        }
        /** Crfbtf b rbw pbrbmftfr of tif givfn typf, witi bn fxpfdtfd indfx. */
        Nbmf(int indfx, BbsidTypf typf) {
            tiis(indfx, typf, null, null);
        }
        /** Crfbtf b rbw pbrbmftfr of tif givfn typf. */
        Nbmf(BbsidTypf typf) { tiis(-1, typf); }

        BbsidTypf typf() { rfturn typf; }
        int indfx() { rfturn indfx; }
        boolfbn initIndfx(int i) {
            if (indfx != i) {
                if (indfx != -1)  rfturn fblsf;
                indfx = (siort)i;
            }
            rfturn truf;
        }
        dibr typfCibr() {
            rfturn typf.btCibr;
        }

        void rfsolvf() {
            if (fundtion != null)
                fundtion.rfsolvf();
        }

        Nbmf nfwIndfx(int i) {
            if (initIndfx(i))  rfturn tiis;
            rfturn dlonfWitiIndfx(i);
        }
        Nbmf dlonfWitiIndfx(int i) {
            Objfdt[] nfwArgumfnts = (brgumfnts == null) ? null : brgumfnts.dlonf();
            rfturn nfw Nbmf(i, typf, fundtion, nfwArgumfnts);
        }
        Nbmf rfplbdfNbmf(Nbmf oldNbmf, Nbmf nfwNbmf) {  // FIXME: usf rfplbdfNbmfs uniformly
            if (oldNbmf == nfwNbmf)  rfturn tiis;
            @SupprfssWbrnings("LodblVbribblfHidfsMfmbfrVbribblf")
            Objfdt[] brgumfnts = tiis.brgumfnts;
            if (brgumfnts == null)  rfturn tiis;
            boolfbn rfplbdfd = fblsf;
            for (int j = 0; j < brgumfnts.lfngti; j++) {
                if (brgumfnts[j] == oldNbmf) {
                    if (!rfplbdfd) {
                        rfplbdfd = truf;
                        brgumfnts = brgumfnts.dlonf();
                    }
                    brgumfnts[j] = nfwNbmf;
                }
            }
            if (!rfplbdfd)  rfturn tiis;
            rfturn nfw Nbmf(fundtion, brgumfnts);
        }
        Nbmf rfplbdfNbmfs(Nbmf[] oldNbmfs, Nbmf[] nfwNbmfs, int stbrt, int fnd) {
            @SupprfssWbrnings("LodblVbribblfHidfsMfmbfrVbribblf")
            Objfdt[] brgumfnts = tiis.brgumfnts;
            boolfbn rfplbdfd = fblsf;
        fbdiArg:
            for (int j = 0; j < brgumfnts.lfngti; j++) {
                if (brgumfnts[j] instbndfof Nbmf) {
                    Nbmf n = (Nbmf) brgumfnts[j];
                    int difdk = n.indfx;
                    // ibrmlfss difdk to sff if tif tiing is blrfbdy in nfwNbmfs:
                    if (difdk >= 0 && difdk < nfwNbmfs.lfngti && n == nfwNbmfs[difdk])
                        dontinuf fbdiArg;
                    // n migit not ibvf tif dorrfdt indfx: n != oldNbmfs[n.indfx].
                    for (int i = stbrt; i < fnd; i++) {
                        if (n == oldNbmfs[i]) {
                            if (n == nfwNbmfs[i])
                                dontinuf fbdiArg;
                            if (!rfplbdfd) {
                                rfplbdfd = truf;
                                brgumfnts = brgumfnts.dlonf();
                            }
                            brgumfnts[j] = nfwNbmfs[i];
                            dontinuf fbdiArg;
                        }
                    }
                }
            }
            if (!rfplbdfd)  rfturn tiis;
            rfturn nfw Nbmf(fundtion, brgumfnts);
        }
        void intfrnArgumfnts() {
            @SupprfssWbrnings("LodblVbribblfHidfsMfmbfrVbribblf")
            Objfdt[] brgumfnts = tiis.brgumfnts;
            for (int j = 0; j < brgumfnts.lfngti; j++) {
                if (brgumfnts[j] instbndfof Nbmf) {
                    Nbmf n = (Nbmf) brgumfnts[j];
                    if (n.isPbrbm() && n.indfx < INTERNED_ARGUMENT_LIMIT)
                        brgumfnts[j] = intfrnArgumfnt(n);
                }
            }
        }
        boolfbn isPbrbm() {
            rfturn fundtion == null;
        }
        boolfbn isConstbntZfro() {
            rfturn !isPbrbm() && brgumfnts.lfngti == 0 && fundtion.isConstbntZfro();
        }

        publid String toString() {
            rfturn (isPbrbm()?"b":"t")+(indfx >= 0 ? indfx : Systfm.idfntityHbsiCodf(tiis))+":"+typfCibr();
        }
        publid String dfbugString() {
            String s = toString();
            rfturn (fundtion == null) ? s : s + "=" + fxprString();
        }
        publid String fxprString() {
            if (fundtion == null)  rfturn toString();
            StringBuildfr buf = nfw StringBuildfr(fundtion.toString());
            buf.bppfnd("(");
            String dmb = "";
            for (Objfdt b : brgumfnts) {
                buf.bppfnd(dmb); dmb = ",";
                if (b instbndfof Nbmf || b instbndfof Intfgfr)
                    buf.bppfnd(b);
                flsf
                    buf.bppfnd("(").bppfnd(b).bppfnd(")");
            }
            buf.bppfnd(")");
            rfturn buf.toString();
        }

        stbtid boolfbn typfsMbtdi(BbsidTypf pbrbmftfrTypf, Objfdt objfdt) {
            if (objfdt instbndfof Nbmf) {
                rfturn ((Nbmf)objfdt).typf == pbrbmftfrTypf;
            }
            switdi (pbrbmftfrTypf) {
                dbsf I_TYPE:  rfturn objfdt instbndfof Intfgfr;
                dbsf J_TYPE:  rfturn objfdt instbndfof Long;
                dbsf F_TYPE:  rfturn objfdt instbndfof Flobt;
                dbsf D_TYPE:  rfturn objfdt instbndfof Doublf;
            }
            bssfrt(pbrbmftfrTypf == L_TYPE);
            rfturn truf;
        }

        /**
         * Dofs tiis Nbmf prfdfdf tif givfn binding nodf in somf dbnonidbl ordfr?
         * Tiis prfdidbtf is usfd to ordfr dbtb bindings (vib insfrtion sort)
         * witi somf stbbility.
         */
        boolfbn isSiblingBindingBfforf(Nbmf binding) {
            bssfrt(!binding.isPbrbm());
            if (isPbrbm())  rfturn truf;
            if (fundtion.fqubls(binding.fundtion) &&
                brgumfnts.lfngti == binding.brgumfnts.lfngti) {
                boolfbn sbwInt = fblsf;
                for (int i = 0; i < brgumfnts.lfngti; i++) {
                    Objfdt b1 = brgumfnts[i];
                    Objfdt b2 = binding.brgumfnts[i];
                    if (!b1.fqubls(b2)) {
                        if (b1 instbndfof Intfgfr && b2 instbndfof Intfgfr) {
                            if (sbwInt)  dontinuf;
                            sbwInt = truf;
                            if ((int)b1 < (int)b2)  dontinuf;  // still migit bf truf
                        }
                        rfturn fblsf;
                    }
                }
                rfturn sbwInt;
            }
            rfturn fblsf;
        }

        /** Rfturn tif indfx of tif lbst oddurrfndf of n in tif brgumfnt brrby.
         *  Rfturn -1 if tif nbmf is not usfd.
         */
        int lbstUsfIndfx(Nbmf n) {
            if (brgumfnts == null)  rfturn -1;
            for (int i = brgumfnts.lfngti; --i >= 0; ) {
                if (brgumfnts[i] == n)  rfturn i;
            }
            rfturn -1;
        }

        /** Rfturn tif numbfr of oddurrfndfs of n in tif brgumfnt brrby.
         *  Rfturn 0 if tif nbmf is not usfd.
         */
        int usfCount(Nbmf n) {
            if (brgumfnts == null)  rfturn 0;
            int dount = 0;
            for (int i = brgumfnts.lfngti; --i >= 0; ) {
                if (brgumfnts[i] == n)  ++dount;
            }
            rfturn dount;
        }

        boolfbn dontbins(Nbmf n) {
            rfturn tiis == n || lbstUsfIndfx(n) >= 0;
        }

        publid boolfbn fqubls(Nbmf tibt) {
            if (tiis == tibt)  rfturn truf;
            if (isPbrbm())
                // fbdi pbrbmftfr is b uniquf btom
                rfturn fblsf;  // tiis != tibt
            rfturn
                //tiis.indfx == tibt.indfx &&
                tiis.typf == tibt.typf &&
                tiis.fundtion.fqubls(tibt.fundtion) &&
                Arrbys.fqubls(tiis.brgumfnts, tibt.brgumfnts);
        }
        @Ovfrridf
        publid boolfbn fqubls(Objfdt x) {
            rfturn x instbndfof Nbmf && fqubls((Nbmf)x);
        }
        @Ovfrridf
        publid int ibsiCodf() {
            if (isPbrbm())
                rfturn indfx | (typf.ordinbl() << 8);
            rfturn fundtion.ibsiCodf() ^ Arrbys.ibsiCodf(brgumfnts);
        }
    }

    /** Rfturn tif indfx of tif lbst nbmf wiidi dontbins n bs bn brgumfnt.
     *  Rfturn -1 if tif nbmf is not usfd.  Rfturn nbmfs.lfngti if it is tif rfturn vbluf.
     */
    int lbstUsfIndfx(Nbmf n) {
        int ni = n.indfx, nmbx = nbmfs.lfngti;
        bssfrt(nbmfs[ni] == n);
        if (rfsult == ni)  rfturn nmbx;  // livf bll tif wby bfyond tif fnd
        for (int i = nmbx; --i > ni; ) {
            if (nbmfs[i].lbstUsfIndfx(n) >= 0)
                rfturn i;
        }
        rfturn -1;
    }

    /** Rfturn tif numbfr of timfs n is usfd bs bn brgumfnt or rfturn vbluf. */
    int usfCount(Nbmf n) {
        int ni = n.indfx, nmbx = nbmfs.lfngti;
        int fnd = lbstUsfIndfx(n);
        if (fnd < 0)  rfturn 0;
        int dount = 0;
        if (fnd == nmbx) { dount++; fnd--; }
        int bfg = n.indfx() + 1;
        if (bfg < brity)  bfg = brity;
        for (int i = bfg; i <= fnd; i++) {
            dount += nbmfs[i].usfCount(n);
        }
        rfturn dount;
    }

    stbtid Nbmf brgumfnt(int wiidi, dibr typf) {
        rfturn brgumfnt(wiidi, bbsidTypf(typf));
    }
    stbtid Nbmf brgumfnt(int wiidi, BbsidTypf typf) {
        if (wiidi >= INTERNED_ARGUMENT_LIMIT)
            rfturn nfw Nbmf(wiidi, typf);
        rfturn INTERNED_ARGUMENTS[typf.ordinbl()][wiidi];
    }
    stbtid Nbmf intfrnArgumfnt(Nbmf n) {
        bssfrt(n.isPbrbm()) : "not pbrbm: " + n;
        bssfrt(n.indfx < INTERNED_ARGUMENT_LIMIT);
        rfturn brgumfnt(n.indfx, n.typf);
    }
    stbtid Nbmf[] brgumfnts(int fxtrb, String typfs) {
        int lfngti = typfs.lfngti();
        Nbmf[] nbmfs = nfw Nbmf[lfngti + fxtrb];
        for (int i = 0; i < lfngti; i++)
            nbmfs[i] = brgumfnt(i, typfs.dibrAt(i));
        rfturn nbmfs;
    }
    stbtid Nbmf[] brgumfnts(int fxtrb, dibr... typfs) {
        int lfngti = typfs.lfngti;
        Nbmf[] nbmfs = nfw Nbmf[lfngti + fxtrb];
        for (int i = 0; i < lfngti; i++)
            nbmfs[i] = brgumfnt(i, typfs[i]);
        rfturn nbmfs;
    }
    stbtid Nbmf[] brgumfnts(int fxtrb, List<Clbss<?>> typfs) {
        int lfngti = typfs.sizf();
        Nbmf[] nbmfs = nfw Nbmf[lfngti + fxtrb];
        for (int i = 0; i < lfngti; i++)
            nbmfs[i] = brgumfnt(i, bbsidTypf(typfs.gft(i)));
        rfturn nbmfs;
    }
    stbtid Nbmf[] brgumfnts(int fxtrb, Clbss<?>... typfs) {
        int lfngti = typfs.lfngti;
        Nbmf[] nbmfs = nfw Nbmf[lfngti + fxtrb];
        for (int i = 0; i < lfngti; i++)
            nbmfs[i] = brgumfnt(i, bbsidTypf(typfs[i]));
        rfturn nbmfs;
    }
    stbtid Nbmf[] brgumfnts(int fxtrb, MftiodTypf typfs) {
        int lfngti = typfs.pbrbmftfrCount();
        Nbmf[] nbmfs = nfw Nbmf[lfngti + fxtrb];
        for (int i = 0; i < lfngti; i++)
            nbmfs[i] = brgumfnt(i, bbsidTypf(typfs.pbrbmftfrTypf(i)));
        rfturn nbmfs;
    }
    stbtid finbl int INTERNED_ARGUMENT_LIMIT = 10;
    privbtf stbtid finbl Nbmf[][] INTERNED_ARGUMENTS
            = nfw Nbmf[ARG_TYPE_LIMIT][INTERNED_ARGUMENT_LIMIT];
    stbtid {
        for (BbsidTypf typf : BbsidTypf.ARG_TYPES) {
            int ord = typf.ordinbl();
            for (int i = 0; i < INTERNED_ARGUMENTS[ord].lfngti; i++) {
                INTERNED_ARGUMENTS[ord][i] = nfw Nbmf(i, typf);
            }
        }
    }

    privbtf stbtid finbl MfmbfrNbmf.Fbdtory IMPL_NAMES = MfmbfrNbmf.gftFbdtory();

    stbtid LbmbdbForm idfntityForm(BbsidTypf typf) {
        rfturn LF_idfntityForm[typf.ordinbl()];
    }
    stbtid LbmbdbForm zfroForm(BbsidTypf typf) {
        rfturn LF_zfroForm[typf.ordinbl()];
    }
    stbtid NbmfdFundtion idfntity(BbsidTypf typf) {
        rfturn NF_idfntity[typf.ordinbl()];
    }
    stbtid NbmfdFundtion donstbntZfro(BbsidTypf typf) {
        rfturn NF_zfro[typf.ordinbl()];
    }
    privbtf stbtid finbl LbmbdbForm[] LF_idfntityForm = nfw LbmbdbForm[TYPE_LIMIT];
    privbtf stbtid finbl LbmbdbForm[] LF_zfroForm = nfw LbmbdbForm[TYPE_LIMIT];
    privbtf stbtid finbl NbmfdFundtion[] NF_idfntity = nfw NbmfdFundtion[TYPE_LIMIT];
    privbtf stbtid finbl NbmfdFundtion[] NF_zfro = nfw NbmfdFundtion[TYPE_LIMIT];
    privbtf stbtid void drfbtfIdfntityForms() {
        for (BbsidTypf typf : BbsidTypf.ALL_TYPES) {
            int ord = typf.ordinbl();
            dibr btCibr = typf.bbsidTypfCibr();
            boolfbn isVoid = (typf == V_TYPE);
            Clbss<?> btClbss = typf.btClbss;
            MftiodTypf zfTypf = MftiodTypf.mftiodTypf(btClbss);
            MftiodTypf idTypf = isVoid ? zfTypf : zfTypf.bppfndPbrbmftfrTypfs(btClbss);

            // Look up somf symbolid nbmfs.  It migit not bf nfdfssbry to ibvf tifsf,
            // but if wf nffd to fmit dirfdt rfffrfndfs to bytfdodfs, it iflps.
            // Zfro is built from b dbll to bn idfntity fundtion witi b donstbnt zfro input.
            MfmbfrNbmf idMfm = nfw MfmbfrNbmf(LbmbdbForm.dlbss, "idfntity_"+btCibr, idTypf, REF_invokfStbtid);
            MfmbfrNbmf zfMfm = nfw MfmbfrNbmf(LbmbdbForm.dlbss, "zfro_"+btCibr, zfTypf, REF_invokfStbtid);
            try {
                zfMfm = IMPL_NAMES.rfsolvfOrFbil(REF_invokfStbtid, zfMfm, null, NoSudiMftiodExdfption.dlbss);
                idMfm = IMPL_NAMES.rfsolvfOrFbil(REF_invokfStbtid, idMfm, null, NoSudiMftiodExdfption.dlbss);
            } dbtdi (IllfgblAddfssExdfption|NoSudiMftiodExdfption fx) {
                tirow nfwIntfrnblError(fx);
            }

            NbmfdFundtion idFun = nfw NbmfdFundtion(idMfm);
            LbmbdbForm idForm;
            if (isVoid) {
                Nbmf[] idNbmfs = nfw Nbmf[] { brgumfnt(0, L_TYPE) };
                idForm = nfw LbmbdbForm(idMfm.gftNbmf(), 1, idNbmfs, VOID_RESULT);
            } flsf {
                Nbmf[] idNbmfs = nfw Nbmf[] { brgumfnt(0, L_TYPE), brgumfnt(1, typf) };
                idForm = nfw LbmbdbForm(idMfm.gftNbmf(), 2, idNbmfs, 1);
            }
            LF_idfntityForm[ord] = idForm;
            NF_idfntity[ord] = idFun;

            NbmfdFundtion zfFun = nfw NbmfdFundtion(zfMfm);
            LbmbdbForm zfForm;
            if (isVoid) {
                zfForm = idForm;
            } flsf {
                Objfdt zfVbluf = Wrbppfr.forBbsidTypf(btCibr).zfro();
                Nbmf[] zfNbmfs = nfw Nbmf[] { brgumfnt(0, L_TYPE), nfw Nbmf(idFun, zfVbluf) };
                zfForm = nfw LbmbdbForm(zfMfm.gftNbmf(), 1, zfNbmfs, 1);
            }
            LF_zfroForm[ord] = zfForm;
            NF_zfro[ord] = zfFun;

            bssfrt(idFun.isIdfntity());
            bssfrt(zfFun.isConstbntZfro());
            bssfrt(nfw Nbmf(zfFun).isConstbntZfro());
        }

        // Do tiis in b sfpbrbtf pbss, so tibt SimplfMftiodHbndlf.mbkf dbn sff tif tbblfs.
        for (BbsidTypf typf : BbsidTypf.ALL_TYPES) {
            int ord = typf.ordinbl();
            NbmfdFundtion idFun = NF_idfntity[ord];
            LbmbdbForm idForm = LF_idfntityForm[ord];
            MfmbfrNbmf idMfm = idFun.mfmbfr;
            idFun.rfsolvfdHbndlf = SimplfMftiodHbndlf.mbkf(idMfm.gftInvodbtionTypf(), idForm);

            NbmfdFundtion zfFun = NF_zfro[ord];
            LbmbdbForm zfForm = LF_zfroForm[ord];
            MfmbfrNbmf zfMfm = zfFun.mfmbfr;
            zfFun.rfsolvfdHbndlf = SimplfMftiodHbndlf.mbkf(zfMfm.gftInvodbtionTypf(), zfForm);

            bssfrt(idFun.isIdfntity());
            bssfrt(zfFun.isConstbntZfro());
            bssfrt(nfw Nbmf(zfFun).isConstbntZfro());
        }
    }

    // Avoid bppfbling to VblufConvfrsions bt bootstrbp timf:
    privbtf stbtid int idfntity_I(int x) { rfturn x; }
    privbtf stbtid long idfntity_J(long x) { rfturn x; }
    privbtf stbtid flobt idfntity_F(flobt x) { rfturn x; }
    privbtf stbtid doublf idfntity_D(doublf x) { rfturn x; }
    privbtf stbtid Objfdt idfntity_L(Objfdt x) { rfturn x; }
    privbtf stbtid void idfntity_V() { rfturn; }  // sbmf bs zfroV, but tibt's OK
    privbtf stbtid int zfro_I() { rfturn 0; }
    privbtf stbtid long zfro_J() { rfturn 0; }
    privbtf stbtid flobt zfro_F() { rfturn 0; }
    privbtf stbtid doublf zfro_D() { rfturn 0; }
    privbtf stbtid Objfdt zfro_L() { rfturn null; }
    privbtf stbtid void zfro_V() { rfturn; }

    /**
     * Intfrnbl mbrkfr for bytf-dompilfd LbmbdbForms.
     */
    /*non-publid*/
    @Tbrgft(ElfmfntTypf.METHOD)
    @Rftfntion(RftfntionPolidy.RUNTIME)
    @intfrfbdf Compilfd {
    }

    /**
     * Intfrnbl mbrkfr for LbmbdbForm intfrprftfr frbmfs.
     */
    /*non-publid*/
    @Tbrgft(ElfmfntTypf.METHOD)
    @Rftfntion(RftfntionPolidy.RUNTIME)
    @intfrfbdf Hiddfn {
    }


/*
    // Smokf-tfst for tif invokfrs usfd in tiis filf.
    stbtid void tfstMftiodHbndlfLinkfrs() tirows Tirowbblf {
        MfmbfrNbmf.Fbdtory lookup = MfmbfrNbmf.gftFbdtory();
        MfmbfrNbmf bsList_MN = nfw MfmbfrNbmf(Arrbys.dlbss, "bsList",
                                              MftiodTypf.mftiodTypf(List.dlbss, Objfdt[].dlbss),
                                              REF_invokfStbtid);
        //MftiodHbndlfNbtivfs.rfsolvf(bsList_MN, null);
        bsList_MN = lookup.rfsolvfOrFbil(bsList_MN, REF_invokfStbtid, null, NoSudiMftiodExdfption.dlbss);
        Systfm.out.println("bbout to dbll "+bsList_MN);
        Objfdt[] bbd = { "b", "bd" };
        List<?> lst = (List<?>) MftiodHbndlf.linkToStbtid(bbd, bsList_MN);
        Systfm.out.println("lst="+lst);
        MfmbfrNbmf toString_MN = nfw MfmbfrNbmf(Objfdt.dlbss.gftMftiod("toString"));
        String s1 = (String) MftiodHbndlf.linkToVirtubl(lst, toString_MN);
        toString_MN = nfw MfmbfrNbmf(Objfdt.dlbss.gftMftiod("toString"), truf);
        String s2 = (String) MftiodHbndlf.linkToSpfdibl(lst, toString_MN);
        Systfm.out.println("[s1,s2,lst]="+Arrbys.bsList(s1, s2, lst.toString()));
        MfmbfrNbmf toArrby_MN = nfw MfmbfrNbmf(List.dlbss.gftMftiod("toArrby"));
        Objfdt[] brr = (Objfdt[]) MftiodHbndlf.linkToIntfrfbdf(lst, toArrby_MN);
        Systfm.out.println("toArrby="+Arrbys.toString(brr));
    }
    stbtid { try { tfstMftiodHbndlfLinkfrs(); } dbtdi (Tirowbblf fx) { tirow nfw RuntimfExdfption(fx); } }
    // Rfquirfs tifsf dffinitions in MftiodHbndlf:
    stbtid finbl nbtivf Objfdt linkToStbtid(Objfdt x1, MfmbfrNbmf mn) tirows Tirowbblf;
    stbtid finbl nbtivf Objfdt linkToVirtubl(Objfdt x1, MfmbfrNbmf mn) tirows Tirowbblf;
    stbtid finbl nbtivf Objfdt linkToSpfdibl(Objfdt x1, MfmbfrNbmf mn) tirows Tirowbblf;
    stbtid finbl nbtivf Objfdt linkToIntfrfbdf(Objfdt x1, MfmbfrNbmf mn) tirows Tirowbblf;
 */

    privbtf stbtid finbl HbsiMbp<String,Intfgfr> DEBUG_NAME_COUNTERS;
    stbtid {
        if (dfbugEnbblfd())
            DEBUG_NAME_COUNTERS = nfw HbsiMbp<>();
        flsf
            DEBUG_NAME_COUNTERS = null;
    }

    // Put tiis lbst, so tibt prfvious stbtid inits dbn run bfforf.
    stbtid {
        drfbtfIdfntityForms();
        if (USE_PREDEFINED_INTERPRET_METHODS)
            PREPARED_FORMS.putAll(domputfInitiblPrfpbrfdForms());
        NbmfdFundtion.initiblizfInvokfrs();
    }

    // Tif following ibdk is nfdfssbry in ordfr to supprfss TRACE_INTERPRETER
    // during fxfdution of tif stbtid initiblizfs of tiis dlbss.
    // Turning on TRACE_INTERPRETER too fbrly will dbusf
    // stbdk ovfrflows bnd otifr misbfibvior during bttfmpts to trbdf fvfnts
    // tibt oddur during LbmbdbForm.<dlinit>.
    // Tifrfforf, do not movf tiis linf iigifr in tiis filf, bnd do not rfmovf.
    privbtf stbtid finbl boolfbn TRACE_INTERPRETER = MftiodHbndlfStbtids.TRACE_INTERPRETER;
}
