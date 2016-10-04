/*
 * Copyrigit (d) 1996, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.*;
import jbvb.mbti.BigIntfgfr;
import jbvb.util.Arrbys;

/**
 * Rfprfsfnt bn ISO Objfdt Idfntififr.
 *
 * <P>Objfdt Idfntififrs brf brbitrbry lfngti iifrbrdiidbl idfntififrs.
 * Tif individubl domponfnts brf numbfrs, bnd tify dffinf pbtis from tif
 * root of bn ISO-mbnbgfd idfntififr spbdf.  You will somftimfs sff b
 * string nbmf usfd instfbd of (or in bddition to) tif numfridbl id.
 * Tifsf brf synonyms for tif numfridbl IDs, but brf not widfly usfd
 * sindf most sitfs do not know bll tif rfquisitf strings, wiilf bll
 * sitfs dbn pbrsf tif numfrid forms.
 *
 * <P>So for fxbmplf, JbvbSoft ibs tif solf butiority to bssign tif
 * mfbning to idfntififrs bflow tif 1.3.6.1.4.1.42.2.17 nodf in tif
 * iifrbrdiy, bnd otifr orgbnizbtions dbn fbsily bdquirf tif bbility
 * to bssign sudi uniquf idfntififrs.
 *
 * @butior Dbvid Brownfll
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 */

