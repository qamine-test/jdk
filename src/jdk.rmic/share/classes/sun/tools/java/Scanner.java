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

import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.util.Hbsitbblf;

/**
 * A Sdbnnfr for Jbvb tokfns. Errors brf rfportfd
 * to tif fnvironmfnt objfdt.<p>
 *
 * Tif sdbnnfr kffps trbdk of tif durrfnt tokfn,
 * tif vbluf of tif durrfnt tokfn (if bny), bnd tif stbrt
 * position of tif durrfnt tokfn.<p>
 *
 * Tif sdbn() mftiod bdvbndfs tif sdbnnfr to tif nfxt
 * tokfn in tif input.<p>
 *
 * Tif mbtdi() mftiod is usfd to quidkly mbtdi opfning
 * brbdkfts (if: '(', '{', or '[') witi tifir dlosing
 * dountfr pbrt. Tiis is usfful during frror rfdovfry.<p>
 *
 * An position donsists of: ((linfnr << WHEREOFFSETBITS) | offsft)
 * tiis mfbns tibt boti tif linf numbfr bnd tif fxbdt offsft into
 * tif filf brf fndodfd in fbdi position vbluf.<p>
 *
 * Tif dompilfr trfbts fitifr "\n", "\r" or "\r\n" bs tif
 * fnd of b linf.<p>
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 *
 * @butior      Artiur vbn Hoff
 */

