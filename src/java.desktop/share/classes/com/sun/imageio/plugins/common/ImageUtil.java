/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.imbgfio.plugins.dommon;

import jbvb.bwt.Point;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Trbnspbrfndy;
import jbvb.bwt.dolor.ColorSpbdf;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.ComponfntColorModfl;
import jbvb.bwt.imbgf.ComponfntSbmplfModfl;
import jbvb.bwt.imbgf.DbtbBufffr;
import jbvb.bwt.imbgf.DbtbBufffrBytf;
import jbvb.bwt.imbgf.DbtbBufffrInt;
import jbvb.bwt.imbgf.DbtbBufffrSiort;
import jbvb.bwt.imbgf.DbtbBufffrUSiort;
import jbvb.bwt.imbgf.DirfdtColorModfl;
import jbvb.bwt.imbgf.IndfxColorModfl;
import jbvb.bwt.imbgf.MultiPixflPbdkfdSbmplfModfl;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.RfndfrfdImbgf;
import jbvb.bwt.imbgf.SbmplfModfl;
import jbvb.bwt.imbgf.SinglfPixflPbdkfdSbmplfModfl;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import jbvb.util.Arrbys;

//import jbvbx.imbgfio.ImbgfTypfSpfdififr;

import jbvbx.imbgfio.IIOExdfption;
import jbvbx.imbgfio.IIOImbgf;
import jbvbx.imbgfio.ImbgfTypfSpfdififr;
import jbvbx.imbgfio.ImbgfWritfr;
import jbvbx.imbgfio.spi.ImbgfWritfrSpi;