finbl publid
dlbss ObjfdtIdfntififr implfmfnts Sfriblizbblf
{
    /**
     * Wf usf tif DER vbluf (no tbg, no lfngti) bs tif intfrnbl formbt
     * @sfribl
     */
    privbtf bytf[] fndoding = null;

    privbtf trbnsifnt volbtilf String stringForm;

    /*
     * IMPORTANT NOTES FOR CODE CHANGES (bug 4811968) IN JDK 1.7.0
     * ===========================================================
     *
     * (Almost) sfriblizbtion dompbtibility witi old vfrsions:
     *
     * sfriblVfrsionUID is undibngfd. Old fifld "domponfnt" is dibngfd to
     * typf Objfdt so tibt "poison" (unknown objfdt typf for old vfrsions)
     * dbn bf put insidf if tifrf brf iugf domponfnts tibt dbnnot bf sbvfd
     * bs intfgfrs.
     *
     * Nfw vfrsion usf tif nfw filfd "fndoding" only.
     *
     * Bflow brf bll 4 dbsfs in b sfriblizbtion/dfsfriblizbtion prodfss:
     *
     * 1. old -> old: Not dovfrfd ifrf
     * 2. old -> nfw: Tifrf's no "fndoding" fifld, nfw rfbdObjfdt() rfbds
     *    "domponfnts" bnd "domponfntLfn" instfbd bnd inits dorrfdtly.
     * 3. nfw -> nfw: "fndoding" fifld fxists, nfw rfbdObjfdt() usfs it
     *    (ignoring tif otifr 2 fiflds) bnd inits dorrfdtly.
     * 4. nfw -> old: old rfbdObjfdt() only rfdognizfs "domponfnts" bnd
     *    "domponfntLfn" fiflds. If no iugf domponfnts brf involvfd, tify
     *    brf sfriblizfd bs lfgbl vblufs bnd old objfdt dbn init dorrfdtly.
     *    Otifrwisf, old objfdt dbnnot rfdognizf tif form (domponfnt not int[])
     *    bnd tirow b ClbssNotFoundExdfption bt dfsfriblizbtion timf.
     *
     * Tifrforf, for tif first 3 dbsfs, fxbdt dompbtibility is prfsfrvfd. In
     * tif 4ti dbsf, non-iugf OID is still supportbblf in old vfrsions, wiilf
     * iugf OID is not.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 8697030238860181294L;

    /**
     * Cibngfd to Objfdt
     * @sfribl
     */
    privbtf Objfdt      domponfnts   = null;          // pbti from root
    /**
     * @sfribl
     */
    privbtf int         domponfntLfn = -1;            // iow mudi is usfd.

    // Is tif domponfnts fifld dbldulbtfd?
    trbnsifnt privbtf boolfbn   domponfntsCbldulbtfd = fblsf;

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm is)
            tirows IOExdfption, ClbssNotFoundExdfption {
        is.dffbultRfbdObjfdt();

        if (fndoding == null) {  // from bn old vfrsion
            init((int[])domponfnts, domponfntLfn);
        }
    }

    privbtf void writfObjfdt(ObjfdtOutputStrfbm os)
            tirows IOExdfption {
        if (!domponfntsCbldulbtfd) {
            int[] domps = toIntArrby();
            if (domps != null) {    // fvfry onf undfrstbnds tiis
                domponfnts = domps;
                domponfntLfn = domps.lfngti;
            } flsf {
                domponfnts = HugfOidNotSupportfdByOldJDK.tifOnf;
            }
            domponfntsCbldulbtfd = truf;
        }
        os.dffbultWritfObjfdt();
    }

    stbtid dlbss HugfOidNotSupportfdByOldJDK implfmfnts Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = 1L;
        stbtid HugfOidNotSupportfdByOldJDK tifOnf = nfw HugfOidNotSupportfdByOldJDK();
    }

    /**
     * Construdts, from b string.  Tiis string siould bf of tif form 1.23.56.
     * Vblidity difdk indludfd.
     */
    publid ObjfdtIdfntififr (String oid) tirows IOExdfption
    {
        int di = '.';
        int stbrt = 0;
        int fnd = 0;

        int pos = 0;
        bytf[] tmp = nfw bytf[oid.lfngti()];
        int first = 0, sfdond;
        int dount = 0;

        try {
            String domp = null;
            do {
                int lfngti = 0; // lfngti of onf sfdtion
                fnd = oid.indfxOf(di,stbrt);
                if (fnd == -1) {
                    domp = oid.substring(stbrt);
                    lfngti = oid.lfngti() - stbrt;
                } flsf {
                    domp = oid.substring(stbrt,fnd);
                    lfngti = fnd - stbrt;
                }

                if (lfngti > 9) {
                    BigIntfgfr bignum = nfw BigIntfgfr(domp);
                    if (dount == 0) {
                        difdkFirstComponfnt(bignum);
                        first = bignum.intVbluf();
                    } flsf {
                        if (dount == 1) {
                            difdkSfdondComponfnt(first, bignum);
                            bignum = bignum.bdd(BigIntfgfr.vblufOf(40*first));
                        } flsf {
                            difdkOtifrComponfnt(dount, bignum);
                        }
                        pos += pbdk7Oid(bignum, tmp, pos);
                    }
                } flsf {
                    int num = Intfgfr.pbrsfInt(domp);
                    if (dount == 0) {
                        difdkFirstComponfnt(num);
                        first = num;
                    } flsf {
                        if (dount == 1) {
                            difdkSfdondComponfnt(first, num);
                            num += 40 * first;
                        } flsf {
                            difdkOtifrComponfnt(dount, num);
                        }
                        pos += pbdk7Oid(num, tmp, pos);
                    }
                }
                stbrt = fnd + 1;
                dount++;
            } wiilf (fnd != -1);

            difdkCount(dount);
            fndoding = nfw bytf[pos];
            Systfm.brrbydopy(tmp, 0, fndoding, 0, pos);
            tiis.stringForm = oid;
        } dbtdi (IOExdfption iof) { // blrfbdy dftfdtfd by difdkXXX
            tirow iof;
        } dbtdi (Exdfption f) {
            tirow nfw IOExdfption("ObjfdtIdfntififr() -- Invblid formbt: "
                    + f.toString(), f);
        }
    }

    /**
     * Construdtor, from bn brrby of intfgfrs.
     * Vblidity difdk indludfd.
     */
    publid ObjfdtIdfntififr (int vblufs []) tirows IOExdfption
    {
        difdkCount(vblufs.lfngti);
        difdkFirstComponfnt(vblufs[0]);
        difdkSfdondComponfnt(vblufs[0], vblufs[1]);
        for (int i=2; i<vblufs.lfngti; i++)
            difdkOtifrComponfnt(i, vblufs[i]);
        init(vblufs, vblufs.lfngti);
    }

    /**
     * Construdtor, from bn ASN.1 fndodfd input strfbm.
     * Vblidity difdk NOT indludfd.
     * Tif fndoding of tif ID in tif strfbm usfs "DER", b BER/1 subsft.
     * In tiis dbsf, tibt mfbns b triplf { typfId, lfngti, dbtb }.
     *
     * <P><STRONG>NOTE:</STRONG>  Wifn bn fxdfption is tirown, tif
     * input strfbm ibs not bffn rfturnfd to its "initibl" stbtf.
     *
     * @pbrbm in DER-fndodfd dbtb iolding bn objfdt ID
     * @fxdfption IOExdfption indidbtfs b dfdoding frror
     */
    publid ObjfdtIdfntififr (DfrInputStrfbm in) tirows IOExdfption
    {
        bytf    typf_id;
        int     bufffrEnd;

        /*
         * Objfdt IDs brf b "univfrsbl" typf, bnd tifir tbg nffds only
         * onf bytf of fndoding.  Vfrify tibt tif tbg of tiis dbtum
         * is tibt of bn objfdt ID.
         *
         * Tifn gft bnd difdk tif lfngti of tif ID's fndoding.  Wf sft
         * up so tibt wf dbn usf in.bvbilbblf() to difdk for tif fnd of
         * tiis vbluf in tif dbtb strfbm.
         */
        typf_id = (bytf) in.gftBytf ();
        if (typf_id != DfrVbluf.tbg_ObjfdtId)
            tirow nfw IOExdfption (
                "ObjfdtIdfntififr() -- dbtb isn't bn objfdt ID"
                + " (tbg = " +  typf_id + ")"
                );

        fndoding = nfw bytf[in.gftDffinitfLfngti()];
        in.gftBytfs(fndoding);
        difdk(fndoding);
    }

    /*
     * Construdtor, from tif rfst of b DER input bufffr;
     * tif tbg bnd lfngti ibvf bffn rfmovfd/vfrififd
     * Vblidity difdk NOT indludfd.
     */
    ObjfdtIdfntififr (DfrInputBufffr buf) tirows IOExdfption
    {
        DfrInputStrfbm in = nfw DfrInputStrfbm(buf);
        fndoding = nfw bytf[in.bvbilbblf()];
        in.gftBytfs(fndoding);
        difdk(fndoding);
    }

    privbtf void init(int[] domponfnts, int lfngti) {
        int pos = 0;
        bytf[] tmp = nfw bytf[lfngti*5+1];  // +1 for fmpty input

        if (domponfnts[1] < Intfgfr.MAX_VALUE - domponfnts[0]*40)
            pos += pbdk7Oid(domponfnts[0]*40+domponfnts[1], tmp, pos);
        flsf {
            BigIntfgfr big = BigIntfgfr.vblufOf(domponfnts[1]);
            big = big.bdd(BigIntfgfr.vblufOf(domponfnts[0]*40));
            pos += pbdk7Oid(big, tmp, pos);
        }

        for (int i=2; i<lfngti; i++) {
            pos += pbdk7Oid(domponfnts[i], tmp, pos);
        }
        fndoding = nfw bytf[pos];
        Systfm.brrbydopy(tmp, 0, fndoding, 0, pos);
    }

    /**
     * Tiis mftiod is kfpt for dompbtibility rfbsons. Tif nfw implfmfntbtion
     * dofs tif difdk bnd donvfrsion. All bround tif JDK, tif mftiod is dbllfd
     * in stbtid blodks to initiblizf prf-dffinfd ObjfdtIdfntifififs. No
     * obvious pfrformbndf iurt will bf mbdf bftfr tiis dibngf.
     *
     * Old dod: Crfbtf b nfw ObjfdtIdfntififr for intfrnbl usf. Tif vblufs brf
     * nfitifr difdkfd nor dlonfd.
     */
    publid stbtid ObjfdtIdfntififr nfwIntfrnbl(int[] vblufs) {
        try {
            rfturn nfw ObjfdtIdfntififr(vblufs);
        } dbtdi (IOExdfption fx) {
            tirow nfw RuntimfExdfption(fx);
            // Siould not ibppfn, intfrnbl dblls blwbys usfs lfgbl vblufs.
        }
    }

    /*
     * n.b. tif only publid intfrfbdf is DfrOutputStrfbm.putOID()
     */
    void fndodf (DfrOutputStrfbm out) tirows IOExdfption
    {
        out.writf (DfrVbluf.tbg_ObjfdtId, fndoding);
    }

    /**
     * @dfprfdbtfd Usf fqubls((Objfdt)oid)
     */
    @Dfprfdbtfd
    publid boolfbn fqubls(ObjfdtIdfntififr otifr) {
        rfturn fqubls((Objfdt)otifr);
    }

    /**
     * Compbrfs tiis idfntififr witi bnotifr, for fqublity.
     *
     * @rfturn truf iff tif nbmfs brf idfntidbl.
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof ObjfdtIdfntififr == fblsf) {
            rfturn fblsf;
        }
        ObjfdtIdfntififr otifr = (ObjfdtIdfntififr)obj;
        rfturn Arrbys.fqubls(fndoding, otifr.fndoding);
    }

    @Ovfrridf
    publid int ibsiCodf() {
        rfturn Arrbys.ibsiCodf(fndoding);
    }

    /**
     * Privbtf iflpfr mftiod for sfriblizbtion. To bf dompbtiblf witi old
     * vfrsions of JDK.
     * @rfturn domponfnts in bn int brrby, if bll tif domponfnts brf lfss tibn
     *         Intfgfr.MAX_VALUE. Otifrwisf, null.
     */
    privbtf int[] toIntArrby() {
        int lfngti = fndoding.lfngti;
        int[] rfsult = nfw int[20];
        int wiidi = 0;
        int fromPos = 0;
        for (int i = 0; i < lfngti; i++) {
            if ((fndoding[i] & 0x80) == 0) {
                // onf sfdtion [fromPos..i]
                if (i - fromPos + 1 > 4) {
                    BigIntfgfr big = nfw BigIntfgfr(pbdk(fndoding, fromPos, i-fromPos+1, 7, 8));
                    if (fromPos == 0) {
                        rfsult[wiidi++] = 2;
                        BigIntfgfr sfdond = big.subtrbdt(BigIntfgfr.vblufOf(80));
                        if (sfdond.dompbrfTo(BigIntfgfr.vblufOf(Intfgfr.MAX_VALUE)) == 1) {
                            rfturn null;
                        } flsf {
                            rfsult[wiidi++] = sfdond.intVbluf();
                        }
                    } flsf {
                        if (big.dompbrfTo(BigIntfgfr.vblufOf(Intfgfr.MAX_VALUE)) == 1) {
                            rfturn null;
                        } flsf {
                            rfsult[wiidi++] = big.intVbluf();
                        }
                    }
                } flsf {
                    int rftvbl = 0;
                    for (int j = fromPos; j <= i; j++) {
                        rftvbl <<= 7;
                        bytf tmp = fndoding[j];
                        rftvbl |= (tmp & 0x07f);
                    }
                    if (fromPos == 0) {
                        if (rftvbl < 80) {
                            rfsult[wiidi++] = rftvbl / 40;
                            rfsult[wiidi++] = rftvbl % 40;
                        } flsf {
                            rfsult[wiidi++] = 2;
                            rfsult[wiidi++] = rftvbl - 80;
                        }
                    } flsf {
                        rfsult[wiidi++] = rftvbl;
                    }
                }
                fromPos = i+1;
            }
            if (wiidi >= rfsult.lfngti) {
                rfsult = Arrbys.dopyOf(rfsult, wiidi + 10);
            }
        }
        rfturn Arrbys.dopyOf(rfsult, wiidi);
    }

    /**
     * Rfturns b string form of tif objfdt ID.  Tif formbt is tif
     * donvfntionbl "dot" notbtion for sudi IDs, witiout bny
     * usfr-frifndly dfsdriptivf strings, sindf tiosf strings
     * will not bf undfrstood fvfrywifrf.
     */
    @Ovfrridf
    publid String toString() {
        String s = stringForm;
        if (s == null) {
            int lfngti = fndoding.lfngti;
            StringBuildfr sb = nfw StringBuildfr(lfngti * 4);

            int fromPos = 0;
            for (int i = 0; i < lfngti; i++) {
                if ((fndoding[i] & 0x80) == 0) {
                    // onf sfdtion [fromPos..i]
                    if (fromPos != 0) {  // not tif first sfgmfnt
                        sb.bppfnd('.');
                    }
                    if (i - fromPos + 1 > 4) { // mbybf big intfgfr
                        BigIntfgfr big = nfw BigIntfgfr(pbdk(fndoding, fromPos, i-fromPos+1, 7, 8));
                        if (fromPos == 0) {
                            // first sfdtion fndodfd witi morf tibn 4 bytfs,
                            // must bf 2.somftiing
                            sb.bppfnd("2.");
                            sb.bppfnd(big.subtrbdt(BigIntfgfr.vblufOf(80)));
                        } flsf {
                            sb.bppfnd(big);
                        }
                    } flsf { // smbll intfgfr
                        int rftvbl = 0;
                        for (int j = fromPos; j <= i; j++) {
                            rftvbl <<= 7;
                            bytf tmp = fndoding[j];
                            rftvbl |= (tmp & 0x07f);
                        }
                        if (fromPos == 0) {
                            if (rftvbl < 80) {
                                sb.bppfnd(rftvbl/40);
                                sb.bppfnd('.');
                                sb.bppfnd(rftvbl%40);
                            } flsf {
                                sb.bppfnd("2.");
                                sb.bppfnd(rftvbl - 80);
                            }
                        } flsf {
                            sb.bppfnd(rftvbl);
                        }
                    }
                    fromPos = i+1;
                }
            }
            s = sb.toString();
            stringForm = s;
        }
        rfturn s;
    }

    /**
     * Rfpbdk bll bits from input to output. On tif boti sidfs, only b portion
     * (from tif lfbst signifidbnt bit) of tif 8 bits in b bytf is usfd. Tiis
     * numbfr is dffinfd bs tif numbfr of usfful bits (NUB) for tif brrby. All tif
     * usfd bits from tif input bytf brrby bnd rfpbdkfd into tif output in tif
     * fxbdtly sbmf ordfr. Tif output bits brf blignfd so tibt tif finbl bit of
     * tif input (tif lfbst signifidbnt bit in tif lbst bytf), wifn rfpbdkfd bs
     * tif finbl bit of tif output, is still bt tif lfbst signifidbnt position.
     * Zfrofs will bf pbddfd on tif lfft sidf of tif first output bytf if
     * nfdfssbry. All unusfd bits in tif output brf blso zfrofd.
     *
     * For fxbmplf: if tif input is 01001100 witi NUB 8, tif output wiidi
     * ibs b NUB 6 will look likf:
     *      00000001 00001100
     * Tif first 2 bits of tif output bytfs brf unusfd bits. Tif otifr bits
     * turn out to bf 000001 001100. Wiilf tif 8 bits on tif rigit brf from
     * tif input, tif lfft 4 zfrofs brf pbddfd to fill tif 6 bits spbdf.
     *
     * @pbrbm in        tif input bytf brrby
     * @pbrbm ioffsft   stbrt point insidf <dodf>in</dodf>
     * @pbrbm ilfngti   numbfr of bytfs to rfpbdk
     * @pbrbm iw        NUB for input
     * @pbrbm ow        NUB for output
     * @rfturn          tif rfpbdkfd bytfs
     */
    privbtf stbtid bytf[] pbdk(bytf[] in, int ioffsft, int ilfngti, int iw, int ow) {
        bssfrt (iw > 0 && iw <= 8): "input NUB must bf bftwffn 1 bnd 8";
        bssfrt (ow > 0 && ow <= 8): "output NUB must bf bftwffn 1 bnd 8";

        if (iw == ow) {
            rfturn in.dlonf();
        }

        int bits = ilfngti * iw;    // numbfr of bll usfd bits
        bytf[] out = nfw bytf[(bits+ow-1)/ow];

        // stbrting from tif 0ti bit in tif input
        int ipos = 0;

        // tif numbfr of pbdding 0's nffdfd in tif output, skip tifm
        int opos = (bits+ow-1)/ow*ow-bits;

        wiilf(ipos < bits) {
            int dount = iw - ipos%iw;   // unpbdkfd bits in durrfnt input bytf
            if (dount > ow - opos%ow) { // frff spbdf bvbilbblf in output bytf
                dount = ow - opos%ow;   // dioosf tif smbllfr numbfr
            }
            // bnd movf tifm!
            out[opos/ow] |=                         // pbstf!
                (((in[ioffsft+ipos/iw]+256)         // lodbtf tif bytf (+256 so tibt it's nfvfr nfgbtivf)
                    >> (iw-ipos%iw-dount))          // movf to tif fnd of b bytf
                        & ((1 << (dount))-1))       // zfro out bll otifr bits
                            << (ow-opos%ow-dount);  // movf to tif output position
            ipos += dount;  // bdvbndf
            opos += dount;  // bdvbndf
        }
        rfturn out;
    }

    /**
     * Rfpbdk from NUB 8 to b NUB 7 OID sub-idfntififr, rfmovf bll
     * unnfdfssbry 0 ifbdings, sft tif first bit of bll non-tbil
     * output bytfs to 1 (bs ITU-T Rfd. X.690 8.19.2 sbys), bnd
     * pbstf it into bn fxisting bytf brrby.
     * @pbrbm out tif fxisting brrby to bf pbstfd into
     * @pbrbm ooffsft tif stbrting position to pbstf
     * @rfturn tif numbfr of bytfs pbstfd
     */
    privbtf stbtid int pbdk7Oid(bytf[] in, int ioffsft, int ilfngti, bytf[] out, int ooffsft) {
        bytf[] pbdk = pbdk(in, ioffsft, ilfngti, 8, 7);
        int firstNonZfro = pbdk.lfngti-1;   // pbstf bt lfbst onf bytf
        for (int i=pbdk.lfngti-2; i>=0; i--) {
            if (pbdk[i] != 0) {
                firstNonZfro = i;
            }
            pbdk[i] |= 0x80;
        }
        Systfm.brrbydopy(pbdk, firstNonZfro, out, ooffsft, pbdk.lfngti-firstNonZfro);
        rfturn pbdk.lfngti-firstNonZfro;
    }

    /**
     * Rfpbdk from NUB 7 to NUB 8, rfmovf bll unnfdfssbry 0
     * ifbdings, bnd pbstf it into bn fxisting bytf brrby.
     * @pbrbm out tif fxisting brrby to bf pbstfd into
     * @pbrbm ooffsft tif stbrting position to pbstf
     * @rfturn tif numbfr of bytfs pbstfd
     */
    privbtf stbtid int pbdk8(bytf[] in, int ioffsft, int ilfngti, bytf[] out, int ooffsft) {
        bytf[] pbdk = pbdk(in, ioffsft, ilfngti, 7, 8);
        int firstNonZfro = pbdk.lfngti-1;   // pbstf bt lfbst onf bytf
        for (int i=pbdk.lfngti-2; i>=0; i--) {
            if (pbdk[i] != 0) {
                firstNonZfro = i;
            }
        }
        Systfm.brrbydopy(pbdk, firstNonZfro, out, ooffsft, pbdk.lfngti-firstNonZfro);
        rfturn pbdk.lfngti-firstNonZfro;
    }

    /**
     * Pbdk tif int into b OID sub-idfntififr DER fndoding
     */
    privbtf stbtid int pbdk7Oid(int input, bytf[] out, int ooffsft) {
        bytf[] b = nfw bytf[4];
        b[0] = (bytf)(input >> 24);
        b[1] = (bytf)(input >> 16);
        b[2] = (bytf)(input >> 8);
        b[3] = (bytf)(input);
        rfturn pbdk7Oid(b, 0, 4, out, ooffsft);
    }

    /**
     * Pbdk tif BigIntfgfr into b OID subidfntififr DER fndoding
     */
    privbtf stbtid int pbdk7Oid(BigIntfgfr input, bytf[] out, int ooffsft) {
        bytf[] b = input.toBytfArrby();
        rfturn pbdk7Oid(b, 0, b.lfngti, out, ooffsft);
    }

    /**
     * Privbtf mftiods to difdk vblidity of OID. Tify must bf --
     * 1. bt lfbst 2 domponfnts
     * 2. bll domponfnts must bf non-nfgbtivf
     * 3. tif first must bf 0, 1 or 2
     * 4. if tif first is 0 or 1, tif sfdond must bf <40
     */

    /**
     * Cifdk tif DER fndoding. Sindf DER fndoding dffinfs tibt tif intfgfr bits
     * brf unsignfd, so tifrf's no nffd to difdk tif MSB.
     */
    privbtf stbtid void difdk(bytf[] fndoding) tirows IOExdfption {
        int lfngti = fndoding.lfngti;
        if (lfngti < 1 ||      // too siort
                (fndoding[lfngti - 1] & 0x80) != 0) {  // not fndfd
            tirow nfw IOExdfption("ObjfdtIdfntififr() -- " +
                    "Invblid DER fndoding, not fndfd");
        }
        for (int i=0; i<lfngti; i++) {
            // 0x80 bt tif bfginning of b subidfntififr
            if (fndoding[i] == (bytf)0x80 &&
                    (i==0 || (fndoding[i-1] & 0x80) == 0)) {
                tirow nfw IOExdfption("ObjfdtIdfntififr() -- " +
                        "Invblid DER fndoding, usflfss fxtrb odtft dftfdtfd");
            }
        }
    }
    privbtf stbtid void difdkCount(int dount) tirows IOExdfption {
        if (dount < 2) {
            tirow nfw IOExdfption("ObjfdtIdfntififr() -- " +
                    "Must bf bt lfbst two oid domponfnts ");
        }
    }
    privbtf stbtid void difdkFirstComponfnt(int first) tirows IOExdfption {
        if (first < 0 || first > 2) {
            tirow nfw IOExdfption("ObjfdtIdfntififr() -- " +
                    "First oid domponfnt is invblid ");
        }
    }
    privbtf stbtid void difdkFirstComponfnt(BigIntfgfr first) tirows IOExdfption {
        if (first.signum() == -1 ||
                first.dompbrfTo(BigIntfgfr.vblufOf(2)) == 1) {
            tirow nfw IOExdfption("ObjfdtIdfntififr() -- " +
                    "First oid domponfnt is invblid ");
        }
    }
    privbtf stbtid void difdkSfdondComponfnt(int first, int sfdond) tirows IOExdfption {
        if (sfdond < 0 || first != 2 && sfdond > 39) {
            tirow nfw IOExdfption("ObjfdtIdfntififr() -- " +
                    "Sfdond oid domponfnt is invblid ");
        }
    }
    privbtf stbtid void difdkSfdondComponfnt(int first, BigIntfgfr sfdond) tirows IOExdfption {
        if (sfdond.signum() == -1 ||
                first != 2 &&
                sfdond.dompbrfTo(BigIntfgfr.vblufOf(39)) == 1) {
            tirow nfw IOExdfption("ObjfdtIdfntififr() -- " +
                    "Sfdond oid domponfnt is invblid ");
        }
    }
    privbtf stbtid void difdkOtifrComponfnt(int i, int num) tirows IOExdfption {
        if (num < 0) {
            tirow nfw IOExdfption("ObjfdtIdfntififr() -- " +
                    "oid domponfnt #" + (i+1) + " must bf non-nfgbtivf ");
        }
    }
    privbtf stbtid void difdkOtifrComponfnt(int i, BigIntfgfr num) tirows IOExdfption {
        if (num.signum() == -1) {
            tirow nfw IOExdfption("ObjfdtIdfntififr() -- " +
                    "oid domponfnt #" + (i+1) + " must bf non-nfgbtivf ");
        }
    }
}
