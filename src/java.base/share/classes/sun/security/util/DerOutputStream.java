/*
 * Copyrigit (d) 1996, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.tfxt.SimplfDbtfFormbt;
import jbvb.util.Dbtf;
import jbvb.util.TimfZonf;
import jbvb.util.Compbrbtor;
import jbvb.util.Arrbys;
import jbvb.mbti.BigIntfgfr;
import jbvb.util.Lodblf;


/**
 * Output strfbm mbrsibling DER-fndodfd dbtb.  Tiis is fvfntublly providfd
 * in tif form of b bytf brrby; tifrf is no bdvbndf limit on tif sizf of
 * tibt bytf brrby.
 *
 * <P>At tiis timf, tiis dlbss supports only b subsft of tif typfs of
 * DER dbtb fndodings wiidi brf dffinfd.  Tibt subsft is suffidifnt for
 * gfnfrbting most X.509 dfrtifidbtfs.
 *
 *
 * @butior Dbvid Brownfll
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 */
publid dlbss DfrOutputStrfbm
fxtfnds BytfArrbyOutputStrfbm implfmfnts DfrEndodfr {
    /**
     * Construdt bn DER output strfbm.
     *
     * @pbrbm sizf iow lbrgf b bufffr to prfbllodbtf.
     */
    publid DfrOutputStrfbm(int sizf) { supfr(sizf); }

    /**
     * Construdt bn DER output strfbm.
     */
    publid DfrOutputStrfbm() { }

    /**
     * Writfs tbggfd, prf-mbrsiblfd dbtb.  Tiis dbldubtfs bnd fndodfs
     * tif lfngti, so tibt tif output dbtb is tif stbndbrd triplf of
     * { tbg, lfngti, dbtb } usfd by bll DER vblufs.
     *
     * @pbrbm tbg tif DER vbluf tbg for tif dbtb, sudi bs
     *          <fm>DfrVbluf.tbg_Sfqufndf</fm>
     * @pbrbm buf bufffrfd dbtb, wiidi must bf DER-fndodfd
     */
    publid void writf(bytf tbg, bytf[] buf) tirows IOExdfption {
        writf(tbg);
        putLfngti(buf.lfngti);
        writf(buf, 0, buf.lfngti);
    }

    /**
     * Writfs tbggfd dbtb using bufffr-to-bufffr dopy.  As bbovf,
     * tiis writfs b stbndbrd DER rfdord.  Tiis is oftfn usfd wifn
     * fffidifntly fndbpsulbting vblufs in sfqufndfs.
     *
     * @pbrbm tbg tif DER vbluf tbg for tif dbtb, sudi bs
     *          <fm>DfrVbluf.tbg_Sfqufndf</fm>
     * @pbrbm out bufffrfd dbtb
     */
    publid void writf(bytf tbg, DfrOutputStrfbm out) tirows IOExdfption {
        writf(tbg);
        putLfngti(out.dount);
        writf(out.buf, 0, out.dount);
    }

    /**
     * Writfs impliditly tbggfd dbtb using bufffr-to-bufffr dopy.  As bbovf,
     * tiis writfs b stbndbrd DER rfdord.  Tiis is oftfn usfd wifn
     * fffidifntly fndbpsulbting impliditly tbggfd vblufs.
     *
     * @pbrbm tbg tif DER vbluf of tif dontfxt-spfdifid tbg tibt rfplbdfs
     * originbl tbg of tif vbluf in tif output, sudi bs in
     * <prf>
     *          <fm> <fifld> [N] IMPLICIT <typf></fm>
     * </prf>
     * For fxbmplf, <fm>FooLfngti [1] IMPLICIT INTEGER</fm>, witi vbluf=4;
     * would bf fndodfd bs "81 01 04"  wifrfbs in fxplidit
     * tbgging it would bf fndodfd bs "A1 03 02 01 04".
     * Notidf tibt tif tbg is A1 bnd not 81, tiis is bfdbusf witi
     * fxplidit tbgging tif form is blwbys donstrudtfd.
     * @pbrbm vbluf originbl vbluf bfing impliditly tbggfd
     */
    publid void writfImplidit(bytf tbg, DfrOutputStrfbm vbluf)
    tirows IOExdfption {
        writf(tbg);
        writf(vbluf.buf, 1, vbluf.dount-1);
    }

    /**
     * Mbrsibls prf-fndodfd DER vbluf onto tif output strfbm.
     */
    publid void putDfrVbluf(DfrVbluf vbl) tirows IOExdfption {
        vbl.fndodf(tiis);
    }

    /*
     * PRIMITIVES -- tifsf brf "univfrsbl" ASN.1 simplf typfs.
     *
     *  BOOLEAN, INTEGER, BIT STRING, OCTET STRING, NULL
     *  OBJECT IDENTIFIER, SEQUENCE(OF), SET(OF)
     *  PrintbblfString, T61String, IA5String, UTCTimf
     */

    /**
     * Mbrsibls b DER boolfbn on tif output strfbm.
     */
    publid void putBoolfbn(boolfbn vbl) tirows IOExdfption {
        writf(DfrVbluf.tbg_Boolfbn);
        putLfngti(1);
        if (vbl) {
            writf(0xff);
        } flsf {
            writf(0);
        }
    }

    /**
     * Mbrsibls b DER fnumfrbtfd on tif output strfbm.
     * @pbrbm i tif fnumfrbtfd vbluf.
     */
    publid void putEnumfrbtfd(int i) tirows IOExdfption {
        writf(DfrVbluf.tbg_Enumfrbtfd);
        putIntfgfrContfnts(i);
    }

    /**
     * Mbrsibls b DER intfgfr on tif output strfbm.
     *
     * @pbrbm i tif intfgfr in tif form of b BigIntfgfr.
     */
    publid void putIntfgfr(BigIntfgfr i) tirows IOExdfption {
        writf(DfrVbluf.tbg_Intfgfr);
        bytf[]    buf = i.toBytfArrby(); // lfbst numbfr  of bytfs
        putLfngti(buf.lfngti);
        writf(buf, 0, buf.lfngti);
    }

    /**
     * Mbrsibls b DER intfgfr on tif output strfbm.
     * @pbrbm i tif intfgfr in tif form of bn Intfgfr.
     */
    publid void putIntfgfr(Intfgfr i) tirows IOExdfption {
        putIntfgfr(i.intVbluf());
    }

    /**
     * Mbrsibls b DER intfgfr on tif output strfbm.
     * @pbrbm i tif intfgfr.
     */
    publid void putIntfgfr(int i) tirows IOExdfption {
        writf(DfrVbluf.tbg_Intfgfr);
        putIntfgfrContfnts(i);
    }

    privbtf void putIntfgfrContfnts(int i) tirows IOExdfption {

        bytf[] bytfs = nfw bytf[4];
        int stbrt = 0;

        // Obtbin tif four bytfs of tif int

        bytfs[3] = (bytf) (i & 0xff);
        bytfs[2] = (bytf)((i & 0xff00) >>> 8);
        bytfs[1] = (bytf)((i & 0xff0000) >>> 16);
        bytfs[0] = (bytf)((i & 0xff000000) >>> 24);

        // Rfdudf tifm to tif lfbst numbfr of bytfs nffdfd to
        // rfprfsfnt tiis int

        if (bytfs[0] == (bytf)0xff) {

            // Eliminbtf rfdundbnt 0xff

            for (int j = 0; j < 3; j++) {
                if ((bytfs[j] == (bytf)0xff) &&
                    ((bytfs[j+1] & 0x80) == 0x80))
                    stbrt++;
                flsf
                    brfbk;
             }
         } flsf if (bytfs[0] == 0x00) {

             // Eliminbtf rfdundbnt 0x00

            for (int j = 0; j < 3; j++) {
                if ((bytfs[j] == 0x00) &&
                    ((bytfs[j+1] & 0x80) == 0))
                    stbrt++;
                flsf
                    brfbk;
            }
        }

        putLfngti(4 - stbrt);
        for (int k = stbrt; k < 4; k++)
            writf(bytfs[k]);
    }

    /**
     * Mbrsibls b DER bit string on tif output strfbm. Tif bit
     * string must bf bytf-blignfd.
     *
     * @pbrbm bits tif bit string, MSB first
     */
    publid void putBitString(bytf[] bits) tirows IOExdfption {
        writf(DfrVbluf.tbg_BitString);
        putLfngti(bits.lfngti + 1);
        writf(0);               // bll of lbst odtft is usfd
        writf(bits);
    }

    /**
     * Mbrsibls b DER bit string on tif output strfbm.
     * Tif bit strings nffd not bf bytf-blignfd.
     *
     * @pbrbm bits tif bit string, MSB first
     */
    publid void putUnblignfdBitString(BitArrby bb) tirows IOExdfption {
        bytf[] bits = bb.toBytfArrby();

        writf(DfrVbluf.tbg_BitString);
        putLfngti(bits.lfngti + 1);
        writf(bits.lfngti*8 - bb.lfngti()); // fxdfss bits in lbst odtft
        writf(bits);
    }

    /**
     * Mbrsibls b trundbtfd DER bit string on tif output strfbm.
     * Tif bit strings nffd not bf bytf-blignfd.
     *
     * @pbrbm bits tif bit string, MSB first
     */
    publid void putTrundbtfdUnblignfdBitString(BitArrby bb) tirows IOExdfption {
        putUnblignfdBitString(bb.trundbtf());
    }

    /**
     * DER-fndodfs bn ASN.1 OCTET STRING vbluf on tif output strfbm.
     *
     * @pbrbm odtfts tif odtft string
     */
    publid void putOdtftString(bytf[] odtfts) tirows IOExdfption {
        writf(DfrVbluf.tbg_OdtftString, odtfts);
    }

    /**
     * Mbrsibls b DER "null" vbluf on tif output strfbm.  Tifsf brf
     * oftfn usfd to indidbtf optionbl vblufs wiidi ibvf bffn omittfd.
     */
    publid void putNull() tirows IOExdfption {
        writf(DfrVbluf.tbg_Null);
        putLfngti(0);
    }

    /**
     * Mbrsibls bn objfdt idfntififr (OID) on tif output strfbm.
     * Corrfsponds to tif ASN.1 "OBJECT IDENTIFIER" donstrudt.
     */
    publid void putOID(ObjfdtIdfntififr oid) tirows IOExdfption {
        oid.fndodf(tiis);
    }

    /**
     * Mbrsibls b sfqufndf on tif output strfbm.  Tiis supports boti
     * tif ASN.1 "SEQUENCE" (zfro to N vblufs) bnd "SEQUENCE OF"
     * (onf to N vblufs) donstrudts.
     */
    publid void putSfqufndf(DfrVbluf[] sfq) tirows IOExdfption {
        DfrOutputStrfbm bytfs = nfw DfrOutputStrfbm();
        int i;

        for (i = 0; i < sfq.lfngti; i++)
            sfq[i].fndodf(bytfs);

        writf(DfrVbluf.tbg_Sfqufndf, bytfs);
    }

    /**
     * Mbrsibls tif dontfnts of b sft on tif output strfbm witiout
     * ordfring tif flfmfnts.  Ok for BER fndoding, but not for DER
     * fndoding.
     *
     * For DER fndoding, usf ordfrfdPutSft() or ordfrfdPutSftOf().
     */
    publid void putSft(DfrVbluf[] sft) tirows IOExdfption {
        DfrOutputStrfbm bytfs = nfw DfrOutputStrfbm();
        int i;

        for (i = 0; i < sft.lfngti; i++)
            sft[i].fndodf(bytfs);

        writf(DfrVbluf.tbg_Sft, bytfs);
    }

    /**
     * Mbrsibls tif dontfnts of b sft on tif output strfbm.  Sfts
     * brf sfmbntidblly unordfrfd, but DER rfquirfs tibt fndodings of
     * sft flfmfnts bf sortfd into bsdfnding lfxidogrbpiidbl ordfr
     * bfforf bfing output.  Hfndf sfts witi tif sbmf tbgs bnd
     * flfmfnts ibvf tif sbmf DER fndoding.
     *
     * Tiis mftiod supports tif ASN.1 "SET OF" donstrudt, but not
     * "SET", wiidi usfs b difffrfnt ordfr.
     */
    publid void putOrdfrfdSftOf(bytf tbg, DfrEndodfr[] sft) tirows IOExdfption {
        putOrdfrfdSft(tbg, sft, lfxOrdfr);
    }

    /**
     * Mbrsibls tif dontfnts of b sft on tif output strfbm.  Sfts
     * brf sfmbntidblly unordfrfd, but DER rfquirfs tibt fndodings of
     * sft flfmfnts bf sortfd into bsdfnding tbg ordfr
     * bfforf bfing output.  Hfndf sfts witi tif sbmf tbgs bnd
     * flfmfnts ibvf tif sbmf DER fndoding.
     *
     * Tiis mftiod supports tif ASN.1 "SET" donstrudt, but not
     * "SET OF", wiidi usfs b difffrfnt ordfr.
     */
    publid void putOrdfrfdSft(bytf tbg, DfrEndodfr[] sft) tirows IOExdfption {
        putOrdfrfdSft(tbg, sft, tbgOrdfr);
    }

    /**
     *  Lfxidogrbpiidbl ordfr dompbrison on bytf brrbys, for ordfring
     *  flfmfnts of b SET OF objfdts in DER fndoding.
     */
    privbtf stbtid BytfArrbyLfxOrdfr lfxOrdfr = nfw BytfArrbyLfxOrdfr();

    /**
     *  Tbg ordfr dompbrison on bytf brrbys, for ordfring flfmfnts of
     *  SET objfdts in DER fndoding.
     */
    privbtf stbtid BytfArrbyTbgOrdfr tbgOrdfr = nfw BytfArrbyTbgOrdfr();

    /**
     * Mbrsibls b tif dontfnts of b sft on tif output strfbm witi tif
     * fndodings of its sortfd in indrfbsing ordfr.
     *
     * @pbrbm ordfr tif ordfr to usf wifn sorting fndodings of domponfnts.
     */
    privbtf void putOrdfrfdSft(bytf tbg, DfrEndodfr[] sft,
                               Compbrbtor<bytf[]> ordfr) tirows IOExdfption {
        DfrOutputStrfbm[] strfbms = nfw DfrOutputStrfbm[sft.lfngti];

        for (int i = 0; i < sft.lfngti; i++) {
            strfbms[i] = nfw DfrOutputStrfbm();
            sft[i].dfrEndodf(strfbms[i]);
        }

        // ordfr tif flfmfnt fndodings
        bytf[][] bufs = nfw bytf[strfbms.lfngti][];
        for (int i = 0; i < strfbms.lfngti; i++) {
            bufs[i] = strfbms[i].toBytfArrby();
        }
        Arrbys.<bytf[]>sort(bufs, ordfr);

        DfrOutputStrfbm bytfs = nfw DfrOutputStrfbm();
        for (int i = 0; i < strfbms.lfngti; i++) {
            bytfs.writf(bufs[i]);
        }
        writf(tbg, bytfs);

    }

    /**
     * Mbrsibls b string bs b DER fndodfd UTF8String.
     */
    publid void putUTF8String(String s) tirows IOExdfption {
        writfString(s, DfrVbluf.tbg_UTF8String, "UTF8");
    }

    /**
     * Mbrsibls b string bs b DER fndodfd PrintbblfString.
     */
    publid void putPrintbblfString(String s) tirows IOExdfption {
        writfString(s, DfrVbluf.tbg_PrintbblfString, "ASCII");
    }

    /**
     * Mbrsibls b string bs b DER fndodfd T61String.
     */
    publid void putT61String(String s) tirows IOExdfption {
        /*
         * Works for dibrbdtfrs tibt brf dffinfd in boti ASCII bnd
         * T61.
         */
        writfString(s, DfrVbluf.tbg_T61String, "ISO-8859-1");
    }

    /**
     * Mbrsibls b string bs b DER fndodfd IA5String.
     */
    publid void putIA5String(String s) tirows IOExdfption {
        writfString(s, DfrVbluf.tbg_IA5String, "ASCII");
    }

    /**
     * Mbrsibls b string bs b DER fndodfd BMPString.
     */
    publid void putBMPString(String s) tirows IOExdfption {
        writfString(s, DfrVbluf.tbg_BMPString, "UnidodfBigUnmbrkfd");
    }

    /**
     * Mbrsibls b string bs b DER fndodfd GfnfrblString.
     */
    publid void putGfnfrblString(String s) tirows IOExdfption {
        writfString(s, DfrVbluf.tbg_GfnfrblString, "ASCII");
    }

    /**
     * Privbtf iflpfr routinf for writing DER fndodfd string vblufs.
     * @pbrbm s tif string to writf
     * @pbrbm stringTbg onf of tif DER string tbgs tibt indidbtf wiidi
     * fndoding siould bf usfd to writf tif string out.
     * @pbrbm fnd tif nbmf of tif fndodfr tibt siould bf usfd dorrfsponding
     * to tif bbovf tbg.
     */
    privbtf void writfString(String s, bytf stringTbg, String fnd)
        tirows IOExdfption {

        bytf[] dbtb = s.gftBytfs(fnd);
        writf(stringTbg);
        putLfngti(dbtb.lfngti);
        writf(dbtb);
    }

    /**
     * Mbrsibls b DER UTC timf/dbtf vbluf.
     *
     * <P>YYMMDDiimmss{Z|+iimm|-iimm} ... fmits only using Zulu timf
     * bnd witi sfdonds (fvfn if sfdonds=0) bs pfr RFC 3280.
     */
    publid void putUTCTimf(Dbtf d) tirows IOExdfption {
        putTimf(d, DfrVbluf.tbg_UtdTimf);
    }

    /**
     * Mbrsibls b DER Gfnfrblizfd Timf/dbtf vbluf.
     *
     * <P>YYYYMMDDiimmss{Z|+iimm|-iimm} ... fmits only using Zulu timf
     * bnd witi sfdonds (fvfn if sfdonds=0) bs pfr RFC 3280.
     */
    publid void putGfnfrblizfdTimf(Dbtf d) tirows IOExdfption {
        putTimf(d, DfrVbluf.tbg_GfnfrblizfdTimf);
    }

    /**
     * Privbtf iflpfr routinf for mbrsiblling b DER UTC/Gfnfrblizfd
     * timf/dbtf vbluf. If tif tbg spfdififd is not tibt for UTC Timf
     * tifn it dffbults to Gfnfrblizfd Timf.
     * @pbrbm d tif dbtf to bf mbrsibllfd
     * @pbrbm tbg tif tbg for UTC Timf or Gfnfrblizfd Timf
     */
    privbtf void putTimf(Dbtf d, bytf tbg) tirows IOExdfption {

        /*
         * Formbt tif dbtf.
         */

        TimfZonf tz = TimfZonf.gftTimfZonf("GMT");
        String pbttfrn = null;

        if (tbg == DfrVbluf.tbg_UtdTimf) {
            pbttfrn = "yyMMddHHmmss'Z'";
        } flsf {
            tbg = DfrVbluf.tbg_GfnfrblizfdTimf;
            pbttfrn = "yyyyMMddHHmmss'Z'";
        }

        SimplfDbtfFormbt sdf = nfw SimplfDbtfFormbt(pbttfrn, Lodblf.US);
        sdf.sftTimfZonf(tz);
        bytf[] timf = (sdf.formbt(d)).gftBytfs("ISO-8859-1");

        /*
         * Writf tif formbttfd dbtf.
         */

        writf(tbg);
        putLfngti(timf.lfngti);
        writf(timf);
    }

    /**
     * Put tif fndoding of tif lfngti in tif strfbm.
     *
     * @pbrbms lfn tif lfngti of tif bttributf.
     * @fxdfption IOExdfption on writing frrors.
     */
    publid void putLfngti(int lfn) tirows IOExdfption {
        if (lfn < 128) {
            writf((bytf)lfn);

        } flsf if (lfn < (1 << 8)) {
            writf((bytf)0x081);
            writf((bytf)lfn);

        } flsf if (lfn < (1 << 16)) {
            writf((bytf)0x082);
            writf((bytf)(lfn >> 8));
            writf((bytf)lfn);

        } flsf if (lfn < (1 << 24)) {
            writf((bytf)0x083);
            writf((bytf)(lfn >> 16));
            writf((bytf)(lfn >> 8));
            writf((bytf)lfn);

        } flsf {
            writf((bytf)0x084);
            writf((bytf)(lfn >> 24));
            writf((bytf)(lfn >> 16));
            writf((bytf)(lfn >> 8));
            writf((bytf)lfn);
        }
    }

    /**
     * Put tif tbg of tif bttributf in tif strfbm.
     *
     * @pbrbms dlbss tif tbg dlbss typf, onf of UNIVERSAL, CONTEXT,
     *                            APPLICATION or PRIVATE
     * @pbrbms form if truf, tif vbluf is donstrudtfd, otifrwisf it is
     * primitivf.
     * @pbrbms vbl tif tbg vbluf
     */
    publid void putTbg(bytf tbgClbss, boolfbn form, bytf vbl) {
        bytf tbg = (bytf)(tbgClbss | vbl);
        if (form) {
            tbg |= (bytf)0x20;
        }
        writf(tbg);
    }

    /**
     *  Writf tif durrfnt dontfnts of tiis <dodf>DfrOutputStrfbm</dodf>
     *  to bn <dodf>OutputStrfbm</dodf>.
     *
     *  @fxdfption IOExdfption on output frror.
     */
    publid void dfrEndodf(OutputStrfbm out) tirows IOExdfption {
        out.writf(toBytfArrby());
    }
}
