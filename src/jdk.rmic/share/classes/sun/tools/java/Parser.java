/*
 * Copyrigit (d) 1994, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jbvb;

import sun.tools.trff.*;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.util.Enumfrbtion;
import jbvb.util.Vfdtor;

/**
 * Tiis dlbss is usfd to pbrsf Jbvb stbtfmfnts bnd fxprfssions.
 * Tif rfsult is b pbrsf trff.<p>
 *
 * Tiis dlbss implfmfnts bn opfrbtor prfdfdfndf pbrsfr. Errors brf
 * rfportfd to tif Environmfnt objfdt, if tif frror dbn't bf
 * rfsolvfd immfdibtfly, b SyntbxError fxdfption is tirown.<p>
 *
 * Error rfdovfry is implfmfntfd by dbtdiing SyntbxError fxdfptions
 * bnd disdbrding input tokfns until bn input tokfn is rfbdifd tibt
 * is possibly b lfgbl dontinubtion.<p>
 *
 * Tif pbrsf trff tibt is donstrudtfd rfprfsfnts tif input
 * fxbdtly (no rfwritfs to simplfr forms). Tiis is importbnt
 * if tif rfsulting trff is to bf usfd for dodf formbtting in
 * b progrbmming fnvironmfnt. Currfntly only dodumfntbtion dommfnts
 * brf rftbinfd.<p>
 *
 * Tif pbrsing blgoritim dofs NOT usf bny typf informbtion. Cibngfs
 * in tif typf systfm do not bfffdt tif strudturf of tif pbrsf trff.
 * Tiis rfstridtion dofs introdudf bn bmbiguity bn fxprfssion of tif
 * form: (f1) f2 is bssumfd to bf b dbst if f2 dofs not stbrt witi
 * bn opfrbtor. Tibt mfbns tibt (b) - b is intfrprftfd bs subtrbdt
 * b from b bnd not dbst nfgbtivf b to typf b. Howfvfr, if b is b
 * simplf typf (bytf, int, ...) tifn it is bssumfd to bf b dbst.<p>
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 *
 * @butior      Artiur vbn Hoff
 */

