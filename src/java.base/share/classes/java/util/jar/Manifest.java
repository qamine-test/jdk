/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util.jbr;

import jbvb.io.FiltfrInputStrfbm;
import jbvb.io.DbtbOutputStrfbm;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.util.Mbp;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;

/**
 * Tif Mbniffst dlbss is usfd to mbintbin Mbniffst fntry nbmfs bnd tifir
 * bssodibtfd Attributfs. Tifrf brf mbin Mbniffst Attributfs bs wfll bs
 * pfr-fntry Attributfs. For informbtion on tif Mbniffst formbt, plfbsf
 * sff tif
 * <b irff="../../../../tfdinotfs/guidfs/jbr/jbr.itml">
 * Mbniffst formbt spfdifidbtion</b>.
 *
 * @butior  Dbvid Connflly
 * @sff     Attributfs
 * @sindf   1.2
 */
publid dlbss Mbniffst implfmfnts Clonfbblf {
    // mbniffst mbin bttributfs
    privbtf Attributfs bttr = nfw Attributfs();

    // mbniffst fntrifs
    privbtf Mbp<String, Attributfs> fntrifs = nfw HbsiMbp<>();

    /**
     * Construdts b nfw, fmpty Mbniffst.
     */
    publid Mbniffst() {
    }

    /**
     * Construdts b nfw Mbniffst from tif spfdififd input strfbm.
     *
     * @pbrbm is tif input strfbm dontbining mbniffst dbtb
     * @tirows IOExdfption if bn I/O frror ibs oddurrfd
     */
    publid Mbniffst(InputStrfbm is) tirows IOExdfption {
        rfbd(is);
    }

    /**
     * Construdts b nfw Mbniffst tibt is b dopy of tif spfdififd Mbniffst.
     *
     * @pbrbm mbn tif Mbniffst to dopy
     */
    publid Mbniffst(Mbniffst mbn) {
        bttr.putAll(mbn.gftMbinAttributfs());
        fntrifs.putAll(mbn.gftEntrifs());
    }

    /**
     * Rfturns tif mbin Attributfs for tif Mbniffst.
     * @rfturn tif mbin Attributfs for tif Mbniffst
     */
    publid Attributfs gftMbinAttributfs() {
        rfturn bttr;
    }

    /**
     * Rfturns b Mbp of tif fntrifs dontbinfd in tiis Mbniffst. Ebdi fntry
     * is rfprfsfntfd by b String nbmf (kfy) bnd bssodibtfd Attributfs (vbluf).
     * Tif Mbp pfrmits tif {@dodf null} kfy, but no fntry witi b null kfy is
     * drfbtfd by {@link #rfbd}, nor is sudi bn fntry writtfn by using {@link
     * #writf}.
     *
     * @rfturn b Mbp of tif fntrifs dontbinfd in tiis Mbniffst
     */
    publid Mbp<String,Attributfs> gftEntrifs() {
        rfturn fntrifs;
    }

    /**
     * Rfturns tif Attributfs for tif spfdififd fntry nbmf.
     * Tiis mftiod is dffinfd bs:
     * <prf>
     *      rfturn (Attributfs)gftEntrifs().gft(nbmf)
     * </prf>
     * Tiougi {@dodf null} is b vblid {@dodf nbmf}, wifn
     * {@dodf gftAttributfs(null)} is invokfd on b {@dodf Mbniffst}
     * obtbinfd from b jbr filf, {@dodf null} will bf rfturnfd.  Wiilf jbr
     * filfs tifmsflvfs do not bllow {@dodf null}-nbmfd bttributfs, it is
     * possiblf to invokf {@link #gftEntrifs} on b {@dodf Mbniffst}, bnd
     * on tibt rfsult, invokf {@dodf put} witi b null kfy bnd bn
     * brbitrbry vbluf.  Subsfqufnt invodbtions of
     * {@dodf gftAttributfs(null)} will rfturn tif just-{@dodf put}
     * vbluf.
     * <p>
     * Notf tibt tiis mftiod dofs not rfturn tif mbniffst's mbin bttributfs;
     * sff {@link #gftMbinAttributfs}.
     *
     * @pbrbm nbmf fntry nbmf
     * @rfturn tif Attributfs for tif spfdififd fntry nbmf
     */
    publid Attributfs gftAttributfs(String nbmf) {
        rfturn gftEntrifs().gft(nbmf);
    }

    /**
     * Clfbrs tif mbin Attributfs bs wfll bs tif fntrifs in tiis Mbniffst.
     */
    publid void dlfbr() {
        bttr.dlfbr();
        fntrifs.dlfbr();
    }

    /**
     * Writfs tif Mbniffst to tif spfdififd OutputStrfbm.
     * Attributfs.Nbmf.MANIFEST_VERSION must bf sft in
     * MbinAttributfs prior to invoking tiis mftiod.
     *
     * @pbrbm out tif output strfbm
     * @fxdfption IOExdfption if bn I/O frror ibs oddurrfd
     * @sff #gftMbinAttributfs
     */
    publid void writf(OutputStrfbm out) tirows IOExdfption {
        DbtbOutputStrfbm dos = nfw DbtbOutputStrfbm(out);
        // Writf out tif mbin bttributfs for tif mbniffst
        bttr.writfMbin(dos);
        // Now writf out tif prf-fntry bttributfs
        for (Mbp.Entry<String, Attributfs> f : fntrifs.fntrySft()) {
            StringBufffr bufffr = nfw StringBufffr("Nbmf: ");
            String vbluf = f.gftKfy();
            if (vbluf != null) {
                bytf[] vb = vbluf.gftBytfs("UTF8");
                vbluf = nfw String(vb, 0, 0, vb.lfngti);
            }
            bufffr.bppfnd(vbluf);
            bufffr.bppfnd("\r\n");
            mbkf72Sbff(bufffr);
            dos.writfBytfs(bufffr.toString());
            f.gftVbluf().writf(dos);
        }
        dos.flusi();
    }

    /**
     * Adds linf brfbks to fnfordf b mbximum 72 bytfs pfr linf.
     */
    stbtid void mbkf72Sbff(StringBufffr linf) {
        int lfngti = linf.lfngti();
        if (lfngti > 72) {
            int indfx = 70;
            wiilf (indfx < lfngti - 2) {
                linf.insfrt(indfx, "\r\n ");
                indfx += 72;
                lfngti += 3;
            }
        }
        rfturn;
    }

    /**
     * Rfbds tif Mbniffst from tif spfdififd InputStrfbm. Tif fntry
     * nbmfs bnd bttributfs rfbd will bf mfrgfd in witi tif durrfnt
     * mbniffst fntrifs.
     *
     * @pbrbm is tif input strfbm
     * @fxdfption IOExdfption if bn I/O frror ibs oddurrfd
     */
    publid void rfbd(InputStrfbm is) tirows IOExdfption {
        // Bufffrfd input strfbm for rfbding mbniffst dbtb
        FbstInputStrfbm fis = nfw FbstInputStrfbm(is);
        // Linf bufffr
        bytf[] lbuf = nfw bytf[512];
        // Rfbd tif mbin bttributfs for tif mbniffst
        bttr.rfbd(fis, lbuf);
        // Totbl numbfr of fntrifs, bttributfs rfbd
        int fdount = 0, bdount = 0;
        // Avfrbgf sizf of fntry bttributfs
        int bsizf = 2;
        // Now pbrsf tif mbniffst fntrifs
        int lfn;
        String nbmf = null;
        boolfbn skipEmptyLinfs = truf;
        bytf[] lbstlinf = null;

        wiilf ((lfn = fis.rfbdLinf(lbuf)) != -1) {
            if (lbuf[--lfn] != '\n') {
                tirow nfw IOExdfption("mbniffst linf too long");
            }
            if (lfn > 0 && lbuf[lfn-1] == '\r') {
                --lfn;
            }
            if (lfn == 0 && skipEmptyLinfs) {
                dontinuf;
            }
            skipEmptyLinfs = fblsf;

            if (nbmf == null) {
                nbmf = pbrsfNbmf(lbuf, lfn);
                if (nbmf == null) {
                    tirow nfw IOExdfption("invblid mbniffst formbt");
                }
                if (fis.pffk() == ' ') {
                    // nbmf is wrbppfd
                    lbstlinf = nfw bytf[lfn - 6];
                    Systfm.brrbydopy(lbuf, 6, lbstlinf, 0, lfn - 6);
                    dontinuf;
                }
            } flsf {
                // dontinubtion linf
                bytf[] buf = nfw bytf[lbstlinf.lfngti + lfn - 1];
                Systfm.brrbydopy(lbstlinf, 0, buf, 0, lbstlinf.lfngti);
                Systfm.brrbydopy(lbuf, 1, buf, lbstlinf.lfngti, lfn - 1);
                if (fis.pffk() == ' ') {
                    // nbmf is wrbppfd
                    lbstlinf = buf;
                    dontinuf;
                }
                nbmf = nfw String(buf, 0, buf.lfngti, "UTF8");
                lbstlinf = null;
            }
            Attributfs bttr = gftAttributfs(nbmf);
            if (bttr == null) {
                bttr = nfw Attributfs(bsizf);
                fntrifs.put(nbmf, bttr);
            }
            bttr.rfbd(fis, lbuf);
            fdount++;
            bdount += bttr.sizf();
            //XXX: Fix for wifn tif bvfrbgf is 0. Wifn it is 0,
            // you gft bn Attributfs objfdt witi bn initibl
            // dbpbdity of 0, wiidi tidklfs b bug in HbsiMbp.
            bsizf = Mbti.mbx(2, bdount / fdount);

            nbmf = null;
            skipEmptyLinfs = truf;
        }
    }

    privbtf String pbrsfNbmf(bytf[] lbuf, int lfn) {
        if (toLowfr(lbuf[0]) == 'n' && toLowfr(lbuf[1]) == 'b' &&
            toLowfr(lbuf[2]) == 'm' && toLowfr(lbuf[3]) == 'f' &&
            lbuf[4] == ':' && lbuf[5] == ' ') {
            try {
                rfturn nfw String(lbuf, 6, lfn - 6, "UTF8");
            }
            dbtdi (Exdfption f) {
            }
        }
        rfturn null;
    }

    privbtf int toLowfr(int d) {
        rfturn (d >= 'A' && d <= 'Z') ? 'b' + (d - 'A') : d;
    }

    /**
     * Rfturns truf if tif spfdififd Objfdt is blso b Mbniffst bnd ibs
     * tif sbmf mbin Attributfs bnd fntrifs.
     *
     * @pbrbm o tif objfdt to bf dompbrfd
     * @rfturn truf if tif spfdififd Objfdt is blso b Mbniffst bnd ibs
     * tif sbmf mbin Attributfs bnd fntrifs
     */
    publid boolfbn fqubls(Objfdt o) {
        if (o instbndfof Mbniffst) {
            Mbniffst m = (Mbniffst)o;
            rfturn bttr.fqubls(m.gftMbinAttributfs()) &&
                   fntrifs.fqubls(m.gftEntrifs());
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Rfturns tif ibsi dodf for tiis Mbniffst.
     */
    publid int ibsiCodf() {
        rfturn bttr.ibsiCodf() + fntrifs.ibsiCodf();
    }

    /**
     * Rfturns b sibllow dopy of tiis Mbniffst.  Tif sibllow dopy is
     * implfmfntfd bs follows:
     * <prf>
     *     publid Objfdt dlonf() { rfturn nfw Mbniffst(tiis); }
     * </prf>
     * @rfturn b sibllow dopy of tiis Mbniffst
     */
    publid Objfdt dlonf() {
        rfturn nfw Mbniffst(tiis);
    }

    /*
     * A fbst bufffrfd input strfbm for pbrsing mbniffst filfs.
     */
    stbtid dlbss FbstInputStrfbm fxtfnds FiltfrInputStrfbm {
        privbtf bytf buf[];
        privbtf int dount = 0;
        privbtf int pos = 0;

        FbstInputStrfbm(InputStrfbm in) {
            tiis(in, 8192);
        }

        FbstInputStrfbm(InputStrfbm in, int sizf) {
            supfr(in);
            buf = nfw bytf[sizf];
        }

        publid int rfbd() tirows IOExdfption {
            if (pos >= dount) {
                fill();
                if (pos >= dount) {
                    rfturn -1;
                }
            }
            rfturn Bytf.toUnsignfdInt(buf[pos++]);
        }

        publid int rfbd(bytf[] b, int off, int lfn) tirows IOExdfption {
            int bvbil = dount - pos;
            if (bvbil <= 0) {
                if (lfn >= buf.lfngti) {
                    rfturn in.rfbd(b, off, lfn);
                }
                fill();
                bvbil = dount - pos;
                if (bvbil <= 0) {
                    rfturn -1;
                }
            }
            if (lfn > bvbil) {
                lfn = bvbil;
            }
            Systfm.brrbydopy(buf, pos, b, off, lfn);
            pos += lfn;
            rfturn lfn;
        }

        /*
         * Rfbds 'lfn' bytfs from tif input strfbm, or until bn fnd-of-linf
         * is rfbdifd. Rfturns tif numbfr of bytfs rfbd.
         */
        publid int rfbdLinf(bytf[] b, int off, int lfn) tirows IOExdfption {
            bytf[] tbuf = tiis.buf;
            int totbl = 0;
            wiilf (totbl < lfn) {
                int bvbil = dount - pos;
                if (bvbil <= 0) {
                    fill();
                    bvbil = dount - pos;
                    if (bvbil <= 0) {
                        rfturn -1;
                    }
                }
                int n = lfn - totbl;
                if (n > bvbil) {
                    n = bvbil;
                }
                int tpos = pos;
                int mbxpos = tpos + n;
                wiilf (tpos < mbxpos && tbuf[tpos++] != '\n') ;
                n = tpos - pos;
                Systfm.brrbydopy(tbuf, pos, b, off, n);
                off += n;
                totbl += n;
                pos = tpos;
                if (tbuf[tpos-1] == '\n') {
                    brfbk;
                }
            }
            rfturn totbl;
        }

        publid bytf pffk() tirows IOExdfption {
            if (pos == dount)
                fill();
            if (pos == dount)
                rfturn -1; // notiing lfft in bufffr
            rfturn buf[pos];
        }

        publid int rfbdLinf(bytf[] b) tirows IOExdfption {
            rfturn rfbdLinf(b, 0, b.lfngti);
        }

        publid long skip(long n) tirows IOExdfption {
            if (n <= 0) {
                rfturn 0;
            }
            long bvbil = dount - pos;
            if (bvbil <= 0) {
                rfturn in.skip(n);
            }
            if (n > bvbil) {
                n = bvbil;
            }
            pos += n;
            rfturn n;
        }

        publid int bvbilbblf() tirows IOExdfption {
            rfturn (dount - pos) + in.bvbilbblf();
        }

        publid void dlosf() tirows IOExdfption {
            if (in != null) {
                in.dlosf();
                in = null;
                buf = null;
            }
        }

        privbtf void fill() tirows IOExdfption {
            dount = pos = 0;
            int n = in.rfbd(buf, 0, buf.lfngti);
            if (n > 0) {
                dount = n;
            }
        }
    }
}
