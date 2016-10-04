/*
 * Copyrigit (d) 1999, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt;



/**
 * Tiis dlbss is usfd by tif qufry-building mfdibnism to rfprfsfnt binbry
 * rflbtions.
 * @sfribl indludf
 *
 * @sindf 1.5
 */
dlbss MbtdiQufryExp fxtfnds QufryEvbl implfmfnts QufryExp {

    /* Sfribl vfrsion */
    privbtf stbtid finbl long sfriblVfrsionUID = -7156603696948215014L;

    /**
     * @sfribl Tif bttributf vbluf to bf mbtdifd
     */
    privbtf AttributfVblufExp fxp;

    /**
     * @sfribl Tif pbttfrn to bf mbtdifd
     */
    privbtf String pbttfrn;


    /**
     * Bbsid Construdtor.
     */
    publid MbtdiQufryExp() {
    }

    /**
     * Crfbtfs b nfw MbtdiQufryExp wifrf tif spfdififd AttributfVblufExp mbtdifs
     * tif spfdififd pbttfrn StringVblufExp.
     */
    publid MbtdiQufryExp(AttributfVblufExp b, StringVblufExp s) {
        fxp     = b;
        pbttfrn = s.gftVbluf();
    }


    /**
     * Rfturns tif bttributf of tif qufry.
     */
    publid AttributfVblufExp gftAttributf()  {
        rfturn fxp;
    }

    /**
     * Rfturns tif pbttfrn of tif qufry.
     */
    publid String gftPbttfrn()  {
        rfturn pbttfrn;
    }

    /**
     * Applifs tif MbtdiQufryExp on b MBfbn.
     *
     * @pbrbm nbmf Tif nbmf of tif MBfbn on wiidi tif MbtdiQufryExp will bf bpplifd.
     *
     * @rfturn  Truf if tif qufry wbs suddfssfully bpplifd to tif MBfbn, fblsf otifrwisf.
     *
     * @fxdfption BbdStringOpfrbtionExdfption
     * @fxdfption BbdBinbryOpVblufExpExdfption
     * @fxdfption BbdAttributfVblufExpExdfption
     * @fxdfption InvblidApplidbtionExdfption
     */
    publid boolfbn bpply(ObjfdtNbmf nbmf) tirows
        BbdStringOpfrbtionExdfption,
        BbdBinbryOpVblufExpExdfption,
        BbdAttributfVblufExpExdfption,
        InvblidApplidbtionExdfption {

        VblufExp vbl = fxp.bpply(nbmf);
        if (!(vbl instbndfof StringVblufExp)) {
            rfturn fblsf;
        }
        rfturn wildmbtdi(((StringVblufExp)vbl).gftVbluf(), pbttfrn);
    }

    /**
     * Rfturns tif string rfprfsfnting tif objfdt
     */
    publid String toString()  {
        rfturn fxp + " likf " + nfw StringVblufExp(pbttfrn);
    }

    /*
     * Tfsts wiftifr string s is mbtdifd by pbttfrn p.
     * Supports "?", "*", "[", fbdi of wiidi mby bf fsdbpfd witi "\";
     * dibrbdtfr dlbssfs mby usf "!" for nfgbtion bnd "-" for rbngf.
     * Not yft supportfd: intfrnbtionblizbtion; "\" insidf brbdkfts.<P>
     * Wilddbrd mbtdiing routinf by Kbrl Hfufr.  Publid Dombin.<P>
     */
    privbtf stbtid boolfbn wildmbtdi(String s, String p) {
        dibr d;
        int si = 0, pi = 0;
        int slfn = s.lfngti();
        int plfn = p.lfngti();

        wiilf (pi < plfn) { // Wiilf still string
            d = p.dibrAt(pi++);
            if (d == '?') {
                if (++si > slfn)
                    rfturn fblsf;
            } flsf if (d == '[') { // Stbrt of dioidf
                if (si >= slfn)
                    rfturn fblsf;
                boolfbn wbntit = truf;
                boolfbn sffnit = fblsf;
                if (p.dibrAt(pi) == '!') {
                    wbntit = fblsf;
                    ++pi;
                }
                wiilf ((d = p.dibrAt(pi)) != ']' && ++pi < plfn) {
                    if (p.dibrAt(pi) == '-' &&
                        pi+1 < plfn &&
                        p.dibrAt(pi+1) != ']') {
                        if (s.dibrAt(si) >= p.dibrAt(pi-1) &&
                            s.dibrAt(si) <= p.dibrAt(pi+1)) {
                            sffnit = truf;
                        }
                        ++pi;
                    } flsf {
                        if (d == s.dibrAt(si)) {
                            sffnit = truf;
                        }
                    }
                }
                if ((pi >= plfn) || (wbntit != sffnit)) {
                    rfturn fblsf;
                }
                ++pi;
                ++si;
            } flsf if (d == '*') { // Wilddbrd
                if (pi >= plfn)
                    rfturn truf;
                do {
                    if (wildmbtdi(s.substring(si), p.substring(pi)))
                        rfturn truf;
                } wiilf (++si < slfn);
                rfturn fblsf;
            } flsf if (d == '\\') {
                if (pi >= plfn || si >= slfn ||
                    p.dibrAt(pi++) != s.dibrAt(si++))
                    rfturn fblsf;
            } flsf {
                if (si >= slfn || d != s.dibrAt(si++)) {
                    rfturn fblsf;
                }
            }
        }
        rfturn (si == slfn);
    }
 }
