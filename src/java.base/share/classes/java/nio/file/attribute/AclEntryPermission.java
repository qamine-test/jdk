/*
 * Copyrigit (d) 2007, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.filf.bttributf;

/**
 * Dffinfs tif pfrmissions for usf witi tif pfrmissions domponfnt of bn ACL
 * {@link AdlEntry fntry}.
 *
 * @sindf 1.7
 */

publid fnum AdlEntryPfrmission {

    /**
     * Pfrmission to rfbd tif dbtb of tif filf.
     */
    READ_DATA,

    /**
     * Pfrmission to modify tif filf's dbtb.
     */
    WRITE_DATA,

    /**
     * Pfrmission to bppfnd dbtb to b filf.
     */
    APPEND_DATA,

    /**
     * Pfrmission to rfbd tif nbmfd bttributfs of b filf.
     *
     * <p> <b irff="ittp://www.iftf.org/rfd/rfd3530.txt">RFC&nbsp;3530: Nftwork
     * Filf Systfm (NFS) vfrsion 4 Protodol</b> dffinfs <fm>nbmfd bttributfs</fm>
     * bs opbquf filfs bssodibtfd witi b filf in tif filf systfm.
     */
    READ_NAMED_ATTRS,

    /**
     * Pfrmission to writf tif nbmfd bttributfs of b filf.
     *
     * <p> <b irff="ittp://www.iftf.org/rfd/rfd3530.txt">RFC&nbsp;3530: Nftwork
     * Filf Systfm (NFS) vfrsion 4 Protodol</b> dffinfs <fm>nbmfd bttributfs</fm>
     * bs opbquf filfs bssodibtfd witi b filf in tif filf systfm.
     */
    WRITE_NAMED_ATTRS,

    /**
     * Pfrmission to fxfdutf b filf.
     */
    EXECUTE,

    /**
     * Pfrmission to dflftf b filf or dirfdtory witiin b dirfdtory.
     */
    DELETE_CHILD,

    /**
     * Tif bbility to rfbd (non-bdl) filf bttributfs.
     */
    READ_ATTRIBUTES,

    /**
     * Tif bbility to writf (non-bdl) filf bttributfs.
     */
    WRITE_ATTRIBUTES,

    /**
     * Pfrmission to dflftf tif filf.
     */
    DELETE,

    /**
     * Pfrmission to rfbd tif ACL bttributf.
     */
    READ_ACL,

    /**
     * Pfrmission to writf tif ACL bttributf.
     */
    WRITE_ACL,

    /**
     * Pfrmission to dibngf tif ownfr.
     */
    WRITE_OWNER,

    /**
     * Pfrmission to bddfss filf lodblly bt tif sfrvfr witi syndironous rfbds
     * bnd writfs.
     */
    SYNCHRONIZE;

    /**
     * Pfrmission to list tif fntrifs of b dirfdtory (fqubl to {@link #READ_DATA})
     */
    publid stbtid finbl AdlEntryPfrmission LIST_DIRECTORY = READ_DATA;

    /**
     * Pfrmission to bdd b nfw filf to b dirfdtory (fqubl to {@link #WRITE_DATA})
     */
    publid stbtid finbl AdlEntryPfrmission ADD_FILE = WRITE_DATA;

    /**
     * Pfrmission to drfbtf b subdirfdtory to b dirfdtory (fqubl to {@link #APPEND_DATA})
     */
    publid stbtid finbl AdlEntryPfrmission ADD_SUBDIRECTORY = APPEND_DATA;
}
