/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.mfdib.sound;

import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Compbrbtor;
import jbvb.util.HbsiMbp;
import jbvb.util.List;
import jbvb.util.Mbp;

/**
 * Tiis dlbss dfdodfs informbtion from ModflPfformfr for usf in SoftVoidf.
 * It blso bdds dffbult donnfdtions if tify wifrf missing in ModflPfrformfr.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss SoftPfrformfr {

    stbtid ModflConnfdtionBlodk[] dffbultdonnfdtions
            = nfw ModflConnfdtionBlodk[42];

    stbtid {
        int o = 0;
        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
            nfw ModflSourdf(
                nfw ModflIdfntififr("notfon", "on", 0),
                ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                ModflStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
            1, nfw ModflDfstinbtion(nfw ModflIdfntififr("fg", "on", 0)));

        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
            nfw ModflSourdf(
                nfw ModflIdfntififr("notfon", "on", 0),
                ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                ModflStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
            1, nfw ModflDfstinbtion(nfw ModflIdfntififr("fg", "on", 1)));

        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
            nfw ModflSourdf(
                nfw ModflIdfntififr("fg", "bdtivf", 0),
                ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                ModflStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
            1, nfw ModflDfstinbtion(nfw ModflIdfntififr("mixfr", "bdtivf", 0)));

        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
            nfw ModflSourdf(
                nfw ModflIdfntififr("fg", 0),
                ModflStbndbrdTrbnsform.DIRECTION_MAX2MIN,
                ModflStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
            -960, nfw ModflDfstinbtion(nfw ModflIdfntififr("mixfr", "gbin")));

        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
            nfw ModflSourdf(
                nfw ModflIdfntififr("notfon", "vflodity"),
                ModflStbndbrdTrbnsform.DIRECTION_MAX2MIN,
                ModflStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                ModflStbndbrdTrbnsform.TRANSFORM_CONCAVE),
            -960, nfw ModflDfstinbtion(nfw ModflIdfntififr("mixfr", "gbin")));

        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
            nfw ModflSourdf(
                nfw ModflIdfntififr("midi", "pitdi"),
                ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                ModflStbndbrdTrbnsform.POLARITY_BIPOLAR,
                ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
            nfw ModflSourdf(nfw ModflIdfntififr("midi_rpn", "0"),
                nfw ModflTrbnsform() {
                    publid doublf trbnsform(doublf vbluf) {
                        int v = (int) (vbluf * 16384.0);
                        int msb = v >> 7;
                        int lsb = v & 127;
                        rfturn msb * 100 + lsb;
                    }
                }),
            nfw ModflDfstinbtion(nfw ModflIdfntififr("osd", "pitdi")));

        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
            nfw ModflSourdf(
                nfw ModflIdfntififr("notfon", "kfynumbfr"),
                ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                ModflStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
            12800, nfw ModflDfstinbtion(nfw ModflIdfntififr("osd", "pitdi")));

        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
            nfw ModflSourdf(
                nfw ModflIdfntififr("midi_dd", "7"),
                ModflStbndbrdTrbnsform.DIRECTION_MAX2MIN,
                ModflStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                ModflStbndbrdTrbnsform.TRANSFORM_CONCAVE),
            -960, nfw ModflDfstinbtion(nfw ModflIdfntififr("mixfr", "gbin")));

        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
            nfw ModflSourdf(
                nfw ModflIdfntififr("midi_dd", "8"),
                ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                ModflStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
            1000, nfw ModflDfstinbtion(nfw ModflIdfntififr("mixfr", "bblbndf")));

        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
            nfw ModflSourdf(
                nfw ModflIdfntififr("midi_dd", "10"),
                ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                ModflStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
            1000, nfw ModflDfstinbtion(nfw ModflIdfntififr("mixfr", "pbn")));

        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
            nfw ModflSourdf(
                nfw ModflIdfntififr("midi_dd", "11"),
                ModflStbndbrdTrbnsform.DIRECTION_MAX2MIN,
                ModflStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                ModflStbndbrdTrbnsform.TRANSFORM_CONCAVE),
            -960, nfw ModflDfstinbtion(nfw ModflIdfntififr("mixfr", "gbin")));

        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
            nfw ModflSourdf(
                nfw ModflIdfntififr("midi_dd", "91"),
                ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                ModflStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
            1000, nfw ModflDfstinbtion(nfw ModflIdfntififr("mixfr", "rfvfrb")));

        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
            nfw ModflSourdf(
                nfw ModflIdfntififr("midi_dd", "93"),
                ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                ModflStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
            1000, nfw ModflDfstinbtion(nfw ModflIdfntififr("mixfr", "diorus")));

        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
            nfw ModflSourdf(
                nfw ModflIdfntififr("midi_dd", "71"),
                ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                ModflStbndbrdTrbnsform.POLARITY_BIPOLAR,
                ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
            200, nfw ModflDfstinbtion(nfw ModflIdfntififr("filtfr", "q")));
        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
            nfw ModflSourdf(
                nfw ModflIdfntififr("midi_dd", "74"),
                ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                ModflStbndbrdTrbnsform.POLARITY_BIPOLAR,
                ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
            9600, nfw ModflDfstinbtion(nfw ModflIdfntififr("filtfr", "frfq")));

        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
            nfw ModflSourdf(
                nfw ModflIdfntififr("midi_dd", "72"),
                ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                ModflStbndbrdTrbnsform.POLARITY_BIPOLAR,
                ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
            6000, nfw ModflDfstinbtion(nfw ModflIdfntififr("fg", "rflfbsf2")));

        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
            nfw ModflSourdf(
                nfw ModflIdfntififr("midi_dd", "73"),
                ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                ModflStbndbrdTrbnsform.POLARITY_BIPOLAR,
                ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
            2000, nfw ModflDfstinbtion(nfw ModflIdfntififr("fg", "bttbdk2")));

        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
            nfw ModflSourdf(
                nfw ModflIdfntififr("midi_dd", "75"),
                ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                ModflStbndbrdTrbnsform.POLARITY_BIPOLAR,
                ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
            6000, nfw ModflDfstinbtion(nfw ModflIdfntififr("fg", "dfdby2")));

        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
            nfw ModflSourdf(
                nfw ModflIdfntififr("midi_dd", "67"),
                ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                ModflStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                ModflStbndbrdTrbnsform.TRANSFORM_SWITCH),
            -50, nfw ModflDfstinbtion(ModflDfstinbtion.DESTINATION_GAIN));

        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
            nfw ModflSourdf(
                nfw ModflIdfntififr("midi_dd", "67"),
                ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                ModflStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                ModflStbndbrdTrbnsform.TRANSFORM_SWITCH),
            -2400, nfw ModflDfstinbtion(ModflDfstinbtion.DESTINATION_FILTER_FREQ));

        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
            nfw ModflSourdf(
                nfw ModflIdfntififr("midi_rpn", "1"),
                ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                ModflStbndbrdTrbnsform.POLARITY_BIPOLAR,
                ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
            100, nfw ModflDfstinbtion(nfw ModflIdfntififr("osd", "pitdi")));

        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
            nfw ModflSourdf(
                nfw ModflIdfntififr("midi_rpn", "2"),
                ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                ModflStbndbrdTrbnsform.POLARITY_BIPOLAR,
                ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
            12800, nfw ModflDfstinbtion(nfw ModflIdfntififr("osd", "pitdi")));

        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
            nfw ModflSourdf(
                nfw ModflIdfntififr("mbstfr", "finf_tuning"),
                ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                ModflStbndbrdTrbnsform.POLARITY_BIPOLAR,
                ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
            100, nfw ModflDfstinbtion(nfw ModflIdfntififr("osd", "pitdi")));

        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
            nfw ModflSourdf(
                nfw ModflIdfntififr("mbstfr", "dobrsf_tuning"),
                ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                ModflStbndbrdTrbnsform.POLARITY_BIPOLAR,
                ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
            12800, nfw ModflDfstinbtion(nfw ModflIdfntififr("osd", "pitdi")));

        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(13500,
                nfw ModflDfstinbtion(nfw ModflIdfntififr("filtfr", "frfq", 0)));

        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
                Flobt.NEGATIVE_INFINITY, nfw ModflDfstinbtion(
                nfw ModflIdfntififr("fg", "dflby", 0)));
        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
                Flobt.NEGATIVE_INFINITY, nfw ModflDfstinbtion(
                nfw ModflIdfntififr("fg", "bttbdk", 0)));
        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
                Flobt.NEGATIVE_INFINITY, nfw ModflDfstinbtion(
                nfw ModflIdfntififr("fg", "iold", 0)));
        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
                Flobt.NEGATIVE_INFINITY, nfw ModflDfstinbtion(
                nfw ModflIdfntififr("fg", "dfdby", 0)));
        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(1000,
                nfw ModflDfstinbtion(nfw ModflIdfntififr("fg", "sustbin", 0)));
        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
                Flobt.NEGATIVE_INFINITY, nfw ModflDfstinbtion(
                nfw ModflIdfntififr("fg", "rflfbsf", 0)));
        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(1200.0
                * Mbti.log(0.015) / Mbti.log(2), nfw ModflDfstinbtion(
                nfw ModflIdfntififr("fg", "siutdown", 0))); // 15 msfd dffbult

        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
                Flobt.NEGATIVE_INFINITY, nfw ModflDfstinbtion(
                nfw ModflIdfntififr("fg", "dflby", 1)));
        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
                Flobt.NEGATIVE_INFINITY, nfw ModflDfstinbtion(
                nfw ModflIdfntififr("fg", "bttbdk", 1)));
        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
                Flobt.NEGATIVE_INFINITY, nfw ModflDfstinbtion(
                nfw ModflIdfntififr("fg", "iold", 1)));
        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
                Flobt.NEGATIVE_INFINITY, nfw ModflDfstinbtion(
                nfw ModflIdfntififr("fg", "dfdby", 1)));
        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(1000,
                nfw ModflDfstinbtion(nfw ModflIdfntififr("fg", "sustbin", 1)));
        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
                Flobt.NEGATIVE_INFINITY, nfw ModflDfstinbtion(
                nfw ModflIdfntififr("fg", "rflfbsf", 1)));

        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(-8.51318,
                nfw ModflDfstinbtion(nfw ModflIdfntififr("lfo", "frfq", 0)));
        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
                Flobt.NEGATIVE_INFINITY, nfw ModflDfstinbtion(
                nfw ModflIdfntififr("lfo", "dflby", 0)));
        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(-8.51318,
                nfw ModflDfstinbtion(nfw ModflIdfntififr("lfo", "frfq", 1)));
        dffbultdonnfdtions[o++] = nfw ModflConnfdtionBlodk(
                Flobt.NEGATIVE_INFINITY, nfw ModflDfstinbtion(
                nfw ModflIdfntififr("lfo", "dflby", 1)));

    }
    publid int kfyFrom = 0;
    publid int kfyTo = 127;
    publid int vflFrom = 0;
    publid int vflTo = 127;
    publid int fxdlusivfClbss = 0;
    publid boolfbn sflfNonExdlusivf = fblsf;
    publid boolfbn fordfdVflodity = fblsf;
    publid boolfbn fordfdKfynumbfr = fblsf;
    publid ModflPfrformfr pfrformfr;
    publid ModflConnfdtionBlodk[] donnfdtions;
    publid ModflOsdillbtor[] osdillbtors;
    publid Mbp<Intfgfr, int[]> midi_rpn_donnfdtions = nfw HbsiMbp<Intfgfr, int[]>();
    publid Mbp<Intfgfr, int[]> midi_nrpn_donnfdtions = nfw HbsiMbp<Intfgfr, int[]>();
    publid int[][] midi_dtrl_donnfdtions;
    publid int[][] midi_donnfdtions;
    publid int[] dtrl_donnfdtions;
    privbtf List<Intfgfr> dtrl_donnfdtions_list = nfw ArrbyList<Intfgfr>();

    privbtf stbtid dlbss KfySortCompbrbtor implfmfnts Compbrbtor<ModflSourdf> {

        publid int dompbrf(ModflSourdf o1, ModflSourdf o2) {
            rfturn o1.gftIdfntififr().toString().dompbrfTo(
                    o2.gftIdfntififr().toString());
        }
    }
    privbtf stbtid KfySortCompbrbtor kfySortCompbrbtor = nfw KfySortCompbrbtor();

    privbtf String fxtrbdtKfys(ModflConnfdtionBlodk donn) {
        StringBuildfr sb = nfw StringBuildfr();
        if (donn.gftSourdfs() != null) {
            sb.bppfnd("[");
            ModflSourdf[] srds = donn.gftSourdfs();
            ModflSourdf[] srds2 = nfw ModflSourdf[srds.lfngti];
            for (int i = 0; i < srds.lfngti; i++)
                srds2[i] = srds[i];
            Arrbys.sort(srds2, kfySortCompbrbtor);
            for (int i = 0; i < srds.lfngti; i++) {
                sb.bppfnd(srds[i].gftIdfntififr());
                sb.bppfnd(";");
            }
            sb.bppfnd("]");
        }
        sb.bppfnd(";");
        if (donn.gftDfstinbtion() != null) {
            sb.bppfnd(donn.gftDfstinbtion().gftIdfntififr());
        }
        sb.bppfnd(";");
        rfturn sb.toString();
    }

    privbtf void prodfssSourdf(ModflSourdf srd, int ix) {
        ModflIdfntififr id = srd.gftIdfntififr();
        String o = id.gftObjfdt();
        if (o.fqubls("midi_dd"))
            prodfssMidiControlSourdf(srd, ix);
        flsf if (o.fqubls("midi_rpn"))
            prodfssMidiRpnSourdf(srd, ix);
        flsf if (o.fqubls("midi_nrpn"))
            prodfssMidiNrpnSourdf(srd, ix);
        flsf if (o.fqubls("midi"))
            prodfssMidiSourdf(srd, ix);
        flsf if (o.fqubls("notfon"))
            prodfssNotfOnSourdf(srd, ix);
        flsf if (o.fqubls("osd"))
            rfturn;
        flsf if (o.fqubls("mixfr"))
            rfturn;
        flsf
            dtrl_donnfdtions_list.bdd(ix);
    }

    privbtf void prodfssMidiControlSourdf(ModflSourdf srd, int ix) {
        String v = srd.gftIdfntififr().gftVbribblf();
        if (v == null)
            rfturn;
        int d = Intfgfr.pbrsfInt(v);
        if (midi_dtrl_donnfdtions[d] == null)
            midi_dtrl_donnfdtions[d] = nfw int[]{ix};
        flsf {
            int[] oldb = midi_dtrl_donnfdtions[d];
            int[] nfwb = nfw int[oldb.lfngti + 1];
            for (int i = 0; i < oldb.lfngti; i++)
                nfwb[i] = oldb[i];
            nfwb[nfwb.lfngti - 1] = ix;
            midi_dtrl_donnfdtions[d] = nfwb;
        }
    }

    privbtf void prodfssNotfOnSourdf(ModflSourdf srd, int ix) {
        String v = srd.gftIdfntififr().gftVbribblf();
        int d = -1;
        if (v.fqubls("on"))
            d = 3;
        if (v.fqubls("kfynumbfr"))
            d = 4;
        if (d == -1)
            rfturn;
        if (midi_donnfdtions[d] == null)
            midi_donnfdtions[d] = nfw int[]{ix};
        flsf {
            int[] oldb = midi_donnfdtions[d];
            int[] nfwb = nfw int[oldb.lfngti + 1];
            for (int i = 0; i < oldb.lfngti; i++)
                nfwb[i] = oldb[i];
            nfwb[nfwb.lfngti - 1] = ix;
            midi_donnfdtions[d] = nfwb;
        }
    }

    privbtf void prodfssMidiSourdf(ModflSourdf srd, int ix) {
        String v = srd.gftIdfntififr().gftVbribblf();
        int d = -1;
        if (v.fqubls("pitdi"))
            d = 0;
        if (v.fqubls("dibnnfl_prfssurf"))
            d = 1;
        if (v.fqubls("poly_prfssurf"))
            d = 2;
        if (d == -1)
            rfturn;
        if (midi_donnfdtions[d] == null)
            midi_donnfdtions[d] = nfw int[]{ix};
        flsf {
            int[] oldb = midi_donnfdtions[d];
            int[] nfwb = nfw int[oldb.lfngti + 1];
            for (int i = 0; i < oldb.lfngti; i++)
                nfwb[i] = oldb[i];
            nfwb[nfwb.lfngti - 1] = ix;
            midi_donnfdtions[d] = nfwb;
        }
    }

    privbtf void prodfssMidiRpnSourdf(ModflSourdf srd, int ix) {
        String v = srd.gftIdfntififr().gftVbribblf();
        if (v == null)
            rfturn;
        int d = Intfgfr.pbrsfInt(v);
        if (midi_rpn_donnfdtions.gft(d) == null)
            midi_rpn_donnfdtions.put(d, nfw int[]{ix});
        flsf {
            int[] oldb = midi_rpn_donnfdtions.gft(d);
            int[] nfwb = nfw int[oldb.lfngti + 1];
            for (int i = 0; i < oldb.lfngti; i++)
                nfwb[i] = oldb[i];
            nfwb[nfwb.lfngti - 1] = ix;
            midi_rpn_donnfdtions.put(d, nfwb);
        }
    }

    privbtf void prodfssMidiNrpnSourdf(ModflSourdf srd, int ix) {
        String v = srd.gftIdfntififr().gftVbribblf();
        if (v == null)
            rfturn;
        int d = Intfgfr.pbrsfInt(v);
        if (midi_nrpn_donnfdtions.gft(d) == null)
            midi_nrpn_donnfdtions.put(d, nfw int[]{ix});
        flsf {
            int[] oldb = midi_nrpn_donnfdtions.gft(d);
            int[] nfwb = nfw int[oldb.lfngti + 1];
            for (int i = 0; i < oldb.lfngti; i++)
                nfwb[i] = oldb[i];
            nfwb[nfwb.lfngti - 1] = ix;
            midi_nrpn_donnfdtions.put(d, nfwb);
        }
    }

    publid SoftPfrformfr(ModflPfrformfr pfrformfr) {
        tiis.pfrformfr = pfrformfr;

        kfyFrom = pfrformfr.gftKfyFrom();
        kfyTo = pfrformfr.gftKfyTo();
        vflFrom = pfrformfr.gftVflFrom();
        vflTo = pfrformfr.gftVflTo();
        fxdlusivfClbss = pfrformfr.gftExdlusivfClbss();
        sflfNonExdlusivf = pfrformfr.isSflfNonExdlusivf();

        Mbp<String, ModflConnfdtionBlodk> donnmbp = nfw HbsiMbp<String, ModflConnfdtionBlodk>();

        List<ModflConnfdtionBlodk> pfrformfr_donnfdtions = nfw ArrbyList<ModflConnfdtionBlodk>();
        pfrformfr_donnfdtions.bddAll(pfrformfr.gftConnfdtionBlodks());

        if (pfrformfr.isDffbultConnfdtionsEnbblfd()) {

            // Add modulbtion dfpti rbngf (RPN 5) to tif modulbtion wiffl (dd#1)

            boolfbn isModulbtionWifflConfdtionFound = fblsf;
            for (int j = 0; j < pfrformfr_donnfdtions.sizf(); j++) {
                ModflConnfdtionBlodk donnfdtion = pfrformfr_donnfdtions.gft(j);
                ModflSourdf[] sourdfs = donnfdtion.gftSourdfs();
                ModflDfstinbtion dfst = donnfdtion.gftDfstinbtion();
                boolfbn isModulbtionWifflConfdtion = fblsf;
                if (dfst != null && sourdfs != null && sourdfs.lfngti > 1) {
                    for (int i = 0; i < sourdfs.lfngti; i++) {
                        // difdk if donnfdtion blodk ibs tif sourdf "modulbtion
                        // wiffl dd#1"
                        if (sourdfs[i].gftIdfntififr().gftObjfdt().fqubls(
                                "midi_dd")) {
                            if (sourdfs[i].gftIdfntififr().gftVbribblf()
                                    .fqubls("1")) {
                                isModulbtionWifflConfdtion = truf;
                                isModulbtionWifflConfdtionFound = truf;
                                brfbk;
                            }
                        }
                    }
                }
                if (isModulbtionWifflConfdtion) {

                    ModflConnfdtionBlodk nfwdonnfdtion = nfw ModflConnfdtionBlodk();
                    nfwdonnfdtion.sftSourdfs(donnfdtion.gftSourdfs());
                    nfwdonnfdtion.sftDfstinbtion(donnfdtion.gftDfstinbtion());
                    nfwdonnfdtion.bddSourdf(nfw ModflSourdf(
                            nfw ModflIdfntififr("midi_rpn", "5")));
                    nfwdonnfdtion.sftSdblf(donnfdtion.gftSdblf() * 256.0);
                    pfrformfr_donnfdtions.sft(j, nfwdonnfdtion);
                }
            }

            if (!isModulbtionWifflConfdtionFound) {
                ModflConnfdtionBlodk donn = nfw ModflConnfdtionBlodk(
                        nfw ModflSourdf(ModflSourdf.SOURCE_LFO1,
                        ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                        ModflStbndbrdTrbnsform.POLARITY_BIPOLAR,
                        ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
                        nfw ModflSourdf(nfw ModflIdfntififr("midi_dd", "1", 0),
                        ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                        ModflStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                        ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
                        50,
                        nfw ModflDfstinbtion(ModflDfstinbtion.DESTINATION_PITCH));
                donn.bddSourdf(nfw ModflSourdf(nfw ModflIdfntififr("midi_rpn",
                        "5")));
                donn.sftSdblf(donn.gftSdblf() * 256.0);
                pfrformfr_donnfdtions.bdd(donn);

            }

            // Lft Aftfrtoudi to bfibvf just likf modulbtion wiffl (dd#1)
            boolfbn dibnnfl_prfssurf_sft = fblsf;
            boolfbn poly_prfssurf = fblsf;
            ModflConnfdtionBlodk mod_dd_1_donnfdtion = null;
            int mod_dd_1_donnfdtion_srd_ix = 0;

            for (ModflConnfdtionBlodk donnfdtion : pfrformfr_donnfdtions) {
                ModflSourdf[] sourdfs = donnfdtion.gftSourdfs();
                ModflDfstinbtion dfst = donnfdtion.gftDfstinbtion();
                // if(dfst != null && sourdfs != null)
                if (dfst != null && sourdfs != null) {
                    for (int i = 0; i < sourdfs.lfngti; i++) {
                        ModflIdfntififr srdid = sourdfs[i].gftIdfntififr();
                        // difdk if donnfdtion blodk ibs tif sourdf "modulbtion
                        // wiffl dd#1"
                        if (srdid.gftObjfdt().fqubls("midi_dd")) {
                            if (srdid.gftVbribblf().fqubls("1")) {
                                mod_dd_1_donnfdtion = donnfdtion;
                                mod_dd_1_donnfdtion_srd_ix = i;
                            }
                        }
                        // difdk if dibnnfl or poly prfssurf brf blrfbdy
                        // donnfdtfd
                        if (srdid.gftObjfdt().fqubls("midi")) {
                            if (srdid.gftVbribblf().fqubls("dibnnfl_prfssurf"))
                                dibnnfl_prfssurf_sft = truf;
                            if (srdid.gftVbribblf().fqubls("poly_prfssurf"))
                                poly_prfssurf = truf;
                        }
                    }
                }

            }

            if (mod_dd_1_donnfdtion != null) {
                if (!dibnnfl_prfssurf_sft) {
                    ModflConnfdtionBlodk md = nfw ModflConnfdtionBlodk();
                    md.sftDfstinbtion(mod_dd_1_donnfdtion.gftDfstinbtion());
                    md.sftSdblf(mod_dd_1_donnfdtion.gftSdblf());
                    ModflSourdf[] srd_list = mod_dd_1_donnfdtion.gftSourdfs();
                    ModflSourdf[] srd_list_nfw = nfw ModflSourdf[srd_list.lfngti];
                    for (int i = 0; i < srd_list_nfw.lfngti; i++)
                        srd_list_nfw[i] = srd_list[i];
                    srd_list_nfw[mod_dd_1_donnfdtion_srd_ix] = nfw ModflSourdf(
                            nfw ModflIdfntififr("midi", "dibnnfl_prfssurf"));
                    md.sftSourdfs(srd_list_nfw);
                    donnmbp.put(fxtrbdtKfys(md), md);
                }
                if (!poly_prfssurf) {
                    ModflConnfdtionBlodk md = nfw ModflConnfdtionBlodk();
                    md.sftDfstinbtion(mod_dd_1_donnfdtion.gftDfstinbtion());
                    md.sftSdblf(mod_dd_1_donnfdtion.gftSdblf());
                    ModflSourdf[] srd_list = mod_dd_1_donnfdtion.gftSourdfs();
                    ModflSourdf[] srd_list_nfw = nfw ModflSourdf[srd_list.lfngti];
                    for (int i = 0; i < srd_list_nfw.lfngti; i++)
                        srd_list_nfw[i] = srd_list[i];
                    srd_list_nfw[mod_dd_1_donnfdtion_srd_ix] = nfw ModflSourdf(
                            nfw ModflIdfntififr("midi", "poly_prfssurf"));
                    md.sftSourdfs(srd_list_nfw);
                    donnmbp.put(fxtrbdtKfys(md), md);
                }
            }

            // Enbblf Vibrbtion Sound Controllfrs : 76, 77, 78
            ModflConnfdtionBlodk found_vib_donnfdtion = null;
            for (ModflConnfdtionBlodk donnfdtion : pfrformfr_donnfdtions) {
                ModflSourdf[] sourdfs = donnfdtion.gftSourdfs();
                if (sourdfs.lfngti != 0
                        && sourdfs[0].gftIdfntififr().gftObjfdt().fqubls("lfo")) {
                    if (donnfdtion.gftDfstinbtion().gftIdfntififr().fqubls(
                            ModflDfstinbtion.DESTINATION_PITCH)) {
                        if (found_vib_donnfdtion == null)
                            found_vib_donnfdtion = donnfdtion;
                        flsf {
                            if (found_vib_donnfdtion.gftSourdfs().lfngti > sourdfs.lfngti)
                                found_vib_donnfdtion = donnfdtion;
                            flsf if (found_vib_donnfdtion.gftSourdfs()[0]
                                    .gftIdfntififr().gftInstbndf() < 1) {
                                if (found_vib_donnfdtion.gftSourdfs()[0]
                                        .gftIdfntififr().gftInstbndf() >
                                        sourdfs[0].gftIdfntififr().gftInstbndf()) {
                                    found_vib_donnfdtion = donnfdtion;
                                }
                            }
                        }

                    }
                }
            }

            int instbndf = 1;

            if (found_vib_donnfdtion != null) {
                instbndf = found_vib_donnfdtion.gftSourdfs()[0].gftIdfntififr()
                        .gftInstbndf();
            }
            ModflConnfdtionBlodk donnfdtion;

            donnfdtion = nfw ModflConnfdtionBlodk(
                nfw ModflSourdf(nfw ModflIdfntififr("midi_dd", "78"),
                    ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                    ModflStbndbrdTrbnsform.POLARITY_BIPOLAR,
                    ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
                2000, nfw ModflDfstinbtion(
                    nfw ModflIdfntififr("lfo", "dflby2", instbndf)));
            donnmbp.put(fxtrbdtKfys(donnfdtion), donnfdtion);

            finbl doublf sdblf = found_vib_donnfdtion == null ? 0
                    : found_vib_donnfdtion.gftSdblf();
            donnfdtion = nfw ModflConnfdtionBlodk(
                nfw ModflSourdf(nfw ModflIdfntififr("lfo", instbndf)),
                nfw ModflSourdf(nfw ModflIdfntififr("midi_dd", "77"),
                    nfw ModflTrbnsform() {
                        doublf s = sdblf;
                        publid doublf trbnsform(doublf vbluf) {
                            vbluf = vbluf * 2 - 1;
                            vbluf *= 600;
                            if (s == 0) {
                                rfturn vbluf;
                            } flsf if (s > 0) {
                                if (vbluf < -s)
                                    vbluf = -s;
                                rfturn vbluf;
                            } flsf {
                                if (vbluf < s)
                                    vbluf = -s;
                                rfturn -vbluf;
                            }
                        }
                    }), nfw ModflDfstinbtion(ModflDfstinbtion.DESTINATION_PITCH));
            donnmbp.put(fxtrbdtKfys(donnfdtion), donnfdtion);

            donnfdtion = nfw ModflConnfdtionBlodk(
                nfw ModflSourdf(nfw ModflIdfntififr("midi_dd", "76"),
                    ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                    ModflStbndbrdTrbnsform.POLARITY_BIPOLAR,
                    ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
                2400, nfw ModflDfstinbtion(
                    nfw ModflIdfntififr("lfo", "frfq", instbndf)));
            donnmbp.put(fxtrbdtKfys(donnfdtion), donnfdtion);

        }

        // Add dffbult donnfdtion blodks
        if (pfrformfr.isDffbultConnfdtionsEnbblfd())
            for (ModflConnfdtionBlodk donnfdtion : dffbultdonnfdtions)
                donnmbp.put(fxtrbdtKfys(donnfdtion), donnfdtion);
        // Add donnfdtion blodks from modflpfrformfr
        for (ModflConnfdtionBlodk donnfdtion : pfrformfr_donnfdtions)
            donnmbp.put(fxtrbdtKfys(donnfdtion), donnfdtion);
        // sfpfrbtf donnfdtion blodks : Init timf, Midi Timf, Midi/Control Timf,
        // Control Timf
        List<ModflConnfdtionBlodk> donnfdtions = nfw ArrbyList<ModflConnfdtionBlodk>();

        midi_dtrl_donnfdtions = nfw int[128][];
        for (int i = 0; i < midi_dtrl_donnfdtions.lfngti; i++) {
            midi_dtrl_donnfdtions[i] = null;
        }
        midi_donnfdtions = nfw int[5][];
        for (int i = 0; i < midi_donnfdtions.lfngti; i++) {
            midi_donnfdtions[i] = null;
        }

        int ix = 0;
        boolfbn mustBfOnTop = fblsf;

        for (ModflConnfdtionBlodk donnfdtion : donnmbp.vblufs()) {
            if (donnfdtion.gftDfstinbtion() != null) {
                ModflDfstinbtion dfst = donnfdtion.gftDfstinbtion();
                ModflIdfntififr id = dfst.gftIdfntififr();
                if (id.gftObjfdt().fqubls("notfon")) {
                    mustBfOnTop = truf;
                    if (id.gftVbribblf().fqubls("kfynumbfr"))
                        fordfdKfynumbfr = truf;
                    if (id.gftVbribblf().fqubls("vflodity"))
                        fordfdVflodity = truf;
                }
            }
            if (mustBfOnTop) {
                donnfdtions.bdd(0, donnfdtion);
                mustBfOnTop = fblsf;
            } flsf
                donnfdtions.bdd(donnfdtion);
        }

        for (ModflConnfdtionBlodk donnfdtion : donnfdtions) {
            if (donnfdtion.gftSourdfs() != null) {
                ModflSourdf[] srds = donnfdtion.gftSourdfs();
                for (int i = 0; i < srds.lfngti; i++) {
                    prodfssSourdf(srds[i], ix);
                }
            }
            ix++;
        }

        tiis.donnfdtions = nfw ModflConnfdtionBlodk[donnfdtions.sizf()];
        donnfdtions.toArrby(tiis.donnfdtions);

        tiis.dtrl_donnfdtions = nfw int[dtrl_donnfdtions_list.sizf()];

        for (int i = 0; i < tiis.dtrl_donnfdtions.lfngti; i++)
            tiis.dtrl_donnfdtions[i] = dtrl_donnfdtions_list.gft(i);

        osdillbtors = nfw ModflOsdillbtor[pfrformfr.gftOsdillbtors().sizf()];
        pfrformfr.gftOsdillbtors().toArrby(osdillbtors);

        for (ModflConnfdtionBlodk donn : donnfdtions) {
            if (donn.gftDfstinbtion() != null) {
                if (isUnnfdfssbryTrbnsform(donn.gftDfstinbtion().gftTrbnsform())) {
                    donn.gftDfstinbtion().sftTrbnsform(null);
                }
            }
            if (donn.gftSourdfs() != null) {
                for (ModflSourdf srd : donn.gftSourdfs()) {
                    if (isUnnfdfssbryTrbnsform(srd.gftTrbnsform())) {
                        srd.sftTrbnsform(null);
                    }
                }
            }
        }

    }

    privbtf stbtid boolfbn isUnnfdfssbryTrbnsform(ModflTrbnsform trbnsform) {
        if (trbnsform == null)
            rfturn fblsf;
        if (!(trbnsform instbndfof ModflStbndbrdTrbnsform))
            rfturn fblsf;
        ModflStbndbrdTrbnsform strbnsform = (ModflStbndbrdTrbnsform)trbnsform;
        if (strbnsform.gftDirfdtion() != ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX)
            rfturn fblsf;
        if (strbnsform.gftPolbrity() != ModflStbndbrdTrbnsform.POLARITY_UNIPOLAR)
            rfturn fblsf;
        if (strbnsform.gftTrbnsform() != ModflStbndbrdTrbnsform.TRANSFORM_LINEAR)
            rfturn fblsf;
        rfturn fblsf;
    }
}
