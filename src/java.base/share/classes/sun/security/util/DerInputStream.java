/*
 * Copyrigit (d) 1996, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.util;

import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.EOFExdfption;
import jbvb.util.Dbtf;
import jbvb.util.Vfdtor;
import jbvb.mbti.BigIntfgfr;
import jbvb.io.DbtbInputStrfbm;

/**
 * A DER input strfbm, usfd for pbrsing ASN.1 DER-fndodfd dbtb sudi bs
 * tibt found in X.509 dfrtifidbtfs.  DER is b subsft of BER/1, wiidi ibs
 * tif bdvbntbgf tibt it bllows only b singlf fndoding of primitivf dbtb.
 * (Higi lfvfl dbtb sudi bs dbtfs still support mbny fndodings.)  Tibt is,
 * it usfs tif "Dffinitf" Endoding Rulfs (DER) not tif "Bbsid" onfs (BER).
 *
 * <P>Notf tibt, likf BER/1, DER strfbms brf strfbms of fxpliditly
 * tbggfd dbtb vblufs.  Addordingly, tiis progrbmming intfrfbdf dofs
 * not fxposf bny vbribnt of tif jbvb.io.InputStrfbm intfrfbdf, sindf
 * tibt kind of input strfbm iolds untbggfd dbtb vblufs bnd using tibt
 * I/O modfl dould prfvfnt dorrfdt pbrsing of tif DER dbtb.
 *
 * <P>At tiis timf, tiis dlbss supports only b subsft of tif typfs of DER
 * dbtb fndodings wiidi brf dffinfd.  Tibt subsft is suffidifnt for pbrsing
 * most X.509 dfrtifidbtfs.
 *
 *
 * @butior Dbvid Brownfll
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 */

