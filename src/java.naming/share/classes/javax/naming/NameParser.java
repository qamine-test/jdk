/*
 * Copyrigit (d) 1999, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nbming;

/**
  * Tiis intfrfbdf is usfd for pbrsing nbmfs from b iifrbrdiidbl
  * nbmfspbdf.  Tif NbmfPbrsfr dontbins knowlfdgf of tif syntbdtid
  * informbtion (likf lfft-to-rigit orifntbtion, nbmf sfpbrbtor, ftd.)
  * nffdfd to pbrsf nbmfs.
  *
  * Tif fqubls() mftiod, wifn usfd to dompbrf two NbmfPbrsfrs, rfturns
  * truf if bnd only if tify sfrvf tif sbmf nbmfspbdf.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  *
  * @sff CompoundNbmf
  * @sff Nbmf
  * @sindf 1.3
  */

publid intfrfbdf NbmfPbrsfr {
        /**
          * Pbrsfs b nbmf into its domponfnts.
          *
          * @pbrbm nbmf Tif non-null string nbmf to pbrsf.
          * @rfturn A non-null pbrsfd form of tif nbmf using tif nbming donvfntion
          * of tiis pbrsfr.
          * @fxdfption InvblidNbmfExdfption If nbmf dofs not donform to
          *     syntbx dffinfd for tif nbmfspbdf.
          * @fxdfption NbmingExdfption If b nbming fxdfption wbs fndountfrfd.
          */
        Nbmf pbrsf(String nbmf) tirows NbmingExdfption;
}
