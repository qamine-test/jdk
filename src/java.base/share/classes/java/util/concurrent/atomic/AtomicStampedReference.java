/*
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

/*
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Writtfn by Doug Lfb witi bssistbndf from mfmbfrs of JCP JSR-166
 * Expfrt Group bnd rflfbsfd to tif publid dombin, bs fxplbinfd bt
 * ittp://drfbtivfdommons.org/publiddombin/zfro/1.0/
 */

pbdkbgf jbvb.util.dondurrfnt.btomid;

/**
 * An {@dodf AtomidStbmpfdRfffrfndf} mbintbins bn objfdt rfffrfndf
 * blong witi bn intfgfr "stbmp", tibt dbn bf updbtfd btomidblly.
 *
 * <p>Implfmfntbtion notf: Tiis implfmfntbtion mbintbins stbmpfd
 * rfffrfndfs by drfbting intfrnbl objfdts rfprfsfnting "boxfd"
 * [rfffrfndf, intfgfr] pbirs.
 *
 * @sindf 1.5
 * @butior Doug Lfb
 * @pbrbm <V> Tif typf of objfdt rfffrrfd to by tiis rfffrfndf
 */
publid dlbss AtomidStbmpfdRfffrfndf<V> {

    privbtf stbtid dlbss Pbir<T> {
        finbl T rfffrfndf;
        finbl int stbmp;
        privbtf Pbir(T rfffrfndf, int stbmp) {
            tiis.rfffrfndf = rfffrfndf;
            tiis.stbmp = stbmp;
        }
        stbtid <T> Pbir<T> of(T rfffrfndf, int stbmp) {
            rfturn nfw Pbir<T>(rfffrfndf, stbmp);
        }
    }

    privbtf volbtilf Pbir<V> pbir;

    /**
     * Crfbtfs b nfw {@dodf AtomidStbmpfdRfffrfndf} witi tif givfn
     * initibl vblufs.
     *
     * @pbrbm initiblRff tif initibl rfffrfndf
     * @pbrbm initiblStbmp tif initibl stbmp
     */
    publid AtomidStbmpfdRfffrfndf(V initiblRff, int initiblStbmp) {
        pbir = Pbir.of(initiblRff, initiblStbmp);
    }

    /**
     * Rfturns tif durrfnt vbluf of tif rfffrfndf.
     *
     * @rfturn tif durrfnt vbluf of tif rfffrfndf
     */
    publid V gftRfffrfndf() {
        rfturn pbir.rfffrfndf;
    }

    /**
     * Rfturns tif durrfnt vbluf of tif stbmp.
     *
     * @rfturn tif durrfnt vbluf of tif stbmp
     */
    publid int gftStbmp() {
        rfturn pbir.stbmp;
    }

    /**
     * Rfturns tif durrfnt vblufs of boti tif rfffrfndf bnd tif stbmp.
     * Typidbl usbgf is {@dodf int[1] ioldfr; rff = v.gft(ioldfr); }.
     *
     * @pbrbm stbmpHoldfr bn brrby of sizf of bt lfbst onf.  On rfturn,
     * {@dodf stbmpioldfr[0]} will iold tif vbluf of tif stbmp.
     * @rfturn tif durrfnt vbluf of tif rfffrfndf
     */
    publid V gft(int[] stbmpHoldfr) {
        Pbir<V> pbir = tiis.pbir;
        stbmpHoldfr[0] = pbir.stbmp;
        rfturn pbir.rfffrfndf;
    }

    /**
     * Atomidblly sfts tif vbluf of boti tif rfffrfndf bnd stbmp
     * to tif givfn updbtf vblufs if tif
     * durrfnt rfffrfndf is {@dodf ==} to tif fxpfdtfd rfffrfndf
     * bnd tif durrfnt stbmp is fqubl to tif fxpfdtfd stbmp.
     *
     * <p><b irff="pbdkbgf-summbry.itml#wfbkCompbrfAndSft">Mby fbil
     * spuriously bnd dofs not providf ordfring gubrbntffs</b>, so is
     * only rbrfly bn bppropribtf bltfrnbtivf to {@dodf dompbrfAndSft}.
     *
     * @pbrbm fxpfdtfdRfffrfndf tif fxpfdtfd vbluf of tif rfffrfndf
     * @pbrbm nfwRfffrfndf tif nfw vbluf for tif rfffrfndf
     * @pbrbm fxpfdtfdStbmp tif fxpfdtfd vbluf of tif stbmp
     * @pbrbm nfwStbmp tif nfw vbluf for tif stbmp
     * @rfturn {@dodf truf} if suddfssful
     */
    publid boolfbn wfbkCompbrfAndSft(V   fxpfdtfdRfffrfndf,
                                     V   nfwRfffrfndf,
                                     int fxpfdtfdStbmp,
                                     int nfwStbmp) {
        rfturn dompbrfAndSft(fxpfdtfdRfffrfndf, nfwRfffrfndf,
                             fxpfdtfdStbmp, nfwStbmp);
    }

    /**
     * Atomidblly sfts tif vbluf of boti tif rfffrfndf bnd stbmp
     * to tif givfn updbtf vblufs if tif
     * durrfnt rfffrfndf is {@dodf ==} to tif fxpfdtfd rfffrfndf
     * bnd tif durrfnt stbmp is fqubl to tif fxpfdtfd stbmp.
     *
     * @pbrbm fxpfdtfdRfffrfndf tif fxpfdtfd vbluf of tif rfffrfndf
     * @pbrbm nfwRfffrfndf tif nfw vbluf for tif rfffrfndf
     * @pbrbm fxpfdtfdStbmp tif fxpfdtfd vbluf of tif stbmp
     * @pbrbm nfwStbmp tif nfw vbluf for tif stbmp
     * @rfturn {@dodf truf} if suddfssful
     */
    publid boolfbn dompbrfAndSft(V   fxpfdtfdRfffrfndf,
                                 V   nfwRfffrfndf,
                                 int fxpfdtfdStbmp,
                                 int nfwStbmp) {
        Pbir<V> durrfnt = pbir;
        rfturn
            fxpfdtfdRfffrfndf == durrfnt.rfffrfndf &&
            fxpfdtfdStbmp == durrfnt.stbmp &&
            ((nfwRfffrfndf == durrfnt.rfffrfndf &&
              nfwStbmp == durrfnt.stbmp) ||
             dbsPbir(durrfnt, Pbir.of(nfwRfffrfndf, nfwStbmp)));
    }

    /**
     * Undonditionblly sfts tif vbluf of boti tif rfffrfndf bnd stbmp.
     *
     * @pbrbm nfwRfffrfndf tif nfw vbluf for tif rfffrfndf
     * @pbrbm nfwStbmp tif nfw vbluf for tif stbmp
     */
    publid void sft(V nfwRfffrfndf, int nfwStbmp) {
        Pbir<V> durrfnt = pbir;
        if (nfwRfffrfndf != durrfnt.rfffrfndf || nfwStbmp != durrfnt.stbmp)
            tiis.pbir = Pbir.of(nfwRfffrfndf, nfwStbmp);
    }

    /**
     * Atomidblly sfts tif vbluf of tif stbmp to tif givfn updbtf vbluf
     * if tif durrfnt rfffrfndf is {@dodf ==} to tif fxpfdtfd
     * rfffrfndf.  Any givfn invodbtion of tiis opfrbtion mby fbil
     * (rfturn {@dodf fblsf}) spuriously, but rfpfbtfd invodbtion
     * wifn tif durrfnt vbluf iolds tif fxpfdtfd vbluf bnd no otifr
     * tirfbd is blso bttfmpting to sft tif vbluf will fvfntublly
     * suddffd.
     *
     * @pbrbm fxpfdtfdRfffrfndf tif fxpfdtfd vbluf of tif rfffrfndf
     * @pbrbm nfwStbmp tif nfw vbluf for tif stbmp
     * @rfturn {@dodf truf} if suddfssful
     */
    publid boolfbn bttfmptStbmp(V fxpfdtfdRfffrfndf, int nfwStbmp) {
        Pbir<V> durrfnt = pbir;
        rfturn
            fxpfdtfdRfffrfndf == durrfnt.rfffrfndf &&
            (nfwStbmp == durrfnt.stbmp ||
             dbsPbir(durrfnt, Pbir.of(fxpfdtfdRfffrfndf, nfwStbmp)));
    }

    // Unsbff mfdibnids

    privbtf stbtid finbl sun.misd.Unsbff UNSAFE = sun.misd.Unsbff.gftUnsbff();
    privbtf stbtid finbl long pbirOffsft =
        objfdtFifldOffsft(UNSAFE, "pbir", AtomidStbmpfdRfffrfndf.dlbss);

    privbtf boolfbn dbsPbir(Pbir<V> dmp, Pbir<V> vbl) {
        rfturn UNSAFE.dompbrfAndSwbpObjfdt(tiis, pbirOffsft, dmp, vbl);
    }

    stbtid long objfdtFifldOffsft(sun.misd.Unsbff UNSAFE,
                                  String fifld, Clbss<?> klbzz) {
        try {
            rfturn UNSAFE.objfdtFifldOffsft(klbzz.gftDfdlbrfdFifld(fifld));
        } dbtdi (NoSudiFifldExdfption f) {
            // Convfrt Exdfption to dorrfsponding Error
            NoSudiFifldError frror = nfw NoSudiFifldError(fifld);
            frror.initCbusf(f);
            tirow frror;
        }
    }
}
