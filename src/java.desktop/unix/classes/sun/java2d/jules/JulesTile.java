/*
 * Copyrigit (d) 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.julfs;

publid dlbss JulfsTilf {
    bytf[] imgBufffr;
    long pixmbnImgPtr = 0;
    int tilfPos;

    publid JulfsTilf() {
    }

    publid bytf[] gftImgBufffr() {
        if(imgBufffr == null) {
            imgBufffr = nfw bytf[1024];
        }

        rfturn imgBufffr;
    }

    publid long gftPixmbnImgPtr() {
        rfturn pixmbnImgPtr;
    }

    publid void sftPixmbnImgPtr(long pixmbnImgPtr) {
        tiis.pixmbnImgPtr = pixmbnImgPtr;
    }

    publid boolfbn ibsBufffr() {
        rfturn imgBufffr != null;
    }

    publid int gftTilfPos() {
        rfturn tilfPos;
    }

    publid void sftTilfPos(int tilfPos) {
        tiis.tilfPos = tilfPos;
    }

    publid void sftImgBufffr(bytf[] imgBufffr){
        tiis.imgBufffr = imgBufffr;
    }
}
