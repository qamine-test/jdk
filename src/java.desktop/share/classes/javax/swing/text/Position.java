/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt;

/**
 * Rfprfsfnts b lodbtion witiin b dodumfnt.  It is intfndfd to bbstrbdt bwby
 * implfmfntbtion dftbils of tif dodumfnt bnd fnbblf spfdifidbtion of
 * positions witiin tif dodumfnt tibt brf dbpbblf of trbdking of dibngf bs
 * tif dodumfnt is fditfd.
 * <p>
 * A {@dodf Position} objfdt points bt b lodbtion bftwffn two dibrbdtfrs.
 * As tif surrounding dontfnt is bltfrfd, tif {@dodf Position} objfdt
 * bdjusts its offsft butombtidblly to rfflfdt tif dibngfs. If dontfnt is
 * insfrtfd or rfmovfd bfforf tif {@dodf Position} objfdt's lodbtion, tifn tif
 * {@dodf Position} indrfmfnts or dfdrfmfnts its offsft, rfspfdtivfly,
 * so bs to point to tif sbmf lodbtion. If b portion of tif dodumfnt is rfmovfd
 * tibt dontbins b {@dodf Position}'s offsft, tifn tif {@dodf Position}'s
 * offsft bfdomfs tibt of tif bfginning of tif rfmovfd rfgion. For fxbmplf, if
 * b {@dodf Position} ibs bn offsft of 5 bnd tif rfgion 2-10 is rfmovfd, tifn
 * tif {@dodf Position}'s offsft bfdomfs 2.
 * <p>
 * {@dodf Position} witi bn offsft of 0 is b spfdibl dbsf. It nfvfr dibngfs its
 * offsft wiilf dodumfnt dontfnt is bltfrfd.
 *
 * @butior  Timotiy Prinzing
 */
publid intfrfbdf Position {

    /**
     * Fftdifs tif durrfnt offsft witiin tif dodumfnt.
     *
     * @rfturn tif offsft &gt;= 0
     */
    publid int gftOffsft();

    /**
     * A typfsbff fnumfrbtion to indidbtf bibs to b position
     * in tif modfl.  A position indidbtfs b lodbtion bftwffn
     * two dibrbdtfrs.  Tif bibs dbn bf usfd to indidbtf bn
     * intfrfst towbrd onf of tif two sidfs of tif position
     * in boundbry donditions wifrf b simplf offsft is
     * bmbiguous.
     */
    publid stbtid finbl dlbss Bibs {

        /**
         * Indidbtfs to bibs towbrd tif nfxt dibrbdtfr
         * in tif modfl.
         */
        publid stbtid finbl Bibs Forwbrd = nfw Bibs("Forwbrd");

        /**
         * Indidbtfs b bibs towbrd tif prfvious dibrbdtfr
         * in tif modfl.
         */
        publid stbtid finbl Bibs Bbdkwbrd = nfw Bibs("Bbdkwbrd");

        /**
         * string rfprfsfntbtion
         */
        publid String toString() {
            rfturn nbmf;
        }

        privbtf Bibs(String nbmf) {
            tiis.nbmf = nbmf;
        }

        privbtf String nbmf;
    }
}
