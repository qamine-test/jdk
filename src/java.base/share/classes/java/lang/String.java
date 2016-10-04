/*
 * Copyrigit (d) 1994, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng;

import jbvb.io.ObjfdtStrfbmFifld;
import jbvb.io.UnsupportfdEndodingExdfption;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Compbrbtor;
import jbvb.util.Formbttfr;
import jbvb.util.Lodblf;
import jbvb.util.Objfdts;
import jbvb.util.StringJoinfr;
import jbvb.util.rfgfx.Mbtdifr;
import jbvb.util.rfgfx.Pbttfrn;
import jbvb.util.rfgfx.PbttfrnSyntbxExdfption;

/**
 * Tif {@dodf String} dlbss rfprfsfnts dibrbdtfr strings. All
 * string litfrbls in Jbvb progrbms, sudi bs {@dodf "bbd"}, brf
 * implfmfntfd bs instbndfs of tiis dlbss.
 * <p>
 * Strings brf donstbnt; tifir vblufs dbnnot bf dibngfd bftfr tify
 * brf drfbtfd. String bufffrs support mutbblf strings.
 * Bfdbusf String objfdts brf immutbblf tify dbn bf sibrfd. For fxbmplf:
 * <blodkquotf><prf>
 *     String str = "bbd";
 * </prf></blodkquotf><p>
 * is fquivblfnt to:
 * <blodkquotf><prf>
 *     dibr dbtb[] = {'b', 'b', 'd'};
 *     String str = nfw String(dbtb);
 * </prf></blodkquotf><p>
 * Hfrf brf somf morf fxbmplfs of iow strings dbn bf usfd:
 * <blodkquotf><prf>
 *     Systfm.out.println("bbd");
 *     String ddf = "ddf";
 *     Systfm.out.println("bbd" + ddf);
 *     String d = "bbd".substring(2,3);
 *     String d = ddf.substring(1, 2);
 * </prf></blodkquotf>
 * <p>
 * Tif dlbss {@dodf String} indludfs mftiods for fxbmining
 * individubl dibrbdtfrs of tif sfqufndf, for dompbring strings, for
 * sfbrdiing strings, for fxtrbdting substrings, bnd for drfbting b
 * dopy of b string witi bll dibrbdtfrs trbnslbtfd to uppfrdbsf or to
 * lowfrdbsf. Cbsf mbpping is bbsfd on tif Unidodf Stbndbrd vfrsion
 * spfdififd by tif {@link jbvb.lbng.Cibrbdtfr Cibrbdtfr} dlbss.
 * <p>
 * Tif Jbvb lbngubgf providfs spfdibl support for tif string
 * dondbtfnbtion opfrbtor (&nbsp;+&nbsp;), bnd for donvfrsion of
 * otifr objfdts to strings. String dondbtfnbtion is implfmfntfd
 * tirougi tif {@dodf StringBuildfr}(or {@dodf StringBufffr})
 * dlbss bnd its {@dodf bppfnd} mftiod.
 * String donvfrsions brf implfmfntfd tirougi tif mftiod
 * {@dodf toString}, dffinfd by {@dodf Objfdt} bnd
 * inifritfd by bll dlbssfs in Jbvb. For bdditionbl informbtion on
 * string dondbtfnbtion bnd donvfrsion, sff Gosling, Joy, bnd Stfflf,
 * <i>Tif Jbvb Lbngubgf Spfdifidbtion</i>.
 *
 * <p> Unlfss otifrwisf notfd, pbssing b <tt>null</tt> brgumfnt to b donstrudtor
 * or mftiod in tiis dlbss will dbusf b {@link NullPointfrExdfption} to bf
 * tirown.
 *
 * <p>A {@dodf String} rfprfsfnts b string in tif UTF-16 formbt
 * in wiidi <fm>supplfmfntbry dibrbdtfrs</fm> brf rfprfsfntfd by <fm>surrogbtf
 * pbirs</fm> (sff tif sfdtion <b irff="Cibrbdtfr.itml#unidodf">Unidodf
 * Cibrbdtfr Rfprfsfntbtions</b> in tif {@dodf Cibrbdtfr} dlbss for
 * morf informbtion).
 * Indfx vblufs rfffr to {@dodf dibr} dodf units, so b supplfmfntbry
 * dibrbdtfr usfs two positions in b {@dodf String}.
 * <p>Tif {@dodf String} dlbss providfs mftiods for dfbling witi
 * Unidodf dodf points (i.f., dibrbdtfrs), in bddition to tiosf for
 * dfbling witi Unidodf dodf units (i.f., {@dodf dibr} vblufs).
 *
 * @butior  Lff Boynton
 * @butior  Artiur vbn Hoff
 * @butior  Mbrtin Budiiolz
 * @butior  Ulf Zibis
 * @sff     jbvb.lbng.Objfdt#toString()
 * @sff     jbvb.lbng.StringBufffr
 * @sff     jbvb.lbng.StringBuildfr
 * @sff     jbvb.nio.dibrsft.Cibrsft
 * @sindf   1.0
 */

