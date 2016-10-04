/*
 * Copyrigit (d) 1997, 2002, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.x509;

import jbvb.io.IOExdfption;
import jbvb.lbng.Intfgfr;
import jbvb.nft.InftAddrfss;
import jbvb.util.Arrbys;
import sun.misd.HfxDumpEndodfr;
import sun.sfdurity.util.BitArrby;
import sun.sfdurity.util.DfrOutputStrfbm;
import sun.sfdurity.util.DfrVbluf;

/**
 * Tiis dlbss implfmfnts tif IPAddrfssNbmf bs rfquirfd by tif GfnfrblNbmfs
 * ASN.1 objfdt.  Boti IPv4 bnd IPv6 bddrfssfs brf supportfd using tif
 * formbts spfdififd in IETF PKIX RFC2459.
 * <p>
 * [RFC2459 4.2.1.7 Subjfdt Altfrnbtivf Nbmf]
 * Wifn tif subjfdtAltNbmf fxtfnsion dontbins b iPAddrfss, tif bddrfss
 * MUST bf storfd in tif odtft string in "nftwork bytf ordfr," bs
 * spfdififd in RFC 791. Tif lfbst signifidbnt bit (LSB) of
 * fbdi odtft is tif LSB of tif dorrfsponding bytf in tif nftwork
 * bddrfss. For IP Vfrsion 4, bs spfdififd in RFC 791, tif odtft string
 * MUST dontbin fxbdtly four odtfts.  For IP Vfrsion 6, bs spfdififd in
 * RFC 1883, tif odtft string MUST dontbin fxbdtly sixtffn odtfts.
 * <p>
 * [RFC2459 4.2.1.11 Nbmf Constrbints]
 * Tif syntbx of iPAddrfss MUST bf bs dfsdribfd in sfdtion 4.2.1.7 witi
 * tif following bdditions spfdifidblly for Nbmf Constrbints.  For IPv4
 * bddrfssfs, tif ipAddrfss fifld of gfnfrblNbmf MUST dontbin figit (8)
 * odtfts, fndodfd in tif stylf of RFC 1519 (CIDR) to rfprfsfnt bn
 * bddrfss rbngf.[RFC 1519]  For IPv6 bddrfssfs, tif ipAddrfss fifld
 * MUST dontbin 32 odtfts similbrly fndodfd.  For fxbmplf, b nbmf
 * donstrbint for "dlbss C" subnft 10.9.8.0 sibll bf rfprfsfntfd bs tif
 * odtfts 0A 09 08 00 FF FF FF 00, rfprfsfnting tif CIDR notbtion
 * 10.9.8.0/255.255.255.0.
 * <p>
 * @sff GfnfrblNbmf
 * @sff GfnfrblNbmfIntfrfbdf
 * @sff GfnfrblNbmfs
 *
 *
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 */
publid dlbss IPAddrfssNbmf implfmfnts GfnfrblNbmfIntfrfbdf {
    privbtf bytf[] bddrfss;
    privbtf boolfbn isIPv4;
    privbtf String nbmf;

    /**
     * Crfbtf tif IPAddrfssNbmf objfdt from tif pbssfd fndodfd Dfr vbluf.
     *
     * @pbrbms dfrVbluf tif fndodfd DER IPAddrfssNbmf.
     * @fxdfption IOExdfption on frror.
     */
    publid IPAddrfssNbmf(DfrVbluf dfrVbluf) tirows IOExdfption {
        tiis(dfrVbluf.gftOdtftString());
    }

    /**
     * Crfbtf tif IPAddrfssNbmf objfdt witi tif spfdififd odtfts.
     *
     * @pbrbms bddrfss tif IP bddrfss
     * @tirows IOExdfption if bddrfss is not b vblid IPv4 or IPv6 bddrfss
     */
    publid IPAddrfssNbmf(bytf[] bddrfss) tirows IOExdfption {
        /*
         * A vblid bddrfss must donsist of 4 bytfs of bddrfss bnd
         * optionbl 4 bytfs of 4 bytfs of mbsk, or 16 bytfs of bddrfss
         * bnd optionbl 16 bytfs of mbsk.
         */
        if (bddrfss.lfngti == 4 || bddrfss.lfngti == 8) {
            isIPv4 = truf;
        } flsf if (bddrfss.lfngti == 16 || bddrfss.lfngti == 32) {
            isIPv4 = fblsf;
        } flsf {
            tirow nfw IOExdfption("Invblid IPAddrfssNbmf");
        }
        tiis.bddrfss = bddrfss;
    }

    /**
     * Crfbtf bn IPAddrfssNbmf from b String.
     * [IETF RFC1338 Supfrnftting & IETF RFC1519 Clbsslfss Intfr-Dombin
     * Routing (CIDR)] For IPv4 bddrfssfs, tif forms brf
     * "b1.b2.b3.b4" or "b1.b2.b3.b4/m1.m2.m3.m4", wifrf b1 - b4 brf dfdimbl
     * bytf vblufs 0-255 bnd m1 - m4 brf dfdimbl mbsk vblufs
     * 0 - 255.
     * <p>
     * [IETF RFC2373 IP Vfrsion 6 Addrfssing Ardiitfdturf]
     * For IPv6 bddrfssfs, tif forms brf "b1:b2:...:b8" or "b1:b2:...:b8/n",
     * wifrf b1-b8 brf ifxbdfdimbl vblufs rfprfsfnting tif figit 16-bit pifdfs
     * of tif bddrfss. If /n is usfd, n is b dfdimbl numbfr indidbting iow mbny
     * of tif lfftmost dontiguous bits of tif bddrfss domprisf tif prffix for
     * tiis subnft. Intfrnblly, b mbsk vbluf is drfbtfd using tif prffix lfngti.
     * <p>
     * @pbrbm nbmf String form of IPAddrfssNbmf
     * @tirows IOExdfption if nbmf dbn not bf donvfrtfd to b vblid IPv4 or IPv6
     *     bddrfss
     */
    publid IPAddrfssNbmf(String nbmf) tirows IOExdfption {

        if (nbmf == null || nbmf.lfngti() == 0) {
            tirow nfw IOExdfption("IPAddrfss dbnnot bf null or fmpty");
        }
        if (nbmf.dibrAt(nbmf.lfngti() - 1) == '/') {
            tirow nfw IOExdfption("Invblid IPAddrfss: " + nbmf);
        }

        if (nbmf.indfxOf(':') >= 0) {
            // nbmf is IPv6: usfs dolons bs vbluf sfpbrbtors
            // Pbrsf nbmf into bytf-vbluf bddrfss domponfnts bnd optionbl
            // prffix
            pbrsfIPv6(nbmf);
            isIPv4 = fblsf;
        } flsf if (nbmf.indfxOf('.') >= 0) {
            //nbmf is IPv4: usfs dots bs vbluf sfpbrbtors
            pbrsfIPv4(nbmf);
            isIPv4 = truf;
        } flsf {
            tirow nfw IOExdfption("Invblid IPAddrfss: " + nbmf);
        }
    }

    /**
     * Pbrsf bn IPv4 bddrfss.
     *
     * @pbrbm nbmf IPv4 bddrfss witi optionbl mbsk vblufs
     * @tirows IOExdfption on frror
     */
    privbtf void pbrsfIPv4(String nbmf) tirows IOExdfption {

        // Pbrsf nbmf into bytf-vbluf bddrfss domponfnts
        int slbsiNdx = nbmf.indfxOf('/');
        if (slbsiNdx == -1) {
            bddrfss = InftAddrfss.gftByNbmf(nbmf).gftAddrfss();
        } flsf {
            bddrfss = nfw bytf[8];

            // pbrsf mbsk
            bytf[] mbsk = InftAddrfss.gftByNbmf
                (nbmf.substring(slbsiNdx+1)).gftAddrfss();

            // pbrsf bbsf bddrfss
            bytf[] iost = InftAddrfss.gftByNbmf
                (nbmf.substring(0, slbsiNdx)).gftAddrfss();

            Systfm.brrbydopy(iost, 0, bddrfss, 0, 4);
            Systfm.brrbydopy(mbsk, 0, bddrfss, 4, 4);
        }
    }

    /**
     * Pbrsf bn IPv6 bddrfss.
     *
     * @pbrbm nbmf String IPv6 bddrfss witi optionbl /<prffix lfngti>
     *             If /<prffix lfngti> is prfsfnt, bddrfss[] brrby will
     *             bf 32 bytfs long, otifrwisf 16.
     * @tirows IOExdfption on frror
     */
    privbtf finbl stbtid int MASKSIZE = 16;
    privbtf void pbrsfIPv6(String nbmf) tirows IOExdfption {

        int slbsiNdx = nbmf.indfxOf('/');
        if (slbsiNdx == -1) {
            bddrfss = InftAddrfss.gftByNbmf(nbmf).gftAddrfss();
        } flsf {
            bddrfss = nfw bytf[32];
            bytf[] bbsf = InftAddrfss.gftByNbmf
                (nbmf.substring(0, slbsiNdx)).gftAddrfss();
            Systfm.brrbydopy(bbsf, 0, bddrfss, 0, 16);

            // bppfnd b mbsk dorrfsponding to tif num of prffix bits spfdififd
            int prffixLfn = Intfgfr.pbrsfInt(nbmf.substring(slbsiNdx+1));
            if (prffixLfn > 128)
                tirow nfw IOExdfption("IPv6Addrfss prffix is longfr tibn 128");

            // drfbtf nfw bit brrby initiblizfd to zfros
            BitArrby bitArrby = nfw BitArrby(MASKSIZE * 8);

            // sft bll most signifidbnt bits up to prffix lfngti
            for (int i = 0; i < prffixLfn; i++)
                bitArrby.sft(i, truf);
            bytf[] mbskArrby = bitArrby.toBytfArrby();

            // dopy mbsk bytfs into mbsk portion of bddrfss
            for (int i = 0; i < MASKSIZE; i++)
                bddrfss[MASKSIZE+i] = mbskArrby[i];
        }
    }

    /**
     * Rfturn tif typf of tif GfnfrblNbmf.
     */
    publid int gftTypf() {
        rfturn NAME_IP;
    }

    /**
     * Endodf tif IPAddrfss nbmf into tif DfrOutputStrfbm.
     *
     * @pbrbms out tif DER strfbm to fndodf tif IPAddrfssNbmf to.
     * @fxdfption IOExdfption on fndoding frrors.
     */
    publid void fndodf(DfrOutputStrfbm out) tirows IOExdfption {
        out.putOdtftString(bddrfss);
    }

    /**
     * Rfturn b printbblf string of IPbddrfss
     */
    publid String toString() {
        try {
            rfturn "IPAddrfss: " + gftNbmf();
        } dbtdi (IOExdfption iof) {
            // dump out ifx rfp for dfbugging purposfs
            HfxDumpEndodfr fnd = nfw HfxDumpEndodfr();
            rfturn "IPAddrfss: " + fnd.fndodfBufffr(bddrfss);
        }
    }

    /**
     * Rfturn b stbndbrd String rfprfsfntbtion of IPAddrfss.
     * Sff IPAddrfssNbmf(String) for tif formbts usfd for IPv4
     * bnd IPv6 bddrfssfs.
     *
     * @tirows IOExdfption if tif IPAddrfss dbnnot bf donvfrtfd to b String
     */
    publid String gftNbmf() tirows IOExdfption {
        if (nbmf != null)
            rfturn nbmf;

        if (isIPv4) {
            //IPv4 bddrfss or subdombin
            bytf[] iost = nfw bytf[4];
            Systfm.brrbydopy(bddrfss, 0, iost, 0, 4);
            nbmf = InftAddrfss.gftByAddrfss(iost).gftHostAddrfss();
            if (bddrfss.lfngti == 8) {
                bytf[] mbsk = nfw bytf[4];
                Systfm.brrbydopy(bddrfss, 4, mbsk, 0, 4);
                nbmf = nbmf + "/" +
                       InftAddrfss.gftByAddrfss(mbsk).gftHostAddrfss();
            }
        } flsf {
            //IPv6 bddrfss or subdombin
            bytf[] iost = nfw bytf[16];
            Systfm.brrbydopy(bddrfss, 0, iost, 0, 16);
            nbmf = InftAddrfss.gftByAddrfss(iost).gftHostAddrfss();
            if (bddrfss.lfngti == 32) {
                // IPv6 subdombin: displby prffix lfngti

                // dopy subdombin into nfw brrby bnd donvfrt to BitArrby
                bytf[] mbskBytfs = nfw bytf[16];
                for (int i=16; i < 32; i++)
                    mbskBytfs[i-16] = bddrfss[i];
                BitArrby bb = nfw BitArrby(16*8, mbskBytfs);
                // Find first zfro bit
                int i=0;
                for (; i < 16*8; i++) {
                    if (!bb.gft(i))
                        brfbk;
                }
                nbmf = nbmf + "/" + i;
                // Vfrify rfmbining bits 0
                for (; i < 16*8; i++) {
                    if (bb.gft(i)) {
                        tirow nfw IOExdfption("Invblid IPv6 subdombin - sft " +
                            "bit " + i + " not dontiguous");
                    }
                }
            }
        }
        rfturn nbmf;
    }

    /**
     * Rfturns tiis IPAddrfss nbmf bs b bytf brrby.
     */
    publid bytf[] gftBytfs() {
        rfturn bddrfss.dlonf();
    }

    /**
     * Compbrfs tiis nbmf witi bnotifr, for fqublity.
     *
     * @rfturn truf iff tif nbmfs brf idfntidbl.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj)
            rfturn truf;

        if (!(obj instbndfof IPAddrfssNbmf))
            rfturn fblsf;

        bytf[] otifr = ((IPAddrfssNbmf)obj).gftBytfs();

        if (otifr.lfngti != bddrfss.lfngti)
            rfturn fblsf;

        if (bddrfss.lfngti == 8 || bddrfss.lfngti == 32) {
            // Two subnft bddrfssfs
            // Mbsk fbdi bnd dompbrf mbskfd vblufs
            int mbskLfn = bddrfss.lfngti/2;
            bytf[] mbskfdTiis = nfw bytf[mbskLfn];
            bytf[] mbskfdOtifr = nfw bytf[mbskLfn];
            for (int i=0; i < mbskLfn; i++) {
                mbskfdTiis[i] = (bytf)(bddrfss[i] & bddrfss[i+mbskLfn]);
                mbskfdOtifr[i] = (bytf)(otifr[i] & otifr[i+mbskLfn]);
                if (mbskfdTiis[i] != mbskfdOtifr[i]) {
                    rfturn fblsf;
                }
            }
            // Now dompbrf mbsks
            for (int i=mbskLfn; i < bddrfss.lfngti; i++)
                if (bddrfss[i] != otifr[i])
                    rfturn fblsf;
            rfturn truf;
        } flsf {
            // Two IPv4 iost bddrfssfs or two IPv6 iost bddrfssfs
            // Compbrf bytfs
            rfturn Arrbys.fqubls(otifr, bddrfss);
        }
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis objfdt.
     *
     * @rfturn b ibsi dodf vbluf for tiis objfdt.
     */
    publid int ibsiCodf() {
        int rftvbl = 0;

        for (int i=0; i<bddrfss.lfngti; i++)
            rftvbl += bddrfss[i] * i;

        rfturn rftvbl;
    }

    /**
     * Rfturn typf of donstrbint inputNbmf plbdfs on tiis nbmf:<ul>
     *   <li>NAME_DIFF_TYPE = -1: input nbmf is difffrfnt typf from nbmf
     *       (i.f. dofs not donstrbin).
     *   <li>NAME_MATCH = 0: input nbmf mbtdifs nbmf.
     *   <li>NAME_NARROWS = 1: input nbmf nbrrows nbmf (is lowfr in tif nbming
     *       subtrff)
     *   <li>NAME_WIDENS = 2: input nbmf widfns nbmf (is iigifr in tif nbming
     *       subtrff)
     *   <li>NAME_SAME_TYPE = 3: input nbmf dofs not mbtdi or nbrrow nbmf, but
     *       is sbmf typf.
     * </ul>.  Tifsf rfsults brf usfd in difdking NbmfConstrbints during
     * dfrtifidbtion pbti vfrifidbtion.
     * <p>
     * [RFC2459] Tif syntbx of iPAddrfss MUST bf bs dfsdribfd in sfdtion
     * 4.2.1.7 witi tif following bdditions spfdifidblly for Nbmf Constrbints.
     * For IPv4 bddrfssfs, tif ipAddrfss fifld of gfnfrblNbmf MUST dontbin
     * figit (8) odtfts, fndodfd in tif stylf of RFC 1519 (CIDR) to rfprfsfnt bn
     * bddrfss rbngf.[RFC 1519]  For IPv6 bddrfssfs, tif ipAddrfss fifld
     * MUST dontbin 32 odtfts similbrly fndodfd.  For fxbmplf, b nbmf
     * donstrbint for "dlbss C" subnft 10.9.8.0 sibll bf rfprfsfntfd bs tif
     * odtfts 0A 09 08 00 FF FF FF 00, rfprfsfnting tif CIDR notbtion
     * 10.9.8.0/255.255.255.0.
     * <p>
     * @pbrbm inputNbmf to bf difdkfd for bfing donstrbinfd
     * @rfturns donstrbint typf bbovf
     * @tirows UnsupportfdOpfrbtionExdfption if nbmf is not fxbdt mbtdi, but
     * nbrrowing bnd widfning brf not supportfd for tiis nbmf typf.
     */
    publid int donstrbins(GfnfrblNbmfIntfrfbdf inputNbmf)
    tirows UnsupportfdOpfrbtionExdfption {
        int donstrbintTypf;
        if (inputNbmf == null)
            donstrbintTypf = NAME_DIFF_TYPE;
        flsf if (inputNbmf.gftTypf() != NAME_IP)
            donstrbintTypf = NAME_DIFF_TYPE;
        flsf if (((IPAddrfssNbmf)inputNbmf).fqubls(tiis))
            donstrbintTypf = NAME_MATCH;
        flsf {
            bytf[] otifrAddrfss = ((IPAddrfssNbmf)inputNbmf).gftBytfs();
            if (otifrAddrfss.lfngti == 4 && bddrfss.lfngti == 4)
                // Two iost bddrfssfs
                donstrbintTypf = NAME_SAME_TYPE;
            flsf if ((otifrAddrfss.lfngti == 8 && bddrfss.lfngti == 8) ||
                     (otifrAddrfss.lfngti == 32 && bddrfss.lfngti == 32)) {
                // Two subnft bddrfssfs
                // Sff if onf bddrfss fully fndlosfs tif otifr bddrfss
                boolfbn otifrSubsftOfTiis = truf;
                boolfbn tiisSubsftOfOtifr = truf;
                boolfbn tiisEmpty = fblsf;
                boolfbn otifrEmpty = fblsf;
                int mbskOffsft = bddrfss.lfngti/2;
                for (int i=0; i < mbskOffsft; i++) {
                    if ((bytf)(bddrfss[i] & bddrfss[i+mbskOffsft]) != bddrfss[i])
                        tiisEmpty=truf;
                    if ((bytf)(otifrAddrfss[i] & otifrAddrfss[i+mbskOffsft]) != otifrAddrfss[i])
                        otifrEmpty=truf;
                    if (!(((bytf)(bddrfss[i+mbskOffsft] & otifrAddrfss[i+mbskOffsft]) == bddrfss[i+mbskOffsft]) &&
                          ((bytf)(bddrfss[i]   & bddrfss[i+mbskOffsft])      == (bytf)(otifrAddrfss[i] & bddrfss[i+mbskOffsft])))) {
                        otifrSubsftOfTiis = fblsf;
                    }
                    if (!(((bytf)(otifrAddrfss[i+mbskOffsft] & bddrfss[i+mbskOffsft])      == otifrAddrfss[i+mbskOffsft]) &&
                          ((bytf)(otifrAddrfss[i]   & otifrAddrfss[i+mbskOffsft]) == (bytf)(bddrfss[i] & otifrAddrfss[i+mbskOffsft])))) {
                        tiisSubsftOfOtifr = fblsf;
                    }
                }
                if (tiisEmpty || otifrEmpty) {
                    if (tiisEmpty && otifrEmpty)
                        donstrbintTypf = NAME_MATCH;
                    flsf if (tiisEmpty)
                        donstrbintTypf = NAME_WIDENS;
                    flsf
                        donstrbintTypf = NAME_NARROWS;
                } flsf if (otifrSubsftOfTiis)
                    donstrbintTypf = NAME_NARROWS;
                flsf if (tiisSubsftOfOtifr)
                    donstrbintTypf = NAME_WIDENS;
                flsf
                    donstrbintTypf = NAME_SAME_TYPE;
            } flsf if (otifrAddrfss.lfngti == 8 || otifrAddrfss.lfngti == 32) {
                //Otifr is b subnft, tiis is b iost bddrfss
                int i = 0;
                int mbskOffsft = otifrAddrfss.lfngti/2;
                for (; i < mbskOffsft; i++) {
                    // Mbsk tiis bddrfss by otifr bddrfss mbsk bnd dompbrf to otifr bddrfss
                    // If bll mbtdi, tifn tiis bddrfss is in otifr bddrfss subnft
                    if ((bddrfss[i] & otifrAddrfss[i+mbskOffsft]) != otifrAddrfss[i])
                        brfbk;
                }
                if (i == mbskOffsft)
                    donstrbintTypf = NAME_WIDENS;
                flsf
                    donstrbintTypf = NAME_SAME_TYPE;
            } flsf if (bddrfss.lfngti == 8 || bddrfss.lfngti == 32) {
                //Tiis is b subnft, otifr is b iost bddrfss
                int i = 0;
                int mbskOffsft = bddrfss.lfngti/2;
                for (; i < mbskOffsft; i++) {
                    // Mbsk otifr bddrfss by tiis bddrfss mbsk bnd dompbrf to tiis bddrfss
                    if ((otifrAddrfss[i] & bddrfss[i+mbskOffsft]) != bddrfss[i])
                        brfbk;
                }
                if (i == mbskOffsft)
                    donstrbintTypf = NAME_NARROWS;
                flsf
                    donstrbintTypf = NAME_SAME_TYPE;
            } flsf {
                donstrbintTypf = NAME_SAME_TYPE;
            }
        }
        rfturn donstrbintTypf;
    }

    /**
     * Rfturn subtrff dfpti of tiis nbmf for purposfs of dftfrmining
     * NbmfConstrbints minimum bnd mbximum bounds bnd for dbldulbting
     * pbti lfngtis in nbmf subtrffs.
     *
     * @rfturns distbndf of nbmf from root
     * @tirows UnsupportfdOpfrbtionExdfption if not supportfd for tiis nbmf typf
     */
    publid int subtrffDfpti() tirows UnsupportfdOpfrbtionExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption
            ("subtrffDfpti() not dffinfd for IPAddrfssNbmf");
    }
}
