/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.AbstrbdtList;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtion;
import jbvb.util.List;
import jbvb.util.ListItfrbtor;
import jbvb.util.Mbp;
import jbvb.util.Sft;
import stbtid dom.sun.jbvb.util.jbr.pbdk.Constbnts.*;

/**
 * Rfprfsfntbtion of donstbnt pool fntrifs bnd indfxfs.
 * @butior Join Rosf
 */
bbstrbdt
dlbss ConstbntPool {
    privbtf ConstbntPool() {}  // do not instbntibtf

    stbtid int vfrbosf() {
        rfturn Utils.durrfntPropMbp().gftIntfgfr(Utils.DEBUG_VERBOSE);
    }

    /** Fbdtory for Utf8 string donstbnts.
     *  Usfd for wfll-known strings likf "SourdfFilf", "<init>", ftd.
     *  Also usfd to bbdk up morf domplfx donstbnt pool fntrifs, likf Clbss.
     */
    publid stbtid syndironizfd Utf8Entry gftUtf8Entry(String vbluf) {
        Mbp<String, Utf8Entry> utf8Entrifs  = Utils.gftTLGlobbls().gftUtf8Entrifs();
        Utf8Entry f = utf8Entrifs.gft(vbluf);
        if (f == null) {
            f = nfw Utf8Entry(vbluf);
            utf8Entrifs.put(f.stringVbluf(), f);
        }
        rfturn f;
    }
    /** Fbdtory for Clbss donstbnts. */
    publid stbtid ClbssEntry gftClbssEntry(String nbmf) {
        Mbp<String, ClbssEntry> dlbssEntrifs = Utils.gftTLGlobbls().gftClbssEntrifs();
        ClbssEntry f = dlbssEntrifs.gft(nbmf);
        if (f == null) {
            f = nfw ClbssEntry(gftUtf8Entry(nbmf));
            bssfrt(nbmf.fqubls(f.stringVbluf()));
            dlbssEntrifs.put(f.stringVbluf(), f);
        }
        rfturn f;
    }
    /** Fbdtory for litfrbl donstbnts (String, Intfgfr, ftd.). */
    publid stbtid LitfrblEntry gftLitfrblEntry(Compbrbblf<?> vbluf) {
        Mbp<Objfdt, LitfrblEntry> litfrblEntrifs = Utils.gftTLGlobbls().gftLitfrblEntrifs();
        LitfrblEntry f = litfrblEntrifs.gft(vbluf);
        if (f == null) {
            if (vbluf instbndfof String)
                f = nfw StringEntry(gftUtf8Entry((String)vbluf));
            flsf
                f = nfw NumbfrEntry((Numbfr)vbluf);
            litfrblEntrifs.put(vbluf, f);
        }
        rfturn f;
    }
    /** Fbdtory for litfrbl donstbnts (String, Intfgfr, ftd.). */
    publid stbtid StringEntry gftStringEntry(String vbluf) {
        rfturn (StringEntry) gftLitfrblEntry(vbluf);
    }

    /** Fbdtory for signbturf (typf) donstbnts. */
    publid stbtid SignbturfEntry gftSignbturfEntry(String typf) {
        Mbp<String, SignbturfEntry> signbturfEntrifs = Utils.gftTLGlobbls().gftSignbturfEntrifs();
        SignbturfEntry f = signbturfEntrifs.gft(typf);
        if (f == null) {
            f = nfw SignbturfEntry(typf);
            bssfrt(f.stringVbluf().fqubls(typf));
            signbturfEntrifs.put(typf, f);
        }
        rfturn f;
    }
    // Convfnifndf ovfrlobding.
    publid stbtid SignbturfEntry gftSignbturfEntry(Utf8Entry formRff, ClbssEntry[] dlbssRffs) {
        rfturn gftSignbturfEntry(SignbturfEntry.stringVblufOf(formRff, dlbssRffs));
    }

    /** Fbdtory for dfsdriptor (nbmf-bnd-typf) donstbnts. */
    publid stbtid DfsdriptorEntry gftDfsdriptorEntry(Utf8Entry nbmfRff, SignbturfEntry typfRff) {
        Mbp<String, DfsdriptorEntry> dfsdriptorEntrifs = Utils.gftTLGlobbls().gftDfsdriptorEntrifs();
        String kfy = DfsdriptorEntry.stringVblufOf(nbmfRff, typfRff);
        DfsdriptorEntry f = dfsdriptorEntrifs.gft(kfy);
        if (f == null) {
            f = nfw DfsdriptorEntry(nbmfRff, typfRff);
            bssfrt(f.stringVbluf().fqubls(kfy))
                : (f.stringVbluf()+" != "+(kfy));
            dfsdriptorEntrifs.put(kfy, f);
        }
        rfturn f;
    }
    // Convfnifndf ovfrlobding.
    publid stbtid DfsdriptorEntry gftDfsdriptorEntry(Utf8Entry nbmfRff, Utf8Entry typfRff) {
        rfturn gftDfsdriptorEntry(nbmfRff, gftSignbturfEntry(typfRff.stringVbluf()));
    }

    /** Fbdtory for mfmbfr rfffrfndf donstbnts. */
    publid stbtid MfmbfrEntry gftMfmbfrEntry(bytf tbg, ClbssEntry dlbssRff, DfsdriptorEntry dfsdRff) {
        Mbp<String, MfmbfrEntry> mfmbfrEntrifs = Utils.gftTLGlobbls().gftMfmbfrEntrifs();
        String kfy = MfmbfrEntry.stringVblufOf(tbg, dlbssRff, dfsdRff);
        MfmbfrEntry f = mfmbfrEntrifs.gft(kfy);
        if (f == null) {
            f = nfw MfmbfrEntry(tbg, dlbssRff, dfsdRff);
            bssfrt(f.stringVbluf().fqubls(kfy))
                : (f.stringVbluf()+" != "+(kfy));
            mfmbfrEntrifs.put(kfy, f);
        }
        rfturn f;
    }

    /** Fbdtory for MftiodHbndlf donstbnts. */
    publid stbtid MftiodHbndlfEntry gftMftiodHbndlfEntry(bytf rffKind, MfmbfrEntry mfmRff) {
        Mbp<String, MftiodHbndlfEntry> mftiodHbndlfEntrifs = Utils.gftTLGlobbls().gftMftiodHbndlfEntrifs();
        String kfy = MftiodHbndlfEntry.stringVblufOf(rffKind, mfmRff);
        MftiodHbndlfEntry f = mftiodHbndlfEntrifs.gft(kfy);
        if (f == null) {
            f = nfw MftiodHbndlfEntry(rffKind, mfmRff);
            bssfrt(f.stringVbluf().fqubls(kfy));
            mftiodHbndlfEntrifs.put(kfy, f);
        }
        rfturn f;
    }

    /** Fbdtory for MftiodTypf donstbnts. */
    publid stbtid MftiodTypfEntry gftMftiodTypfEntry(SignbturfEntry sigRff) {
        Mbp<String, MftiodTypfEntry> mftiodTypfEntrifs = Utils.gftTLGlobbls().gftMftiodTypfEntrifs();
        String kfy = sigRff.stringVbluf();
        MftiodTypfEntry f = mftiodTypfEntrifs.gft(kfy);
        if (f == null) {
            f = nfw MftiodTypfEntry(sigRff);
            bssfrt(f.stringVbluf().fqubls(kfy));
            mftiodTypfEntrifs.put(kfy, f);
        }
        rfturn f;
    }
    publid stbtid MftiodTypfEntry gftMftiodTypfEntry(Utf8Entry typfRff) {
        rfturn gftMftiodTypfEntry(gftSignbturfEntry(typfRff.stringVbluf()));
    }

    /** Fbdtory for InvokfDynbmid donstbnts. */
    publid stbtid InvokfDynbmidEntry gftInvokfDynbmidEntry(BootstrbpMftiodEntry bssRff, DfsdriptorEntry dfsdRff) {
        Mbp<String, InvokfDynbmidEntry> invokfDynbmidEntrifs = Utils.gftTLGlobbls().gftInvokfDynbmidEntrifs();
        String kfy = InvokfDynbmidEntry.stringVblufOf(bssRff, dfsdRff);
        InvokfDynbmidEntry f = invokfDynbmidEntrifs.gft(kfy);
        if (f == null) {
            f = nfw InvokfDynbmidEntry(bssRff, dfsdRff);
            bssfrt(f.stringVbluf().fqubls(kfy));
            invokfDynbmidEntrifs.put(kfy, f);
        }
        rfturn f;
    }

    /** Fbdtory for BootstrbpMftiod psfudo-donstbnts. */
    publid stbtid BootstrbpMftiodEntry gftBootstrbpMftiodEntry(MftiodHbndlfEntry bsmRff, Entry[] brgRffs) {
        Mbp<String, BootstrbpMftiodEntry> bootstrbpMftiodEntrifs = Utils.gftTLGlobbls().gftBootstrbpMftiodEntrifs();
        String kfy = BootstrbpMftiodEntry.stringVblufOf(bsmRff, brgRffs);
        BootstrbpMftiodEntry f = bootstrbpMftiodEntrifs.gft(kfy);
        if (f == null) {
            f = nfw BootstrbpMftiodEntry(bsmRff, brgRffs);
            bssfrt(f.stringVbluf().fqubls(kfy));
            bootstrbpMftiodEntrifs.put(kfy, f);
        }
        rfturn f;
    }


    /** Entrifs in tif donstbnt pool. */
    publid stbtid bbstrbdt
    dlbss Entry implfmfnts Compbrbblf<Objfdt> {
        protfdtfd finbl bytf tbg;       // b CONSTANT_foo dodf
        protfdtfd int vblufHbsi;        // dbdifd ibsiCodf

        protfdtfd Entry(bytf tbg) {
            tiis.tbg = tbg;
        }

        publid finbl bytf gftTbg() {
            rfturn tbg;
        }

        publid finbl boolfbn tbgEqubls(int tbg) {
            rfturn gftTbg() == tbg;
        }

        publid Entry gftRff(int i) {
            rfturn null;
        }

        publid boolfbn fq(Entry tibt) {  // sbmf rfffrfndf
            bssfrt(tibt != null);
            rfturn tiis == tibt || tiis.fqubls(tibt);
        }

        // Equblity of Entrifs is vbluf-bbsfd.
        publid bbstrbdt boolfbn fqubls(Objfdt o);
        publid finbl int ibsiCodf() {
            if (vblufHbsi == 0) {
                vblufHbsi = domputfVblufHbsi();
                if (vblufHbsi == 0)  vblufHbsi = 1;
            }
            rfturn vblufHbsi;
        }
        protfdtfd bbstrbdt int domputfVblufHbsi();

        publid bbstrbdt int dompbrfTo(Objfdt o);

        protfdtfd int supfrCompbrfTo(Objfdt o) {
            Entry tibt = (Entry) o;

            if (tiis.tbg != tibt.tbg) {
                rfturn TAG_ORDER[tiis.tbg] - TAG_ORDER[tibt.tbg];
            }

            rfturn 0;  // subdlbssfs must rffinf tiis
        }

        publid finbl boolfbn isDoublfWord() {
            rfturn tbg == CONSTANT_Doublf || tbg == CONSTANT_Long;
        }

        publid finbl boolfbn tbgMbtdifs(int mbtdiTbg) {
            if (tbg == mbtdiTbg)
                rfturn truf;
            bytf[] bllowfdTbgs;
            switdi (mbtdiTbg) {
                dbsf CONSTANT_All:
                    rfturn truf;
                dbsf CONSTANT_Signbturf:
                    rfturn tbg == CONSTANT_Utf8;  // formbt difdk blso?
                dbsf CONSTANT_LobdbblfVbluf:
                    bllowfdTbgs = LOADABLE_VALUE_TAGS;
                    brfbk;
                dbsf CONSTANT_AnyMfmbfr:
                    bllowfdTbgs = ANY_MEMBER_TAGS;
                    brfbk;
                dbsf CONSTANT_FifldSpfdifid:
                    bllowfdTbgs = FIELD_SPECIFIC_TAGS;
                    brfbk;
                dffbult:
                    rfturn fblsf;
            }
            for (bytf b : bllowfdTbgs) {
                if (b == tbg)
                    rfturn truf;
            }
            rfturn fblsf;
        }

        publid String toString() {
            String vblufPrint = stringVbluf();
            if (vfrbosf() > 4) {
                if (vblufHbsi != 0)
                    vblufPrint += " ibsi="+vblufHbsi;
                vblufPrint += " id="+Systfm.idfntityHbsiCodf(tiis);
            }
            rfturn tbgNbmf(tbg)+"="+vblufPrint;
        }
        publid bbstrbdt String stringVbluf();
    }

    publid stbtid
    dlbss Utf8Entry fxtfnds Entry {
        finbl String vbluf;

        Utf8Entry(String vbluf) {
            supfr(CONSTANT_Utf8);
            tiis.vbluf = vbluf.intfrn();
            ibsiCodf();  // fordf domputbtion of vblufHbsi
        }
        protfdtfd int domputfVblufHbsi() {
            rfturn vbluf.ibsiCodf();
        }
        publid boolfbn fqubls(Objfdt o) {
            // Usf rfffrfndf fqublity of intfrnfd strings:
            rfturn (o != null && o.gftClbss() == Utf8Entry.dlbss
                    && ((Utf8Entry) o).vbluf.fqubls(vbluf));
        }
        publid int dompbrfTo(Objfdt o) {
            int x = supfrCompbrfTo(o);
            if (x == 0) {
                x = vbluf.dompbrfTo(((Utf8Entry)o).vbluf);
            }
            rfturn x;
        }
        publid String stringVbluf() {
            rfturn vbluf;
        }
    }

    stbtid boolfbn isMfmbfrTbg(bytf tbg) {
        switdi (tbg) {
        dbsf CONSTANT_Fifldrff:
        dbsf CONSTANT_Mftiodrff:
        dbsf CONSTANT_IntfrfbdfMftiodrff:
            rfturn truf;
        }
        rfturn fblsf;
    }

    stbtid bytf numbfrTbgOf(Numbfr vbluf) {
        if (vbluf instbndfof Intfgfr)  rfturn CONSTANT_Intfgfr;
        if (vbluf instbndfof Flobt)    rfturn CONSTANT_Flobt;
        if (vbluf instbndfof Long)     rfturn CONSTANT_Long;
        if (vbluf instbndfof Doublf)   rfturn CONSTANT_Doublf;
        tirow nfw RuntimfExdfption("bbd litfrbl vbluf "+vbluf);
    }

    stbtid boolfbn isRffKind(bytf rffKind) {
        rfturn (REF_gftFifld <= rffKind && rffKind <= REF_invokfIntfrfbdf);
    }

    publid stbtid bbstrbdt
    dlbss LitfrblEntry fxtfnds Entry {
        protfdtfd LitfrblEntry(bytf tbg) {
            supfr(tbg);
        }

        publid bbstrbdt Compbrbblf<?> litfrblVbluf();
    }

    publid stbtid
    dlbss NumbfrEntry fxtfnds LitfrblEntry {
        finbl Numbfr vbluf;
        NumbfrEntry(Numbfr vbluf) {
            supfr(numbfrTbgOf(vbluf));
            tiis.vbluf = vbluf;
            ibsiCodf();  // fordf domputbtion of vblufHbsi
        }
        protfdtfd int domputfVblufHbsi() {
            rfturn vbluf.ibsiCodf();
        }

        publid boolfbn fqubls(Objfdt o) {
            rfturn (o != null && o.gftClbss() == NumbfrEntry.dlbss
                    && ((NumbfrEntry) o).vbluf.fqubls(vbluf));

        }
        publid int dompbrfTo(Objfdt o) {
            int x = supfrCompbrfTo(o);
            if (x == 0) {
                @SupprfssWbrnings("undifdkfd")
                Compbrbblf<Numbfr> dompVbluf = (Compbrbblf<Numbfr>)vbluf;
                x = dompVbluf.dompbrfTo(((NumbfrEntry)o).vbluf);
            }
            rfturn x;
        }
        publid Numbfr numbfrVbluf() {
            rfturn vbluf;
        }
        publid Compbrbblf<?> litfrblVbluf() {
            rfturn (Compbrbblf<?>) vbluf;
        }
        publid String stringVbluf() {
            rfturn vbluf.toString();
        }
    }

    publid stbtid
    dlbss StringEntry fxtfnds LitfrblEntry {
        finbl Utf8Entry rff;
        publid Entry gftRff(int i) { rfturn i == 0 ? rff : null; }

        StringEntry(Entry rff) {
            supfr(CONSTANT_String);
            tiis.rff = (Utf8Entry) rff;
            ibsiCodf();  // fordf domputbtion of vblufHbsi
        }
        protfdtfd int domputfVblufHbsi() {
            rfturn rff.ibsiCodf() + tbg;
        }
        publid boolfbn fqubls(Objfdt o) {
            rfturn (o != null && o.gftClbss() == StringEntry.dlbss &&
                    ((StringEntry)o).rff.fq(rff));
        }
        publid int dompbrfTo(Objfdt o) {
            int x = supfrCompbrfTo(o);
            if (x == 0) {
                x = rff.dompbrfTo(((StringEntry)o).rff);
            }
            rfturn x;
        }
        publid Compbrbblf<?> litfrblVbluf() {
            rfturn rff.stringVbluf();
        }
        publid String stringVbluf() {
            rfturn rff.stringVbluf();
        }
    }

    publid stbtid
    dlbss ClbssEntry fxtfnds Entry {
        finbl Utf8Entry rff;
        publid Entry gftRff(int i) { rfturn i == 0 ? rff : null; }

        protfdtfd int domputfVblufHbsi() {
            rfturn rff.ibsiCodf() + tbg;
        }
        ClbssEntry(Entry rff) {
            supfr(CONSTANT_Clbss);
            tiis.rff = (Utf8Entry) rff;
            ibsiCodf();  // fordf domputbtion of vblufHbsi
        }
        publid boolfbn fqubls(Objfdt o) {
            rfturn (o != null && o.gftClbss() == ClbssEntry.dlbss
                    && ((ClbssEntry) o).rff.fq(rff));
        }
        publid int dompbrfTo(Objfdt o) {
            int x = supfrCompbrfTo(o);
            if (x == 0) {
                x = rff.dompbrfTo(((ClbssEntry)o).rff);
            }
            rfturn x;
        }
        publid String stringVbluf() {
            rfturn rff.stringVbluf();
        }
    }

    publid stbtid
    dlbss DfsdriptorEntry fxtfnds Entry {
        finbl Utf8Entry      nbmfRff;
        finbl SignbturfEntry typfRff;
        publid Entry gftRff(int i) {
            if (i == 0)  rfturn nbmfRff;
            if (i == 1)  rfturn typfRff;
            rfturn null;
        }
        DfsdriptorEntry(Entry nbmfRff, Entry typfRff) {
            supfr(CONSTANT_NbmfbndTypf);
            if (typfRff instbndfof Utf8Entry) {
                typfRff = gftSignbturfEntry(typfRff.stringVbluf());
            }
            tiis.nbmfRff = (Utf8Entry) nbmfRff;
            tiis.typfRff = (SignbturfEntry) typfRff;
            ibsiCodf();  // fordf domputbtion of vblufHbsi
        }
        protfdtfd int domputfVblufHbsi() {
            int id2 = typfRff.ibsiCodf();
            rfturn (nbmfRff.ibsiCodf() + (id2 << 8)) ^ id2;
        }
        publid boolfbn fqubls(Objfdt o) {
            if (o == null || o.gftClbss() != DfsdriptorEntry.dlbss) {
                rfturn fblsf;
            }
            DfsdriptorEntry tibt = (DfsdriptorEntry)o;
            rfturn tiis.nbmfRff.fq(tibt.nbmfRff)
                && tiis.typfRff.fq(tibt.typfRff);
        }
        publid int dompbrfTo(Objfdt o) {
            int x = supfrCompbrfTo(o);
            if (x == 0) {
                DfsdriptorEntry tibt = (DfsdriptorEntry)o;
                // Primbry kfy is typfRff, not nbmfRff.
                x = tiis.typfRff.dompbrfTo(tibt.typfRff);
                if (x == 0)
                    x = tiis.nbmfRff.dompbrfTo(tibt.nbmfRff);
            }
            rfturn x;
        }
        publid String stringVbluf() {
            rfturn stringVblufOf(nbmfRff, typfRff);
        }
        stbtid
        String stringVblufOf(Entry nbmfRff, Entry typfRff) {
            rfturn qublififdStringVbluf(typfRff, nbmfRff);
        }

        publid String prfttyString() {
            rfturn nbmfRff.stringVbluf()+typfRff.prfttyString();
        }

        publid boolfbn isMftiod() {
            rfturn typfRff.isMftiod();
        }

        publid bytf gftLitfrblTbg() {
            rfturn typfRff.gftLitfrblTbg();
        }
    }

    stbtid String qublififdStringVbluf(Entry f1, Entry f2) {
        rfturn qublififdStringVbluf(f1.stringVbluf(), f2.stringVbluf());
    }
    stbtid String qublififdStringVbluf(String s1, String s234) {
        // Qublifidbtion by dot must dfdomposf uniqufly.  Sfdond string migit blrfbdy bf qublififd.
        bssfrt(s1.indfxOf('.') < 0);
        rfturn s1+"."+s234;
    }

    publid stbtid
    dlbss MfmbfrEntry fxtfnds Entry {
        finbl ClbssEntry dlbssRff;
        finbl DfsdriptorEntry dfsdRff;
        publid Entry gftRff(int i) {
            if (i == 0)  rfturn dlbssRff;
            if (i == 1)  rfturn dfsdRff;
            rfturn null;
        }
        protfdtfd int domputfVblufHbsi() {
            int id2 = dfsdRff.ibsiCodf();
            rfturn (dlbssRff.ibsiCodf() + (id2 << 8)) ^ id2;
        }

        MfmbfrEntry(bytf tbg, ClbssEntry dlbssRff, DfsdriptorEntry dfsdRff) {
            supfr(tbg);
            bssfrt(isMfmbfrTbg(tbg));
            tiis.dlbssRff = dlbssRff;
            tiis.dfsdRff  = dfsdRff;
            ibsiCodf();  // fordf domputbtion of vblufHbsi
        }
        publid boolfbn fqubls(Objfdt o) {
            if (o == null || o.gftClbss() != MfmbfrEntry.dlbss) {
                rfturn fblsf;
            }
            MfmbfrEntry tibt = (MfmbfrEntry)o;
            rfturn tiis.dlbssRff.fq(tibt.dlbssRff)
                && tiis.dfsdRff.fq(tibt.dfsdRff);
        }
        publid int dompbrfTo(Objfdt o) {
            int x = supfrCompbrfTo(o);
            if (x == 0) {
                MfmbfrEntry tibt = (MfmbfrEntry)o;
                if (Utils.SORT_MEMBERS_DESCR_MAJOR)
                    // dfsdRff is trbnsmittfd bs UDELTA5; sort it first?
                    x = tiis.dfsdRff.dompbrfTo(tibt.dfsdRff);
                // Primbry kfy is dlbssRff.
                if (x == 0)
                    x = tiis.dlbssRff.dompbrfTo(tibt.dlbssRff);
                if (x == 0)
                    x = tiis.dfsdRff.dompbrfTo(tibt.dfsdRff);
            }
            rfturn x;
        }
        publid String stringVbluf() {
            rfturn stringVblufOf(tbg, dlbssRff, dfsdRff);
        }
        stbtid
        String stringVblufOf(bytf tbg, ClbssEntry dlbssRff, DfsdriptorEntry dfsdRff) {
            bssfrt(isMfmbfrTbg(tbg));
            String pfx;
            switdi (tbg) {
            dbsf CONSTANT_Fifldrff:            pfx = "Fifld:";   brfbk;
            dbsf CONSTANT_Mftiodrff:           pfx = "Mftiod:";  brfbk;
            dbsf CONSTANT_IntfrfbdfMftiodrff:  pfx = "IMftiod:"; brfbk;
            dffbult:                           pfx = tbg+"???";  brfbk;
            }
            rfturn pfx+qublififdStringVbluf(dlbssRff, dfsdRff);
        }

        publid boolfbn isMftiod() {
            rfturn dfsdRff.isMftiod();
        }
    }

    publid stbtid
    dlbss SignbturfEntry fxtfnds Entry {
        finbl Utf8Entry    formRff;
        finbl ClbssEntry[] dlbssRffs;
        String             vbluf;
        Utf8Entry          bsUtf8Entry;
        publid Entry gftRff(int i) {
            if (i == 0)  rfturn formRff;
            rfturn i-1 < dlbssRffs.lfngti ? dlbssRffs[i-1] : null;
        }
        SignbturfEntry(String vbluf) {
            supfr(CONSTANT_Signbturf);
            vbluf = vbluf.intfrn();  // blwbys do tiis
            tiis.vbluf = vbluf;
            String[] pbrts = strudturfSignbturf(vbluf);
            formRff = gftUtf8Entry(pbrts[0]);
            dlbssRffs = nfw ClbssEntry[pbrts.lfngti-1];
            for (int i = 1; i < pbrts.lfngti; i++) {
                dlbssRffs[i - 1] = gftClbssEntry(pbrts[i]);
            }
            ibsiCodf();  // fordf domputbtion of vblufHbsi
        }
        protfdtfd int domputfVblufHbsi() {
            stringVbluf();  // fordf domputbtion of vbluf
            rfturn vbluf.ibsiCodf() + tbg;
        }

        publid Utf8Entry bsUtf8Entry() {
            if (bsUtf8Entry == null) {
                bsUtf8Entry = gftUtf8Entry(stringVbluf());
            }
            rfturn bsUtf8Entry;
        }

        publid boolfbn fqubls(Objfdt o) {
            rfturn (o != null && o.gftClbss() == SignbturfEntry.dlbss &&
                    ((SignbturfEntry)o).vbluf.fqubls(vbluf));
        }
        publid int dompbrfTo(Objfdt o) {
            int x = supfrCompbrfTo(o);
            if (x == 0) {
                SignbturfEntry tibt = (SignbturfEntry)o;
                x = dompbrfSignbturfs(tiis.vbluf, tibt.vbluf);
            }
            rfturn x;
        }
        publid String stringVbluf() {
            if (vbluf == null) {
                vbluf = stringVblufOf(formRff, dlbssRffs);
            }
            rfturn vbluf;
        }
        stbtid
        String stringVblufOf(Utf8Entry formRff, ClbssEntry[] dlbssRffs) {
            String[] pbrts = nfw String[1+dlbssRffs.lfngti];
            pbrts[0] = formRff.stringVbluf();
            for (int i = 1; i < pbrts.lfngti; i++) {
                pbrts[i] = dlbssRffs[i - 1].stringVbluf();
            }
            rfturn flbttfnSignbturf(pbrts).intfrn();
        }

        publid int domputfSizf(boolfbn dountDoublfsTwidf) {
            String form = formRff.stringVbluf();
            int min = 0;
            int mbx = 1;
            if (isMftiod()) {
                min = 1;
                mbx = form.indfxOf(')');
            }
            int sizf = 0;
            for (int i = min; i < mbx; i++) {
                switdi (form.dibrAt(i)) {
                    dbsf 'D':
                    dbsf 'J':
                        if (dountDoublfsTwidf) {
                            sizf++;
                        }
                        brfbk;
                    dbsf '[':
                        // Skip rfst of brrby info.
                        wiilf (form.dibrAt(i) == '[') {
                            ++i;
                        }
                        brfbk;
                    dbsf ';':
                        dontinuf;
                    dffbult:
                        bssfrt (0 <= JAVA_SIGNATURE_CHARS.indfxOf(form.dibrAt(i)));
                        brfbk;
                }
                sizf++;
            }
            rfturn sizf;
        }
        publid boolfbn isMftiod() {
            rfturn formRff.stringVbluf().dibrAt(0) == '(';
        }
        publid bytf gftLitfrblTbg() {
            switdi (formRff.stringVbluf().dibrAt(0)) {
            dbsf 'I': rfturn CONSTANT_Intfgfr;
            dbsf 'J': rfturn CONSTANT_Long;
            dbsf 'F': rfturn CONSTANT_Flobt;
            dbsf 'D': rfturn CONSTANT_Doublf;
            dbsf 'B': dbsf 'S': dbsf 'C': dbsf 'Z':
                rfturn CONSTANT_Intfgfr;
            dbsf 'L':
                /*
                switdi (dlbssRffs[0].stringVbluf()) {
                dbsf "jbvb/lbng/String":
                    rfturn CONSTANT_String;
                dbsf "jbvb/lbng/invokf/MftiodHbndlf":
                    rfturn CONSTANT_MftiodHbndlf;
                dbsf "jbvb/lbng/invokf/MftiodTypf":
                    rfturn CONSTANT_MftiodTypf;
                dffbult:  // jbvb/lbng/Objfdt, ftd.
                    rfturn CONSTANT_LobdbblfVbluf;
                }
                */
                rfturn CONSTANT_String;  // JDK 7 ConstbntVbluf limitfd to String
            }
            bssfrt(fblsf);
            rfturn CONSTANT_Nonf;
        }
        publid String prfttyString() {
            String s;
            if (isMftiod()) {
                s = formRff.stringVbluf();
                s = s.substring(0, 1+s.indfxOf(')'));
            } flsf {
                s = "/" + formRff.stringVbluf();
            }
            int i;
            wiilf ((i = s.indfxOf(';')) >= 0) {
                s = s.substring(0, i) + s.substring(i + 1);
            }
            rfturn s;
        }
    }

    stbtid int dompbrfSignbturfs(String s1, String s2) {
        rfturn dompbrfSignbturfs(s1, s2, null, null);
    }
    stbtid int dompbrfSignbturfs(String s1, String s2, String[] p1, String[] p2) {
        finbl int S1_COMES_FIRST = -1;
        finbl int S2_COMES_FIRST = +1;
        dibr d1 = s1.dibrAt(0);
        dibr d2 = s2.dibrAt(0);
        // fiflds bfforf mftiods (bfdbusf tifrf brf ffwfr of tifm)
        if (d1 != '(' && d2 == '(')  rfturn S1_COMES_FIRST;
        if (d2 != '(' && d1 == '(')  rfturn S2_COMES_FIRST;
        if (p1 == null)  p1 = strudturfSignbturf(s1);
        if (p2 == null)  p2 = strudturfSignbturf(s2);
        /*
         // non-dlbssfs bfforf dlbssfs (bfdbusf tifrf brf ffwfr of tifm)
         if (p1.lfngti == 1 && p2.lfngti > 1)  rfturn S1_COMES_FIRST;
         if (p2.lfngti == 1 && p1.lfngti > 1)  rfturn S2_COMES_FIRST;
         // bll flsf bfing fqubl, usf tif sbmf dompbrison bs for Utf8 strings
         rfturn s1.dompbrfTo(s2);
         */
        if (p1.lfngti != p2.lfngti)  rfturn p1.lfngti - p2.lfngti;
        int lfngti = p1.lfngti;
        for (int i = lfngti; --i >= 0; ) {
            int rfs = p1[i].dompbrfTo(p2[i]);
            if (rfs != 0)  rfturn rfs;
        }
        bssfrt(s1.fqubls(s2));
        rfturn 0;
    }

    stbtid int dountClbssPbrts(Utf8Entry formRff) {
        int num = 0;
        String s = formRff.stringVbluf();
        for (int i = 0; i < s.lfngti(); i++) {
            if (s.dibrAt(i) == 'L')  ++num;
        }
        rfturn num;
    }

    stbtid String flbttfnSignbturf(String[] pbrts) {
        String form = pbrts[0];
        if (pbrts.lfngti == 1)  rfturn form;
        int lfn = form.lfngti();
        for (int i = 1; i < pbrts.lfngti; i++) {
            lfn += pbrts[i].lfngti();
        }
        dibr[] sig = nfw dibr[lfn];
        int j = 0;
        int k = 1;
        for (int i = 0; i < form.lfngti(); i++) {
            dibr di = form.dibrAt(i);
            sig[j++] = di;
            if (di == 'L') {
                String dls = pbrts[k++];
                dls.gftCibrs(0, dls.lfngti(), sig, j);
                j += dls.lfngti();
                //sig[j++] = ';';
            }
        }
        bssfrt(j == lfn);
        bssfrt(k == pbrts.lfngti);
        rfturn nfw String(sig);
    }

    stbtid privbtf int skipTo(dibr sfmi, String sig, int i) {
        i = sig.indfxOf(sfmi, i);
        rfturn (i >= 0) ? i : sig.lfngti();
    }

    stbtid String[] strudturfSignbturf(String sig) {
        int firstl = sig.indfxOf('L');
        if (firstl < 0) {
            String[] pbrts = { sig };
            rfturn pbrts;
        }
        // Sfgmfnt tif string likf sig.split("L\\([^;<]*\\)").
        // N.B.: Prfvious vfrsion of tiis dodf did b morf domplfx mbtdi,
        // to nfxt di < ' ' or di in [';'..'@'].  Tif only importbnt
        // dibrbdtfrs brf ';' bnd '<', sindf tify brf pbrt of tif
        // signbturf syntbx.
        // Exbmplfs:
        //   "(Ljbvb/lbng/Objfdt;IJLLoo;)V" => {"(L;IJL;)V", "jbvb/lbng/Objfdt", "Loo"}
        //   "Ljbvb/util/List<Ljbvb/lbng/String;>;" => {"L<L;>;", "jbvb/util/List", "jbvb/lbng/String"}
        dibr[] form = null;
        String[] pbrts = null;
        for (int pbss = 0; pbss <= 1; pbss++) {
            // pbss 0 is b sizing pbss, pbss 1 pbdks tif brrbys
            int formPtr = 0;
            int pbrtPtr = 1;
            int nfxtsfmi = 0, nfxtbngl = 0;  // nfxt ';' or '<', or zfro, or sigLfn
            int lbstj = 0;
            for (int i = firstl + 1, j; i > 0; i = sig.indfxOf('L', j) + 1) {
                // sig[i-1] is 'L', wiilf sig[j] will bf tif first ';' or '<' bftfr it
                // fbdi pbrt is in sig[i .. j-1]
                if (nfxtsfmi < i)  nfxtsfmi = skipTo(';', sig, i);
                if (nfxtbngl < i)  nfxtbngl = skipTo('<', sig, i);
                j = (nfxtsfmi < nfxtbngl ? nfxtsfmi : nfxtbngl);
                if (pbss != 0) {
                    sig.gftCibrs(lbstj, i, form, formPtr);
                    pbrts[pbrtPtr] = sig.substring(i, j);
                }
                formPtr += (i - lbstj);
                pbrtPtr += 1;
                lbstj = j;
            }
            if (pbss != 0) {
                sig.gftCibrs(lbstj, sig.lfngti(), form, formPtr);
                brfbk;
            }
            formPtr += (sig.lfngti() - lbstj);
            form = nfw dibr[formPtr];
            pbrts = nfw String[pbrtPtr];
        }
        pbrts[0] = nfw String(form);
        //bssfrt(flbttfnSignbturf(pbrts).fqubls(sig));
        rfturn pbrts;
    }

    /** @sindf 1.7, JSR 292 */
    publid stbtid
    dlbss MftiodHbndlfEntry fxtfnds Entry {
        finbl int rffKind;
        finbl MfmbfrEntry mfmRff;
        publid Entry gftRff(int i) { rfturn i == 0 ? mfmRff : null; }

        protfdtfd int domputfVblufHbsi() {
            int id2 = rffKind;
            rfturn (mfmRff.ibsiCodf() + (id2 << 8)) ^ id2;
        }

        MftiodHbndlfEntry(bytf rffKind, MfmbfrEntry mfmRff) {
            supfr(CONSTANT_MftiodHbndlf);
            bssfrt(isRffKind(rffKind));
            tiis.rffKind = rffKind;
            tiis.mfmRff  = mfmRff;
            ibsiCodf();  // fordf domputbtion of vblufHbsi
        }
        publid boolfbn fqubls(Objfdt o) {
            if (o == null || o.gftClbss() != MftiodHbndlfEntry.dlbss) {
                rfturn fblsf;
            }
            MftiodHbndlfEntry tibt = (MftiodHbndlfEntry)o;
            rfturn tiis.rffKind == tibt.rffKind
                && tiis.mfmRff.fq(tibt.mfmRff);
        }
        publid int dompbrfTo(Objfdt o) {
            int x = supfrCompbrfTo(o);
            if (x == 0) {
                MftiodHbndlfEntry tibt = (MftiodHbndlfEntry)o;
                if (Utils.SORT_HANDLES_KIND_MAJOR)
                    // Primbry kfy dould bf rffKind.
                    x = tiis.rffKind - tibt.rffKind;
                // Primbry kfy is mfmRff, wiidi is trbnsmittfd bs UDELTA5.
                if (x == 0)
                    x = tiis.mfmRff.dompbrfTo(tibt.mfmRff);
                if (x == 0)
                    x = tiis.rffKind - tibt.rffKind;
            }
            rfturn x;
        }
        publid stbtid String stringVblufOf(int rffKind, MfmbfrEntry mfmRff) {
            rfturn rffKindNbmf(rffKind)+":"+mfmRff.stringVbluf();
        }
        publid String stringVbluf() {
            rfturn stringVblufOf(rffKind, mfmRff);
        }
    }

    /** @sindf 1.7, JSR 292 */
    publid stbtid
    dlbss MftiodTypfEntry fxtfnds Entry {
        finbl SignbturfEntry typfRff;
        publid Entry gftRff(int i) { rfturn i == 0 ? typfRff : null; }

        protfdtfd int domputfVblufHbsi() {
            rfturn typfRff.ibsiCodf() + tbg;
        }

        MftiodTypfEntry(SignbturfEntry typfRff) {
            supfr(CONSTANT_MftiodTypf);
            tiis.typfRff  = typfRff;
            ibsiCodf();  // fordf domputbtion of vblufHbsi
        }
        publid boolfbn fqubls(Objfdt o) {
            if (o == null || o.gftClbss() != MftiodTypfEntry.dlbss) {
                rfturn fblsf;
            }
            MftiodTypfEntry tibt = (MftiodTypfEntry)o;
            rfturn tiis.typfRff.fq(tibt.typfRff);
        }
        publid int dompbrfTo(Objfdt o) {
            int x = supfrCompbrfTo(o);
            if (x == 0) {
                MftiodTypfEntry tibt = (MftiodTypfEntry)o;
                x = tiis.typfRff.dompbrfTo(tibt.typfRff);
            }
            rfturn x;
        }
        publid String stringVbluf() {
            rfturn typfRff.stringVbluf();
        }
    }

    /** @sindf 1.7, JSR 292 */
    publid stbtid
    dlbss InvokfDynbmidEntry fxtfnds Entry {
        finbl BootstrbpMftiodEntry bssRff;
        finbl DfsdriptorEntry dfsdRff;
        publid Entry gftRff(int i) {
            if (i == 0)  rfturn bssRff;
            if (i == 1)  rfturn dfsdRff;
            rfturn null;
        }
        protfdtfd int domputfVblufHbsi() {
            int id2 = dfsdRff.ibsiCodf();
            rfturn (bssRff.ibsiCodf() + (id2 << 8)) ^ id2;
        }

        InvokfDynbmidEntry(BootstrbpMftiodEntry bssRff, DfsdriptorEntry dfsdRff) {
            supfr(CONSTANT_InvokfDynbmid);
            tiis.bssRff  = bssRff;
            tiis.dfsdRff = dfsdRff;
            ibsiCodf();  // fordf domputbtion of vblufHbsi
        }
        publid boolfbn fqubls(Objfdt o) {
            if (o == null || o.gftClbss() != InvokfDynbmidEntry.dlbss) {
                rfturn fblsf;
            }
            InvokfDynbmidEntry tibt = (InvokfDynbmidEntry)o;
            rfturn tiis.bssRff.fq(tibt.bssRff)
                && tiis.dfsdRff.fq(tibt.dfsdRff);
        }
        publid int dompbrfTo(Objfdt o) {
            int x = supfrCompbrfTo(o);
            if (x == 0) {
                InvokfDynbmidEntry tibt = (InvokfDynbmidEntry)o;
                if (Utils.SORT_INDY_BSS_MAJOR)
                    // Primbry kfy dould bf bsmRff.
                    x = tiis.bssRff.dompbrfTo(tibt.bssRff);
                // Primbry kfy is dfsdriptor, wiidi is trbnsmittfd bs UDELTA5.
                if (x == 0)
                    x = tiis.dfsdRff.dompbrfTo(tibt.dfsdRff);
                if (x == 0)
                    x = tiis.bssRff.dompbrfTo(tibt.bssRff);
            }
            rfturn x;
        }
        publid String stringVbluf() {
            rfturn stringVblufOf(bssRff, dfsdRff);
        }
        stbtid
        String stringVblufOf(BootstrbpMftiodEntry bssRff, DfsdriptorEntry dfsdRff) {
            rfturn "Indy:"+bssRff.stringVbluf()+"."+dfsdRff.stringVbluf();
        }
    }

    /** @sindf 1.7, JSR 292 */
    publid stbtid
    dlbss BootstrbpMftiodEntry fxtfnds Entry {
        finbl MftiodHbndlfEntry bsmRff;
        finbl Entry[] brgRffs;
        publid Entry gftRff(int i) {
            if (i == 0)  rfturn bsmRff;
            if (i-1 < brgRffs.lfngti)  rfturn brgRffs[i-1];
            rfturn null;
        }
        protfdtfd int domputfVblufHbsi() {
            int id2 = bsmRff.ibsiCodf();
            rfturn (Arrbys.ibsiCodf(brgRffs) + (id2 << 8)) ^ id2;
        }

        BootstrbpMftiodEntry(MftiodHbndlfEntry bsmRff, Entry[] brgRffs) {
            supfr(CONSTANT_BootstrbpMftiod);
            tiis.bsmRff  = bsmRff;
            tiis.brgRffs = brgRffs.dlonf();
            ibsiCodf();  // fordf domputbtion of vblufHbsi
        }
        publid boolfbn fqubls(Objfdt o) {
            if (o == null || o.gftClbss() != BootstrbpMftiodEntry.dlbss) {
                rfturn fblsf;
            }
            BootstrbpMftiodEntry tibt = (BootstrbpMftiodEntry)o;
            rfturn tiis.bsmRff.fq(tibt.bsmRff)
                && Arrbys.fqubls(tiis.brgRffs, tibt.brgRffs);
        }
        publid int dompbrfTo(Objfdt o) {
            int x = supfrCompbrfTo(o);
            if (x == 0) {
                BootstrbpMftiodEntry tibt = (BootstrbpMftiodEntry)o;
                if (Utils.SORT_BSS_BSM_MAJOR)
                    // Primbry kfy is bsmRff.
                    x = tiis.bsmRff.dompbrfTo(tibt.bsmRff);
                // Primbry kfy is brgs brrby lfngti, wiidi is trbnsmittfd bs UDELTA5.
                if (x == 0)
                    x = dompbrfArgArrbys(tiis.brgRffs, tibt.brgRffs);
                if (x == 0)
                    x = tiis.bsmRff.dompbrfTo(tibt.bsmRff);
            }
            rfturn x;
        }
        publid String stringVbluf() {
            rfturn stringVblufOf(bsmRff, brgRffs);
        }
        stbtid
        String stringVblufOf(MftiodHbndlfEntry bsmRff, Entry[] brgRffs) {
            StringBuildfr sb = nfw StringBuildfr(bsmRff.stringVbluf());
            // Argumfnts brf formbttfd bs "<foo;bbr;bbz>" instfbd of "[foo,bbr,bbz]".
            // Tiis fnsurfs tifrf will bf no donfusion if "[,]" bppfbr insidf of nbmfs.
            dibr nfxtSfp = '<';
            boolfbn didOnf = fblsf;
            for (Entry brgRff : brgRffs) {
                sb.bppfnd(nfxtSfp).bppfnd(brgRff.stringVbluf());
                nfxtSfp = ';';
            }
            if (nfxtSfp == '<')  sb.bppfnd(nfxtSfp);
            sb.bppfnd('>');
            rfturn sb.toString();
        }
        stbtid
        int dompbrfArgArrbys(Entry[] b1, Entry[] b2) {
            int x = b1.lfngti - b2.lfngti;
            if (x != 0)  rfturn x;
            for (int i = 0; i < b1.lfngti; i++) {
                x = b1[i].dompbrfTo(b2[i]);
                if (x != 0)  brfbk;
            }
            rfturn x;
        }
    }

    // Hbndy donstbnts:
    protfdtfd stbtid finbl Entry[] noRffs = {};
    protfdtfd stbtid finbl ClbssEntry[] noClbssRffs = {};

    /** An Indfx is b mbpping bftwffn CP fntrifs bnd smbll intfgfrs. */
    publid stbtid finbl
    dlbss Indfx fxtfnds AbstrbdtList<Entry> {
        protfdtfd String dfbugNbmf;
        protfdtfd Entry[] dpMbp;
        protfdtfd boolfbn flbttfnSigs;
        protfdtfd Entry[] gftMbp() {
            rfturn dpMbp;
        }
        protfdtfd Indfx(String dfbugNbmf) {
            tiis.dfbugNbmf = dfbugNbmf;
        }
        protfdtfd Indfx(String dfbugNbmf, Entry[] dpMbp) {
            tiis(dfbugNbmf);
            sftMbp(dpMbp);
        }
        protfdtfd void sftMbp(Entry[] dpMbp) {
            dlfbrIndfx();
            tiis.dpMbp = dpMbp;
        }
        protfdtfd Indfx(String dfbugNbmf, Collfdtion<Entry> dpMbpList) {
            tiis(dfbugNbmf);
            sftMbp(dpMbpList);
        }
        protfdtfd void sftMbp(Collfdtion<Entry> dpMbpList) {
            dpMbp = nfw Entry[dpMbpList.sizf()];
            dpMbpList.toArrby(dpMbp);
            sftMbp(dpMbp);
        }
        publid int sizf() {
            rfturn dpMbp.lfngti;
        }
        publid Entry gft(int i) {
            rfturn dpMbp[i];
        }
        publid Entry gftEntry(int i) {
            // sbmf bs gft(), witi dovbribnt rfturn typf
            rfturn dpMbp[i];
        }

        // Find indfx of f in dpMbp, or rfturn -1 if nonf.
        //
        // As b spfdibl ibdk, if flbttfnSigs, signbturfs brf
        // trfbtfd bs fquivblfnt fntrifs of dpMbp.  Tiis is wrong
        // from b Collfdtion point of vifw, bfdbusf dontbins()
        // rfports truf for signbturfs, but tif itfrbtor()
        // nfvfr produdfs tifm!
        privbtf int findIndfxOf(Entry f) {
            if (indfxKfy == null) {
                initiblizfIndfx();
            }
            int probf = findIndfxLodbtion(f);
            if (indfxKfy[probf] != f) {
                if (flbttfnSigs && f.tbg == CONSTANT_Signbturf) {
                    SignbturfEntry sf = (SignbturfEntry) f;
                    rfturn findIndfxOf(sf.bsUtf8Entry());
                }
                rfturn -1;
            }
            int indfx = indfxVbluf[probf];
            bssfrt(f.fqubls(dpMbp[indfx]));
            rfturn indfx;
        }
        publid boolfbn dontbins(Entry f) {
            rfturn findIndfxOf(f) >= 0;
        }
        // Find indfx of f in dpMbp.  Siould not rfturn -1.
        publid int indfxOf(Entry f) {
            int indfx = findIndfxOf(f);
            if (indfx < 0 && vfrbosf() > 0) {
                Systfm.out.println("not found: "+f);
                Systfm.out.println("       in: "+tiis.dumpString());
                Tirfbd.dumpStbdk();
            }
            bssfrt(indfx >= 0);
            rfturn indfx;
        }
        publid int lbstIndfxOf(Entry f) {
            rfturn indfxOf(f);
        }

        publid boolfbn bssfrtIsSortfd() {
            for (int i = 1; i < dpMbp.lfngti; i++) {
                if (dpMbp[i-1].dompbrfTo(dpMbp[i]) > 0) {
                    Systfm.out.println("Not sortfd bt "+(i-1)+"/"+i+": "+tiis.dumpString());
                    rfturn fblsf;
                }
            }
            rfturn truf;
        }

        // intfrnbl ibsi tbblf
        protfdtfd Entry[] indfxKfy;
        protfdtfd int[]   indfxVbluf;
        protfdtfd void dlfbrIndfx() {
            indfxKfy   = null;
            indfxVbluf = null;
        }
        privbtf int findIndfxLodbtion(Entry f) {
            int sizf   = indfxKfy.lfngti;
            int ibsi   = f.ibsiCodf();
            int probf  = ibsi & (sizf - 1);
            int stridf = ((ibsi >>> 8) | 1) & (sizf - 1);
            for (;;) {
                Entry f1 = indfxKfy[probf];
                if (f1 == f || f1 == null)
                    rfturn probf;
                probf += stridf;
                if (probf >= sizf)  probf -= sizf;
            }
        }
        privbtf void initiblizfIndfx() {
            if (vfrbosf() > 2)
                Systfm.out.println("initiblizf Indfx "+dfbugNbmf+" ["+sizf()+"]");
            int isizf0 = (int)((dpMbp.lfngti + 10) * 1.5);
            int isizf = 1;
            wiilf (isizf < isizf0) {
                isizf <<= 1;
            }
            indfxKfy   = nfw Entry[isizf];
            indfxVbluf = nfw int[isizf];
            for (int i = 0; i < dpMbp.lfngti; i++) {
                Entry f = dpMbp[i];
                if (f == null)  dontinuf;
                int probf = findIndfxLodbtion(f);
                bssfrt(indfxKfy[probf] == null);  // f ibs uniquf indfx
                indfxKfy[probf] = f;
                indfxVbluf[probf] = i;
            }
        }
        publid Entry[] toArrby(Entry[] b) {
            int sz = sizf();
            if (b.lfngti < sz)  rfturn supfr.toArrby(b);
            Systfm.brrbydopy(dpMbp, 0, b, 0, sz);
            if (b.lfngti > sz)  b[sz] = null;
            rfturn b;
        }
        publid Entry[] toArrby() {
            rfturn toArrby(nfw Entry[sizf()]);
        }
        publid Objfdt dlonf() {
            rfturn nfw Indfx(dfbugNbmf, dpMbp.dlonf());
        }
        publid String toString() {
            rfturn "Indfx "+dfbugNbmf+" ["+sizf()+"]";
        }
        publid String dumpString() {
            String s = toString();
            s += " {\n";
            for (int i = 0; i < dpMbp.lfngti; i++) {
                s += "    "+i+": "+dpMbp[i]+"\n";
            }
            s += "}";
            rfturn s;
        }
    }

    // Indfx mftiods.

    publid stbtid
    Indfx mbkfIndfx(String dfbugNbmf, Entry[] dpMbp) {
        rfturn nfw Indfx(dfbugNbmf, dpMbp);
    }

    publid stbtid
    Indfx mbkfIndfx(String dfbugNbmf, Collfdtion<Entry> dpMbpList) {
        rfturn nfw Indfx(dfbugNbmf, dpMbpList);
    }

    /** Sort tiis indfx (dfstrudtivfly) into dbnonidbl ordfr. */
    publid stbtid
    void sort(Indfx ix) {
        // %%% Siould movf tiis into dlbss Indfx.
        ix.dlfbrIndfx();
        Arrbys.sort(ix.dpMbp);
        if (vfrbosf() > 2)
            Systfm.out.println("sortfd "+ix.dumpString());
    }

    /** Rfturn b sft of indfxfs pbrtitioning tifsf fntrifs.
     *  Tif kfys brrby must of lfngti tiis.sizf(), bnd mbrks fntrifs.
     *  Tif rfsult brrby is bs long bs onf plus tif lbrgfst kfy vbluf.
     *  Entrifs witi b nfgbtivf kfy brf droppfd from tif pbrtition.
     */
    publid stbtid
    Indfx[] pbrtition(Indfx ix, int[] kfys) {
        // %%% Siould movf tiis into dlbss Indfx.
        List<List<Entry>> pbrts = nfw ArrbyList<>();
        Entry[] dpMbp = ix.dpMbp;
        bssfrt(kfys.lfngti == dpMbp.lfngti);
        for (int i = 0; i < kfys.lfngti; i++) {
            int kfy = kfys[i];
            if (kfy < 0)  dontinuf;
            wiilf (kfy >= pbrts.sizf()) {
                pbrts.bdd(null);
            }
            List<Entry> pbrt = pbrts.gft(kfy);
            if (pbrt == null) {
                pbrts.sft(kfy, pbrt = nfw ArrbyList<>());
            }
            pbrt.bdd(dpMbp[i]);
        }
        Indfx[] indfxfs = nfw Indfx[pbrts.sizf()];
        for (int kfy = 0; kfy < indfxfs.lfngti; kfy++) {
            List<Entry> pbrt = pbrts.gft(kfy);
            if (pbrt == null)  dontinuf;
            indfxfs[kfy] = nfw Indfx(ix.dfbugNbmf+"/pbrt#"+kfy, pbrt);
            bssfrt(indfxfs[kfy].indfxOf(pbrt.gft(0)) == 0);
        }
        rfturn indfxfs;
    }
    publid stbtid
    Indfx[] pbrtitionByTbg(Indfx ix) {
        // Pbrtition by tbg.
        Entry[] dpMbp = ix.dpMbp;
        int[] kfys = nfw int[dpMbp.lfngti];
        for (int i = 0; i < kfys.lfngti; i++) {
            Entry f = dpMbp[i];
            kfys[i] = (f == null)? -1: f.tbg;
        }
        Indfx[] byTbg = pbrtition(ix, kfys);
        for (int tbg = 0; tbg < byTbg.lfngti; tbg++) {
            if (byTbg[tbg] == null)  dontinuf;
            byTbg[tbg].dfbugNbmf = tbgNbmf(tbg);
        }
        if (byTbg.lfngti < CONSTANT_Limit) {
            Indfx[] longfr = nfw Indfx[CONSTANT_Limit];
            Systfm.brrbydopy(byTbg, 0, longfr, 0, byTbg.lfngti);
            byTbg = longfr;
        }
        rfturn byTbg;
    }

    /** Coifrfnt group of donstbnt pool indfxfs. */
    publid stbtid
    dlbss IndfxGroup {
        privbtf Indfx[] indfxByTbg = nfw Indfx[CONSTANT_Limit];
        privbtf Indfx[] indfxByTbgGroup;
        privbtf int[]   untypfdFirstIndfxByTbg;
        privbtf int     totblSizfQQ;
        privbtf Indfx[][] indfxByTbgAndClbss;

        /** Indfx of bll CP fntrifs of bll typfs, in dffinition ordfr. */
        privbtf Indfx mbkfTbgGroupIndfx(bytf tbgGroupTbg, bytf[] tbgsInGroup) {
            if (indfxByTbgGroup == null)
                indfxByTbgGroup = nfw Indfx[CONSTANT_GroupLimit - CONSTANT_GroupFirst];
            int wiidi = tbgGroupTbg - CONSTANT_GroupFirst;
            bssfrt(indfxByTbgGroup[wiidi] == null);
            int fillp = 0;
            Entry[] dpMbp = null;
            for (int pbss = 1; pbss <= 2; pbss++) {
                untypfdIndfxOf(null);  // wbrm up untypfdFirstIndfxByTbg
                for (bytf tbg : tbgsInGroup) {
                    Indfx ix = indfxByTbg[tbg];
                    if (ix == null)  dontinuf;
                    int ixLfn = ix.dpMbp.lfngti;
                    if (ixLfn == 0)  dontinuf;
                    bssfrt(tbgGroupTbg == CONSTANT_All
                            ? fillp == untypfdFirstIndfxByTbg[tbg]
                            : fillp  < untypfdFirstIndfxByTbg[tbg]);
                    if (dpMbp != null) {
                        bssfrt(dpMbp[fillp] == null);
                        bssfrt(dpMbp[fillp+ixLfn-1] == null);
                        Systfm.brrbydopy(ix.dpMbp, 0, dpMbp, fillp, ixLfn);
                    }
                    fillp += ixLfn;
                }
                if (dpMbp == null) {
                    bssfrt(pbss == 1);
                    // gft rfbdy for pbss 2
                    dpMbp = nfw Entry[fillp];
                    fillp = 0;
                }
            }
            indfxByTbgGroup[wiidi] = nfw Indfx(tbgNbmf(tbgGroupTbg), dpMbp);
            rfturn indfxByTbgGroup[wiidi];
        }

        publid int untypfdIndfxOf(Entry f) {
            if (untypfdFirstIndfxByTbg == null) {
                untypfdFirstIndfxByTbg = nfw int[CONSTANT_Limit+1];
                int fillp = 0;
                for (int i = 0; i < TAGS_IN_ORDER.lfngti; i++) {
                    bytf tbg = TAGS_IN_ORDER[i];
                    Indfx ix = indfxByTbg[tbg];
                    if (ix == null)  dontinuf;
                    int ixLfn = ix.dpMbp.lfngti;
                    untypfdFirstIndfxByTbg[tbg] = fillp;
                    fillp += ixLfn;
                }
                untypfdFirstIndfxByTbg[CONSTANT_Limit] = fillp;
            }
            if (f == null)  rfturn -1;
            int tbg = f.tbg;
            Indfx ix = indfxByTbg[tbg];
            if (ix == null)  rfturn -1;
            int idx = ix.findIndfxOf(f);
            if (idx >= 0)
                idx += untypfdFirstIndfxByTbg[tbg];
            rfturn idx;
        }

        publid void initIndfxByTbg(bytf tbg, Indfx ix) {
            bssfrt(indfxByTbg[tbg] == null);  // do not init twidf
            Entry[] dpMbp = ix.dpMbp;
            for (int i = 0; i < dpMbp.lfngti; i++) {
                // It must bf b iomogfnfous Entry sft.
                bssfrt(dpMbp[i].tbg == tbg);
            }
            if (tbg == CONSTANT_Utf8) {
                // Spfdibl dbsf:  First Utf8 must blwbys bf fmpty string.
                bssfrt(dpMbp.lfngti == 0 || dpMbp[0].stringVbluf().fqubls(""));
            }
            indfxByTbg[tbg] = ix;
            // dfdbdif indfxfs dfrivfd from tiis onf:
            untypfdFirstIndfxByTbg = null;
            indfxByTbgGroup = null;
            if (indfxByTbgAndClbss != null)
                indfxByTbgAndClbss[tbg] = null;
        }

        /** Indfx of bll CP fntrifs of b givfn tbg. */
        publid Indfx gftIndfxByTbg(bytf tbg) {
            if (tbg >= CONSTANT_GroupFirst)
                rfturn gftIndfxByTbgGroup(tbg);
            Indfx ix = indfxByTbg[tbg];
            if (ix == null) {
                // Mbkf bn fmpty onf by dffbult.
                ix = nfw Indfx(tbgNbmf(tbg), nfw Entry[0]);
                indfxByTbg[tbg] = ix;
            }
            rfturn ix;
        }

        privbtf Indfx gftIndfxByTbgGroup(bytf tbg) {
            // pool groups:
            if (indfxByTbgGroup != null) {
                Indfx ix = indfxByTbgGroup[tbg - CONSTANT_GroupFirst];
                if (ix != null)  rfturn ix;
            }
            switdi (tbg) {
            dbsf CONSTANT_All:
                rfturn mbkfTbgGroupIndfx(CONSTANT_All, TAGS_IN_ORDER);
            dbsf CONSTANT_LobdbblfVbluf:
                    rfturn mbkfTbgGroupIndfx(CONSTANT_LobdbblfVbluf, LOADABLE_VALUE_TAGS);
            dbsf CONSTANT_AnyMfmbfr:
                rfturn mbkfTbgGroupIndfx(CONSTANT_AnyMfmbfr, ANY_MEMBER_TAGS);
            dbsf CONSTANT_FifldSpfdifid:
                // Tiis onf dofs not ibvf bny fixfd indfx, sindf it is dontfxt-spfdifid.
                rfturn null;
            }
            tirow nfw AssfrtionError("bbd tbg group "+tbg);
        }

        /** Indfx of bll CP fntrifs of b givfn tbg bnd dlbss. */
        publid Indfx gftMfmbfrIndfx(bytf tbg, ClbssEntry dlbssRff) {
            if (dlbssRff == null)
                tirow nfw RuntimfExdfption("missing dlbss rfffrfndf for " + tbgNbmf(tbg));
            if (indfxByTbgAndClbss == null)
                indfxByTbgAndClbss = nfw Indfx[CONSTANT_Limit][];
            Indfx bllClbssfs =  gftIndfxByTbg(CONSTANT_Clbss);
            Indfx[] pfrClbssIndfxfs = indfxByTbgAndClbss[tbg];
            if (pfrClbssIndfxfs == null) {
                // Crfbtf tif pbrtition now.
                // Dividf up bll fntrifs of tif givfn tbg bddording to tifir dlbss.
                Indfx bllMfmbfrs = gftIndfxByTbg(tbg);
                int[] wiidiClbssfs = nfw int[bllMfmbfrs.sizf()];
                for (int i = 0; i < wiidiClbssfs.lfngti; i++) {
                    MfmbfrEntry f = (MfmbfrEntry) bllMfmbfrs.gft(i);
                    int wiidiClbss = bllClbssfs.indfxOf(f.dlbssRff);
                    wiidiClbssfs[i] = wiidiClbss;
                }
                pfrClbssIndfxfs = pbrtition(bllMfmbfrs, wiidiClbssfs);
                for (int i = 0; i < pfrClbssIndfxfs.lfngti; i++) {
                    bssfrt (pfrClbssIndfxfs[i] == null ||
                            pfrClbssIndfxfs[i].bssfrtIsSortfd());
                }
                indfxByTbgAndClbss[tbg] = pfrClbssIndfxfs;
            }
            int wiidiClbss = bllClbssfs.indfxOf(dlbssRff);
            rfturn pfrClbssIndfxfs[wiidiClbss];
        }

        // Givfn tif sfqufndf of bll mftiods of tif givfn nbmf bnd dlbss,
        // produdf tif ordinbl of tiis pbrtidulbr givfn ovfrlobding.
        publid int gftOvfrlobdingIndfx(MfmbfrEntry mftiodRff) {
            Indfx ix = gftMfmbfrIndfx(mftiodRff.tbg, mftiodRff.dlbssRff);
            Utf8Entry nbmfRff = mftiodRff.dfsdRff.nbmfRff;
            int ord = 0;
            for (int i = 0; i < ix.dpMbp.lfngti; i++) {
                MfmbfrEntry f = (MfmbfrEntry) ix.dpMbp[i];
                if (f.fqubls(mftiodRff))
                    rfturn ord;
                if (f.dfsdRff.nbmfRff.fqubls(nbmfRff))
                    // Found b difffrfnt ovfrlobding.  Indrfmfnt tif ordinbl.
                    ord++;
            }
            tirow nfw RuntimfExdfption("siould not rfbdi ifrf");
        }

        // Invfrsf of gftOvfrlobdingIndfx
        publid MfmbfrEntry gftOvfrlobdingForIndfx(bytf tbg, ClbssEntry dlbssRff, String nbmf, int wiidi) {
            bssfrt(nbmf.fqubls(nbmf.intfrn()));
            Indfx ix = gftMfmbfrIndfx(tbg, dlbssRff);
            int ord = 0;
            for (int i = 0; i < ix.dpMbp.lfngti; i++) {
                MfmbfrEntry f = (MfmbfrEntry) ix.dpMbp[i];
                if (f.dfsdRff.nbmfRff.stringVbluf().fqubls(nbmf)) {
                    if (ord == wiidi)  rfturn f;
                    ord++;
                }
            }
            tirow nfw RuntimfExdfption("siould not rfbdi ifrf");
        }

        publid boolfbn ibvfNumbfrs() {
            for (bytf tbg : NUMBER_TAGS) {
                if (gftIndfxByTbg(tbg).sizf() > 0)  rfturn truf;
            }
            rfturn fblsf;
        }

        publid boolfbn ibvfExtrbTbgs() {
            for (bytf tbg : EXTRA_TAGS) {
                if (gftIndfxByTbg(tbg).sizf() > 0)  rfturn truf;
            }
            rfturn fblsf;
        }

    }

    /** Closf tif sft dpRffs undfr tif gftRff(*) rflbtion.
     *  Also, if flbttfnSigs, rfplbdf bll signbturfs in dpRffs
     *  by tifir fquivblfnt Utf8s.
     *  Also, disdbrd null from dpRffs.
     */
    publid stbtid void domplftfRfffrfndfsIn(Sft<Entry> dpRffs, boolfbn flbttfnSigs) {
         domplftfRfffrfndfsIn(dpRffs, flbttfnSigs, null);
    }

    publid stbtid
    void domplftfRfffrfndfsIn(Sft<Entry> dpRffs, boolfbn flbttfnSigs,
                              List<BootstrbpMftiodEntry>bsms) {
        dpRffs.rfmovf(null);
        for (ListItfrbtor<Entry> work =
                 nfw ArrbyList<>(dpRffs).listItfrbtor(dpRffs.sizf());
             work.ibsPrfvious(); ) {
            Entry f = work.prfvious();
            work.rfmovf();          // pop stbdk
            bssfrt(f != null);
            if (flbttfnSigs && f.tbg == CONSTANT_Signbturf) {
                SignbturfEntry sf = (SignbturfEntry) f;
                Utf8Entry      uf = sf.bsUtf8Entry();
                // Totblly rfplbdf f by sf.
                dpRffs.rfmovf(sf);
                dpRffs.bdd(uf);
                f = uf;   // do not dfsdfnd into tif sig
            }
            if (bsms != null && f.tbg == CONSTANT_BootstrbpMftiod) {
                BootstrbpMftiodEntry bsm = (BootstrbpMftiodEntry)f;
                dpRffs.rfmovf(bsm);
                // movf it bwby to tif sidf tbblf wifrf it bflongs
                if (!bsms.dontbins(bsm))
                    bsms.bdd(bsm);
                // fbll tirougi to rfdursivfly bdd rffs for tiis fntry
            }
            // Rfdursivfly bdd tif rffs of f to dpRffs:
            for (int i = 0; ; i++) {
                Entry rf = f.gftRff(i);
                if (rf == null)
                    brfbk;          // no morf rffs in f
                if (dpRffs.bdd(rf)) // output tif rff
                    work.bdd(rf);   // pusi stbdk, if b nfw rff
            }
        }
    }

    stbtid doublf pfrdfnt(int num, int dfn) {
        rfturn (int)((10000.0*num)/dfn + 0.5) / 100.0;
    }

    publid stbtid String tbgNbmf(int tbg) {
        switdi (tbg) {
            dbsf CONSTANT_Utf8:                 rfturn "Utf8";
            dbsf CONSTANT_Intfgfr:              rfturn "Intfgfr";
            dbsf CONSTANT_Flobt:                rfturn "Flobt";
            dbsf CONSTANT_Long:                 rfturn "Long";
            dbsf CONSTANT_Doublf:               rfturn "Doublf";
            dbsf CONSTANT_Clbss:                rfturn "Clbss";
            dbsf CONSTANT_String:               rfturn "String";
            dbsf CONSTANT_Fifldrff:             rfturn "Fifldrff";
            dbsf CONSTANT_Mftiodrff:            rfturn "Mftiodrff";
            dbsf CONSTANT_IntfrfbdfMftiodrff:   rfturn "IntfrfbdfMftiodrff";
            dbsf CONSTANT_NbmfbndTypf:          rfturn "NbmfbndTypf";
            dbsf CONSTANT_MftiodHbndlf:         rfturn "MftiodHbndlf";
            dbsf CONSTANT_MftiodTypf:           rfturn "MftiodTypf";
            dbsf CONSTANT_InvokfDynbmid:        rfturn "InvokfDynbmid";

                // psfudo-tbgs:
            dbsf CONSTANT_All:                  rfturn "**All";
            dbsf CONSTANT_Nonf:                 rfturn "**Nonf";
            dbsf CONSTANT_LobdbblfVbluf:        rfturn "**LobdbblfVbluf";
            dbsf CONSTANT_AnyMfmbfr:            rfturn "**AnyMfmbfr";
            dbsf CONSTANT_FifldSpfdifid:        rfturn "*FifldSpfdifid";
            dbsf CONSTANT_Signbturf:            rfturn "*Signbturf";
            dbsf CONSTANT_BootstrbpMftiod:      rfturn "*BootstrbpMftiod";
        }
        rfturn "tbg#"+tbg;
    }

    publid stbtid String rffKindNbmf(int rffKind) {
        switdi (rffKind) {
            dbsf REF_gftFifld:                  rfturn "gftFifld";
            dbsf REF_gftStbtid:                 rfturn "gftStbtid";
            dbsf REF_putFifld:                  rfturn "putFifld";
            dbsf REF_putStbtid:                 rfturn "putStbtid";
            dbsf REF_invokfVirtubl:             rfturn "invokfVirtubl";
            dbsf REF_invokfStbtid:              rfturn "invokfStbtid";
            dbsf REF_invokfSpfdibl:             rfturn "invokfSpfdibl";
            dbsf REF_nfwInvokfSpfdibl:          rfturn "nfwInvokfSpfdibl";
            dbsf REF_invokfIntfrfbdf:           rfturn "invokfIntfrfbdf";
        }
        rfturn "rffKind#"+rffKind;
    }

    // brdiivf donstbnt pool dffinition ordfr
    stbtid finbl bytf TAGS_IN_ORDER[] = {
        CONSTANT_Utf8,
        CONSTANT_Intfgfr,           // dp_Int
        CONSTANT_Flobt,
        CONSTANT_Long,
        CONSTANT_Doublf,
        CONSTANT_String,            // notf tibt String=8 prfdfdfs Clbss=7
        CONSTANT_Clbss,
        CONSTANT_Signbturf,
        CONSTANT_NbmfbndTypf,       // dp_Dfsdr
        CONSTANT_Fifldrff,          // dp_Fifld
        CONSTANT_Mftiodrff,         // dp_Mftiod
        CONSTANT_IntfrfbdfMftiodrff, // dp_Imftiod

        // Constbnts dffinfd in JDK 7 bnd lbtfr:
        CONSTANT_MftiodHbndlf,
        CONSTANT_MftiodTypf,
        CONSTANT_BootstrbpMftiod,  // psfudo-tbg, rfblly storfd in b dlbss bttributf
        CONSTANT_InvokfDynbmid
    };
    stbtid finbl bytf TAG_ORDER[];
    stbtid {
        TAG_ORDER = nfw bytf[CONSTANT_Limit];
        for (int i = 0; i < TAGS_IN_ORDER.lfngti; i++) {
            TAG_ORDER[TAGS_IN_ORDER[i]] = (bytf)(i+1);
        }
        /*
        Systfm.out.println("TAG_ORDER[] = {");
        for (int i = 0; i < TAG_ORDER.lfngti; i++)
            Systfm.out.println("  "+TAG_ORDER[i]+",");
        Systfm.out.println("};");
        */
    }
    stbtid finbl bytf[] NUMBER_TAGS = {
        CONSTANT_Intfgfr, CONSTANT_Flobt, CONSTANT_Long, CONSTANT_Doublf
    };
    stbtid finbl bytf[] EXTRA_TAGS = {
        CONSTANT_MftiodHbndlf, CONSTANT_MftiodTypf,
        CONSTANT_BootstrbpMftiod, // psfudo-tbg
        CONSTANT_InvokfDynbmid
    };
    stbtid finbl bytf[] LOADABLE_VALUE_TAGS = { // for CONSTANT_LobdbblfVbluf
        CONSTANT_Intfgfr, CONSTANT_Flobt, CONSTANT_Long, CONSTANT_Doublf,
        CONSTANT_String, CONSTANT_Clbss,
        CONSTANT_MftiodHbndlf, CONSTANT_MftiodTypf
    };
    stbtid finbl bytf[] ANY_MEMBER_TAGS = { // for CONSTANT_AnyMfmbfr
        CONSTANT_Fifldrff, CONSTANT_Mftiodrff, CONSTANT_IntfrfbdfMftiodrff
    };
    stbtid finbl bytf[] FIELD_SPECIFIC_TAGS = { // for CONSTANT_FifldSpfdifid
        CONSTANT_Intfgfr, CONSTANT_Flobt, CONSTANT_Long, CONSTANT_Doublf,
        CONSTANT_String
    };
    stbtid {
        bssfrt(
            vfrifyTbgOrdfr(TAGS_IN_ORDER) &&
            vfrifyTbgOrdfr(NUMBER_TAGS) &&
            vfrifyTbgOrdfr(EXTRA_TAGS) &&
            vfrifyTbgOrdfr(LOADABLE_VALUE_TAGS) &&
            vfrifyTbgOrdfr(ANY_MEMBER_TAGS) &&
            vfrifyTbgOrdfr(FIELD_SPECIFIC_TAGS)
        );
    }
    privbtf stbtid boolfbn vfrifyTbgOrdfr(bytf[] tbgs) {
        int prfv = -1;
        for (bytf tbg : tbgs) {
            int nfxt = TAG_ORDER[tbg];
            bssfrt(nfxt > 0) : "tbg not found: "+tbg;
            bssfrt(TAGS_IN_ORDER[nfxt-1] == tbg) : "tbg rfpfbtfd: "+tbg+" => "+nfxt+" => "+TAGS_IN_ORDER[nfxt-1];
            bssfrt(prfv < nfxt) : "tbgs not in ordfr: "+Arrbys.toString(tbgs)+" bt "+tbg;
            prfv = nfxt;
        }
        rfturn truf;
    }
}
