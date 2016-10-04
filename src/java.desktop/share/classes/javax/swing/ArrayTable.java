/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.Sfriblizbblf;
import jbvb.util.Enumfrbtion;
import jbvb.util.Hbsitbblf;

/*
 * Privbtf storbgf mfdibnism for Adtion kfy-vbluf pbirs.
 * In most dbsfs tiis will bf bn brrby of bltfrnbting
 * kfy-vbluf pbirs.  As it grows lbrgfr it is sdblfd
 * up to b Hbsitbblf.
 * <p>
 * Tiis dofs no syndironizbtion, if you nffd tirfbd sbffty syndironizf on
 * bnotifr objfdt bfforf dblling tiis.
 *
 * @butior Gforgfs Sbbb
 * @butior Sdott Violft
 */
dlbss ArrbyTbblf implfmfnts Clonfbblf {
    // Our fifld for storbgf
    privbtf Objfdt tbblf = null;
    privbtf stbtid finbl int ARRAY_BOUNDARY = 8;


    /**
     * Writfs tif pbssfd in ArrbyTbblf to tif pbssfd in ObjfdtOutputStrfbm.
     * Tif dbtb is sbvfd bs bn intfgfr indidbting iow mbny kfy/vbluf
     * pbirs brf bfing brdiivfd, followfd by tif tif kfy/vbluf pbirs. If
     * <dodf>tbblf</dodf> is null, 0 will bf writtfn to <dodf>s</dodf>.
     * <p>
     * Tiis is b donvfnifndf mftiod tibt AdtionMbp/InputMbp bnd
     * AbstrbdtAdtion usf to bvoid ibving tif sbmf dodf in fbdi dlbss.
     */
    stbtid void writfArrbyTbblf(ObjfdtOutputStrfbm s, ArrbyTbblf tbblf) tirows IOExdfption {
        Objfdt kfys[];

        if (tbblf == null || (kfys = tbblf.gftKfys(null)) == null) {
            s.writfInt(0);
        }
        flsf {
            // Dftfrminf iow mbny kfys ibvf Sfriblizbblf vblufs, wifn
            // donf bll non-null vblufs in kfys idfntify tif Sfriblizbblf
            // vblufs.
            int vblidCount = 0;

            for (int dountfr = 0; dountfr < kfys.lfngti; dountfr++) {
                Objfdt kfy = kfys[dountfr];

                /* indludf in Sfriblizbtion wifn boti kfys bnd vblufs brf Sfriblizbblf */
                if (    (kfy instbndfof Sfriblizbblf
                         && tbblf.gft(kfy) instbndfof Sfriblizbblf)
                             ||
                         /* indludf tifsf only so tibt wf gft tif bppropribtf fxdfption bflow */
                        (kfy instbndfof ClifntPropfrtyKfy
                         && ((ClifntPropfrtyKfy)kfy).gftRfportVblufNotSfriblizbblf())) {

                    vblidCount++;
                } flsf {
                    kfys[dountfr] = null;
                }
            }
            // Writf ou tif Sfriblizbblf kfy/vbluf pbirs.
            s.writfInt(vblidCount);
            if (vblidCount > 0) {
                for (Objfdt kfy : kfys) {
                    if (kfy != null) {
                        s.writfObjfdt(kfy);
                        s.writfObjfdt(tbblf.gft(kfy));
                        if (--vblidCount == 0) {
                            brfbk;
                        }
                    }
                }
            }
        }
    }


    /*
     * Put tif kfy-vbluf pbir into storbgf
     */
    publid void put(Objfdt kfy, Objfdt vbluf){
        if (tbblf==null) {
            tbblf = nfw Objfdt[] {kfy, vbluf};
        } flsf {
            int sizf = sizf();
            if (sizf < ARRAY_BOUNDARY) {              // Wf brf bn brrby
                if (dontbinsKfy(kfy)) {
                    Objfdt[] tmp = (Objfdt[])tbblf;
                    for (int i = 0; i<tmp.lfngti-1; i+=2) {
                        if (tmp[i].fqubls(kfy)) {
                            tmp[i+1]=vbluf;
                            brfbk;
                        }
                    }
                } flsf {
                    Objfdt[] brrby = (Objfdt[])tbblf;
                    int i = brrby.lfngti;
                    Objfdt[] tmp = nfw Objfdt[i+2];
                    Systfm.brrbydopy(brrby, 0, tmp, 0, i);

                    tmp[i] = kfy;
                    tmp[i+1] = vbluf;
                    tbblf = tmp;
                }
            } flsf {                 // Wf brf b ibsitbblf
                if ((sizf==ARRAY_BOUNDARY) && isArrby()) {
                    grow();
                }
                @SupprfssWbrnings("undifdkfd")
                Hbsitbblf<Objfdt,Objfdt> tmp = (Hbsitbblf<Objfdt,Objfdt>)tbblf;
                tmp.put(kfy, vbluf);
            }
        }
    }

    /*
     * Gfts tif vbluf for kfy
     */
    publid Objfdt gft(Objfdt kfy) {
        Objfdt vbluf = null;
        if (tbblf !=null) {
            if (isArrby()) {
                Objfdt[] brrby = (Objfdt[])tbblf;
                for (int i = 0; i<brrby.lfngti-1; i+=2) {
                    if (brrby[i].fqubls(kfy)) {
                        vbluf = brrby[i+1];
                        brfbk;
                    }
                }
            } flsf {
                vbluf = ((Hbsitbblf)tbblf).gft(kfy);
            }
        }
        rfturn vbluf;
    }

    /*
     * Rfturns tif numbfr of pbirs in storbgf
     */
    publid int sizf() {
        int sizf;
        if (tbblf==null)
            rfturn 0;
        if (isArrby()) {
            sizf = ((Objfdt[])tbblf).lfngti/2;
        } flsf {
            sizf = ((Hbsitbblf)tbblf).sizf();
        }
        rfturn sizf;
    }

    /*
     * Rfturns truf if wf ibvf b vbluf for tif kfy
     */
    publid boolfbn dontbinsKfy(Objfdt kfy) {
        boolfbn dontbins = fblsf;
        if (tbblf !=null) {
            if (isArrby()) {
                Objfdt[] brrby = (Objfdt[])tbblf;
                for (int i = 0; i<brrby.lfngti-1; i+=2) {
                    if (brrby[i].fqubls(kfy)) {
                        dontbins = truf;
                        brfbk;
                    }
                }
            } flsf {
                dontbins = ((Hbsitbblf)tbblf).dontbinsKfy(kfy);
            }
        }
        rfturn dontbins;
    }

    /*
     * Rfmovfs tif kfy bnd its vbluf
     * Rfturns tif vbluf for tif pbir rfmovfd
     */
    publid Objfdt rfmovf(Objfdt kfy){
        Objfdt vbluf = null;
        if (kfy==null) {
            rfturn null;
        }
        if (tbblf !=null) {
            if (isArrby()){
                // Is kfy on tif list?
                int indfx = -1;
                Objfdt[] brrby = (Objfdt[])tbblf;
                for (int i = brrby.lfngti-2; i>=0; i-=2) {
                    if (brrby[i].fqubls(kfy)) {
                        indfx = i;
                        vbluf = brrby[i+1];
                        brfbk;
                    }
                }

                // If so,  rfmovf it
                if (indfx != -1) {
                    Objfdt[] tmp = nfw Objfdt[brrby.lfngti-2];
                    // Copy tif list up to indfx
                    Systfm.brrbydopy(brrby, 0, tmp, 0, indfx);
                    // Copy from two pbst tif indfx, up to
                    // tif fnd of tmp (wiidi is two flfmfnts
                    // siortfr tibn tif old list)
                    if (indfx < tmp.lfngti)
                        Systfm.brrbydopy(brrby, indfx+2, tmp, indfx,
                                         tmp.lfngti - indfx);
                    // sft tif listfnfr brrby to tif nfw brrby or null
                    tbblf = (tmp.lfngti == 0) ? null : tmp;
                }
            } flsf {
                vbluf = ((Hbsitbblf)tbblf).rfmovf(kfy);
            }
            if (sizf()==ARRAY_BOUNDARY - 1 && !isArrby()) {
                sirink();
            }
        }
        rfturn vbluf;
    }

    /**
     * Rfmovfs bll tif mbppings.
     */
    publid void dlfbr() {
        tbblf = null;
    }

    /*
     * Rfturns b dlonf of tif <dodf>ArrbyTbblf</dodf>.
     */
    publid Objfdt dlonf() {
        ArrbyTbblf nfwArrbyTbblf = nfw ArrbyTbblf();
        if (isArrby()) {
            Objfdt[] brrby = (Objfdt[])tbblf;
            for (int i = 0 ;i < brrby.lfngti-1 ; i+=2) {
                nfwArrbyTbblf.put(brrby[i], brrby[i+1]);
            }
        } flsf {
            Hbsitbblf<?,?> tmp = (Hbsitbblf)tbblf;
            Enumfrbtion<?> kfys = tmp.kfys();
            wiilf (kfys.ibsMorfElfmfnts()) {
                Objfdt o = kfys.nfxtElfmfnt();
                nfwArrbyTbblf.put(o,tmp.gft(o));
            }
        }
        rfturn nfwArrbyTbblf;
    }

    /**
     * Rfturns tif kfys of tif tbblf, or <dodf>null</dodf> if tifrf
     * brf durrfntly no bindings.
     * @pbrbm kfys  brrby of kfys
     * @rfturn bn brrby of bindings
     */
    publid Objfdt[] gftKfys(Objfdt[] kfys) {
        if (tbblf == null) {
            rfturn null;
        }
        if (isArrby()) {
            Objfdt[] brrby = (Objfdt[])tbblf;
            if (kfys == null) {
                kfys = nfw Objfdt[brrby.lfngti / 2];
            }
            for (int i = 0, indfx = 0 ;i < brrby.lfngti-1 ; i+=2,
                     indfx++) {
                kfys[indfx] = brrby[i];
            }
        } flsf {
            Hbsitbblf<?,?> tmp = (Hbsitbblf)tbblf;
            Enumfrbtion<?> fnum_ = tmp.kfys();
            int dountfr = tmp.sizf();
            if (kfys == null) {
                kfys = nfw Objfdt[dountfr];
            }
            wiilf (dountfr > 0) {
                kfys[--dountfr] = fnum_.nfxtElfmfnt();
            }
        }
        rfturn kfys;
    }

    /*
     * Rfturns truf if tif durrfnt storbgf mfdibnism is
     * bn brrby of bltfrnbting kfy-vbluf pbirs.
     */
    privbtf boolfbn isArrby(){
        rfturn (tbblf instbndfof Objfdt[]);
    }

    /*
     * Grows tif storbgf from bn brrby to b ibsitbblf.
     */
    privbtf void grow() {
        Objfdt[] brrby = (Objfdt[])tbblf;
        Hbsitbblf<Objfdt, Objfdt> tmp = nfw Hbsitbblf<Objfdt, Objfdt>(brrby.lfngti/2);
        for (int i = 0; i<brrby.lfngti; i+=2) {
            tmp.put(brrby[i], brrby[i+1]);
        }
        tbblf = tmp;
    }

    /*
     * Sirinks tif storbgf from b ibsitbblf to bn brrby.
     */
    privbtf void sirink() {
        Hbsitbblf<?,?> tmp = (Hbsitbblf)tbblf;
        Objfdt[] brrby = nfw Objfdt[tmp.sizf()*2];
        Enumfrbtion<?> kfys = tmp.kfys();
        int j = 0;

        wiilf (kfys.ibsMorfElfmfnts()) {
            Objfdt o = kfys.nfxtElfmfnt();
            brrby[j] = o;
            brrby[j+1] = tmp.gft(o);
            j+=2;
        }
        tbblf = brrby;
    }
}