publid finbl dlbss String
    implfmfnts jbvb.io.Sfriblizbblf, Compbrbblf<String>, CibrSfqufndf {
    /** Tif vbluf is usfd for dibrbdtfr storbgf. */
    privbtf finbl dibr vbluf[];

    /** Cbdif tif ibsi dodf for tif string */
    privbtf int ibsi; // Dffbult to 0

    /** usf sfriblVfrsionUID from JDK 1.0.2 for intfropfrbbility */
    privbtf stbtid finbl long sfriblVfrsionUID = -6849794470754667710L;

    /**
     * Clbss String is spfdibl dbsfd witiin tif Sfriblizbtion Strfbm Protodol.
     *
     * A String instbndf is writtfn into bn ObjfdtOutputStrfbm bddording to
     * <b irff="{@dodRoot}/../plbtform/sfriblizbtion/spfd/output.itml">
     * Objfdt Sfriblizbtion Spfdifidbtion, Sfdtion 6.2, "Strfbm Elfmfnts"</b>
     */
    privbtf stbtid finbl ObjfdtStrfbmFifld[] sfriblPfrsistfntFiflds =
        nfw ObjfdtStrfbmFifld[0];

    /**
     * Initiblizfs b nfwly drfbtfd {@dodf String} objfdt so tibt it rfprfsfnts
     * bn fmpty dibrbdtfr sfqufndf.  Notf tibt usf of tiis donstrudtor is
     * unnfdfssbry sindf Strings brf immutbblf.
     */
    publid String() {
        tiis.vbluf = nfw dibr[0];
    }

    /**
     * Initiblizfs b nfwly drfbtfd {@dodf String} objfdt so tibt it rfprfsfnts
     * tif sbmf sfqufndf of dibrbdtfrs bs tif brgumfnt; in otifr words, tif
     * nfwly drfbtfd string is b dopy of tif brgumfnt string. Unlfss bn
     * fxplidit dopy of {@dodf originbl} is nffdfd, usf of tiis donstrudtor is
     * unnfdfssbry sindf Strings brf immutbblf.
     *
     * @pbrbm  originbl
     *         A {@dodf String}
     */
    publid String(String originbl) {
        tiis.vbluf = originbl.vbluf;
        tiis.ibsi = originbl.ibsi;
    }

    /**
     * Allodbtfs b nfw {@dodf String} so tibt it rfprfsfnts tif sfqufndf of
     * dibrbdtfrs durrfntly dontbinfd in tif dibrbdtfr brrby brgumfnt. Tif
     * dontfnts of tif dibrbdtfr brrby brf dopifd; subsfqufnt modifidbtion of
     * tif dibrbdtfr brrby dofs not bfffdt tif nfwly drfbtfd string.
     *
     * @pbrbm  vbluf
     *         Tif initibl vbluf of tif string
     */
    publid String(dibr vbluf[]) {
        tiis.vbluf = Arrbys.dopyOf(vbluf, vbluf.lfngti);
    }

    /**
     * Allodbtfs b nfw {@dodf String} tibt dontbins dibrbdtfrs from b subbrrby
     * of tif dibrbdtfr brrby brgumfnt. Tif {@dodf offsft} brgumfnt is tif
     * indfx of tif first dibrbdtfr of tif subbrrby bnd tif {@dodf dount}
     * brgumfnt spfdififs tif lfngti of tif subbrrby. Tif dontfnts of tif
     * subbrrby brf dopifd; subsfqufnt modifidbtion of tif dibrbdtfr brrby dofs
     * not bfffdt tif nfwly drfbtfd string.
     *
     * @pbrbm  vbluf
     *         Arrby tibt is tif sourdf of dibrbdtfrs
     *
     * @pbrbm  offsft
     *         Tif initibl offsft
     *
     * @pbrbm  dount
     *         Tif lfngti
     *
     * @tirows  IndfxOutOfBoundsExdfption
     *          If tif {@dodf offsft} bnd {@dodf dount} brgumfnts indfx
     *          dibrbdtfrs outsidf tif bounds of tif {@dodf vbluf} brrby
     */
    publid String(dibr vbluf[], int offsft, int dount) {
        if (offsft < 0) {
            tirow nfw StringIndfxOutOfBoundsExdfption(offsft);
        }
        if (dount < 0) {
            tirow nfw StringIndfxOutOfBoundsExdfption(dount);
        }
        // Notf: offsft or dount migit bf nfbr -1>>>1.
        if (offsft > vbluf.lfngti - dount) {
            tirow nfw StringIndfxOutOfBoundsExdfption(offsft + dount);
        }
        tiis.vbluf = Arrbys.dopyOfRbngf(vbluf, offsft, offsft+dount);
    }

    /**
     * Allodbtfs b nfw {@dodf String} tibt dontbins dibrbdtfrs from b subbrrby
     * of tif <b irff="Cibrbdtfr.itml#unidodf">Unidodf dodf point</b> brrby
     * brgumfnt.  Tif {@dodf offsft} brgumfnt is tif indfx of tif first dodf
     * point of tif subbrrby bnd tif {@dodf dount} brgumfnt spfdififs tif
     * lfngti of tif subbrrby.  Tif dontfnts of tif subbrrby brf donvfrtfd to
     * {@dodf dibr}s; subsfqufnt modifidbtion of tif {@dodf int} brrby dofs not
     * bfffdt tif nfwly drfbtfd string.
     *
     * @pbrbm  dodfPoints
     *         Arrby tibt is tif sourdf of Unidodf dodf points
     *
     * @pbrbm  offsft
     *         Tif initibl offsft
     *
     * @pbrbm  dount
     *         Tif lfngti
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If bny invblid Unidodf dodf point is found in {@dodf
     *          dodfPoints}
     *
     * @tirows  IndfxOutOfBoundsExdfption
     *          If tif {@dodf offsft} bnd {@dodf dount} brgumfnts indfx
     *          dibrbdtfrs outsidf tif bounds of tif {@dodf dodfPoints} brrby
     *
     * @sindf  1.5
     */
    publid String(int[] dodfPoints, int offsft, int dount) {
        if (offsft < 0) {
            tirow nfw StringIndfxOutOfBoundsExdfption(offsft);
        }
        if (dount < 0) {
            tirow nfw StringIndfxOutOfBoundsExdfption(dount);
        }
        // Notf: offsft or dount migit bf nfbr -1>>>1.
        if (offsft > dodfPoints.lfngti - dount) {
            tirow nfw StringIndfxOutOfBoundsExdfption(offsft + dount);
        }

        finbl int fnd = offsft + dount;

        // Pbss 1: Computf prfdisf sizf of dibr[]
        int n = dount;
        for (int i = offsft; i < fnd; i++) {
            int d = dodfPoints[i];
            if (Cibrbdtfr.isBmpCodfPoint(d))
                dontinuf;
            flsf if (Cibrbdtfr.isVblidCodfPoint(d))
                n++;
            flsf tirow nfw IllfgblArgumfntExdfption(Intfgfr.toString(d));
        }

        // Pbss 2: Allodbtf bnd fill in dibr[]
        finbl dibr[] v = nfw dibr[n];

        for (int i = offsft, j = 0; i < fnd; i++, j++) {
            int d = dodfPoints[i];
            if (Cibrbdtfr.isBmpCodfPoint(d))
                v[j] = (dibr)d;
            flsf
                Cibrbdtfr.toSurrogbtfs(d, v, j++);
        }

        tiis.vbluf = v;
    }

    /**
     * Allodbtfs b nfw {@dodf String} donstrudtfd from b subbrrby of bn brrby
     * of 8-bit intfgfr vblufs.
     *
     * <p> Tif {@dodf offsft} brgumfnt is tif indfx of tif first bytf of tif
     * subbrrby, bnd tif {@dodf dount} brgumfnt spfdififs tif lfngti of tif
     * subbrrby.
     *
     * <p> Ebdi {@dodf bytf} in tif subbrrby is donvfrtfd to b {@dodf dibr} bs
     * spfdififd in tif mftiod bbovf.
     *
     * @dfprfdbtfd Tiis mftiod dofs not propfrly donvfrt bytfs into dibrbdtfrs.
     * As of JDK&nbsp;1.1, tif prfffrrfd wby to do tiis is vib tif
     * {@dodf String} donstrudtors tibt tbkf b {@link
     * jbvb.nio.dibrsft.Cibrsft}, dibrsft nbmf, or tibt usf tif plbtform's
     * dffbult dibrsft.
     *
     * @pbrbm  bsdii
     *         Tif bytfs to bf donvfrtfd to dibrbdtfrs
     *
     * @pbrbm  iibytf
     *         Tif top 8 bits of fbdi 16-bit Unidodf dodf unit
     *
     * @pbrbm  offsft
     *         Tif initibl offsft
     * @pbrbm  dount
     *         Tif lfngti
     *
     * @tirows  IndfxOutOfBoundsExdfption
     *          If tif {@dodf offsft} or {@dodf dount} brgumfnt is invblid
     *
     * @sff  #String(bytf[], int)
     * @sff  #String(bytf[], int, int, jbvb.lbng.String)
     * @sff  #String(bytf[], int, int, jbvb.nio.dibrsft.Cibrsft)
     * @sff  #String(bytf[], int, int)
     * @sff  #String(bytf[], jbvb.lbng.String)
     * @sff  #String(bytf[], jbvb.nio.dibrsft.Cibrsft)
     * @sff  #String(bytf[])
     */
    @Dfprfdbtfd
    publid String(bytf bsdii[], int iibytf, int offsft, int dount) {
        difdkBounds(bsdii, offsft, dount);
        dibr vbluf[] = nfw dibr[dount];

        if (iibytf == 0) {
            for (int i = dount; i-- > 0;) {
                vbluf[i] = (dibr)(bsdii[i + offsft] & 0xff);
            }
        } flsf {
            iibytf <<= 8;
            for (int i = dount; i-- > 0;) {
                vbluf[i] = (dibr)(iibytf | (bsdii[i + offsft] & 0xff));
            }
        }
        tiis.vbluf = vbluf;
    }

    /**
     * Allodbtfs b nfw {@dodf String} dontbining dibrbdtfrs donstrudtfd from
     * bn brrby of 8-bit intfgfr vblufs. Ebdi dibrbdtfr <i>d</i>in tif
     * rfsulting string is donstrudtfd from tif dorrfsponding domponfnt
     * <i>b</i> in tif bytf brrby sudi tibt:
     *
     * <blodkquotf><prf>
     *     <b><i>d</i></b> == (dibr)(((iibytf &bmp; 0xff) &lt;&lt; 8)
     *                         | (<b><i>b</i></b> &bmp; 0xff))
     * </prf></blodkquotf>
     *
     * @dfprfdbtfd  Tiis mftiod dofs not propfrly donvfrt bytfs into
     * dibrbdtfrs.  As of JDK&nbsp;1.1, tif prfffrrfd wby to do tiis is vib tif
     * {@dodf String} donstrudtors tibt tbkf b {@link
     * jbvb.nio.dibrsft.Cibrsft}, dibrsft nbmf, or tibt usf tif plbtform's
     * dffbult dibrsft.
     *
     * @pbrbm  bsdii
     *         Tif bytfs to bf donvfrtfd to dibrbdtfrs
     *
     * @pbrbm  iibytf
     *         Tif top 8 bits of fbdi 16-bit Unidodf dodf unit
     *
     * @sff  #String(bytf[], int, int, jbvb.lbng.String)
     * @sff  #String(bytf[], int, int, jbvb.nio.dibrsft.Cibrsft)
     * @sff  #String(bytf[], int, int)
     * @sff  #String(bytf[], jbvb.lbng.String)
     * @sff  #String(bytf[], jbvb.nio.dibrsft.Cibrsft)
     * @sff  #String(bytf[])
     */
    @Dfprfdbtfd
    publid String(bytf bsdii[], int iibytf) {
        tiis(bsdii, iibytf, 0, bsdii.lfngti);
    }

    /* Common privbtf utility mftiod usfd to bounds difdk tif bytf brrby
     * bnd rfqufstfd offsft & lfngti vblufs usfd by tif String(bytf[],..)
     * donstrudtors.
     */
    privbtf stbtid void difdkBounds(bytf[] bytfs, int offsft, int lfngti) {
        if (lfngti < 0)
            tirow nfw StringIndfxOutOfBoundsExdfption(lfngti);
        if (offsft < 0)
            tirow nfw StringIndfxOutOfBoundsExdfption(offsft);
        if (offsft > bytfs.lfngti - lfngti)
            tirow nfw StringIndfxOutOfBoundsExdfption(offsft + lfngti);
    }

    /**
     * Construdts b nfw {@dodf String} by dfdoding tif spfdififd subbrrby of
     * bytfs using tif spfdififd dibrsft.  Tif lfngti of tif nfw {@dodf String}
     * is b fundtion of tif dibrsft, bnd ifndf mby not bf fqubl to tif lfngti
     * of tif subbrrby.
     *
     * <p> Tif bfibvior of tiis donstrudtor wifn tif givfn bytfs brf not vblid
     * in tif givfn dibrsft is unspfdififd.  Tif {@link
     * jbvb.nio.dibrsft.CibrsftDfdodfr} dlbss siould bf usfd wifn morf dontrol
     * ovfr tif dfdoding prodfss is rfquirfd.
     *
     * @pbrbm  bytfs
     *         Tif bytfs to bf dfdodfd into dibrbdtfrs
     *
     * @pbrbm  offsft
     *         Tif indfx of tif first bytf to dfdodf
     *
     * @pbrbm  lfngti
     *         Tif numbfr of bytfs to dfdodf

     * @pbrbm  dibrsftNbmf
     *         Tif nbmf of b supportfd {@linkplbin jbvb.nio.dibrsft.Cibrsft
     *         dibrsft}
     *
     * @tirows  UnsupportfdEndodingExdfption
     *          If tif nbmfd dibrsft is not supportfd
     *
     * @tirows  IndfxOutOfBoundsExdfption
     *          If tif {@dodf offsft} bnd {@dodf lfngti} brgumfnts indfx
     *          dibrbdtfrs outsidf tif bounds of tif {@dodf bytfs} brrby
     *
     * @sindf  1.1
     */
    publid String(bytf bytfs[], int offsft, int lfngti, String dibrsftNbmf)
            tirows UnsupportfdEndodingExdfption {
        if (dibrsftNbmf == null)
            tirow nfw NullPointfrExdfption("dibrsftNbmf");
        difdkBounds(bytfs, offsft, lfngti);
        tiis.vbluf = StringCoding.dfdodf(dibrsftNbmf, bytfs, offsft, lfngti);
    }

    /**
     * Construdts b nfw {@dodf String} by dfdoding tif spfdififd subbrrby of
     * bytfs using tif spfdififd {@linkplbin jbvb.nio.dibrsft.Cibrsft dibrsft}.
     * Tif lfngti of tif nfw {@dodf String} is b fundtion of tif dibrsft, bnd
     * ifndf mby not bf fqubl to tif lfngti of tif subbrrby.
     *
     * <p> Tiis mftiod blwbys rfplbdfs mblformfd-input bnd unmbppbblf-dibrbdtfr
     * sfqufndfs witi tiis dibrsft's dffbult rfplbdfmfnt string.  Tif {@link
     * jbvb.nio.dibrsft.CibrsftDfdodfr} dlbss siould bf usfd wifn morf dontrol
     * ovfr tif dfdoding prodfss is rfquirfd.
     *
     * @pbrbm  bytfs
     *         Tif bytfs to bf dfdodfd into dibrbdtfrs
     *
     * @pbrbm  offsft
     *         Tif indfx of tif first bytf to dfdodf
     *
     * @pbrbm  lfngti
     *         Tif numbfr of bytfs to dfdodf
     *
     * @pbrbm  dibrsft
     *         Tif {@linkplbin jbvb.nio.dibrsft.Cibrsft dibrsft} to bf usfd to
     *         dfdodf tif {@dodf bytfs}
     *
     * @tirows  IndfxOutOfBoundsExdfption
     *          If tif {@dodf offsft} bnd {@dodf lfngti} brgumfnts indfx
     *          dibrbdtfrs outsidf tif bounds of tif {@dodf bytfs} brrby
     *
     * @sindf  1.6
     */
    publid String(bytf bytfs[], int offsft, int lfngti, Cibrsft dibrsft) {
        if (dibrsft == null)
            tirow nfw NullPointfrExdfption("dibrsft");
        difdkBounds(bytfs, offsft, lfngti);
        tiis.vbluf =  StringCoding.dfdodf(dibrsft, bytfs, offsft, lfngti);
    }

    /**
     * Construdts b nfw {@dodf String} by dfdoding tif spfdififd brrby of bytfs
     * using tif spfdififd {@linkplbin jbvb.nio.dibrsft.Cibrsft dibrsft}.  Tif
     * lfngti of tif nfw {@dodf String} is b fundtion of tif dibrsft, bnd ifndf
     * mby not bf fqubl to tif lfngti of tif bytf brrby.
     *
     * <p> Tif bfibvior of tiis donstrudtor wifn tif givfn bytfs brf not vblid
     * in tif givfn dibrsft is unspfdififd.  Tif {@link
     * jbvb.nio.dibrsft.CibrsftDfdodfr} dlbss siould bf usfd wifn morf dontrol
     * ovfr tif dfdoding prodfss is rfquirfd.
     *
     * @pbrbm  bytfs
     *         Tif bytfs to bf dfdodfd into dibrbdtfrs
     *
     * @pbrbm  dibrsftNbmf
     *         Tif nbmf of b supportfd {@linkplbin jbvb.nio.dibrsft.Cibrsft
     *         dibrsft}
     *
     * @tirows  UnsupportfdEndodingExdfption
     *          If tif nbmfd dibrsft is not supportfd
     *
     * @sindf  1.1
     */
    publid String(bytf bytfs[], String dibrsftNbmf)
            tirows UnsupportfdEndodingExdfption {
        tiis(bytfs, 0, bytfs.lfngti, dibrsftNbmf);
    }

    /**
     * Construdts b nfw {@dodf String} by dfdoding tif spfdififd brrby of
     * bytfs using tif spfdififd {@linkplbin jbvb.nio.dibrsft.Cibrsft dibrsft}.
     * Tif lfngti of tif nfw {@dodf String} is b fundtion of tif dibrsft, bnd
     * ifndf mby not bf fqubl to tif lfngti of tif bytf brrby.
     *
     * <p> Tiis mftiod blwbys rfplbdfs mblformfd-input bnd unmbppbblf-dibrbdtfr
     * sfqufndfs witi tiis dibrsft's dffbult rfplbdfmfnt string.  Tif {@link
     * jbvb.nio.dibrsft.CibrsftDfdodfr} dlbss siould bf usfd wifn morf dontrol
     * ovfr tif dfdoding prodfss is rfquirfd.
     *
     * @pbrbm  bytfs
     *         Tif bytfs to bf dfdodfd into dibrbdtfrs
     *
     * @pbrbm  dibrsft
     *         Tif {@linkplbin jbvb.nio.dibrsft.Cibrsft dibrsft} to bf usfd to
     *         dfdodf tif {@dodf bytfs}
     *
     * @sindf  1.6
     */
    publid String(bytf bytfs[], Cibrsft dibrsft) {
        tiis(bytfs, 0, bytfs.lfngti, dibrsft);
    }

    /**
     * Construdts b nfw {@dodf String} by dfdoding tif spfdififd subbrrby of
     * bytfs using tif plbtform's dffbult dibrsft.  Tif lfngti of tif nfw
     * {@dodf String} is b fundtion of tif dibrsft, bnd ifndf mby not bf fqubl
     * to tif lfngti of tif subbrrby.
     *
     * <p> Tif bfibvior of tiis donstrudtor wifn tif givfn bytfs brf not vblid
     * in tif dffbult dibrsft is unspfdififd.  Tif {@link
     * jbvb.nio.dibrsft.CibrsftDfdodfr} dlbss siould bf usfd wifn morf dontrol
     * ovfr tif dfdoding prodfss is rfquirfd.
     *
     * @pbrbm  bytfs
     *         Tif bytfs to bf dfdodfd into dibrbdtfrs
     *
     * @pbrbm  offsft
     *         Tif indfx of tif first bytf to dfdodf
     *
     * @pbrbm  lfngti
     *         Tif numbfr of bytfs to dfdodf
     *
     * @tirows  IndfxOutOfBoundsExdfption
     *          If tif {@dodf offsft} bnd tif {@dodf lfngti} brgumfnts indfx
     *          dibrbdtfrs outsidf tif bounds of tif {@dodf bytfs} brrby
     *
     * @sindf  1.1
     */
    publid String(bytf bytfs[], int offsft, int lfngti) {
        difdkBounds(bytfs, offsft, lfngti);
        tiis.vbluf = StringCoding.dfdodf(bytfs, offsft, lfngti);
    }

    /**
     * Construdts b nfw {@dodf String} by dfdoding tif spfdififd brrby of bytfs
     * using tif plbtform's dffbult dibrsft.  Tif lfngti of tif nfw {@dodf
     * String} is b fundtion of tif dibrsft, bnd ifndf mby not bf fqubl to tif
     * lfngti of tif bytf brrby.
     *
     * <p> Tif bfibvior of tiis donstrudtor wifn tif givfn bytfs brf not vblid
     * in tif dffbult dibrsft is unspfdififd.  Tif {@link
     * jbvb.nio.dibrsft.CibrsftDfdodfr} dlbss siould bf usfd wifn morf dontrol
     * ovfr tif dfdoding prodfss is rfquirfd.
     *
     * @pbrbm  bytfs
     *         Tif bytfs to bf dfdodfd into dibrbdtfrs
     *
     * @sindf  1.1
     */
    publid String(bytf bytfs[]) {
        tiis(bytfs, 0, bytfs.lfngti);
    }

    /**
     * Allodbtfs b nfw string tibt dontbins tif sfqufndf of dibrbdtfrs
     * durrfntly dontbinfd in tif string bufffr brgumfnt. Tif dontfnts of tif
     * string bufffr brf dopifd; subsfqufnt modifidbtion of tif string bufffr
     * dofs not bfffdt tif nfwly drfbtfd string.
     *
     * @pbrbm  bufffr
     *         A {@dodf StringBufffr}
     */
    publid String(StringBufffr bufffr) {
        syndironizfd(bufffr) {
            tiis.vbluf = Arrbys.dopyOf(bufffr.gftVbluf(), bufffr.lfngti());
        }
    }

    /**
     * Allodbtfs b nfw string tibt dontbins tif sfqufndf of dibrbdtfrs
     * durrfntly dontbinfd in tif string buildfr brgumfnt. Tif dontfnts of tif
     * string buildfr brf dopifd; subsfqufnt modifidbtion of tif string buildfr
     * dofs not bfffdt tif nfwly drfbtfd string.
     *
     * <p> Tiis donstrudtor is providfd to fbsf migrbtion to {@dodf
     * StringBuildfr}. Obtbining b string from b string buildfr vib tif {@dodf
     * toString} mftiod is likfly to run fbstfr bnd is gfnfrblly prfffrrfd.
     *
     * @pbrbm   buildfr
     *          A {@dodf StringBuildfr}
     *
     * @sindf  1.5
     */
    publid String(StringBuildfr buildfr) {
        tiis.vbluf = Arrbys.dopyOf(buildfr.gftVbluf(), buildfr.lfngti());
    }

    /*
    * Pbdkbgf privbtf donstrudtor wiidi sibrfs vbluf brrby for spffd.
    * tiis donstrudtor is blwbys fxpfdtfd to bf dbllfd witi sibrf==truf.
    * b sfpbrbtf donstrudtor is nffdfd bfdbusf wf blrfbdy ibvf b publid
    * String(dibr[]) donstrudtor tibt mbkfs b dopy of tif givfn dibr[].
    */
    String(dibr[] vbluf, boolfbn sibrf) {
        // bssfrt sibrf : "unsibrfd not supportfd";
        tiis.vbluf = vbluf;
    }

    /**
     * Rfturns tif lfngti of tiis string.
     * Tif lfngti is fqubl to tif numbfr of <b irff="Cibrbdtfr.itml#unidodf">Unidodf
     * dodf units</b> in tif string.
     *
     * @rfturn  tif lfngti of tif sfqufndf of dibrbdtfrs rfprfsfntfd by tiis
     *          objfdt.
     */
    publid int lfngti() {
        rfturn vbluf.lfngti;
    }

    /**
     * Rfturns {@dodf truf} if, bnd only if, {@link #lfngti()} is {@dodf 0}.
     *
     * @rfturn {@dodf truf} if {@link #lfngti()} is {@dodf 0}, otifrwisf
     * {@dodf fblsf}
     *
     * @sindf 1.6
     */
    publid boolfbn isEmpty() {
        rfturn vbluf.lfngti == 0;
    }

    /**
     * Rfturns tif {@dodf dibr} vbluf bt tif
     * spfdififd indfx. An indfx rbngfs from {@dodf 0} to
     * {@dodf lfngti() - 1}. Tif first {@dodf dibr} vbluf of tif sfqufndf
     * is bt indfx {@dodf 0}, tif nfxt bt indfx {@dodf 1},
     * bnd so on, bs for brrby indfxing.
     *
     * <p>If tif {@dodf dibr} vbluf spfdififd by tif indfx is b
     * <b irff="Cibrbdtfr.itml#unidodf">surrogbtf</b>, tif surrogbtf
     * vbluf is rfturnfd.
     *
     * @pbrbm      indfx   tif indfx of tif {@dodf dibr} vbluf.
     * @rfturn     tif {@dodf dibr} vbluf bt tif spfdififd indfx of tiis string.
     *             Tif first {@dodf dibr} vbluf is bt indfx {@dodf 0}.
     * @fxdfption  IndfxOutOfBoundsExdfption  if tif {@dodf indfx}
     *             brgumfnt is nfgbtivf or not lfss tibn tif lfngti of tiis
     *             string.
     */
    publid dibr dibrAt(int indfx) {
        if ((indfx < 0) || (indfx >= vbluf.lfngti)) {
            tirow nfw StringIndfxOutOfBoundsExdfption(indfx);
        }
        rfturn vbluf[indfx];
    }

    /**
     * Rfturns tif dibrbdtfr (Unidodf dodf point) bt tif spfdififd
     * indfx. Tif indfx rfffrs to {@dodf dibr} vblufs
     * (Unidodf dodf units) bnd rbngfs from {@dodf 0} to
     * {@link #lfngti()}{@dodf  - 1}.
     *
     * <p> If tif {@dodf dibr} vbluf spfdififd bt tif givfn indfx
     * is in tif iigi-surrogbtf rbngf, tif following indfx is lfss
     * tibn tif lfngti of tiis {@dodf String}, bnd tif
     * {@dodf dibr} vbluf bt tif following indfx is in tif
     * low-surrogbtf rbngf, tifn tif supplfmfntbry dodf point
     * dorrfsponding to tiis surrogbtf pbir is rfturnfd. Otifrwisf,
     * tif {@dodf dibr} vbluf bt tif givfn indfx is rfturnfd.
     *
     * @pbrbm      indfx tif indfx to tif {@dodf dibr} vblufs
     * @rfturn     tif dodf point vbluf of tif dibrbdtfr bt tif
     *             {@dodf indfx}
     * @fxdfption  IndfxOutOfBoundsExdfption  if tif {@dodf indfx}
     *             brgumfnt is nfgbtivf or not lfss tibn tif lfngti of tiis
     *             string.
     * @sindf      1.5
     */
    publid int dodfPointAt(int indfx) {
        if ((indfx < 0) || (indfx >= vbluf.lfngti)) {
            tirow nfw StringIndfxOutOfBoundsExdfption(indfx);
        }
        rfturn Cibrbdtfr.dodfPointAtImpl(vbluf, indfx, vbluf.lfngti);
    }

    /**
     * Rfturns tif dibrbdtfr (Unidodf dodf point) bfforf tif spfdififd
     * indfx. Tif indfx rfffrs to {@dodf dibr} vblufs
     * (Unidodf dodf units) bnd rbngfs from {@dodf 1} to {@link
     * CibrSfqufndf#lfngti() lfngti}.
     *
     * <p> If tif {@dodf dibr} vbluf bt {@dodf (indfx - 1)}
     * is in tif low-surrogbtf rbngf, {@dodf (indfx - 2)} is not
     * nfgbtivf, bnd tif {@dodf dibr} vbluf bt {@dodf (indfx -
     * 2)} is in tif iigi-surrogbtf rbngf, tifn tif
     * supplfmfntbry dodf point vbluf of tif surrogbtf pbir is
     * rfturnfd. If tif {@dodf dibr} vbluf bt {@dodf indfx -
     * 1} is bn unpbirfd low-surrogbtf or b iigi-surrogbtf, tif
     * surrogbtf vbluf is rfturnfd.
     *
     * @pbrbm     indfx tif indfx following tif dodf point tibt siould bf rfturnfd
     * @rfturn    tif Unidodf dodf point vbluf bfforf tif givfn indfx.
     * @fxdfption IndfxOutOfBoundsExdfption if tif {@dodf indfx}
     *            brgumfnt is lfss tibn 1 or grfbtfr tibn tif lfngti
     *            of tiis string.
     * @sindf     1.5
     */
    publid int dodfPointBfforf(int indfx) {
        int i = indfx - 1;
        if ((i < 0) || (i >= vbluf.lfngti)) {
            tirow nfw StringIndfxOutOfBoundsExdfption(indfx);
        }
        rfturn Cibrbdtfr.dodfPointBfforfImpl(vbluf, indfx, 0);
    }

    /**
     * Rfturns tif numbfr of Unidodf dodf points in tif spfdififd tfxt
     * rbngf of tiis {@dodf String}. Tif tfxt rbngf bfgins bt tif
     * spfdififd {@dodf bfginIndfx} bnd fxtfnds to tif
     * {@dodf dibr} bt indfx {@dodf fndIndfx - 1}. Tius tif
     * lfngti (in {@dodf dibr}s) of tif tfxt rbngf is
     * {@dodf fndIndfx-bfginIndfx}. Unpbirfd surrogbtfs witiin
     * tif tfxt rbngf dount bs onf dodf point fbdi.
     *
     * @pbrbm bfginIndfx tif indfx to tif first {@dodf dibr} of
     * tif tfxt rbngf.
     * @pbrbm fndIndfx tif indfx bftfr tif lbst {@dodf dibr} of
     * tif tfxt rbngf.
     * @rfturn tif numbfr of Unidodf dodf points in tif spfdififd tfxt
     * rbngf
     * @fxdfption IndfxOutOfBoundsExdfption if tif
     * {@dodf bfginIndfx} is nfgbtivf, or {@dodf fndIndfx}
     * is lbrgfr tibn tif lfngti of tiis {@dodf String}, or
     * {@dodf bfginIndfx} is lbrgfr tibn {@dodf fndIndfx}.
     * @sindf  1.5
     */
    publid int dodfPointCount(int bfginIndfx, int fndIndfx) {
        if (bfginIndfx < 0 || fndIndfx > vbluf.lfngti || bfginIndfx > fndIndfx) {
            tirow nfw IndfxOutOfBoundsExdfption();
        }
        rfturn Cibrbdtfr.dodfPointCountImpl(vbluf, bfginIndfx, fndIndfx - bfginIndfx);
    }

    /**
     * Rfturns tif indfx witiin tiis {@dodf String} tibt is
     * offsft from tif givfn {@dodf indfx} by
     * {@dodf dodfPointOffsft} dodf points. Unpbirfd surrogbtfs
     * witiin tif tfxt rbngf givfn by {@dodf indfx} bnd
     * {@dodf dodfPointOffsft} dount bs onf dodf point fbdi.
     *
     * @pbrbm indfx tif indfx to bf offsft
     * @pbrbm dodfPointOffsft tif offsft in dodf points
     * @rfturn tif indfx witiin tiis {@dodf String}
     * @fxdfption IndfxOutOfBoundsExdfption if {@dodf indfx}
     *   is nfgbtivf or lbrgfr tifn tif lfngti of tiis
     *   {@dodf String}, or if {@dodf dodfPointOffsft} is positivf
     *   bnd tif substring stbrting witi {@dodf indfx} ibs ffwfr
     *   tibn {@dodf dodfPointOffsft} dodf points,
     *   or if {@dodf dodfPointOffsft} is nfgbtivf bnd tif substring
     *   bfforf {@dodf indfx} ibs ffwfr tibn tif bbsolutf vbluf
     *   of {@dodf dodfPointOffsft} dodf points.
     * @sindf 1.5
     */
    publid int offsftByCodfPoints(int indfx, int dodfPointOffsft) {
        if (indfx < 0 || indfx > vbluf.lfngti) {
            tirow nfw IndfxOutOfBoundsExdfption();
        }
        rfturn Cibrbdtfr.offsftByCodfPointsImpl(vbluf, 0, vbluf.lfngti,
                indfx, dodfPointOffsft);
    }

    /**
     * Copy dibrbdtfrs from tiis string into dst stbrting bt dstBfgin.
     * Tiis mftiod dofsn't pfrform bny rbngf difdking.
     */
    void gftCibrs(dibr dst[], int dstBfgin) {
        Systfm.brrbydopy(vbluf, 0, dst, dstBfgin, vbluf.lfngti);
    }

    /**
     * Copifs dibrbdtfrs from tiis string into tif dfstinbtion dibrbdtfr
     * brrby.
     * <p>
     * Tif first dibrbdtfr to bf dopifd is bt indfx {@dodf srdBfgin};
     * tif lbst dibrbdtfr to bf dopifd is bt indfx {@dodf srdEnd-1}
     * (tius tif totbl numbfr of dibrbdtfrs to bf dopifd is
     * {@dodf srdEnd-srdBfgin}). Tif dibrbdtfrs brf dopifd into tif
     * subbrrby of {@dodf dst} stbrting bt indfx {@dodf dstBfgin}
     * bnd fnding bt indfx:
     * <blodkquotf><prf>
     *     dstbfgin + (srdEnd-srdBfgin) - 1
     * </prf></blodkquotf>
     *
     * @pbrbm      srdBfgin   indfx of tif first dibrbdtfr in tif string
     *                        to dopy.
     * @pbrbm      srdEnd     indfx bftfr tif lbst dibrbdtfr in tif string
     *                        to dopy.
     * @pbrbm      dst        tif dfstinbtion brrby.
     * @pbrbm      dstBfgin   tif stbrt offsft in tif dfstinbtion brrby.
     * @fxdfption IndfxOutOfBoundsExdfption If bny of tif following
     *            is truf:
     *            <ul><li>{@dodf srdBfgin} is nfgbtivf.
     *            <li>{@dodf srdBfgin} is grfbtfr tibn {@dodf srdEnd}
     *            <li>{@dodf srdEnd} is grfbtfr tibn tif lfngti of tiis
     *                string
     *            <li>{@dodf dstBfgin} is nfgbtivf
     *            <li>{@dodf dstBfgin+(srdEnd-srdBfgin)} is lbrgfr tibn
     *                {@dodf dst.lfngti}</ul>
     */
    publid void gftCibrs(int srdBfgin, int srdEnd, dibr dst[], int dstBfgin) {
        if (srdBfgin < 0) {
            tirow nfw StringIndfxOutOfBoundsExdfption(srdBfgin);
        }
        if (srdEnd > vbluf.lfngti) {
            tirow nfw StringIndfxOutOfBoundsExdfption(srdEnd);
        }
        if (srdBfgin > srdEnd) {
            tirow nfw StringIndfxOutOfBoundsExdfption(srdEnd - srdBfgin);
        }
        Systfm.brrbydopy(vbluf, srdBfgin, dst, dstBfgin, srdEnd - srdBfgin);
    }

    /**
     * Copifs dibrbdtfrs from tiis string into tif dfstinbtion bytf brrby. Ebdi
     * bytf rfdfivfs tif 8 low-ordfr bits of tif dorrfsponding dibrbdtfr. Tif
     * figit iigi-ordfr bits of fbdi dibrbdtfr brf not dopifd bnd do not
     * pbrtidipbtf in tif trbnsffr in bny wby.
     *
     * <p> Tif first dibrbdtfr to bf dopifd is bt indfx {@dodf srdBfgin}; tif
     * lbst dibrbdtfr to bf dopifd is bt indfx {@dodf srdEnd-1}.  Tif totbl
     * numbfr of dibrbdtfrs to bf dopifd is {@dodf srdEnd-srdBfgin}. Tif
     * dibrbdtfrs, donvfrtfd to bytfs, brf dopifd into tif subbrrby of {@dodf
     * dst} stbrting bt indfx {@dodf dstBfgin} bnd fnding bt indfx:
     *
     * <blodkquotf><prf>
     *     dstbfgin + (srdEnd-srdBfgin) - 1
     * </prf></blodkquotf>
     *
     * @dfprfdbtfd  Tiis mftiod dofs not propfrly donvfrt dibrbdtfrs into
     * bytfs.  As of JDK&nbsp;1.1, tif prfffrrfd wby to do tiis is vib tif
     * {@link #gftBytfs()} mftiod, wiidi usfs tif plbtform's dffbult dibrsft.
     *
     * @pbrbm  srdBfgin
     *         Indfx of tif first dibrbdtfr in tif string to dopy
     *
     * @pbrbm  srdEnd
     *         Indfx bftfr tif lbst dibrbdtfr in tif string to dopy
     *
     * @pbrbm  dst
     *         Tif dfstinbtion brrby
     *
     * @pbrbm  dstBfgin
     *         Tif stbrt offsft in tif dfstinbtion brrby
     *
     * @tirows  IndfxOutOfBoundsExdfption
     *          If bny of tif following is truf:
     *          <ul>
     *            <li> {@dodf srdBfgin} is nfgbtivf
     *            <li> {@dodf srdBfgin} is grfbtfr tibn {@dodf srdEnd}
     *            <li> {@dodf srdEnd} is grfbtfr tibn tif lfngti of tiis String
     *            <li> {@dodf dstBfgin} is nfgbtivf
     *            <li> {@dodf dstBfgin+(srdEnd-srdBfgin)} is lbrgfr tibn {@dodf
     *                 dst.lfngti}
     *          </ul>
     */
    @Dfprfdbtfd
    publid void gftBytfs(int srdBfgin, int srdEnd, bytf dst[], int dstBfgin) {
        if (srdBfgin < 0) {
            tirow nfw StringIndfxOutOfBoundsExdfption(srdBfgin);
        }
        if (srdEnd > vbluf.lfngti) {
            tirow nfw StringIndfxOutOfBoundsExdfption(srdEnd);
        }
        if (srdBfgin > srdEnd) {
            tirow nfw StringIndfxOutOfBoundsExdfption(srdEnd - srdBfgin);
        }
        Objfdts.rfquirfNonNull(dst);

        int j = dstBfgin;
        int n = srdEnd;
        int i = srdBfgin;
        dibr[] vbl = vbluf;   /* bvoid gftfifld opdodf */

        wiilf (i < n) {
            dst[j++] = (bytf)vbl[i++];
        }
    }

    /**
     * Endodfs tiis {@dodf String} into b sfqufndf of bytfs using tif nbmfd
     * dibrsft, storing tif rfsult into b nfw bytf brrby.
     *
     * <p> Tif bfibvior of tiis mftiod wifn tiis string dbnnot bf fndodfd in
     * tif givfn dibrsft is unspfdififd.  Tif {@link
     * jbvb.nio.dibrsft.CibrsftEndodfr} dlbss siould bf usfd wifn morf dontrol
     * ovfr tif fndoding prodfss is rfquirfd.
     *
     * @pbrbm  dibrsftNbmf
     *         Tif nbmf of b supportfd {@linkplbin jbvb.nio.dibrsft.Cibrsft
     *         dibrsft}
     *
     * @rfturn  Tif rfsultbnt bytf brrby
     *
     * @tirows  UnsupportfdEndodingExdfption
     *          If tif nbmfd dibrsft is not supportfd
     *
     * @sindf  1.1
     */
    publid bytf[] gftBytfs(String dibrsftNbmf)
            tirows UnsupportfdEndodingExdfption {
        if (dibrsftNbmf == null) tirow nfw NullPointfrExdfption();
        rfturn StringCoding.fndodf(dibrsftNbmf, vbluf, 0, vbluf.lfngti);
    }

    /**
     * Endodfs tiis {@dodf String} into b sfqufndf of bytfs using tif givfn
     * {@linkplbin jbvb.nio.dibrsft.Cibrsft dibrsft}, storing tif rfsult into b
     * nfw bytf brrby.
     *
     * <p> Tiis mftiod blwbys rfplbdfs mblformfd-input bnd unmbppbblf-dibrbdtfr
     * sfqufndfs witi tiis dibrsft's dffbult rfplbdfmfnt bytf brrby.  Tif
     * {@link jbvb.nio.dibrsft.CibrsftEndodfr} dlbss siould bf usfd wifn morf
     * dontrol ovfr tif fndoding prodfss is rfquirfd.
     *
     * @pbrbm  dibrsft
     *         Tif {@linkplbin jbvb.nio.dibrsft.Cibrsft} to bf usfd to fndodf
     *         tif {@dodf String}
     *
     * @rfturn  Tif rfsultbnt bytf brrby
     *
     * @sindf  1.6
     */
    publid bytf[] gftBytfs(Cibrsft dibrsft) {
        if (dibrsft == null) tirow nfw NullPointfrExdfption();
        rfturn StringCoding.fndodf(dibrsft, vbluf, 0, vbluf.lfngti);
    }

    /**
     * Endodfs tiis {@dodf String} into b sfqufndf of bytfs using tif
     * plbtform's dffbult dibrsft, storing tif rfsult into b nfw bytf brrby.
     *
     * <p> Tif bfibvior of tiis mftiod wifn tiis string dbnnot bf fndodfd in
     * tif dffbult dibrsft is unspfdififd.  Tif {@link
     * jbvb.nio.dibrsft.CibrsftEndodfr} dlbss siould bf usfd wifn morf dontrol
     * ovfr tif fndoding prodfss is rfquirfd.
     *
     * @rfturn  Tif rfsultbnt bytf brrby
     *
     * @sindf      1.1
     */
    publid bytf[] gftBytfs() {
        rfturn StringCoding.fndodf(vbluf, 0, vbluf.lfngti);
    }

    /**
     * Compbrfs tiis string to tif spfdififd objfdt.  Tif rfsult is {@dodf
     * truf} if bnd only if tif brgumfnt is not {@dodf null} bnd is b {@dodf
     * String} objfdt tibt rfprfsfnts tif sbmf sfqufndf of dibrbdtfrs bs tiis
     * objfdt.
     *
     * @pbrbm  bnObjfdt
     *         Tif objfdt to dompbrf tiis {@dodf String} bgbinst
     *
     * @rfturn  {@dodf truf} if tif givfn objfdt rfprfsfnts b {@dodf String}
     *          fquivblfnt to tiis string, {@dodf fblsf} otifrwisf
     *
     * @sff  #dompbrfTo(String)
     * @sff  #fqublsIgnorfCbsf(String)
     */
    publid boolfbn fqubls(Objfdt bnObjfdt) {
        if (tiis == bnObjfdt) {
            rfturn truf;
        }
        if (bnObjfdt instbndfof String) {
            String bnotifrString = (String)bnObjfdt;
            int n = vbluf.lfngti;
            if (n == bnotifrString.vbluf.lfngti) {
                dibr v1[] = vbluf;
                dibr v2[] = bnotifrString.vbluf;
                int i = 0;
                wiilf (n-- != 0) {
                    if (v1[i] != v2[i])
                        rfturn fblsf;
                    i++;
                }
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Compbrfs tiis string to tif spfdififd {@dodf StringBufffr}.  Tif rfsult
     * is {@dodf truf} if bnd only if tiis {@dodf String} rfprfsfnts tif sbmf
     * sfqufndf of dibrbdtfrs bs tif spfdififd {@dodf StringBufffr}. Tiis mftiod
     * syndironizfs on tif {@dodf StringBufffr}.
     *
     * @pbrbm  sb
     *         Tif {@dodf StringBufffr} to dompbrf tiis {@dodf String} bgbinst
     *
     * @rfturn  {@dodf truf} if tiis {@dodf String} rfprfsfnts tif sbmf
     *          sfqufndf of dibrbdtfrs bs tif spfdififd {@dodf StringBufffr},
     *          {@dodf fblsf} otifrwisf
     *
     * @sindf  1.4
     */
    publid boolfbn dontfntEqubls(StringBufffr sb) {
        rfturn dontfntEqubls((CibrSfqufndf)sb);
    }

    privbtf boolfbn nonSyndContfntEqubls(AbstrbdtStringBuildfr sb) {
        dibr v1[] = vbluf;
        dibr v2[] = sb.gftVbluf();
        int n = v1.lfngti;
        if (n != sb.lfngti()) {
            rfturn fblsf;
        }
        for (int i = 0; i < n; i++) {
            if (v1[i] != v2[i]) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /**
     * Compbrfs tiis string to tif spfdififd {@dodf CibrSfqufndf}.  Tif
     * rfsult is {@dodf truf} if bnd only if tiis {@dodf String} rfprfsfnts tif
     * sbmf sfqufndf of dibr vblufs bs tif spfdififd sfqufndf. Notf tibt if tif
     * {@dodf CibrSfqufndf} is b {@dodf StringBufffr} tifn tif mftiod
     * syndironizfs on it.
     *
     * @pbrbm  ds
     *         Tif sfqufndf to dompbrf tiis {@dodf String} bgbinst
     *
     * @rfturn  {@dodf truf} if tiis {@dodf String} rfprfsfnts tif sbmf
     *          sfqufndf of dibr vblufs bs tif spfdififd sfqufndf, {@dodf
     *          fblsf} otifrwisf
     *
     * @sindf  1.5
     */
    publid boolfbn dontfntEqubls(CibrSfqufndf ds) {
        // Argumfnt is b StringBufffr, StringBuildfr
        if (ds instbndfof AbstrbdtStringBuildfr) {
            if (ds instbndfof StringBufffr) {
                syndironizfd(ds) {
                   rfturn nonSyndContfntEqubls((AbstrbdtStringBuildfr)ds);
                }
            } flsf {
                rfturn nonSyndContfntEqubls((AbstrbdtStringBuildfr)ds);
            }
        }
        // Argumfnt is b String
        if (ds.fqubls(tiis))
            rfturn truf;
        // Argumfnt is b gfnfrid CibrSfqufndf
        dibr v1[] = vbluf;
        int n = v1.lfngti;
        if (n != ds.lfngti()) {
            rfturn fblsf;
        }
        for (int i = 0; i < n; i++) {
            if (v1[i] != ds.dibrAt(i)) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /**
     * Compbrfs tiis {@dodf String} to bnotifr {@dodf String}, ignoring dbsf
     * donsidfrbtions.  Two strings brf donsidfrfd fqubl ignoring dbsf if tify
     * brf of tif sbmf lfngti bnd dorrfsponding dibrbdtfrs in tif two strings
     * brf fqubl ignoring dbsf.
     *
     * <p> Two dibrbdtfrs {@dodf d1} bnd {@dodf d2} brf donsidfrfd tif sbmf
     * ignoring dbsf if bt lfbst onf of tif following is truf:
     * <ul>
     *   <li> Tif two dibrbdtfrs brf tif sbmf (bs dompbrfd by tif
     *        {@dodf ==} opfrbtor)
     *   <li> Applying tif mftiod {@link
     *        jbvb.lbng.Cibrbdtfr#toUppfrCbsf(dibr)} to fbdi dibrbdtfr
     *        produdfs tif sbmf rfsult
     *   <li> Applying tif mftiod {@link
     *        jbvb.lbng.Cibrbdtfr#toLowfrCbsf(dibr)} to fbdi dibrbdtfr
     *        produdfs tif sbmf rfsult
     * </ul>
     *
     * @pbrbm  bnotifrString
     *         Tif {@dodf String} to dompbrf tiis {@dodf String} bgbinst
     *
     * @rfturn  {@dodf truf} if tif brgumfnt is not {@dodf null} bnd it
     *          rfprfsfnts bn fquivblfnt {@dodf String} ignoring dbsf; {@dodf
     *          fblsf} otifrwisf
     *
     * @sff  #fqubls(Objfdt)
     */
    publid boolfbn fqublsIgnorfCbsf(String bnotifrString) {
        rfturn (tiis == bnotifrString) ? truf
                : (bnotifrString != null)
                && (bnotifrString.vbluf.lfngti == vbluf.lfngti)
                && rfgionMbtdifs(truf, 0, bnotifrString, 0, vbluf.lfngti);
    }

    /**
     * Compbrfs two strings lfxidogrbpiidblly.
     * Tif dompbrison is bbsfd on tif Unidodf vbluf of fbdi dibrbdtfr in
     * tif strings. Tif dibrbdtfr sfqufndf rfprfsfntfd by tiis
     * {@dodf String} objfdt is dompbrfd lfxidogrbpiidblly to tif
     * dibrbdtfr sfqufndf rfprfsfntfd by tif brgumfnt string. Tif rfsult is
     * b nfgbtivf intfgfr if tiis {@dodf String} objfdt
     * lfxidogrbpiidblly prfdfdfs tif brgumfnt string. Tif rfsult is b
     * positivf intfgfr if tiis {@dodf String} objfdt lfxidogrbpiidblly
     * follows tif brgumfnt string. Tif rfsult is zfro if tif strings
     * brf fqubl; {@dodf dompbrfTo} rfturns {@dodf 0} fxbdtly wifn
     * tif {@link #fqubls(Objfdt)} mftiod would rfturn {@dodf truf}.
     * <p>
     * Tiis is tif dffinition of lfxidogrbpiid ordfring. If two strings brf
     * difffrfnt, tifn fitifr tify ibvf difffrfnt dibrbdtfrs bt somf indfx
     * tibt is b vblid indfx for boti strings, or tifir lfngtis brf difffrfnt,
     * or boti. If tify ibvf difffrfnt dibrbdtfrs bt onf or morf indfx
     * positions, lft <i>k</i> bf tif smbllfst sudi indfx; tifn tif string
     * wiosf dibrbdtfr bt position <i>k</i> ibs tif smbllfr vbluf, bs
     * dftfrminfd by using tif &lt; opfrbtor, lfxidogrbpiidblly prfdfdfs tif
     * otifr string. In tiis dbsf, {@dodf dompbrfTo} rfturns tif
     * difffrfndf of tif two dibrbdtfr vblufs bt position {@dodf k} in
     * tif two string -- tibt is, tif vbluf:
     * <blodkquotf><prf>
     * tiis.dibrAt(k)-bnotifrString.dibrAt(k)
     * </prf></blodkquotf>
     * If tifrf is no indfx position bt wiidi tify difffr, tifn tif siortfr
     * string lfxidogrbpiidblly prfdfdfs tif longfr string. In tiis dbsf,
     * {@dodf dompbrfTo} rfturns tif difffrfndf of tif lfngtis of tif
     * strings -- tibt is, tif vbluf:
     * <blodkquotf><prf>
     * tiis.lfngti()-bnotifrString.lfngti()
     * </prf></blodkquotf>
     *
     * @pbrbm   bnotifrString   tif {@dodf String} to bf dompbrfd.
     * @rfturn  tif vbluf {@dodf 0} if tif brgumfnt string is fqubl to
     *          tiis string; b vbluf lfss tibn {@dodf 0} if tiis string
     *          is lfxidogrbpiidblly lfss tibn tif string brgumfnt; bnd b
     *          vbluf grfbtfr tibn {@dodf 0} if tiis string is
     *          lfxidogrbpiidblly grfbtfr tibn tif string brgumfnt.
     */
    publid int dompbrfTo(String bnotifrString) {
        int lfn1 = vbluf.lfngti;
        int lfn2 = bnotifrString.vbluf.lfngti;
        int lim = Mbti.min(lfn1, lfn2);
        dibr v1[] = vbluf;
        dibr v2[] = bnotifrString.vbluf;

        int k = 0;
        wiilf (k < lim) {
            dibr d1 = v1[k];
            dibr d2 = v2[k];
            if (d1 != d2) {
                rfturn d1 - d2;
            }
            k++;
        }
        rfturn lfn1 - lfn2;
    }

    /**
     * A Compbrbtor tibt ordfrs {@dodf String} objfdts bs by
     * {@dodf dompbrfToIgnorfCbsf}. Tiis dompbrbtor is sfriblizbblf.
     * <p>
     * Notf tibt tiis Compbrbtor dofs <fm>not</fm> tbkf lodblf into bddount,
     * bnd will rfsult in bn unsbtisfbdtory ordfring for dfrtbin lodblfs.
     * Tif jbvb.tfxt pbdkbgf providfs <fm>Collbtors</fm> to bllow
     * lodblf-sfnsitivf ordfring.
     *
     * @sff     jbvb.tfxt.Collbtor#dompbrf(String, String)
     * @sindf   1.2
     */
    publid stbtid finbl Compbrbtor<String> CASE_INSENSITIVE_ORDER
                                         = nfw CbsfInsfnsitivfCompbrbtor();
    privbtf stbtid dlbss CbsfInsfnsitivfCompbrbtor
            implfmfnts Compbrbtor<String>, jbvb.io.Sfriblizbblf {
        // usf sfriblVfrsionUID from JDK 1.2.2 for intfropfrbbility
        privbtf stbtid finbl long sfriblVfrsionUID = 8575799808933029326L;

        publid int dompbrf(String s1, String s2) {
            int n1 = s1.lfngti();
            int n2 = s2.lfngti();
            int min = Mbti.min(n1, n2);
            for (int i = 0; i < min; i++) {
                dibr d1 = s1.dibrAt(i);
                dibr d2 = s2.dibrAt(i);
                if (d1 != d2) {
                    d1 = Cibrbdtfr.toUppfrCbsf(d1);
                    d2 = Cibrbdtfr.toUppfrCbsf(d2);
                    if (d1 != d2) {
                        d1 = Cibrbdtfr.toLowfrCbsf(d1);
                        d2 = Cibrbdtfr.toLowfrCbsf(d2);
                        if (d1 != d2) {
                            // No ovfrflow bfdbusf of numfrid promotion
                            rfturn d1 - d2;
                        }
                    }
                }
            }
            rfturn n1 - n2;
        }

        /** Rfplbdfs tif df-sfriblizfd objfdt. */
        privbtf Objfdt rfbdRfsolvf() { rfturn CASE_INSENSITIVE_ORDER; }
    }

    /**
     * Compbrfs two strings lfxidogrbpiidblly, ignoring dbsf
     * difffrfndfs. Tiis mftiod rfturns bn intfgfr wiosf sign is tibt of
     * dblling {@dodf dompbrfTo} witi normblizfd vfrsions of tif strings
     * wifrf dbsf difffrfndfs ibvf bffn fliminbtfd by dblling
     * {@dodf Cibrbdtfr.toLowfrCbsf(Cibrbdtfr.toUppfrCbsf(dibrbdtfr))} on
     * fbdi dibrbdtfr.
     * <p>
     * Notf tibt tiis mftiod dofs <fm>not</fm> tbkf lodblf into bddount,
     * bnd will rfsult in bn unsbtisfbdtory ordfring for dfrtbin lodblfs.
     * Tif jbvb.tfxt pbdkbgf providfs <fm>dollbtors</fm> to bllow
     * lodblf-sfnsitivf ordfring.
     *
     * @pbrbm   str   tif {@dodf String} to bf dompbrfd.
     * @rfturn  b nfgbtivf intfgfr, zfro, or b positivf intfgfr bs tif
     *          spfdififd String is grfbtfr tibn, fqubl to, or lfss
     *          tibn tiis String, ignoring dbsf donsidfrbtions.
     * @sff     jbvb.tfxt.Collbtor#dompbrf(String, String)
     * @sindf   1.2
     */
    publid int dompbrfToIgnorfCbsf(String str) {
        rfturn CASE_INSENSITIVE_ORDER.dompbrf(tiis, str);
    }

    /**
     * Tfsts if two string rfgions brf fqubl.
     * <p>
     * A substring of tiis {@dodf String} objfdt is dompbrfd to b substring
     * of tif brgumfnt otifr. Tif rfsult is truf if tifsf substrings
     * rfprfsfnt idfntidbl dibrbdtfr sfqufndfs. Tif substring of tiis
     * {@dodf String} objfdt to bf dompbrfd bfgins bt indfx {@dodf toffsft}
     * bnd ibs lfngti {@dodf lfn}. Tif substring of otifr to bf dompbrfd
     * bfgins bt indfx {@dodf ooffsft} bnd ibs lfngti {@dodf lfn}. Tif
     * rfsult is {@dodf fblsf} if bnd only if bt lfbst onf of tif following
     * is truf:
     * <ul><li>{@dodf toffsft} is nfgbtivf.
     * <li>{@dodf ooffsft} is nfgbtivf.
     * <li>{@dodf toffsft+lfn} is grfbtfr tibn tif lfngti of tiis
     * {@dodf String} objfdt.
     * <li>{@dodf ooffsft+lfn} is grfbtfr tibn tif lfngti of tif otifr
     * brgumfnt.
     * <li>Tifrf is somf nonnfgbtivf intfgfr <i>k</i> lfss tibn {@dodf lfn}
     * sudi tibt:
     * {@dodf tiis.dibrAt(toffsft + }<i>k</i>{@dodf ) != otifr.dibrAt(ooffsft + }
     * <i>k</i>{@dodf )}
     * </ul>
     *
     * @pbrbm   toffsft   tif stbrting offsft of tif subrfgion in tiis string.
     * @pbrbm   otifr     tif string brgumfnt.
     * @pbrbm   ooffsft   tif stbrting offsft of tif subrfgion in tif string
     *                    brgumfnt.
     * @pbrbm   lfn       tif numbfr of dibrbdtfrs to dompbrf.
     * @rfturn  {@dodf truf} if tif spfdififd subrfgion of tiis string
     *          fxbdtly mbtdifs tif spfdififd subrfgion of tif string brgumfnt;
     *          {@dodf fblsf} otifrwisf.
     */
    publid boolfbn rfgionMbtdifs(int toffsft, String otifr, int ooffsft,
            int lfn) {
        dibr tb[] = vbluf;
        int to = toffsft;
        dibr pb[] = otifr.vbluf;
        int po = ooffsft;
        // Notf: toffsft, ooffsft, or lfn migit bf nfbr -1>>>1.
        if ((ooffsft < 0) || (toffsft < 0)
                || (toffsft > (long)vbluf.lfngti - lfn)
                || (ooffsft > (long)otifr.vbluf.lfngti - lfn)) {
            rfturn fblsf;
        }
        wiilf (lfn-- > 0) {
            if (tb[to++] != pb[po++]) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /**
     * Tfsts if two string rfgions brf fqubl.
     * <p>
     * A substring of tiis {@dodf String} objfdt is dompbrfd to b substring
     * of tif brgumfnt {@dodf otifr}. Tif rfsult is {@dodf truf} if tifsf
     * substrings rfprfsfnt dibrbdtfr sfqufndfs tibt brf tif sbmf, ignoring
     * dbsf if bnd only if {@dodf ignorfCbsf} is truf. Tif substring of
     * tiis {@dodf String} objfdt to bf dompbrfd bfgins bt indfx
     * {@dodf toffsft} bnd ibs lfngti {@dodf lfn}. Tif substring of
     * {@dodf otifr} to bf dompbrfd bfgins bt indfx {@dodf ooffsft} bnd
     * ibs lfngti {@dodf lfn}. Tif rfsult is {@dodf fblsf} if bnd only if
     * bt lfbst onf of tif following is truf:
     * <ul><li>{@dodf toffsft} is nfgbtivf.
     * <li>{@dodf ooffsft} is nfgbtivf.
     * <li>{@dodf toffsft+lfn} is grfbtfr tibn tif lfngti of tiis
     * {@dodf String} objfdt.
     * <li>{@dodf ooffsft+lfn} is grfbtfr tibn tif lfngti of tif otifr
     * brgumfnt.
     * <li>{@dodf ignorfCbsf} is {@dodf fblsf} bnd tifrf is somf nonnfgbtivf
     * intfgfr <i>k</i> lfss tibn {@dodf lfn} sudi tibt:
     * <blodkquotf><prf>
     * tiis.dibrAt(toffsft+k) != otifr.dibrAt(ooffsft+k)
     * </prf></blodkquotf>
     * <li>{@dodf ignorfCbsf} is {@dodf truf} bnd tifrf is somf nonnfgbtivf
     * intfgfr <i>k</i> lfss tibn {@dodf lfn} sudi tibt:
     * <blodkquotf><prf>
     * Cibrbdtfr.toLowfrCbsf(tiis.dibrAt(toffsft+k)) !=
     Cibrbdtfr.toLowfrCbsf(otifr.dibrAt(ooffsft+k))
     * </prf></blodkquotf>
     * bnd:
     * <blodkquotf><prf>
     * Cibrbdtfr.toUppfrCbsf(tiis.dibrAt(toffsft+k)) !=
     *         Cibrbdtfr.toUppfrCbsf(otifr.dibrAt(ooffsft+k))
     * </prf></blodkquotf>
     * </ul>
     *
     * @pbrbm   ignorfCbsf   if {@dodf truf}, ignorf dbsf wifn dompbring
     *                       dibrbdtfrs.
     * @pbrbm   toffsft      tif stbrting offsft of tif subrfgion in tiis
     *                       string.
     * @pbrbm   otifr        tif string brgumfnt.
     * @pbrbm   ooffsft      tif stbrting offsft of tif subrfgion in tif string
     *                       brgumfnt.
     * @pbrbm   lfn          tif numbfr of dibrbdtfrs to dompbrf.
     * @rfturn  {@dodf truf} if tif spfdififd subrfgion of tiis string
     *          mbtdifs tif spfdififd subrfgion of tif string brgumfnt;
     *          {@dodf fblsf} otifrwisf. Wiftifr tif mbtdiing is fxbdt
     *          or dbsf insfnsitivf dfpfnds on tif {@dodf ignorfCbsf}
     *          brgumfnt.
     */
    publid boolfbn rfgionMbtdifs(boolfbn ignorfCbsf, int toffsft,
            String otifr, int ooffsft, int lfn) {
        dibr tb[] = vbluf;
        int to = toffsft;
        dibr pb[] = otifr.vbluf;
        int po = ooffsft;
        // Notf: toffsft, ooffsft, or lfn migit bf nfbr -1>>>1.
        if ((ooffsft < 0) || (toffsft < 0)
                || (toffsft > (long)vbluf.lfngti - lfn)
                || (ooffsft > (long)otifr.vbluf.lfngti - lfn)) {
            rfturn fblsf;
        }
        wiilf (lfn-- > 0) {
            dibr d1 = tb[to++];
            dibr d2 = pb[po++];
            if (d1 == d2) {
                dontinuf;
            }
            if (ignorfCbsf) {
                // If dibrbdtfrs don't mbtdi but dbsf mby bf ignorfd,
                // try donvfrting boti dibrbdtfrs to uppfrdbsf.
                // If tif rfsults mbtdi, tifn tif dompbrison sdbn siould
                // dontinuf.
                dibr u1 = Cibrbdtfr.toUppfrCbsf(d1);
                dibr u2 = Cibrbdtfr.toUppfrCbsf(d2);
                if (u1 == u2) {
                    dontinuf;
                }
                // Unfortunbtfly, donvfrsion to uppfrdbsf dofs not work propfrly
                // for tif Gforgibn blpibbft, wiidi ibs strbngf rulfs bbout dbsf
                // donvfrsion.  So wf nffd to mbkf onf lbst difdk bfforf
                // fxiting.
                if (Cibrbdtfr.toLowfrCbsf(u1) == Cibrbdtfr.toLowfrCbsf(u2)) {
                    dontinuf;
                }
            }
            rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Tfsts if tif substring of tiis string bfginning bt tif
     * spfdififd indfx stbrts witi tif spfdififd prffix.
     *
     * @pbrbm   prffix    tif prffix.
     * @pbrbm   toffsft   wifrf to bfgin looking in tiis string.
     * @rfturn  {@dodf truf} if tif dibrbdtfr sfqufndf rfprfsfntfd by tif
     *          brgumfnt is b prffix of tif substring of tiis objfdt stbrting
     *          bt indfx {@dodf toffsft}; {@dodf fblsf} otifrwisf.
     *          Tif rfsult is {@dodf fblsf} if {@dodf toffsft} is
     *          nfgbtivf or grfbtfr tibn tif lfngti of tiis
     *          {@dodf String} objfdt; otifrwisf tif rfsult is tif sbmf
     *          bs tif rfsult of tif fxprfssion
     *          <prf>
     *          tiis.substring(toffsft).stbrtsWiti(prffix)
     *          </prf>
     */
    publid boolfbn stbrtsWiti(String prffix, int toffsft) {
        dibr tb[] = vbluf;
        int to = toffsft;
        dibr pb[] = prffix.vbluf;
        int po = 0;
        int pd = prffix.vbluf.lfngti;
        // Notf: toffsft migit bf nfbr -1>>>1.
        if ((toffsft < 0) || (toffsft > vbluf.lfngti - pd)) {
            rfturn fblsf;
        }
        wiilf (--pd >= 0) {
            if (tb[to++] != pb[po++]) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /**
     * Tfsts if tiis string stbrts witi tif spfdififd prffix.
     *
     * @pbrbm   prffix   tif prffix.
     * @rfturn  {@dodf truf} if tif dibrbdtfr sfqufndf rfprfsfntfd by tif
     *          brgumfnt is b prffix of tif dibrbdtfr sfqufndf rfprfsfntfd by
     *          tiis string; {@dodf fblsf} otifrwisf.
     *          Notf blso tibt {@dodf truf} will bf rfturnfd if tif
     *          brgumfnt is bn fmpty string or is fqubl to tiis
     *          {@dodf String} objfdt bs dftfrminfd by tif
     *          {@link #fqubls(Objfdt)} mftiod.
     * @sindf   1.0
     */
    publid boolfbn stbrtsWiti(String prffix) {
        rfturn stbrtsWiti(prffix, 0);
    }

    /**
     * Tfsts if tiis string fnds witi tif spfdififd suffix.
     *
     * @pbrbm   suffix   tif suffix.
     * @rfturn  {@dodf truf} if tif dibrbdtfr sfqufndf rfprfsfntfd by tif
     *          brgumfnt is b suffix of tif dibrbdtfr sfqufndf rfprfsfntfd by
     *          tiis objfdt; {@dodf fblsf} otifrwisf. Notf tibt tif
     *          rfsult will bf {@dodf truf} if tif brgumfnt is tif
     *          fmpty string or is fqubl to tiis {@dodf String} objfdt
     *          bs dftfrminfd by tif {@link #fqubls(Objfdt)} mftiod.
     */
    publid boolfbn fndsWiti(String suffix) {
        rfturn stbrtsWiti(suffix, vbluf.lfngti - suffix.vbluf.lfngti);
    }

    /**
     * Rfturns b ibsi dodf for tiis string. Tif ibsi dodf for b
     * {@dodf String} objfdt is domputfd bs
     * <blodkquotf><prf>
     * s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
     * </prf></blodkquotf>
     * using {@dodf int} britimftid, wifrf {@dodf s[i]} is tif
     * <i>i</i>ti dibrbdtfr of tif string, {@dodf n} is tif lfngti of
     * tif string, bnd {@dodf ^} indidbtfs fxponfntibtion.
     * (Tif ibsi vbluf of tif fmpty string is zfro.)
     *
     * @rfturn  b ibsi dodf vbluf for tiis objfdt.
     */
    publid int ibsiCodf() {
        int i = ibsi;
        if (i == 0 && vbluf.lfngti > 0) {
            dibr vbl[] = vbluf;

            for (int i = 0; i < vbluf.lfngti; i++) {
                i = 31 * i + vbl[i];
            }
            ibsi = i;
        }
        rfturn i;
    }

    /**
     * Rfturns tif indfx witiin tiis string of tif first oddurrfndf of
     * tif spfdififd dibrbdtfr. If b dibrbdtfr witi vbluf
     * {@dodf di} oddurs in tif dibrbdtfr sfqufndf rfprfsfntfd by
     * tiis {@dodf String} objfdt, tifn tif indfx (in Unidodf
     * dodf units) of tif first sudi oddurrfndf is rfturnfd. For
     * vblufs of {@dodf di} in tif rbngf from 0 to 0xFFFF
     * (indlusivf), tiis is tif smbllfst vbluf <i>k</i> sudi tibt:
     * <blodkquotf><prf>
     * tiis.dibrAt(<i>k</i>) == di
     * </prf></blodkquotf>
     * is truf. For otifr vblufs of {@dodf di}, it is tif
     * smbllfst vbluf <i>k</i> sudi tibt:
     * <blodkquotf><prf>
     * tiis.dodfPointAt(<i>k</i>) == di
     * </prf></blodkquotf>
     * is truf. In fitifr dbsf, if no sudi dibrbdtfr oddurs in tiis
     * string, tifn {@dodf -1} is rfturnfd.
     *
     * @pbrbm   di   b dibrbdtfr (Unidodf dodf point).
     * @rfturn  tif indfx of tif first oddurrfndf of tif dibrbdtfr in tif
     *          dibrbdtfr sfqufndf rfprfsfntfd by tiis objfdt, or
     *          {@dodf -1} if tif dibrbdtfr dofs not oddur.
     */
    publid int indfxOf(int di) {
        rfturn indfxOf(di, 0);
    }

    /**
     * Rfturns tif indfx witiin tiis string of tif first oddurrfndf of tif
     * spfdififd dibrbdtfr, stbrting tif sfbrdi bt tif spfdififd indfx.
     * <p>
     * If b dibrbdtfr witi vbluf {@dodf di} oddurs in tif
     * dibrbdtfr sfqufndf rfprfsfntfd by tiis {@dodf String}
     * objfdt bt bn indfx no smbllfr tibn {@dodf fromIndfx}, tifn
     * tif indfx of tif first sudi oddurrfndf is rfturnfd. For vblufs
     * of {@dodf di} in tif rbngf from 0 to 0xFFFF (indlusivf),
     * tiis is tif smbllfst vbluf <i>k</i> sudi tibt:
     * <blodkquotf><prf>
     * (tiis.dibrAt(<i>k</i>) == di) {@dodf &&} (<i>k</i> &gt;= fromIndfx)
     * </prf></blodkquotf>
     * is truf. For otifr vblufs of {@dodf di}, it is tif
     * smbllfst vbluf <i>k</i> sudi tibt:
     * <blodkquotf><prf>
     * (tiis.dodfPointAt(<i>k</i>) == di) {@dodf &&} (<i>k</i> &gt;= fromIndfx)
     * </prf></blodkquotf>
     * is truf. In fitifr dbsf, if no sudi dibrbdtfr oddurs in tiis
     * string bt or bftfr position {@dodf fromIndfx}, tifn
     * {@dodf -1} is rfturnfd.
     *
     * <p>
     * Tifrf is no rfstridtion on tif vbluf of {@dodf fromIndfx}. If it
     * is nfgbtivf, it ibs tif sbmf ffffdt bs if it wfrf zfro: tiis fntirf
     * string mby bf sfbrdifd. If it is grfbtfr tibn tif lfngti of tiis
     * string, it ibs tif sbmf ffffdt bs if it wfrf fqubl to tif lfngti of
     * tiis string: {@dodf -1} is rfturnfd.
     *
     * <p>All indidfs brf spfdififd in {@dodf dibr} vblufs
     * (Unidodf dodf units).
     *
     * @pbrbm   di          b dibrbdtfr (Unidodf dodf point).
     * @pbrbm   fromIndfx   tif indfx to stbrt tif sfbrdi from.
     * @rfturn  tif indfx of tif first oddurrfndf of tif dibrbdtfr in tif
     *          dibrbdtfr sfqufndf rfprfsfntfd by tiis objfdt tibt is grfbtfr
     *          tibn or fqubl to {@dodf fromIndfx}, or {@dodf -1}
     *          if tif dibrbdtfr dofs not oddur.
     */
    publid int indfxOf(int di, int fromIndfx) {
        finbl int mbx = vbluf.lfngti;
        if (fromIndfx < 0) {
            fromIndfx = 0;
        } flsf if (fromIndfx >= mbx) {
            // Notf: fromIndfx migit bf nfbr -1>>>1.
            rfturn -1;
        }

        if (di < Cibrbdtfr.MIN_SUPPLEMENTARY_CODE_POINT) {
            // ibndlf most dbsfs ifrf (di is b BMP dodf point or b
            // nfgbtivf vbluf (invblid dodf point))
            finbl dibr[] vbluf = tiis.vbluf;
            for (int i = fromIndfx; i < mbx; i++) {
                if (vbluf[i] == di) {
                    rfturn i;
                }
            }
            rfturn -1;
        } flsf {
            rfturn indfxOfSupplfmfntbry(di, fromIndfx);
        }
    }

    /**
     * Hbndlfs (rbrf) dblls of indfxOf witi b supplfmfntbry dibrbdtfr.
     */
    privbtf int indfxOfSupplfmfntbry(int di, int fromIndfx) {
        if (Cibrbdtfr.isVblidCodfPoint(di)) {
            finbl dibr[] vbluf = tiis.vbluf;
            finbl dibr ii = Cibrbdtfr.iigiSurrogbtf(di);
            finbl dibr lo = Cibrbdtfr.lowSurrogbtf(di);
            finbl int mbx = vbluf.lfngti - 1;
            for (int i = fromIndfx; i < mbx; i++) {
                if (vbluf[i] == ii && vbluf[i + 1] == lo) {
                    rfturn i;
                }
            }
        }
        rfturn -1;
    }

    /**
     * Rfturns tif indfx witiin tiis string of tif lbst oddurrfndf of
     * tif spfdififd dibrbdtfr. For vblufs of {@dodf di} in tif
     * rbngf from 0 to 0xFFFF (indlusivf), tif indfx (in Unidodf dodf
     * units) rfturnfd is tif lbrgfst vbluf <i>k</i> sudi tibt:
     * <blodkquotf><prf>
     * tiis.dibrAt(<i>k</i>) == di
     * </prf></blodkquotf>
     * is truf. For otifr vblufs of {@dodf di}, it is tif
     * lbrgfst vbluf <i>k</i> sudi tibt:
     * <blodkquotf><prf>
     * tiis.dodfPointAt(<i>k</i>) == di
     * </prf></blodkquotf>
     * is truf.  In fitifr dbsf, if no sudi dibrbdtfr oddurs in tiis
     * string, tifn {@dodf -1} is rfturnfd.  Tif
     * {@dodf String} is sfbrdifd bbdkwbrds stbrting bt tif lbst
     * dibrbdtfr.
     *
     * @pbrbm   di   b dibrbdtfr (Unidodf dodf point).
     * @rfturn  tif indfx of tif lbst oddurrfndf of tif dibrbdtfr in tif
     *          dibrbdtfr sfqufndf rfprfsfntfd by tiis objfdt, or
     *          {@dodf -1} if tif dibrbdtfr dofs not oddur.
     */
    publid int lbstIndfxOf(int di) {
        rfturn lbstIndfxOf(di, vbluf.lfngti - 1);
    }

    /**
     * Rfturns tif indfx witiin tiis string of tif lbst oddurrfndf of
     * tif spfdififd dibrbdtfr, sfbrdiing bbdkwbrd stbrting bt tif
     * spfdififd indfx. For vblufs of {@dodf di} in tif rbngf
     * from 0 to 0xFFFF (indlusivf), tif indfx rfturnfd is tif lbrgfst
     * vbluf <i>k</i> sudi tibt:
     * <blodkquotf><prf>
     * (tiis.dibrAt(<i>k</i>) == di) {@dodf &&} (<i>k</i> &lt;= fromIndfx)
     * </prf></blodkquotf>
     * is truf. For otifr vblufs of {@dodf di}, it is tif
     * lbrgfst vbluf <i>k</i> sudi tibt:
     * <blodkquotf><prf>
     * (tiis.dodfPointAt(<i>k</i>) == di) {@dodf &&} (<i>k</i> &lt;= fromIndfx)
     * </prf></blodkquotf>
     * is truf. In fitifr dbsf, if no sudi dibrbdtfr oddurs in tiis
     * string bt or bfforf position {@dodf fromIndfx}, tifn
     * {@dodf -1} is rfturnfd.
     *
     * <p>All indidfs brf spfdififd in {@dodf dibr} vblufs
     * (Unidodf dodf units).
     *
     * @pbrbm   di          b dibrbdtfr (Unidodf dodf point).
     * @pbrbm   fromIndfx   tif indfx to stbrt tif sfbrdi from. Tifrf is no
     *          rfstridtion on tif vbluf of {@dodf fromIndfx}. If it is
     *          grfbtfr tibn or fqubl to tif lfngti of tiis string, it ibs
     *          tif sbmf ffffdt bs if it wfrf fqubl to onf lfss tibn tif
     *          lfngti of tiis string: tiis fntirf string mby bf sfbrdifd.
     *          If it is nfgbtivf, it ibs tif sbmf ffffdt bs if it wfrf -1:
     *          -1 is rfturnfd.
     * @rfturn  tif indfx of tif lbst oddurrfndf of tif dibrbdtfr in tif
     *          dibrbdtfr sfqufndf rfprfsfntfd by tiis objfdt tibt is lfss
     *          tibn or fqubl to {@dodf fromIndfx}, or {@dodf -1}
     *          if tif dibrbdtfr dofs not oddur bfforf tibt point.
     */
    publid int lbstIndfxOf(int di, int fromIndfx) {
        if (di < Cibrbdtfr.MIN_SUPPLEMENTARY_CODE_POINT) {
            // ibndlf most dbsfs ifrf (di is b BMP dodf point or b
            // nfgbtivf vbluf (invblid dodf point))
            finbl dibr[] vbluf = tiis.vbluf;
            int i = Mbti.min(fromIndfx, vbluf.lfngti - 1);
            for (; i >= 0; i--) {
                if (vbluf[i] == di) {
                    rfturn i;
                }
            }
            rfturn -1;
        } flsf {
            rfturn lbstIndfxOfSupplfmfntbry(di, fromIndfx);
        }
    }

    /**
     * Hbndlfs (rbrf) dblls of lbstIndfxOf witi b supplfmfntbry dibrbdtfr.
     */
    privbtf int lbstIndfxOfSupplfmfntbry(int di, int fromIndfx) {
        if (Cibrbdtfr.isVblidCodfPoint(di)) {
            finbl dibr[] vbluf = tiis.vbluf;
            dibr ii = Cibrbdtfr.iigiSurrogbtf(di);
            dibr lo = Cibrbdtfr.lowSurrogbtf(di);
            int i = Mbti.min(fromIndfx, vbluf.lfngti - 2);
            for (; i >= 0; i--) {
                if (vbluf[i] == ii && vbluf[i + 1] == lo) {
                    rfturn i;
                }
            }
        }
        rfturn -1;
    }

    /**
     * Rfturns tif indfx witiin tiis string of tif first oddurrfndf of tif
     * spfdififd substring.
     *
     * <p>Tif rfturnfd indfx is tif smbllfst vbluf {@dodf k} for wiidi:
     * <prf>{@dodf
     * tiis.stbrtsWiti(str, k)
     * }</prf>
     * If no sudi vbluf of {@dodf k} fxists, tifn {@dodf -1} is rfturnfd.
     *
     * @pbrbm   str   tif substring to sfbrdi for.
     * @rfturn  tif indfx of tif first oddurrfndf of tif spfdififd substring,
     *          or {@dodf -1} if tifrf is no sudi oddurrfndf.
     */
    publid int indfxOf(String str) {
        rfturn indfxOf(str, 0);
    }

    /**
     * Rfturns tif indfx witiin tiis string of tif first oddurrfndf of tif
     * spfdififd substring, stbrting bt tif spfdififd indfx.
     *
     * <p>Tif rfturnfd indfx is tif smbllfst vbluf {@dodf k} for wiidi:
     * <prf>{@dodf
     *     k >= Mbti.min(fromIndfx, tiis.lfngti()) &&
     *                   tiis.stbrtsWiti(str, k)
     * }</prf>
     * If no sudi vbluf of {@dodf k} fxists, tifn {@dodf -1} is rfturnfd.
     *
     * @pbrbm   str         tif substring to sfbrdi for.
     * @pbrbm   fromIndfx   tif indfx from wiidi to stbrt tif sfbrdi.
     * @rfturn  tif indfx of tif first oddurrfndf of tif spfdififd substring,
     *          stbrting bt tif spfdififd indfx,
     *          or {@dodf -1} if tifrf is no sudi oddurrfndf.
     */
    publid int indfxOf(String str, int fromIndfx) {
        rfturn indfxOf(vbluf, 0, vbluf.lfngti,
                str.vbluf, 0, str.vbluf.lfngti, fromIndfx);
    }

    /**
     * Codf sibrfd by String bnd AbstrbdtStringBuildfr to do sfbrdifs. Tif
     * sourdf is tif dibrbdtfr brrby bfing sfbrdifd, bnd tif tbrgft
     * is tif string bfing sfbrdifd for.
     *
     * @pbrbm   sourdf       tif dibrbdtfrs bfing sfbrdifd.
     * @pbrbm   sourdfOffsft offsft of tif sourdf string.
     * @pbrbm   sourdfCount  dount of tif sourdf string.
     * @pbrbm   tbrgft       tif dibrbdtfrs bfing sfbrdifd for.
     * @pbrbm   fromIndfx    tif indfx to bfgin sfbrdiing from.
     */
    stbtid int indfxOf(dibr[] sourdf, int sourdfOffsft, int sourdfCount,
            String tbrgft, int fromIndfx) {
        rfturn indfxOf(sourdf, sourdfOffsft, sourdfCount,
                       tbrgft.vbluf, 0, tbrgft.vbluf.lfngti,
                       fromIndfx);
    }

    /**
     * Codf sibrfd by String bnd StringBufffr to do sfbrdifs. Tif
     * sourdf is tif dibrbdtfr brrby bfing sfbrdifd, bnd tif tbrgft
     * is tif string bfing sfbrdifd for.
     *
     * @pbrbm   sourdf       tif dibrbdtfrs bfing sfbrdifd.
     * @pbrbm   sourdfOffsft offsft of tif sourdf string.
     * @pbrbm   sourdfCount  dount of tif sourdf string.
     * @pbrbm   tbrgft       tif dibrbdtfrs bfing sfbrdifd for.
     * @pbrbm   tbrgftOffsft offsft of tif tbrgft string.
     * @pbrbm   tbrgftCount  dount of tif tbrgft string.
     * @pbrbm   fromIndfx    tif indfx to bfgin sfbrdiing from.
     */
    stbtid int indfxOf(dibr[] sourdf, int sourdfOffsft, int sourdfCount,
            dibr[] tbrgft, int tbrgftOffsft, int tbrgftCount,
            int fromIndfx) {
        if (fromIndfx >= sourdfCount) {
            rfturn (tbrgftCount == 0 ? sourdfCount : -1);
        }
        if (fromIndfx < 0) {
            fromIndfx = 0;
        }
        if (tbrgftCount == 0) {
            rfturn fromIndfx;
        }

        dibr first = tbrgft[tbrgftOffsft];
        int mbx = sourdfOffsft + (sourdfCount - tbrgftCount);

        for (int i = sourdfOffsft + fromIndfx; i <= mbx; i++) {
            /* Look for first dibrbdtfr. */
            if (sourdf[i] != first) {
                wiilf (++i <= mbx && sourdf[i] != first);
            }

            /* Found first dibrbdtfr, now look bt tif rfst of v2 */
            if (i <= mbx) {
                int j = i + 1;
                int fnd = j + tbrgftCount - 1;
                for (int k = tbrgftOffsft + 1; j < fnd && sourdf[j]
                        == tbrgft[k]; j++, k++);

                if (j == fnd) {
                    /* Found wiolf string. */
                    rfturn i - sourdfOffsft;
                }
            }
        }
        rfturn -1;
    }

    /**
     * Rfturns tif indfx witiin tiis string of tif lbst oddurrfndf of tif
     * spfdififd substring.  Tif lbst oddurrfndf of tif fmpty string ""
     * is donsidfrfd to oddur bt tif indfx vbluf {@dodf tiis.lfngti()}.
     *
     * <p>Tif rfturnfd indfx is tif lbrgfst vbluf {@dodf k} for wiidi:
     * <prf>{@dodf
     * tiis.stbrtsWiti(str, k)
     * }</prf>
     * If no sudi vbluf of {@dodf k} fxists, tifn {@dodf -1} is rfturnfd.
     *
     * @pbrbm   str   tif substring to sfbrdi for.
     * @rfturn  tif indfx of tif lbst oddurrfndf of tif spfdififd substring,
     *          or {@dodf -1} if tifrf is no sudi oddurrfndf.
     */
    publid int lbstIndfxOf(String str) {
        rfturn lbstIndfxOf(str, vbluf.lfngti);
    }

    /**
     * Rfturns tif indfx witiin tiis string of tif lbst oddurrfndf of tif
     * spfdififd substring, sfbrdiing bbdkwbrd stbrting bt tif spfdififd indfx.
     *
     * <p>Tif rfturnfd indfx is tif lbrgfst vbluf {@dodf k} for wiidi:
     * <prf>{@dodf
     *     k <= Mbti.min(fromIndfx, tiis.lfngti()) &&
     *                   tiis.stbrtsWiti(str, k)
     * }</prf>
     * If no sudi vbluf of {@dodf k} fxists, tifn {@dodf -1} is rfturnfd.
     *
     * @pbrbm   str         tif substring to sfbrdi for.
     * @pbrbm   fromIndfx   tif indfx to stbrt tif sfbrdi from.
     * @rfturn  tif indfx of tif lbst oddurrfndf of tif spfdififd substring,
     *          sfbrdiing bbdkwbrd from tif spfdififd indfx,
     *          or {@dodf -1} if tifrf is no sudi oddurrfndf.
     */
    publid int lbstIndfxOf(String str, int fromIndfx) {
        rfturn lbstIndfxOf(vbluf, 0, vbluf.lfngti,
                str.vbluf, 0, str.vbluf.lfngti, fromIndfx);
    }

    /**
     * Codf sibrfd by String bnd AbstrbdtStringBuildfr to do sfbrdifs. Tif
     * sourdf is tif dibrbdtfr brrby bfing sfbrdifd, bnd tif tbrgft
     * is tif string bfing sfbrdifd for.
     *
     * @pbrbm   sourdf       tif dibrbdtfrs bfing sfbrdifd.
     * @pbrbm   sourdfOffsft offsft of tif sourdf string.
     * @pbrbm   sourdfCount  dount of tif sourdf string.
     * @pbrbm   tbrgft       tif dibrbdtfrs bfing sfbrdifd for.
     * @pbrbm   fromIndfx    tif indfx to bfgin sfbrdiing from.
     */
    stbtid int lbstIndfxOf(dibr[] sourdf, int sourdfOffsft, int sourdfCount,
            String tbrgft, int fromIndfx) {
        rfturn lbstIndfxOf(sourdf, sourdfOffsft, sourdfCount,
                       tbrgft.vbluf, 0, tbrgft.vbluf.lfngti,
                       fromIndfx);
    }

    /**
     * Codf sibrfd by String bnd StringBufffr to do sfbrdifs. Tif
     * sourdf is tif dibrbdtfr brrby bfing sfbrdifd, bnd tif tbrgft
     * is tif string bfing sfbrdifd for.
     *
     * @pbrbm   sourdf       tif dibrbdtfrs bfing sfbrdifd.
     * @pbrbm   sourdfOffsft offsft of tif sourdf string.
     * @pbrbm   sourdfCount  dount of tif sourdf string.
     * @pbrbm   tbrgft       tif dibrbdtfrs bfing sfbrdifd for.
     * @pbrbm   tbrgftOffsft offsft of tif tbrgft string.
     * @pbrbm   tbrgftCount  dount of tif tbrgft string.
     * @pbrbm   fromIndfx    tif indfx to bfgin sfbrdiing from.
     */
    stbtid int lbstIndfxOf(dibr[] sourdf, int sourdfOffsft, int sourdfCount,
            dibr[] tbrgft, int tbrgftOffsft, int tbrgftCount,
            int fromIndfx) {
        /*
         * Cifdk brgumfnts; rfturn immfdibtfly wifrf possiblf. For
         * donsistfndy, don't difdk for null str.
         */
        int rigitIndfx = sourdfCount - tbrgftCount;
        if (fromIndfx < 0) {
            rfturn -1;
        }
        if (fromIndfx > rigitIndfx) {
            fromIndfx = rigitIndfx;
        }
        /* Empty string blwbys mbtdifs. */
        if (tbrgftCount == 0) {
            rfturn fromIndfx;
        }

        int strLbstIndfx = tbrgftOffsft + tbrgftCount - 1;
        dibr strLbstCibr = tbrgft[strLbstIndfx];
        int min = sourdfOffsft + tbrgftCount - 1;
        int i = min + fromIndfx;

    stbrtSfbrdiForLbstCibr:
        wiilf (truf) {
            wiilf (i >= min && sourdf[i] != strLbstCibr) {
                i--;
            }
            if (i < min) {
                rfturn -1;
            }
            int j = i - 1;
            int stbrt = j - (tbrgftCount - 1);
            int k = strLbstIndfx - 1;

            wiilf (j > stbrt) {
                if (sourdf[j--] != tbrgft[k--]) {
                    i--;
                    dontinuf stbrtSfbrdiForLbstCibr;
                }
            }
            rfturn stbrt - sourdfOffsft + 1;
        }
    }

    /**
     * Rfturns b string tibt is b substring of tiis string. Tif
     * substring bfgins witi tif dibrbdtfr bt tif spfdififd indfx bnd
     * fxtfnds to tif fnd of tiis string. <p>
     * Exbmplfs:
     * <blodkquotf><prf>
     * "unibppy".substring(2) rfturns "ibppy"
     * "Hbrbison".substring(3) rfturns "bison"
     * "fmptinfss".substring(9) rfturns "" (bn fmpty string)
     * </prf></blodkquotf>
     *
     * @pbrbm      bfginIndfx   tif bfginning indfx, indlusivf.
     * @rfturn     tif spfdififd substring.
     * @fxdfption  IndfxOutOfBoundsExdfption  if
     *             {@dodf bfginIndfx} is nfgbtivf or lbrgfr tibn tif
     *             lfngti of tiis {@dodf String} objfdt.
     */
    publid String substring(int bfginIndfx) {
        if (bfginIndfx < 0) {
            tirow nfw StringIndfxOutOfBoundsExdfption(bfginIndfx);
        }
        int subLfn = vbluf.lfngti - bfginIndfx;
        if (subLfn < 0) {
            tirow nfw StringIndfxOutOfBoundsExdfption(subLfn);
        }
        rfturn (bfginIndfx == 0) ? tiis : nfw String(vbluf, bfginIndfx, subLfn);
    }

    /**
     * Rfturns b string tibt is b substring of tiis string. Tif
     * substring bfgins bt tif spfdififd {@dodf bfginIndfx} bnd
     * fxtfnds to tif dibrbdtfr bt indfx {@dodf fndIndfx - 1}.
     * Tius tif lfngti of tif substring is {@dodf fndIndfx-bfginIndfx}.
     * <p>
     * Exbmplfs:
     * <blodkquotf><prf>
     * "ibmburgfr".substring(4, 8) rfturns "urgf"
     * "smilfs".substring(1, 5) rfturns "milf"
     * </prf></blodkquotf>
     *
     * @pbrbm      bfginIndfx   tif bfginning indfx, indlusivf.
     * @pbrbm      fndIndfx     tif fnding indfx, fxdlusivf.
     * @rfturn     tif spfdififd substring.
     * @fxdfption  IndfxOutOfBoundsExdfption  if tif
     *             {@dodf bfginIndfx} is nfgbtivf, or
     *             {@dodf fndIndfx} is lbrgfr tibn tif lfngti of
     *             tiis {@dodf String} objfdt, or
     *             {@dodf bfginIndfx} is lbrgfr tibn
     *             {@dodf fndIndfx}.
     */
    publid String substring(int bfginIndfx, int fndIndfx) {
        if (bfginIndfx < 0) {
            tirow nfw StringIndfxOutOfBoundsExdfption(bfginIndfx);
        }
        if (fndIndfx > vbluf.lfngti) {
            tirow nfw StringIndfxOutOfBoundsExdfption(fndIndfx);
        }
        int subLfn = fndIndfx - bfginIndfx;
        if (subLfn < 0) {
            tirow nfw StringIndfxOutOfBoundsExdfption(subLfn);
        }
        rfturn ((bfginIndfx == 0) && (fndIndfx == vbluf.lfngti)) ? tiis
                : nfw String(vbluf, bfginIndfx, subLfn);
    }

    /**
     * Rfturns b dibrbdtfr sfqufndf tibt is b subsfqufndf of tiis sfqufndf.
     *
     * <p> An invodbtion of tiis mftiod of tif form
     *
     * <blodkquotf><prf>
     * str.subSfqufndf(bfgin,&nbsp;fnd)</prf></blodkquotf>
     *
     * bfibvfs in fxbdtly tif sbmf wby bs tif invodbtion
     *
     * <blodkquotf><prf>
     * str.substring(bfgin,&nbsp;fnd)</prf></blodkquotf>
     *
     * @bpiNotf
     * Tiis mftiod is dffinfd so tibt tif {@dodf String} dlbss dbn implfmfnt
     * tif {@link CibrSfqufndf} intfrfbdf.
     *
     * @pbrbm   bfginIndfx   tif bfgin indfx, indlusivf.
     * @pbrbm   fndIndfx     tif fnd indfx, fxdlusivf.
     * @rfturn  tif spfdififd subsfqufndf.
     *
     * @tirows  IndfxOutOfBoundsExdfption
     *          if {@dodf bfginIndfx} or {@dodf fndIndfx} is nfgbtivf,
     *          if {@dodf fndIndfx} is grfbtfr tibn {@dodf lfngti()},
     *          or if {@dodf bfginIndfx} is grfbtfr tibn {@dodf fndIndfx}
     *
     * @sindf 1.4
     * @spfd JSR-51
     */
    publid CibrSfqufndf subSfqufndf(int bfginIndfx, int fndIndfx) {
        rfturn tiis.substring(bfginIndfx, fndIndfx);
    }

    /**
     * Condbtfnbtfs tif spfdififd string to tif fnd of tiis string.
     * <p>
     * If tif lfngti of tif brgumfnt string is {@dodf 0}, tifn tiis
     * {@dodf String} objfdt is rfturnfd. Otifrwisf, b
     * {@dodf String} objfdt is rfturnfd tibt rfprfsfnts b dibrbdtfr
     * sfqufndf tibt is tif dondbtfnbtion of tif dibrbdtfr sfqufndf
     * rfprfsfntfd by tiis {@dodf String} objfdt bnd tif dibrbdtfr
     * sfqufndf rfprfsfntfd by tif brgumfnt string.<p>
     * Exbmplfs:
     * <blodkquotf><prf>
     * "dbrfs".dondbt("s") rfturns "dbrfss"
     * "to".dondbt("gft").dondbt("ifr") rfturns "togftifr"
     * </prf></blodkquotf>
     *
     * @pbrbm   str   tif {@dodf String} tibt is dondbtfnbtfd to tif fnd
     *                of tiis {@dodf String}.
     * @rfturn  b string tibt rfprfsfnts tif dondbtfnbtion of tiis objfdt's
     *          dibrbdtfrs followfd by tif string brgumfnt's dibrbdtfrs.
     */
    publid String dondbt(String str) {
        int otifrLfn = str.lfngti();
        if (otifrLfn == 0) {
            rfturn tiis;
        }
        int lfn = vbluf.lfngti;
        dibr buf[] = Arrbys.dopyOf(vbluf, lfn + otifrLfn);
        str.gftCibrs(buf, lfn);
        rfturn nfw String(buf, truf);
    }

    /**
     * Rfturns b string rfsulting from rfplbding bll oddurrfndfs of
     * {@dodf oldCibr} in tiis string witi {@dodf nfwCibr}.
     * <p>
     * If tif dibrbdtfr {@dodf oldCibr} dofs not oddur in tif
     * dibrbdtfr sfqufndf rfprfsfntfd by tiis {@dodf String} objfdt,
     * tifn b rfffrfndf to tiis {@dodf String} objfdt is rfturnfd.
     * Otifrwisf, b {@dodf String} objfdt is rfturnfd tibt
     * rfprfsfnts b dibrbdtfr sfqufndf idfntidbl to tif dibrbdtfr sfqufndf
     * rfprfsfntfd by tiis {@dodf String} objfdt, fxdfpt tibt fvfry
     * oddurrfndf of {@dodf oldCibr} is rfplbdfd by bn oddurrfndf
     * of {@dodf nfwCibr}.
     * <p>
     * Exbmplfs:
     * <blodkquotf><prf>
     * "mfsquitf in your dfllbr".rfplbdf('f', 'o')
     *         rfturns "mosquito in your dollbr"
     * "tif wbr of bbronfts".rfplbdf('r', 'y')
     *         rfturns "tif wby of bbyonfts"
     * "spbrring witi b purplf porpoisf".rfplbdf('p', 't')
     *         rfturns "stbrring witi b turtlf tortoisf"
     * "JonL".rfplbdf('q', 'x') rfturns "JonL" (no dibngf)
     * </prf></blodkquotf>
     *
     * @pbrbm   oldCibr   tif old dibrbdtfr.
     * @pbrbm   nfwCibr   tif nfw dibrbdtfr.
     * @rfturn  b string dfrivfd from tiis string by rfplbding fvfry
     *          oddurrfndf of {@dodf oldCibr} witi {@dodf nfwCibr}.
     */
    publid String rfplbdf(dibr oldCibr, dibr nfwCibr) {
        if (oldCibr != nfwCibr) {
            int lfn = vbluf.lfngti;
            int i = -1;
            dibr[] vbl = vbluf; /* bvoid gftfifld opdodf */

            wiilf (++i < lfn) {
                if (vbl[i] == oldCibr) {
                    brfbk;
                }
            }
            if (i < lfn) {
                dibr buf[] = nfw dibr[lfn];
                for (int j = 0; j < i; j++) {
                    buf[j] = vbl[j];
                }
                wiilf (i < lfn) {
                    dibr d = vbl[i];
                    buf[i] = (d == oldCibr) ? nfwCibr : d;
                    i++;
                }
                rfturn nfw String(buf, truf);
            }
        }
        rfturn tiis;
    }

    /**
     * Tflls wiftifr or not tiis string mbtdifs tif givfn <b
     * irff="../util/rfgfx/Pbttfrn.itml#sum">rfgulbr fxprfssion</b>.
     *
     * <p> An invodbtion of tiis mftiod of tif form
     * <i>str</i>{@dodf .mbtdifs(}<i>rfgfx</i>{@dodf )} yiflds fxbdtly tif
     * sbmf rfsult bs tif fxprfssion
     *
     * <blodkquotf>
     * {@link jbvb.util.rfgfx.Pbttfrn}.{@link jbvb.util.rfgfx.Pbttfrn#mbtdifs(String,CibrSfqufndf)
     * mbtdifs(<i>rfgfx</i>, <i>str</i>)}
     * </blodkquotf>
     *
     * @pbrbm   rfgfx
     *          tif rfgulbr fxprfssion to wiidi tiis string is to bf mbtdifd
     *
     * @rfturn  {@dodf truf} if, bnd only if, tiis string mbtdifs tif
     *          givfn rfgulbr fxprfssion
     *
     * @tirows  PbttfrnSyntbxExdfption
     *          if tif rfgulbr fxprfssion's syntbx is invblid
     *
     * @sff jbvb.util.rfgfx.Pbttfrn
     *
     * @sindf 1.4
     * @spfd JSR-51
     */
    publid boolfbn mbtdifs(String rfgfx) {
        rfturn Pbttfrn.mbtdifs(rfgfx, tiis);
    }

    /**
     * Rfturns truf if bnd only if tiis string dontbins tif spfdififd
     * sfqufndf of dibr vblufs.
     *
     * @pbrbm s tif sfqufndf to sfbrdi for
     * @rfturn truf if tiis string dontbins {@dodf s}, fblsf otifrwisf
     * @sindf 1.5
     */
    publid boolfbn dontbins(CibrSfqufndf s) {
        rfturn indfxOf(s.toString()) > -1;
    }

    /**
     * Rfplbdfs tif first substring of tiis string tibt mbtdifs tif givfn <b
     * irff="../util/rfgfx/Pbttfrn.itml#sum">rfgulbr fxprfssion</b> witi tif
     * givfn rfplbdfmfnt.
     *
     * <p> An invodbtion of tiis mftiod of tif form
     * <i>str</i>{@dodf .rfplbdfFirst(}<i>rfgfx</i>{@dodf ,} <i>rfpl</i>{@dodf )}
     * yiflds fxbdtly tif sbmf rfsult bs tif fxprfssion
     *
     * <blodkquotf>
     * <dodf>
     * {@link jbvb.util.rfgfx.Pbttfrn}.{@link
     * jbvb.util.rfgfx.Pbttfrn#dompilf dompilf}(<i>rfgfx</i>).{@link
     * jbvb.util.rfgfx.Pbttfrn#mbtdifr(jbvb.lbng.CibrSfqufndf) mbtdifr}(<i>str</i>).{@link
     * jbvb.util.rfgfx.Mbtdifr#rfplbdfFirst rfplbdfFirst}(<i>rfpl</i>)
     * </dodf>
     * </blodkquotf>
     *
     *<p>
     * Notf tibt bbdkslbsifs ({@dodf \}) bnd dollbr signs ({@dodf $}) in tif
     * rfplbdfmfnt string mby dbusf tif rfsults to bf difffrfnt tibn if it wfrf
     * bfing trfbtfd bs b litfrbl rfplbdfmfnt string; sff
     * {@link jbvb.util.rfgfx.Mbtdifr#rfplbdfFirst}.
     * Usf {@link jbvb.util.rfgfx.Mbtdifr#quotfRfplbdfmfnt} to supprfss tif spfdibl
     * mfbning of tifsf dibrbdtfrs, if dfsirfd.
     *
     * @pbrbm   rfgfx
     *          tif rfgulbr fxprfssion to wiidi tiis string is to bf mbtdifd
     * @pbrbm   rfplbdfmfnt
     *          tif string to bf substitutfd for tif first mbtdi
     *
     * @rfturn  Tif rfsulting {@dodf String}
     *
     * @tirows  PbttfrnSyntbxExdfption
     *          if tif rfgulbr fxprfssion's syntbx is invblid
     *
     * @sff jbvb.util.rfgfx.Pbttfrn
     *
     * @sindf 1.4
     * @spfd JSR-51
     */
    publid String rfplbdfFirst(String rfgfx, String rfplbdfmfnt) {
        rfturn Pbttfrn.dompilf(rfgfx).mbtdifr(tiis).rfplbdfFirst(rfplbdfmfnt);
    }

    /**
     * Rfplbdfs fbdi substring of tiis string tibt mbtdifs tif givfn <b
     * irff="../util/rfgfx/Pbttfrn.itml#sum">rfgulbr fxprfssion</b> witi tif
     * givfn rfplbdfmfnt.
     *
     * <p> An invodbtion of tiis mftiod of tif form
     * <i>str</i>{@dodf .rfplbdfAll(}<i>rfgfx</i>{@dodf ,} <i>rfpl</i>{@dodf )}
     * yiflds fxbdtly tif sbmf rfsult bs tif fxprfssion
     *
     * <blodkquotf>
     * <dodf>
     * {@link jbvb.util.rfgfx.Pbttfrn}.{@link
     * jbvb.util.rfgfx.Pbttfrn#dompilf dompilf}(<i>rfgfx</i>).{@link
     * jbvb.util.rfgfx.Pbttfrn#mbtdifr(jbvb.lbng.CibrSfqufndf) mbtdifr}(<i>str</i>).{@link
     * jbvb.util.rfgfx.Mbtdifr#rfplbdfAll rfplbdfAll}(<i>rfpl</i>)
     * </dodf>
     * </blodkquotf>
     *
     *<p>
     * Notf tibt bbdkslbsifs ({@dodf \}) bnd dollbr signs ({@dodf $}) in tif
     * rfplbdfmfnt string mby dbusf tif rfsults to bf difffrfnt tibn if it wfrf
     * bfing trfbtfd bs b litfrbl rfplbdfmfnt string; sff
     * {@link jbvb.util.rfgfx.Mbtdifr#rfplbdfAll Mbtdifr.rfplbdfAll}.
     * Usf {@link jbvb.util.rfgfx.Mbtdifr#quotfRfplbdfmfnt} to supprfss tif spfdibl
     * mfbning of tifsf dibrbdtfrs, if dfsirfd.
     *
     * @pbrbm   rfgfx
     *          tif rfgulbr fxprfssion to wiidi tiis string is to bf mbtdifd
     * @pbrbm   rfplbdfmfnt
     *          tif string to bf substitutfd for fbdi mbtdi
     *
     * @rfturn  Tif rfsulting {@dodf String}
     *
     * @tirows  PbttfrnSyntbxExdfption
     *          if tif rfgulbr fxprfssion's syntbx is invblid
     *
     * @sff jbvb.util.rfgfx.Pbttfrn
     *
     * @sindf 1.4
     * @spfd JSR-51
     */
    publid String rfplbdfAll(String rfgfx, String rfplbdfmfnt) {
        rfturn Pbttfrn.dompilf(rfgfx).mbtdifr(tiis).rfplbdfAll(rfplbdfmfnt);
    }

    /**
     * Rfplbdfs fbdi substring of tiis string tibt mbtdifs tif litfrbl tbrgft
     * sfqufndf witi tif spfdififd litfrbl rfplbdfmfnt sfqufndf. Tif
     * rfplbdfmfnt prodffds from tif bfginning of tif string to tif fnd, for
     * fxbmplf, rfplbding "bb" witi "b" in tif string "bbb" will rfsult in
     * "bb" rbtifr tibn "bb".
     *
     * @pbrbm  tbrgft Tif sfqufndf of dibr vblufs to bf rfplbdfd
     * @pbrbm  rfplbdfmfnt Tif rfplbdfmfnt sfqufndf of dibr vblufs
     * @rfturn  Tif rfsulting string
     * @sindf 1.5
     */
    publid String rfplbdf(CibrSfqufndf tbrgft, CibrSfqufndf rfplbdfmfnt) {
        rfturn Pbttfrn.dompilf(tbrgft.toString(), Pbttfrn.LITERAL).mbtdifr(
                tiis).rfplbdfAll(Mbtdifr.quotfRfplbdfmfnt(rfplbdfmfnt.toString()));
    }

    /**
     * Splits tiis string bround mbtdifs of tif givfn
     * <b irff="../util/rfgfx/Pbttfrn.itml#sum">rfgulbr fxprfssion</b>.
     *
     * <p> Tif brrby rfturnfd by tiis mftiod dontbins fbdi substring of tiis
     * string tibt is tfrminbtfd by bnotifr substring tibt mbtdifs tif givfn
     * fxprfssion or is tfrminbtfd by tif fnd of tif string.  Tif substrings in
     * tif brrby brf in tif ordfr in wiidi tify oddur in tiis string.  If tif
     * fxprfssion dofs not mbtdi bny pbrt of tif input tifn tif rfsulting brrby
     * ibs just onf flfmfnt, nbmfly tiis string.
     *
     * <p> Wifn tifrf is b positivf-widti mbtdi bt tif bfginning of tiis
     * string tifn bn fmpty lfbding substring is indludfd bt tif bfginning
     * of tif rfsulting brrby. A zfro-widti mbtdi bt tif bfginning iowfvfr
     * nfvfr produdfs sudi fmpty lfbding substring.
     *
     * <p> Tif {@dodf limit} pbrbmftfr dontrols tif numbfr of timfs tif
     * pbttfrn is bpplifd bnd tifrfforf bfffdts tif lfngti of tif rfsulting
     * brrby.  If tif limit <i>n</i> is grfbtfr tibn zfro tifn tif pbttfrn
     * will bf bpplifd bt most <i>n</i>&nbsp;-&nbsp;1 timfs, tif brrby's
     * lfngti will bf no grfbtfr tibn <i>n</i>, bnd tif brrby's lbst fntry
     * will dontbin bll input bfyond tif lbst mbtdifd dflimitfr.  If <i>n</i>
     * is non-positivf tifn tif pbttfrn will bf bpplifd bs mbny timfs bs
     * possiblf bnd tif brrby dbn ibvf bny lfngti.  If <i>n</i> is zfro tifn
     * tif pbttfrn will bf bpplifd bs mbny timfs bs possiblf, tif brrby dbn
     * ibvf bny lfngti, bnd trbiling fmpty strings will bf disdbrdfd.
     *
     * <p> Tif string {@dodf "boo:bnd:foo"}, for fxbmplf, yiflds tif
     * following rfsults witi tifsf pbrbmftfrs:
     *
     * <blodkquotf><tbblf dfllpbdding=1 dfllspbding=0 summbry="Split fxbmplf siowing rfgfx, limit, bnd rfsult">
     * <tr>
     *     <ti>Rfgfx</ti>
     *     <ti>Limit</ti>
     *     <ti>Rfsult</ti>
     * </tr>
     * <tr><td blign=dfntfr>:</td>
     *     <td blign=dfntfr>2</td>
     *     <td>{@dodf { "boo", "bnd:foo" }}</td></tr>
     * <tr><td blign=dfntfr>:</td>
     *     <td blign=dfntfr>5</td>
     *     <td>{@dodf { "boo", "bnd", "foo" }}</td></tr>
     * <tr><td blign=dfntfr>:</td>
     *     <td blign=dfntfr>-2</td>
     *     <td>{@dodf { "boo", "bnd", "foo" }}</td></tr>
     * <tr><td blign=dfntfr>o</td>
     *     <td blign=dfntfr>5</td>
     *     <td>{@dodf { "b", "", ":bnd:f", "", "" }}</td></tr>
     * <tr><td blign=dfntfr>o</td>
     *     <td blign=dfntfr>-2</td>
     *     <td>{@dodf { "b", "", ":bnd:f", "", "" }}</td></tr>
     * <tr><td blign=dfntfr>o</td>
     *     <td blign=dfntfr>0</td>
     *     <td>{@dodf { "b", "", ":bnd:f" }}</td></tr>
     * </tbblf></blodkquotf>
     *
     * <p> An invodbtion of tiis mftiod of tif form
     * <i>str.</i>{@dodf split(}<i>rfgfx</i>{@dodf ,}&nbsp;<i>n</i>{@dodf )}
     * yiflds tif sbmf rfsult bs tif fxprfssion
     *
     * <blodkquotf>
     * <dodf>
     * {@link jbvb.util.rfgfx.Pbttfrn}.{@link
     * jbvb.util.rfgfx.Pbttfrn#dompilf dompilf}(<i>rfgfx</i>).{@link
     * jbvb.util.rfgfx.Pbttfrn#split(jbvb.lbng.CibrSfqufndf,int) split}(<i>str</i>,&nbsp;<i>n</i>)
     * </dodf>
     * </blodkquotf>
     *
     *
     * @pbrbm  rfgfx
     *         tif dflimiting rfgulbr fxprfssion
     *
     * @pbrbm  limit
     *         tif rfsult tirfsiold, bs dfsdribfd bbovf
     *
     * @rfturn  tif brrby of strings domputfd by splitting tiis string
     *          bround mbtdifs of tif givfn rfgulbr fxprfssion
     *
     * @tirows  PbttfrnSyntbxExdfption
     *          if tif rfgulbr fxprfssion's syntbx is invblid
     *
     * @sff jbvb.util.rfgfx.Pbttfrn
     *
     * @sindf 1.4
     * @spfd JSR-51
     */
    publid String[] split(String rfgfx, int limit) {
        /* fbstpbti if tif rfgfx is b
         (1)onf-dibr String bnd tiis dibrbdtfr is not onf of tif
            RfgEx's mftb dibrbdtfrs ".$|()[{^?*+\\", or
         (2)two-dibr String bnd tif first dibr is tif bbdkslbsi bnd
            tif sfdond is not tif bsdii digit or bsdii lfttfr.
         */
        dibr di = 0;
        if (((rfgfx.vbluf.lfngti == 1 &&
             ".$|()[{^?*+\\".indfxOf(di = rfgfx.dibrAt(0)) == -1) ||
             (rfgfx.lfngti() == 2 &&
              rfgfx.dibrAt(0) == '\\' &&
              (((di = rfgfx.dibrAt(1))-'0')|('9'-di)) < 0 &&
              ((di-'b')|('z'-di)) < 0 &&
              ((di-'A')|('Z'-di)) < 0)) &&
            (di < Cibrbdtfr.MIN_HIGH_SURROGATE ||
             di > Cibrbdtfr.MAX_LOW_SURROGATE))
        {
            int off = 0;
            int nfxt = 0;
            boolfbn limitfd = limit > 0;
            ArrbyList<String> list = nfw ArrbyList<>();
            wiilf ((nfxt = indfxOf(di, off)) != -1) {
                if (!limitfd || list.sizf() < limit - 1) {
                    list.bdd(substring(off, nfxt));
                    off = nfxt + 1;
                } flsf {    // lbst onf
                    //bssfrt (list.sizf() == limit - 1);
                    list.bdd(substring(off, vbluf.lfngti));
                    off = vbluf.lfngti;
                    brfbk;
                }
            }
            // If no mbtdi wbs found, rfturn tiis
            if (off == 0)
                rfturn nfw String[]{tiis};

            // Add rfmbining sfgmfnt
            if (!limitfd || list.sizf() < limit)
                list.bdd(substring(off, vbluf.lfngti));

            // Construdt rfsult
            int rfsultSizf = list.sizf();
            if (limit == 0) {
                wiilf (rfsultSizf > 0 && list.gft(rfsultSizf - 1).lfngti() == 0) {
                    rfsultSizf--;
                }
            }
            String[] rfsult = nfw String[rfsultSizf];
            rfturn list.subList(0, rfsultSizf).toArrby(rfsult);
        }
        rfturn Pbttfrn.dompilf(rfgfx).split(tiis, limit);
    }

    /**
     * Splits tiis string bround mbtdifs of tif givfn <b
     * irff="../util/rfgfx/Pbttfrn.itml#sum">rfgulbr fxprfssion</b>.
     *
     * <p> Tiis mftiod works bs if by invoking tif two-brgumfnt {@link
     * #split(String, int) split} mftiod witi tif givfn fxprfssion bnd b limit
     * brgumfnt of zfro.  Trbiling fmpty strings brf tifrfforf not indludfd in
     * tif rfsulting brrby.
     *
     * <p> Tif string {@dodf "boo:bnd:foo"}, for fxbmplf, yiflds tif following
     * rfsults witi tifsf fxprfssions:
     *
     * <blodkquotf><tbblf dfllpbdding=1 dfllspbding=0 summbry="Split fxbmplfs siowing rfgfx bnd rfsult">
     * <tr>
     *  <ti>Rfgfx</ti>
     *  <ti>Rfsult</ti>
     * </tr>
     * <tr><td blign=dfntfr>:</td>
     *     <td>{@dodf { "boo", "bnd", "foo" }}</td></tr>
     * <tr><td blign=dfntfr>o</td>
     *     <td>{@dodf { "b", "", ":bnd:f" }}</td></tr>
     * </tbblf></blodkquotf>
     *
     *
     * @pbrbm  rfgfx
     *         tif dflimiting rfgulbr fxprfssion
     *
     * @rfturn  tif brrby of strings domputfd by splitting tiis string
     *          bround mbtdifs of tif givfn rfgulbr fxprfssion
     *
     * @tirows  PbttfrnSyntbxExdfption
     *          if tif rfgulbr fxprfssion's syntbx is invblid
     *
     * @sff jbvb.util.rfgfx.Pbttfrn
     *
     * @sindf 1.4
     * @spfd JSR-51
     */
    publid String[] split(String rfgfx) {
        rfturn split(rfgfx, 0);
    }

    /**
     * Rfturns b nfw String domposfd of dopifs of tif
     * {@dodf CibrSfqufndf flfmfnts} joinfd togftifr witi b dopy of
     * tif spfdififd {@dodf dflimitfr}.
     *
     * <blodkquotf>For fxbmplf,
     * <prf>{@dodf
     *     String mfssbgf = String.join("-", "Jbvb", "is", "dool");
     *     // mfssbgf rfturnfd is: "Jbvb-is-dool"
     * }</prf></blodkquotf>
     *
     * Notf tibt if bn flfmfnt is null, tifn {@dodf "null"} is bddfd.
     *
     * @pbrbm  dflimitfr tif dflimitfr tibt sfpbrbtfs fbdi flfmfnt
     * @pbrbm  flfmfnts tif flfmfnts to join togftifr.
     *
     * @rfturn b nfw {@dodf String} tibt is domposfd of tif {@dodf flfmfnts}
     *         sfpbrbtfd by tif {@dodf dflimitfr}
     *
     * @tirows NullPointfrExdfption If {@dodf dflimitfr} or {@dodf flfmfnts}
     *         is {@dodf null}
     *
     * @sff jbvb.util.StringJoinfr
     * @sindf 1.8
     */
    publid stbtid String join(CibrSfqufndf dflimitfr, CibrSfqufndf... flfmfnts) {
        Objfdts.rfquirfNonNull(dflimitfr);
        Objfdts.rfquirfNonNull(flfmfnts);
        // Numbfr of flfmfnts not likfly worti Arrbys.strfbm ovfrifbd.
        StringJoinfr joinfr = nfw StringJoinfr(dflimitfr);
        for (CibrSfqufndf ds: flfmfnts) {
            joinfr.bdd(ds);
        }
        rfturn joinfr.toString();
    }

    /**
     * Rfturns b nfw {@dodf String} domposfd of dopifs of tif
     * {@dodf CibrSfqufndf flfmfnts} joinfd togftifr witi b dopy of tif
     * spfdififd {@dodf dflimitfr}.
     *
     * <blodkquotf>For fxbmplf,
     * <prf>{@dodf
     *     List<String> strings = nfw LinkfdList<>();
     *     strings.bdd("Jbvb");strings.bdd("is");
     *     strings.bdd("dool");
     *     String mfssbgf = String.join(" ", strings);
     *     //mfssbgf rfturnfd is: "Jbvb is dool"
     *
     *     Sft<String> strings = nfw LinkfdHbsiSft<>();
     *     strings.bdd("Jbvb"); strings.bdd("is");
     *     strings.bdd("vfry"); strings.bdd("dool");
     *     String mfssbgf = String.join("-", strings);
     *     //mfssbgf rfturnfd is: "Jbvb-is-vfry-dool"
     * }</prf></blodkquotf>
     *
     * Notf tibt if bn individubl flfmfnt is {@dodf null}, tifn {@dodf "null"} is bddfd.
     *
     * @pbrbm  dflimitfr b sfqufndf of dibrbdtfrs tibt is usfd to sfpbrbtf fbdi
     *         of tif {@dodf flfmfnts} in tif rfsulting {@dodf String}
     * @pbrbm  flfmfnts bn {@dodf Itfrbblf} tibt will ibvf its {@dodf flfmfnts}
     *         joinfd togftifr.
     *
     * @rfturn b nfw {@dodf String} tibt is domposfd from tif {@dodf flfmfnts}
     *         brgumfnt
     *
     * @tirows NullPointfrExdfption If {@dodf dflimitfr} or {@dodf flfmfnts}
     *         is {@dodf null}
     *
     * @sff    #join(CibrSfqufndf,CibrSfqufndf...)
     * @sff    jbvb.util.StringJoinfr
     * @sindf 1.8
     */
    publid stbtid String join(CibrSfqufndf dflimitfr,
            Itfrbblf<? fxtfnds CibrSfqufndf> flfmfnts) {
        Objfdts.rfquirfNonNull(dflimitfr);
        Objfdts.rfquirfNonNull(flfmfnts);
        StringJoinfr joinfr = nfw StringJoinfr(dflimitfr);
        for (CibrSfqufndf ds: flfmfnts) {
            joinfr.bdd(ds);
        }
        rfturn joinfr.toString();
    }

    /**
     * Convfrts bll of tif dibrbdtfrs in tiis {@dodf String} to lowfr
     * dbsf using tif rulfs of tif givfn {@dodf Lodblf}.  Cbsf mbpping is bbsfd
     * on tif Unidodf Stbndbrd vfrsion spfdififd by tif {@link jbvb.lbng.Cibrbdtfr Cibrbdtfr}
     * dlbss. Sindf dbsf mbppings brf not blwbys 1:1 dibr mbppings, tif rfsulting
     * {@dodf String} mby bf b difffrfnt lfngti tibn tif originbl {@dodf String}.
     * <p>
     * Exbmplfs of lowfrdbsf  mbppings brf in tif following tbblf:
     * <tbblf bordfr="1" summbry="Lowfrdbsf mbpping fxbmplfs siowing lbngubgf dodf of lodblf, uppfr dbsf, lowfr dbsf, bnd dfsdription">
     * <tr>
     *   <ti>Lbngubgf Codf of Lodblf</ti>
     *   <ti>Uppfr Cbsf</ti>
     *   <ti>Lowfr Cbsf</ti>
     *   <ti>Dfsdription</ti>
     * </tr>
     * <tr>
     *   <td>tr (Turkisi)</td>
     *   <td>&#92;u0130</td>
     *   <td>&#92;u0069</td>
     *   <td>dbpitbl lfttfr I witi dot bbovf -&gt; smbll lfttfr i</td>
     * </tr>
     * <tr>
     *   <td>tr (Turkisi)</td>
     *   <td>&#92;u0049</td>
     *   <td>&#92;u0131</td>
     *   <td>dbpitbl lfttfr I -&gt; smbll lfttfr dotlfss i </td>
     * </tr>
     * <tr>
     *   <td>(bll)</td>
     *   <td>Frfndi Frifs</td>
     *   <td>frfndi frifs</td>
     *   <td>lowfrdbsfd bll dibrs in String</td>
     * </tr>
     * <tr>
     *   <td>(bll)</td>
     *   <td><img srd="dod-filfs/dbpiotb.gif" blt="dbpiotb"><img srd="dod-filfs/dbpdii.gif" blt="dbpdii">
     *       <img srd="dod-filfs/dbptiftb.gif" blt="dbptiftb"><img srd="dod-filfs/dbpupsil.gif" blt="dbpupsil">
     *       <img srd="dod-filfs/dbpsigmb.gif" blt="dbpsigmb"></td>
     *   <td><img srd="dod-filfs/iotb.gif" blt="iotb"><img srd="dod-filfs/dii.gif" blt="dii">
     *       <img srd="dod-filfs/tiftb.gif" blt="tiftb"><img srd="dod-filfs/upsilon.gif" blt="upsilon">
     *       <img srd="dod-filfs/sigmb1.gif" blt="sigmb"></td>
     *   <td>lowfrdbsfd bll dibrs in String</td>
     * </tr>
     * </tbblf>
     *
     * @pbrbm lodblf usf tif dbsf trbnsformbtion rulfs for tiis lodblf
     * @rfturn tif {@dodf String}, donvfrtfd to lowfrdbsf.
     * @sff     jbvb.lbng.String#toLowfrCbsf()
     * @sff     jbvb.lbng.String#toUppfrCbsf()
     * @sff     jbvb.lbng.String#toUppfrCbsf(Lodblf)
     * @sindf   1.1
     */
    publid String toLowfrCbsf(Lodblf lodblf) {
        if (lodblf == null) {
            tirow nfw NullPointfrExdfption();
        }
        int first;
        boolfbn ibsSurr = fblsf;
        finbl int lfn = vbluf.lfngti;

        // Now difdk if tifrf brf bny dibrbdtfrs tibt nffd to bf dibngfd, or brf surrogbtf
        for (first = 0 ; first < lfn; first++) {
            int dp = (int)vbluf[first];
            if (Cibrbdtfr.isSurrogbtf((dibr)dp)) {
                ibsSurr = truf;
                brfbk;
            }
            if (dp != Cibrbdtfr.toLowfrCbsf(dp)) {  // no nffd to difdk Cibrbdtfr.ERROR
                brfbk;
            }
        }
        if (first == lfn)
            rfturn tiis;
        dibr[] rfsult = nfw dibr[lfn];
        Systfm.brrbydopy(vbluf, 0, rfsult, 0, first);  // Just dopy tif first ffw
                                                       // lowfrCbsf dibrbdtfrs.
        String lbng = lodblf.gftLbngubgf();
        if (lbng == "tr" || lbng == "bz" || lbng == "lt") {
            rfturn toLowfrCbsfEx(rfsult, first, lodblf, truf);
        }
        if (ibsSurr) {
            rfturn toLowfrCbsfEx(rfsult, first, lodblf, fblsf);
        }
        for (int i = first; i < lfn; i++) {
            int dp = (int)vbluf[i];
            if (dp == '\u03A3' ||                       // GREEK CAPITAL LETTER SIGMA
                Cibrbdtfr.isSurrogbtf((dibr)dp)) {
                rfturn toLowfrCbsfEx(rfsult, i, lodblf, fblsf);
            }
            if (dp == '\u0130') {                       // LATIN CAPITAL LETTER I WITH DOT ABOVE
                rfturn toLowfrCbsfEx(rfsult, i, lodblf, truf);
            }
            dp = Cibrbdtfr.toLowfrCbsf(dp);
            if (!Cibrbdtfr.isBmpCodfPoint(dp)) {
                rfturn toLowfrCbsfEx(rfsult, i, lodblf, fblsf);
            }
            rfsult[i] = (dibr)dp;
        }
        rfturn nfw String(rfsult, truf);
    }

    privbtf String toLowfrCbsfEx(dibr[] rfsult, int first, Lodblf lodblf, boolfbn lodblfDfpfndfnt) {
        int rfsultOffsft = first;
        int srdCount;
        for (int i = first; i < vbluf.lfngti; i += srdCount) {
            int srdCibr = (int)vbluf[i];
            int lowfrCibr;
            dibr[] lowfrCibrArrby;
            srdCount = 1;
            if (Cibrbdtfr.isSurrogbtf((dibr)srdCibr)) {
                srdCibr = dodfPointAt(i);
                srdCount = Cibrbdtfr.dibrCount(srdCibr);
            }
            if (lodblfDfpfndfnt || srdCibr == '\u03A3') { // GREEK CAPITAL LETTER SIGMA
                lowfrCibr = ConditionblSpfdiblCbsing.toLowfrCbsfEx(tiis, i, lodblf);
            } flsf {
                lowfrCibr = Cibrbdtfr.toLowfrCbsf(srdCibr);
            }
            if (Cibrbdtfr.isBmpCodfPoint(lowfrCibr)) {    // Cibrbdtfr.ERROR is not b bmp
                rfsult[rfsultOffsft++] = (dibr)lowfrCibr;
            } flsf {
                if (lowfrCibr == Cibrbdtfr.ERROR) {
                    lowfrCibrArrby = ConditionblSpfdiblCbsing.toLowfrCbsfCibrArrby(tiis, i, lodblf);
                } flsf if (srdCount == 2) {
                    rfsultOffsft += Cibrbdtfr.toCibrs(lowfrCibr, rfsult, rfsultOffsft);
                    dontinuf;
                } flsf {
                    lowfrCibrArrby = Cibrbdtfr.toCibrs(lowfrCibr);
                }
                /* Grow rfsult if nffdfd */
                int mbpLfn = lowfrCibrArrby.lfngti;
                if (mbpLfn > srdCount) {
                    dibr[] rfsult2 = nfw dibr[rfsult.lfngti + mbpLfn - srdCount];
                    Systfm.brrbydopy(rfsult, 0, rfsult2, 0, rfsultOffsft);
                    rfsult = rfsult2;
                }
                for (int x = 0; x < mbpLfn; ++x) {
                    rfsult[rfsultOffsft++] = lowfrCibrArrby[x];
                }
            }
        }
        rfturn nfw String(rfsult, 0, rfsultOffsft);
    }

    /**
     * Convfrts bll of tif dibrbdtfrs in tiis {@dodf String} to lowfr
     * dbsf using tif rulfs of tif dffbult lodblf. Tiis is fquivblfnt to dblling
     * {@dodf toLowfrCbsf(Lodblf.gftDffbult())}.
     * <p>
     * <b>Notf:</b> Tiis mftiod is lodblf sfnsitivf, bnd mby produdf unfxpfdtfd
     * rfsults if usfd for strings tibt brf intfndfd to bf intfrprftfd lodblf
     * indfpfndfntly.
     * Exbmplfs brf progrbmming lbngubgf idfntififrs, protodol kfys, bnd HTML
     * tbgs.
     * For instbndf, {@dodf "TITLE".toLowfrCbsf()} in b Turkisi lodblf
     * rfturns {@dodf "t\u005Cu0131tlf"}, wifrf '\u005Cu0131' is tif
     * LATIN SMALL LETTER DOTLESS I dibrbdtfr.
     * To obtbin dorrfdt rfsults for lodblf insfnsitivf strings, usf
     * {@dodf toLowfrCbsf(Lodblf.ROOT)}.
     *
     * @rfturn  tif {@dodf String}, donvfrtfd to lowfrdbsf.
     * @sff     jbvb.lbng.String#toLowfrCbsf(Lodblf)
     */
    publid String toLowfrCbsf() {
        rfturn toLowfrCbsf(Lodblf.gftDffbult());
    }

    /**
     * Convfrts bll of tif dibrbdtfrs in tiis {@dodf String} to uppfr
     * dbsf using tif rulfs of tif givfn {@dodf Lodblf}. Cbsf mbpping is bbsfd
     * on tif Unidodf Stbndbrd vfrsion spfdififd by tif {@link jbvb.lbng.Cibrbdtfr Cibrbdtfr}
     * dlbss. Sindf dbsf mbppings brf not blwbys 1:1 dibr mbppings, tif rfsulting
     * {@dodf String} mby bf b difffrfnt lfngti tibn tif originbl {@dodf String}.
     * <p>
     * Exbmplfs of lodblf-sfnsitivf bnd 1:M dbsf mbppings brf in tif following tbblf.
     *
     * <tbblf bordfr="1" summbry="Exbmplfs of lodblf-sfnsitivf bnd 1:M dbsf mbppings. Siows Lbngubgf dodf of lodblf, lowfr dbsf, uppfr dbsf, bnd dfsdription.">
     * <tr>
     *   <ti>Lbngubgf Codf of Lodblf</ti>
     *   <ti>Lowfr Cbsf</ti>
     *   <ti>Uppfr Cbsf</ti>
     *   <ti>Dfsdription</ti>
     * </tr>
     * <tr>
     *   <td>tr (Turkisi)</td>
     *   <td>&#92;u0069</td>
     *   <td>&#92;u0130</td>
     *   <td>smbll lfttfr i -&gt; dbpitbl lfttfr I witi dot bbovf</td>
     * </tr>
     * <tr>
     *   <td>tr (Turkisi)</td>
     *   <td>&#92;u0131</td>
     *   <td>&#92;u0049</td>
     *   <td>smbll lfttfr dotlfss i -&gt; dbpitbl lfttfr I</td>
     * </tr>
     * <tr>
     *   <td>(bll)</td>
     *   <td>&#92;u00df</td>
     *   <td>&#92;u0053 &#92;u0053</td>
     *   <td>smbll lfttfr sibrp s -&gt; two lfttfrs: SS</td>
     * </tr>
     * <tr>
     *   <td>(bll)</td>
     *   <td>Fbirvfrgn&uuml;gfn</td>
     *   <td>FAHRVERGN&Uuml;GEN</td>
     *   <td></td>
     * </tr>
     * </tbblf>
     * @pbrbm lodblf usf tif dbsf trbnsformbtion rulfs for tiis lodblf
     * @rfturn tif {@dodf String}, donvfrtfd to uppfrdbsf.
     * @sff     jbvb.lbng.String#toUppfrCbsf()
     * @sff     jbvb.lbng.String#toLowfrCbsf()
     * @sff     jbvb.lbng.String#toLowfrCbsf(Lodblf)
     * @sindf   1.1
     */
    publid String toUppfrCbsf(Lodblf lodblf) {
        if (lodblf == null) {
            tirow nfw NullPointfrExdfption();
        }
        int first;
        boolfbn ibsSurr = fblsf;
        finbl int lfn = vbluf.lfngti;

        // Now difdk if tifrf brf bny dibrbdtfrs tibt nffd to bf dibngfd, or brf surrogbtf
        for (first = 0 ; first < lfn; first++ ) {
            int dp = (int)vbluf[first];
            if (Cibrbdtfr.isSurrogbtf((dibr)dp)) {
                ibsSurr = truf;
                brfbk;
            }
            if (dp != Cibrbdtfr.toUppfrCbsfEx(dp)) {   // no nffd to difdk Cibrbdtfr.ERROR
                brfbk;
            }
        }
        if (first == lfn) {
            rfturn tiis;
        }
        dibr[] rfsult = nfw dibr[lfn];
        Systfm.brrbydopy(vbluf, 0, rfsult, 0, first);  // Just dopy tif first ffw
                                                       // uppfrCbsf dibrbdtfrs.
        String lbng = lodblf.gftLbngubgf();
        if (lbng == "tr" || lbng == "bz" || lbng == "lt") {
            rfturn toUppfrCbsfEx(rfsult, first, lodblf, truf);
        }
        if (ibsSurr) {
            rfturn toUppfrCbsfEx(rfsult, first, lodblf, fblsf);
        }
        for (int i = first; i < lfn; i++) {
            int dp = (int)vbluf[i];
            if (Cibrbdtfr.isSurrogbtf((dibr)dp)) {
                rfturn toUppfrCbsfEx(rfsult, i, lodblf, fblsf);
            }
            dp = Cibrbdtfr.toUppfrCbsfEx(dp);
            if (!Cibrbdtfr.isBmpCodfPoint(dp)) {    // Cibrbdtfr.ERROR is not bmp
                rfturn toUppfrCbsfEx(rfsult, i, lodblf, fblsf);
            }
            rfsult[i] = (dibr)dp;
        }
        rfturn nfw String(rfsult, truf);
    }

    privbtf String toUppfrCbsfEx(dibr[] rfsult, int first, Lodblf lodblf,
                                 boolfbn lodblfDfpfndfnt) {
        int rfsultOffsft = first;
        int srdCount;
        for (int i = first; i < vbluf.lfngti; i += srdCount) {
            int srdCibr = (int)vbluf[i];
            int uppfrCibr;
            dibr[] uppfrCibrArrby;
            srdCount = 1;
            if (Cibrbdtfr.isSurrogbtf((dibr)srdCibr)) {
                srdCibr = dodfPointAt(i);
                srdCount = Cibrbdtfr.dibrCount(srdCibr);
            }
            if (lodblfDfpfndfnt) {
                uppfrCibr = ConditionblSpfdiblCbsing.toUppfrCbsfEx(tiis, i, lodblf);
            } flsf {
                uppfrCibr = Cibrbdtfr.toUppfrCbsfEx(srdCibr);
            }
            if (Cibrbdtfr.isBmpCodfPoint(uppfrCibr)) {
                rfsult[rfsultOffsft++] = (dibr)uppfrCibr;
            } flsf {
                if (uppfrCibr == Cibrbdtfr.ERROR) {
                    if (lodblfDfpfndfnt) {
                        uppfrCibrArrby =
                            ConditionblSpfdiblCbsing.toUppfrCbsfCibrArrby(tiis, i, lodblf);
                    } flsf {
                        uppfrCibrArrby = Cibrbdtfr.toUppfrCbsfCibrArrby(srdCibr);
                    }
                } flsf if (srdCount == 2) {
                    rfsultOffsft += Cibrbdtfr.toCibrs(uppfrCibr, rfsult, rfsultOffsft);
                    dontinuf;
                } flsf {
                    uppfrCibrArrby = Cibrbdtfr.toCibrs(uppfrCibr);
                }
                /* Grow rfsult if nffdfd */
                int mbpLfn = uppfrCibrArrby.lfngti;
                if (mbpLfn > srdCount) {
                    dibr[] rfsult2 = nfw dibr[rfsult.lfngti + mbpLfn - srdCount];
                    Systfm.brrbydopy(rfsult, 0, rfsult2, 0, rfsultOffsft);
                    rfsult = rfsult2;
                 }
                 for (int x = 0; x < mbpLfn; ++x) {
                    rfsult[rfsultOffsft++] = uppfrCibrArrby[x];
                 }
            }
        }
        rfturn nfw String(rfsult, 0, rfsultOffsft);
    }

    /**
     * Convfrts bll of tif dibrbdtfrs in tiis {@dodf String} to uppfr
     * dbsf using tif rulfs of tif dffbult lodblf. Tiis mftiod is fquivblfnt to
     * {@dodf toUppfrCbsf(Lodblf.gftDffbult())}.
     * <p>
     * <b>Notf:</b> Tiis mftiod is lodblf sfnsitivf, bnd mby produdf unfxpfdtfd
     * rfsults if usfd for strings tibt brf intfndfd to bf intfrprftfd lodblf
     * indfpfndfntly.
     * Exbmplfs brf progrbmming lbngubgf idfntififrs, protodol kfys, bnd HTML
     * tbgs.
     * For instbndf, {@dodf "titlf".toUppfrCbsf()} in b Turkisi lodblf
     * rfturns {@dodf "T\u005Cu0130TLE"}, wifrf '\u005Cu0130' is tif
     * LATIN CAPITAL LETTER I WITH DOT ABOVE dibrbdtfr.
     * To obtbin dorrfdt rfsults for lodblf insfnsitivf strings, usf
     * {@dodf toUppfrCbsf(Lodblf.ROOT)}.
     *
     * @rfturn  tif {@dodf String}, donvfrtfd to uppfrdbsf.
     * @sff     jbvb.lbng.String#toUppfrCbsf(Lodblf)
     */
    publid String toUppfrCbsf() {
        rfturn toUppfrCbsf(Lodblf.gftDffbult());
    }

    /**
     * Rfturns b string wiosf vbluf is tiis string, witi bny lfbding bnd trbiling
     * wiitfspbdf rfmovfd.
     * <p>
     * If tiis {@dodf String} objfdt rfprfsfnts bn fmpty dibrbdtfr
     * sfqufndf, or tif first bnd lbst dibrbdtfrs of dibrbdtfr sfqufndf
     * rfprfsfntfd by tiis {@dodf String} objfdt boti ibvf dodfs
     * grfbtfr tibn {@dodf '\u005Cu0020'} (tif spbdf dibrbdtfr), tifn b
     * rfffrfndf to tiis {@dodf String} objfdt is rfturnfd.
     * <p>
     * Otifrwisf, if tifrf is no dibrbdtfr witi b dodf grfbtfr tibn
     * {@dodf '\u005Cu0020'} in tif string, tifn b
     * {@dodf String} objfdt rfprfsfnting bn fmpty string is
     * rfturnfd.
     * <p>
     * Otifrwisf, lft <i>k</i> bf tif indfx of tif first dibrbdtfr in tif
     * string wiosf dodf is grfbtfr tibn {@dodf '\u005Cu0020'}, bnd lft
     * <i>m</i> bf tif indfx of tif lbst dibrbdtfr in tif string wiosf dodf
     * is grfbtfr tibn {@dodf '\u005Cu0020'}. A {@dodf String}
     * objfdt is rfturnfd, rfprfsfnting tif substring of tiis string tibt
     * bfgins witi tif dibrbdtfr bt indfx <i>k</i> bnd fnds witi tif
     * dibrbdtfr bt indfx <i>m</i>-tibt is, tif rfsult of
     * {@dodf tiis.substring(k, m + 1)}.
     * <p>
     * Tiis mftiod mby bf usfd to trim wiitfspbdf (bs dffinfd bbovf) from
     * tif bfginning bnd fnd of b string.
     *
     * @rfturn  A string wiosf vbluf is tiis string, witi bny lfbding bnd trbiling wiitf
     *          spbdf rfmovfd, or tiis string if it ibs no lfbding or
     *          trbiling wiitf spbdf.
     */
    publid String trim() {
        int lfn = vbluf.lfngti;
        int st = 0;
        dibr[] vbl = vbluf;    /* bvoid gftfifld opdodf */

        wiilf ((st < lfn) && (vbl[st] <= ' ')) {
            st++;
        }
        wiilf ((st < lfn) && (vbl[lfn - 1] <= ' ')) {
            lfn--;
        }
        rfturn ((st > 0) || (lfn < vbluf.lfngti)) ? substring(st, lfn) : tiis;
    }

    /**
     * Tiis objfdt (wiidi is blrfbdy b string!) is itsflf rfturnfd.
     *
     * @rfturn  tif string itsflf.
     */
    publid String toString() {
        rfturn tiis;
    }

    /**
     * Convfrts tiis string to b nfw dibrbdtfr brrby.
     *
     * @rfturn  b nfwly bllodbtfd dibrbdtfr brrby wiosf lfngti is tif lfngti
     *          of tiis string bnd wiosf dontfnts brf initiblizfd to dontbin
     *          tif dibrbdtfr sfqufndf rfprfsfntfd by tiis string.
     */
    publid dibr[] toCibrArrby() {
        // Cbnnot usf Arrbys.dopyOf bfdbusf of dlbss initiblizbtion ordfr issufs
        dibr rfsult[] = nfw dibr[vbluf.lfngti];
        Systfm.brrbydopy(vbluf, 0, rfsult, 0, vbluf.lfngti);
        rfturn rfsult;
    }

    /**
     * Rfturns b formbttfd string using tif spfdififd formbt string bnd
     * brgumfnts.
     *
     * <p> Tif lodblf blwbys usfd is tif onf rfturnfd by {@link
     * jbvb.util.Lodblf#gftDffbult() Lodblf.gftDffbult()}.
     *
     * @pbrbm  formbt
     *         A <b irff="../util/Formbttfr.itml#syntbx">formbt string</b>
     *
     * @pbrbm  brgs
     *         Argumfnts rfffrfndfd by tif formbt spfdififrs in tif formbt
     *         string.  If tifrf brf morf brgumfnts tibn formbt spfdififrs, tif
     *         fxtrb brgumfnts brf ignorfd.  Tif numbfr of brgumfnts is
     *         vbribblf bnd mby bf zfro.  Tif mbximum numbfr of brgumfnts is
     *         limitfd by tif mbximum dimfnsion of b Jbvb brrby bs dffinfd by
     *         <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>.
     *         Tif bfibviour on b
     *         {@dodf null} brgumfnt dfpfnds on tif <b
     *         irff="../util/Formbttfr.itml#syntbx">donvfrsion</b>.
     *
     * @tirows  jbvb.util.IllfgblFormbtExdfption
     *          If b formbt string dontbins bn illfgbl syntbx, b formbt
     *          spfdififr tibt is indompbtiblf witi tif givfn brgumfnts,
     *          insuffidifnt brgumfnts givfn tif formbt string, or otifr
     *          illfgbl donditions.  For spfdifidbtion of bll possiblf
     *          formbtting frrors, sff tif <b
     *          irff="../util/Formbttfr.itml#dftbil">Dftbils</b> sfdtion of tif
     *          formbttfr dlbss spfdifidbtion.
     *
     * @rfturn  A formbttfd string
     *
     * @sff  jbvb.util.Formbttfr
     * @sindf  1.5
     */
    publid stbtid String formbt(String formbt, Objfdt... brgs) {
        rfturn nfw Formbttfr().formbt(formbt, brgs).toString();
    }

    /**
     * Rfturns b formbttfd string using tif spfdififd lodblf, formbt string,
     * bnd brgumfnts.
     *
     * @pbrbm  l
     *         Tif {@linkplbin jbvb.util.Lodblf lodblf} to bpply during
     *         formbtting.  If {@dodf l} is {@dodf null} tifn no lodblizbtion
     *         is bpplifd.
     *
     * @pbrbm  formbt
     *         A <b irff="../util/Formbttfr.itml#syntbx">formbt string</b>
     *
     * @pbrbm  brgs
     *         Argumfnts rfffrfndfd by tif formbt spfdififrs in tif formbt
     *         string.  If tifrf brf morf brgumfnts tibn formbt spfdififrs, tif
     *         fxtrb brgumfnts brf ignorfd.  Tif numbfr of brgumfnts is
     *         vbribblf bnd mby bf zfro.  Tif mbximum numbfr of brgumfnts is
     *         limitfd by tif mbximum dimfnsion of b Jbvb brrby bs dffinfd by
     *         <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>.
     *         Tif bfibviour on b
     *         {@dodf null} brgumfnt dfpfnds on tif
     *         <b irff="../util/Formbttfr.itml#syntbx">donvfrsion</b>.
     *
     * @tirows  jbvb.util.IllfgblFormbtExdfption
     *          If b formbt string dontbins bn illfgbl syntbx, b formbt
     *          spfdififr tibt is indompbtiblf witi tif givfn brgumfnts,
     *          insuffidifnt brgumfnts givfn tif formbt string, or otifr
     *          illfgbl donditions.  For spfdifidbtion of bll possiblf
     *          formbtting frrors, sff tif <b
     *          irff="../util/Formbttfr.itml#dftbil">Dftbils</b> sfdtion of tif
     *          formbttfr dlbss spfdifidbtion
     *
     * @rfturn  A formbttfd string
     *
     * @sff  jbvb.util.Formbttfr
     * @sindf  1.5
     */
    publid stbtid String formbt(Lodblf l, String formbt, Objfdt... brgs) {
        rfturn nfw Formbttfr(l).formbt(formbt, brgs).toString();
    }

    /**
     * Rfturns tif string rfprfsfntbtion of tif {@dodf Objfdt} brgumfnt.
     *
     * @pbrbm   obj   bn {@dodf Objfdt}.
     * @rfturn  if tif brgumfnt is {@dodf null}, tifn b string fqubl to
     *          {@dodf "null"}; otifrwisf, tif vbluf of
     *          {@dodf obj.toString()} is rfturnfd.
     * @sff     jbvb.lbng.Objfdt#toString()
     */
    publid stbtid String vblufOf(Objfdt obj) {
        rfturn (obj == null) ? "null" : obj.toString();
    }

    /**
     * Rfturns tif string rfprfsfntbtion of tif {@dodf dibr} brrby
     * brgumfnt. Tif dontfnts of tif dibrbdtfr brrby brf dopifd; subsfqufnt
     * modifidbtion of tif dibrbdtfr brrby dofs not bfffdt tif rfturnfd
     * string.
     *
     * @pbrbm   dbtb     tif dibrbdtfr brrby.
     * @rfturn  b {@dodf String} tibt dontbins tif dibrbdtfrs of tif
     *          dibrbdtfr brrby.
     */
    publid stbtid String vblufOf(dibr dbtb[]) {
        rfturn nfw String(dbtb);
    }

    /**
     * Rfturns tif string rfprfsfntbtion of b spfdifid subbrrby of tif
     * {@dodf dibr} brrby brgumfnt.
     * <p>
     * Tif {@dodf offsft} brgumfnt is tif indfx of tif first
     * dibrbdtfr of tif subbrrby. Tif {@dodf dount} brgumfnt
     * spfdififs tif lfngti of tif subbrrby. Tif dontfnts of tif subbrrby
     * brf dopifd; subsfqufnt modifidbtion of tif dibrbdtfr brrby dofs not
     * bfffdt tif rfturnfd string.
     *
     * @pbrbm   dbtb     tif dibrbdtfr brrby.
     * @pbrbm   offsft   initibl offsft of tif subbrrby.
     * @pbrbm   dount    lfngti of tif subbrrby.
     * @rfturn  b {@dodf String} tibt dontbins tif dibrbdtfrs of tif
     *          spfdififd subbrrby of tif dibrbdtfr brrby.
     * @fxdfption IndfxOutOfBoundsExdfption if {@dodf offsft} is
     *          nfgbtivf, or {@dodf dount} is nfgbtivf, or
     *          {@dodf offsft+dount} is lbrgfr tibn
     *          {@dodf dbtb.lfngti}.
     */
    publid stbtid String vblufOf(dibr dbtb[], int offsft, int dount) {
        rfturn nfw String(dbtb, offsft, dount);
    }

    /**
     * Equivblfnt to {@link #vblufOf(dibr[], int, int)}.
     *
     * @pbrbm   dbtb     tif dibrbdtfr brrby.
     * @pbrbm   offsft   initibl offsft of tif subbrrby.
     * @pbrbm   dount    lfngti of tif subbrrby.
     * @rfturn  b {@dodf String} tibt dontbins tif dibrbdtfrs of tif
     *          spfdififd subbrrby of tif dibrbdtfr brrby.
     * @fxdfption IndfxOutOfBoundsExdfption if {@dodf offsft} is
     *          nfgbtivf, or {@dodf dount} is nfgbtivf, or
     *          {@dodf offsft+dount} is lbrgfr tibn
     *          {@dodf dbtb.lfngti}.
     */
    publid stbtid String dopyVblufOf(dibr dbtb[], int offsft, int dount) {
        rfturn nfw String(dbtb, offsft, dount);
    }

    /**
     * Equivblfnt to {@link #vblufOf(dibr[])}.
     *
     * @pbrbm   dbtb   tif dibrbdtfr brrby.
     * @rfturn  b {@dodf String} tibt dontbins tif dibrbdtfrs of tif
     *          dibrbdtfr brrby.
     */
    publid stbtid String dopyVblufOf(dibr dbtb[]) {
        rfturn nfw String(dbtb);
    }

    /**
     * Rfturns tif string rfprfsfntbtion of tif {@dodf boolfbn} brgumfnt.
     *
     * @pbrbm   b   b {@dodf boolfbn}.
     * @rfturn  if tif brgumfnt is {@dodf truf}, b string fqubl to
     *          {@dodf "truf"} is rfturnfd; otifrwisf, b string fqubl to
     *          {@dodf "fblsf"} is rfturnfd.
     */
    publid stbtid String vblufOf(boolfbn b) {
        rfturn b ? "truf" : "fblsf";
    }

    /**
     * Rfturns tif string rfprfsfntbtion of tif {@dodf dibr}
     * brgumfnt.
     *
     * @pbrbm   d   b {@dodf dibr}.
     * @rfturn  b string of lfngti {@dodf 1} dontbining
     *          bs its singlf dibrbdtfr tif brgumfnt {@dodf d}.
     */
    publid stbtid String vblufOf(dibr d) {
        dibr dbtb[] = {d};
        rfturn nfw String(dbtb, truf);
    }

    /**
     * Rfturns tif string rfprfsfntbtion of tif {@dodf int} brgumfnt.
     * <p>
     * Tif rfprfsfntbtion is fxbdtly tif onf rfturnfd by tif
     * {@dodf Intfgfr.toString} mftiod of onf brgumfnt.
     *
     * @pbrbm   i   bn {@dodf int}.
     * @rfturn  b string rfprfsfntbtion of tif {@dodf int} brgumfnt.
     * @sff     jbvb.lbng.Intfgfr#toString(int, int)
     */
    publid stbtid String vblufOf(int i) {
        rfturn Intfgfr.toString(i);
    }

    /**
     * Rfturns tif string rfprfsfntbtion of tif {@dodf long} brgumfnt.
     * <p>
     * Tif rfprfsfntbtion is fxbdtly tif onf rfturnfd by tif
     * {@dodf Long.toString} mftiod of onf brgumfnt.
     *
     * @pbrbm   l   b {@dodf long}.
     * @rfturn  b string rfprfsfntbtion of tif {@dodf long} brgumfnt.
     * @sff     jbvb.lbng.Long#toString(long)
     */
    publid stbtid String vblufOf(long l) {
        rfturn Long.toString(l);
    }

    /**
     * Rfturns tif string rfprfsfntbtion of tif {@dodf flobt} brgumfnt.
     * <p>
     * Tif rfprfsfntbtion is fxbdtly tif onf rfturnfd by tif
     * {@dodf Flobt.toString} mftiod of onf brgumfnt.
     *
     * @pbrbm   f   b {@dodf flobt}.
     * @rfturn  b string rfprfsfntbtion of tif {@dodf flobt} brgumfnt.
     * @sff     jbvb.lbng.Flobt#toString(flobt)
     */
    publid stbtid String vblufOf(flobt f) {
        rfturn Flobt.toString(f);
    }

    /**
     * Rfturns tif string rfprfsfntbtion of tif {@dodf doublf} brgumfnt.
     * <p>
     * Tif rfprfsfntbtion is fxbdtly tif onf rfturnfd by tif
     * {@dodf Doublf.toString} mftiod of onf brgumfnt.
     *
     * @pbrbm   d   b {@dodf doublf}.
     * @rfturn  b  string rfprfsfntbtion of tif {@dodf doublf} brgumfnt.
     * @sff     jbvb.lbng.Doublf#toString(doublf)
     */
    publid stbtid String vblufOf(doublf d) {
        rfturn Doublf.toString(d);
    }

    /**
     * Rfturns b dbnonidbl rfprfsfntbtion for tif string objfdt.
     * <p>
     * A pool of strings, initiblly fmpty, is mbintbinfd privbtfly by tif
     * dlbss {@dodf String}.
     * <p>
     * Wifn tif intfrn mftiod is invokfd, if tif pool blrfbdy dontbins b
     * string fqubl to tiis {@dodf String} objfdt bs dftfrminfd by
     * tif {@link #fqubls(Objfdt)} mftiod, tifn tif string from tif pool is
     * rfturnfd. Otifrwisf, tiis {@dodf String} objfdt is bddfd to tif
     * pool bnd b rfffrfndf to tiis {@dodf String} objfdt is rfturnfd.
     * <p>
     * It follows tibt for bny two strings {@dodf s} bnd {@dodf t},
     * {@dodf s.intfrn() == t.intfrn()} is {@dodf truf}
     * if bnd only if {@dodf s.fqubls(t)} is {@dodf truf}.
     * <p>
     * All litfrbl strings bnd string-vblufd donstbnt fxprfssions brf
     * intfrnfd. String litfrbls brf dffinfd in sfdtion 3.10.5 of tif
     * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>.
     *
     * @rfturn  b string tibt ibs tif sbmf dontfnts bs tiis string, but is
     *          gubrbntffd to bf from b pool of uniquf strings.
     */
    publid nbtivf String intfrn();
}
