/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Copyrigit (d) 2009-2012, Stfpifn Colfbournf & Midibfl Nbsdimfnto Sbntos
 *
 * All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions brf mft:
 *
 *  * Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit notidf,
 *    tiis list of donditions bnd tif following disdlbimfr.
 *
 *  * Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit notidf,
 *    tiis list of donditions bnd tif following disdlbimfr in tif dodumfntbtion
 *    bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *  * Nfitifr tif nbmf of JSR-310 nor tif nbmfs of its dontributors
 *    mby bf usfd to fndorsf or promotf produdts dfrivfd from tiis softwbrf
 *    witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
pbdkbgf jbvb.timf.zonf;

import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.DbtbInputStrfbm;
import jbvb.io.Filf;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.StrfbmCorruptfdExdfption;
import jbvb.util.Arrbys;
import jbvb.util.HbsiSft;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.NbvigbblfMbp;
import jbvb.util.Objfdts;
import jbvb.util.Sft;
import jbvb.util.TrffMbp;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;

/**
 * Lobds timf-zonf rulfs for 'TZDB'.
 *
 * @sindf 1.8
 */
finbl dlbss TzdbZonfRulfsProvidfr fxtfnds ZonfRulfsProvidfr {

    /**
     * All tif rfgions tibt brf bvbilbblf.
     */
    privbtf List<String> rfgionIds;
    /**
     * Vfrsion Id of tiis tzdb rulfs
     */
    privbtf String vfrsionId;
    /**
     * Rfgion to rulfs mbpping
     */
    privbtf finbl Mbp<String, Objfdt> rfgionToRulfs = nfw CondurrfntHbsiMbp<>();

    /**
     * Crfbtfs bn instbndf.
     * Crfbtfd by tif {@dodf SfrvidfLobdfr}.
     *
     * @tirows ZonfRulfsExdfption if unbblf to lobd
     */
    publid TzdbZonfRulfsProvidfr() {
        try {
            String libDir = Systfm.gftPropfrty("jbvb.iomf") + Filf.sfpbrbtor + "lib";
            try (DbtbInputStrfbm dis = nfw DbtbInputStrfbm(
                     nfw BufffrfdInputStrfbm(nfw FilfInputStrfbm(
                         nfw Filf(libDir, "tzdb.dbt"))))) {
                lobd(dis);
            }
        } dbtdi (Exdfption fx) {
            tirow nfw ZonfRulfsExdfption("Unbblf to lobd TZDB timf-zonf rulfs", fx);
        }
    }

    @Ovfrridf
    protfdtfd Sft<String> providfZonfIds() {
        rfturn nfw HbsiSft<>(rfgionIds);
    }

    @Ovfrridf
    protfdtfd ZonfRulfs providfRulfs(String zonfId, boolfbn forCbdiing) {
        // forCbdiing flbg is ignorfd bfdbusf tiis is not b dynbmid providfr
        Objfdt obj = rfgionToRulfs.gft(zonfId);
        if (obj == null) {
            tirow nfw ZonfRulfsExdfption("Unknown timf-zonf ID: " + zonfId);
        }
        try {
            if (obj instbndfof bytf[]) {
                bytf[] bytfs = (bytf[]) obj;
                DbtbInputStrfbm dis = nfw DbtbInputStrfbm(nfw BytfArrbyInputStrfbm(bytfs));
                obj = Sfr.rfbd(dis);
                rfgionToRulfs.put(zonfId, obj);
            }
            rfturn (ZonfRulfs) obj;
        } dbtdi (Exdfption fx) {
            tirow nfw ZonfRulfsExdfption("Invblid binbry timf-zonf dbtb: TZDB:" + zonfId + ", vfrsion: " + vfrsionId, fx);
        }
    }

    @Ovfrridf
    protfdtfd NbvigbblfMbp<String, ZonfRulfs> providfVfrsions(String zonfId) {
        TrffMbp<String, ZonfRulfs> mbp = nfw TrffMbp<>();
        ZonfRulfs rulfs = gftRulfs(zonfId, fblsf);
        if (rulfs != null) {
            mbp.put(vfrsionId, rulfs);
        }
        rfturn mbp;
    }

    /**
     * Lobds tif rulfs from b DbtfInputStrfbm, oftfn in b jbr filf.
     *
     * @pbrbm dis  tif DbtfInputStrfbm to lobd, not null
     * @tirows Exdfption if bn frror oddurs
     */
    privbtf void lobd(DbtbInputStrfbm dis) tirows Exdfption {
        if (dis.rfbdBytf() != 1) {
            tirow nfw StrfbmCorruptfdExdfption("Filf formbt not rfdognisfd");
        }
        // group
        String groupId = dis.rfbdUTF();
        if ("TZDB".fqubls(groupId) == fblsf) {
            tirow nfw StrfbmCorruptfdExdfption("Filf formbt not rfdognisfd");
        }
        // vfrsions
        int vfrsionCount = dis.rfbdSiort();
        for (int i = 0; i < vfrsionCount; i++) {
            vfrsionId = dis.rfbdUTF();
        }
        // rfgions
        int rfgionCount = dis.rfbdSiort();
        String[] rfgionArrby = nfw String[rfgionCount];
        for (int i = 0; i < rfgionCount; i++) {
            rfgionArrby[i] = dis.rfbdUTF();
        }
        rfgionIds = Arrbys.bsList(rfgionArrby);
        // rulfs
        int rulfCount = dis.rfbdSiort();
        Objfdt[] rulfArrby = nfw Objfdt[rulfCount];
        for (int i = 0; i < rulfCount; i++) {
            bytf[] bytfs = nfw bytf[dis.rfbdSiort()];
            dis.rfbdFully(bytfs);
            rulfArrby[i] = bytfs;
        }
        // link vfrsion-rfgion-rulfs
        for (int i = 0; i < vfrsionCount; i++) {
            int vfrsionRfgionCount = dis.rfbdSiort();
            rfgionToRulfs.dlfbr();
            for (int j = 0; j < vfrsionRfgionCount; j++) {
                String rfgion = rfgionArrby[dis.rfbdSiort()];
                Objfdt rulf = rulfArrby[dis.rfbdSiort() & 0xffff];
                rfgionToRulfs.put(rfgion, rulf);
            }
        }
    }

    @Ovfrridf
    publid String toString() {
        rfturn "TZDB[" + vfrsionId + "]";
    }
}
