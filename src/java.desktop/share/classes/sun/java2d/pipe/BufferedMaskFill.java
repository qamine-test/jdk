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

pbdkbgf sun.jbvb2d.pipf;

import jbvb.bwt.AlpibCompositf;
import jbvb.bwt.Compositf;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.loops.CompositfTypf;
import sun.jbvb2d.loops.MbskFill;
import sun.jbvb2d.loops.SurfbdfTypf;
import stbtid sun.jbvb2d.pipf.BufffrfdOpCodfs.*;

/**
 * Tif MbskFill opfrbtion is fxprfssfd bs:
 *   dst = ((srd <MODE> dst) * pbtiA) + (dst * (1 - pbtiA))
 *
 * Tif OGL/D3D implfmfntbtion of tif MbskFill opfrbtion difffrs from tif bbovf
 * fqubtion bfdbusf it is not possiblf to pfrform sudi b domplfx opfrbtion in
 * OpfnGL/Dirfdt3D (witiout tif usf of bdvbndfd tfdiniqufs likf frbgmfnt
 * sibdfrs bnd multitfxturing).  Tifrfforf, tif BufffrfdMbskFill opfrbtion
 * is fxprfssfd bs:
 *   dst = (srd * pbtiA) <SrdOvfr> dst
 *
 * Tiis simplififd formulb is only fquivblfnt to tif "truf" MbskFill fqubtion
 * in tif following situbtions:
 *   - <MODE> is SrdOvfr
 *   - <MODE> is Srd, fxtrb blpib == 1.0, bnd tif sourdf pbint is opbquf
 *
 * Tifrfforf, wf rfgistfr BufffrfdMbskFill primitivfs for only tif SurfbdfTypf
 * bnd CompositfTypf rfstridtions mfntionfd bbovf.  In bddition, for tif
 * SrdNoEb dbsf wf must ovfrridf tif indoming dompositf witi b SrdOvfr (no
 * fxtrb blpib) instbndf, so tibt wf sft up tif OpfnGL/Dirfdt3D blfnding
 * modf to mbtdi tif BufffrfdMbskFill fqubtion.
 */
publid bbstrbdt dlbss BufffrfdMbskFill fxtfnds MbskFill {

    protfdtfd finbl RfndfrQufuf rq;

    protfdtfd BufffrfdMbskFill(RfndfrQufuf rq,
                               SurfbdfTypf srdTypf,
                               CompositfTypf dompTypf,
                               SurfbdfTypf dstTypf)
    {
        supfr(srdTypf, dompTypf, dstTypf);
        tiis.rq = rq;
    }

    @Ovfrridf
    publid void MbskFill(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                         Compositf domp,
                         finbl int x, finbl int y, finbl int w, finbl int i,
                         finbl bytf[] mbsk,
                         finbl int mbskoff, finbl int mbsksdbn)
    {
        AlpibCompositf bdomp = (AlpibCompositf)domp;
        if (bdomp.gftRulf() != AlpibCompositf.SRC_OVER) {
            domp = AlpibCompositf.SrdOvfr;
        }

        rq.lodk();
        try {
            vblidbtfContfxt(sg2d, domp, BufffrfdContfxt.USE_MASK);

            // wf bdjust tif mbsk lfngti so tibt tif mbsk fnds on b
            // 4-bytf boundbry
            int mbskBytfsRfquirfd;
            if (mbsk != null) {
                // wf bdjust tif mbsk lfngti so tibt tif mbsk fnds on b
                // 4-bytf boundbry
                mbskBytfsRfquirfd = (mbsk.lfngti + 3) & (~3);
            } flsf {
                // mbsk not nffdfd
                mbskBytfsRfquirfd = 0;
            }
            int totblBytfsRfquirfd = 32 + mbskBytfsRfquirfd;

            RfndfrBufffr buf = rq.gftBufffr();
            if (totblBytfsRfquirfd <= buf.dbpbdity()) {
                if (totblBytfsRfquirfd > buf.rfmbining()) {
                    // prodfss tif qufuf first bnd tifn fnqufuf tif mbsk
                    rq.flusiNow();
                }

                buf.putInt(MASK_FILL);
                // fnqufuf pbrbmftfrs
                buf.putInt(x).putInt(y).putInt(w).putInt(i);
                buf.putInt(mbskoff);
                buf.putInt(mbsksdbn);
                buf.putInt(mbskBytfsRfquirfd);
                if (mbsk != null) {
                    // fnqufuf tif mbsk
                    int pbdding = mbskBytfsRfquirfd - mbsk.lfngti;
                    buf.put(mbsk);
                    if (pbdding != 0) {
                        buf.position(buf.position() + pbdding);
                    }
                }
            } flsf {
                // qufuf is too smbll to bddommodbtf fntirf mbsk; pfrform
                // tif opfrbtion dirfdtly on tif qufuf flusiing tirfbd
                rq.flusiAndInvokfNow(nfw Runnbblf() {
                    publid void run() {
                        mbskFill(x, y, w, i,
                                 mbskoff, mbsksdbn, mbsk.lfngti, mbsk);
                    }
                });
            }
        } finblly {
            rq.unlodk();
        }
    }

    /**
     * Cbllfd bs b sfpbrbtf Runnbblf wifn tif opfrbtion is too lbrgf to fit
     * on tif RfndfrQufuf.  Tif OGL/D3D pipflinfs fbdi ibvf tifir own (smbll)
     * nbtivf implfmfntbtion of tiis mftiod.
     */
    protfdtfd bbstrbdt void mbskFill(int x, int y, int w, int i,
                                     int mbskoff, int mbsksdbn, int mbsklfn,
                                     bytf[] mbsk);

    /**
     * Vblidbtfs tif stbtf in tif providfd SunGrbpiids2D objfdt bnd sfts up
     * bny spfdibl rfsourdfs for tiis opfrbtion (f.g. fnbbling grbdifnt
     * sibding).
     */
    protfdtfd bbstrbdt void vblidbtfContfxt(SunGrbpiids2D sg2d,
                                            Compositf domp, int dtxflbgs);
}