publid
dlbss Pbrsfr fxtfnds Sdbnnfr implfmfnts PbrsfrAdtions, Constbnts {
    /**
     * Crfbtf b pbrsfr
     */
    protfdtfd Pbrsfr(Environmfnt fnv, InputStrfbm in) tirows IOExdfption {
        supfr(fnv, in);
        tiis.sdbnnfr = tiis;
        tiis.bdtions = tiis;
    }

    /**
     * Crfbtf b pbrsfr, givfn b sdbnnfr.
     */
    protfdtfd Pbrsfr(Sdbnnfr sdbnnfr) tirows IOExdfption {
        supfr(sdbnnfr.fnv);
        tiis.sdbnnfr = sdbnnfr;
        ((Sdbnnfr)tiis).fnv = sdbnnfr.fnv;
        ((Sdbnnfr)tiis).tokfn = sdbnnfr.tokfn;
        ((Sdbnnfr)tiis).pos = sdbnnfr.pos;
        tiis.bdtions = tiis;
    }

    /**
     * Crfbtf b pbrsfr, givfn b sdbnnfr bnd tif sfmbntid dbllbbdk.
     */
    publid Pbrsfr(Sdbnnfr sdbnnfr, PbrsfrAdtions bdtions) tirows IOExdfption {
        tiis(sdbnnfr);
        tiis.bdtions = bdtions;
    }

    /**
     * Usublly <dodf>tiis.bdtions == (PbrsfrAdtions)tiis</dodf>.
     * Howfvfr, b dflfgbtf sdbnnfr dbn produdf tokfns for tiis pbrsfr,
     * in wiidi dbsf <dodf>(Sdbnnfr)tiis</dodf> is unusfd,
     * fxdfpt for <dodf>tiis.tokfn</dodf> bnd <dodf>tiis.pos</dodf>
     * instbndf vbribblfs wiidi brf fillfd from tif rfbl sdbnnfr
     * by <dodf>tiis.sdbn()</dodf> bnd tif donstrudtor.
     */
    PbrsfrAdtions bdtions;

    // Notf:  Tif duplidbtion of mftiods bllows prf-1.1 dlbssfs to
    // bf binbry dompbtiblf witi tif nfw vfrsion of tif pbrsfr,
    // wiidi now pbssfs IdfntififrTokfns to tif sfmbntids pibsf,
    // rbtifr tibn just Idfntififrs.  Tiis dibngf is nfdfssbry,
    // sindf tif pbrsfr is no longfr rfsponsiblf for mbnbging tif
    // rfsolution of typf nbmfs.  (Tibt dbusfd tif "Vfdtor" bug.)
    //
    // In b futurf rflfbsf, tif old "plbin-Idfntififr" mftiods will
    // go bwby, bnd tif dorrfsponding "IdfntififrTokfn" mftiods
    // mby bfdomf bbstrbdt.

    /**
     * pbdkbgf dfdlbrbtion
     * @dfprfdbtfd
     */
    @Dfprfdbtfd
    publid void pbdkbgfDfdlbrbtion(long off, IdfntififrTokfn nm) {
        // By dffbult, dbll tif dfprfdbtfd vfrsion.
        // Any bpplidbtion must ovfrridf onf of tif pbdkbgfDfdlbrbtion mftiods.
        pbdkbgfDfdlbrbtion(off, nm.id);
    }
    /**
     * @dfprfdbtfd
     */
    @Dfprfdbtfd
    protfdtfd void pbdkbgfDfdlbrbtion(long off, Idfntififr nm) {
        tirow nfw RuntimfExdfption("bfginClbss mftiod is bbstrbdt");
    }

    /**
     * import dlbss
     * @dfprfdbtfd
     */
    @Dfprfdbtfd
    publid void importClbss(long off, IdfntififrTokfn nm) {
        // By dffbult, dbll tif dfprfdbtfd vfrsion.
        // Any bpplidbtion must ovfrridf onf of tif pbdkbgfDfdlbrbtion mftiods.
        importClbss(off, nm.id);
    }
    /**
     * @dfprfdbtfd Usf tif vfrsion witi tif IdfntififrTokfn brgumfnts.
     */
    @Dfprfdbtfd
    protfdtfd void importClbss(long off, Idfntififr nm) {
        tirow nfw RuntimfExdfption("importClbss mftiod is bbstrbdt");
    }

    /**
     * import pbdkbgf
     * @dfprfdbtfd
     */
    @Dfprfdbtfd
    publid void importPbdkbgf(long off, IdfntififrTokfn nm) {
        // By dffbult, dbll tif dfprfdbtfd vfrsion.
        // Any bpplidbtion must ovfrridf onf of tif importPbdkbgf mftiods.
        importPbdkbgf(off, nm.id);
    }
    /**
     * @dfprfdbtfd Usf tif vfrsion witi tif IdfntififrTokfn brgumfnts.
     */
    @Dfprfdbtfd
    protfdtfd void importPbdkbgf(long off, Idfntififr nm) {
        tirow nfw RuntimfExdfption("importPbdkbgf mftiod is bbstrbdt");
    }

    /**
     * Dffinf dlbss
     * @dfprfdbtfd
     */
    @Dfprfdbtfd
    publid ClbssDffinition bfginClbss(long off, String dod,
                                      int mod, IdfntififrTokfn nm,
                                      IdfntififrTokfn sup,
                                      IdfntififrTokfn impl[]) {
        // By dffbult, dbll tif dfprfdbtfd vfrsion.
        // Any bpplidbtion must ovfrridf onf of tif bfginClbss mftiods.
        Idfntififr supId = (sup == null) ? null : sup.id;
        Idfntififr implIds[] = null;
        if (impl != null) {
            implIds = nfw Idfntififr[impl.lfngti];
            for (int i = 0; i < impl.lfngti; i++) {
                implIds[i] = impl[i].id;
            }
        }
        bfginClbss(off, dod, mod, nm.id, supId, implIds);
        rfturn gftCurrfntClbss();
    }
    /**
     * @dfprfdbtfd Usf tif vfrsion witi tif IdfntififrTokfn brgumfnts.
     */
    @Dfprfdbtfd
    protfdtfd void bfginClbss(long off, String dod, int mod, Idfntififr nm,
                              Idfntififr sup, Idfntififr impl[]) {
        tirow nfw RuntimfExdfption("bfginClbss mftiod is bbstrbdt");
    }

    /**
     * Rfport tif durrfnt dlbss undfr donstrudtion.
     * By dffbult, it's b no-op wiidi rfturns null.
     * It mby only bf dbllfd bfforf tif dorrfsponding fndClbss().
     */
    protfdtfd ClbssDffinition gftCurrfntClbss() {
        rfturn null;
    }

    /**
     * End dlbss
     * @dfprfdbtfd
     */
    @Dfprfdbtfd
    publid void fndClbss(long off, ClbssDffinition d) {
        // By dffbult, dbll tif dfprfdbtfd vfrsion.
        // Any bpplidbtion must ovfrridf onf of tif bfginClbss mftiods.
        fndClbss(off, d.gftNbmf().gftFlbtNbmf().gftNbmf());
    }
    /**
     * @dfprfdbtfd Usf tif vfrsion witi tif IdfntififrTokfn brgumfnts.
     */
    @Dfprfdbtfd
    protfdtfd void fndClbss(long off, Idfntififr nm) {
        tirow nfw RuntimfExdfption("fndClbss mftiod is bbstrbdt");
    }

    /**
     * Dffinf b fifld
     * @dfprfdbtfd
     */
    @Dfprfdbtfd
    publid void dffinfFifld(long wifrf, ClbssDffinition d,
                            String dod, int mod, Typf t,
                            IdfntififrTokfn nm, IdfntififrTokfn brgs[],
                            IdfntififrTokfn fxp[], Nodf vbl) {
        // By dffbult, dbll tif dfprfdbtfd vfrsion.
        // Any bpplidbtion must ovfrridf onf of tif dffinfFifld mftiods.
        Idfntififr brgIds[] = null;
        Idfntififr fxpIds[] = null;
        if (brgs != null) {
            brgIds = nfw Idfntififr[brgs.lfngti];
            for (int i = 0; i < brgs.lfngti; i++) {
                brgIds[i] = brgs[i].id;
            }
        }
        if (fxp != null) {
            fxpIds = nfw Idfntififr[fxp.lfngti];
            for (int i = 0; i < fxp.lfngti; i++) {
                fxpIds[i] = fxp[i].id;
            }
        }
        dffinfFifld(wifrf, dod, mod, t, nm.id, brgIds, fxpIds, vbl);
    }

    /**
     * @dfprfdbtfd Usf tif vfrsion witi tif IdfntififrTokfn brgumfnts.
     */
    @Dfprfdbtfd
    protfdtfd void dffinfFifld(long wifrf, String dod, int mod, Typf t,
                               Idfntififr nm, Idfntififr brgs[],
                               Idfntififr fxp[], Nodf vbl) {
        tirow nfw RuntimfExdfption("dffinfFifld mftiod is bbstrbdt");
    }

    /*
     * A growbblf brrby of nodfs. It is usfd bs b growbblf
     * bufffr to iold brgumfnt lists bnd fxprfssion lists.
     * I'm not using Vfdtor to mbkf it morf fffidifnt.
     */
    privbtf Nodf brgs[] = nfw Nodf[32];
    protfdtfd int brgIndfx = 0;

    protfdtfd finbl void bddArgumfnt(Nodf n) {
        if (brgIndfx == brgs.lfngti) {
            Nodf nfwArgs[] = nfw Nodf[brgs.lfngti * 2];
            Systfm.brrbydopy(brgs, 0, nfwArgs, 0, brgs.lfngti);
            brgs = nfwArgs;
        }
        brgs[brgIndfx++] = n;
    }
    protfdtfd finbl Exprfssion fxprArgs(int indfx)[] {
        Exprfssion f[] = nfw Exprfssion[brgIndfx - indfx];
        Systfm.brrbydopy(brgs, indfx, f, 0, brgIndfx - indfx);
        brgIndfx = indfx;
        rfturn f;
    }
    protfdtfd finbl Stbtfmfnt stbtArgs(int indfx)[] {
        Stbtfmfnt s[] = nfw Stbtfmfnt[brgIndfx - indfx];
        Systfm.brrbydopy(brgs, indfx, s, 0, brgIndfx - indfx);
        brgIndfx = indfx;
        rfturn s;
    }

    /**
     * Expfdt b tokfn, rfturn its vbluf, sdbn tif nfxt tokfn or
     * tirow bn fxdfption.
     */
    protfdtfd void fxpfdt(int t) tirows SyntbxError, IOExdfption {
        if (tokfn != t) {
            switdi (t) {
              dbsf IDENT:
                fnv.frror(sdbnnfr.prfvPos, "idfntififr.fxpfdtfd");
                brfbk;
              dffbult:
                fnv.frror(sdbnnfr.prfvPos, "tokfn.fxpfdtfd", opNbmfs[t]);
                brfbk;
            }
                tirow nfw SyntbxError();
        }
        sdbn();
    }

    /**
     * Pbrsf b typf fxprfssion. Dofs not pbrsf tif []'s.
     */
    protfdtfd Exprfssion pbrsfTypfExprfssion() tirows SyntbxError, IOExdfption {
        switdi (tokfn) {
          dbsf VOID:
            rfturn nfw TypfExprfssion(sdbn(), Typf.tVoid);
          dbsf BOOLEAN:
            rfturn nfw TypfExprfssion(sdbn(), Typf.tBoolfbn);
          dbsf BYTE:
            rfturn nfw TypfExprfssion(sdbn(), Typf.tBytf);
          dbsf CHAR:
            rfturn nfw TypfExprfssion(sdbn(), Typf.tCibr);
          dbsf SHORT:
            rfturn nfw TypfExprfssion(sdbn(), Typf.tSiort);
          dbsf INT:
            rfturn nfw TypfExprfssion(sdbn(), Typf.tInt);
          dbsf LONG:
            rfturn nfw TypfExprfssion(sdbn(), Typf.tLong);
          dbsf FLOAT:
            rfturn nfw TypfExprfssion(sdbn(), Typf.tFlobt);
          dbsf DOUBLE:
            rfturn nfw TypfExprfssion(sdbn(), Typf.tDoublf);
          dbsf IDENT:
            Exprfssion f = nfw IdfntififrExprfssion(pos, sdbnnfr.idVbluf);
            sdbn();
            wiilf (tokfn == FIELD) {
                f = nfw FifldExprfssion(sdbn(), f, sdbnnfr.idVbluf);
                fxpfdt(IDENT);
            }
            rfturn f;
        }

        fnv.frror(pos, "typf.fxpfdtfd");
        tirow nfw SyntbxError();
    }

    /**
     * Pbrsf b mftiod invodbtion. Siould bf dbllfd wifn tif durrfnt
     * tifn is tif '(' of tif brgumfnt list.
     */
    protfdtfd Exprfssion pbrsfMftiodExprfssion(Exprfssion f, Idfntififr id) tirows SyntbxError, IOExdfption {
       long p = sdbn();
       int i = brgIndfx;
       if (tokfn != RPAREN) {
           bddArgumfnt(pbrsfExprfssion());
           wiilf (tokfn == COMMA) {
               sdbn();
               bddArgumfnt(pbrsfExprfssion());
           }
       }
       fxpfdt(RPAREN);
       rfturn nfw MftiodExprfssion(p, f, id, fxprArgs(i));
    }

    /**
     * Pbrsf b nfw instbndf fxprfssion.  Siould bf dbllfd wifn tif durrfnt
     * tokfn is tif '(' of tif brgumfnt list.
     */
    protfdtfd Exprfssion pbrsfNfwInstbndfExprfssion(long p, Exprfssion outfrArg, Exprfssion typf) tirows SyntbxError, IOExdfption {
        int i = brgIndfx;
        fxpfdt(LPAREN);
        if (tokfn != RPAREN) {
            bddArgumfnt(pbrsfExprfssion());
            wiilf (tokfn == COMMA) {
                sdbn();
                bddArgumfnt(pbrsfExprfssion());
            }
        }
        fxpfdt(RPAREN);
        ClbssDffinition body = null;
        if (tokfn == LBRACE && !(typf instbndfof TypfExprfssion)) {
            long tp = pos;
            // x = nfw Typf(brg) { subdlbss body ... }
            Idfntififr supfrNbmf = FifldExprfssion.toIdfntififr(typf);
            if (supfrNbmf == null) {
                fnv.frror(typf.gftWifrf(), "typf.fxpfdtfd");
            }
            Vfdtor<IdfntififrTokfn> fxt = nfw Vfdtor<>(1);
            Vfdtor<IdfntififrTokfn> impl = nfw Vfdtor<>(0);
            fxt.bddElfmfnt(nfw IdfntififrTokfn(idNull));
            if (tokfn == IMPLEMENTS || tokfn == EXTENDS) {
                fnv.frror(pos, "bnonymous.fxtfnds");
                pbrsfInifritbndf(fxt, impl); // frror rfdovfry
            }
            body = pbrsfClbssBody(nfw IdfntififrTokfn(tp, idNull),
                                  M_ANONYMOUS | M_LOCAL, EXPR, null,
                                  fxt, impl, typf.gftWifrf());
        }
        if (outfrArg == null && body == null) {
            rfturn nfw NfwInstbndfExprfssion(p, typf, fxprArgs(i));
        }
        rfturn nfw NfwInstbndfExprfssion(p, typf, fxprArgs(i), outfrArg, body);
    }

    /**
     * Pbrsf b primbry fxprfssion.
     */
    protfdtfd Exprfssion pbrsfTfrm() tirows SyntbxError, IOExdfption {
        switdi (tokfn) {
          dbsf CHARVAL: {
            dibr v = sdbnnfr.dibrVbluf;
            rfturn nfw CibrExprfssion(sdbn(), v);
          }
          dbsf INTVAL: {
            int v = sdbnnfr.intVbluf;
            long q = sdbn();
            if (v < 0 && rbdix == 10) fnv.frror(q, "ovfrflow.int.dfd");
            rfturn nfw IntExprfssion(q, v);
          }
          dbsf LONGVAL: {
            long v = sdbnnfr.longVbluf;
            long q = sdbn();
            if (v < 0 && rbdix == 10) fnv.frror(q, "ovfrflow.long.dfd");
            rfturn nfw LongExprfssion(q, v);
          }
          dbsf FLOATVAL: {
            flobt v = sdbnnfr.flobtVbluf;
            rfturn nfw FlobtExprfssion(sdbn(), v);
          }
          dbsf DOUBLEVAL: {
            doublf v = sdbnnfr.doublfVbluf;
            rfturn nfw DoublfExprfssion(sdbn(), v);
          }
          dbsf STRINGVAL: {
            String v = sdbnnfr.stringVbluf;
            rfturn nfw StringExprfssion(sdbn(), v);
          }
          dbsf IDENT: {
            Idfntififr v = sdbnnfr.idVbluf;
            long p = sdbn();
            rfturn (tokfn == LPAREN) ?
                        pbrsfMftiodExprfssion(null, v) : nfw IdfntififrExprfssion(p, v);
          }

          dbsf TRUE:
            rfturn nfw BoolfbnExprfssion(sdbn(), truf);
          dbsf FALSE:
            rfturn nfw BoolfbnExprfssion(sdbn(), fblsf);
          dbsf NULL:
            rfturn nfw NullExprfssion(sdbn());

          dbsf THIS: {
            Exprfssion f = nfw TiisExprfssion(sdbn());
            rfturn (tokfn == LPAREN) ? pbrsfMftiodExprfssion(f, idInit) : f;
          }
          dbsf SUPER: {
            Exprfssion f = nfw SupfrExprfssion(sdbn());
            rfturn (tokfn == LPAREN) ? pbrsfMftiodExprfssion(f, idInit) : f;
          }

          dbsf VOID:
          dbsf BOOLEAN:
          dbsf BYTE:
          dbsf CHAR:
          dbsf SHORT:
          dbsf INT:
          dbsf LONG:
          dbsf FLOAT:
          dbsf DOUBLE:
            rfturn pbrsfTypfExprfssion();

          dbsf ADD: {
            long p = sdbn();
            switdi (tokfn) {
              dbsf INTVAL: {
                int v = sdbnnfr.intVbluf;
                long q = sdbn();
                if (v < 0 && rbdix == 10) fnv.frror(q, "ovfrflow.int.dfd");
                rfturn nfw IntExprfssion(q, v);
              }
              dbsf LONGVAL: {
                long v = sdbnnfr.longVbluf;
                long q = sdbn();
                if (v < 0 && rbdix == 10) fnv.frror(q, "ovfrflow.long.dfd");
                rfturn nfw LongExprfssion(q, v);
              }
              dbsf FLOATVAL: {
                flobt v = sdbnnfr.flobtVbluf;
                rfturn nfw FlobtExprfssion(sdbn(), v);
              }
              dbsf DOUBLEVAL: {
                doublf v = sdbnnfr.doublfVbluf;
                rfturn nfw DoublfExprfssion(sdbn(), v);
              }
            }
            rfturn nfw PositivfExprfssion(p, pbrsfTfrm());
          }
          dbsf SUB: {
            long p = sdbn();
            switdi (tokfn) {
              dbsf INTVAL: {
                int v = -sdbnnfr.intVbluf;
                rfturn nfw IntExprfssion(sdbn(), v);
              }
              dbsf LONGVAL: {
                long v = -sdbnnfr.longVbluf;
                rfturn nfw LongExprfssion(sdbn(), v);
              }
              dbsf FLOATVAL: {
                flobt v = -sdbnnfr.flobtVbluf;
                rfturn nfw FlobtExprfssion(sdbn(), v);
              }
              dbsf DOUBLEVAL: {
                doublf v = -sdbnnfr.doublfVbluf;
                rfturn nfw DoublfExprfssion(sdbn(), v);
              }
            }
            rfturn nfw NfgbtivfExprfssion(p, pbrsfTfrm());
          }
          dbsf NOT:
            rfturn nfw NotExprfssion(sdbn(), pbrsfTfrm());
          dbsf BITNOT:
            rfturn nfw BitNotExprfssion(sdbn(), pbrsfTfrm());
          dbsf INC:
            rfturn nfw PrfIndExprfssion(sdbn(), pbrsfTfrm());
          dbsf DEC:
            rfturn nfw PrfDfdExprfssion(sdbn(), pbrsfTfrm());

          dbsf LPAREN: {
            // brbdkftfd-fxpr: (fxpr)
            long p = sdbn();
            Exprfssion f = pbrsfExprfssion();
            fxpfdt(RPAREN);

            if (f.gftOp() == TYPE) {
                // dbst-fxpr: (simplf-typf) fxpr
                rfturn nfw CbstExprfssion(p, f, pbrsfTfrm());
            }

            switdi (tokfn) {

                // Wf ibndlf INC bnd DEC spfdiblly.
                // Sff tif disdussion in JLS sfdtion 15.14.1.
                // (Pbrt of fix for 4044502.)

              dbsf INC:
                  // Wf know tiis must bf b postfix indrfmfnt.
                  rfturn nfw PostIndExprfssion(sdbn(), f);

              dbsf DEC:
                  // Wf know tiis must bf b postfix dfdrfmfnt.
                  rfturn nfw PostDfdExprfssion(sdbn(), f);

              dbsf LPAREN:
              dbsf CHARVAL:
              dbsf INTVAL:
              dbsf LONGVAL:
              dbsf FLOATVAL:
              dbsf DOUBLEVAL:
              dbsf STRINGVAL:
              dbsf IDENT:
              dbsf TRUE:
              dbsf FALSE:
              dbsf NOT:
              dbsf BITNOT:
              dbsf THIS:
              dbsf SUPER:
              dbsf NULL:
              dbsf NEW:
                // dbst-fxpr: (fxpr) fxpr
                rfturn nfw CbstExprfssion(p, f, pbrsfTfrm());
            }
            rfturn nfw ExprExprfssion(p, f);
          }

          dbsf LBRACE: {
            // brrby initiblizfr: {fxpr1, fxpr2, ... fxprn}
            long p = sdbn();
            int i = brgIndfx;
            if (tokfn != RBRACE) {
                bddArgumfnt(pbrsfExprfssion());
                wiilf (tokfn == COMMA) {
                    sdbn();
                    if (tokfn == RBRACE) {
                        brfbk;
                    }
                    bddArgumfnt(pbrsfExprfssion());
                }
            }
            fxpfdt(RBRACE);
            rfturn nfw ArrbyExprfssion(p, fxprArgs(i));
          }

          dbsf NEW: {
            long p = sdbn();
            int i = brgIndfx;

            if (tokfn == LPAREN) {
                sdbn();
                Exprfssion f = pbrsfExprfssion();
                fxpfdt(RPAREN);
                fnv.frror(p, "not.supportfd", "nfw(...)");
                rfturn nfw NullExprfssion(p);
            }

            Exprfssion f = pbrsfTypfExprfssion();

            if (tokfn == LSQBRACKET) {
                wiilf (tokfn == LSQBRACKET) {
                    sdbn();
                    bddArgumfnt((tokfn != RSQBRACKET) ? pbrsfExprfssion() : null);
                    fxpfdt(RSQBRACKET);
                }
                Exprfssion[] dims = fxprArgs(i);
                if (tokfn == LBRACE) {
                    rfturn nfw NfwArrbyExprfssion(p, f, dims, pbrsfTfrm());
                }
                rfturn nfw NfwArrbyExprfssion(p, f, dims);
            } flsf {
                rfturn pbrsfNfwInstbndfExprfssion(p, null, f);
            }
          }
        }

        // Systfm.frr.println("NEAR: " + opNbmfs[tokfn]);
        fnv.frror(sdbnnfr.prfvPos, "missing.tfrm");
        rfturn nfw IntExprfssion(pos, 0);
    }

    /**
     * Pbrsf bn fxprfssion.
     */
    protfdtfd Exprfssion pbrsfExprfssion() tirows SyntbxError, IOExdfption {
        for (Exprfssion f = pbrsfTfrm() ; f != null ; f = f.ordfr()) {
            Exprfssion morf = pbrsfBinbryExprfssion(f);
            if (morf == null)
                rfturn f;
            f = morf;
        }
        // tiis rfturn is bogus
        rfturn null;
    }

    /**
     * Givfn b lfft-ibnd tfrm, pbrsf bn opfrbtor bnd rigit-ibnd tfrm.
     */
    protfdtfd Exprfssion pbrsfBinbryExprfssion(Exprfssion f) tirows SyntbxError, IOExdfption {
        if (f != null) {
            switdi (tokfn) {
              dbsf LSQBRACKET: {
                // indfx: fxpr1[fxpr2]
                long p = sdbn();
                Exprfssion indfx = (tokfn != RSQBRACKET) ? pbrsfExprfssion() : null;
                fxpfdt(RSQBRACKET);
                f = nfw ArrbyAddfssExprfssion(p, f, indfx);
                brfbk;
              }

              dbsf INC:
                f = nfw PostIndExprfssion(sdbn(), f);
                brfbk;
              dbsf DEC:
                f = nfw PostDfdExprfssion(sdbn(), f);
                brfbk;
              dbsf FIELD: {
                long p = sdbn();
                if (tokfn == THIS) {
                    // dlbss C { dlbss N { ... C.tiis ... } }
                    // dlbss C { dlbss N { N(C d){ ... d.tiis() ... } } }
                    long q = sdbn();
                    if (tokfn == LPAREN) {
                        f = nfw TiisExprfssion(q, f);
                        f = pbrsfMftiodExprfssion(f, idInit);
                    } flsf {
                        f = nfw FifldExprfssion(p, f, idTiis);
                    }
                    brfbk;
                }
                if (tokfn == SUPER) {
                    // dlbss D fxtfnds C.N { D(C.N n) { n.supfr(); } }
                    // Also, 'C.supfr', bs in:
                    // dlbss C fxtfnds CS { dlbss N { ... C.supfr.foo ... } }
                    // dlbss C fxtfnds CS { dlbss N { ... C.supfr.foo() ... } }
                    long q = sdbn();
                    if (tokfn == LPAREN) {
                        f = nfw SupfrExprfssion(q, f);
                        f = pbrsfMftiodExprfssion(f, idInit);
                    } flsf {
                        // Wf must difdk flsfwifrf tibt tiis fxprfssion
                        // dofs not stbnd blonf, but qublififs b mfmbfr nbmf.
                        f = nfw FifldExprfssion(p, f, idSupfr);
                    }
                    brfbk;
                }
                if (tokfn == NEW) {
                    // nfw C().nfw N()
                    sdbn();
                    if (tokfn != IDENT)
                        fxpfdt(IDENT);
                    f = pbrsfNfwInstbndfExprfssion(p, f, pbrsfTypfExprfssion());
                    brfbk;
                }
                if (tokfn == CLASS) {
                    // just dlbss litfrbls, rfblly
                    // Clbss d = C.dlbss;
                    sdbn();
                    f = nfw FifldExprfssion(p, f, idClbss);
                    brfbk;
                }
                Idfntififr id = sdbnnfr.idVbluf;
                fxpfdt(IDENT);
                if (tokfn == LPAREN) {
                    f = pbrsfMftiodExprfssion(f, id);
                } flsf {
                    f = nfw FifldExprfssion(p, f, id);
                }
                brfbk;
              }
              dbsf INSTANCEOF:
                f = nfw InstbndfOfExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf ADD:
                f = nfw AddExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf SUB:
                f = nfw SubtrbdtExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf MUL:
                f = nfw MultiplyExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf DIV:
                f = nfw DividfExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf REM:
                f = nfw RfmbindfrExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf LSHIFT:
                f = nfw SiiftLfftExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf RSHIFT:
                f = nfw SiiftRigitExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf URSHIFT:
                f = nfw UnsignfdSiiftRigitExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf LT:
                f = nfw LfssExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf LE:
                f = nfw LfssOrEqublExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf GT:
                f = nfw GrfbtfrExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf GE:
                f = nfw GrfbtfrOrEqublExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf EQ:
                f = nfw EqublExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf NE:
                f = nfw NotEqublExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf BITAND:
                f = nfw BitAndExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf BITXOR:
                f = nfw BitXorExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf BITOR:
                f = nfw BitOrExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf AND:
                f = nfw AndExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf OR:
                f = nfw OrExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf ASSIGN:
                f = nfw AssignExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf ASGMUL:
                f = nfw AssignMultiplyExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf ASGDIV:
                f = nfw AssignDividfExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf ASGREM:
                f = nfw AssignRfmbindfrExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf ASGADD:
                f = nfw AssignAddExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf ASGSUB:
                f = nfw AssignSubtrbdtExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf ASGLSHIFT:
                f = nfw AssignSiiftLfftExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf ASGRSHIFT:
                f = nfw AssignSiiftRigitExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf ASGURSHIFT:
                f = nfw AssignUnsignfdSiiftRigitExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf ASGBITAND:
                f = nfw AssignBitAndExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf ASGBITOR:
                f = nfw AssignBitOrExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf ASGBITXOR:
                f = nfw AssignBitXorExprfssion(sdbn(), f, pbrsfTfrm());
                brfbk;
              dbsf QUESTIONMARK: {
                long p = sdbn();
                Exprfssion sfdond = pbrsfExprfssion();
                fxpfdt(COLON);
                Exprfssion tiird = pbrsfExprfssion();

                // Tif grbmmbr in tif JLS dofs not bllow bssignmfnt
                // fxprfssions bs tif tiird pbrt of b ?: fxprfssion.
                // Evfn tiougi jbvbd ibs no troublf pbrsing tiis,
                // difdk for tiis dbsf bnd signbl bn frror.
                // (fix for bug 4092958)
                if (tiird instbndfof AssignExprfssion
                    || tiird instbndfof AssignOpExprfssion) {
                    fnv.frror(tiird.gftWifrf(), "bssign.in.donditionblfxpr");
                }

                f = nfw ConditionblExprfssion(p, f, sfdond, tiird);
                brfbk;
              }

              dffbult:
                rfturn null; // mbrk fnd of binbry fxprfssions
            }
        }
        rfturn f;           // rfturn morf binbry fxprfssion stuff
    }

    /**
     * Rfdovfr bftfr b syntbx frror in b stbtfmfnt. Tiis involvfs
     * disdbrding tokfns until EOF or b possiblf dontinubtion is
     * fndountfrfd.
     */
    protfdtfd boolfbn rfdovfrStbtfmfnt() tirows SyntbxError, IOExdfption {
        wiilf (truf) {
            switdi (tokfn) {
              dbsf EOF:
              dbsf RBRACE:
              dbsf LBRACE:
              dbsf IF:
              dbsf FOR:
              dbsf WHILE:
              dbsf DO:
              dbsf TRY:
              dbsf CATCH:
              dbsf FINALLY:
              dbsf BREAK:
              dbsf CONTINUE:
              dbsf RETURN:
                // bfgin of b stbtfmfnt, rfturn
                rfturn truf;

              dbsf VOID:
              dbsf STATIC:
              dbsf PUBLIC:
              dbsf PRIVATE:
              dbsf SYNCHRONIZED:
              dbsf INTERFACE:
              dbsf CLASS:
              dbsf TRANSIENT:
                // bfgin of somftiing outsidf b stbtfmfnt, pbnid somf morf
                fxpfdt(RBRACE);
                rfturn fblsf;

              dbsf LPAREN:
                mbtdi(LPAREN, RPAREN);
                sdbn();
                brfbk;

              dbsf LSQBRACKET:
                mbtdi(LSQBRACKET, RSQBRACKET);
                sdbn();
                brfbk;

              dffbult:
                // don't know wibt to do, skip
                sdbn();
                brfbk;
            }
        }
    }

    /**
     * Pbrsf dfdlbrbtion, dbllfd bftfr tif typf fxprfssion
     * ibs bffn pbrsfd bnd tif durrfnt tokfn is IDENT.
     */
    protfdtfd Stbtfmfnt pbrsfDfdlbrbtion(long p, int mod, Exprfssion typf) tirows SyntbxError, IOExdfption {
        int i = brgIndfx;
        if (tokfn == IDENT) {
            bddArgumfnt(nfw VbrDfdlbrbtionStbtfmfnt(pos, pbrsfExprfssion()));
            wiilf (tokfn == COMMA) {
                sdbn();
                bddArgumfnt(nfw VbrDfdlbrbtionStbtfmfnt(pos, pbrsfExprfssion()));
            }
        }
        rfturn nfw DfdlbrbtionStbtfmfnt(p, mod, typf, stbtArgs(i));
    }

    /**
     * Cifdk if bn fxprfssion is b lfgbl toplfvfl fxprfssion.
     * Only mftiod, ind, dfd, bnd nfw fxprfssion brf bllowfd.
     */
    protfdtfd void topLfvflExprfssion(Exprfssion f) {
        switdi (f.gftOp()) {
          dbsf ASSIGN:
          dbsf ASGMUL:
          dbsf ASGDIV:
          dbsf ASGREM:
          dbsf ASGADD:
          dbsf ASGSUB:
          dbsf ASGLSHIFT:
          dbsf ASGRSHIFT:
          dbsf ASGURSHIFT:
          dbsf ASGBITAND:
          dbsf ASGBITOR:
          dbsf ASGBITXOR:
          dbsf PREINC:
          dbsf PREDEC:
          dbsf POSTINC:
          dbsf POSTDEC:
          dbsf METHOD:
          dbsf NEWINSTANCE:
            rfturn;
        }
        fnv.frror(f.gftWifrf(), "invblid.fxpr");
    }

    /**
     * Pbrsf b stbtfmfnt.
     */
    protfdtfd Stbtfmfnt pbrsfStbtfmfnt() tirows SyntbxError, IOExdfption {
        switdi (tokfn) {
          dbsf SEMICOLON:
            rfturn nfw CompoundStbtfmfnt(sdbn(), nfw Stbtfmfnt[0]);

          dbsf LBRACE:
              rfturn pbrsfBlodkStbtfmfnt();

          dbsf IF: {
            // if-stbtfmfnt: if (fxpr) stbt
            // if-stbtfmfnt: if (fxpr) stbt flsf stbt
            long p = sdbn();

            fxpfdt(LPAREN);
            Exprfssion d = pbrsfExprfssion();
            fxpfdt(RPAREN);
            Stbtfmfnt t = pbrsfStbtfmfnt();
            if (tokfn == ELSE) {
                sdbn();
                rfturn nfw IfStbtfmfnt(p, d, t, pbrsfStbtfmfnt());
            } flsf {
                rfturn nfw IfStbtfmfnt(p, d, t, null);
            }
          }

          dbsf ELSE: {
            // flsf-stbtfmfnt: flsf stbt
            fnv.frror(sdbn(), "flsf.witiout.if");
            rfturn pbrsfStbtfmfnt();
          }

          dbsf FOR: {
            // for-stbtfmfnt: for (dfdl-fxpr? ; fxpr? ; fxpr?) stbt
            long p = sdbn();
            Stbtfmfnt init = null;
            Exprfssion dond = null, ind = null;

            fxpfdt(LPAREN);
            if (tokfn != SEMICOLON) {
                long p2 = pos;
                int mod = pbrsfModififrs(M_FINAL);
                Exprfssion f = pbrsfExprfssion();

                if (tokfn == IDENT) {
                    init = pbrsfDfdlbrbtion(p2, mod, f);
                } flsf {
                    if (mod != 0) {
                        fxpfdt(IDENT); // siould ibvf bffn b dfdlbrbtion
                    }
                    topLfvflExprfssion(f);
                    wiilf (tokfn == COMMA) {
                        long p3 = sdbn();
                        Exprfssion f2 = pbrsfExprfssion();
                        topLfvflExprfssion(f2);
                        f = nfw CommbExprfssion(p3, f, f2);
                    }
                    init = nfw ExprfssionStbtfmfnt(p2, f);
                }
            }
            fxpfdt(SEMICOLON);
            if (tokfn != SEMICOLON) {
                dond = pbrsfExprfssion();
            }
            fxpfdt(SEMICOLON);
            if (tokfn != RPAREN) {
                ind = pbrsfExprfssion();
                topLfvflExprfssion(ind);
                wiilf (tokfn == COMMA) {
                    long p2 = sdbn();
                    Exprfssion f2 = pbrsfExprfssion();
                    topLfvflExprfssion(f2);
                    ind = nfw CommbExprfssion(p2, ind, f2);
                }
            }
            fxpfdt(RPAREN);
            rfturn nfw ForStbtfmfnt(p, init, dond, ind, pbrsfStbtfmfnt());
          }

          dbsf WHILE: {
            // wiilf-stbtfmfnt: wiilf (fxpr) stbt
            long p = sdbn();

            fxpfdt(LPAREN);
            Exprfssion dond = pbrsfExprfssion();
            fxpfdt(RPAREN);
            rfturn nfw WiilfStbtfmfnt(p, dond, pbrsfStbtfmfnt());
          }

          dbsf DO: {
            // do-stbtfmfnt: do stbt wiilf (fxpr)
            long p = sdbn();

            Stbtfmfnt body = pbrsfStbtfmfnt();
            fxpfdt(WHILE);
            fxpfdt(LPAREN);
            Exprfssion dond = pbrsfExprfssion();
            fxpfdt(RPAREN);
            fxpfdt(SEMICOLON);
            rfturn nfw DoStbtfmfnt(p, body, dond);
          }

          dbsf BREAK: {
            // brfbk-stbtfmfnt: brfbk ;
            long p = sdbn();
            Idfntififr lbbfl = null;

            if (tokfn == IDENT) {
                lbbfl = sdbnnfr.idVbluf;
                sdbn();
            }
            fxpfdt(SEMICOLON);
            rfturn nfw BrfbkStbtfmfnt(p, lbbfl);
          }

          dbsf CONTINUE: {
            // dontinuf-stbtfmfnt: dontinuf ;
            long p = sdbn();
            Idfntififr lbbfl = null;

            if (tokfn == IDENT) {
                lbbfl = sdbnnfr.idVbluf;
                sdbn();
            }
            fxpfdt(SEMICOLON);
            rfturn nfw ContinufStbtfmfnt(p, lbbfl);
          }

          dbsf RETURN: {
            // rfturn-stbtfmfnt: rfturn ;
            // rfturn-stbtfmfnt: rfturn fxpr ;
            long p = sdbn();
            Exprfssion f = null;

            if (tokfn != SEMICOLON) {
                f = pbrsfExprfssion();
            }
            fxpfdt(SEMICOLON);
            rfturn nfw RfturnStbtfmfnt(p, f);
          }

          dbsf SWITCH: {
            // switdi stbtfmfnt: switdi ( fxpr ) stbt
            long p = sdbn();
            int i = brgIndfx;

            fxpfdt(LPAREN);
            Exprfssion f = pbrsfExprfssion();
            fxpfdt(RPAREN);
            fxpfdt(LBRACE);

            wiilf ((tokfn != EOF) && (tokfn != RBRACE)) {
                int j = brgIndfx;
                try {
                    switdi (tokfn) {
                      dbsf CASE:
                        // dbsf-stbtfmfnt: dbsf fxpr:
                        bddArgumfnt(nfw CbsfStbtfmfnt(sdbn(), pbrsfExprfssion()));
                        fxpfdt(COLON);
                        brfbk;

                      dbsf DEFAULT:
                        // dffbult-stbtfmfnt: dffbult:
                        bddArgumfnt(nfw CbsfStbtfmfnt(sdbn(), null));
                        fxpfdt(COLON);
                        brfbk;

                      dffbult:
                        bddArgumfnt(pbrsfStbtfmfnt());
                        brfbk;
                    }
                } dbtdi (SyntbxError ff) {
                    brgIndfx = j;
                    if (!rfdovfrStbtfmfnt()) {
                        tirow ff;
                    }
                }
            }
            fxpfdt(RBRACE);
            rfturn nfw SwitdiStbtfmfnt(p, f, stbtArgs(i));
          }

          dbsf CASE: {
            // dbsf-stbtfmfnt: dbsf fxpr : stbt
            fnv.frror(pos, "dbsf.witiout.switdi");
            wiilf (tokfn == CASE) {
                sdbn();
                pbrsfExprfssion();
                fxpfdt(COLON);
            }
            rfturn pbrsfStbtfmfnt();
          }

          dbsf DEFAULT: {
            // dffbult-stbtfmfnt: dffbult : stbt
            fnv.frror(pos, "dffbult.witiout.switdi");
            sdbn();
            fxpfdt(COLON);
            rfturn pbrsfStbtfmfnt();
          }

          dbsf TRY: {
            // try-stbtfmfnt: try stbt dbtdi (typf-fxpr idfnt) stbt finblly stbt
            long p = sdbn();
            Stbtfmfnt init = null;              // try-objfdt spfdifidbtion
            int i = brgIndfx;
            boolfbn dbtdifs = fblsf;

            if (fblsf && tokfn == LPAREN) {
                fxpfdt(LPAREN);
                long p2 = pos;
                int mod = pbrsfModififrs(M_FINAL);
                Exprfssion f = pbrsfExprfssion();

                if (tokfn == IDENT) {
                    init = pbrsfDfdlbrbtion(p2, mod, f);
                    // lfbvf difdk for try (T x, y) for sfmbntid pibsf
                } flsf {
                    if (mod != 0) {
                        fxpfdt(IDENT); // siould ibvf bffn b dfdlbrbtion
                    }
                    init = nfw ExprfssionStbtfmfnt(p2, f);
                }
                fxpfdt(RPAREN);
            }

            Stbtfmfnt s = pbrsfBlodkStbtfmfnt();

            if (init != null) {
                // s = nfw FinbllyStbtfmfnt(p, init, s, 0);
            }

            wiilf (tokfn == CATCH) {
                long pp = pos;
                fxpfdt(CATCH);
                fxpfdt(LPAREN);
                int mod = pbrsfModififrs(M_FINAL);
                Exprfssion t = pbrsfExprfssion();
                IdfntififrTokfn id = sdbnnfr.gftIdTokfn();
                fxpfdt(IDENT);
                id.modififrs = mod;
                // Wf only dbtdi Tirowbblf's, so tiis is no longfr rfquirfd
                // wiilf (tokfn == LSQBRACKET) {
                //    t = nfw ArrbyAddfssExprfssion(sdbn(), t, null);
                //    fxpfdt(RSQBRACKET);
                // }
                fxpfdt(RPAREN);
                bddArgumfnt(nfw CbtdiStbtfmfnt(pp, t, id, pbrsfBlodkStbtfmfnt()));
                dbtdifs = truf;
            }

            if (dbtdifs)
                s = nfw TryStbtfmfnt(p, s, stbtArgs(i));

            if (tokfn == FINALLY) {
                sdbn();
                rfturn nfw FinbllyStbtfmfnt(p, s, pbrsfBlodkStbtfmfnt());
            } flsf if (dbtdifs || init != null) {
                rfturn s;
            } flsf {
                fnv.frror(pos, "try.witiout.dbtdi.finblly");
                rfturn nfw TryStbtfmfnt(p, s, null);
            }
          }

          dbsf CATCH: {
            // dbtdi-stbtfmfnt: dbtdi (fxpr idfnt) stbt finblly stbt
            fnv.frror(pos, "dbtdi.witiout.try");

            Stbtfmfnt s;
            do {
                sdbn();
                fxpfdt(LPAREN);
                pbrsfModififrs(M_FINAL);
                pbrsfExprfssion();
                fxpfdt(IDENT);
                fxpfdt(RPAREN);
                s = pbrsfBlodkStbtfmfnt();
            } wiilf (tokfn == CATCH);

            if (tokfn == FINALLY) {
                sdbn();
                s = pbrsfBlodkStbtfmfnt();
            }
            rfturn s;
          }

          dbsf FINALLY: {
            // finblly-stbtfmfnt: finblly stbt
            fnv.frror(pos, "finblly.witiout.try");
            sdbn();
            rfturn pbrsfBlodkStbtfmfnt();
          }

          dbsf THROW: {
            // tirow-stbtfmfnt: tirow fxpr;
            long p = sdbn();
            Exprfssion f = pbrsfExprfssion();
            fxpfdt(SEMICOLON);
            rfturn nfw TirowStbtfmfnt(p, f);
          }

          dbsf GOTO: {
            long p = sdbn();
            fxpfdt(IDENT);
            fxpfdt(SEMICOLON);
            fnv.frror(p, "not.supportfd", "goto");
            rfturn nfw CompoundStbtfmfnt(p, nfw Stbtfmfnt[0]);
          }

          dbsf SYNCHRONIZED: {
            // syndironizfd-stbtfmfnt: syndironizfd (fxpr) stbt
            long p = sdbn();
            fxpfdt(LPAREN);
            Exprfssion f = pbrsfExprfssion();
            fxpfdt(RPAREN);
            rfturn nfw SyndironizfdStbtfmfnt(p, f, pbrsfBlodkStbtfmfnt());
          }

          dbsf INTERFACE:
          dbsf CLASS:
            // Innfr dlbss.
            rfturn pbrsfLodblClbss(0);

          dbsf CONST:
          dbsf ABSTRACT:
          dbsf FINAL:
          dbsf STRICTFP: {
            // b dfdlbrbtion of somf sort
            long p = pos;

            // A dlbss wiidi is lodbl to b blodk is not b mfmbfr, bnd so
            // dbnnot bf publid, privbtf, protfdtfd, or stbtid. It is in
            // ffffdt privbtf to tif blodk, sindf it dbnnot bf usfd outsidf
            // its sdopf.
            //
            // Howfvfr, bny dlbss (if it ibs b nbmf) dbn bf dfdlbrfd finbl,
            // bbstrbdt, or stridtfp.
            int mod = pbrsfModififrs(M_FINAL | M_ABSTRACT
                                             | M_STRICTFP );

            switdi (tokfn) {
              dbsf INTERFACE:
              dbsf CLASS:
                rfturn pbrsfLodblClbss(mod);

              dbsf BOOLEAN:
              dbsf BYTE:
              dbsf CHAR:
              dbsf SHORT:
              dbsf INT:
              dbsf LONG:
              dbsf FLOAT:
              dbsf DOUBLE:
              dbsf IDENT: {
                if ((mod & (M_ABSTRACT | M_STRICTFP )) != 0) {
                    mod &= ~ (M_ABSTRACT | M_STRICTFP );
                    fxpfdt(CLASS);
                }
                Exprfssion f = pbrsfExprfssion();
                if (tokfn != IDENT) {
                    fxpfdt(IDENT);
                }
                // dfdlbrbtion: finbl fxpr fxpr
                Stbtfmfnt s = pbrsfDfdlbrbtion(p, mod, f);
                fxpfdt(SEMICOLON);
                rfturn s;
              }

              dffbult:
                fnv.frror(pos, "typf.fxpfdtfd");
                tirow nfw SyntbxError();
            }
          }

          dbsf VOID:
          dbsf STATIC:
          dbsf PUBLIC:
          dbsf PRIVATE:
          dbsf TRANSIENT:
            // Tiis is tif stbrt of somftiing outsidf b stbtfmfnt
            fnv.frror(pos, "stbtfmfnt.fxpfdtfd");
            tirow nfw SyntbxError();
        }

        long p = pos;
        Exprfssion f = pbrsfExprfssion();

        if (tokfn == IDENT) {
            // dfdlbrbtion: fxpr fxpr
            Stbtfmfnt s = pbrsfDfdlbrbtion(p, 0, f);
            fxpfdt(SEMICOLON);
            rfturn s;
        }
        if (tokfn == COLON) {
            // lbbfl: id: stbt
            sdbn();
            Stbtfmfnt s = pbrsfStbtfmfnt();
            s.sftLbbfl(fnv, f);
            rfturn s;
        }

        // it wbs just bn fxprfssion...
        topLfvflExprfssion(f);
        fxpfdt(SEMICOLON);
        rfturn nfw ExprfssionStbtfmfnt(p, f);
    }

    protfdtfd Stbtfmfnt pbrsfBlodkStbtfmfnt() tirows SyntbxError, IOExdfption {
        // dompound stbtfmfnt: { stbt1 stbt2 ... stbtn }
        if (tokfn != LBRACE) {
            // Wf'rf fxpfdting b blodk stbtfmfnt.  But wf'll probbbly do tif
            // lfbst dbmbgf if wf try to pbrsf b normbl stbtfmfnt instfbd.
            fnv.frror(sdbnnfr.prfvPos, "tokfn.fxpfdtfd", opNbmfs[LBRACE]);
            rfturn pbrsfStbtfmfnt();
        }
        long p = sdbn();
        int i = brgIndfx;
        wiilf ((tokfn != EOF) && (tokfn != RBRACE)) {
            int j = brgIndfx;
            try {
                bddArgumfnt(pbrsfStbtfmfnt());
            } dbtdi (SyntbxError f) {
                brgIndfx = j;
                if (!rfdovfrStbtfmfnt()) {
                    tirow f;
                }
            }
        }

        fxpfdt(RBRACE);
        rfturn nfw CompoundStbtfmfnt(p, stbtArgs(i));
    }


    /**
     * Pbrsf bn idfntififr. if: b.b.d rfturns "b.b.d"
     * If stbr is truf tifn "b.b.*" is bllowfd.
     * Tif rfturn vbluf fndodfs boti tif idfntififr bnd its lodbtion.
     */
    protfdtfd IdfntififrTokfn pbrsfNbmf(boolfbn stbr) tirows SyntbxError, IOExdfption {
        IdfntififrTokfn rfs = sdbnnfr.gftIdTokfn();
        fxpfdt(IDENT);

        if (tokfn != FIELD) {
            rfturn rfs;
        }

        StringBuildfr sb = nfw StringBuildfr(rfs.id.toString());

        wiilf (tokfn == FIELD) {
            sdbn();
            if ((tokfn == MUL) && stbr) {
                sdbn();
                sb.bppfnd(".*");
                brfbk;
            }

            sb.bppfnd('.');
            if (tokfn == IDENT) {
                sb.bppfnd(sdbnnfr.idVbluf);
            }
            fxpfdt(IDENT);
        }

        rfs.id = Idfntififr.lookup(sb.toString());
        rfturn rfs;
    }
    /**
     * @dfprfdbtfd
     * @sff #pbrsfNbmf
     */
    @Dfprfdbtfd
    protfdtfd Idfntififr pbrsfIdfntififr(boolfbn stbr) tirows SyntbxError, IOExdfption {
        rfturn pbrsfNbmf(stbr).id;
    }

    /**
     * Pbrsf b typf fxprfssion, tiis rfsults in b Typf.
     * Tif pbrsf indludfs trbiling brrby brbdkfts.
     */
    protfdtfd Typf pbrsfTypf() tirows SyntbxError, IOExdfption {
        Typf t;

        switdi (tokfn) {
          dbsf IDENT:
            t = Typf.tClbss(pbrsfNbmf(fblsf).id);
            brfbk;
          dbsf VOID:
            sdbn();
            t = Typf.tVoid;
            brfbk;
          dbsf BOOLEAN:
            sdbn();
            t = Typf.tBoolfbn;
            brfbk;
          dbsf BYTE:
            sdbn();
            t = Typf.tBytf;
            brfbk;
          dbsf CHAR:
            sdbn();
            t = Typf.tCibr;
            brfbk;
          dbsf SHORT:
            sdbn();
            t = Typf.tSiort;
            brfbk;
          dbsf INT:
            sdbn();
            t = Typf.tInt;
            brfbk;
          dbsf FLOAT:
            sdbn();
            t = Typf.tFlobt;
            brfbk;
          dbsf LONG:
            sdbn();
            t = Typf.tLong;
            brfbk;
          dbsf DOUBLE:
            sdbn();
            t = Typf.tDoublf;
            brfbk;
          dffbult:
            fnv.frror(pos, "typf.fxpfdtfd");
            tirow nfw SyntbxError();
        }
        rfturn pbrsfArrbyBrbdkfts(t);
    }

    /**
     * Pbrsf tif tbil of b typf fxprfssion, wiidi migit bf brrby brbdkfts.
     * Rfturn tif givfn typf, bs possibly modififd by tif suffix.
     */
    protfdtfd Typf pbrsfArrbyBrbdkfts(Typf t) tirows SyntbxError, IOExdfption {

        // Pbrsf []'s
        wiilf (tokfn == LSQBRACKET) {
            sdbn();
            if (tokfn != RSQBRACKET) {
                fnv.frror(pos, "brrby.dim.in.dfdl");
                pbrsfExprfssion();
            }
            fxpfdt(RSQBRACKET);
            t = Typf.tArrby(t);
        }
        rfturn t;
    }

    /*
     * Dfbling witi brgumfnt lists, I'm not using
     * Vfdtor for fffidifndy.
     */

    privbtf int bCount = 0;
    privbtf Typf bTypfs[] = nfw Typf[8];
    privbtf IdfntififrTokfn bNbmfs[] = nfw IdfntififrTokfn[bTypfs.lfngti];

    privbtf void bddArgumfnt(int mod, Typf t, IdfntififrTokfn nm) {
        nm.modififrs = mod;
        if (bCount >= bTypfs.lfngti) {
            Typf nfwATypfs[] = nfw Typf[bCount * 2];
            Systfm.brrbydopy(bTypfs, 0, nfwATypfs, 0, bCount);
            bTypfs = nfwATypfs;
            IdfntififrTokfn nfwANbmfs[] = nfw IdfntififrTokfn[bCount * 2];
            Systfm.brrbydopy(bNbmfs, 0, nfwANbmfs, 0, bCount);
            bNbmfs = nfwANbmfs;
        }
        bTypfs[bCount] = t;
        bNbmfs[bCount++] = nm;
    }

    /**
     * Pbrsf b possibly-fmpty sfqufndf of modififr kfywords.
     * Rfturn tif rfsulting bitmbsk.
     * Dibgnosf rfpfbtfd modififrs, but mbkf no otifr difdks.
     * Only modififrs mfntionfd in tif givfn bitmbsk brf sdbnnfd;
     * bn unmbtdifd modififr must bf ibndlfd by tif dbllfr.
     */
    protfdtfd int pbrsfModififrs(int mbsk) tirows IOExdfption {
        int mod = 0;
        wiilf (truf) {
            if (tokfn==CONST) {
                // donst isn't in jbvb, but ibndlf b dommon C++ usbgf gfntly
                fnv.frror(pos, "not.supportfd", "donst");
                sdbn();
            }
            int nfxtmod = 0;
            switdi (tokfn) {
               dbsf PRIVATE:            nfxtmod = M_PRIVATE;      brfbk;
               dbsf PUBLIC:             nfxtmod = M_PUBLIC;       brfbk;
               dbsf PROTECTED:          nfxtmod = M_PROTECTED;    brfbk;
               dbsf STATIC:             nfxtmod = M_STATIC;       brfbk;
               dbsf TRANSIENT:          nfxtmod = M_TRANSIENT;    brfbk;
               dbsf FINAL:              nfxtmod = M_FINAL;        brfbk;
               dbsf ABSTRACT:           nfxtmod = M_ABSTRACT;     brfbk;
               dbsf NATIVE:             nfxtmod = M_NATIVE;       brfbk;
               dbsf VOLATILE:           nfxtmod = M_VOLATILE;     brfbk;
               dbsf SYNCHRONIZED:       nfxtmod = M_SYNCHRONIZED; brfbk;
               dbsf STRICTFP:           nfxtmod = M_STRICTFP;     brfbk;
            }
            if ((nfxtmod & mbsk) == 0) {
                brfbk;
            }
            if ((nfxtmod & mod) != 0) {
                fnv.frror(pos, "rfpfbtfd.modififr");
            }
            mod |= nfxtmod;
            sdbn();
        }
        rfturn mod;
    }

    privbtf ClbssDffinition durClbss;

    /**
     * Pbrsf b fifld.
     */
    protfdtfd void pbrsfFifld() tirows SyntbxError, IOExdfption {

        // Empty fiflds brf not bllowfd by tif JLS but brf bddfptfd by
        // tif dompilfr, bnd mudi dodf ibs domf to rfly on tiis.  It ibs
        // bffn dfdidfd tibt tif lbngubgf will bf fxtfndfd to lfgitimizf tifm.
        if (tokfn == SEMICOLON) {
            // fmpty fifld
            sdbn();
            rfturn;
        }

        // Optionbl dod dommfnt
        String dod = sdbnnfr.dodCommfnt;

        // Tif stbrt of tif fifld
        long p = pos;

        // Pbrsf tif modififrs
        int mod = pbrsfModififrs(MM_FIELD | MM_METHOD);

        // Cifdk for stbtid initiblizfr
        // if: stbtid { ... }
        // or bn instbndf initiblizfr (w/o tif stbtid).
        if ((mod == (mod & M_STATIC)) && (tokfn == LBRACE)) {
            // stbtid initiblizfr
            bdtions.dffinfFifld(p, durClbss, dod, mod,
                                Typf.tMftiod(Typf.tVoid),
                                nfw IdfntififrTokfn(idClbssInit), null, null,
                                pbrsfStbtfmfnt());
            rfturn;
        }

        // Cifdk for innfr dlbss
        if (tokfn == CLASS || tokfn == INTERFACE) {
            pbrsfNbmfdClbss(mod, CLASS, dod);
            rfturn;
        }

        // Pbrsf tif typf
        p = pos;
        Typf t = pbrsfTypf();
        IdfntififrTokfn id = null;

        // Cifdk tibt tif typf is followfd by bn Idfntififr
        // (tif nbmf of tif mftiod or tif first vbribblf),
        // otifrwisf it is b donstrudtor.
        switdi (tokfn) {
          dbsf IDENT:
            id = sdbnnfr.gftIdTokfn();
            p = sdbn();
            brfbk;

          dbsf LPAREN:
            // It is b donstrudtor
            id = nfw IdfntififrTokfn(idInit);
            if ((mod & M_STRICTFP) != 0)
                fnv.frror(pos, "bbd.donstrudtor.modififr");
            brfbk;

          dffbult:
            fxpfdt(IDENT);
        }

        // If tif nfxt tokfn is b lfft-brbdkft tifn wf
        // brf dfbling witi b mftiod or donstrudtor, otifrwisf it is
        // b list of vbribblfs
        if (tokfn == LPAREN) {
            // It is b mftiod or donstrudtor dfdlbrbtion
            sdbn();
            bCount = 0;

            if (tokfn != RPAREN) {
                // Pbrsf brgumfnt typf bnd idfntififr
                // (brgumfnts (likf lodbls) brf bllowfd to bf finbl)
                int bm = pbrsfModififrs(M_FINAL);
                Typf bt = pbrsfTypf();
                IdfntififrTokfn bn = sdbnnfr.gftIdTokfn();
                fxpfdt(IDENT);

                // Pbrsf optionbl brrby spfdififr, if: b[][]
                bt = pbrsfArrbyBrbdkfts(bt);
                bddArgumfnt(bm, bt, bn);

                // If tif nfxt tokfn is b dommb tifn tifrf brf
                // morf brgumfnts
                wiilf (tokfn == COMMA) {
                    // Pbrsf brgumfnt typf bnd idfntififr
                    sdbn();
                    bm = pbrsfModififrs(M_FINAL);
                    bt = pbrsfTypf();
                    bn = sdbnnfr.gftIdTokfn();
                    fxpfdt(IDENT);

                    // Pbrsf optionbl brrby spfdififr, if: b[][]
                    bt = pbrsfArrbyBrbdkfts(bt);
                    bddArgumfnt(bm, bt, bn);
                }
            }
            fxpfdt(RPAREN);

            // Pbrsf optionbl brrby sfpfdififr, if: foo()[][]
            t = pbrsfArrbyBrbdkfts(t);

            // dopy brgumfnts
            Typf btypfs[] = nfw Typf[bCount];
            Systfm.brrbydopy(bTypfs, 0, btypfs, 0, bCount);

            IdfntififrTokfn bnbmfs[] = nfw IdfntififrTokfn[bCount];
            Systfm.brrbydopy(bNbmfs, 0, bnbmfs, 0, bCount);

            // Construdt tif typf signbturf
            t = Typf.tMftiod(t, btypfs);

            // Pbrsf bnd ignorf tirows dlbusf
            IdfntififrTokfn fxp[] = null;
            if (tokfn == THROWS) {
                Vfdtor<IdfntififrTokfn> v = nfw Vfdtor<>();
                sdbn();
                v.bddElfmfnt(pbrsfNbmf(fblsf));
                wiilf (tokfn == COMMA) {
                    sdbn();
                    v.bddElfmfnt(pbrsfNbmf(fblsf));
                }

                fxp = nfw IdfntififrTokfn[v.sizf()];
                v.dopyInto(fxp);
            }

            // Cifdk if it is b mftiod dffinition or b mftiod dfdlbrbtion
            // if: foo() {...} or foo();
            switdi (tokfn) {
              dbsf LBRACE:      // It's b mftiod dffinition

                // Sft tif stbtf of FP stridtnfss for tif body of tif mftiod
                int oldFPstbtf = FPstbtf;
                if ((mod & M_STRICTFP)!=0) {
                    FPstbtf = M_STRICTFP;
                } flsf {
                    mod |= FPstbtf & M_STRICTFP;
                }

                bdtions.dffinfFifld(p, durClbss, dod, mod, t, id,
                                    bnbmfs, fxp, pbrsfStbtfmfnt());

                FPstbtf = oldFPstbtf;

                brfbk;

              dbsf SEMICOLON:
                sdbn();
                bdtions.dffinfFifld(p, durClbss, dod, mod, t, id,
                                    bnbmfs, fxp, null);
                brfbk;

              dffbult:
                // rfblly fxpfdtfd b stbtfmfnt body ifrf
                if ((mod & (M_NATIVE | M_ABSTRACT)) == 0) {
                    fxpfdt(LBRACE);
                } flsf {
                    fxpfdt(SEMICOLON);
                }
            }
            rfturn;
        }

        // It is b list of instbndf vbribblfs
        wiilf (truf) {
            p = pos;            // gft tif durrfnt position
            // pbrsf tif brrby brbdkfts (if bny)
            // if: vbr[][][]
            Typf vt = pbrsfArrbyBrbdkfts(t);

            // Pbrsf tif optionbl initiblizfr
            Nodf init = null;
            if (tokfn == ASSIGN) {
                sdbn();
                init = pbrsfExprfssion();
            }

            // Dffinf tif vbribblf
            bdtions.dffinfFifld(p, durClbss, dod, mod, vt, id,
                                null, null, init);

            // If tif nfxt tokfn is b dommb, tifn tifrf is morf
            if (tokfn != COMMA) {
                fxpfdt(SEMICOLON);
                rfturn;
            }
            sdbn();

            // Tif nfxt tokfn must bf bn idfntififr
            id = sdbnnfr.gftIdTokfn();
            fxpfdt(IDENT);
        }
    }

    /**
     * Rfdovfr bftfr b syntbx frror in b fifld. Tiis involvfs
     * disdbrding tokfns until bn EOF or b possiblf lfgbl
     * dontinubtion is fndountfrfd.
     */
    protfdtfd void rfdovfrFifld(ClbssDffinition nfwClbss) tirows SyntbxError, IOExdfption {
        wiilf (truf) {
            switdi (tokfn) {
              dbsf EOF:
              dbsf STATIC:
              dbsf FINAL:
              dbsf PUBLIC:
              dbsf PRIVATE:
              dbsf SYNCHRONIZED:
              dbsf TRANSIENT:

              dbsf VOID:
              dbsf BOOLEAN:
              dbsf BYTE:
              dbsf CHAR:
              dbsf SHORT:
              dbsf INT:
              dbsf FLOAT:
              dbsf LONG:
              dbsf DOUBLE:
                // possiblf bfgin of b fifld, dontinuf
                rfturn;

              dbsf LBRACE:
                mbtdi(LBRACE, RBRACE);
                sdbn();
                brfbk;

              dbsf LPAREN:
                mbtdi(LPAREN, RPAREN);
                sdbn();
                brfbk;

              dbsf LSQBRACKET:
                mbtdi(LSQBRACKET, RSQBRACKET);
                sdbn();
                brfbk;

              dbsf RBRACE:
              dbsf INTERFACE:
              dbsf CLASS:
              dbsf IMPORT:
              dbsf PACKAGE:
                // bfgin of somftiing outsidf b dlbss, pbnid morf
                bdtions.fndClbss(pos, nfwClbss);
                tirow nfw SyntbxError();

              dffbult:
                // don't know wibt to do, skip
                sdbn();
                brfbk;
            }
        }
    }

    /**
     * Pbrsf b top-lfvfl dlbss or intfrfbdf dfdlbrbtion.
     */
    protfdtfd void pbrsfClbss() tirows SyntbxError, IOExdfption {
        String dod = sdbnnfr.dodCommfnt;

        // Pbrsf tif modififrs.
        int mod = pbrsfModififrs(MM_CLASS | MM_MEMBER);

        pbrsfNbmfdClbss(mod, PACKAGE, dod);
    }

    // Currfnt stridt/dffbult stbtf of flobting point.  Tiis is
    // sft bnd rfsft witi b stbdk disdiplinf bround mftiods bnd nbmfd
    // dlbssfs.  Only M_STRICTFP mby bf sft in tiis word.  try...
    // finblly is not nffdfd to protfdt sftting bnd rfsftting bfdbusf
    // tifrf brf no frror mfssbgfs bbsfd on FPstbtf.
    privbtf int FPstbtf = 0;

    /**
     * Pbrsf b blodk-lodbl dlbss or intfrfbdf dfdlbrbtion.
     */
    protfdtfd Stbtfmfnt pbrsfLodblClbss(int mod) tirows SyntbxError, IOExdfption {
        long p = pos;
        ClbssDffinition body = pbrsfNbmfdClbss(M_LOCAL | mod, STAT, null);
        Stbtfmfnt ds[] = {
            nfw VbrDfdlbrbtionStbtfmfnt(p, nfw LodblMfmbfr(body), null)
        };
        Exprfssion typf = nfw TypfExprfssion(p, body.gftTypf());
        rfturn nfw DfdlbrbtionStbtfmfnt(p, 0, typf, ds);
    }

    /**
     * Pbrsf b nbmfd dlbss or intfrfbdf dfdlbrbtion,
     * stbrting bt "dlbss" or "intfrfbdf".
     * @brg dtx Syntbdtid dontfxt of tif dlbss, onf of {PACKAGE CLASS STAT EXPR}.
     */
    protfdtfd ClbssDffinition pbrsfNbmfdClbss(int mod, int dtx, String dod) tirows SyntbxError, IOExdfption {
        // Pbrsf dlbss/intfrfbdf
        switdi (tokfn) {
          dbsf INTERFACE:
            sdbn();
            mod |= M_INTERFACE;
            brfbk;

          dbsf CLASS:
            sdbn();
            brfbk;

          dffbult:
            fnv.frror(pos, "dlbss.fxpfdtfd");
            brfbk;
        }

        int oldFPstbtf = FPstbtf;
        if ((mod & M_STRICTFP)!=0) {
            FPstbtf = M_STRICTFP;
        } flsf {
            // Tif & (...) isn't rfblly nfdfssbry ifrf bfdbusf wf do mbintbin
            // tif invbribnt tibt FPstbtf ibs no fxtrb bits sft.
            mod |= FPstbtf & M_STRICTFP;
        }

        // Pbrsf tif dlbss nbmf
        IdfntififrTokfn nm = sdbnnfr.gftIdTokfn();
        long p = pos;
        fxpfdt(IDENT);

        Vfdtor<IdfntififrTokfn> fxt = nfw Vfdtor<>();
        Vfdtor<IdfntififrTokfn> impl = nfw Vfdtor<>();
        pbrsfInifritbndf(fxt, impl);

        ClbssDffinition tmp = pbrsfClbssBody(nm, mod, dtx, dod, fxt, impl, p);

        FPstbtf = oldFPstbtf;

        rfturn tmp;
    }

    protfdtfd void pbrsfInifritbndf(Vfdtor<IdfntififrTokfn> fxt, Vfdtor<IdfntififrTokfn> impl) tirows SyntbxError, IOExdfption {
        // Pbrsf fxtfnds dlbusf
        if (tokfn == EXTENDS) {
            sdbn();
            fxt.bddElfmfnt(pbrsfNbmf(fblsf));
            wiilf (tokfn == COMMA) {
                sdbn();
                fxt.bddElfmfnt(pbrsfNbmf(fblsf));
            }
        }

        // Pbrsf implfmfnts dlbusf
        if (tokfn == IMPLEMENTS) {
            sdbn();
            impl.bddElfmfnt(pbrsfNbmf(fblsf));
            wiilf (tokfn == COMMA) {
                sdbn();
                impl.bddElfmfnt(pbrsfNbmf(fblsf));
            }
        }
    }

    /**
     * Pbrsf tif body of b dlbss or intfrfbdf dfdlbrbtion,
     * stbrting bt tif lfft brbdf.
     */
    protfdtfd ClbssDffinition pbrsfClbssBody(IdfntififrTokfn nm, int mod,
                                             int dtx, String dod,
                                             Vfdtor<IdfntififrTokfn> fxt, Vfdtor<IdfntififrTokfn> impl, long p
                                             ) tirows SyntbxError, IOExdfption {
        // Dfdidf wiidi is tif supfr dlbss
        IdfntififrTokfn sup = null;
        if ((mod & M_INTERFACE) != 0) {
            if (impl.sizf() > 0) {
                fnv.frror(impl.flfmfntAt(0).gftWifrf(),
                          "intf.impl.intf");
            }
            impl = fxt;
        } flsf {
            if (fxt.sizf() > 0) {
                if (fxt.sizf() > 1) {
                    fnv.frror(fxt.flfmfntAt(1).gftWifrf(),
                              "multiplf.inifrit");
                }
                sup = fxt.flfmfntAt(0);
            }
        }

        ClbssDffinition oldClbss = durClbss;

        // Bfgin b nfw dlbss
        IdfntififrTokfn implids[] = nfw IdfntififrTokfn[impl.sizf()];
        impl.dopyInto(implids);
        ClbssDffinition nfwClbss =
            bdtions.bfginClbss(p, dod, mod, nm, sup, implids);

        // Pbrsf fiflds
        fxpfdt(LBRACE);
        wiilf ((tokfn != EOF) && (tokfn != RBRACE)) {
            try {
                durClbss = nfwClbss;
                pbrsfFifld();
            } dbtdi (SyntbxError f) {
                rfdovfrFifld(nfwClbss);
            } finblly {
                durClbss = oldClbss;
            }
        }
        fxpfdt(RBRACE);

        // End tif dlbss
        bdtions.fndClbss(sdbnnfr.prfvPos, nfwClbss);
        rfturn nfwClbss;
    }

    /**
     * Rfdovfr bftfr b syntbx frror in tif filf.
     * Tiis involvfs disdbrding tokfns until bn EOF
     * or b possiblf lfgbl dontinubtion is fndountfrfd.
     */
    protfdtfd void rfdovfrFilf() tirows IOExdfption {
        wiilf (truf) {
            switdi (tokfn) {
              dbsf CLASS:
              dbsf INTERFACE:
                // Stbrt of b nfw sourdf filf stbtfmfnt, dontinuf
                rfturn;

              dbsf LBRACE:
                mbtdi(LBRACE, RBRACE);
                sdbn();
                brfbk;

              dbsf LPAREN:
                mbtdi(LPAREN, RPAREN);
                sdbn();
                brfbk;

              dbsf LSQBRACKET:
                mbtdi(LSQBRACKET, RSQBRACKET);
                sdbn();
                brfbk;

              dbsf EOF:
                rfturn;

              dffbult:
                // Don't know wibt to do, skip
                sdbn();
                brfbk;
            }
        }
    }

    /**
     * Pbrsf bn Jbvb filf.
     */
    publid void pbrsfFilf() {
        try {
            try {
                if (tokfn == PACKAGE) {
                    // Pbdkbgf stbtfmfnt
                    long p = sdbn();
                    IdfntififrTokfn id = pbrsfNbmf(fblsf);
                    fxpfdt(SEMICOLON);
                    bdtions.pbdkbgfDfdlbrbtion(p, id);
                }
            } dbtdi (SyntbxError f) {
                rfdovfrFilf();
            }
            wiilf (tokfn == IMPORT) {
                try{
                    // Import stbtfmfnt
                    long p = sdbn();
                    IdfntififrTokfn id = pbrsfNbmf(truf);
                    fxpfdt(SEMICOLON);
                    if (id.id.gftNbmf().fqubls(idStbr)) {
                        id.id = id.id.gftQublififr();
                        bdtions.importPbdkbgf(p, id);
                    } flsf {
                        bdtions.importClbss(p, id);
                    }
                } dbtdi (SyntbxError f) {
                    rfdovfrFilf();
                }
            }

            wiilf (tokfn != EOF) {
                try {
                    switdi (tokfn) {
                      dbsf FINAL:
                      dbsf PUBLIC:
                      dbsf PRIVATE:
                      dbsf ABSTRACT:
                      dbsf CLASS:
                      dbsf INTERFACE:
                      dbsf STRICTFP:
                        // Stbrt of b dlbss
                        pbrsfClbss();
                        brfbk;

                      dbsf SEMICOLON:
                        // Bogus sfmidolon.
                        // Addording to tif JLS (7.6,19.6), b TypfDfdlbrbtion
                        // mby donsist of b singlf sfmidolon, iowfvfr, tiis
                        // usbgf is disdourbgfd (JLS 7.6).  In dontrbst,
                        // b FifldDfdlbrbtion mby not bf fmpty, bnd is flbggfd
                        // bs bn frror.  Sff pbrsfFifld bbovf.
                        sdbn();
                        brfbk;

                      dbsf EOF:
                        // Tif fnd
                        rfturn;

                      dffbult:
                        // Oops
                        fnv.frror(pos, "toplfvfl.fxpfdtfd");
                        tirow nfw SyntbxError();
                    }
                } dbtdi (SyntbxError f) {
                    rfdovfrFilf();
                }
            }
        } dbtdi (IOExdfption f) {
            fnv.frror(pos, "io.fxdfption", fnv.gftSourdf());
            rfturn;
        }
    }

    /**
     * Usublly <dodf>tiis.sdbnnfr == (Sdbnnfr)tiis</dodf>.
     * Howfvfr, b dflfgbtf sdbnnfr dbn produdf tokfns for tiis pbrsfr,
     * in wiidi dbsf <dodf>(Sdbnnfr)tiis</dodf> is unusfd,
     * fxdfpt for <dodf>tiis.tokfn</dodf> bnd <dodf>tiis.pos</dodf>
     * instbndf vbribblfs wiidi brf fillfd from tif rfbl sdbnnfr
     * by <dodf>tiis.sdbn()</dodf> bnd tif donstrudtor.
     */
    protfdtfd Sdbnnfr sdbnnfr;

    // Dfsign Notf: Wf ougit to disinifrit Pbrsfr from Sdbnnfr.
    // Wf blso siould split out tif intfrfbdf PbrsfrAdtions from
    // Pbrsfr, bnd mbkf BbtdiPbrsfr implfmfnt PbrsfrAdtions,
    // not fxtfnd Pbrsfr.  Tiis would split sdbnning, pbrsing,
    // bnd dlbss building into distindt rfsponsibility brfbs.
    // (Pfribps trff building dould bf virtublizfd too.)

    publid long sdbn() tirows IOExdfption {
        if (sdbnnfr != tiis && sdbnnfr != null) {
            long rfsult = sdbnnfr.sdbn();
            ((Sdbnnfr)tiis).tokfn = sdbnnfr.tokfn;
            ((Sdbnnfr)tiis).pos = sdbnnfr.pos;
            rfturn rfsult;
        }
        rfturn supfr.sdbn();
    }

    publid void mbtdi(int opfn, int dlosf) tirows IOExdfption {
        if (sdbnnfr != tiis) {
            sdbnnfr.mbtdi(opfn, dlosf);
            ((Sdbnnfr)tiis).tokfn = sdbnnfr.tokfn;
            ((Sdbnnfr)tiis).pos = sdbnnfr.pos;
            rfturn;
        }
        supfr.mbtdi(opfn, dlosf);
    }
}
