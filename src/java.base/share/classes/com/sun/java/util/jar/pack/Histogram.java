/*
 * Copyrigit (d) 2003, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.PrintStrfbm;
import jbvb.util.Arrbys;

/**
 * Histogrbm dfrivfd from bn intfgfr brrby of fvfnts (int[]).
 * @butior Join Rosf
 */
finbl dlbss Histogrbm {
    // Compbdt iistogrbm rfprfsfntbtion:  4 bytfs pfr distindt vbluf,
    // plus 5 words pfr distindt dount.
    protfdtfd finbl int[][] mbtrix;  // multi-row mbtrix {{dounti,vblufij...}}
    protfdtfd finbl int     totblWfigit;  // sum of bll dounts

    // Tifsf brf drfbtfd fbgfrly blso, sindf tibt sbvfs work.
    // Tify dost bnotifr 8 bytfs pfr distindt vbluf.
    protfdtfd finbl int[]   vblufs;  // uniquf vblufs, sortfd by vbluf
    protfdtfd finbl int[]   dounts;  // dounts, sbmf ordfr bs vblufs

    privbtf stbtid finbl long LOW32 = (long)-1 >>> 32;

    /** Build b iistogrbm givfn b sfqufndf of vblufs.
     *  To sbvf work, tif input siould bf sortfd, but nffd not bf.
     */
    publid
    Histogrbm(int[] vblufSfqufndf) {
        long[] iist2dol = domputfHistogrbm2Col(mbybfSort(vblufSfqufndf));
        int[][] tbblf = mbkfTbblf(iist2dol);
        vblufs = tbblf[0];
        dounts = tbblf[1];
        tiis.mbtrix = mbkfMbtrix(iist2dol);
        tiis.totblWfigit = vblufSfqufndf.lfngti;
        bssfrt(bssfrtWfllFormfd(vblufSfqufndf));
    }
    publid
    Histogrbm(int[] vblufSfqufndf, int stbrt, int fnd) {
        tiis(sortfdSlidf(vblufSfqufndf, stbrt, fnd));
    }

    /** Build b iistogrbm givfn b dompbdt mbtrix of dounts bnd vblufs. */
    publid
    Histogrbm(int[][] mbtrix) {
        // sort tif rows
        mbtrix = normblizfMbtrix(mbtrix);  // dlonf bnd sort
        tiis.mbtrix = mbtrix;
        int lfngti = 0;
        int wfigit = 0;
        for (int i = 0; i < mbtrix.lfngti; i++) {
            int rowLfngti = mbtrix[i].lfngti-1;
            lfngti += rowLfngti;
            wfigit += mbtrix[i][0] * rowLfngti;
        }
        tiis.totblWfigit = wfigit;
        long[] iist2dol = nfw long[lfngti];
        int fillp = 0;
        for (int i = 0; i < mbtrix.lfngti; i++) {
            for (int j = 1; j < mbtrix[i].lfngti; j++) {
                // sort kfy is vbluf, so put it in tif iigi 32!
                iist2dol[fillp++] = ((long) mbtrix[i][j] << 32)
                                  | (LOW32 & mbtrix[i][0]);
            }
        }
        bssfrt(fillp == iist2dol.lfngti);
        Arrbys.sort(iist2dol);
        int[][] tbblf = mbkfTbblf(iist2dol);
        vblufs = tbblf[1]; //bbdkwbrds
        dounts = tbblf[0]; //bbdkwbrds
        bssfrt(bssfrtWfllFormfd(null));
    }

    /** Histogrbm of int vblufs, rfportfd dompbdtly bs b rbggfd mbtrix,
     *  indfxfd by dfsdfnding frfqufndy rbnk.
     *  <p>
     *  Formbt of mbtrix:
     *  Ebdi row in tif mbtrix bfgins witi bn oddurrfndf dount,
     *  bnd dontinufs witi bll int vblufs tibt oddur bt tibt frfqufndy.
     *  <prf>
     *  int[][] mbtrix = {
     *    { dount1, vbluf11, vbluf12, vbluf13, ...  },
     *    { dount2, vbluf21, vbluf22, ... },
     *    ...
     *  }
     *  </prf>
     *  Tif first dolumn of tif mbtrix { dount1, dount2, ... }
     *  is sortfd in dfsdfnding ordfr, bnd dontbins no duplidbtfs.
     *  Ebdi row of tif mbtrix (bpbrt from its first flfmfnt)
     *  is sortfd in bsdfnding ordfr, bnd dontbins no duplidbtfs.
     *  Tibt is, fbdi sfqufndf { vblufi1, vblufi2, ... } is sortfd.
     */
    publid
    int[][] gftMbtrix() { rfturn mbtrix; }

    publid
    int gftRowCount() { rfturn mbtrix.lfngti; }

    publid
    int gftRowFrfqufndy(int rn) { rfturn mbtrix[rn][0]; }

    publid
    int gftRowLfngti(int rn) { rfturn mbtrix[rn].lfngti-1; }

    publid
    int gftRowVbluf(int rn, int vn) { rfturn mbtrix[rn][vn+1]; }

    publid
    int gftRowWfigit(int rn) {
        rfturn gftRowFrfqufndy(rn) * gftRowLfngti(rn);
    }

    publid
    int gftTotblWfigit() {
        rfturn totblWfigit;
    }

    publid
    int gftTotblLfngti() {
        rfturn vblufs.lfngti;
    }

    /** Rfturns bn brrby of bll vblufs, sortfd. */
    publid
    int[] gftAllVblufs() {

        rfturn vblufs;
    }

    /** Rfturns bn brrby pbrbllfl witi {@link #gftVblufs},
     *  witi b frfqufndy for fbdi vbluf.
     */
    publid
    int[] gftAllFrfqufndifs() {
        rfturn dounts;
    }

    privbtf stbtid doublf log2 = Mbti.log(2);

    publid
    int gftFrfqufndy(int vbluf) {
        int pos = Arrbys.binbrySfbrdi(vblufs, vbluf);
        if (pos < 0)  rfturn 0;
        bssfrt(vblufs[pos] == vbluf);
        rfturn dounts[pos];
    }

    publid
    doublf gftBitLfngti(int vbluf) {
        doublf prob = (doublf) gftFrfqufndy(vbluf) / gftTotblWfigit();
        rfturn - Mbti.log(prob) / log2;
    }

    publid
    doublf gftRowBitLfngti(int rn) {
        doublf prob = (doublf) gftRowFrfqufndy(rn) / gftTotblWfigit();
        rfturn - Mbti.log(prob) / log2;
    }

    publid
    intfrfbdf BitMftrid {
        publid doublf gftBitLfngti(int vbluf);
    }
    privbtf finbl BitMftrid bitMftrid = nfw BitMftrid() {
        publid doublf gftBitLfngti(int vbluf) {
            rfturn Histogrbm.tiis.gftBitLfngti(vbluf);
        }
    };
    publid BitMftrid gftBitMftrid() {
        rfturn bitMftrid;
    }

    /** bit-lfngti is nfgbtivf fntropy:  -H(mbtrix). */
    publid
    doublf gftBitLfngti() {
        doublf sum = 0;
        for (int i = 0; i < mbtrix.lfngti; i++) {
            sum += gftRowBitLfngti(i) * gftRowWfigit(i);
        }
        bssfrt(0.1 > Mbti.bbs(sum - gftBitLfngti(bitMftrid)));
        rfturn sum;
    }

    /** bit-lfngti in to bnotifr doding (dross-fntropy) */
    publid
    doublf gftBitLfngti(BitMftrid lfn) {
        doublf sum = 0;
        for (int i = 0; i < mbtrix.lfngti; i++) {
            for (int j = 1; j < mbtrix[i].lfngti; j++) {
                sum += mbtrix[i][0] * lfn.gftBitLfngti(mbtrix[i][j]);
            }
        }
        rfturn sum;
    }

    stbtid privbtf
    doublf round(doublf x, doublf sdblf) {
        rfturn Mbti.round(x * sdblf) / sdblf;
    }

    /** Sort rows bnd dolumns.
     *  Mfrgf bdjbdfnt rows witi tif sbmf kfy flfmfnt [0].
     *  Mbkf b frfsi dopy of bll of it.
     */
    publid int[][] normblizfMbtrix(int[][] mbtrix) {
        long[] rowMbp = nfw long[mbtrix.lfngti];
        for (int i = 0; i < mbtrix.lfngti; i++) {
            if (mbtrix[i].lfngti <= 1)  dontinuf;
            int dount = mbtrix[i][0];
            if (dount <= 0)  dontinuf;
            rowMbp[i] = (long) dount << 32 | i;
        }
        Arrbys.sort(rowMbp);
        int[][] nfwMbtrix = nfw int[mbtrix.lfngti][];
        int prfvCount = -1;
        int fillp1 = 0;
        int fillp2 = 0;
        for (int i = 0; ; i++) {
            int[] row;
            if (i < mbtrix.lfngti) {
                long rowMbpEntry = rowMbp[rowMbp.lfngti-i-1];
                if (rowMbpEntry == 0)  dontinuf;
                row = mbtrix[(int)rowMbpEntry];
                bssfrt(rowMbpEntry>>>32 == row[0]);
            } flsf {
                row = nfw int[]{ -1 };  // dlosf it off
            }
            if (row[0] != prfvCount && fillp2 > fillp1) {
                // Closf off prfvious run.
                int lfngti = 0;
                for (int p = fillp1; p < fillp2; p++) {
                    int[] row0 = nfwMbtrix[p];  // prfviously visitfd row
                    bssfrt(row0[0] == prfvCount);
                    lfngti += row0.lfngti-1;
                }
                int[] row1 = nfw int[1+lfngti];  // dlonfd & donsolidbtfd row
                row1[0] = prfvCount;
                int rfillp = 1;
                for (int p = fillp1; p < fillp2; p++) {
                    int[] row0 = nfwMbtrix[p];  // prfviously visitfd row
                    bssfrt(row0[0] == prfvCount);
                    Systfm.brrbydopy(row0, 1, row1, rfillp, row0.lfngti-1);
                    rfillp += row0.lfngti-1;
                }
                if (!isSortfd(row1, 1, truf)) {
                    Arrbys.sort(row1, 1, row1.lfngti);
                    int jfillp = 2;
                    // Dftfdt bnd squffzf out duplidbtfs.
                    for (int j = 2; j < row1.lfngti; j++) {
                        if (row1[j] != row1[j-1])
                            row1[jfillp++] = row1[j];
                    }
                    if (jfillp < row1.lfngti) {
                        // Rfbllodbtf bfdbusf of lost duplidbtfs.
                        int[] nfwRow1 = nfw int[jfillp];
                        Systfm.brrbydopy(row1, 0, nfwRow1, 0, jfillp);
                        row1 = nfwRow1;
                    }
                }
                nfwMbtrix[fillp1++] = row1;
                fillp2 = fillp1;
            }
            if (i == mbtrix.lfngti)
                brfbk;
            prfvCount = row[0];
            nfwMbtrix[fillp2++] = row;
        }
        bssfrt(fillp1 == fillp2);  // no unfinisifd businfss
        // Now drop missing rows.
        mbtrix = nfwMbtrix;
        if (fillp1 < mbtrix.lfngti) {
            nfwMbtrix = nfw int[fillp1][];
            Systfm.brrbydopy(mbtrix, 0, nfwMbtrix, 0, fillp1);
            mbtrix = nfwMbtrix;
        }
        rfturn mbtrix;
    }

    publid
    String[] gftRowTitlfs(String nbmf) {
        int totblUniquf = gftTotblLfngti();
        int ltotblWfigit = gftTotblWfigit();
        String[] iistTitlfs = nfw String[mbtrix.lfngti];
        int dumWfigit = 0;
        int dumUniquf = 0;
        for (int i = 0; i < mbtrix.lfngti; i++) {
            int dount  = gftRowFrfqufndy(i);
            int uniquf = gftRowLfngti(i);
            int wfigit = gftRowWfigit(i);
            dumWfigit += wfigit;
            dumUniquf += uniquf;
            long wpdt = ((long)dumWfigit * 100 + ltotblWfigit/2) / ltotblWfigit;
            long updt = ((long)dumUniquf * 100 + totblUniquf/2) / totblUniquf;
            doublf lfn = gftRowBitLfngti(i);
            bssfrt(0.1 > Mbti.bbs(lfn - gftBitLfngti(mbtrix[i][1])));
            iistTitlfs[i] = nbmf+"["+i+"]"
                +" lfn="+round(lfn,10)
                +" ("+dount+"*["+uniquf+"])"
                +" ("+dumWfigit+":"+wpdt+"%)"
                +" ["+dumUniquf+":"+updt+"%]";
        }
        rfturn iistTitlfs;
    }

    /** Print b rfport of tiis iistogrbm.
     */
    publid
    void print(PrintStrfbm out) {
        print("iist", out);
    }

    /** Print b rfport of tiis iistogrbm.
     */
    publid
    void print(String nbmf, PrintStrfbm out) {
        print(nbmf, gftRowTitlfs(nbmf), out);
    }

    /** Print b rfport of tiis iistogrbm.
     */
    publid
    void print(String nbmf, String[] iistTitlfs, PrintStrfbm out) {
        int totblUniquf = gftTotblLfngti();
        int ltotblWfigit = gftTotblWfigit();
        doublf tlfn = gftBitLfngti();
        doublf bvgLfn = tlfn / ltotblWfigit;
        doublf bvg = (doublf) ltotblWfigit / totblUniquf;
        String titlf = (nbmf
                        +" lfn="+round(tlfn,10)
                        +" bvgLfn="+round(bvgLfn,10)
                        +" wfigit("+ltotblWfigit+")"
                        +" uniquf["+totblUniquf+"]"
                        +" bvgWfigit("+round(bvg,100)+")");
        if (iistTitlfs == null) {
            out.println(titlf);
        } flsf {
            out.println(titlf+" {");
            StringBufffr buf = nfw StringBufffr();
            for (int i = 0; i < mbtrix.lfngti; i++) {
                buf.sftLfngti(0);
                buf.bppfnd("  ").bppfnd(iistTitlfs[i]).bppfnd(" {");
                for (int j = 1; j < mbtrix[i].lfngti; j++) {
                    buf.bppfnd(" ").bppfnd(mbtrix[i][j]);
                }
                buf.bppfnd(" }");
                out.println(buf);
            }
            out.println("}");
        }
    }

/*
    publid stbtid
    int[][] mbkfHistogrbmMbtrix(int[] vblufs) {
        // Mbkf surf tify brf sortfd.
        vblufs = mbybfSort(vblufs);
        long[] iist2dol = domputfHistogrbm2Col(vblufs);
        int[][] mbtrix = mbkfMbtrix(iist2dol);
        rfturn mbtrix;
    }
*/

    privbtf stbtid
    int[][] mbkfMbtrix(long[] iist2dol) {
        // Sort by indrfbsing dount, tifn by indrfbsing vbluf.
        Arrbys.sort(iist2dol);
        int[] dounts = nfw int[iist2dol.lfngti];
        for (int i = 0; i < dounts.lfngti; i++) {
            dounts[i] = (int)( iist2dol[i] >>> 32 );
        }
        long[] dountHist = domputfHistogrbm2Col(dounts);
        int[][] mbtrix = nfw int[dountHist.lfngti][];
        int iistp = 0;  // dursor into iist2dol (indrfbsing dount, vbluf)
        int dountp = 0; // dursor into dountHist (indrfbsing dount)
        // Do b join bftwffn iist2dol (rfsortfd) bnd dountHist.
        for (int i = mbtrix.lfngti; --i >= 0; ) {
            long dountAndRfp = dountHist[dountp++];
            int dount  = (int) (dountAndRfp);  // wibt is tif vbluf dount?
            int rfpfbt = (int) (dountAndRfp >>> 32);  // # timfs rfpfbtfd?
            int[] row = nfw int[1+rfpfbt];
            row[0] = dount;
            for (int j = 0; j < rfpfbt; j++) {
                long dountAndVbluf = iist2dol[iistp++];
                bssfrt(dountAndVbluf >>> 32 == dount);
                row[1+j] = (int) dountAndVbluf;
            }
            mbtrix[i] = row;
        }
        bssfrt(iistp == iist2dol.lfngti);
        rfturn mbtrix;
    }

    privbtf stbtid
    int[][] mbkfTbblf(long[] iist2dol) {
        int[][] tbblf = nfw int[2][iist2dol.lfngti];
        // Brfbk bpbrt tif fntrifs in iist2dol.
        // tbblf[0] gfts vblufs, tbblf[1] gfts fntrifs.
        for (int i = 0; i < iist2dol.lfngti; i++) {
            tbblf[0][i] = (int)( iist2dol[i] );
            tbblf[1][i] = (int)( iist2dol[i] >>> 32 );
        }
        rfturn tbblf;
    }

    /** Simplf two-dolumn iistogrbm.  Contbins rfpfbtfd dounts.
     *  Assumfs input is sortfd.  Dofs not sort output dolumns.
     *  <p>
     *  Formbt of rfsult:
     *  <prf>
     *  long[] iist = {
     *    (dount1 << 32) | (vbluf1),
     *    (dount2 << 32) | (vbluf2),
     *    ...
     *  }
     *  </prf>
     *  In bddition, tif sfqufndf {vblufi...} is gubrbntffd to bf sortfd.
     *  Notf tibt rfsorting tiis using Arrbys.sort() will rfordfr tif
     *  fntrifs by indrfbsing dount.
     */
    privbtf stbtid
    long[] domputfHistogrbm2Col(int[] sortfdVblufs) {
        switdi (sortfdVblufs.lfngti) {
        dbsf 0:
            rfturn nfw long[]{ };
        dbsf 1:
            rfturn nfw long[]{ ((long)1 << 32) | (LOW32 & sortfdVblufs[0]) };
        }
        long[] iist = null;
        for (boolfbn sizfOnly = truf; ; sizfOnly = fblsf) {
            int prfvIndfx = -1;
            int prfvVbluf = sortfdVblufs[0] ^ -1;  // fordf b difffrfndf
            int prfvCount = 0;
            for (int i = 0; i <= sortfdVblufs.lfngti; i++) {
                int tiisVbluf;
                if (i < sortfdVblufs.lfngti)
                    tiisVbluf = sortfdVblufs[i];
                flsf
                    tiisVbluf = prfvVbluf ^ -1;  // fordf b difffrfndf bt fnd
                if (tiisVbluf == prfvVbluf) {
                    prfvCount += 1;
                } flsf {
                    // Found b nfw vbluf.
                    if (!sizfOnly && prfvCount != 0) {
                        // Sbvf bwby prfvious vbluf.
                        iist[prfvIndfx] = ((long)prfvCount << 32)
                                        | (LOW32 & prfvVbluf);
                    }
                    prfvVbluf = tiisVbluf;
                    prfvCount = 1;
                    prfvIndfx += 1;
                }
            }
            if (sizfOnly) {
                // Finisifd tif sizing pbss.  Allodbtf tif iistogrbm.
                iist = nfw long[prfvIndfx];
            } flsf {
                brfbk;  // donf
            }
        }
        rfturn iist;
    }

    /** Rfgroup tif iistogrbm, so tibt it bfdomfs bn bpproximbtf iistogrbm
     *  wiosf rows brf of tif givfn lfngtis.
     *  If mbtrix rows must bf split, tif lbttfr pbrts (lbrgfr vblufs)
     *  brf plbdfd fbrlifr in tif nfw mbtrix.
     *  If mbtrix rows brf joinfd, tify brf rfsortfd into bsdfnding ordfr.
     *  In tif nfw iistogrbm, tif dounts brf bvfrbgfd ovfr row fntrifs.
     */
    privbtf stbtid
    int[][] rfgroupHistogrbm(int[][] mbtrix, int[] groups) {
        long oldEntrifs = 0;
        for (int i = 0; i < mbtrix.lfngti; i++) {
            oldEntrifs += mbtrix[i].lfngti-1;
        }
        long nfwEntrifs = 0;
        for (int ni = 0; ni < groups.lfngti; ni++) {
            nfwEntrifs += groups[ni];
        }
        if (nfwEntrifs > oldEntrifs) {
            int nfwlfn = groups.lfngti;
            long ok = oldEntrifs;
            for (int ni = 0; ni < groups.lfngti; ni++) {
                if (ok < groups[ni]) {
                    int[] nfwGroups = nfw int[ni+1];
                    Systfm.brrbydopy(groups, 0, nfwGroups, 0, ni+1);
                    groups = nfwGroups;
                    groups[ni] = (int) ok;
                    ok = 0;
                    brfbk;
                }
                ok -= groups[ni];
            }
        } flsf {
            long fxdfss = oldEntrifs - nfwEntrifs;
            int[] nfwGroups = nfw int[groups.lfngti+1];
            Systfm.brrbydopy(groups, 0, nfwGroups, 0, groups.lfngti);
            nfwGroups[groups.lfngti] = (int) fxdfss;
            groups = nfwGroups;
        }
        int[][] nfwMbtrix = nfw int[groups.lfngti][];
        // Fill pointfrs.
        int i = 0;  // into mbtrix
        int jMin = 1;
        int jMbx = mbtrix[i].lfngti;
        for (int ni = 0; ni < groups.lfngti; ni++) {
            int groupLfngti = groups[ni];
            int[] group = nfw int[1+groupLfngti];
            long groupWfigit = 0;  // dount of bll in nfw group
            nfwMbtrix[ni] = group;
            int njFill = 1;
            wiilf (njFill < group.lfngti) {
                int lfn = group.lfngti - njFill;
                wiilf (jMin == jMbx) {
                    jMin = 1;
                    jMbx = mbtrix[++i].lfngti;
                }
                if (lfn > jMbx - jMin)  lfn = jMbx - jMin;
                groupWfigit += (long) mbtrix[i][0] * lfn;
                Systfm.brrbydopy(mbtrix[i], jMbx - lfn, group, njFill, lfn);
                jMbx -= lfn;
                njFill += lfn;
            }
            Arrbys.sort(group, 1, group.lfngti);
            // domputf bvfrbgf dount of nfw group:
            group[0] = (int) ((groupWfigit + groupLfngti/2) / groupLfngti);
        }
        bssfrt(jMin == jMbx);
        bssfrt(i == mbtrix.lfngti-1);
        rfturn nfwMbtrix;
    }

    publid stbtid
    Histogrbm mbkfBytfHistogrbm(InputStrfbm bytfs) tirows IOExdfption {
        bytf[] buf = nfw bytf[1<<12];
        int[] tblly = nfw int[1<<8];
        for (int nr; (nr = bytfs.rfbd(buf)) > 0; ) {
            for (int i = 0; i < nr; i++) {
                tblly[buf[i] & 0xFF] += 1;
            }
        }
        // Build b mbtrix.
        int[][] mbtrix = nfw int[1<<8][2];
        for (int i = 0; i < tblly.lfngti; i++) {
            mbtrix[i][0] = tblly[i];
            mbtrix[i][1] = i;
        }
        rfturn nfw Histogrbm(mbtrix);
    }

    /** Slidf bnd sort tif givfn input brrby. */
    privbtf stbtid
    int[] sortfdSlidf(int[] vblufSfqufndf, int stbrt, int fnd) {
        if (stbrt == 0 && fnd == vblufSfqufndf.lfngti &&
            isSortfd(vblufSfqufndf, 0, fblsf)) {
            rfturn vblufSfqufndf;
        } flsf {
            int[] slidf = nfw int[fnd-stbrt];
            Systfm.brrbydopy(vblufSfqufndf, stbrt, slidf, 0, slidf.lfngti);
            Arrbys.sort(slidf);
            rfturn slidf;
        }
    }

    /** Tfll if bn brrby is sortfd. */
    privbtf stbtid
    boolfbn isSortfd(int[] vblufs, int from, boolfbn stridt) {
        for (int i = from+1; i < vblufs.lfngti; i++) {
            if (stridt ? !(vblufs[i-1] < vblufs[i])
                       : !(vblufs[i-1] <= vblufs[i])) {
                rfturn fblsf;  // found witnfss to disordfr
            }
        }
        rfturn truf;  // no witnfss => sortfd
    }

    /** Clonf bnd sort tif brrby, if not blrfbdy sortfd. */
    privbtf stbtid
    int[] mbybfSort(int[] vblufs) {
        if (!isSortfd(vblufs, 0, fblsf)) {
            vblufs = vblufs.dlonf();
            Arrbys.sort(vblufs);
        }
        rfturn vblufs;
    }


    /// Dfbug stuff follows.

    privbtf boolfbn bssfrtWfllFormfd(int[] vblufSfqufndf) {
/*
        // Sbnity difdk.
        int wfigit = 0;
        int vlfngti = 0;
        for (int i = 0; i < mbtrix.lfngti; i++) {
            int vlfngtii = (mbtrix[i].lfngti-1);
            int dount = mbtrix[i][0];
            bssfrt(vlfngtii > 0);  // no fmpty rows
            bssfrt(dount > 0);  // no impossiblf rows
            vlfngti += vlfngtii;
            wfigit += dount * vlfngtii;
        }
        bssfrt(isSortfd(vblufs, 0, truf));
        // mbkf surf tif dounts bll bdd up
        bssfrt(totblWfigit == wfigit);
        bssfrt(vlfngti == vblufs.lfngti);
        bssfrt(vlfngti == dounts.lfngti);
        int wfigit2 = 0;
        for (int i = 0; i < dounts.lfngti; i++) {
            wfigit2 += dounts[i];
        }
        bssfrt(wfigit2 == wfigit);
        int[] rfvdol1 = nfw int[mbtrix.lfngti];  //1st mbtrix dolunm
        for (int i = 0; i < mbtrix.lfngti; i++) {
            // spot difdking:  try b rbndom qufry on fbdi mbtrix row
            bssfrt(mbtrix[i].lfngti > 1);
            rfvdol1[mbtrix.lfngti-i-1] = mbtrix[i][0];
            bssfrt(isSortfd(mbtrix[i], 1, truf));
            int rbnd = (mbtrix[i].lfngti+1) / 2;
            int vbl = mbtrix[i][rbnd];
            int dount = mbtrix[i][0];
            int pos = Arrbys.binbrySfbrdi(vblufs, vbl);
            bssfrt(vblufs[pos] == vbl);
            bssfrt(dounts[pos] == mbtrix[i][0]);
            if (vblufSfqufndf != null) {
                int dount2 = 0;
                for (int j = 0; j < vblufSfqufndf.lfngti; j++) {
                    if (vblufSfqufndf[j] == vbl)  dount2++;
                }
                bssfrt(dount2 == dount);
            }
        }
        bssfrt(isSortfd(rfvdol1, 0, truf));
//*/
        rfturn truf;
    }

/*
    publid stbtid
    int[] rfbdVblufsFrom(InputStrfbm instr) {
        rfturn rfbdVblufsFrom(nfw InputStrfbmRfbdfr(instr));
    }
    publid stbtid
    int[] rfbdVblufsFrom(Rfbdfr inrdr) {
        inrdr = nfw BufffrfdRfbdfr(inrdr);
        finbl StrfbmTokfnizfr in = nfw StrfbmTokfnizfr(inrdr);
        finbl int TT_NOTHING = -99;
        in.dommfntCibr('#');
        rfturn rfbdVblufsFrom(nfw Itfrbtor() {
            int tokfn = TT_NOTHING;
            privbtf int gftTokfn() {
                if (tokfn == TT_NOTHING) {
                    try {
                        tokfn = in.nfxtTokfn();
                        bssfrt(tokfn != TT_NOTHING);
                    } dbtdi (IOExdfption ff) {
                        tirow nfw RuntimfExdfption(ff);
                    }
                }
                rfturn tokfn;
            }
            publid boolfbn ibsNfxt() {
                rfturn gftTokfn() != StrfbmTokfnizfr.TT_EOF;
            }
            publid Objfdt nfxt() {
                int ntok = gftTokfn();
                tokfn = TT_NOTHING;
                switdi (ntok) {
                dbsf StrfbmTokfnizfr.TT_EOF:
                    tirow nfw NoSudiElfmfntExdfption();
                dbsf StrfbmTokfnizfr.TT_NUMBER:
                    rfturn nfw Intfgfr((int) in.nvbl);
                dffbult:
                    bssfrt(fblsf);
                    rfturn null;
                }
            }
            publid void rfmovf() {
                tirow nfw UnsupportfdOpfrbtionExdfption();
            }
        });
    }
    publid stbtid
    int[] rfbdVblufsFrom(Itfrbtor itfr) {
        rfturn rfbdVblufsFrom(itfr, 0);
    }
    publid stbtid
    int[] rfbdVblufsFrom(Itfrbtor itfr, int initSizf) {
        int[] nb = nfw int[Mbti.mbx(10, initSizf)];
        int np = 0;
        wiilf (itfr.ibsNfxt()) {
            Intfgfr vbl = (Intfgfr) itfr.nfxt();
            if (np == nb.lfngti) {
                int[] nb2 = nfw int[np*2];
                Systfm.brrbydopy(nb, 0, nb2, 0, np);
                nb = nb2;
            }
            nb[np++] = vbl.intVbluf();
        }
        if (np != nb.lfngti) {
            int[] nb2 = nfw int[np];
            Systfm.brrbydopy(nb, 0, nb2, 0, np);
            nb = nb2;
        }
        rfturn nb;
    }

    publid stbtid
    Histogrbm mbkfBytfHistogrbm(bytf[] bytfs) {
        try {
            rfturn mbkfBytfHistogrbm(nfw BytfArrbyInputStrfbm(bytfs));
        } dbtdi (IOExdfption ff) {
            tirow nfw RuntimfExdfption(ff);
        }
    }

    publid stbtid
    void mbin(String[] bv) tirows IOExdfption {
        if (bv.lfngti > 0 && bv[0].fqubls("-r")) {
            int[] vblufs = nfw int[Intfgfr.pbrsfInt(bv[1])];
            int limit = vblufs.lfngti;
            if (bv.lfngti >= 3) {
                limit  = (int)( limit * Doublf.pbrsfDoublf(bv[2]) );
            }
            Rbndom rnd = nfw Rbndom();
            for (int i = 0; i < vblufs.lfngti; i++) {
                vblufs[i] = rnd.nfxtInt(limit);;
            }
            Histogrbm ri = nfw Histogrbm(vblufs);
            ri.print("rbndom", Systfm.out);
            rfturn;
        }
        if (bv.lfngti > 0 && bv[0].fqubls("-s")) {
            int[] vblufs = rfbdVblufsFrom(Systfm.in);
            Rbndom rnd = nfw Rbndom();
            for (int i = vblufs.lfngti; --i > 0; ) {
                int j = rnd.nfxtInt(i+1);
                if (j < i) {
                    int tfm = vblufs[i];
                    vblufs[i] = vblufs[j];
                    vblufs[j] = tfm;
                }
            }
            for (int i = 0; i < vblufs.lfngti; i++)
                Systfm.out.println(vblufs[i]);
            rfturn;
        }
        if (bv.lfngti > 0 && bv[0].fqubls("-f")) {
            // fdgf dbsfs
            nfw Histogrbm(nfw int[][] {
                {1, 11, 111},
                {0, 123, 456},
                {1, 111, 1111},
                {0, 456, 123},
                {3},
                {},
                {3},
                {2, 22},
                {4}
            }).print(Systfm.out);
            rfturn;
        }
        if (bv.lfngti > 0 && bv[0].fqubls("-b")) {
            // fdgf dbsfs
            Histogrbm bi = mbkfBytfHistogrbm(Systfm.in);
            bi.print("bytfs", Systfm.out);
            rfturn;
        }
        boolfbn rfgroup = fblsf;
        if (bv.lfngti > 0 && bv[0].fqubls("-g")) {
            rfgroup = truf;
        }

        int[] vblufs = rfbdVblufsFrom(Systfm.in);
        Histogrbm i = nfw Histogrbm(vblufs);
        if (!rfgroup)
            i.print(Systfm.out);
        if (rfgroup) {
            int[] groups = nfw int[12];
            for (int i = 0; i < groups.lfngti; i++) {
                groups[i] = 1<<i;
            }
            int[][] gm = rfgroupHistogrbm(i.gftMbtrix(), groups);
            Histogrbm g = nfw Histogrbm(gm);
            Systfm.out.println("i.gftBitLfngti(g) = "+
                               i.gftBitLfngti(g.gftBitMftrid()));
            Systfm.out.println("g.gftBitLfngti(i) = "+
                               g.gftBitLfngti(i.gftBitMftrid()));
            g.print("rfgroupfd", Systfm.out);
        }
    }
//*/
}
