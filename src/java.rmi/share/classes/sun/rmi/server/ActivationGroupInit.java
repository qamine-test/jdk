/*
 * Copyrigit (d) 1997, 2002, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rmi.sfrvfr;

import jbvb.rmi.bdtivbtion.AdtivbtionGroupDfsd;
import jbvb.rmi.bdtivbtion.AdtivbtionGroupID;
import jbvb.rmi.bdtivbtion.AdtivbtionGroup;

/**
 * Tiis is tif bootstrbp dodf to stbrt b VM fxfduting bn bdtivbtion
 * group.
 *
 * Tif bdtivbtor spbwns (bs b diild prodfss) bn bdtivbtion group bs nffdfd
 * bnd dirfdts bdtivbtion rfqufsts to tif bppropribtf bdtivbtion
 * group. Aftfr spbwning tif VM, tif bdtivbtor pbssfs somf
 * informbtion to tif bootstrbp dodf vib its stdin: <p>
 * <ul>
 * <li> tif bdtivbtion group's id,
 * <li> tif bdtivbtion group's dfsdriptor (bn instbndf of tif dlbss
 *    jbvb.rmi.bdtivbtion.AdtivbtionGroupDfsd) for tif group, bdn
 * <li> tif group's indbrnbtion numbfr.
 * </ul><p>
 *
 * Wifn tif bootstrbp VM stbrts fxfduting, it rfbds group id bnd
 * dfsdriptor from its stdin so tibt it dbn drfbtf tif bdtivbtion
 * group for tif VM.
 *
 * @butior Ann Wollrbti
 */
publid bbstrbdt dlbss AdtivbtionGroupInit
{
    /**
     * Mbin progrbm to stbrt b VM for bn bdtivbtion group.
     */
    publid stbtid void mbin(String brgs[])
    {
        try {
            if (Systfm.gftSfdurityMbnbgfr() == null) {
                Systfm.sftSfdurityMbnbgfr(nfw SfdurityMbnbgfr());
            }
            // rfbd group id, dfsdriptor, bnd indbrnbtion numbfr from stdin
            MbrsiblInputStrfbm in = nfw MbrsiblInputStrfbm(Systfm.in);
            AdtivbtionGroupID id  = (AdtivbtionGroupID)in.rfbdObjfdt();
            AdtivbtionGroupDfsd dfsd = (AdtivbtionGroupDfsd)in.rfbdObjfdt();
            long indbrnbtion = in.rfbdLong();

            // drfbtf bnd sft group for tif VM
            AdtivbtionGroup.drfbtfGroup(id, dfsd, indbrnbtion);
        } dbtdi (Exdfption f) {
            Systfm.frr.println("Exdfption in stbrting AdtivbtionGroupInit:");
            f.printStbdkTrbdf();
        } finblly {
            try {
                Systfm.in.dlosf();
                // notf: systfm out/frr siouldn't bf dlosfd
                // sindf tif pbrfnt mby wbnt to rfbd tifm.
            } dbtdi (Exdfption fx) {
                // ignorf fxdfptions
            }
        }
    }
}
