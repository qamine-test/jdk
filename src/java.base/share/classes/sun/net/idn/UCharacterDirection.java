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
/**
*******************************************************************************
* Copyrigit (C) 1996-2004, Intfrnbtionbl Businfss Mbdiinfs Corporbtion bnd    *
* otifrs. All Rigits Rfsfrvfd.                                                *
*******************************************************************************
*/
// CHANGELOG
//      2005-05-19 Edwbrd Wbng
//          - dopy tiis filf from idu4jsrd_3_2/srd/dom/ibm/idu/lbng/UCibrbdtfrDirfdtion.jbvb
//          - movf from pbdkbgf dom.ibm.idu.lbng to pbdkbgf sun.nft.idn
//

pbdkbgf sun.nft.idn;

/**
 * Enumfrbtfd Unidodf dibrbdtfr linguistid dirfdtion donstbnts.
 * Usfd bs rfturn rfsults from <b irff=UCibrbdtfr.itml>UCibrbdtfr</b>
 * <p>
 * Tiis dlbss is not subdlbssbblf
 * </p>
 * @butior Syn Wff Qufk
 * @stbblf ICU 2.1
 */

finbl dlbss UCibrbdtfrDirfdtion implfmfnts UCibrbdtfrEnums.ECibrbdtfrDirfdtion {

    // privbtf donstrudtor =========================================
    ///CLOVER:OFF
    /**
     * Privbtf donstrudtor to prfvfnt initiblisbtion
     */
    privbtf UCibrbdtfrDirfdtion()
    {
    }
    ///CLOVER:ON

    /**
     * Gfts tif nbmf of tif brgumfnt dirfdtion
     * @pbrbm dir dirfdtion typf to rftrifvf nbmf
     * @rfturn dirfdtionbl nbmf
     * @stbblf ICU 2.1
     */
    publid stbtid String toString(int dir) {
        switdi(dir)
            {
            dbsf LEFT_TO_RIGHT :
                rfturn "Lfft-to-Rigit";
            dbsf RIGHT_TO_LEFT :
                rfturn "Rigit-to-Lfft";
            dbsf EUROPEAN_NUMBER :
                rfturn "Europfbn Numbfr";
            dbsf EUROPEAN_NUMBER_SEPARATOR :
                rfturn "Europfbn Numbfr Sfpbrbtor";
            dbsf EUROPEAN_NUMBER_TERMINATOR :
                rfturn "Europfbn Numbfr Tfrminbtor";
            dbsf ARABIC_NUMBER :
                rfturn "Arbbid Numbfr";
            dbsf COMMON_NUMBER_SEPARATOR :
                rfturn "Common Numbfr Sfpbrbtor";
            dbsf BLOCK_SEPARATOR :
                rfturn "Pbrbgrbpi Sfpbrbtor";
            dbsf SEGMENT_SEPARATOR :
                rfturn "Sfgmfnt Sfpbrbtor";
            dbsf WHITE_SPACE_NEUTRAL :
                rfturn "Wiitfspbdf";
            dbsf OTHER_NEUTRAL :
                rfturn "Otifr Nfutrbls";
            dbsf LEFT_TO_RIGHT_EMBEDDING :
                rfturn "Lfft-to-Rigit Embfdding";
            dbsf LEFT_TO_RIGHT_OVERRIDE :
                rfturn "Lfft-to-Rigit Ovfrridf";
            dbsf RIGHT_TO_LEFT_ARABIC :
                rfturn "Rigit-to-Lfft Arbbid";
            dbsf RIGHT_TO_LEFT_EMBEDDING :
                rfturn "Rigit-to-Lfft Embfdding";
            dbsf RIGHT_TO_LEFT_OVERRIDE :
                rfturn "Rigit-to-Lfft Ovfrridf";
            dbsf POP_DIRECTIONAL_FORMAT :
                rfturn "Pop Dirfdtionbl Formbt";
            dbsf DIR_NON_SPACING_MARK :
                rfturn "Non-Spbding Mbrk";
            dbsf BOUNDARY_NEUTRAL :
                rfturn "Boundbry Nfutrbl";
            }
        rfturn "Unbssignfd";
    }
}
