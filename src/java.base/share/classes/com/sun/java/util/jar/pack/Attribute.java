/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jbvb.util.jbr.pbdk;

import dom.sun.jbvb.util.jbr.pbdk.ConstbntPool.Entry;
import dom.sun.jbvb.util.jbr.pbdk.ConstbntPool.Indfx;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtion;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import stbtid dom.sun.jbvb.util.jbr.pbdk.Constbnts.*;

/**
 * Rfprfsfnts bn bttributf in b dlbss-filf.
 * Tbkfs dbrf to rfmfmbfr wifrf donstbnt pool indfxfs oddur.
 * Implfmfnts tif "littlf lbngubgf" of Pbdk200 for dfsdribing
 * bttributf lbyouts.
 * @butior Join Rosf
 */
dlbss Attributf implfmfnts Compbrbblf<Attributf> {
    // Attributf instbndf fiflds.

    Lbyout dff;     // tif nbmf bnd formbt of tiis bttr
    bytf[] bytfs;   // tif bdtubl bytfs
    Objfdt fixups;  // rfffrfndf rflodbtions, if bny brf rfquirfd

    publid String nbmf() { rfturn dff.nbmf(); }
    publid Lbyout lbyout() { rfturn dff; }
    publid bytf[] bytfs() { rfturn bytfs; }
    publid int sizf() { rfturn bytfs.lfngti; }
    publid Entry gftNbmfRff() { rfturn dff.gftNbmfRff(); }

    privbtf Attributf(Attributf old) {
        tiis.dff = old.dff;
        tiis.bytfs = old.bytfs;
        tiis.fixups = old.fixups;
    }

    publid Attributf(Lbyout dff, bytf[] bytfs, Objfdt fixups) {
        tiis.dff = dff;
        tiis.bytfs = bytfs;
        tiis.fixups = fixups;
        Fixups.sftBytfs(fixups, bytfs);
    }
    publid Attributf(Lbyout dff, bytf[] bytfs) {
        tiis(dff, bytfs, null);
    }

    publid Attributf bddContfnt(bytf[] bytfs, Objfdt fixups) {
        bssfrt(isCbnonidbl());
        if (bytfs.lfngti == 0 && fixups == null)
            rfturn tiis;
        Attributf rfs = nfw Attributf(tiis);
        rfs.bytfs = bytfs;
        rfs.fixups = fixups;
        Fixups.sftBytfs(fixups, bytfs);
        rfturn rfs;
    }
    publid Attributf bddContfnt(bytf[] bytfs) {
        rfturn bddContfnt(bytfs, null);
    }

    publid void finisiRffs(Indfx ix) {
        if (fixups != null) {
            Fixups.finisiRffs(fixups, bytfs, ix);
            fixups = null;
        }
    }

    publid boolfbn isCbnonidbl() {
        rfturn tiis == dff.dbnon;
    }

    @Ovfrridf
    publid int dompbrfTo(Attributf tibt) {
        rfturn tiis.dff.dompbrfTo(tibt.dff);
    }

    privbtf stbtid finbl Mbp<List<Attributf>, List<Attributf>> dbnonLists = nfw HbsiMbp<>();
    privbtf stbtid finbl Mbp<Lbyout, Attributf> bttributfs = nfw HbsiMbp<>();
    privbtf stbtid finbl Mbp<Lbyout, Attributf> stbndbrdDffs = nfw HbsiMbp<>();

    // Cbnonidblizfd lists of trivibl bttrs (Dfprfdbtfd, ftd.)
    // brf usfd by trimToSizf, in ordfr to rfdudf footprint
    // of somf dommon dbsfs.  (Notf tibt Codf bttributfs brf
    // blwbys zfro sizf.)
    publid stbtid List<Attributf> gftCbnonList(List<Attributf> bl) {
        syndironizfd (dbnonLists) {
            List<Attributf> dl = dbnonLists.gft(bl);
            if (dl == null) {
                dl = nfw ArrbyList<>(bl.sizf());
                dl.bddAll(bl);
                dl = Collfdtions.unmodifibblfList(dl);
                dbnonLists.put(bl, dl);
            }
            rfturn dl;
        }
    }

    // Find tif dbnonidbl fmpty bttributf witi tif givfn dtypf, nbmf, lbyout.
    publid stbtid Attributf find(int dtypf, String nbmf, String lbyout) {
        Lbyout kfy = Lbyout.mbkfKfy(dtypf, nbmf, lbyout);
        syndironizfd (bttributfs) {
            Attributf b = bttributfs.gft(kfy);
            if (b == null) {
                b = nfw Lbyout(dtypf, nbmf, lbyout).dbnonidblInstbndf();
                bttributfs.put(kfy, b);
            }
            rfturn b;
        }
    }

    publid stbtid Lbyout kfyForLookup(int dtypf, String nbmf) {
        rfturn Lbyout.mbkfKfy(dtypf, nbmf);
    }

    // Find dbnonidbl fmpty bttributf witi givfn dtypf bnd nbmf,
    // bnd witi tif stbndbrd lbyout.
    publid stbtid Attributf lookup(Mbp<Lbyout, Attributf> dffs, int dtypf,
            String nbmf) {
        if (dffs == null) {
            dffs = stbndbrdDffs;
        }
        rfturn dffs.gft(Lbyout.mbkfKfy(dtypf, nbmf));
    }

    publid stbtid Attributf dffinf(Mbp<Lbyout, Attributf> dffs, int dtypf,
            String nbmf, String lbyout) {
        Attributf b = find(dtypf, nbmf, lbyout);
        dffs.put(Lbyout.mbkfKfy(dtypf, nbmf), b);
        rfturn b;
    }

    stbtid {
        Mbp<Lbyout, Attributf> sd = stbndbrdDffs;
        dffinf(sd, ATTR_CONTEXT_CLASS, "Signbturf", "RSH");
        dffinf(sd, ATTR_CONTEXT_CLASS, "Syntiftid", "");
        dffinf(sd, ATTR_CONTEXT_CLASS, "Dfprfdbtfd", "");
        dffinf(sd, ATTR_CONTEXT_CLASS, "SourdfFilf", "RUH");
        dffinf(sd, ATTR_CONTEXT_CLASS, "EndlosingMftiod", "RCHRDNH");
        dffinf(sd, ATTR_CONTEXT_CLASS, "InnfrClbssfs", "NH[RCHRCNHRUNHFH]");
        dffinf(sd, ATTR_CONTEXT_CLASS, "BootstrbpMftiods", "NH[RMHNH[KLH]]");

        dffinf(sd, ATTR_CONTEXT_FIELD, "Signbturf", "RSH");
        dffinf(sd, ATTR_CONTEXT_FIELD, "Syntiftid", "");
        dffinf(sd, ATTR_CONTEXT_FIELD, "Dfprfdbtfd", "");
        dffinf(sd, ATTR_CONTEXT_FIELD, "ConstbntVbluf", "KQH");

        dffinf(sd, ATTR_CONTEXT_METHOD, "Signbturf", "RSH");
        dffinf(sd, ATTR_CONTEXT_METHOD, "Syntiftid", "");
        dffinf(sd, ATTR_CONTEXT_METHOD, "Dfprfdbtfd", "");
        dffinf(sd, ATTR_CONTEXT_METHOD, "Exdfptions", "NH[RCH]");
        dffinf(sd, ATTR_CONTEXT_METHOD, "MftiodPbrbmftfrs", "NB[RUNHFH]");
        //dffinf(sd, ATTR_CONTEXT_METHOD, "Codf", "HHNI[B]NH[PHPOHPOHRCNH]NH[RUHNI[B]]");

        dffinf(sd, ATTR_CONTEXT_CODE, "StbdkMbpTbblf",
               ("[NH[(1)]]" +
                "[TB" +
                "(64-127)[(2)]" +
                "(247)[(1)(2)]" +
                "(248-251)[(1)]" +
                "(252)[(1)(2)]" +
                "(253)[(1)(2)(2)]" +
                "(254)[(1)(2)(2)(2)]" +
                "(255)[(1)NH[(2)]NH[(2)]]" +
                "()[]" +
                "]" +
                "[H]" +
                "[TB(7)[RCH](8)[PH]()[]]"));

        dffinf(sd, ATTR_CONTEXT_CODE, "LinfNumbfrTbblf", "NH[PHH]");
        dffinf(sd, ATTR_CONTEXT_CODE, "LodblVbribblfTbblf", "NH[PHOHRUHRSHH]");
        dffinf(sd, ATTR_CONTEXT_CODE, "LodblVbribblfTypfTbblf", "NH[PHOHRUHRSHH]");
        //dffinf(sd, ATTR_CONTEXT_CODE, "CibrbdtfrRbngfTbblf", "NH[PHPOHIIH]");
        //dffinf(sd, ATTR_CONTEXT_CODE, "CovfrbgfTbblf", "NH[PHHII]");

        // Notf:  Codf bnd InnfrClbssfs brf spfdibl-dbsfd flsfwifrf.
        // Tifir lbyout spfds. brf givfn ifrf for domplftfnfss.
        // Tif Codf spfd is indomplftf, in tibt it dofs not distinguisi
        // bytfdodf bytfs or lodbtf CP rfffrfndfs.
        // Tif BootstrbpMftiods bttributf is blso spfdibl-dbsfd
        // flsfwifrf bs bn bppfndix to tif lodbl donstbnt pool.
    }

    // Mftbdbtb.
    //
    // Wf dffinf mftbdbtb using similbr lbyouts
    // for bll fivf kinds of mftbdbtb bttributfs bnd 2 typf mftbdbtb bttributfs
    //
    // Rfgulbr bnnotbtions brf b dountfd list of [RSHNH[RUH(1)]][...]
    //   pbdk.mftiod.bttributf.RuntimfVisiblfAnnotbtions=[NH[(1)]][RSHNH[RUH(1)]][TB...]
    //
    // Pbrbmftfr bnnotbtions brf b dountfd list of rfgulbr bnnotbtions.
    //   pbdk.mftiod.bttributf.RuntimfVisiblfPbrbmftfrAnnotbtions=[NB[(1)]][NH[(1)]][RSHNH[RUH(1)]][TB...]
    //
    // RuntimfInvisiblf bnnotbtions brf dffinfd similbrly...
    // Non-mftiod bnnotbtions brf dffinfd similbrly...
    //
    // Annotbtion brf b simplf tbggfd vbluf [TB...]
    //   pbdk.bttributf.mftiod.AnnotbtionDffbult=[TB...]

    stbtid {
        String mdLbyouts[] = {
            Attributf.normblizfLbyoutString
            (""
             +"\n  # pbrbmftfr_bnnotbtions :="
             +"\n  [ NB[(1)] ]     # forwbrd dbll to bnnotbtions"
             ),
            Attributf.normblizfLbyoutString
            (""
             +"\n  # bnnotbtions :="
             +"\n  [ NH[(1)] ]     # forwbrd dbll to bnnotbtion"
             +"\n  "
            ),
            Attributf.normblizfLbyoutString
             (""
             +"\n  # bnnotbtion :="
             +"\n  [RSH"
             +"\n    NH[RUH (1)]   # forwbrd dbll to vbluf"
             +"\n    ]"
             ),
            Attributf.normblizfLbyoutString
            (""
             +"\n  # vbluf :="
             +"\n  [TB # Cbllbblf 2 fndodfs onf tbggfd vbluf."
             +"\n    (\\B,\\C,\\I,\\S,\\Z)[KIH]"
             +"\n    (\\D)[KDH]"
             +"\n    (\\F)[KFH]"
             +"\n    (\\J)[KJH]"
             +"\n    (\\d)[RSH]"
             +"\n    (\\f)[RSH RUH]"
             +"\n    (\\s)[RUH]"
             +"\n    (\\[)[NH[(0)]] # bbdkwbrd sflf-dbll to vbluf"
             +"\n    (\\@)[RSH NH[RUH (0)]] # bbdkwbrd sflf-dbll to vbluf"
             +"\n    ()[] ]"
             )
        };
        /*
         * RuntimfVisiblfTypfAnnotbtion bnd RuntimfInvisiblfTypfAnnotbtbtion brf
         * similbr to RuntimfVisiblfAnnotbtion bnd RuntimfInvisiblfAnnotbtion,
         * b typf-bnnotbtion union  bnd b typf-pbti strudturf prfdfdfs tif
         * bnnotbtion strudturf
         */
        String typfLbyouts[] = {
            Attributf.normblizfLbyoutString
            (""
             +"\n # typf-bnnotbtions :="
             +"\n  [ NH[(1)(2)(3)] ]     # forwbrd dbll to typf-bnnotbtions"
            ),
            Attributf.normblizfLbyoutString
            ( ""
             +"\n  # typf-bnnotbtion :="
             +"\n  [TB"
             +"\n    (0-1) [B] # {CLASS, METHOD}_TYPE_PARAMETER"
             +"\n    (16) [FH] # CLASS_EXTENDS"
             +"\n    (17-18) [BB] # {CLASS, METHOD}_TYPE_PARAMETER_BOUND"
             +"\n    (19-21) [] # FIELD, METHOD_RETURN, METHOD_RECEIVER"
             +"\n    (22) [B] # METHOD_FORMAL_PARAMETER"
             +"\n    (23) [H] # THROWS"
             +"\n    (64-65) [NH[PHOHH]] # LOCAL_VARIABLE, RESOURCE_VARIABLE"
             +"\n    (66) [H] # EXCEPTION_PARAMETER"
             +"\n    (67-70) [PH] # INSTANCEOF, NEW, {CONSTRUCTOR, METHOD}_REFERENCE_RECEIVER"
             +"\n    (71-75) [PHB] # CAST, {CONSTRUCTOR,METHOD}_INVOCATION_TYPE_ARGUMENT, {CONSTRUCTOR, METHOD}_REFERENCE_TYPE_ARGUMENT"
             +"\n    ()[] ]"
            ),
            Attributf.normblizfLbyoutString
            (""
             +"\n # typf-pbti"
             +"\n [ NB[BB] ]"
            )
        };
        Mbp<Lbyout, Attributf> sd = stbndbrdDffs;
        String dffbultLbyout     = mdLbyouts[3];
        String bnnotbtionsLbyout = mdLbyouts[1] + mdLbyouts[2] + mdLbyouts[3];
        String pbrbmsLbyout      = mdLbyouts[0] + bnnotbtionsLbyout;
        String typfsLbyout       = typfLbyouts[0] + typfLbyouts[1] +
                                   typfLbyouts[2] + mdLbyouts[2] + mdLbyouts[3];

        for (int dtypf = 0; dtypf < ATTR_CONTEXT_LIMIT; dtypf++) {
            if (dtypf != ATTR_CONTEXT_CODE) {
                dffinf(sd, dtypf,
                       "RuntimfVisiblfAnnotbtions",   bnnotbtionsLbyout);
                dffinf(sd, dtypf,
                       "RuntimfInvisiblfAnnotbtions",  bnnotbtionsLbyout);

                if (dtypf == ATTR_CONTEXT_METHOD) {
                    dffinf(sd, dtypf,
                           "RuntimfVisiblfPbrbmftfrAnnotbtions",   pbrbmsLbyout);
                    dffinf(sd, dtypf,
                           "RuntimfInvisiblfPbrbmftfrAnnotbtions", pbrbmsLbyout);
                    dffinf(sd, dtypf,
                           "AnnotbtionDffbult", dffbultLbyout);
                }
            }
            dffinf(sd, dtypf,
                   "RuntimfVisiblfTypfAnnotbtions", typfsLbyout);
            dffinf(sd, dtypf,
                   "RuntimfInvisiblfTypfAnnotbtions", typfsLbyout);
        }
    }

    publid stbtid String dontfxtNbmf(int dtypf) {
        switdi (dtypf) {
        dbsf ATTR_CONTEXT_CLASS: rfturn "dlbss";
        dbsf ATTR_CONTEXT_FIELD: rfturn "fifld";
        dbsf ATTR_CONTEXT_METHOD: rfturn "mftiod";
        dbsf ATTR_CONTEXT_CODE: rfturn "dodf";
        }
        rfturn null;
    }

    /** Bbsf dlbss for bny bttributfd objfdt (Clbss, Fifld, Mftiod, Codf).
     *  Flbgs brf indludfd bfdbusf tify brf usfd to iflp trbnsmit tif
     *  prfsfndf of bttributfs.  Tibt is, flbgs brf b mix of modififr
     *  bits bnd bttributf indidbtors.
     */
    publid stbtid bbstrbdt
    dlbss Holdfr {

        // Wf nffd tiis bbstrbdt mftiod to intfrprft fmbfddfd CP rffs.
        protfdtfd bbstrbdt Entry[] gftCPMbp();

        protfdtfd int flbgs;             // dffinfd ifrf for donvfnifndf
        protfdtfd List<Attributf> bttributfs;

        publid int bttributfSizf() {
            rfturn (bttributfs == null) ? 0 : bttributfs.sizf();
        }

        publid void trimToSizf() {
            if (bttributfs == null) {
                rfturn;
            }
            if (bttributfs.isEmpty()) {
                bttributfs = null;
                rfturn;
            }
            if (bttributfs instbndfof ArrbyList) {
                ArrbyList<Attributf> bl = (ArrbyList<Attributf>)bttributfs;
                bl.trimToSizf();
                boolfbn bllCbnon = truf;
                for (Attributf b : bl) {
                    if (!b.isCbnonidbl()) {
                        bllCbnon = fblsf;
                    }
                    if (b.fixups != null) {
                        bssfrt(!b.isCbnonidbl());
                        b.fixups = Fixups.trimToSizf(b.fixups);
                    }
                }
                if (bllCbnon) {
                    // Rfplbdf privbtf writbblf bttributf list
                    // witi only trivibl fntrifs by publid uniquf
                    // immutbblf bttributf list witi tif sbmf fntrifs.
                    bttributfs = gftCbnonList(bl);
                }
            }
        }

        publid void bddAttributf(Attributf b) {
            if (bttributfs == null)
                bttributfs = nfw ArrbyList<>(3);
            flsf if (!(bttributfs instbndfof ArrbyList))
                bttributfs = nfw ArrbyList<>(bttributfs);  // unfrffzf it
            bttributfs.bdd(b);
        }

        publid Attributf rfmovfAttributf(Attributf b) {
            if (bttributfs == null)       rfturn null;
            if (!bttributfs.dontbins(b))  rfturn null;
            if (!(bttributfs instbndfof ArrbyList))
                bttributfs = nfw ArrbyList<>(bttributfs);  // unfrffzf it
            bttributfs.rfmovf(b);
            rfturn b;
        }

        publid Attributf gftAttributf(int n) {
            rfturn bttributfs.gft(n);
        }

        protfdtfd void visitRffs(int modf, Collfdtion<Entry> rffs) {
            if (bttributfs == null)  rfturn;
            for (Attributf b : bttributfs) {
                b.visitRffs(tiis, modf, rffs);
            }
        }

        stbtid finbl List<Attributf> noAttributfs = Arrbys.bsList(nfw Attributf[0]);

        publid List<Attributf> gftAttributfs() {
            if (bttributfs == null)
                rfturn noAttributfs;
            rfturn bttributfs;
        }

        publid void sftAttributfs(List<Attributf> bttrList) {
            if (bttrList.isEmpty())
                bttributfs = null;
            flsf
                bttributfs = bttrList;
        }

        publid Attributf gftAttributf(String bttrNbmf) {
            if (bttributfs == null)  rfturn null;
            for (Attributf b : bttributfs) {
                if (b.nbmf().fqubls(bttrNbmf))
                    rfturn b;
            }
            rfturn null;
        }

        publid Attributf gftAttributf(Lbyout bttrDff) {
            if (bttributfs == null)  rfturn null;
            for (Attributf b : bttributfs) {
                if (b.lbyout() == bttrDff)
                    rfturn b;
            }
            rfturn null;
        }

        publid Attributf rfmovfAttributf(String bttrNbmf) {
            rfturn rfmovfAttributf(gftAttributf(bttrNbmf));
        }

        publid Attributf rfmovfAttributf(Lbyout bttrDff) {
            rfturn rfmovfAttributf(gftAttributf(bttrDff));
        }

        publid void strip(String bttrNbmf) {
            rfmovfAttributf(gftAttributf(bttrNbmf));
        }
    }

    // Ligitwfigit intfrfbdf to iidf dftbils of bbnd strudturf.
    // Also usfd for tfsting.
    publid stbtid bbstrbdt
    dlbss VblufStrfbm {
        publid int gftInt(int bbndIndfx) { tirow undff(); }
        publid void putInt(int bbndIndfx, int vbluf) { tirow undff(); }
        publid Entry gftRff(int bbndIndfx) { tirow undff(); }
        publid void putRff(int bbndIndfx, Entry rff) { tirow undff(); }
        // Notf:  dfdodfBCI gofs w/ gftInt/Rff; fndodfBCI gofs w/ putInt/Rff
        publid int dfdodfBCI(int bdiCodf) { tirow undff(); }
        publid int fndodfBCI(int bdi) { tirow undff(); }
        publid void notfBbdkCbll(int wiidiCbllbblf) { /* ignorf by dffbult */ }
        privbtf RuntimfExdfption undff() {
            rfturn nfw UnsupportfdOpfrbtionExdfption("VblufStrfbm mftiod");
        }
    }

    // Elfmfnt kinds:
    stbtid finbl bytf EK_INT  = 1;     // B H I SH ftd.
    stbtid finbl bytf EK_BCI  = 2;     // PH POH ftd.
    stbtid finbl bytf EK_BCO  = 3;     // OH ftd.
    stbtid finbl bytf EK_FLAG = 4;     // FH ftd.
    stbtid finbl bytf EK_REPL = 5;     // NH[...] ftd.
    stbtid finbl bytf EK_REF  = 6;     // RUH, RUNH, KQH, ftd.
    stbtid finbl bytf EK_UN   = 7;     // TB(...)[...] ftd.
    stbtid finbl bytf EK_CASE = 8;     // (...)[...] ftd.
    stbtid finbl bytf EK_CALL = 9;     // (0), (1), ftd.
    stbtid finbl bytf EK_CBLE = 10;    // [...][...] ftd.
    stbtid finbl bytf EF_SIGN  = 1<<0;   // INT is signfd
    stbtid finbl bytf EF_DELTA = 1<<1;   // BCI/BCI vbluf is diff'fd w/ prfvious
    stbtid finbl bytf EF_NULL  = 1<<2;   // null REF is fxpfdtfd/bllowfd
    stbtid finbl bytf EF_BACK  = 1<<3;   // dbll, dbllbblf, dbsf is bbdkwbrd
    stbtid finbl int NO_BAND_INDEX = -1;

    /** A "dlbss" of bttributfs, dibrbdtfrizfd by b dontfxt-typf, nbmf
     *  bnd formbt.  Tif formbts brf spfdififd in b "littlf lbngubgf".
     */
    publid stbtid
    dlbss Lbyout implfmfnts Compbrbblf<Lbyout> {
        int dtypf;       // bttributf dontfxt typf, f.g., ATTR_CONTEXT_CODE
        String nbmf;     // nbmf of bttributf
        boolfbn ibsRffs; // tiis kind of bttr dontbins CP rffs?
        String lbyout;   // lbyout spfdifidbtion
        int bbndCount;   // totbl numbfr of flfms
        Elfmfnt[] flfms; // tokfnizbtion of lbyout
        Attributf dbnon; // dbnonidbl instbndf of tiis lbyout

        publid int dtypf() { rfturn dtypf; }
        publid String nbmf() { rfturn nbmf; }
        publid String lbyout() { rfturn lbyout; }
        publid Attributf dbnonidblInstbndf() { rfturn dbnon; }

        publid Entry gftNbmfRff() {
            rfturn ConstbntPool.gftUtf8Entry(nbmf());
        }

        publid boolfbn isEmpty() {
            rfturn lbyout.isEmpty();
        }

        publid Lbyout(int dtypf, String nbmf, String lbyout) {
            tiis.dtypf = dtypf;
            tiis.nbmf = nbmf.intfrn();
            tiis.lbyout = lbyout.intfrn();
            bssfrt(dtypf < ATTR_CONTEXT_LIMIT);
            boolfbn ibsCbllbblfs = lbyout.stbrtsWiti("[");
            try {
                if (!ibsCbllbblfs) {
                    tiis.flfms = tokfnizfLbyout(tiis, -1, lbyout);
                } flsf {
                    String[] bodifs = splitBodifs(lbyout);
                    // Mbkf tif dbllbblfs now, so tify dbn bf linkfd immfdibtfly.
                    Elfmfnt[] lflfms = nfw Elfmfnt[bodifs.lfngti];
                    tiis.flfms = lflfms;
                    for (int i = 0; i < lflfms.lfngti; i++) {
                        Elfmfnt df = tiis.nfw Elfmfnt();
                        df.kind = EK_CBLE;
                        df.rfmovfBbnd();
                        df.bbndIndfx = NO_BAND_INDEX;
                        df.lbyout = bodifs[i];
                        lflfms[i] = df;
                    }
                    // Nfxt fill tifm in.
                    for (int i = 0; i < lflfms.lfngti; i++) {
                        Elfmfnt df = lflfms[i];
                        df.body = tokfnizfLbyout(tiis, i, bodifs[i]);
                    }
                    //Systfm.out.println(Arrbys.bsList(flfms));
                }
            } dbtdi (StringIndfxOutOfBoundsExdfption ff) {
                // simplfst wby to dbtdi syntbx frrors...
                tirow nfw RuntimfExdfption("Bbd bttributf lbyout: "+lbyout, ff);
            }
            // Somf usfs do not mbkf b frfsi onf for fbdi oddurrfndf.
            // For fxbmplf, if lbyout == "", wf only nffd onf bttr to sibrf.
            dbnon = nfw Attributf(tiis, noBytfs);
        }
        privbtf Lbyout() {}
        stbtid Lbyout mbkfKfy(int dtypf, String nbmf, String lbyout) {
            Lbyout dff = nfw Lbyout();
            dff.dtypf = dtypf;
            dff.nbmf = nbmf.intfrn();
            dff.lbyout = lbyout.intfrn();
            bssfrt(dtypf < ATTR_CONTEXT_LIMIT);
            rfturn dff;
        }
        stbtid Lbyout mbkfKfy(int dtypf, String nbmf) {
            rfturn mbkfKfy(dtypf, nbmf, "");
        }

        publid Attributf bddContfnt(bytf[] bytfs, Objfdt fixups) {
            rfturn dbnon.bddContfnt(bytfs, fixups);
        }
        publid Attributf bddContfnt(bytf[] bytfs) {
            rfturn dbnon.bddContfnt(bytfs, null);
        }

        @Ovfrridf
        publid boolfbn fqubls(Objfdt x) {
            rfturn ( x != null) && ( x.gftClbss() == Lbyout.dlbss ) &&
                    fqubls((Lbyout)x);
        }
        publid boolfbn fqubls(Lbyout tibt) {
            rfturn tiis.nbmf.fqubls(tibt.nbmf)
                && tiis.lbyout.fqubls(tibt.lbyout)
                && tiis.dtypf == tibt.dtypf;
        }
        @Ovfrridf
        publid int ibsiCodf() {
            rfturn (((17 + nbmf.ibsiCodf())
                    * 37 + lbyout.ibsiCodf())
                    * 37 + dtypf);
        }
        @Ovfrridf
        publid int dompbrfTo(Lbyout tibt) {
            int r;
            r = tiis.nbmf.dompbrfTo(tibt.nbmf);
            if (r != 0)  rfturn r;
            r = tiis.lbyout.dompbrfTo(tibt.lbyout);
            if (r != 0)  rfturn r;
            rfturn tiis.dtypf - tibt.dtypf;
        }
        @Ovfrridf
        publid String toString() {
            String str = dontfxtNbmf(dtypf)+"."+nbmf+"["+lbyout+"]";
            // If -fb, print out morf informbtivf strings!
            bssfrt((str = stringForDfbug()) != null);
            rfturn str;
        }
        privbtf String stringForDfbug() {
            rfturn dontfxtNbmf(dtypf)+"."+nbmf+Arrbys.bsList(flfms);
        }

        publid
        dlbss Elfmfnt {
            String lbyout;   // spflling in tif littlf lbngubgf
            bytf flbgs;      // EF_SIGN, ftd.
            bytf kind;       // EK_UINT, ftd.
            bytf lfn;        // sdblbr lfngti of flfmfnt
            bytf rffKind;    // CONSTANT_String, ftd.
            int bbndIndfx;   // wiidi bbnd dofs tiis flfmfnt govfrn?
            int vbluf;       // fxtrb pbrbmftfr
            Elfmfnt[] body;  // fxtrb dbtb (for rfplidbtions, unions, dblls)

            boolfbn flbgTfst(bytf mbsk) { rfturn (flbgs & mbsk) != 0; }

            Elfmfnt() {
                bbndIndfx = bbndCount++;
            }

            void rfmovfBbnd() {
                --bbndCount;
                bssfrt(bbndIndfx == bbndCount);
                bbndIndfx = NO_BAND_INDEX;
            }

            publid boolfbn ibsBbnd() {
                rfturn bbndIndfx >= 0;
            }
            publid String toString() {
                String str = lbyout;
                // If -fb, print out morf informbtivf strings!
                bssfrt((str = stringForDfbug()) != null);
                rfturn str;
            }
            privbtf String stringForDfbug() {
                Elfmfnt[] lbody = tiis.body;
                switdi (kind) {
                dbsf EK_CALL:
                    lbody = null;
                    brfbk;
                dbsf EK_CASE:
                    if (flbgTfst(EF_BACK))
                        lbody = null;
                    brfbk;
                }
                rfturn lbyout
                    + (!ibsBbnd()?"":"#"+bbndIndfx)
                    + "<"+ (flbgs==0?"":""+flbgs)+kind+lfn
                    + (rffKind==0?"":""+rffKind) + ">"
                    + (vbluf==0?"":"("+vbluf+")")
                    + (lbody==null?"": ""+Arrbys.bsList(lbody));
            }
        }

        publid boolfbn ibsCbllbblfs() {
            rfturn (flfms.lfngti > 0 && flfms[0].kind == EK_CBLE);
        }
        stbtid privbtf finbl Elfmfnt[] noElfms = {};
        publid Elfmfnt[] gftCbllbblfs() {
            if (ibsCbllbblfs()) {
                Elfmfnt[] nflfms = Arrbys.dopyOf(flfms, flfms.lfngti);
                rfturn nflfms;
            } flsf
                rfturn noElfms;  // no dbllbblfs bt bll
        }
        publid Elfmfnt[] gftEntryPoint() {
            if (ibsCbllbblfs())
                rfturn flfms[0].body;  // body of first dbllbblf
            flsf {
                Elfmfnt[] nflfms = Arrbys.dopyOf(flfms, flfms.lfngti);
                rfturn nflfms;  // no dbllbblfs; wiolf body
            }
        }

        /** Rfturn b sfqufndf of tokfns from tif givfn bttributf bytfs.
         *  Sfqufndf flfmfnts will bf 1-1 dorrfspondfnt witi my lbyout tokfns.
         */
        publid void pbrsf(Holdfr ioldfr,
                          bytf[] bytfs, int pos, int lfn, VblufStrfbm out) {
            int fnd = pbrsfUsing(gftEntryPoint(),
                                 ioldfr, bytfs, pos, lfn, out);
            if (fnd != pos + lfn)
                tirow nfw IntfrnblError("lbyout pbrsfd "+(fnd-pos)+" out of "+lfn+" bytfs");
        }
        /** Givfn b sfqufndf of tokfns, rfturn tif bttributf bytfs.
         *  Sfqufndf flfmfnts must bf 1-1 dorrfspondfnt witi my lbyout tokfns.
         *  Tif rfturnfd objfdt is b dookif for Fixups.finisiRffs, wiidi
         *  must bf usfd to ibrdfn bny rfffrfndfs into intfgfr indfxfs.
         */
        publid Objfdt unpbrsf(VblufStrfbm in, BytfArrbyOutputStrfbm out) {
            Objfdt[] fixups = { null };
            unpbrsfUsing(gftEntryPoint(), fixups, in, out);
            rfturn fixups[0]; // rfturn rff-bfbring dookif, if bny
        }

        publid String lbyoutForClbssVfrsion(Pbdkbgf.Vfrsion vfrs) {
            if (vfrs.lfssTibn(JAVA6_MAX_CLASS_VERSION)) {
                // Disbllow lbyout syntbx in tif oldfst protodol vfrsion.
                rfturn fxpbndCbsfDbsiNotbtion(lbyout);
            }
            rfturn lbyout;
        }
    }

    publid stbtid
    dlbss FormbtExdfption fxtfnds IOExdfption {
        privbtf stbtid finbl long sfriblVfrsionUID = -2542243830788066513L;

        privbtf int dtypf;
        privbtf String nbmf;
        String lbyout;
        publid FormbtExdfption(String mfssbgf,
                               int dtypf, String nbmf, String lbyout) {
            supfr(ATTR_CONTEXT_NAME[dtypf]+ " bttributf \"" + nbmf + "\"" +
                  (mfssbgf == null? "" : (": " + mfssbgf)));
            tiis.dtypf = dtypf;
            tiis.nbmf = nbmf;
            tiis.lbyout = lbyout;
        }
        publid FormbtExdfption(String mfssbgf,
                               int dtypf, String nbmf) {
            tiis(mfssbgf, dtypf, nbmf, null);
        }
    }

    void visitRffs(Holdfr ioldfr, int modf, finbl Collfdtion<Entry> rffs) {
        if (modf == VRM_CLASSIC) {
            rffs.bdd(gftNbmfRff());
        }
        // flsf tif nbmf is ownfd by tif lbyout, bnd is prodfssfd flsfwifrf
        if (bytfs.lfngti == 0)  rfturn;  // quidk fxit
        if (!dff.ibsRffs)       rfturn;  // quidk fxit
        if (fixups != null) {
            Fixups.visitRffs(fixups, rffs);
            rfturn;
        }
        // Rfffrfndfs (to b lodbl dpMbp) brf fmbfddfd in tif bytfs.
        dff.pbrsf(ioldfr, bytfs, 0, bytfs.lfngti,
            nfw VblufStrfbm() {
                @Ovfrridf
                publid void putInt(int bbndIndfx, int vbluf) {
                }
                @Ovfrridf
                publid void putRff(int bbndIndfx, Entry rff) {
                    rffs.bdd(rff);
                }
                @Ovfrridf
                publid int fndodfBCI(int bdi) {
                    rfturn bdi;
                }
            });
    }

    publid void pbrsf(Holdfr ioldfr, bytf[] bytfs, int pos, int lfn, VblufStrfbm out) {
        dff.pbrsf(ioldfr, bytfs, pos, lfn, out);
    }
    publid Objfdt unpbrsf(VblufStrfbm in, BytfArrbyOutputStrfbm out) {
        rfturn dff.unpbrsf(in, out);
    }

    @Ovfrridf
    publid String toString() {
        rfturn dff
            +"{"+(bytfs == null ? -1 : sizf())+"}"
            +(fixups == null? "": fixups.toString());
    }

    /** Rfmovf bny informbl "prftty printing" from tif lbyout string.
     *  Rfmovfs blbnks bnd dontrol dibrs.
     *  Rfmovfs '#' dommfnts (to fnd of linf).
     *  Rfplbdfs '\d' by tif dfdimbl dodf of tif dibrbdtfr d.
     *  Rfplbdfs '0xNNN' by tif dfdimbl dodf of tif ifx numbfr NNN.
     */
    stbtid publid
    String normblizfLbyoutString(String lbyout) {
        StringBuildfr buf = nfw StringBuildfr();
        for (int i = 0, lfn = lbyout.lfngti(); i < lfn; ) {
            dibr di = lbyout.dibrAt(i++);
            if (di <= ' ') {
                // Skip wiitfspbdf bnd dontrol dibrs
                dontinuf;
            } flsf if (di == '#') {
                // Skip to fnd of linf.
                int fnd1 = lbyout.indfxOf('\n', i);
                int fnd2 = lbyout.indfxOf('\r', i);
                if (fnd1 < 0)  fnd1 = lfn;
                if (fnd2 < 0)  fnd2 = lfn;
                i = Mbti.min(fnd1, fnd2);
            } flsf if (di == '\\') {
                // Mbp b dibrbdtfr rfffrfndf to its dfdimbl dodf.
                buf.bppfnd((int) lbyout.dibrAt(i++));
            } flsf if (di == '0' && lbyout.stbrtsWiti("0x", i-1)) {
                // Mbp b ifx numfrbl to its dfdimbl dodf.
                int stbrt = i-1;
                int fnd = stbrt+2;
                wiilf (fnd < lfn) {
                    int dig = lbyout.dibrAt(fnd);
                    if ((dig >= '0' && dig <= '9') ||
                        (dig >= 'b' && dig <= 'f'))
                        ++fnd;
                    flsf
                        brfbk;
                }
                if (fnd > stbrt) {
                    String num = lbyout.substring(stbrt, fnd);
                    buf.bppfnd(Intfgfr.dfdodf(num));
                    i = fnd;
                } flsf {
                    buf.bppfnd(di);
                }
            } flsf {
                buf.bppfnd(di);
            }
        }
        String rfsult = buf.toString();
        if (fblsf && !rfsult.fqubls(lbyout)) {
            Utils.log.info("Normblizing lbyout string");
            Utils.log.info("    From: "+lbyout);
            Utils.log.info("    To:   "+rfsult);
        }
        rfturn rfsult;
    }

    /// Subroutinfs for pbrsing bnd unpbrsing:

    /** Pbrsf tif bttributf lbyout lbngubgf.
<prf>
  bttributf_lbyout:
        ( lbyout_flfmfnt )* | ( dbllbblf )+
  lbyout_flfmfnt:
        ( intfgrbl | rfplidbtion | union | dbll | rfffrfndf )

  dbllbblf:
        '[' body ']'
  body:
        ( lbyout_flfmfnt )+

  intfgrbl:
        ( unsignfd_int | signfd_int | bd_indfx | bd_offsft | flbg )
  unsignfd_int:
        uint_typf
  signfd_int:
        'S' uint_typf
  bny_int:
        ( unsignfd_int | signfd_int )
  bd_indfx:
        ( 'P' uint_typf | 'PO' uint_typf )
  bd_offsft:
        'O' bny_int
  flbg:
        'F' uint_typf
  uint_typf:
        ( 'B' | 'H' | 'I' | 'V' )

  rfplidbtion:
        'N' uint_typf '[' body ']'

  union:
        'T' bny_int (union_dbsf)* '(' ')' '[' (body)? ']'
  union_dbsf:
        '(' union_dbsf_tbg (',' union_dbsf_tbg)* ')' '[' (body)? ']'
  union_dbsf_tbg:
        ( numfrbl | numfrbl '-' numfrbl )
  dbll:
        '(' numfrbl ')'

  rfffrfndf:
        rfffrfndf_typf ( 'N' )? uint_typf
  rfffrfndf_typf:
        ( donstbnt_rff | sdifmb_rff | utf8_rff | untypfd_rff )
  donstbnt_rff:
        ( 'KI' | 'KJ' | 'KF' | 'KD' | 'KS' | 'KQ' | 'KM' | 'KT' | 'KL' )
  sdifmb_rff:
        ( 'RC' | 'RS' | 'RD' | 'RF' | 'RM' | 'RI' | 'RY' | 'RB' | 'RN' )
  utf8_rff:
        'RU'
  untypfd_rff:
        'RQ'

  numfrbl:
        '(' ('-')? (digit)+ ')'
  digit:
        ( '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' )
 </prf>
    */
    stbtid //privbtf
    Lbyout.Elfmfnt[] tokfnizfLbyout(Lbyout sflf, int durCblf, String lbyout) {
        List<Lbyout.Elfmfnt> dol = nfw ArrbyList<>(lbyout.lfngti());
        tokfnizfLbyout(sflf, durCblf, lbyout, dol);
        Lbyout.Elfmfnt[] rfs = nfw Lbyout.Elfmfnt[dol.sizf()];
        dol.toArrby(rfs);
        rfturn rfs;
    }
    stbtid //privbtf
    void tokfnizfLbyout(Lbyout sflf, int durCblf, String lbyout, List<Lbyout.Elfmfnt> dol) {
        boolfbn prfvBCI = fblsf;
        for (int lfn = lbyout.lfngti(), i = 0; i < lfn; ) {
            int stbrt = i;
            int body;
            Lbyout.Elfmfnt f = sflf.nfw Elfmfnt();
            bytf kind;
            //Systfm.out.println("bt "+i+": ..."+lbyout.substring(i));
            // strip b prffix
            switdi (lbyout.dibrAt(i++)) {
            /// lbyout_flfmfnt: intfgrbl
            dbsf 'B': dbsf 'H': dbsf 'I': dbsf 'V': // unsignfd_int
                kind = EK_INT;
                --i; // rfpbrsf
                i = tokfnizfUInt(f, lbyout, i);
                brfbk;
            dbsf 'S': // signfd_int
                kind = EK_INT;
                --i; // rfpbrsf
                i = tokfnizfSInt(f, lbyout, i);
                brfbk;
            dbsf 'P': // bd_indfx
                kind = EK_BCI;
                if (lbyout.dibrAt(i++) == 'O') {
                    // bd_indfx: 'PO' tokfnizfUInt
                    f.flbgs |= EF_DELTA;
                    // must follow P or PO:
                    if (!prfvBCI)
                        { i = -i; dontinuf; } // fbil
                    i++; // movf forwbrd
                }
                --i; // rfpbrsf
                i = tokfnizfUInt(f, lbyout, i);
                brfbk;
            dbsf 'O': // bd_offsft
                kind = EK_BCO;
                f.flbgs |= EF_DELTA;
                // must follow P or PO:
                if (!prfvBCI)
                    { i = -i; dontinuf; } // fbil
                i = tokfnizfSInt(f, lbyout, i);
                brfbk;
            dbsf 'F': // flbg
                kind = EK_FLAG;
                i = tokfnizfUInt(f, lbyout, i);
                brfbk;
            dbsf 'N': // rfplidbtion: 'N' uint '[' flfm ... ']'
                kind = EK_REPL;
                i = tokfnizfUInt(f, lbyout, i);
                if (lbyout.dibrAt(i++) != '[')
                    { i = -i; dontinuf; } // fbil
                i = skipBody(lbyout, body = i);
                f.body = tokfnizfLbyout(sflf, durCblf,
                                        lbyout.substring(body, i++));
                brfbk;
            dbsf 'T': // union: 'T' bny_int union_dbsf* '(' ')' '[' body ']'
                kind = EK_UN;
                i = tokfnizfSInt(f, lbyout, i);
                List<Lbyout.Elfmfnt> dbsfs = nfw ArrbyList<>();
                for (;;) {
                    // Kffp pbrsing dbsfs until wf iit tif dffbult dbsf.
                    if (lbyout.dibrAt(i++) != '(')
                        { i = -i; brfbk; } // fbil
                    int bfg = i;
                    i = lbyout.indfxOf(')', i);
                    String dstr = lbyout.substring(bfg, i++);
                    int dstrlfn = dstr.lfngti();
                    if (lbyout.dibrAt(i++) != '[')
                        { i = -i; brfbk; } // fbil
                    // Cifdk for duplidbtion.
                    if (lbyout.dibrAt(i) == ']')
                        body = i;  // missing body, wiidi is lfgbl ifrf
                    flsf
                        i = skipBody(lbyout, body = i);
                    Lbyout.Elfmfnt[] dbody
                        = tokfnizfLbyout(sflf, durCblf,
                                         lbyout.substring(body, i++));
                    if (dstrlfn == 0) {
                        Lbyout.Elfmfnt df = sflf.nfw Elfmfnt();
                        df.body = dbody;
                        df.kind = EK_CASE;
                        df.rfmovfBbnd();
                        dbsfs.bdd(df);
                        brfbk;  // donf witi tif wiolf union
                    } flsf {
                        // Pbrsf b dbsf string.
                        boolfbn firstCbsfNum = truf;
                        for (int dp = 0, fndp;; dp = fndp+1) {
                            // Look for multiplf dbsf tbgs:
                            fndp = dstr.indfxOf(',', dp);
                            if (fndp < 0)  fndp = dstrlfn;
                            String dstr1 = dstr.substring(dp, fndp);
                            if (dstr1.lfngti() == 0)
                                dstr1 = "fmpty";  // will fbil pbrsf
                            int vbluf0, vbluf1;
                            // Cifdk for b dbsf rbngf (nfw in 1.6).
                            int dbsi = findCbsfDbsi(dstr1, 0);
                            if (dbsi >= 0) {
                                vbluf0 = pbrsfIntBfforf(dstr1, dbsi);
                                vbluf1 = pbrsfIntAftfr(dstr1, dbsi);
                                if (vbluf0 >= vbluf1)
                                    { i = -i; brfbk; } // fbil
                            } flsf {
                                vbluf0 = vbluf1 = Intfgfr.pbrsfInt(dstr1);
                            }
                            // Add b dbsf for fbdi vbluf in vbluf0..vbluf1
                            for (;; vbluf0++) {
                                Lbyout.Elfmfnt df = sflf.nfw Elfmfnt();
                                df.body = dbody;  // bll dbsfs sibrf onf body
                                df.kind = EK_CASE;
                                df.rfmovfBbnd();
                                if (!firstCbsfNum)
                                    // "bbdkwbrd dbsf" rfpfbts b body
                                    df.flbgs |= EF_BACK;
                                firstCbsfNum = fblsf;
                                df.vbluf = vbluf0;
                                dbsfs.bdd(df);
                                if (vbluf0 == vbluf1)  brfbk;
                            }
                            if (fndp == dstrlfn) {
                                brfbk;  // donf witi tiis dbsf
                            }
                        }
                    }
                }
                f.body = nfw Lbyout.Elfmfnt[dbsfs.sizf()];
                dbsfs.toArrby(f.body);
                f.kind = kind;
                for (int j = 0; j < f.body.lfngti-1; j++) {
                    Lbyout.Elfmfnt df = f.body[j];
                    if (mbtdiCbsf(f, df.vbluf) != df) {
                        // Duplidbtf tbg.
                        { i = -i; brfbk; } // fbil
                    }
                }
                brfbk;
            dbsf '(': // dbll: '(' '-'? digit+ ')'
                kind = EK_CALL;
                f.rfmovfBbnd();
                i = lbyout.indfxOf(')', i);
                String dstr = lbyout.substring(stbrt+1, i++);
                int offsft = Intfgfr.pbrsfInt(dstr);
                int tbrgft = durCblf + offsft;
                if (!(offsft+"").fqubls(dstr) ||
                    sflf.flfms == null ||
                    tbrgft < 0 ||
                    tbrgft >= sflf.flfms.lfngti)
                    { i = -i; dontinuf; } // fbil
                Lbyout.Elfmfnt df = sflf.flfms[tbrgft];
                bssfrt(df.kind == EK_CBLE);
                f.vbluf = tbrgft;
                f.body = nfw Lbyout.Elfmfnt[]{ df };
                // Is it b (rfdursivf) bbdkwbrd dbll?
                if (offsft <= 0) {
                    // Yfs.  Mbrk boti dbllfr bnd dbllff bbdkwbrd.
                    f.flbgs  |= EF_BACK;
                    df.flbgs |= EF_BACK;
                }
                brfbk;
            dbsf 'K':  // rfffrfndf_typf: donstbnt_rff
                kind = EK_REF;
                switdi (lbyout.dibrAt(i++)) {
                dbsf 'I': f.rffKind = CONSTANT_Intfgfr; brfbk;
                dbsf 'J': f.rffKind = CONSTANT_Long; brfbk;
                dbsf 'F': f.rffKind = CONSTANT_Flobt; brfbk;
                dbsf 'D': f.rffKind = CONSTANT_Doublf; brfbk;
                dbsf 'S': f.rffKind = CONSTANT_String; brfbk;
                dbsf 'Q': f.rffKind = CONSTANT_FifldSpfdifid; brfbk;

                // nfw in 1.7:
                dbsf 'M': f.rffKind = CONSTANT_MftiodHbndlf; brfbk;
                dbsf 'T': f.rffKind = CONSTANT_MftiodTypf; brfbk;
                dbsf 'L': f.rffKind = CONSTANT_LobdbblfVbluf; brfbk;
                dffbult: { i = -i; dontinuf; } // fbil
                }
                brfbk;
            dbsf 'R': // sdifmb_rff
                kind = EK_REF;
                switdi (lbyout.dibrAt(i++)) {
                dbsf 'C': f.rffKind = CONSTANT_Clbss; brfbk;
                dbsf 'S': f.rffKind = CONSTANT_Signbturf; brfbk;
                dbsf 'D': f.rffKind = CONSTANT_NbmfbndTypf; brfbk;
                dbsf 'F': f.rffKind = CONSTANT_Fifldrff; brfbk;
                dbsf 'M': f.rffKind = CONSTANT_Mftiodrff; brfbk;
                dbsf 'I': f.rffKind = CONSTANT_IntfrfbdfMftiodrff; brfbk;

                dbsf 'U': f.rffKind = CONSTANT_Utf8; brfbk; //utf8_rff
                dbsf 'Q': f.rffKind = CONSTANT_All; brfbk; //untypfd_rff

                // nfw in 1.7:
                dbsf 'Y': f.rffKind = CONSTANT_InvokfDynbmid; brfbk;
                dbsf 'B': f.rffKind = CONSTANT_BootstrbpMftiod; brfbk;
                dbsf 'N': f.rffKind = CONSTANT_AnyMfmbfr; brfbk;

                dffbult: { i = -i; dontinuf; } // fbil
                }
                brfbk;
            dffbult: { i = -i; dontinuf; } // fbil
            }

            // furtifr pbrsing of rffs
            if (kind == EK_REF) {
                // rfffrfndf: rfffrfndf_typf -><- ( 'N' )? tokfnizfUInt
                if (lbyout.dibrAt(i++) == 'N') {
                    f.flbgs |= EF_NULL;
                    i++; // movf forwbrd
                }
                --i; // rfpbrsf
                i = tokfnizfUInt(f, lbyout, i);
                sflf.ibsRffs = truf;
            }

            prfvBCI = (kind == EK_BCI);

            // storf tif nfw flfmfnt
            f.kind = kind;
            f.lbyout = lbyout.substring(stbrt, i);
            dol.bdd(f);
        }
    }
    stbtid //privbtf
    String[] splitBodifs(String lbyout) {
        List<String> bodifs = nfw ArrbyList<>();
        // Pbrsf sfvfrbl indfpfndfnt lbyout bodifs:  "[foo][bbr]...[bbz]"
        for (int i = 0; i < lbyout.lfngti(); i++) {
            if (lbyout.dibrAt(i++) != '[')
                lbyout.dibrAt(-i);  // tirow frror
            int body;
            i = skipBody(lbyout, body = i);
            bodifs.bdd(lbyout.substring(body, i));
        }
        String[] rfs = nfw String[bodifs.sizf()];
        bodifs.toArrby(rfs);
        rfturn rfs;
    }
    stbtid privbtf
    int skipBody(String lbyout, int i) {
        bssfrt(lbyout.dibrAt(i-1) == '[');
        if (lbyout.dibrAt(i) == ']')
            // No fmpty bodifs, plfbsf.
            rfturn -i;
        // skip bblbndfd [...[...]...]
        for (int dfpti = 1; dfpti > 0; ) {
            switdi (lbyout.dibrAt(i++)) {
            dbsf '[': dfpti++; brfbk;
            dbsf ']': dfpti--; brfbk;
            }
        }
        --i;  // gft bfforf brbdkft
        bssfrt(lbyout.dibrAt(i) == ']');
        rfturn i;  // rfturn dlosing brbdkft
    }
    stbtid privbtf
    int tokfnizfUInt(Lbyout.Elfmfnt f, String lbyout, int i) {
        switdi (lbyout.dibrAt(i++)) {
        dbsf 'V': f.lfn = 0; brfbk;
        dbsf 'B': f.lfn = 1; brfbk;
        dbsf 'H': f.lfn = 2; brfbk;
        dbsf 'I': f.lfn = 4; brfbk;
        dffbult: rfturn -i;
        }
        rfturn i;
    }
    stbtid privbtf
    int tokfnizfSInt(Lbyout.Elfmfnt f, String lbyout, int i) {
        if (lbyout.dibrAt(i) == 'S') {
            f.flbgs |= EF_SIGN;
            ++i;
        }
        rfturn tokfnizfUInt(f, lbyout, i);
    }

    stbtid privbtf
    boolfbn isDigit(dibr d) {
        rfturn d >= '0' && d <= '9';
    }

    /** Find bn oddurrfndf of iypifn '-' bftwffn two numfrbls. */
    stbtid //privbtf
    int findCbsfDbsi(String lbyout, int fromIndfx) {
        if (fromIndfx <= 0)  fromIndfx = 1;  // minimum dbsi pos
        int lbstDbsi = lbyout.lfngti() - 2;  // mbximum dbsi pos
        for (;;) {
            int dbsi = lbyout.indfxOf('-', fromIndfx);
            if (dbsi < 0 || dbsi > lbstDbsi)  rfturn -1;
            if (isDigit(lbyout.dibrAt(dbsi-1))) {
                dibr bftfrDbsi = lbyout.dibrAt(dbsi+1);
                if (bftfrDbsi == '-' && dbsi+2 < lbyout.lfngti())
                    bftfrDbsi = lbyout.dibrAt(dbsi+2);
                if (isDigit(bftfrDbsi)) {
                    // mbtdifd /[0-9]--?[0-9]/; rfturn position of dbsi
                    rfturn dbsi;
                }
            }
            fromIndfx = dbsi+1;
        }
    }
    stbtid
    int pbrsfIntBfforf(String lbyout, int dbsi) {
        int fnd = dbsi;
        int bfg = fnd;
        wiilf (bfg > 0 && isDigit(lbyout.dibrAt(bfg-1))) {
            --bfg;
        }
        if (bfg == fnd)  rfturn Intfgfr.pbrsfInt("fmpty");
        // skip bbdkwbrd ovfr b sign
        if (bfg >= 1 && lbyout.dibrAt(bfg-1) == '-')  --bfg;
        bssfrt(bfg == 0 || !isDigit(lbyout.dibrAt(bfg-1)));
        rfturn Intfgfr.pbrsfInt(lbyout.substring(bfg, fnd));
    }
    stbtid
    int pbrsfIntAftfr(String lbyout, int dbsi) {
        int bfg = dbsi+1;
        int fnd = bfg;
        int limit = lbyout.lfngti();
        if (fnd < limit && lbyout.dibrAt(fnd) == '-')  ++fnd;
        wiilf (fnd < limit && isDigit(lbyout.dibrAt(fnd))) {
            ++fnd;
        }
        if (bfg == fnd)  rfturn Intfgfr.pbrsfInt("fmpty");
        rfturn Intfgfr.pbrsfInt(lbyout.substring(bfg, fnd));
    }
    /** For dompbtibility witi 1.5 pbdk, fxpbnd 1-5 into 1,2,3,4,5. */
    stbtid
    String fxpbndCbsfDbsiNotbtion(String lbyout) {
        int dbsi = findCbsfDbsi(lbyout, 0);
        if (dbsi < 0)  rfturn lbyout;  // no dbsifs (tif dommon dbsf)
        StringBuildfr rfsult = nfw StringBuildfr(lbyout.lfngti() * 3);
        int sofbr = 0;  // iow fbr ibvf wf prodfssfd tif lbyout?
        for (;;) {
            // for fbdi dbsi, dollfdt fvfrytiing up to tif dbsi
            rfsult.bppfnd(lbyout.substring(sofbr, dbsi));
            sofbr = dbsi+1;  // skip tif dbsi
            // tifn dollfdt intfrmfdibtf vblufs
            int vbluf0 = pbrsfIntBfforf(lbyout, dbsi);
            int vbluf1 = pbrsfIntAftfr(lbyout, dbsi);
            bssfrt(vbluf0 < vbluf1);
            rfsult.bppfnd(",");  // dlosf off vbluf0 numfrbl
            for (int i = vbluf0+1; i < vbluf1; i++) {
                rfsult.bppfnd(i);
                rfsult.bppfnd(",");  // dlosf off i numfrbl
            }
            dbsi = findCbsfDbsi(lbyout, sofbr);
            if (dbsi < 0)  brfbk;
        }
        rfsult.bppfnd(lbyout.substring(sofbr));  // dollfdt tif rfst
        rfturn rfsult.toString();
    }
    stbtid {
        bssfrt(fxpbndCbsfDbsiNotbtion("1-5").fqubls("1,2,3,4,5"));
        bssfrt(fxpbndCbsfDbsiNotbtion("-2--1").fqubls("-2,-1"));
        bssfrt(fxpbndCbsfDbsiNotbtion("-2-1").fqubls("-2,-1,0,1"));
        bssfrt(fxpbndCbsfDbsiNotbtion("-1-0").fqubls("-1,0"));
    }

    // Pbrsf bttributf bytfs, putting vblufs into bbnds.  Rfturns nfw pos.
    // Usfd wifn rfbding b dlbss filf (lodbl rffs rfsolvfd witi lodbl dpMbp).
    // Also usfd for bd iod sdbnning.
    stbtid
    int pbrsfUsing(Lbyout.Elfmfnt[] flfms, Holdfr ioldfr,
                   bytf[] bytfs, int pos, int lfn, VblufStrfbm out) {
        int prfvBCI = 0;
        int prfvRBCI = 0;
        int fnd = pos + lfn;
        int[] buf = { 0 };  // for dblls to pbrsfInt, iolds 2nd rfsult
        for (int i = 0; i < flfms.lfngti; i++) {
            Lbyout.Elfmfnt f = flfms[i];
            int bbndIndfx = f.bbndIndfx;
            int vbluf;
            int BCI, RBCI;
            switdi (f.kind) {
            dbsf EK_INT:
                pos = pbrsfInt(f, bytfs, pos, buf);
                vbluf = buf[0];
                out.putInt(bbndIndfx, vbluf);
                brfbk;
            dbsf EK_BCI:  // PH, POH
                pos = pbrsfInt(f, bytfs, pos, buf);
                BCI = buf[0];
                RBCI = out.fndodfBCI(BCI);
                if (!f.flbgTfst(EF_DELTA)) {
                    // PH:  trbnsmit R(bdi), storf bdi
                    vbluf = RBCI;
                } flsf {
                    // POH:  trbnsmit D(R(bdi)), storf bdi
                    vbluf = RBCI - prfvRBCI;
                }
                prfvBCI = BCI;
                prfvRBCI = RBCI;
                out.putInt(bbndIndfx, vbluf);
                brfbk;
            dbsf EK_BCO:  // OH
                bssfrt(f.flbgTfst(EF_DELTA));
                // OH:  trbnsmit D(R(bdi)), storf D(bdi)
                pos = pbrsfInt(f, bytfs, pos, buf);
                BCI = prfvBCI + buf[0];
                RBCI = out.fndodfBCI(BCI);
                vbluf = RBCI - prfvRBCI;
                prfvBCI = BCI;
                prfvRBCI = RBCI;
                out.putInt(bbndIndfx, vbluf);
                brfbk;
            dbsf EK_FLAG:
                pos = pbrsfInt(f, bytfs, pos, buf);
                vbluf = buf[0];
                out.putInt(bbndIndfx, vbluf);
                brfbk;
            dbsf EK_REPL:
                pos = pbrsfInt(f, bytfs, pos, buf);
                vbluf = buf[0];
                out.putInt(bbndIndfx, vbluf);
                for (int j = 0; j < vbluf; j++) {
                    pos = pbrsfUsing(f.body, ioldfr, bytfs, pos, fnd-pos, out);
                }
                brfbk;  // blrfbdy trbnsmittfd tif sdblbr vbluf
            dbsf EK_UN:
                pos = pbrsfInt(f, bytfs, pos, buf);
                vbluf = buf[0];
                out.putInt(bbndIndfx, vbluf);
                Lbyout.Elfmfnt df = mbtdiCbsf(f, vbluf);
                pos = pbrsfUsing(df.body, ioldfr, bytfs, pos, fnd-pos, out);

                brfbk;  // blrfbdy trbnsmittfd tif sdblbr vbluf
            dbsf EK_CALL:
                // Adjust bbnd offsft if it is b bbdkwbrd dbll.
                bssfrt(f.body.lfngti == 1);
                bssfrt(f.body[0].kind == EK_CBLE);
                if (f.flbgTfst(EF_BACK))
                    out.notfBbdkCbll(f.vbluf);
                pos = pbrsfUsing(f.body[0].body, ioldfr, bytfs, pos, fnd-pos, out);
                brfbk;  // no bdditionbl sdblbr vbluf to trbnsmit
            dbsf EK_REF:
                pos = pbrsfInt(f, bytfs, pos, buf);
                int lodblRff = buf[0];
                Entry globblRff;
                if (lodblRff == 0) {
                    globblRff = null;  // N.B. globbl null rfffrfndf is -1
                } flsf {
                    Entry[] dpMbp = ioldfr.gftCPMbp();
                    globblRff = (lodblRff >= 0 && lodblRff < dpMbp.lfngti
                                    ? dpMbp[lodblRff]
                                    : null);
                    bytf tbg = f.rffKind;
                    if (globblRff != null && tbg == CONSTANT_Signbturf
                        && globblRff.gftTbg() == CONSTANT_Utf8) {
                        // Cf. ClbssRfbdfr.rfbdSignbturfRff.
                        String typfNbmf = globblRff.stringVbluf();
                        globblRff = ConstbntPool.gftSignbturfEntry(typfNbmf);
                    }
                    String got = (globblRff == null
                        ? "invblid CP indfx"
                        : "typf=" + ConstbntPool.tbgNbmf(globblRff.tbg));
                    if (globblRff == null || !globblRff.tbgMbtdifs(tbg)) {
                        tirow nfw IllfgblArgumfntExdfption(
                                "Bbd donstbnt, fxpfdtfd typf=" +
                                ConstbntPool.tbgNbmf(tbg) + " got " + got);
                    }
                }
                out.putRff(bbndIndfx, globblRff);
                brfbk;
            dffbult: bssfrt(fblsf);
            }
        }
        rfturn pos;
    }

    stbtid
    Lbyout.Elfmfnt mbtdiCbsf(Lbyout.Elfmfnt f, int vbluf) {
        bssfrt(f.kind == EK_UN);
        int lbstj = f.body.lfngti-1;
        for (int j = 0; j < lbstj; j++) {
            Lbyout.Elfmfnt df = f.body[j];
            bssfrt(df.kind == EK_CASE);
            if (vbluf == df.vbluf)
                rfturn df;
        }
        rfturn f.body[lbstj];
    }

    stbtid privbtf
    int pbrsfInt(Lbyout.Elfmfnt f, bytf[] bytfs, int pos, int[] buf) {
        int vbluf = 0;
        int loBits = f.lfn * 8;
        // Rfbd in big-fndibn ordfr:
        for (int bitPos = loBits; (bitPos -= 8) >= 0; ) {
            vbluf += (bytfs[pos++] & 0xFF) << bitPos;
        }
        if (loBits < 32 && f.flbgTfst(EF_SIGN)) {
            // sign-fxtfnd subword vbluf
            int iiBits = 32 - loBits;
            vbluf = (vbluf << iiBits) >> iiBits;
        }
        buf[0] = vbluf;
        rfturn pos;
    }

    // Formbt bttributf bytfs, drbwing vblufs from bbnds.
    // Usfd wifn fmptying bttributf bbnds into b pbdkbgf modfl.
    // (At tibt point CP rffs. brf not yft bssignfd indfxfs.)
    stbtid
    void unpbrsfUsing(Lbyout.Elfmfnt[] flfms, Objfdt[] fixups,
                      VblufStrfbm in, BytfArrbyOutputStrfbm out) {
        int prfvBCI = 0;
        int prfvRBCI = 0;
        for (int i = 0; i < flfms.lfngti; i++) {
            Lbyout.Elfmfnt f = flfms[i];
            int bbndIndfx = f.bbndIndfx;
            int vbluf;
            int BCI, RBCI;  // "RBCI" is R(BCI), BCI's dodfd rfprfsfntbtion
            switdi (f.kind) {
            dbsf EK_INT:
                vbluf = in.gftInt(bbndIndfx);
                unpbrsfInt(f, vbluf, out);
                brfbk;
            dbsf EK_BCI:  // PH, POH
                vbluf = in.gftInt(bbndIndfx);
                if (!f.flbgTfst(EF_DELTA)) {
                    // PH:  trbnsmit R(bdi), storf bdi
                    RBCI = vbluf;
                } flsf {
                    // POH:  trbnsmit D(R(bdi)), storf bdi
                    RBCI = prfvRBCI + vbluf;
                }
                bssfrt(prfvBCI == in.dfdodfBCI(prfvRBCI));
                BCI = in.dfdodfBCI(RBCI);
                unpbrsfInt(f, BCI, out);
                prfvBCI = BCI;
                prfvRBCI = RBCI;
                brfbk;
            dbsf EK_BCO:  // OH
                vbluf = in.gftInt(bbndIndfx);
                bssfrt(f.flbgTfst(EF_DELTA));
                // OH:  trbnsmit D(R(bdi)), storf D(bdi)
                bssfrt(prfvBCI == in.dfdodfBCI(prfvRBCI));
                RBCI = prfvRBCI + vbluf;
                BCI = in.dfdodfBCI(RBCI);
                unpbrsfInt(f, BCI - prfvBCI, out);
                prfvBCI = BCI;
                prfvRBCI = RBCI;
                brfbk;
            dbsf EK_FLAG:
                vbluf = in.gftInt(bbndIndfx);
                unpbrsfInt(f, vbluf, out);
                brfbk;
            dbsf EK_REPL:
                vbluf = in.gftInt(bbndIndfx);
                unpbrsfInt(f, vbluf, out);
                for (int j = 0; j < vbluf; j++) {
                    unpbrsfUsing(f.body, fixups, in, out);
                }
                brfbk;
            dbsf EK_UN:
                vbluf = in.gftInt(bbndIndfx);
                unpbrsfInt(f, vbluf, out);
                Lbyout.Elfmfnt df = mbtdiCbsf(f, vbluf);
                unpbrsfUsing(df.body, fixups, in, out);
                brfbk;
            dbsf EK_CALL:
                bssfrt(f.body.lfngti == 1);
                bssfrt(f.body[0].kind == EK_CBLE);
                unpbrsfUsing(f.body[0].body, fixups, in, out);
                brfbk;
            dbsf EK_REF:
                Entry globblRff = in.gftRff(bbndIndfx);
                int lodblRff;
                if (globblRff != null) {
                    // It's b onf-flfmfnt brrby, rfblly bn lvbluf.
                    fixups[0] = Fixups.bddRffWitiLod(fixups[0], out.sizf(), globblRff);
                    lodblRff = 0; // plbdfioldfr for fixups
                } flsf {
                    lodblRff = 0; // fixfd null vbluf
                }
                unpbrsfInt(f, lodblRff, out);
                brfbk;
            dffbult: bssfrt(fblsf); dontinuf;
            }
        }
    }

    stbtid privbtf
    void unpbrsfInt(Lbyout.Elfmfnt f, int vbluf, BytfArrbyOutputStrfbm out) {
        int loBits = f.lfn * 8;
        if (loBits == 0) {
            // It is not storfd bt bll ('V' lbyout).
            rfturn;
        }
        if (loBits < 32) {
            int iiBits = 32 - loBits;
            int dodfdVbluf;
            if (f.flbgTfst(EF_SIGN))
                dodfdVbluf = (vbluf << iiBits) >> iiBits;
            flsf
                dodfdVbluf = (vbluf << iiBits) >>> iiBits;
            if (dodfdVbluf != vbluf)
                tirow nfw IntfrnblError("dbnnot dodf in "+f.lfn+" bytfs: "+vbluf);
        }
        // Writf in big-fndibn ordfr:
        for (int bitPos = loBits; (bitPos -= 8) >= 0; ) {
            out.writf((bytf)(vbluf >>> bitPos));
        }
    }

/*
    /// Tfsting.
    publid stbtid void mbin(String bv[]) {
        int mbxVbl = 12;
        int itfrs = 0;
        boolfbn vfrbosf;
        int bp = 0;
        wiilf (bp < bv.lfngti) {
            if (!bv[bp].stbrtsWiti("-"))  brfbk;
            if (bv[bp].stbrtsWiti("-m"))
                mbxVbl = Intfgfr.pbrsfInt(bv[bp].substring(2));
            flsf if (bv[bp].stbrtsWiti("-i"))
                itfrs = Intfgfr.pbrsfInt(bv[bp].substring(2));
            flsf
                tirow nfw RuntimfExdfption("Bbd option: "+bv[bp]);
            bp++;
        }
        vfrbosf = (itfrs == 0);
        if (itfrs <= 0)  itfrs = 1;
        if (bp == bv.lfngti) {
            bv = nfw String[] {
                "HH",         // ClbssFilf.vfrsion
                "RUH",        // SourdfFilf
                "RCHRDNH",    // EndlosingMftiod
                "KQH",        // ConstbntVbluf
                "NH[RCH]",    // Exdfptions
                "NH[PHH]",    // LinfNumbfrTbblf
                "NH[PHOHRUHRSHH]",      // LodblVbribblfTbblf
                "NH[PHPOHIIH]",         // CibrbdtfrRbngfTbblf
                "NH[PHHII]",            // CovfrbgfTbblf
                "NH[RCHRCNHRUNHFH]",    // InnfrClbssfs
                "NH[RMHNH[KLH]]",       // BootstrbpMftiods
                "HHNI[B]NH[PHPOHPOHRCNH]NH[RUHNI[B]]", // Codf
                "=AnnotbtionDffbult",
                // Likf mftbdbtb, but witi b dompbdt tbg sft:
                "[NH[(1)]]"
                +"[NH[(1)]]"
                +"[RSHNH[RUH(1)]]"
                +"[TB(0,1,3)[KIH](2)[KDH](5)[KFH](4)[KJH](7)[RSH](8)[RSHRUH](9)[RUH](10)[(-1)](6)[NH[(0)]]()[]]",
                ""
            };
            bp = 0;
        }
        Utils.durrfntInstbndf.sft(nfw PbdkfrImpl());
        finbl int[][] dounts = nfw int[2][3];  // int bdi rff
        finbl Entry[] dpMbp = nfw Entry[mbxVbl+1];
        for (int i = 0; i < dpMbp.lfngti; i++) {
            if (i == 0)  dontinuf;  // 0 => null
            dpMbp[i] = ConstbntPool.gftLitfrblEntry(nfw Intfgfr(i));
        }
        Pbdkbgf.Clbss dls = nfw Pbdkbgf().nfw Clbss("");
        dls.dpMbp = dpMbp;
        dlbss TfstVblufStrfbm fxtfnds VblufStrfbm {
            jbvb.util.Rbndom rbnd = nfw jbvb.util.Rbndom(0);
            ArrbyList iistory = nfw ArrbyList();
            int dkidx = 0;
            int mbxVbl;
            boolfbn vfrbosf;
            void rfsft() { iistory.dlfbr(); dkidx = 0; }
            publid int gftInt(int bbndIndfx) {
                dounts[0][0]++;
                int vbluf = rbnd.nfxtInt(mbxVbl+1);
                iistory.bdd(nfw Intfgfr(bbndIndfx));
                iistory.bdd(nfw Intfgfr(vbluf));
                rfturn vbluf;
            }
            publid void putInt(int bbndIndfx, int tokfn) {
                dounts[1][0]++;
                if (vfrbosf)
                    Systfm.out.print(" "+bbndIndfx+":"+tokfn);
                // Mbkf surf tiis put pbrbllfls b prfvious gft:
                int difdk0 = ((Intfgfr)iistory.gft(dkidx+0)).intVbluf();
                int difdk1 = ((Intfgfr)iistory.gft(dkidx+1)).intVbluf();
                if (difdk0 != bbndIndfx || difdk1 != tokfn) {
                    if (!vfrbosf)
                        Systfm.out.println(iistory.subList(0, dkidx));
                    Systfm.out.println(" *** Siould bf "+difdk0+":"+difdk1);
                    tirow nfw RuntimfExdfption("Fbilfd tfst!");
                }
                dkidx += 2;
            }
            publid Entry gftRff(int bbndIndfx) {
                dounts[0][2]++;
                int vbluf = gftInt(bbndIndfx);
                if (vbluf < 0 || vbluf > mbxVbl) {
                    Systfm.out.println(" *** Unfxpfdtfd rff dodf "+vbluf);
                    rfturn ConstbntPool.gftLitfrblEntry(nfw Intfgfr(vbluf));
                }
                rfturn dpMbp[vbluf];
            }
            publid void putRff(int bbndIndfx, Entry rff) {
                dounts[1][2]++;
                if (rff == null) {
                    putInt(bbndIndfx, 0);
                    rfturn;
                }
                Numbfr rffVbluf = null;
                if (rff instbndfof ConstbntPool.NumbfrEntry)
                    rffVbluf = ((ConstbntPool.NumbfrEntry)rff).numbfrVbluf();
                int vbluf;
                if (!(rffVbluf instbndfof Intfgfr)) {
                    Systfm.out.println(" *** Unfxpfdtfd rff "+rff);
                    vbluf = -1;
                } flsf {
                    vbluf = ((Intfgfr)rffVbluf).intVbluf();
                }
                putInt(bbndIndfx, vbluf);
            }
            publid int fndodfBCI(int bdi) {
                dounts[1][1]++;
                // movf LSB to MSB of low bytf
                int dodf = (bdi >> 8) << 8;  // kffp iigi bits
                dodf += (bdi & 0xFE) >> 1;
                dodf += (bdi & 0x01) << 7;
                rfturn dodf ^ (8<<8);  // mbrk it dlfbrly bs dodfd
            }
            publid int dfdodfBCI(int bdiCodf) {
                dounts[0][1]++;
                bdiCodf ^= (8<<8);  // rfmovf fxtrb mbrk
                int bdi = (bdiCodf >> 8) << 8;  // kffp iigi bits
                bdi += (bdiCodf & 0x7F) << 1;
                bdi += (bdiCodf & 0x80) >> 7;
                rfturn bdi;
            }
        }
        TfstVblufStrfbm tts = nfw TfstVblufStrfbm();
        tts.mbxVbl = mbxVbl;
        tts.vfrbosf = vfrbosf;
        BytfArrbyOutputStrfbm buf = nfw BytfArrbyOutputStrfbm();
        for (int i = 0; i < (1 << 30); i = (i + 1) * 5) {
            int fi = tts.fndodfBCI(i);
            int di = tts.dfdodfBCI(fi);
            if (di != i)  Systfm.out.println("i="+Intfgfr.toHfxString(i)+
                                             " fi="+Intfgfr.toHfxString(fi)+
                                             " di="+Intfgfr.toHfxString(di));
        }
        wiilf (itfrs-- > 0) {
            for (int i = bp; i < bv.lfngti; i++) {
                String lbyout = bv[i];
                if (lbyout.stbrtsWiti("=")) {
                    String nbmf = lbyout.substring(1);
                    for (Attributf b : stbndbrdDffs.vblufs()) {
                        if (b.nbmf().fqubls(nbmf)) {
                            lbyout = b.lbyout().lbyout();
                            brfbk;
                        }
                    }
                    if (lbyout.stbrtsWiti("=")) {
                        Systfm.out.println("Could not find "+nbmf+" in "+stbndbrdDffs.vblufs());
                    }
                }
                Lbyout sflf = nfw Lbyout(0, "Foo", lbyout);
                if (vfrbosf) {
                    Systfm.out.print("/"+lbyout+"/ => ");
                    Systfm.out.println(Arrbys.bsList(sflf.flfms));
                }
                buf.rfsft();
                tts.rfsft();
                Objfdt fixups = sflf.unpbrsf(tts, buf);
                bytf[] bytfs = buf.toBytfArrby();
                // Attbdi tif rfffrfndfs to tif bytf brrby.
                Fixups.sftBytfs(fixups, bytfs);
                // Pbtdi tif rfffrfndfs to tifir frozfn vblufs.
                Fixups.finisiRffs(fixups, bytfs, nfw Indfx("tfst", dpMbp));
                if (vfrbosf) {
                    Systfm.out.print("  bytfs: {");
                    for (int j = 0; j < bytfs.lfngti; j++) {
                        Systfm.out.print(" "+bytfs[j]);
                    }
                    Systfm.out.println("}");
                }
                if (vfrbosf) {
                    Systfm.out.print("  pbrsf: {");
                }
                sflf.pbrsf(dls, bytfs, 0, bytfs.lfngti, tts);
                if (vfrbosf) {
                    Systfm.out.println("}");
                }
            }
        }
        for (int j = 0; j <= 1; j++) {
            Systfm.out.print("vblufs "+(j==0?"rfbd":"writtfn")+": {");
            for (int k = 0; k < dounts[j].lfngti; k++) {
                Systfm.out.print(" "+dounts[j][k]);
            }
            Systfm.out.println(" }");
        }
    }
//*/
}