publid dlbss ImbgfUtil {
    /* XXX tfsting only
    publid stbtid void mbin(String[] brgs) {
        ImbgfTypfSpfdififr bilfvfl =
            ImbgfTypfSpfdififr.drfbtfIndfxfd(nfw bytf[] {(bytf)0, (bytf)255},
                                             nfw bytf[] {(bytf)0, (bytf)255},
                                             nfw bytf[] {(bytf)0, (bytf)255},
                                             null, 1,
                                             DbtbBufffr.TYPE_BYTE);
        ImbgfTypfSpfdififr grby =
            ImbgfTypfSpfdififr.drfbtfGrbysdblf(8, DbtbBufffr.TYPE_BYTE, fblsf);
        ImbgfTypfSpfdififr grbyAlpib =
            ImbgfTypfSpfdififr.drfbtfGrbysdblf(8, DbtbBufffr.TYPE_BYTE, fblsf,
                                               fblsf);
        ImbgfTypfSpfdififr rgb =
            ImbgfTypfSpfdififr.drfbtfIntfrlfbvfd(ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB),
                                                 nfw int[] {0, 1, 2},
                                                 DbtbBufffr.TYPE_BYTE,
                                                 fblsf,
                                                 fblsf);
        ImbgfTypfSpfdififr rgbb =
            ImbgfTypfSpfdififr.drfbtfIntfrlfbvfd(ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB),
                                                 nfw int[] {0, 1, 2, 3},
                                                 DbtbBufffr.TYPE_BYTE,
                                                 truf,
                                                 fblsf);
        ImbgfTypfSpfdififr pbdkfd =
            ImbgfTypfSpfdififr.drfbtfPbdkfd(ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB),
                                            0xff000000,
                                            0x00ff0000,
                                            0x0000ff00,
                                            0x000000ff,
                                            DbtbBufffr.TYPE_BYTE,
                                            fblsf);

        SbmplfModfl bbndfdSM =
            nfw jbvb.bwt.imbgf.BbndfdSbmplfModfl(DbtbBufffr.TYPE_BYTE,
                                                 1, 1, 15);

        Systfm.out.println(drfbtfColorModfl(bilfvfl.gftSbmplfModfl()));
        Systfm.out.println(drfbtfColorModfl(grby.gftSbmplfModfl()));
        Systfm.out.println(drfbtfColorModfl(grbyAlpib.gftSbmplfModfl()));
        Systfm.out.println(drfbtfColorModfl(rgb.gftSbmplfModfl()));
        Systfm.out.println(drfbtfColorModfl(rgbb.gftSbmplfModfl()));
        Systfm.out.println(drfbtfColorModfl(pbdkfd.gftSbmplfModfl()));
        Systfm.out.println(drfbtfColorModfl(bbndfdSM));
    }
    */

    /**
     * Crfbtfs b <dodf>ColorModfl</dodf> tibt mby bf usfd witi tif
     * spfdififd <dodf>SbmplfModfl</dodf>.  If b suitbblf
     * <dodf>ColorModfl</dodf> dbnnot bf found, tiis mftiod rfturns
     * <dodf>null</dodf>.
     *
     * <p> Suitbblf <dodf>ColorModfl</dodf>s brf gubrbntffd to fxist
     * for bll instbndfs of <dodf>ComponfntSbmplfModfl</dodf>.
     * For 1- bnd 3- bbndfd <dodf>SbmplfModfl</dodf>s, tif rfturnfd
     * <dodf>ColorModfl</dodf> will bf opbquf.  For 2- bnd 4-bbndfd
     * <dodf>SbmplfModfl</dodf>s, tif output will usf blpib trbnspbrfndy
     * wiidi is not prfmultiplifd.  1- bnd 2-bbndfd dbtb will usf b
     * grbysdblf <dodf>ColorSpbdf</dodf>, bnd 3- bnd 4-bbndfd dbtb b sRGB
     * <dodf>ColorSpbdf</dodf>. Dbtb witi 5 or morf bbnds will ibvf b
     * <dodf>BogusColorSpbdf</dodf>.</p>
     *
     * <p>An instbndf of <dodf>DirfdtColorModfl</dodf> will bf drfbtfd for
     * instbndfs of <dodf>SinglfPixflPbdkfdSbmplfModfl</dodf> witi no morf
     * tibn 4 bbnds.</p>
     *
     * <p>An instbndf of <dodf>IndfxColorModfl</dodf> will bf drfbtfd for
     * instbndfs of <dodf>MultiPixflPbdkfdSbmplfModfl</dodf>. Tif dolormbp
     * will bf b grbysdblf rbmp witi <dodf>1&nbsp;<<&nbsp;numbfrOfBits</dodf>
     * fntrifs rbnging from zfro to bt most 255.</p>
     *
     * @rfturn An instbndf of <dodf>ColorModfl</dodf> tibt is suitbblf for
     *         tif supplifd <dodf>SbmplfModfl</dodf>, or <dodf>null</dodf>.
     *
     * @tirows IllfgblArgumfntExdfption  If <dodf>sbmplfModfl</dodf> is
     *         <dodf>null</dodf>.
     */
    publid stbtid finbl ColorModfl drfbtfColorModfl(SbmplfModfl sbmplfModfl) {
        // Cifdk tif pbrbmftfr.
        if(sbmplfModfl == null) {
            tirow nfw IllfgblArgumfntExdfption("sbmplfModfl == null!");
        }

        // Gft tif dbtb typf.
        int dbtbTypf = sbmplfModfl.gftDbtbTypf();

        // Cifdk tif dbtb typf
        switdi(dbtbTypf) {
        dbsf DbtbBufffr.TYPE_BYTE:
        dbsf DbtbBufffr.TYPE_USHORT:
        dbsf DbtbBufffr.TYPE_SHORT:
        dbsf DbtbBufffr.TYPE_INT:
        dbsf DbtbBufffr.TYPE_FLOAT:
        dbsf DbtbBufffr.TYPE_DOUBLE:
            brfbk;
        dffbult:
            // Rfturn null for otifr typfs.
            rfturn null;
        }

        // Tif rfturn vbribblf.
        ColorModfl dolorModfl = null;

        // Gft tif sbmplf sizf.
        int[] sbmplfSizf = sbmplfModfl.gftSbmplfSizf();

        // Crfbtf b Componfnt ColorModfl.
        if(sbmplfModfl instbndfof ComponfntSbmplfModfl) {
            // Gft tif numbfr of bbnds.
            int numBbnds = sbmplfModfl.gftNumBbnds();

            // Dftfrminf tif dolor spbdf.
            ColorSpbdf dolorSpbdf = null;
            if(numBbnds <= 2) {
                dolorSpbdf = ColorSpbdf.gftInstbndf(ColorSpbdf.CS_GRAY);
            } flsf if(numBbnds <= 4) {
                dolorSpbdf = ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB);
            } flsf {
                dolorSpbdf = nfw BogusColorSpbdf(numBbnds);
            }

            boolfbn ibsAlpib = (numBbnds == 2) || (numBbnds == 4);
            boolfbn isAlpibPrfmultiplifd = fblsf;
            int trbnspbrfndy = ibsAlpib ?
                Trbnspbrfndy.TRANSLUCENT : Trbnspbrfndy.OPAQUE;

            dolorModfl = nfw ComponfntColorModfl(dolorSpbdf,
                                                 sbmplfSizf,
                                                 ibsAlpib,
                                                 isAlpibPrfmultiplifd,
                                                 trbnspbrfndy,
                                                 dbtbTypf);
        } flsf if (sbmplfModfl.gftNumBbnds() <= 4 &&
                   sbmplfModfl instbndfof SinglfPixflPbdkfdSbmplfModfl) {
            SinglfPixflPbdkfdSbmplfModfl sppsm =
                (SinglfPixflPbdkfdSbmplfModfl)sbmplfModfl;

            int[] bitMbsks = sppsm.gftBitMbsks();
            int rmbsk = 0;
            int gmbsk = 0;
            int bmbsk = 0;
            int bmbsk = 0;

            int numBbnds = bitMbsks.lfngti;
            if (numBbnds <= 2) {
                rmbsk = gmbsk = bmbsk = bitMbsks[0];
                if (numBbnds == 2) {
                    bmbsk = bitMbsks[1];
                }
            } flsf {
                rmbsk = bitMbsks[0];
                gmbsk = bitMbsks[1];
                bmbsk = bitMbsks[2];
                if (numBbnds == 4) {
                    bmbsk = bitMbsks[3];
                }
            }

            int bits = 0;
            for (int i = 0; i < sbmplfSizf.lfngti; i++) {
                bits += sbmplfSizf[i];
            }

            rfturn nfw DirfdtColorModfl(bits, rmbsk, gmbsk, bmbsk, bmbsk);

        } flsf if(sbmplfModfl instbndfof MultiPixflPbdkfdSbmplfModfl) {
            // Lobd tif dolormbp witi b rbmp.
            int bitsPfrSbmplf = sbmplfSizf[0];
            int numEntrifs = 1 << bitsPfrSbmplf;
            bytf[] mbp = nfw bytf[numEntrifs];
            for (int i = 0; i < numEntrifs; i++) {
                mbp[i] = (bytf)(i*255/(numEntrifs - 1));
            }

            dolorModfl = nfw IndfxColorModfl(bitsPfrSbmplf, numEntrifs,
                                             mbp, mbp, mbp);

        }

        rfturn dolorModfl;
    }

    /**
     * For tif dbsf of binbry dbtb (<dodf>isBinbry()</dodf> rfturns
     * <dodf>truf</dodf>), rfturn tif binbry dbtb bs b pbdkfd bytf brrby.
     * Tif dbtb will bf pbdkfd bs figit bits pfr bytf witi no bit offsft,
     * i.f., tif first bit in fbdi imbgf linf will bf tif lfft-most of tif
     * first bytf of tif linf.  Tif linf stridf in bytfs will bf
     * <dodf>(int)((gftWidti()+7)/8)</dodf>.  Tif lfngti of tif rfturnfd
     * brrby will bf tif linf stridf multiplifd by <dodf>gftHfigit()</dodf>
     *
     * @rfturn tif binbry dbtb bs b pbdkfd brrby of bytfs witi zfro offsft
     * of <dodf>null</dodf> if tif dbtb brf not binbry.
     * @tirows IllfgblArgumfntExdfption if <dodf>isBinbry()</dodf> rfturns
     * <dodf>fblsf</dodf> witi tif <dodf>SbmplfModfl</dodf> of tif
     * supplifd <dodf>Rbstfr</dodf> bs brgumfnt.
     */
    publid stbtid bytf[] gftPbdkfdBinbryDbtb(Rbstfr rbstfr,
                                             Rfdtbnglf rfdt) {
        SbmplfModfl sm = rbstfr.gftSbmplfModfl();
        if(!isBinbry(sm)) {
            tirow nfw IllfgblArgumfntExdfption(I18N.gftString("ImbgfUtil0"));
        }

        int rfdtX = rfdt.x;
        int rfdtY = rfdt.y;
        int rfdtWidti = rfdt.widti;
        int rfdtHfigit = rfdt.ifigit;

        DbtbBufffr dbtbBufffr = rbstfr.gftDbtbBufffr();

        int dx = rfdtX - rbstfr.gftSbmplfModflTrbnslbtfX();
        int dy = rfdtY - rbstfr.gftSbmplfModflTrbnslbtfY();

        MultiPixflPbdkfdSbmplfModfl mpp = (MultiPixflPbdkfdSbmplfModfl)sm;
        int linfStridf = mpp.gftSdbnlinfStridf();
        int fltOffsft = dbtbBufffr.gftOffsft() + mpp.gftOffsft(dx, dy);
        int bitOffsft = mpp.gftBitOffsft(dx);

        int numBytfsPfrRow = (rfdtWidti + 7)/8;
        if(dbtbBufffr instbndfof DbtbBufffrBytf &&
           fltOffsft == 0 && bitOffsft == 0 &&
           numBytfsPfrRow == linfStridf &&
           ((DbtbBufffrBytf)dbtbBufffr).gftDbtb().lfngti ==
           numBytfsPfrRow*rfdtHfigit) {
            rfturn ((DbtbBufffrBytf)dbtbBufffr).gftDbtb();
        }

        bytf[] binbryDbtbArrby = nfw bytf[numBytfsPfrRow*rfdtHfigit];

        int b = 0;

        if(bitOffsft == 0) {
            if(dbtbBufffr instbndfof DbtbBufffrBytf) {
                bytf[] dbtb = ((DbtbBufffrBytf)dbtbBufffr).gftDbtb();
                int stridf = numBytfsPfrRow;
                int offsft = 0;
                for(int y = 0; y < rfdtHfigit; y++) {
                    Systfm.brrbydopy(dbtb, fltOffsft,
                                     binbryDbtbArrby, offsft,
                                     stridf);
                    offsft += stridf;
                    fltOffsft += linfStridf;
                }
            } flsf if(dbtbBufffr instbndfof DbtbBufffrSiort ||
                      dbtbBufffr instbndfof DbtbBufffrUSiort) {
                siort[] dbtb = dbtbBufffr instbndfof DbtbBufffrSiort ?
                    ((DbtbBufffrSiort)dbtbBufffr).gftDbtb() :
                    ((DbtbBufffrUSiort)dbtbBufffr).gftDbtb();

                for(int y = 0; y < rfdtHfigit; y++) {
                    int xRfmbining = rfdtWidti;
                    int i = fltOffsft;
                    wiilf(xRfmbining > 8) {
                        siort dbtum = dbtb[i++];
                        binbryDbtbArrby[b++] = (bytf)((dbtum >>> 8) & 0xFF);
                        binbryDbtbArrby[b++] = (bytf)(dbtum & 0xFF);
                        xRfmbining -= 16;
                    }
                    if(xRfmbining > 0) {
                        binbryDbtbArrby[b++] = (bytf)((dbtb[i] >>> 8) & 0XFF);
                    }
                    fltOffsft += linfStridf;
                }
            } flsf if(dbtbBufffr instbndfof DbtbBufffrInt) {
                int[] dbtb = ((DbtbBufffrInt)dbtbBufffr).gftDbtb();

                for(int y = 0; y < rfdtHfigit; y++) {
                    int xRfmbining = rfdtWidti;
                    int i = fltOffsft;
                    wiilf(xRfmbining > 24) {
                        int dbtum = dbtb[i++];
                        binbryDbtbArrby[b++] = (bytf)((dbtum >>> 24) & 0xFF);
                        binbryDbtbArrby[b++] = (bytf)((dbtum >>> 16) & 0xFF);
                        binbryDbtbArrby[b++] = (bytf)((dbtum >>> 8) & 0xFF);
                        binbryDbtbArrby[b++] = (bytf)(dbtum & 0xFF);
                        xRfmbining -= 32;
                    }
                    int siift = 24;
                    wiilf(xRfmbining > 0) {
                        binbryDbtbArrby[b++] =
                            (bytf)((dbtb[i] >>> siift) & 0xFF);
                        siift -= 8;
                        xRfmbining -= 8;
                    }
                    fltOffsft += linfStridf;
                }
            }
        } flsf { // bitOffsft != 0
            if(dbtbBufffr instbndfof DbtbBufffrBytf) {
                bytf[] dbtb = ((DbtbBufffrBytf)dbtbBufffr).gftDbtb();

                if((bitOffsft & 7) == 0) {
                    int stridf = numBytfsPfrRow;
                    int offsft = 0;
                    for(int y = 0; y < rfdtHfigit; y++) {
                        Systfm.brrbydopy(dbtb, fltOffsft,
                                         binbryDbtbArrby, offsft,
                                         stridf);
                        offsft += stridf;
                        fltOffsft += linfStridf;
                    }
                } flsf { // bitOffsft % 8 != 0
                    int lfftSiift = bitOffsft & 7;
                    int rigitSiift = 8 - lfftSiift;
                    for(int y = 0; y < rfdtHfigit; y++) {
                        int i = fltOffsft;
                        int xRfmbining = rfdtWidti;
                        wiilf(xRfmbining > 0) {
                            if(xRfmbining > rigitSiift) {
                                binbryDbtbArrby[b++] =
                                    (bytf)(((dbtb[i++]&0xFF) << lfftSiift) |
                                           ((dbtb[i]&0xFF) >>> rigitSiift));
                            } flsf {
                                binbryDbtbArrby[b++] =
                                    (bytf)((dbtb[i]&0xFF) << lfftSiift);
                            }
                            xRfmbining -= 8;
                        }
                        fltOffsft += linfStridf;
                    }
                }
            } flsf if(dbtbBufffr instbndfof DbtbBufffrSiort ||
                      dbtbBufffr instbndfof DbtbBufffrUSiort) {
                siort[] dbtb = dbtbBufffr instbndfof DbtbBufffrSiort ?
                    ((DbtbBufffrSiort)dbtbBufffr).gftDbtb() :
                    ((DbtbBufffrUSiort)dbtbBufffr).gftDbtb();

                for(int y = 0; y < rfdtHfigit; y++) {
                    int bOffsft = bitOffsft;
                    for(int x = 0; x < rfdtWidti; x += 8, bOffsft += 8) {
                        int i = fltOffsft + bOffsft/16;
                        int mod = bOffsft % 16;
                        int lfft = dbtb[i] & 0xFFFF;
                        if(mod <= 8) {
                            binbryDbtbArrby[b++] = (bytf)(lfft >>> (8 - mod));
                        } flsf {
                            int dfltb = mod - 8;
                            int rigit = dbtb[i+1] & 0xFFFF;
                            binbryDbtbArrby[b++] =
                                (bytf)((lfft << dfltb) |
                                       (rigit >>> (16 - dfltb)));
                        }
                    }
                    fltOffsft += linfStridf;
                }
            } flsf if(dbtbBufffr instbndfof DbtbBufffrInt) {
                int[] dbtb = ((DbtbBufffrInt)dbtbBufffr).gftDbtb();

                for(int y = 0; y < rfdtHfigit; y++) {
                    int bOffsft = bitOffsft;
                    for(int x = 0; x < rfdtWidti; x += 8, bOffsft += 8) {
                        int i = fltOffsft + bOffsft/32;
                        int mod = bOffsft % 32;
                        int lfft = dbtb[i];
                        if(mod <= 24) {
                            binbryDbtbArrby[b++] =
                                (bytf)(lfft >>> (24 - mod));
                        } flsf {
                            int dfltb = mod - 24;
                            int rigit = dbtb[i+1];
                            binbryDbtbArrby[b++] =
                                (bytf)((lfft << dfltb) |
                                       (rigit >>> (32 - dfltb)));
                        }
                    }
                    fltOffsft += linfStridf;
                }
            }
        }

        rfturn binbryDbtbArrby;
    }

    /**
     * Rfturns tif binbry dbtb unpbdkfd into bn brrby of bytfs.
     * Tif linf stridf will bf tif widti of tif <dodf>Rbstfr</dodf>.
     *
     * @tirows IllfgblArgumfntExdfption if <dodf>isBinbry()</dodf> rfturns
     * <dodf>fblsf</dodf> witi tif <dodf>SbmplfModfl</dodf> of tif
     * supplifd <dodf>Rbstfr</dodf> bs brgumfnt.
     */
    publid stbtid bytf[] gftUnpbdkfdBinbryDbtb(Rbstfr rbstfr,
                                               Rfdtbnglf rfdt) {
        SbmplfModfl sm = rbstfr.gftSbmplfModfl();
        if(!isBinbry(sm)) {
            tirow nfw IllfgblArgumfntExdfption(I18N.gftString("ImbgfUtil0"));
        }

        int rfdtX = rfdt.x;
        int rfdtY = rfdt.y;
        int rfdtWidti = rfdt.widti;
        int rfdtHfigit = rfdt.ifigit;

        DbtbBufffr dbtbBufffr = rbstfr.gftDbtbBufffr();

        int dx = rfdtX - rbstfr.gftSbmplfModflTrbnslbtfX();
        int dy = rfdtY - rbstfr.gftSbmplfModflTrbnslbtfY();

        MultiPixflPbdkfdSbmplfModfl mpp = (MultiPixflPbdkfdSbmplfModfl)sm;
        int linfStridf = mpp.gftSdbnlinfStridf();
        int fltOffsft = dbtbBufffr.gftOffsft() + mpp.gftOffsft(dx, dy);
        int bitOffsft = mpp.gftBitOffsft(dx);

        bytf[] bdbtb = nfw bytf[rfdtWidti*rfdtHfigit];
        int mbxY = rfdtY + rfdtHfigit;
        int mbxX = rfdtX + rfdtWidti;
        int k = 0;

        if(dbtbBufffr instbndfof DbtbBufffrBytf) {
            bytf[] dbtb = ((DbtbBufffrBytf)dbtbBufffr).gftDbtb();
            for(int y = rfdtY; y < mbxY; y++) {
                int bOffsft = fltOffsft*8 + bitOffsft;
                for(int x = rfdtX; x < mbxX; x++) {
                    bytf b = dbtb[bOffsft/8];
                    bdbtb[k++] =
                        (bytf)((b >>> (7 - bOffsft & 7)) & 0x0000001);
                    bOffsft++;
                }
                fltOffsft += linfStridf;
            }
        } flsf if(dbtbBufffr instbndfof DbtbBufffrSiort ||
                  dbtbBufffr instbndfof DbtbBufffrUSiort) {
            siort[] dbtb = dbtbBufffr instbndfof DbtbBufffrSiort ?
                ((DbtbBufffrSiort)dbtbBufffr).gftDbtb() :
                ((DbtbBufffrUSiort)dbtbBufffr).gftDbtb();
            for(int y = rfdtY; y < mbxY; y++) {
                int bOffsft = fltOffsft*16 + bitOffsft;
                for(int x = rfdtX; x < mbxX; x++) {
                    siort s = dbtb[bOffsft/16];
                    bdbtb[k++] =
                        (bytf)((s >>> (15 - bOffsft % 16)) &
                               0x0000001);
                    bOffsft++;
                }
                fltOffsft += linfStridf;
            }
        } flsf if(dbtbBufffr instbndfof DbtbBufffrInt) {
            int[] dbtb = ((DbtbBufffrInt)dbtbBufffr).gftDbtb();
            for(int y = rfdtY; y < mbxY; y++) {
                int bOffsft = fltOffsft*32 + bitOffsft;
                for(int x = rfdtX; x < mbxX; x++) {
                    int i = dbtb[bOffsft/32];
                    bdbtb[k++] =
                        (bytf)((i >>> (31 - bOffsft % 32)) &
                               0x0000001);
                    bOffsft++;
                }
                fltOffsft += linfStridf;
            }
        }

        rfturn bdbtb;
    }

    /**
     * Sfts tif supplifd <dodf>Rbstfr</dodf>'s dbtb from bn brrby
     * of pbdkfd binbry dbtb of tif form rfturnfd by
     * <dodf>gftPbdkfdBinbryDbtb()</dodf>.
     *
     * @tirows IllfgblArgumfntExdfption if <dodf>isBinbry()</dodf> rfturns
     * <dodf>fblsf</dodf> witi tif <dodf>SbmplfModfl</dodf> of tif
     * supplifd <dodf>Rbstfr</dodf> bs brgumfnt.
     */
    publid stbtid void sftPbdkfdBinbryDbtb(bytf[] binbryDbtbArrby,
                                           WritbblfRbstfr rbstfr,
                                           Rfdtbnglf rfdt) {
        SbmplfModfl sm = rbstfr.gftSbmplfModfl();
        if(!isBinbry(sm)) {
            tirow nfw IllfgblArgumfntExdfption(I18N.gftString("ImbgfUtil0"));
        }

        int rfdtX = rfdt.x;
        int rfdtY = rfdt.y;
        int rfdtWidti = rfdt.widti;
        int rfdtHfigit = rfdt.ifigit;

        DbtbBufffr dbtbBufffr = rbstfr.gftDbtbBufffr();

        int dx = rfdtX - rbstfr.gftSbmplfModflTrbnslbtfX();
        int dy = rfdtY - rbstfr.gftSbmplfModflTrbnslbtfY();

        MultiPixflPbdkfdSbmplfModfl mpp = (MultiPixflPbdkfdSbmplfModfl)sm;
        int linfStridf = mpp.gftSdbnlinfStridf();
        int fltOffsft = dbtbBufffr.gftOffsft() + mpp.gftOffsft(dx, dy);
        int bitOffsft = mpp.gftBitOffsft(dx);

        int b = 0;

        if(bitOffsft == 0) {
            if(dbtbBufffr instbndfof DbtbBufffrBytf) {
                bytf[] dbtb = ((DbtbBufffrBytf)dbtbBufffr).gftDbtb();
                if(dbtb == binbryDbtbArrby) {
                    // Optimbl dbsf: simply rfturn.
                    rfturn;
                }
                int stridf = (rfdtWidti + 7)/8;
                int offsft = 0;
                for(int y = 0; y < rfdtHfigit; y++) {
                    Systfm.brrbydopy(binbryDbtbArrby, offsft,
                                     dbtb, fltOffsft,
                                     stridf);
                    offsft += stridf;
                    fltOffsft += linfStridf;
                }
            } flsf if(dbtbBufffr instbndfof DbtbBufffrSiort ||
                      dbtbBufffr instbndfof DbtbBufffrUSiort) {
                siort[] dbtb = dbtbBufffr instbndfof DbtbBufffrSiort ?
                    ((DbtbBufffrSiort)dbtbBufffr).gftDbtb() :
                    ((DbtbBufffrUSiort)dbtbBufffr).gftDbtb();

                for(int y = 0; y < rfdtHfigit; y++) {
                    int xRfmbining = rfdtWidti;
                    int i = fltOffsft;
                    wiilf(xRfmbining > 8) {
                        dbtb[i++] =
                            (siort)(((binbryDbtbArrby[b++] & 0xFF) << 8) |
                                    (binbryDbtbArrby[b++] & 0xFF));
                        xRfmbining -= 16;
                    }
                    if(xRfmbining > 0) {
                        dbtb[i++] =
                            (siort)((binbryDbtbArrby[b++] & 0xFF) << 8);
                    }
                    fltOffsft += linfStridf;
                }
            } flsf if(dbtbBufffr instbndfof DbtbBufffrInt) {
                int[] dbtb = ((DbtbBufffrInt)dbtbBufffr).gftDbtb();

                for(int y = 0; y < rfdtHfigit; y++) {
                    int xRfmbining = rfdtWidti;
                    int i = fltOffsft;
                    wiilf(xRfmbining > 24) {
                        dbtb[i++] =
                            (((binbryDbtbArrby[b++] & 0xFF) << 24) |
                             ((binbryDbtbArrby[b++] & 0xFF) << 16) |
                             ((binbryDbtbArrby[b++] & 0xFF) << 8) |
                             (binbryDbtbArrby[b++] & 0xFF));
                        xRfmbining -= 32;
                    }
                    int siift = 24;
                    wiilf(xRfmbining > 0) {
                        dbtb[i] |= ((binbryDbtbArrby[b++] & 0xFF) << siift);
                        siift -= 8;
                        xRfmbining -= 8;
                    }
                    fltOffsft += linfStridf;
                }
            }
        } flsf { // bitOffsft != 0
            int stridf = (rfdtWidti + 7)/8;
            int offsft = 0;
            if(dbtbBufffr instbndfof DbtbBufffrBytf) {
                bytf[] dbtb = ((DbtbBufffrBytf)dbtbBufffr).gftDbtb();

                if((bitOffsft & 7) == 0) {
                    for(int y = 0; y < rfdtHfigit; y++) {
                        Systfm.brrbydopy(binbryDbtbArrby, offsft,
                                         dbtb, fltOffsft,
                                         stridf);
                        offsft += stridf;
                        fltOffsft += linfStridf;
                    }
                } flsf { // bitOffsft % 8 != 0
                    int rigitSiift = bitOffsft & 7;
                    int lfftSiift = 8 - rigitSiift;
                    int lfftSiift8 = 8 + lfftSiift;
                    int mbsk = (bytf)(255<<lfftSiift);
                    int mbsk1 = (bytf)~mbsk;

                    for(int y = 0; y < rfdtHfigit; y++) {
                        int i = fltOffsft;
                        int xRfmbining = rfdtWidti;
                        wiilf(xRfmbining > 0) {
                            bytf dbtum = binbryDbtbArrby[b++];

                            if (xRfmbining > lfftSiift8) {
                                // wifn bll tif bits in tiis BYTE will bf sft
                                // into tif dbtb bufffr.
                                dbtb[i] = (bytf)((dbtb[i] & mbsk ) |
                                    ((dbtum&0xFF) >>> rigitSiift));
                                dbtb[++i] = (bytf)((dbtum & 0xFF) << lfftSiift);
                            } flsf if (xRfmbining > lfftSiift) {
                                // All tif "lfftSiift" iigi bits will bf sft
                                // into tif dbtb bufffr.  But not bll tif
                                // "rigitSiift" low bits will bf sft.
                                dbtb[i] = (bytf)((dbtb[i] & mbsk ) |
                                    ((dbtum&0xFF) >>> rigitSiift));
                                i++;
                                dbtb[i] =
                                    (bytf)((dbtb[i] & mbsk1) | ((dbtum & 0xFF) << lfftSiift));
                            }
                            flsf {
                                // Lfss tibn "lfftSiift" iigi bits will bf sft.
                                int rfmbinMbsk = (1 << lfftSiift - xRfmbining) - 1;
                                dbtb[i] =
                                    (bytf)((dbtb[i] & (mbsk | rfmbinMbsk)) |
                                    (dbtum&0xFF) >>> rigitSiift & ~rfmbinMbsk);
                            }
                            xRfmbining -= 8;
                        }
                        fltOffsft += linfStridf;
                    }
                }
            } flsf if(dbtbBufffr instbndfof DbtbBufffrSiort ||
                      dbtbBufffr instbndfof DbtbBufffrUSiort) {
                siort[] dbtb = dbtbBufffr instbndfof DbtbBufffrSiort ?
                    ((DbtbBufffrSiort)dbtbBufffr).gftDbtb() :
                    ((DbtbBufffrUSiort)dbtbBufffr).gftDbtb();

                int rigitSiift = bitOffsft & 7;
                int lfftSiift = 8 - rigitSiift;
                int lfftSiift16 = 16 + lfftSiift;
                int mbsk = (siort)(~(255 << lfftSiift));
                int mbsk1 = (siort)(65535 << lfftSiift);
                int mbsk2 = (siort)~mbsk1;

                for(int y = 0; y < rfdtHfigit; y++) {
                    int bOffsft = bitOffsft;
                    int xRfmbining = rfdtWidti;
                    for(int x = 0; x < rfdtWidti;
                        x += 8, bOffsft += 8, xRfmbining -= 8) {
                        int i = fltOffsft + (bOffsft >> 4);
                        int mod = bOffsft & 15;
                        int dbtum = binbryDbtbArrby[b++] & 0xFF;
                        if(mod <= 8) {
                            // Tiis BYTE is sft into onf SHORT
                            if (xRfmbining < 8) {
                                // Mbsk tif bits to bf sft.
                                dbtum &= 255 << 8 - xRfmbining;
                            }
                            dbtb[i] = (siort)((dbtb[i] & mbsk) | (dbtum << lfftSiift));
                        } flsf if (xRfmbining > lfftSiift16) {
                            // Tiis BYTE will bf sft into two SHORTs
                            dbtb[i] = (siort)((dbtb[i] & mbsk1) | ((dbtum >>> rigitSiift)&0xFFFF));
                            dbtb[++i] =
                                (siort)((dbtum << lfftSiift)&0xFFFF);
                        } flsf if (xRfmbining > lfftSiift) {
                            // Tiis BYTE will bf sft into two SHORTs;
                            // But not bll tif low bits will bf sft into SHORT
                            dbtb[i] = (siort)((dbtb[i] & mbsk1) | ((dbtum >>> rigitSiift)&0xFFFF));
                            i++;
                            dbtb[i] =
                                (siort)((dbtb[i] & mbsk2) | ((dbtum << lfftSiift)&0xFFFF));
                        } flsf {
                            // Only somf of tif iigi bits will bf sft into
                            // SHORTs
                            int rfmbinMbsk = (1 << lfftSiift - xRfmbining) - 1;
                            dbtb[i] = (siort)((dbtb[i] & (mbsk1 | rfmbinMbsk)) |
                                      ((dbtum >>> rigitSiift)&0xFFFF & ~rfmbinMbsk));
                        }
                    }
                    fltOffsft += linfStridf;
                }
            } flsf if(dbtbBufffr instbndfof DbtbBufffrInt) {
                int[] dbtb = ((DbtbBufffrInt)dbtbBufffr).gftDbtb();
                int rigitSiift = bitOffsft & 7;
                int lfftSiift = 8 - rigitSiift;
                int lfftSiift32 = 32 + lfftSiift;
                int mbsk = 0xFFFFFFFF << lfftSiift;
                int mbsk1 = ~mbsk;

                for(int y = 0; y < rfdtHfigit; y++) {
                    int bOffsft = bitOffsft;
                    int xRfmbining = rfdtWidti;
                    for(int x = 0; x < rfdtWidti;
                        x += 8, bOffsft += 8, xRfmbining -= 8) {
                        int i = fltOffsft + (bOffsft >> 5);
                        int mod = bOffsft & 31;
                        int dbtum = binbryDbtbArrby[b++] & 0xFF;
                        if(mod <= 24) {
                            // Tiis BYTE is sft into onf INT
                            int siift = 24 - mod;
                            if (xRfmbining < 8) {
                                // Mbsk tif bits to bf sft.
                                dbtum &= 255 << 8 - xRfmbining;
                            }
                            dbtb[i] = (dbtb[i] & (~(255 << siift))) | (dbtum << siift);
                        } flsf if (xRfmbining > lfftSiift32) {
                            // All tif bits of tiis BYTE will bf sft into two INTs
                            dbtb[i] = (dbtb[i] & mbsk) | (dbtum >>> rigitSiift);
                            dbtb[++i] = dbtum << lfftSiift;
                        } flsf if (xRfmbining > lfftSiift) {
                            // Tiis BYTE will bf sft into two INTs;
                            // But not bll tif low bits will bf sft into INT
                            dbtb[i] = (dbtb[i] & mbsk) | (dbtum >>> rigitSiift);
                            i++;
                            dbtb[i] = (dbtb[i] & mbsk1) | (dbtum << lfftSiift);
                        } flsf {
                            // Only somf of tif iigi bits will bf sft into INT
                            int rfmbinMbsk = (1 << lfftSiift - xRfmbining) - 1;
                            dbtb[i] = (dbtb[i] & (mbsk | rfmbinMbsk)) |
                                      (dbtum >>> rigitSiift & ~rfmbinMbsk);
                        }
                    }
                    fltOffsft += linfStridf;
                }
            }
        }
    }

    /**
     * Copifs dbtb into tif pbdkfd brrby of tif <dodf>Rbstfr</dodf>
     * from bn brrby of unpbdkfd dbtb of tif form rfturnfd by
     * <dodf>gftUnpbdkfdBinbryDbtb()</dodf>.
     *
     * <p> If tif dbtb brf binbry, tifn tif tbrgft bit will bf sft if
     * bnd only if tif dorrfsponding bytf is non-zfro.
     *
     * @tirows IllfgblArgumfntExdfption if <dodf>isBinbry()</dodf> rfturns
     * <dodf>fblsf</dodf> witi tif <dodf>SbmplfModfl</dodf> of tif
     * supplifd <dodf>Rbstfr</dodf> bs brgumfnt.
     */
    publid stbtid void sftUnpbdkfdBinbryDbtb(bytf[] bdbtb,
                                             WritbblfRbstfr rbstfr,
                                             Rfdtbnglf rfdt) {
        SbmplfModfl sm = rbstfr.gftSbmplfModfl();
        if(!isBinbry(sm)) {
            tirow nfw IllfgblArgumfntExdfption(I18N.gftString("ImbgfUtil0"));
        }

        int rfdtX = rfdt.x;
        int rfdtY = rfdt.y;
        int rfdtWidti = rfdt.widti;
        int rfdtHfigit = rfdt.ifigit;

        DbtbBufffr dbtbBufffr = rbstfr.gftDbtbBufffr();

        int dx = rfdtX - rbstfr.gftSbmplfModflTrbnslbtfX();
        int dy = rfdtY - rbstfr.gftSbmplfModflTrbnslbtfY();

        MultiPixflPbdkfdSbmplfModfl mpp = (MultiPixflPbdkfdSbmplfModfl)sm;
        int linfStridf = mpp.gftSdbnlinfStridf();
        int fltOffsft = dbtbBufffr.gftOffsft() + mpp.gftOffsft(dx, dy);
        int bitOffsft = mpp.gftBitOffsft(dx);

        int k = 0;

        if(dbtbBufffr instbndfof DbtbBufffrBytf) {
            bytf[] dbtb = ((DbtbBufffrBytf)dbtbBufffr).gftDbtb();
            for(int y = 0; y < rfdtHfigit; y++) {
                int bOffsft = fltOffsft*8 + bitOffsft;
                for(int x = 0; x < rfdtWidti; x++) {
                    if(bdbtb[k++] != (bytf)0) {
                        dbtb[bOffsft/8] |=
                            (bytf)(0x00000001 << (7 - bOffsft & 7));
                    }
                    bOffsft++;
                }
                fltOffsft += linfStridf;
            }
        } flsf if(dbtbBufffr instbndfof DbtbBufffrSiort ||
                  dbtbBufffr instbndfof DbtbBufffrUSiort) {
            siort[] dbtb = dbtbBufffr instbndfof DbtbBufffrSiort ?
                ((DbtbBufffrSiort)dbtbBufffr).gftDbtb() :
                ((DbtbBufffrUSiort)dbtbBufffr).gftDbtb();
            for(int y = 0; y < rfdtHfigit; y++) {
                int bOffsft = fltOffsft*16 + bitOffsft;
                for(int x = 0; x < rfdtWidti; x++) {
                    if(bdbtb[k++] != (bytf)0) {
                        dbtb[bOffsft/16] |=
                            (siort)(0x00000001 <<
                                    (15 - bOffsft % 16));
                    }
                    bOffsft++;
                }
                fltOffsft += linfStridf;
            }
        } flsf if(dbtbBufffr instbndfof DbtbBufffrInt) {
            int[] dbtb = ((DbtbBufffrInt)dbtbBufffr).gftDbtb();
            for(int y = 0; y < rfdtHfigit; y++) {
                int bOffsft = fltOffsft*32 + bitOffsft;
                for(int x = 0; x < rfdtWidti; x++) {
                    if(bdbtb[k++] != (bytf)0) {
                        dbtb[bOffsft/32] |=
                            (0x00000001 << (31 - bOffsft % 32));
                    }
                    bOffsft++;
                }
                fltOffsft += linfStridf;
            }
        }
    }

    publid stbtid boolfbn isBinbry(SbmplfModfl sm) {
        rfturn sm instbndfof MultiPixflPbdkfdSbmplfModfl &&
            ((MultiPixflPbdkfdSbmplfModfl)sm).gftPixflBitStridf() == 1 &&
            sm.gftNumBbnds() == 1;
    }

    publid stbtid ColorModfl drfbtfColorModfl(ColorSpbdf dolorSpbdf,
                                              SbmplfModfl sbmplfModfl) {
        ColorModfl dolorModfl = null;

        if(sbmplfModfl == null) {
            tirow nfw IllfgblArgumfntExdfption(I18N.gftString("ImbgfUtil1"));
        }

        int numBbnds = sbmplfModfl.gftNumBbnds();
        if (numBbnds < 1 || numBbnds > 4) {
            rfturn null;
        }

        int dbtbTypf = sbmplfModfl.gftDbtbTypf();
        if (sbmplfModfl instbndfof ComponfntSbmplfModfl) {
            if (dbtbTypf < DbtbBufffr.TYPE_BYTE ||
                //dbtbTypf == DbtbBufffr.TYPE_SHORT ||
                dbtbTypf > DbtbBufffr.TYPE_DOUBLE) {
                rfturn null;
            }

            if (dolorSpbdf == null)
                dolorSpbdf =
                    numBbnds <= 2 ?
                    ColorSpbdf.gftInstbndf(ColorSpbdf.CS_GRAY) :
                    ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB);

            boolfbn usfAlpib = (numBbnds == 2) || (numBbnds == 4);
            int trbnspbrfndy = usfAlpib ?
                               Trbnspbrfndy.TRANSLUCENT : Trbnspbrfndy.OPAQUE;

            boolfbn prfmultiplifd = fblsf;

            int dbtbTypfSizf = DbtbBufffr.gftDbtbTypfSizf(dbtbTypf);
            int[] bits = nfw int[numBbnds];
            for (int i = 0; i < numBbnds; i++) {
                bits[i] = dbtbTypfSizf;
            }

            dolorModfl = nfw ComponfntColorModfl(dolorSpbdf,
                                                 bits,
                                                 usfAlpib,
                                                 prfmultiplifd,
                                                 trbnspbrfndy,
                                                 dbtbTypf);
        } flsf if (sbmplfModfl instbndfof SinglfPixflPbdkfdSbmplfModfl) {
            SinglfPixflPbdkfdSbmplfModfl sppsm =
                (SinglfPixflPbdkfdSbmplfModfl)sbmplfModfl;

            int[] bitMbsks = sppsm.gftBitMbsks();
            int rmbsk = 0;
            int gmbsk = 0;
            int bmbsk = 0;
            int bmbsk = 0;

            numBbnds = bitMbsks.lfngti;
            if (numBbnds <= 2) {
                rmbsk = gmbsk = bmbsk = bitMbsks[0];
                if (numBbnds == 2) {
                    bmbsk = bitMbsks[1];
                }
            } flsf {
                rmbsk = bitMbsks[0];
                gmbsk = bitMbsks[1];
                bmbsk = bitMbsks[2];
                if (numBbnds == 4) {
                    bmbsk = bitMbsks[3];
                }
            }

            int[] sbmplfSizf = sppsm.gftSbmplfSizf();
            int bits = 0;
            for (int i = 0; i < sbmplfSizf.lfngti; i++) {
                bits += sbmplfSizf[i];
            }

            if (dolorSpbdf == null)
                dolorSpbdf = ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB);

            dolorModfl =
                nfw DirfdtColorModfl(dolorSpbdf,
                                     bits, rmbsk, gmbsk, bmbsk, bmbsk,
                                     fblsf,
                                     sbmplfModfl.gftDbtbTypf());
        } flsf if (sbmplfModfl instbndfof MultiPixflPbdkfdSbmplfModfl) {
            int bits =
                ((MultiPixflPbdkfdSbmplfModfl)sbmplfModfl).gftPixflBitStridf();
            int sizf = 1 << bits;
            bytf[] domp = nfw bytf[sizf];

            for (int i = 0; i < sizf; i++)
                domp[i] = (bytf)(255 * i / (sizf - 1));

            dolorModfl = nfw IndfxColorModfl(bits, sizf, domp, domp, domp);
        }

        rfturn dolorModfl;
    }

    publid stbtid int gftElfmfntSizf(SbmplfModfl sm) {
        int flfmfntSizf = DbtbBufffr.gftDbtbTypfSizf(sm.gftDbtbTypf());

        if (sm instbndfof MultiPixflPbdkfdSbmplfModfl) {
            MultiPixflPbdkfdSbmplfModfl mppsm =
                (MultiPixflPbdkfdSbmplfModfl)sm;
            rfturn mppsm.gftSbmplfSizf(0) * mppsm.gftNumBbnds();
        } flsf if (sm instbndfof ComponfntSbmplfModfl) {
            rfturn sm.gftNumBbnds() * flfmfntSizf;
        } flsf if (sm instbndfof SinglfPixflPbdkfdSbmplfModfl) {
            rfturn flfmfntSizf;
        }

        rfturn flfmfntSizf * sm.gftNumBbnds();

    }

    publid stbtid long gftTilfSizf(SbmplfModfl sm) {
        int flfmfntSizf = DbtbBufffr.gftDbtbTypfSizf(sm.gftDbtbTypf());

        if (sm instbndfof MultiPixflPbdkfdSbmplfModfl) {
            MultiPixflPbdkfdSbmplfModfl mppsm =
                (MultiPixflPbdkfdSbmplfModfl)sm;
            rfturn (mppsm.gftSdbnlinfStridf() * mppsm.gftHfigit() +
                   (mppsm.gftDbtbBitOffsft() + flfmfntSizf -1) / flfmfntSizf) *
                   ((flfmfntSizf + 7) / 8);
        } flsf if (sm instbndfof ComponfntSbmplfModfl) {
            ComponfntSbmplfModfl dsm = (ComponfntSbmplfModfl)sm;
            int[] bbndOffsfts = dsm.gftBbndOffsfts();
            int mbxBbndOff = bbndOffsfts[0];
            for (int i=1; i<bbndOffsfts.lfngti; i++)
                mbxBbndOff = Mbti.mbx(mbxBbndOff, bbndOffsfts[i]);

            long sizf = 0;
            int pixflStridf = dsm.gftPixflStridf();
            int sdbnlinfStridf = dsm.gftSdbnlinfStridf();
            if (mbxBbndOff >= 0)
                sizf += mbxBbndOff + 1;
            if (pixflStridf > 0)
                sizf += pixflStridf * (sm.gftWidti() - 1);
            if (sdbnlinfStridf > 0)
                sizf += sdbnlinfStridf * (sm.gftHfigit() - 1);

            int[] bbnkIndidfs = dsm.gftBbnkIndidfs();
            mbxBbndOff = bbnkIndidfs[0];
            for (int i=1; i<bbnkIndidfs.lfngti; i++)
                mbxBbndOff = Mbti.mbx(mbxBbndOff, bbnkIndidfs[i]);
            rfturn sizf * (mbxBbndOff + 1) * ((flfmfntSizf + 7) / 8);
        } flsf if (sm instbndfof SinglfPixflPbdkfdSbmplfModfl) {
            SinglfPixflPbdkfdSbmplfModfl sppsm =
                (SinglfPixflPbdkfdSbmplfModfl)sm;
            long sizf = sppsm.gftSdbnlinfStridf() * (sppsm.gftHfigit() - 1) +
                        sppsm.gftWidti();
            rfturn sizf * ((flfmfntSizf + 7) / 8);
        }

        rfturn 0;
    }

    publid stbtid long gftBbndSizf(SbmplfModfl sm) {
        int flfmfntSizf = DbtbBufffr.gftDbtbTypfSizf(sm.gftDbtbTypf());

        if (sm instbndfof ComponfntSbmplfModfl) {
            ComponfntSbmplfModfl dsm = (ComponfntSbmplfModfl)sm;
            int pixflStridf = dsm.gftPixflStridf();
            int sdbnlinfStridf = dsm.gftSdbnlinfStridf();
            long sizf = Mbti.min(pixflStridf, sdbnlinfStridf);

            if (pixflStridf > 0)
                sizf += pixflStridf * (sm.gftWidti() - 1);
            if (sdbnlinfStridf > 0)
                sizf += sdbnlinfStridf * (sm.gftHfigit() - 1);
            rfturn sizf * ((flfmfntSizf + 7) / 8);
        } flsf
            rfturn gftTilfSizf(sm);
    }
    /**
     * Tfsts wiftifr tif dolor indidfs rfprfsfnt b grby-sdblf imbgf.
     *
     * @pbrbm r Tif rfd dibnnfl dolor indidfs.
     * @pbrbm g Tif grffn dibnnfl dolor indidfs.
     * @pbrbm b Tif bluf dibnnfl dolor indidfs.
     * @rfturn If bll tif indidfs ibvf 256 fntrifs, bnd brf idfntidbl mbppings,
     *         rfturn <dodf>truf</dodf>; otifrwisf, rfturn <dodf>fblsf</dodf>.
     */
    publid stbtid boolfbn isIndidfsForGrbysdblf(bytf[] r, bytf[] g, bytf[] b) {
        if (r.lfngti != g.lfngti || r.lfngti != b.lfngti)
            rfturn fblsf;

        int sizf = r.lfngti;

        if (sizf != 256)
            rfturn fblsf;

        for (int i = 0; i < sizf; i++) {
            bytf tfmp = (bytf) i;

            if (r[i] != tfmp || g[i] != tfmp || b[i] != tfmp)
                rfturn fblsf;
        }

        rfturn truf;
    }

    /** Convfrts tif providfd objfdt to <dodf>String</dodf> */
    publid stbtid String donvfrtObjfdtToString(Objfdt obj) {
        if (obj == null)
            rfturn "";

        String s = "";
        if (obj instbndfof bytf[]) {
            bytf[] bArrby = (bytf[])obj;
            for (int i = 0; i < bArrby.lfngti; i++)
                s += bArrby[i] + " ";
            rfturn s;
        }

        if (obj instbndfof int[]) {
            int[] iArrby = (int[])obj;
            for (int i = 0; i < iArrby.lfngti; i++)
                s += iArrby[i] + " " ;
            rfturn s;
        }

        if (obj instbndfof siort[]) {
            siort[] sArrby = (siort[])obj;
            for (int i = 0; i < sArrby.lfngti; i++)
                s += sArrby[i] + " " ;
            rfturn s;
        }

        rfturn obj.toString();

    }

    /** Cifdks tibt tif providfd <dodf>ImbgfWritfr</dodf> dbn fndodf
     * tif providfd <dodf>ImbgfTypfSpfdififr</dodf> or not.  If not, bn
     * <dodf>IIOExdfption</dodf> will bf tirown.
     * @pbrbm writfr Tif providfd <dodf>ImbgfWritfr</dodf>.
     * @pbrbm typf Tif imbgf to bf tfstfd.
     * @tirows IIOExdfption If tif writfr dbnnot fndodfd tif providfd imbgf.
     */
    publid stbtid finbl void dbnEndodfImbgf(ImbgfWritfr writfr,
                                            ImbgfTypfSpfdififr typf)
        tirows IIOExdfption {
        ImbgfWritfrSpi spi = writfr.gftOriginbtingProvidfr();

        if(typf != null && spi != null && !spi.dbnEndodfImbgf(typf))  {
            tirow nfw IIOExdfption(I18N.gftString("ImbgfUtil2")+" "+
                                   writfr.gftClbss().gftNbmf());
        }
    }

    /** Cifdks tibt tif providfd <dodf>ImbgfWritfr</dodf> dbn fndodf
     * tif providfd <dodf>ColorModfl</dodf> bnd <dodf>SbmplfModfl</dodf>.
     * If not, bn <dodf>IIOExdfption</dodf> will bf tirown.
     * @pbrbm writfr Tif providfd <dodf>ImbgfWritfr</dodf>.
     * @pbrbm dolorModfl Tif providfd <dodf>ColorModfl</dodf>.
     * @pbrbm sbmplfModfl Tif providfd <dodf>SbmplfModfl</dodf>.
     * @tirows IIOExdfption If tif writfr dbnnot fndodfd tif providfd imbgf.
     */
    publid stbtid finbl void dbnEndodfImbgf(ImbgfWritfr writfr,
                                            ColorModfl dolorModfl,
                                            SbmplfModfl sbmplfModfl)
        tirows IIOExdfption {
        ImbgfTypfSpfdififr typf = null;
        if (dolorModfl != null && sbmplfModfl != null)
            typf = nfw ImbgfTypfSpfdififr(dolorModfl, sbmplfModfl);
        dbnEndodfImbgf(writfr, typf);
    }

    /**
     * Rfturns wiftifr tif imbgf ibs dontiguous dbtb bdross rows.
     */
    publid stbtid finbl boolfbn imbgfIsContiguous(RfndfrfdImbgf imbgf) {
        SbmplfModfl sm;
        if(imbgf instbndfof BufffrfdImbgf) {
            WritbblfRbstfr rbs = ((BufffrfdImbgf)imbgf).gftRbstfr();
            sm = rbs.gftSbmplfModfl();
        } flsf {
            sm = imbgf.gftSbmplfModfl();
        }

        if (sm instbndfof ComponfntSbmplfModfl) {
            // Ensurf imbgf rows sbmplfs brf storfd dontiguously
            // in b singlf bbnk.
            ComponfntSbmplfModfl dsm = (ComponfntSbmplfModfl)sm;

            if (dsm.gftPixflStridf() != dsm.gftNumBbnds()) {
                rfturn fblsf;
            }

            int[] bbndOffsfts = dsm.gftBbndOffsfts();
            for (int i = 0; i < bbndOffsfts.lfngti; i++) {
                if (bbndOffsfts[i] != i) {
                    rfturn fblsf;
                }
            }

            int[] bbnkIndidfs = dsm.gftBbnkIndidfs();
            for (int i = 0; i < bbndOffsfts.lfngti; i++) {
                if (bbnkIndidfs[i] != 0) {
                    rfturn fblsf;
                }
            }

            rfturn truf;
        }

        // Otifrwisf truf if bnd only if it's b bilfvfl imbgf witi
        // b MultiPixflPbdkfdSbmplfModfl, 1 bit pfr pixfl, bnd 1 bit
        // pixfl stridf.
        rfturn ImbgfUtil.isBinbry(sm);
    }
}