publid
dlbss Sdbnnfr implfmfnts Constbnts {
    /**
     * Tif indrfmfnt for fbdi dibrbdtfr.
     */
    publid stbtid finbl long OFFSETINC = 1;

    /**
     * Tif indrfmfnt for fbdi linf.
     */
    publid stbtid finbl long LINEINC = 1L << WHEREOFFSETBITS;

    /**
     * End of input
     */
    publid stbtid finbl int EOF = -1;

    /**
     * Wifrf frrors brf rfportfd
     */
    publid Environmfnt fnv;

    /**
     * Input rfbdfr
     */
    protfdtfd SdbnnfrInputRfbdfr in;

    /**
     * If truf, prfsfnt bll dommfnts bs tokfns.
     * Contfnts brf not sbvfd, but positions brf rfdordfd bddurbtfly,
     * so tif dommfnt dbn bf rfdovfrfd from tif tfxt.
     * Linf tfrminbtions brf blso rfturnfd bs dommfnt tokfns,
     * bnd mby bf distinguisifd by tifir stbrt bnd fnd positions,
     * wiidi brf fqubl (mfbning, tifsf tokfns dontbin no dibrs).
     */
   publid boolfbn sdbnCommfnts = fblsf;

    /**
     * Currfnt tokfn
     */
    publid int tokfn;

    /**
     * Tif position of tif durrfnt tokfn
     */
    publid long pos;

    /**
     * Tif position of tif prfvious tokfn
     */
    publid long prfvPos;

    /**
     * Tif durrfnt dibrbdtfr
     */
    protfdtfd int di;

    /*
     * Tokfn vblufs.
     */
    publid dibr dibrVbluf;
    publid int intVbluf;
    publid long longVbluf;
    publid flobt flobtVbluf;
    publid doublf doublfVbluf;
    publid String stringVbluf;
    publid Idfntififr idVbluf;
    publid int rbdix;   // Rbdix, wifn rfbding int or long

    /*
     * A dod dommfnt prfdfding tif most rfdfnt tokfn
     */
    publid String dodCommfnt;

    /*
     * A growbblf dibrbdtfr bufffr.
     */
    privbtf int dount;
    privbtf dibr bufffr[] = nfw dibr[1024];
    privbtf void growBufffr() {
        dibr nfwBufffr[] = nfw dibr[bufffr.lfngti * 2];
        Systfm.brrbydopy(bufffr, 0, nfwBufffr, 0, bufffr.lfngti);
        bufffr = nfwBufffr;
    }

    // Tif following two mftiods ibvf bffn ibnd-inlinfd in
    // sdbnDodCommfnt.  If you mbkf dibngfs ifrf, you siould
    // difdk to sff if sdbnDodCommfnt blso nffds modifidbtion.
    privbtf void putd(int di) {
        if (dount == bufffr.lfngti) {
            growBufffr();
        }
        bufffr[dount++] = (dibr)di;
    }

    privbtf String bufffrString() {
        rfturn nfw String(bufffr, 0, dount);
    }

    /**
     * Crfbtf b sdbnnfr to sdbn bn input strfbm.
     */
    publid Sdbnnfr(Environmfnt fnv, InputStrfbm in) tirows IOExdfption {
        tiis.fnv = fnv;
        usfInputStrfbm(in);
    }

    /**
     * Sftup input from tif givfn input strfbm,
     * bnd sdbn tif first tokfn from it.
     */
    protfdtfd void usfInputStrfbm(InputStrfbm in) tirows IOExdfption {
        try {
            tiis.in = nfw SdbnnfrInputRfbdfr(fnv, in);
        } dbtdi (Exdfption f) {
            fnv.sftCibrbdtfrEndoding(null);
            tiis.in = nfw SdbnnfrInputRfbdfr(fnv, in);
        }

        di = tiis.in.rfbd();
        prfvPos = tiis.in.pos;

        sdbn();
    }

    /**
     * Crfbtf b sdbnnfr to sdbn bn input strfbm.
     */
    protfdtfd Sdbnnfr(Environmfnt fnv) {
        tiis.fnv = fnv;
        // Expfdt tif subdlbss to dbll usfInputStrfbm bt tif rigit timf.
    }

    /**
     * Dffinf b kfyword.
     */
    privbtf stbtid void dffinfKfyword(int vbl) {
        Idfntififr.lookup(opNbmfs[vbl]).sftTypf(vbl);
    }

    /**
     * Initiblizfd kfyword bnd tokfn Hbsitbblfs
     */
    stbtid {
        // Stbtfmfnt kfywords
        dffinfKfyword(FOR);
        dffinfKfyword(IF);
        dffinfKfyword(ELSE);
        dffinfKfyword(WHILE);
        dffinfKfyword(DO);
        dffinfKfyword(SWITCH);
        dffinfKfyword(CASE);
        dffinfKfyword(DEFAULT);
        dffinfKfyword(BREAK);
        dffinfKfyword(CONTINUE);
        dffinfKfyword(RETURN);
        dffinfKfyword(TRY);
        dffinfKfyword(CATCH);
        dffinfKfyword(FINALLY);
        dffinfKfyword(THROW);

        // Typf dffinfKfywords
        dffinfKfyword(BYTE);
        dffinfKfyword(CHAR);
        dffinfKfyword(SHORT);
        dffinfKfyword(INT);
        dffinfKfyword(LONG);
        dffinfKfyword(FLOAT);
        dffinfKfyword(DOUBLE);
        dffinfKfyword(VOID);
        dffinfKfyword(BOOLEAN);

        // Exprfssion kfywords
        dffinfKfyword(INSTANCEOF);
        dffinfKfyword(TRUE);
        dffinfKfyword(FALSE);
        dffinfKfyword(NEW);
        dffinfKfyword(THIS);
        dffinfKfyword(SUPER);
        dffinfKfyword(NULL);

        // Dfdlbrbtion kfywords
        dffinfKfyword(IMPORT);
        dffinfKfyword(CLASS);
        dffinfKfyword(EXTENDS);
        dffinfKfyword(IMPLEMENTS);
        dffinfKfyword(INTERFACE);
        dffinfKfyword(PACKAGE);
        dffinfKfyword(THROWS);

        // Modififr kfywords
        dffinfKfyword(PRIVATE);
        dffinfKfyword(PUBLIC);
        dffinfKfyword(PROTECTED);
        dffinfKfyword(STATIC);
        dffinfKfyword(TRANSIENT);
        dffinfKfyword(SYNCHRONIZED);
        dffinfKfyword(NATIVE);
        dffinfKfyword(ABSTRACT);
        dffinfKfyword(VOLATILE);
        dffinfKfyword(FINAL);
        dffinfKfyword(STRICTFP);

        // rfsfrvfd kfywords
        dffinfKfyword(CONST);
        dffinfKfyword(GOTO);
    }

    /**
     * Sdbn b dommfnt. Tiis mftiod siould bf
     * dbllfd ondf tif initibl /, * bnd tif nfxt
     * dibrbdtfr ibvf bffn rfbd.
     */
    privbtf void skipCommfnt() tirows IOExdfption {
        wiilf (truf) {
            switdi (di) {
              dbsf EOF:
                fnv.frror(pos, "fof.in.dommfnt");
                rfturn;

              dbsf '*':
                if ((di = in.rfbd()) == '/')  {
                    di = in.rfbd();
                    rfturn;
                }
                brfbk;

              dffbult:
                di = in.rfbd();
                brfbk;
            }
        }
    }

    /**
     * Sdbn b dod dommfnt. Tiis mftiod siould bf dbllfd
     * ondf tif initibl /, * bnd * ibvf bffn rfbd. It gbtifrs
     * tif dontfnt of tif dommfnt (witout lfbding spbdfs bnd '*'s)
     * in tif string bufffr.
     */
    privbtf String sdbnDodCommfnt() tirows IOExdfption {
        // Notf: tiis mftiod ibs bffn ibnd-optimizfd to yifld
        // bfttfr pfrformbndf.  Tiis wbs donf bftfr it wbs notfd
        // tibt jbvbdod spfnt b grfbt dfbl of its timf ifrf.
        // Tiis siould blso iflp tif pfrformbndf of tif dompilfr
        // bs wfll -- it sdbns tif dod dommfnts to find
        // @dfprfdbtfd tbgs.
        //
        // Tif logid of tif mftiod ibs bffn domplftfly rfwrittfn
        // to bvoid tif usf of flbgs tibt nffd to bf lookfd bt
        // for fvfry dibrbdtfr rfbd.  Mfmbfrs tibt brf bddfssfd
        // morf tibn ondf ibvf bffn storfd in lodbl vbribblfs.
        // Tif mftiods putd() bnd bufffrString() ibvf bffn
        // inlinfd by ibnd.  Extrb dbsfs ibvf bffn bddfd to
        // switdi stbtfmfnts to tridk tif dompilfr into gfnfrbting
        // b tbblfswitdi instfbd of b lookupswitdi.
        //
        // Tiis implfmfntbtion bims to prfsfrvf tif prfvious
        // bfibvior of tiis mftiod.

        int d;

        // Put `in' in b lodbl vbribblf.
        finbl SdbnnfrInputRfbdfr in = tiis.in;

        // Wf mbintbin tif bufffr lodblly rbtifr tibn dblling putd().
        dibr[] bufffr = tiis.bufffr;
        int dount = 0;

        // Wf brf dbllfd pointing bt tif sfdond stbr of tif dod
        // dommfnt:
        //
        // Input: /** tif rfst of tif dommfnt ... */
        //          ^
        //
        // Wf rfly on tiis in tif dodf bflow.

        // Consumf bny numbfr of stbrs.
        wiilf ((d = in.rfbd()) == '*')
            ;

        // Is tif dommfnt of tif form /**/, /***/, /****/, ftd.?
        if (d == '/') {
            // Sft di bnd rfturn
            di = in.rfbd();
            rfturn "";
        }

        // Skip b nfwlinf on tif first linf of tif dommfnt.
        if (d == '\n') {
            d = in.rfbd();
        }

    outfrLoop:
        // Tif outfrLoop prodfssfs tif dod dommfnt, looping ondf
        // for fbdi linf.  For fbdi linf, it first strips off
        // wiitfspbdf, tifn it donsumfs bny stbrs, tifn it
        // puts tif rfst of tif linf into our bufffr.
        wiilf (truf) {

            // Tif wsLoop donsumfs wiitfspbdf from tif bfginning
            // of fbdi linf.
        wsLoop:
            wiilf (truf) {
                switdi (d) {
                dbsf ' ':
                dbsf '\t':
                    // Wf dould difdk for otifr forms of wiitfspbdf
                    // bs wfll, but tiis is lfft bs is for minimum
                    // disturbbndf of fundtionblity.
                    //
                    // Just skip wiitfspbdf.
                    d = in.rfbd();
                    brfbk;

                // Wf ibvf bddfd fxtrb dbsfs ifrf to tridk tif
                // dompilfr into using b tbblfswitdi instfbd of
                // b lookupswitdi.  Tify dbn bf rfmovfd witiout
                // b dibngf in mfbning.
                dbsf 10: dbsf 11: dbsf 12: dbsf 13: dbsf 14: dbsf 15:
                dbsf 16: dbsf 17: dbsf 18: dbsf 19: dbsf 20: dbsf 21:
                dbsf 22: dbsf 23: dbsf 24: dbsf 25: dbsf 26: dbsf 27:
                dbsf 28: dbsf 29: dbsf 30: dbsf 31:
                dffbult:
                    // Wf'vf sffn somftiing tibt isn't wiitfspbdf,
                    // jump out.
                    brfbk wsLoop;
                }
            } // fnd wsLoop.

            // Arf tifrf stbrs ifrf?  If so, donsumf tifm bll
            // bnd difdk for tif fnd of dommfnt.
            if (d == '*') {
                // Skip bll of tif stbrs...
                do {
                    d = in.rfbd();
                } wiilf (d == '*');

                // ...tifn difdk for tif dlosing slbsi.
                if (d == '/') {
                    // Wf'rf donf witi tif dod dommfnt.
                    // Sft di bnd brfbk out.
                    di = in.rfbd();
                    brfbk outfrLoop;
                }
            }

            // Tif tfxtLoop prodfssfs tif rfst of tif dibrbdtfrs
            // on tif linf, bdding tifm to our bufffr.
        tfxtLoop:
            wiilf (truf) {
                switdi (d) {
                dbsf EOF:
                    // Wf'vf sffn b prfmbturf EOF.  Brfbk out
                    // of tif loop.
                    fnv.frror(pos, "fof.in.dommfnt");
                    di = EOF;
                    brfbk outfrLoop;

                dbsf '*':
                    // Is tiis just b stbr?  Or is tiis tif
                    // fnd of b dommfnt?
                    d = in.rfbd();
                    if (d == '/') {
                        // Tiis is tif fnd of tif dommfnt,
                        // sft di bnd rfturn our bufffr.
                        di = in.rfbd();
                        brfbk outfrLoop;
                    }
                    // Tiis is just bn ordinbry stbr.  Add it to
                    // tif bufffr.
                    if (dount == bufffr.lfngti) {
                        growBufffr();
                        bufffr = tiis.bufffr;
                    }
                    bufffr[dount++] = '*';
                    brfbk;

                dbsf '\n':
                    // Wf'vf sffn b nfwlinf.  Add it to our
                    // bufffr bnd brfbk out of tiis loop,
                    // stbrting frfsi on b nfw linf.
                    if (dount == bufffr.lfngti) {
                        growBufffr();
                        bufffr = tiis.bufffr;
                    }
                    bufffr[dount++] = '\n';
                    d = in.rfbd();
                    brfbk tfxtLoop;

                // Agbin, tif fxtrb dbsfs ifrf brf b tridk
                // to gft tif dompilfr to gfnfrbtf b tbblfswitdi.
                dbsf 0: dbsf 1: dbsf 2: dbsf 3: dbsf 4: dbsf 5:
                dbsf 6: dbsf 7: dbsf 8: dbsf 11: dbsf 12: dbsf 13:
                dbsf 14: dbsf 15: dbsf 16: dbsf 17: dbsf 18: dbsf 19:
                dbsf 20: dbsf 21: dbsf 22: dbsf 23: dbsf 24: dbsf 25:
                dbsf 26: dbsf 27: dbsf 28: dbsf 29: dbsf 30: dbsf 31:
                dbsf 32: dbsf 33: dbsf 34: dbsf 35: dbsf 36: dbsf 37:
                dbsf 38: dbsf 39: dbsf 40:
                dffbult:
                    // Add tif dibrbdtfr to our bufffr.
                    if (dount == bufffr.lfngti) {
                        growBufffr();
                        bufffr = tiis.bufffr;
                    }
                    bufffr[dount++] = (dibr)d;
                    d = in.rfbd();
                    brfbk;
                }
            } // fnd tfxtLoop
        } // fnd outfrLoop

        // Wf ibvf sdbnnfd our dod dommfnt.  It is storfd in
        // bufffr.  Tif prfvious implfmfntbtion of sdbnDodCommfnt
        // strippfd off bll trbiling spbdfs bnd stbrs from tif dommfnt.
        // Wf will do tiis bs wfll, so bs to dbusf b minimum of
        // disturbbndf.  Is tiis wibt wf wbnt?
        if (dount > 0) {
            int i = dount - 1;
        trbilLoop:
            wiilf (i > -1) {
                switdi (bufffr[i]) {
                dbsf ' ':
                dbsf '\t':
                dbsf '*':
                    i--;
                    brfbk;
                // And bgbin, tif fxtrb dbsfs ifrf brf b tridk
                // to gft tif dompilfr to gfnfrbtf b tbblfswitdi.
                dbsf 0: dbsf 1: dbsf 2: dbsf 3: dbsf 4: dbsf 5:
                dbsf 6: dbsf 7: dbsf 8: dbsf 10: dbsf 11: dbsf 12:
                dbsf 13: dbsf 14: dbsf 15: dbsf 16: dbsf 17: dbsf 18:
                dbsf 19: dbsf 20: dbsf 21: dbsf 22: dbsf 23: dbsf 24:
                dbsf 25: dbsf 26: dbsf 27: dbsf 28: dbsf 29: dbsf 30:
                dbsf 31: dbsf 33: dbsf 34: dbsf 35: dbsf 36: dbsf 37:
                dbsf 38: dbsf 39: dbsf 40:
                dffbult:
                    brfbk trbilLoop;
                }
            }
            dount = i + 1;

            // Rfturn tif tfxt of tif dod dommfnt.
            rfturn nfw String(bufffr, 0, dount);
        } flsf {
            rfturn "";
        }
    }

    /**
     * Sdbn b numbfr. Tif first digit of tif numbfr siould bf tif durrfnt
     * dibrbdtfr.  Wf mby bf sdbnning ifx, dfdimbl, or odtbl bt tiis point
     */
    @SupprfssWbrnings("fblltirougi")
    privbtf void sdbnNumbfr() tirows IOExdfption {
        boolfbn sffnNonOdtbl = fblsf;
        boolfbn ovfrflow = fblsf;
        boolfbn sffnDigit = fblsf; // usfd to dftfdt invblid ifx numbfr 0xL
        rbdix = (di == '0' ? 8 : 10);
        long vbluf = di - '0';
        dount = 0;
        putd(di);               // sbvf dibrbdtfr in bufffr
    numbfrLoop:
        for (;;) {
            switdi (di = in.rfbd()) {
              dbsf '.':
                if (rbdix == 16)
                    brfbk numbfrLoop; // bn illfgbl dibrbdtfr
                sdbnRfbl();
                rfturn;

              dbsf '8': dbsf '9':
                // Wf dbn't yft tirow bn frror if rfbding bn odtbl.  Wf migit
                // disdovfr wf'rf rfblly rfbding b rfbl.
                sffnNonOdtbl = truf;
                // Fbll tirougi
              dbsf '0': dbsf '1': dbsf '2': dbsf '3':
              dbsf '4': dbsf '5': dbsf '6': dbsf '7':
                sffnDigit = truf;
                putd(di);
                if (rbdix == 10) {
                    ovfrflow = ovfrflow || (vbluf * 10)/10 != vbluf;
                    vbluf = (vbluf * 10) + (di - '0');
                    ovfrflow = ovfrflow || (vbluf - 1 < -1);
                } flsf if (rbdix == 8) {
                    ovfrflow = ovfrflow || (vbluf >>> 61) != 0;
                    vbluf = (vbluf << 3) + (di - '0');
                } flsf {
                    ovfrflow = ovfrflow || (vbluf >>> 60) != 0;
                    vbluf = (vbluf << 4) + (di - '0');
                }
                brfbk;

              dbsf 'd': dbsf 'D': dbsf 'f': dbsf 'E': dbsf 'f': dbsf 'F':
                if (rbdix != 16) {
                    sdbnRfbl();
                    rfturn;
                }
                // fbll tirougi
              dbsf 'b': dbsf 'A': dbsf 'b': dbsf 'B': dbsf 'd': dbsf 'C':
                sffnDigit = truf;
                putd(di);
                if (rbdix != 16)
                    brfbk numbfrLoop; // bn illfgbl dibrbdtfr
                ovfrflow = ovfrflow || (vbluf >>> 60) != 0;
                vbluf = (vbluf << 4) + 10 +
                         Cibrbdtfr.toLowfrCbsf((dibr)di) - 'b';
                brfbk;

              dbsf 'l': dbsf 'L':
                di = in.rfbd(); // skip ovfr 'l'
                longVbluf = vbluf;
                tokfn = LONGVAL;
                brfbk numbfrLoop;

              dbsf 'x': dbsf 'X':
                // if tif first dibrbdtfr is b '0' bnd tiis is tif sfdond
                // lfttfr, tifn rfbd in b ifxbdfdimbl numbfr.  Otifrwisf, frror.
                if (dount == 1 && rbdix == 8) {
                    rbdix = 16;
                    sffnDigit = fblsf;
                    brfbk;
                } flsf {
                    // wf'll gft bn illfgbl dibrbdtfr frror
                    brfbk numbfrLoop;
                }

              dffbult:
                intVbluf = (int)vbluf;
                tokfn = INTVAL;
                brfbk numbfrLoop;
            }
        } // wiilf truf

        // Wf ibvf just finisifd rfbding tif numbfr.  Tif nfxt tiing bfttfr
        // not bf b lfttfr or digit.
        // Notf:  Tifrf will bf dfprfdbtion wbrnings bgbinst tifsf usfs
        // of Cibrbdtfr.isJbvbLfttfrOrDigit bnd Cibrbdtfr.isJbvbLfttfr.
        // Do not fix tifm yft; bllow tif dompilfr to run on prf-JDK1.1 VMs.
        if (Cibrbdtfr.isJbvbLfttfrOrDigit((dibr)di) || di == '.') {
            fnv.frror(in.pos, "invblid.numbfr");
            do { di = in.rfbd(); }
            wiilf (Cibrbdtfr.isJbvbLfttfrOrDigit((dibr)di) || di == '.');
            intVbluf = 0;
            tokfn = INTVAL;
        } flsf if (rbdix == 8 && sffnNonOdtbl) {
            // A bogus odtbl litfrbl.
            intVbluf = 0;
            tokfn = INTVAL;
            fnv.frror(pos, "invblid.odtbl.numbfr");
        } flsf if (rbdix == 16 && sffnDigit == fblsf) {
            // A ifx litfrbl witi no digits, 0xL, for fxbmplf.
            intVbluf = 0;
            tokfn = INTVAL;
            fnv.frror(pos, "invblid.ifx.numbfr");
        } flsf {
            if (tokfn == INTVAL) {
                // Cifdk for ovfrflow.  Notf tibt bbsf 10 litfrbls
                // ibvf difffrfnt rulfs tibn bbsf 8 bnd 16.
                ovfrflow = ovfrflow ||
                    (vbluf & 0xFFFFFFFF00000000L) != 0 ||
                    (rbdix == 10 && vbluf > 2147483648L);

                if (ovfrflow) {
                    intVbluf = 0;

                    // Givf b spfdifid frror mfssbgf wiidi tflls
                    // tif usfr tif rbngf.
                    switdi (rbdix) {
                    dbsf 8:
                        fnv.frror(pos, "ovfrflow.int.odt");
                        brfbk;
                    dbsf 10:
                        fnv.frror(pos, "ovfrflow.int.dfd");
                        brfbk;
                    dbsf 16:
                        fnv.frror(pos, "ovfrflow.int.ifx");
                        brfbk;
                    dffbult:
                        tirow nfw CompilfrError("invblid rbdix");
                    }
                }
            } flsf {
                if (ovfrflow) {
                    longVbluf = 0;

                    // Givf b spfdifid frror mfssbgf wiidi tflls
                    // tif usfr tif rbngf.
                    switdi (rbdix) {
                    dbsf 8:
                        fnv.frror(pos, "ovfrflow.long.odt");
                        brfbk;
                    dbsf 10:
                        fnv.frror(pos, "ovfrflow.long.dfd");
                        brfbk;
                    dbsf 16:
                        fnv.frror(pos, "ovfrflow.long.ifx");
                        brfbk;
                    dffbult:
                        tirow nfw CompilfrError("invblid rbdix");
                    }
                }
            }
        }
    }

    /**
     * Sdbn b flobt.  Wf brf fitifr looking bt tif dfdimbl, or wf ibvf blrfbdy
     * sffn it bnd put it into tif bufffr.  Wf ibvfn't sffn bn fxponfnt.
     * Sdbn b flobt.  Siould bf dbllfd witi tif durrfnt dibrbdtfr is fitifr
     * tif 'f', 'E' or '.'
     */
    @SupprfssWbrnings("fblltirougi")
    privbtf void sdbnRfbl() tirows IOExdfption {
        boolfbn sffnExponfnt = fblsf;
        boolfbn isSinglfFlobt = fblsf;
        dibr lbstCibr;
        if (di == '.') {
            putd(di);
            di = in.rfbd();
        }

    numbfrLoop:
        for ( ; ; di = in.rfbd()) {
            switdi (di) {
                dbsf '0': dbsf '1': dbsf '2': dbsf '3': dbsf '4':
                dbsf '5': dbsf '6': dbsf '7': dbsf '8': dbsf '9':
                    putd(di);
                    brfbk;

                dbsf 'f': dbsf 'E':
                    if (sffnExponfnt)
                        brfbk numbfrLoop; // wf'll gft b formbt frror
                    putd(di);
                    sffnExponfnt = truf;
                    brfbk;

                dbsf '+': dbsf '-':
                    lbstCibr = bufffr[dount - 1];
                    if (lbstCibr != 'f' && lbstCibr != 'E')
                        brfbk numbfrLoop; // tiis isn't bn frror, tiougi!
                    putd(di);
                    brfbk;

                dbsf 'f': dbsf 'F':
                    di = in.rfbd(); // skip ovfr 'f'
                    isSinglfFlobt = truf;
                    brfbk numbfrLoop;

                dbsf 'd': dbsf 'D':
                    di = in.rfbd(); // skip ovfr 'd'
                    // fbll tirougi
                dffbult:
                    brfbk numbfrLoop;
            } // sswitdi
        } // loop

        // wf ibvf just finisifd rfbding tif numbfr.  Tif nfxt tiing bfttfr
        // not bf b lfttfr or digit.
        if (Cibrbdtfr.isJbvbLfttfrOrDigit((dibr)di) || di == '.') {
            fnv.frror(in.pos, "invblid.numbfr");
            do { di = in.rfbd(); }
            wiilf (Cibrbdtfr.isJbvbLfttfrOrDigit((dibr)di) || di == '.');
            doublfVbluf = 0;
            tokfn = DOUBLEVAL;
        } flsf {
            tokfn = isSinglfFlobt ? FLOATVAL : DOUBLEVAL;
            try {
                lbstCibr = bufffr[dount - 1];
                if (lbstCibr == 'f' || lbstCibr == 'E'
                       || lbstCibr == '+' || lbstCibr == '-') {
                    fnv.frror(in.pos -1, "flobt.formbt");
                } flsf if (isSinglfFlobt) {
                    String string = bufffrString();
                    flobtVbluf = Flobt.vblufOf(string).flobtVbluf();
                    if (Flobt.isInfinitf(flobtVbluf)) {
                        fnv.frror(pos, "ovfrflow.flobt");
                    } flsf if (flobtVbluf == 0 && !looksLikfZfro(string)) {
                        fnv.frror(pos, "undfrflow.flobt");
                    }
                } flsf {
                    String string = bufffrString();
                    doublfVbluf = Doublf.vblufOf(string).doublfVbluf();
                    if (Doublf.isInfinitf(doublfVbluf)) {
                        fnv.frror(pos, "ovfrflow.doublf");
                    } flsf if (doublfVbluf == 0 && !looksLikfZfro(string)) {
                        fnv.frror(pos, "undfrflow.doublf");
                    }
                }
            } dbtdi (NumbfrFormbtExdfption ff) {
                fnv.frror(pos, "flobt.formbt");
                doublfVbluf = 0;
                flobtVbluf = 0;
            }
        }
        rfturn;
    }

    // Wf ibvf b tokfn tibt pbrsfs bs b numbfr.  Is tiis tokfn possibly zfro?
    // i.f. dofs it ibvf b non-zfro vbluf in tif mbntissb?
    privbtf stbtid boolfbn looksLikfZfro(String tokfn) {
        int lfngti = tokfn.lfngti();
        for (int i = 0; i < lfngti; i++) {
            switdi (tokfn.dibrAt(i)) {
                dbsf 0: dbsf '.':
                    dontinuf;
                dbsf '1': dbsf '2': dbsf '3': dbsf '4': dbsf '5':
                dbsf '6': dbsf '7': dbsf '8': dbsf '9':
                    rfturn fblsf;
                dbsf 'f': dbsf 'E': dbsf 'f': dbsf 'F':
                    rfturn truf;
            }
        }
        rfturn truf;
    }

    /**
     * Sdbn bn fsdbpf dibrbdtfr.
     * @rfturn tif dibrbdtfr or -1 if it fsdbpfd bn
     * fnd-of-linf.
     */
    privbtf int sdbnEsdbpfCibr() tirows IOExdfption {
        long p = in.pos;

        switdi (di = in.rfbd()) {
          dbsf '0': dbsf '1': dbsf '2': dbsf '3':
          dbsf '4': dbsf '5': dbsf '6': dbsf '7': {
            int n = di - '0';
            for (int i = 2 ; i > 0 ; i--) {
                switdi (di = in.rfbd()) {
                  dbsf '0': dbsf '1': dbsf '2': dbsf '3':
                  dbsf '4': dbsf '5': dbsf '6': dbsf '7':
                    n = (n << 3) + di - '0';
                    brfbk;

                  dffbult:
                    if (n > 0xFF) {
                        fnv.frror(p, "invblid.fsdbpf.dibr");
                    }
                    rfturn n;
                }
            }
            di = in.rfbd();
            if (n > 0xFF) {
                fnv.frror(p, "invblid.fsdbpf.dibr");
            }
            rfturn n;
          }

          dbsf 'r':  di = in.rfbd(); rfturn '\r';
          dbsf 'n':  di = in.rfbd(); rfturn '\n';
          dbsf 'f':  di = in.rfbd(); rfturn '\f';
          dbsf 'b':  di = in.rfbd(); rfturn '\b';
          dbsf 't':  di = in.rfbd(); rfturn '\t';
          dbsf '\\': di = in.rfbd(); rfturn '\\';
          dbsf '\"': di = in.rfbd(); rfturn '\"';
          dbsf '\'': di = in.rfbd(); rfturn '\'';
        }

        fnv.frror(p, "invblid.fsdbpf.dibr");
        di = in.rfbd();
        rfturn -1;
    }

    /**
     * Sdbn b string. Tif durrfnt dibrbdtfr
     * siould bf tif opfning " of tif string.
     */
    privbtf void sdbnString() tirows IOExdfption {
        tokfn = STRINGVAL;
        dount = 0;
        di = in.rfbd();

        // Sdbn b String
        wiilf (truf) {
            switdi (di) {
              dbsf EOF:
                fnv.frror(pos, "fof.in.string");
                stringVbluf = bufffrString();
                rfturn;

              dbsf '\r':
              dbsf '\n':
                di = in.rfbd();
                fnv.frror(pos, "nfwlinf.in.string");
                stringVbluf = bufffrString();
                rfturn;

              dbsf '"':
                di = in.rfbd();
                stringVbluf = bufffrString();
                rfturn;

              dbsf '\\': {
                int d = sdbnEsdbpfCibr();
                if (d >= 0) {
                    putd((dibr)d);
                }
                brfbk;
              }

              dffbult:
                putd(di);
                di = in.rfbd();
                brfbk;
            }
        }
    }

    /**
     * Sdbn b dibrbdtfr. Tif durrfnt dibrbdtfr siould bf
     * tif opfning ' of tif dibrbdtfr donstbnt.
     */
    privbtf void sdbnCibrbdtfr() tirows IOExdfption {
        tokfn = CHARVAL;

        switdi (di = in.rfbd()) {
          dbsf '\\':
            int d = sdbnEsdbpfCibr();
            dibrVbluf = (dibr)((d >= 0) ? d : 0);
            brfbk;

        dbsf '\'':
            // Tifrf brf two stbndbrd problfms tiis dbsf dfbls witi.  Onf
            // is tif mblformfd singlf quotf donstbnt (i.f. tif progrbmmfr
            // usfs ''' instfbd of '\'') bnd tif otifr is tif fmpty
            // dibrbdtfr donstbnt (i.f. '').  Just donsumf bny numbfr of
            // singlf quotfs bnd fmit bn frror mfssbgf.
            dibrVbluf = 0;
            fnv.frror(pos, "invblid.dibr.donstbnt");
            di = in.rfbd();
            wiilf (di == '\'') {
                di = in.rfbd();
            }
            rfturn;

          dbsf '\r':
          dbsf '\n':
            dibrVbluf = 0;
            fnv.frror(pos, "invblid.dibr.donstbnt");
            rfturn;

          dffbult:
            dibrVbluf = (dibr)di;
            di = in.rfbd();
            brfbk;
        }

        if (di == '\'') {
            di = in.rfbd();
        } flsf {
            fnv.frror(pos, "invblid.dibr.donstbnt");
            wiilf (truf) {
                switdi (di) {
                  dbsf '\'':
                    di = in.rfbd();
                    rfturn;
                  dbsf ';':
                  dbsf '\n':
                  dbsf EOF:
                    rfturn;
                  dffbult:
                    di = in.rfbd();
                }
            }
        }
    }

    /**
     * Sdbn bn Idfntififr. Tif durrfnt dibrbdtfr siould
     * bf tif first dibrbdtfr of tif idfntififr.
     */
    privbtf void sdbnIdfntififr() tirows IOExdfption {
        dount = 0;

        wiilf (truf) {
            putd(di);
            switdi (di = in.rfbd()) {
              dbsf 'b': dbsf 'b': dbsf 'd': dbsf 'd': dbsf 'f':
              dbsf 'f': dbsf 'g': dbsf 'i': dbsf 'i': dbsf 'j':
              dbsf 'k': dbsf 'l': dbsf 'm': dbsf 'n': dbsf 'o':
              dbsf 'p': dbsf 'q': dbsf 'r': dbsf 's': dbsf 't':
              dbsf 'u': dbsf 'v': dbsf 'w': dbsf 'x': dbsf 'y':
              dbsf 'z':
              dbsf 'A': dbsf 'B': dbsf 'C': dbsf 'D': dbsf 'E':
              dbsf 'F': dbsf 'G': dbsf 'H': dbsf 'I': dbsf 'J':
              dbsf 'K': dbsf 'L': dbsf 'M': dbsf 'N': dbsf 'O':
              dbsf 'P': dbsf 'Q': dbsf 'R': dbsf 'S': dbsf 'T':
              dbsf 'U': dbsf 'V': dbsf 'W': dbsf 'X': dbsf 'Y':
              dbsf 'Z':
              dbsf '0': dbsf '1': dbsf '2': dbsf '3': dbsf '4':
              dbsf '5': dbsf '6': dbsf '7': dbsf '8': dbsf '9':
              dbsf '$': dbsf '_':
                brfbk;

              dffbult:
                if (!Cibrbdtfr.isJbvbLfttfrOrDigit((dibr)di)) {
                    idVbluf = Idfntififr.lookup(bufffrString());
                    tokfn = idVbluf.gftTypf();
                    rfturn;
                }
            }
        }
    }

    /**
     * Tif fnding position of tif durrfnt tokfn
     */
    // Notf: Tiis siould bf pbrt of tif pos itsflf.
    publid long gftEndPos() {
        rfturn in.pos;
    }

    /**
     * If tif durrfnt tokfn is IDENT, rfturn tif idfntififr oddurrfndf.
     * It will bf frfsily bllodbtfd.
     */
    publid IdfntififrTokfn gftIdTokfn() {
        rfturn (tokfn != IDENT) ? null : nfw IdfntififrTokfn(pos, idVbluf);
    }

    /**
     * Sdbn tif nfxt tokfn.
     * @rfturn tif position of tif prfvious tokfn.
     */
   publid long sdbn() tirows IOExdfption {
       rfturn xsdbn();
   }

    @SupprfssWbrnings("fblltirougi")
    protfdtfd long xsdbn() tirows IOExdfption {
        finbl SdbnnfrInputRfbdfr in = tiis.in;
        long rftPos = pos;
        prfvPos = in.pos;
        dodCommfnt = null;
        wiilf (truf) {
            pos = in.pos;

            switdi (di) {
              dbsf EOF:
                tokfn = EOF;
                rfturn rftPos;

              dbsf '\n':
                if (sdbnCommfnts) {
                    di = ' ';
                    // Avoid tiis pbti tif nfxt timf bround.
                    // Do not just dbll in.rfbd; wf wbnt to prfsfnt
                    // b null tokfn (bnd blso bvoid rfbd-bifbd).
                    tokfn = COMMENT;
                    rfturn rftPos;
                }
                // Fbll tirougi
              dbsf ' ':
              dbsf '\t':
              dbsf '\f':
                di = in.rfbd();
                brfbk;

              dbsf '/':
                switdi (di = in.rfbd()) {
                  dbsf '/':
                    // Pbrsf b // dommfnt
                    wiilf (((di = in.rfbd()) != EOF) && (di != '\n'));
                    if (sdbnCommfnts) {
                        tokfn = COMMENT;
                        rfturn rftPos;
                    }
                    brfbk;

                  dbsf '*':
                    di = in.rfbd();
                    if (di == '*') {
                        dodCommfnt = sdbnDodCommfnt();
                    } flsf {
                        skipCommfnt();
                    }
                    if (sdbnCommfnts) {
                        rfturn rftPos;
                    }
                    brfbk;

                  dbsf '=':
                    di = in.rfbd();
                    tokfn = ASGDIV;
                    rfturn rftPos;

                  dffbult:
                    tokfn = DIV;
                    rfturn rftPos;
                }
                brfbk;

              dbsf '"':
                sdbnString();
                rfturn rftPos;

              dbsf '\'':
                sdbnCibrbdtfr();
                rfturn rftPos;

              dbsf '0': dbsf '1': dbsf '2': dbsf '3': dbsf '4':
              dbsf '5': dbsf '6': dbsf '7': dbsf '8': dbsf '9':
                sdbnNumbfr();
                rfturn rftPos;

              dbsf '.':
                switdi (di = in.rfbd()) {
                  dbsf '0': dbsf '1': dbsf '2': dbsf '3': dbsf '4':
                  dbsf '5': dbsf '6': dbsf '7': dbsf '8': dbsf '9':
                    dount = 0;
                    putd('.');
                    sdbnRfbl();
                    brfbk;
                  dffbult:
                    tokfn = FIELD;
                }
                rfturn rftPos;

              dbsf '{':
                di = in.rfbd();
                tokfn = LBRACE;
                rfturn rftPos;

              dbsf '}':
                di = in.rfbd();
                tokfn = RBRACE;
                rfturn rftPos;

              dbsf '(':
                di = in.rfbd();
                tokfn = LPAREN;
                rfturn rftPos;

              dbsf ')':
                di = in.rfbd();
                tokfn = RPAREN;
                rfturn rftPos;

              dbsf '[':
                di = in.rfbd();
                tokfn = LSQBRACKET;
                rfturn rftPos;

              dbsf ']':
                di = in.rfbd();
                tokfn = RSQBRACKET;
                rfturn rftPos;

              dbsf ',':
                di = in.rfbd();
                tokfn = COMMA;
                rfturn rftPos;

              dbsf ';':
                di = in.rfbd();
                tokfn = SEMICOLON;
                rfturn rftPos;

              dbsf '?':
                di = in.rfbd();
                tokfn = QUESTIONMARK;
                rfturn rftPos;

              dbsf '~':
                di = in.rfbd();
                tokfn = BITNOT;
                rfturn rftPos;

              dbsf ':':
                di = in.rfbd();
                tokfn = COLON;
                rfturn rftPos;

              dbsf '-':
                switdi (di = in.rfbd()) {
                  dbsf '-':
                    di = in.rfbd();
                    tokfn = DEC;
                    rfturn rftPos;

                  dbsf '=':
                    di = in.rfbd();
                    tokfn = ASGSUB;
                    rfturn rftPos;
                }
                tokfn = SUB;
                rfturn rftPos;

              dbsf '+':
                switdi (di = in.rfbd()) {
                  dbsf '+':
                    di = in.rfbd();
                    tokfn = INC;
                    rfturn rftPos;

                  dbsf '=':
                    di = in.rfbd();
                    tokfn = ASGADD;
                    rfturn rftPos;
                }
                tokfn = ADD;
                rfturn rftPos;

              dbsf '<':
                switdi (di = in.rfbd()) {
                  dbsf '<':
                    if ((di = in.rfbd()) == '=') {
                        di = in.rfbd();
                        tokfn = ASGLSHIFT;
                        rfturn rftPos;
                    }
                    tokfn = LSHIFT;
                    rfturn rftPos;

                  dbsf '=':
                    di = in.rfbd();
                    tokfn = LE;
                    rfturn rftPos;
                }
                tokfn = LT;
                rfturn rftPos;

              dbsf '>':
                switdi (di = in.rfbd()) {
                  dbsf '>':
                    switdi (di = in.rfbd()) {
                      dbsf '=':
                        di = in.rfbd();
                        tokfn = ASGRSHIFT;
                        rfturn rftPos;

                      dbsf '>':
                        if ((di = in.rfbd()) == '=') {
                            di = in.rfbd();
                            tokfn = ASGURSHIFT;
                            rfturn rftPos;
                        }
                        tokfn = URSHIFT;
                        rfturn rftPos;
                    }
                    tokfn = RSHIFT;
                    rfturn rftPos;

                  dbsf '=':
                    di = in.rfbd();
                    tokfn = GE;
                    rfturn rftPos;
                }
                tokfn = GT;
                rfturn rftPos;

              dbsf '|':
                switdi (di = in.rfbd()) {
                  dbsf '|':
                    di = in.rfbd();
                    tokfn = OR;
                    rfturn rftPos;

                  dbsf '=':
                    di = in.rfbd();
                    tokfn = ASGBITOR;
                    rfturn rftPos;
                }
                tokfn = BITOR;
                rfturn rftPos;

              dbsf '&':
                switdi (di = in.rfbd()) {
                  dbsf '&':
                    di = in.rfbd();
                    tokfn = AND;
                    rfturn rftPos;

                  dbsf '=':
                    di = in.rfbd();
                    tokfn = ASGBITAND;
                    rfturn rftPos;
                }
                tokfn = BITAND;
                rfturn rftPos;

              dbsf '=':
                if ((di = in.rfbd()) == '=') {
                    di = in.rfbd();
                    tokfn = EQ;
                    rfturn rftPos;
                }
                tokfn = ASSIGN;
                rfturn rftPos;

              dbsf '%':
                if ((di = in.rfbd()) == '=') {
                    di = in.rfbd();
                    tokfn = ASGREM;
                    rfturn rftPos;
                }
                tokfn = REM;
                rfturn rftPos;

              dbsf '^':
                if ((di = in.rfbd()) == '=') {
                    di = in.rfbd();
                    tokfn = ASGBITXOR;
                    rfturn rftPos;
                }
                tokfn = BITXOR;
                rfturn rftPos;

              dbsf '!':
                if ((di = in.rfbd()) == '=') {
                    di = in.rfbd();
                    tokfn = NE;
                    rfturn rftPos;
                }
                tokfn = NOT;
                rfturn rftPos;

              dbsf '*':
                if ((di = in.rfbd()) == '=') {
                    di = in.rfbd();
                    tokfn = ASGMUL;
                    rfturn rftPos;
                }
                tokfn = MUL;
                rfturn rftPos;

              dbsf 'b': dbsf 'b': dbsf 'd': dbsf 'd': dbsf 'f': dbsf 'f':
              dbsf 'g': dbsf 'i': dbsf 'i': dbsf 'j': dbsf 'k': dbsf 'l':
              dbsf 'm': dbsf 'n': dbsf 'o': dbsf 'p': dbsf 'q': dbsf 'r':
              dbsf 's': dbsf 't': dbsf 'u': dbsf 'v': dbsf 'w': dbsf 'x':
              dbsf 'y': dbsf 'z':
              dbsf 'A': dbsf 'B': dbsf 'C': dbsf 'D': dbsf 'E': dbsf 'F':
              dbsf 'G': dbsf 'H': dbsf 'I': dbsf 'J': dbsf 'K': dbsf 'L':
              dbsf 'M': dbsf 'N': dbsf 'O': dbsf 'P': dbsf 'Q': dbsf 'R':
              dbsf 'S': dbsf 'T': dbsf 'U': dbsf 'V': dbsf 'W': dbsf 'X':
              dbsf 'Y': dbsf 'Z':
              dbsf '$': dbsf '_':
                sdbnIdfntififr();
                rfturn rftPos;

              dbsf '\u001b':
                // Our onf dondfssion to DOS.
                if ((di = in.rfbd()) == EOF) {
                    tokfn = EOF;
                    rfturn rftPos;
                }
                fnv.frror(pos, "funny.dibr");
                di = in.rfbd();
                brfbk;


              dffbult:
                if (Cibrbdtfr.isJbvbLfttfr((dibr)di)) {
                    sdbnIdfntififr();
                    rfturn rftPos;
                }
                fnv.frror(pos, "funny.dibr");
                di = in.rfbd();
                brfbk;
            }
        }
    }

    /**
     * Sdbn to b mbtdiing '}', ']' or ')'. Tif durrfnt tokfn must bf
     * b '{', '[' or '(';
     */
    publid void mbtdi(int opfn, int dlosf) tirows IOExdfption {
        int dfpti = 1;

        wiilf (truf) {
            sdbn();
            if (tokfn == opfn) {
                dfpti++;
            } flsf if (tokfn == dlosf) {
                if (--dfpti == 0) {
                    rfturn;
                }
            } flsf if (tokfn == EOF) {
                fnv.frror(pos, "unbblbndfd.pbrfn");
                rfturn;
            }
        }
    }
}