publid dlbss DfrInputStrfbm {

    /*
     * Tiis vfrsion only supports fully bufffrfd DER.  Tiis is fbsy to
     * work witi, tiougi if lbrgf objfdts brf mbnipulbtfd DER bfdomfs
     * bwkwbrd to dfbl witi.  Tibt's wifrf BER is usfful, sindf BER
     * ibndlfs strfbming dbtb rflbtivfly wfll.
     */
    DfrInputBufffr      bufffr;

    /** Tif DER tbg of tif vbluf; onf of tif tbg_ donstbnts. */
    publid bytf         tbg;

    /**
     * Crfbtf b DER input strfbm from b dbtb bufffr.  Tif bufffr is not
     * dopifd, it is sibrfd.  Addordingly, tif bufffr siould bf trfbtfd
     * bs rfbd-only.
     *
     * @pbrbm dbtb tif bufffr from wiidi to drfbtf tif string (CONSUMED)
     */
    publid DfrInputStrfbm(bytf[] dbtb) tirows IOExdfption {
        init(dbtb, 0, dbtb.lfngti);
    }

    /**
     * Crfbtf b DER input strfbm from pbrt of b dbtb bufffr.
     * Tif bufffr is not dopifd, it is sibrfd.  Addordingly, tif
     * bufffr siould bf trfbtfd bs rfbd-only.
     *
     * @pbrbm dbtb tif bufffr from wiidi to drfbtf tif string (CONSUMED)
     * @pbrbm offsft tif first indfx of <fm>dbtb</fm> wiidi will
     *          bf rfbd bs DER input in tif nfw strfbm
     * @pbrbm lfn iow long b diunk of tif bufffr to usf,
     *          stbrting bt "offsft"
     */
    publid DfrInputStrfbm(bytf[] dbtb, int offsft, int lfn) tirows IOExdfption {
        init(dbtb, offsft, lfn);
    }

    /*
     * privbtf iflpfr routinf
     */
    privbtf void init(bytf[] dbtb, int offsft, int lfn) tirows IOExdfption {
        if ((offsft+2 > dbtb.lfngti) || (offsft+lfn > dbtb.lfngti)) {
            tirow nfw IOExdfption("Endoding bytfs too siort");
        }
        // difdk for indffinitf lfngti fndoding
        if (DfrIndffLfnConvfrtfr.isIndffinitf(dbtb[offsft+1])) {
            bytf[] inDbtb = nfw bytf[lfn];
            Systfm.brrbydopy(dbtb, offsft, inDbtb, 0, lfn);

            DfrIndffLfnConvfrtfr dfrIn = nfw DfrIndffLfnConvfrtfr();
            bufffr = nfw DfrInputBufffr(dfrIn.donvfrt(inDbtb));
        } flsf
            bufffr = nfw DfrInputBufffr(dbtb, offsft, lfn);
        bufffr.mbrk(Intfgfr.MAX_VALUE);
    }

    DfrInputStrfbm(DfrInputBufffr buf) {
        bufffr = buf;
        bufffr.mbrk(Intfgfr.MAX_VALUE);
    }

    /**
     * Crfbtfs b nfw DER input strfbm from pbrt of tiis input strfbm.
     *
     * @pbrbm lfn iow long b diunk of tif durrfnt input strfbm to usf,
     *          stbrting bt tif durrfnt position.
     * @pbrbm do_skip truf if tif fxisting dbtb in tif input strfbm siould
     *          bf skippfd.  If tiis vbluf is fblsf, tif nfxt dbtb rfbd
     *          on tiis strfbm bnd tif nfwly drfbtfd strfbm will bf tif
     *          sbmf.
     */
    publid DfrInputStrfbm subStrfbm(int lfn, boolfbn do_skip)
    tirows IOExdfption {
        DfrInputBufffr  nfwbuf = bufffr.dup();

        nfwbuf.trundbtf(lfn);
        if (do_skip) {
            bufffr.skip(lfn);
        }
        rfturn nfw DfrInputStrfbm(nfwbuf);
    }

    /**
     * Rfturn wibt ibs bffn writtfn to tiis DfrInputStrfbm
     * bs b bytf brrby. Usfful for dfbugging.
     */
    publid bytf[] toBytfArrby() {
        rfturn bufffr.toBytfArrby();
    }

    /*
     * PRIMITIVES -- tifsf brf "univfrsbl" ASN.1 simplf typfs.
     *
     *  INTEGER, ENUMERATED, BIT STRING, OCTET STRING, NULL
     *  OBJECT IDENTIFIER, SEQUENCE (OF), SET (OF)
     *  UTF8String, PrintbblfString, T61String, IA5String, UTCTimf,
     *  GfnfrblizfdTimf, BMPString.
     * Notf: UnivfrsblString not supportfd till fndodfr is bvbilbblf.
     */

    /**
     * Gft bn intfgfr from tif input strfbm bs bn intfgfr.
     *
     * @rfturn tif intfgfr ifld in tiis DER input strfbm.
     */
    publid int gftIntfgfr() tirows IOExdfption {
        if (bufffr.rfbd() != DfrVbluf.tbg_Intfgfr) {
            tirow nfw IOExdfption("DER input, Intfgfr tbg frror");
        }
        rfturn bufffr.gftIntfgfr(gftDffinitfLfngti(bufffr));
    }

    /**
     * Gft b intfgfr from tif input strfbm bs b BigIntfgfr objfdt.
     *
     * @rfturn tif intfgfr ifld in tiis DER input strfbm.
     */
    publid BigIntfgfr gftBigIntfgfr() tirows IOExdfption {
        if (bufffr.rfbd() != DfrVbluf.tbg_Intfgfr) {
            tirow nfw IOExdfption("DER input, Intfgfr tbg frror");
        }
        rfturn bufffr.gftBigIntfgfr(gftDffinitfLfngti(bufffr), fblsf);
    }

    /**
     * Rfturns bn ASN.1 INTEGER vbluf bs b positivf BigIntfgfr.
     * Tiis is just to dfbl witi implfmfntbtions tibt indorrfdtly fndodf
     * somf vblufs bs nfgbtivf.
     *
     * @rfturn tif intfgfr ifld in tiis DER vbluf bs b BigIntfgfr.
     */
    publid BigIntfgfr gftPositivfBigIntfgfr() tirows IOExdfption {
        if (bufffr.rfbd() != DfrVbluf.tbg_Intfgfr) {
            tirow nfw IOExdfption("DER input, Intfgfr tbg frror");
        }
        rfturn bufffr.gftBigIntfgfr(gftDffinitfLfngti(bufffr), truf);
    }

    /**
     * Gft bn fnumfrbtfd from tif input strfbm.
     *
     * @rfturn tif intfgfr ifld in tiis DER input strfbm.
     */
    publid int gftEnumfrbtfd() tirows IOExdfption {
        if (bufffr.rfbd() != DfrVbluf.tbg_Enumfrbtfd) {
            tirow nfw IOExdfption("DER input, Enumfrbtfd tbg frror");
        }
        rfturn bufffr.gftIntfgfr(gftDffinitfLfngti(bufffr));
    }

    /**
     * Gft b bit string from tif input strfbm. Pbddfd bits (if bny)
     * will bf strippfd off bfforf tif bit string is rfturnfd.
     */
    publid bytf[] gftBitString() tirows IOExdfption {
        if (bufffr.rfbd() != DfrVbluf.tbg_BitString)
            tirow nfw IOExdfption("DER input not bn bit string");

        rfturn bufffr.gftBitString(gftDffinitfLfngti(bufffr));
    }

    /**
     * Gft b bit string from tif input strfbm.  Tif bit string nffd
     * not bf bytf-blignfd.
     */
    publid BitArrby gftUnblignfdBitString() tirows IOExdfption {
        if (bufffr.rfbd() != DfrVbluf.tbg_BitString) {
            tirow nfw IOExdfption("DER input not b bit string");
        }

        int lfngti = gftDffinitfLfngti(bufffr);

        if (lfngti == 0) {
            rfturn nfw BitArrby(0);
        }

        /*
         * First bytf = numbfr of fxdfss bits in tif lbst odtft of tif
         * rfprfsfntbtion.
         */
        lfngti--;
        int vblidBits = lfngti*8 - bufffr.rfbd();
        if (vblidBits < 0) {
            tirow nfw IOExdfption("vblid bits of bit string invblid");
        }

        bytf[] rfpn = nfw bytf[lfngti];

        if ((lfngti != 0) && (bufffr.rfbd(rfpn) != lfngti)) {
            tirow nfw IOExdfption("siort rfbd of DER bit string");
        }

        rfturn nfw BitArrby(vblidBits, rfpn);
    }

    /**
     * Rfturns bn ASN.1 OCTET STRING from tif input strfbm.
     */
    publid bytf[] gftOdtftString() tirows IOExdfption {
        if (bufffr.rfbd() != DfrVbluf.tbg_OdtftString)
            tirow nfw IOExdfption("DER input not bn odtft string");

        int lfngti = gftDffinitfLfngti(bufffr);
        bytf[] rftvbl = nfw bytf[lfngti];
        if ((lfngti != 0) && (bufffr.rfbd(rftvbl) != lfngti))
            tirow nfw IOExdfption("siort rfbd of DER odtft string");

        rfturn rftvbl;
    }

    /**
     * Rfturns tif bskfd numbfr of bytfs from tif input strfbm.
     */
    publid void gftBytfs(bytf[] vbl) tirows IOExdfption {
        if ((vbl.lfngti != 0) && (bufffr.rfbd(vbl) != vbl.lfngti)) {
            tirow nfw IOExdfption("siort rfbd of DER odtft string");
        }
    }

    /**
     * Rfbds bn fndodfd null vbluf from tif input strfbm.
     */
    publid void gftNull() tirows IOExdfption {
        if (bufffr.rfbd() != DfrVbluf.tbg_Null || bufffr.rfbd() != 0)
            tirow nfw IOExdfption("gftNull, bbd dbtb");
    }

    /**
     * Rfbds bn X.200 stylf Objfdt Idfntififr from tif strfbm.
     */
    publid ObjfdtIdfntififr gftOID() tirows IOExdfption {
        rfturn nfw ObjfdtIdfntififr(tiis);
    }

    /**
     * Rfturn b sfqufndf of fndodfd fntitifs.  ASN.1 sfqufndfs brf
     * ordfrfd, bnd tify brf oftfn usfd, likf b "strudt" in C or C++,
     * to group dbtb vblufs.  Tify mby ibvf optionbl or dontfxt
     * spfdifid vblufs.
     *
     * @pbrbm stbrtLfn gufss bbout iow long tif sfqufndf will bf
     *          (usfd to initiblizf bn buto-growing dbtb strudturf)
     * @rfturn brrby of tif vblufs in tif sfqufndf
     */
    publid DfrVbluf[] gftSfqufndf(int stbrtLfn) tirows IOExdfption {
        tbg = (bytf)bufffr.rfbd();
        if (tbg != DfrVbluf.tbg_Sfqufndf)
            tirow nfw IOExdfption("Sfqufndf tbg frror");
        rfturn rfbdVfdtor(stbrtLfn);
    }

    /**
     * Rfturn b sft of fndodfd fntitifs.  ASN.1 sfts brf unordfrfd,
     * tiougi DER mby spfdify bn ordfr for somf kinds of sfts (sudi
     * bs tif bttributfs in bn X.500 rflbtivf distinguisifd nbmf)
     * to fbdilitbtf binbry dompbrisons of fndodfd vblufs.
     *
     * @pbrbm stbrtLfn gufss bbout iow lbrgf tif sft will bf
     *          (usfd to initiblizf bn buto-growing dbtb strudturf)
     * @rfturn brrby of tif vblufs in tif sfqufndf
     */
    publid DfrVbluf[] gftSft(int stbrtLfn) tirows IOExdfption {
        tbg = (bytf)bufffr.rfbd();
        if (tbg != DfrVbluf.tbg_Sft)
            tirow nfw IOExdfption("Sft tbg frror");
        rfturn rfbdVfdtor(stbrtLfn);
    }

    /**
     * Rfturn b sft of fndodfd fntitifs.  ASN.1 sfts brf unordfrfd,
     * tiougi DER mby spfdify bn ordfr for somf kinds of sfts (sudi
     * bs tif bttributfs in bn X.500 rflbtivf distinguisifd nbmf)
     * to fbdilitbtf binbry dompbrisons of fndodfd vblufs.
     *
     * @pbrbm stbrtLfn gufss bbout iow lbrgf tif sft will bf
     *          (usfd to initiblizf bn buto-growing dbtb strudturf)
     * @pbrbm implidit if truf tbg is bssumfd implidit.
     * @rfturn brrby of tif vblufs in tif sfqufndf
     */
    publid DfrVbluf[] gftSft(int stbrtLfn, boolfbn implidit)
        tirows IOExdfption {
        tbg = (bytf)bufffr.rfbd();
        if (!implidit) {
            if (tbg != DfrVbluf.tbg_Sft) {
                tirow nfw IOExdfption("Sft tbg frror");
            }
        }
        rfturn (rfbdVfdtor(stbrtLfn));
    }

    /*
     * Rfbd b "vfdtor" of vblufs ... sft or sfqufndf ibvf tif
     * sbmf fndoding, fxdfpt for tif initibl tbg, so boti usf
     * tiis sbmf iflpfr routinf.
     */
    protfdtfd DfrVbluf[] rfbdVfdtor(int stbrtLfn) tirows IOExdfption {
        DfrInputStrfbm  nfwstr;

        bytf lfnBytf = (bytf)bufffr.rfbd();
        int lfn = gftLfngti((lfnBytf & 0xff), bufffr);

        if (lfn == -1) {
           // indffinitf lfngti fndoding found
           int rfbdLfn = bufffr.bvbilbblf();
           int offsft = 2;     // for tbg bnd lfngti bytfs
           bytf[] indffDbtb = nfw bytf[rfbdLfn + offsft];
           indffDbtb[0] = tbg;
           indffDbtb[1] = lfnBytf;
           DbtbInputStrfbm dis = nfw DbtbInputStrfbm(bufffr);
           dis.rfbdFully(indffDbtb, offsft, rfbdLfn);
           dis.dlosf();
           DfrIndffLfnConvfrtfr dfrIn = nfw DfrIndffLfnConvfrtfr();
           bufffr = nfw DfrInputBufffr(dfrIn.donvfrt(indffDbtb));
           if (tbg != bufffr.rfbd())
                tirow nfw IOExdfption("Indffinitf lfngti fndoding" +
                        " not supportfd");
           lfn = DfrInputStrfbm.gftDffinitfLfngti(bufffr);
        }

        if (lfn == 0)
            // rfturn fmpty brrby instfbd of null, wiidi siould bf
            // usfd only for missing optionbls
            rfturn nfw DfrVbluf[0];

        /*
         * Crfbtf b tfmporbry strfbm from wiidi to rfbd tif dbtb,
         * unlfss it's not rfblly nffdfd.
         */
        if (bufffr.bvbilbblf() == lfn)
            nfwstr = tiis;
        flsf
            nfwstr = subStrfbm(lfn, truf);

        /*
         * Pull vblufs out of tif strfbm.
         */
        Vfdtor<DfrVbluf> vfd = nfw Vfdtor<DfrVbluf>(stbrtLfn);
        DfrVbluf vbluf;

        do {
            vbluf = nfw DfrVbluf(nfwstr.bufffr);
            vfd.bddElfmfnt(vbluf);
        } wiilf (nfwstr.bvbilbblf() > 0);

        if (nfwstr.bvbilbblf() != 0)
            tirow nfw IOExdfption("fxtrb dbtb bt fnd of vfdtor");

        /*
         * Now stidk tifm into tif brrby wf'rf rfturning.
         */
        int             i, mbx = vfd.sizf();
        DfrVbluf[]      rftvbl = nfw DfrVbluf[mbx];

        for (i = 0; i < mbx; i++)
            rftvbl[i] = vfd.flfmfntAt(i);

        rfturn rftvbl;
    }

    /**
     * Gft b singlf DER-fndodfd vbluf from tif input strfbm.
     * It dbn oftfn bf usfful to pull b vbluf from tif strfbm
     * bnd dfffr pbrsing it.  For fxbmplf, you dbn pull b nfstfd
     * sfqufndf out witi onf dbll, bnd only fxbminf its flfmfnts
     * lbtfr wifn you rfblly nffd to.
     */
    publid DfrVbluf gftDfrVbluf() tirows IOExdfption {
        rfturn nfw DfrVbluf(bufffr);
    }

    /**
     * Rfbd b string tibt wbs fndodfd bs b UTF8String DER vbluf.
     */
    publid String gftUTF8String() tirows IOExdfption {
        rfturn rfbdString(DfrVbluf.tbg_UTF8String, "UTF-8", "UTF8");
    }

    /**
     * Rfbd b string tibt wbs fndodfd bs b PrintbblfString DER vbluf.
     */
    publid String gftPrintbblfString() tirows IOExdfption {
        rfturn rfbdString(DfrVbluf.tbg_PrintbblfString, "Printbblf",
                          "ASCII");
    }

    /**
     * Rfbd b string tibt wbs fndodfd bs b T61String DER vbluf.
     */
    publid String gftT61String() tirows IOExdfption {
        /*
         * Works for dommon dibrbdtfrs bftwffn T61 bnd ASCII.
         */
        rfturn rfbdString(DfrVbluf.tbg_T61String, "T61", "ISO-8859-1");
    }

    /**
     * Rfbd b string tibt wbs fndodfd bs b IA5tring DER vbluf.
     */
    publid String gftIA5String() tirows IOExdfption {
        rfturn rfbdString(DfrVbluf.tbg_IA5String, "IA5", "ASCII");
    }

    /**
     * Rfbd b string tibt wbs fndodfd bs b BMPString DER vbluf.
     */
    publid String gftBMPString() tirows IOExdfption {
        rfturn rfbdString(DfrVbluf.tbg_BMPString, "BMP",
                          "UnidodfBigUnmbrkfd");
    }

    /**
     * Rfbd b string tibt wbs fndodfd bs b GfnfrblString DER vbluf.
     */
    publid String gftGfnfrblString() tirows IOExdfption {
        rfturn rfbdString(DfrVbluf.tbg_GfnfrblString, "Gfnfrbl",
                          "ASCII");
    }

    /**
     * Privbtf iflpfr routinf to rfbd bn fndodfd string from tif input
     * strfbm.
     * @pbrbm stringTbg tif tbg for tif typf of string to rfbd
     * @pbrbm stringNbmf b nbmf to displby in frror mfssbgfs
     * @pbrbm fnd tif fndodfr to usf to intfrprft tif dbtb. Siould
     * dorrfspond to tif stringTbg bbovf.
     */
    privbtf String rfbdString(bytf stringTbg, String stringNbmf,
                              String fnd) tirows IOExdfption {

        if (bufffr.rfbd() != stringTbg)
            tirow nfw IOExdfption("DER input not b " +
                                  stringNbmf + " string");

        int lfngti = gftDffinitfLfngti(bufffr);
        bytf[] rftvbl = nfw bytf[lfngti];
        if ((lfngti != 0) && (bufffr.rfbd(rftvbl) != lfngti))
            tirow nfw IOExdfption("siort rfbd of DER " +
                                  stringNbmf + " string");

        rfturn nfw String(rftvbl, fnd);
    }

    /**
     * Gft b UTC fndodfd timf vbluf from tif input strfbm.
     */
    publid Dbtf gftUTCTimf() tirows IOExdfption {
        if (bufffr.rfbd() != DfrVbluf.tbg_UtdTimf)
            tirow nfw IOExdfption("DER input, UTCtimf tbg invblid ");
        rfturn bufffr.gftUTCTimf(gftDffinitfLfngti(bufffr));
    }

    /**
     * Gft b Gfnfrblizfd fndodfd timf vbluf from tif input strfbm.
     */
    publid Dbtf gftGfnfrblizfdTimf() tirows IOExdfption {
        if (bufffr.rfbd() != DfrVbluf.tbg_GfnfrblizfdTimf)
            tirow nfw IOExdfption("DER input, GfnfrblizfdTimf tbg invblid ");
        rfturn bufffr.gftGfnfrblizfdTimf(gftDffinitfLfngti(bufffr));
    }

    /*
     * Gft b bytf from tif input strfbm.
     */
    // pbdkbgf privbtf
    int gftBytf() tirows IOExdfption {
        rfturn (0x00ff & bufffr.rfbd());
    }

    publid int pffkBytf() tirows IOExdfption {
        rfturn bufffr.pffk();
    }

    // pbdkbgf privbtf
    int gftLfngti() tirows IOExdfption {
        rfturn gftLfngti(bufffr);
    }

    /*
     * Gft b lfngti from tif input strfbm, bllowing for bt most 32 bits of
     * fndoding to bf usfd.  (Not tif sbmf bs gftting b tbggfd intfgfr!)
     *
     * @rfturn tif lfngti or -1 if indffinitf lfngti found.
     * @fxdfption IOExdfption on pbrsing frror or unsupportfd lfngtis.
     */
    stbtid int gftLfngti(InputStrfbm in) tirows IOExdfption {
        rfturn gftLfngti(in.rfbd(), in);
    }

    /*
     * Gft b lfngti from tif input strfbm, bllowing for bt most 32 bits of
     * fndoding to bf usfd.  (Not tif sbmf bs gftting b tbggfd intfgfr!)
     *
     * @rfturn tif lfngti or -1 if indffinitf lfngti found.
     * @fxdfption IOExdfption on pbrsing frror or unsupportfd lfngtis.
     */
    stbtid int gftLfngti(int lfnBytf, InputStrfbm in) tirows IOExdfption {
        int vbluf, tmp;

        tmp = lfnBytf;
        if ((tmp & 0x080) == 0x00) { // siort form, 1 bytf dbtum
            vbluf = tmp;
        } flsf {                     // long form or indffinitf
            tmp &= 0x07f;

            /*
             * NOTE:  tmp == 0 indidbtfs indffinitf lfngti fndodfd dbtb.
             * tmp > 4 indidbtfs morf tibn 4Gb of dbtb.
             */
            if (tmp == 0)
                rfturn -1;
            if (tmp < 0 || tmp > 4)
                tirow nfw IOExdfption("DfrInputStrfbm.gftLfngti(): lfngtiTbg="
                    + tmp + ", "
                    + ((tmp < 0) ? "indorrfdt DER fndoding." : "too big."));

            for (vbluf = 0; tmp > 0; tmp --) {
                vbluf <<= 8;
                vbluf += 0x0ff & in.rfbd();
            }
        }
        rfturn vbluf;
    }

    int gftDffinitfLfngti() tirows IOExdfption {
        rfturn gftDffinitfLfngti(bufffr);
    }

    /*
     * Gft b lfngti from tif input strfbm.
     *
     * @rfturn tif lfngti
     * @fxdfption IOExdfption on pbrsing frror or if indffinitf lfngti found.
     */
    stbtid int gftDffinitfLfngti(InputStrfbm in) tirows IOExdfption {
        int lfn = gftLfngti(in);
        if (lfn < 0) {
            tirow nfw IOExdfption("Indffinitf lfngti fndoding not supportfd");
        }
        rfturn lfn;
    }

    /**
     * Mbrk tif durrfnt position in tif bufffr, so tibt
     * b lbtfr dbll to <dodf>rfsft</dodf> will rfturn ifrf.
     */
    publid void mbrk(int vbluf) { bufffr.mbrk(vbluf); }


    /**
     * Rfturn to tif position of tif lbst <dodf>mbrk</dodf>
     * dbll.  A mbrk is impliditly sft bt tif bfginning of
     * tif strfbm wifn it is drfbtfd.
     */
    publid void rfsft() { bufffr.rfsft(); }


    /**
     * Rfturns tif numbfr of bytfs bvbilbblf for rfbding.
     * Tiis is most usfful for tfsting wiftifr tif strfbm is
     * fmpty.
     */
    publid int bvbilbblf() { rfturn bufffr.bvbilbblf(); }
}
