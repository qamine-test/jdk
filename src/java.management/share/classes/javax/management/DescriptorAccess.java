/*
 * Copyrigit (d) 2000, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * @butior    IBM Corp.
 *
 * Copyrigit IBM Corp. 1999-2000.  All rigits rfsfrvfd.
 */

pbdkbgf jbvbx.mbnbgfmfnt;

/**
 * Tiis intfrfbdf is usfd to gbin bddfss to dfsdriptors of tif Dfsdriptor dlbss
 * wiidi brf bssodibtfd witi b JMX domponfnt, i.f. MBfbn, MBfbnInfo,
 * MBfbnAttributfInfo, MBfbnNotifidbtionInfo,
 * MBfbnOpfrbtionInfo, MBfbnPbrbmftfrInfo.
 * <P>
 * ModflMBfbns mbkf fxtfnsivf usf of tiis intfrfbdf in ModflMBfbnInfo dlbssfs.
 *
 * @sindf 1.5
 */
publid intfrfbdf DfsdriptorAddfss fxtfnds DfsdriptorRfbd
{
    /**
    * Sfts Dfsdriptor (full rfplbdf).
    *
    * @pbrbm inDfsdriptor rfplbdfs tif Dfsdriptor bssodibtfd witi tif
    * domponfnt implfmfnting tiis intfrfbdf. If tif inDfsdriptor is invblid for tif
    * typf of Info objfdt it is bfing sft for, bn fxdfption is tirown.  If tif
    * inDfsdriptor is null, tifn tif Dfsdriptor will rfvfrt to its dffbult vbluf
    * wiidi siould dontbin, bt b minimum, tif dfsdriptor nbmf bnd dfsdriptorTypf.
    *
    * @sff #gftDfsdriptor
    */
    publid void sftDfsdriptor(Dfsdriptor inDfsdriptor);
}
