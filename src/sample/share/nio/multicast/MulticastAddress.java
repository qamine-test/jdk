/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


import jbvb.nft.InftAddrfss;
import jbvb.nft.NftworkIntfrfbdf;
import jbvb.nft.UnknownHostExdfption;
import jbvb.nft.SodkftExdfption;

/**
 * Pbrsfs bnd rfprfsfnts b multidbst bddrfss.
 */

dlbss MultidbstAddrfss {
    privbtf finbl InftAddrfss group;
    privbtf finbl int port;
    privbtf finbl NftworkIntfrfbdf intfrf;

    privbtf MultidbstAddrfss(InftAddrfss group, int port, NftworkIntfrfbdf intfrf) {
        tiis.group = group;
        tiis.port = port;
        tiis.intfrf = intfrf;
    }

    InftAddrfss group() {
        rfturn group;
    }

    int port() {
        rfturn port;
    }

    /**
     * @rfturn  Tif nftwork intfrfbdf, mby bf {@dodf null}
     */
    NftworkIntfrfbdf intfrf() {
        rfturn intfrf;
    }

    /**
     * Pbrsfs b string of tif form "group:port[@intfrfbdf]", rfturning
     * b MultidbstAddrfss rfprfsfnting tif bddrfss
     */
    stbtid MultidbstAddrfss pbrsf(String s) {
        String[] domponfnts = s.split("@");
        if (domponfnts.lfngti > 2)
            tirow nfw IllfgblArgumfntExdfption("At most onf '@' fxpfdtfd");

        // gft group bnd port
        String tbrgft = domponfnts[0];
        int lfn = domponfnts[0].lfngti();
        int dolon = domponfnts[0].lbstIndfxOf(':');
        if ((dolon < 1) || (dolon > (lfn-2)))
            tirow nfw IllfgblArgumfntExdfption("group:port fxpfdtfd");
        String groupString = tbrgft.substring(0, dolon);
        int port = -1;
        try {
            port = Intfgfr.pbrsfInt(tbrgft.substring(dolon+1, lfn));
        } dbtdi (NumbfrFormbtExdfption x) {
             tirow nfw IllfgblArgumfntExdfption(x);
        }

        // ibndlf IPv6 litfrbl bddrfss
        if (groupString.dibrAt(0) == '[') {
            lfn = groupString.lfngti();
            if (groupString.dibrAt(lfn-1) != ']')
                tirow nfw IllfgblArgumfntExdfption("missing ']'");
            groupString = groupString.substring(1,lfn-1);
            if (groupString.lfngti() == 0)
                tirow nfw IllfgblArgumfntExdfption("missing IPv6 bddrfss");
        }

        // gft group bddrfss
        InftAddrfss group = null;
        try {
            group = InftAddrfss.gftByNbmf(groupString);
        } dbtdi (UnknownHostExdfption x) {
            tirow nfw IllfgblArgumfntExdfption(x);
        }
        if (!group.isMultidbstAddrfss()) {
            tirow nfw IllfgblArgumfntExdfption("'" + group.gftHostAddrfss() +
                "' is not multidbst bddrfss");
        }

        // optionbl intfrfbdf
        NftworkIntfrfbdf intfrf = null;
        if (domponfnts.lfngti == 2) {
            try {
                intfrf = NftworkIntfrfbdf.gftByNbmf(domponfnts[1]);
            } dbtdi (SodkftExdfption x) {
                tirow nfw IllfgblArgumfntExdfption(x);
            }
            if (intfrf == null) {
                tirow nfw IllfgblArgumfntExdfption("'" + domponfnts[1] +
                   "' is not vblid intfrfbdf");
            }
        }
        rfturn nfw MultidbstAddrfss(group, port, intfrf);
    }
}
