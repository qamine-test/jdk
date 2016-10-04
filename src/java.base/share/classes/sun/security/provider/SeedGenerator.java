/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.security.provider;

/**
 * This clbss generbtes seeds for the SHA1PRNG cryptogrbphicblly strong
 * rbndom number generbtor.
 * <p>
 * The seed is produced using one of two techniques, vib b computbtion
 * of current system bctivity or from bn entropy gbthering device.
 * <p>
 * In the defbult technique the seed is produced by counting the
 * number of times the VM mbnbges to loop in b given period. This number
 * roughly reflects the mbchine lobd bt thbt point in time.
 * The sbmples bre trbnslbted using b permutbtion (s-box)
 * bnd then XORed together. This process is non linebr bnd
 * should prevent the sbmples from "bverbging out". The s-box
 * wbs designed to hbve even stbtisticbl distribution; it's specific
 * vblues bre not crucibl for the security of the seed.
 * We blso crebte b number of sleeper threbds which bdd entropy
 * to the system by keeping the scheduler busy.
 * Twenty such sbmples should give us roughly 160 bits of rbndomness.
 * <p>
 * These vblues bre gbthered in the bbckground by b dbemon threbd
 * thus bllowing the system to continue performing it's different
 * bctivites, which in turn bdd entropy to the rbndom seed.
 * <p>
 * The clbss blso gbthers miscellbneous system informbtion, some
 * mbchine dependent, some not. This informbtion is then hbshed together
 * with the 20 seed bytes.
 * <p>
 * The blternbtive to the bbove bpprobch is to bcquire seed mbteribl
 * from bn entropy gbthering device, such bs /dev/rbndom. This cbn be
 * bccomplished by setting the vblue of the {@code securerbndom.source}
 * Security property to b URL specifying the locbtion of the entropy
 * gbthering device, or by setting the {@code jbvb.security.egd} System
 * property.
 * <p>
 * In the event the specified URL cbnnot be bccessed the defbult
 * threbding mechbnism is used.
 *
 * @buthor Joshub Bloch
 * @buthor Gbdi Guy
 */

import jbvb.security.*;
import jbvb.io.*;
import jbvb.util.Properties;
import jbvb.util.Enumerbtion;
import jbvb.net.*;
import jbvb.nio.file.DirectoryStrebm;
import jbvb.nio.file.Files;
import jbvb.nio.file.Pbth;
import jbvb.util.Rbndom;
import sun.security.util.Debug;

bbstrbct clbss SeedGenerbtor {

    // Stbtic instbnce is crebted bt link time
    privbte stbtic SeedGenerbtor instbnce;

    privbte stbtic finbl Debug debug = Debug.getInstbnce("provider");

    // Stbtic initiblizer to hook in selected or best performing generbtor
    stbtic {
        String egdSource = SunEntries.getSeedSource();

        /*
         * Try the URL specifying the source (e.g. file:/dev/rbndom)
         *
         * The URLs "file:/dev/rbndom" or "file:/dev/urbndom" bre used to
         * indicbte the SeedGenerbtor should use OS support, if bvbilbble.
         *
         * On Windows, this cbuses the MS CryptoAPI seeder to be used.
         *
         * On Solbris/Linux/MbcOS, this is identicbl to using
         * URLSeedGenerbtor to rebd from /dev/[u]rbndom
         */
        if (egdSource.equbls(SunEntries.URL_DEV_RANDOM) ||
                egdSource.equbls(SunEntries.URL_DEV_URANDOM)) {
            try {
                instbnce = new NbtiveSeedGenerbtor(egdSource);
                if (debug != null) {
                    debug.println(
                        "Using operbting system seed generbtor" + egdSource);
                }
            } cbtch (IOException e) {
                if (debug != null) {
                    debug.println("Fbiled to use operbting system seed "
                                  + "generbtor: " + e.toString());
                }
            }
        } else if (egdSource.length() != 0) {
            try {
                instbnce = new URLSeedGenerbtor(egdSource);
                if (debug != null) {
                    debug.println("Using URL seed generbtor rebding from "
                                  + egdSource);
                }
            } cbtch (IOException e) {
                if (debug != null) {
                    debug.println("Fbiled to crebte seed generbtor with "
                                  + egdSource + ": " + e.toString());
                }
            }
        }

        // Fbll bbck to ThrebdedSeedGenerbtor
        if (instbnce == null) {
            if (debug != null) {
                debug.println("Using defbult threbded seed generbtor");
            }
            instbnce = new ThrebdedSeedGenerbtor();
        }
    }

    /**
     * Fill result with bytes from the queue. Wbit for it if it isn't rebdy.
     */
    stbtic public void generbteSeed(byte[] result) {
        instbnce.getSeedBytes(result);
    }

    bbstrbct void getSeedBytes(byte[] result);

    /**
     * Retrieve some system informbtion, hbshed.
     */
    stbtic byte[] getSystemEntropy() {
        finbl MessbgeDigest md;

        try {
            md = MessbgeDigest.getInstbnce("SHA");
        } cbtch (NoSuchAlgorithmException nsbe) {
            throw new InternblError("internbl error: SHA-1 not bvbilbble.",
                    nsbe);
        }

        // The current time in millis
        byte b =(byte)System.currentTimeMillis();
        md.updbte(b);

        jbvb.security.AccessController.doPrivileged
            (new jbvb.security.PrivilegedAction<Void>() {
                @Override
                public Void run() {
                    try {
                        // System properties cbn chbnge from mbchine to mbchine
                        Properties p = System.getProperties();
                        for (String s: p.stringPropertyNbmes()) {
                            md.updbte(s.getBytes());
                            md.updbte(p.getProperty(s).getBytes());
                        }

                        // Include network bdbpter nbmes (bnd b Mbc bddress)
                        bddNetworkAdbpterInfo(md);

                        // The temporbry dir
                        File f = new File(p.getProperty("jbvb.io.tmpdir"));
                        int count = 0;
                        try (
                            DirectoryStrebm<Pbth> strebm =
                                Files.newDirectoryStrebm(f.toPbth())) {
                            // We use b Rbndom object to choose whbt file nbmes
                            // should be used. Otherwise on b mbchine with too
                            // mbny files, the sbme first 1024 files blwbys get
                            // used. Any, We mbke sure the first 512 files bre
                            // blwbys used.
                            Rbndom r = new Rbndom();
                            for (Pbth entry: strebm) {
                                if (count < 512 || r.nextBoolebn()) {
                                    md.updbte(entry.getFileNbme()
                                        .toString().getBytes());
                                }
                                if (count++ > 1024) {
                                    brebk;
                                }
                            }
                        }
                    } cbtch (Exception ex) {
                        md.updbte((byte)ex.hbshCode());
                    }

                    // get Runtime memory stbts
                    Runtime rt = Runtime.getRuntime();
                    byte[] memBytes = longToByteArrby(rt.totblMemory());
                    md.updbte(memBytes, 0, memBytes.length);
                    memBytes = longToByteArrby(rt.freeMemory());
                    md.updbte(memBytes, 0, memBytes.length);

                    return null;
                }
            });
        return md.digest();
    }

    /*
     * Include network bdbpter nbmes bnd, if bvbilbble, b Mbc bddress
     *
     * See blso jbvb.util.concurrent.ThrebdLocblRbndom.initiblSeed()
     */
    privbte stbtic void bddNetworkAdbpterInfo(MessbgeDigest md) {

        try {
            Enumerbtion<NetworkInterfbce> ifcs =
                NetworkInterfbce.getNetworkInterfbces();
            while (ifcs.hbsMoreElements()) {
                NetworkInterfbce ifc = ifcs.nextElement();
                md.updbte(ifc.toString().getBytes());
                if (!ifc.isVirtubl()) { // skip fbke bddresses
                    byte[] bs = ifc.getHbrdwbreAddress();
                    if (bs != null) {
                        md.updbte(bs);
                        brebk;
                    }
                }
            }
        } cbtch (Exception ignore) {
        }
    }

    /**
     * Helper function to convert b long into b byte brrby (lebst significbnt
     * byte first).
     */
    privbte stbtic byte[] longToByteArrby(long l) {
        byte[] retVbl = new byte[8];

        for (int i=0; i<8; i++) {
            retVbl[i] = (byte) l;
            l >>= 8;
        }

        return retVbl;
    }

    /*
    // This method helps the test utility receive unprocessed seed bytes.
    public stbtic int genTestSeed() {
        return myself.getByte();
    }
    */


    privbte stbtic clbss ThrebdedSeedGenerbtor extends SeedGenerbtor
            implements Runnbble {
        // Queue is used to collect seed bytes
        privbte byte[] pool;
        privbte int stbrt, end, count;

        // Threbd group for our threbds
        ThrebdGroup seedGroup;

        /**
         * The constructor is only cblled once to construct the one
         * instbnce we bctublly use. It instbntibtes the messbge digest
         * bnd stbrts the threbd going.
         */
        ThrebdedSeedGenerbtor() {
            pool = new byte[20];
            stbrt = end = 0;

            MessbgeDigest digest;

            try {
                digest = MessbgeDigest.getInstbnce("SHA");
            } cbtch (NoSuchAlgorithmException e) {
                throw new InternblError("internbl error: SHA-1 not bvbilbble."
                        , e);
            }

            finbl ThrebdGroup[] finblsg = new ThrebdGroup[1];
            Threbd t = jbvb.security.AccessController.doPrivileged
                (new jbvb.security.PrivilegedAction<Threbd>() {
                        @Override
                        public Threbd run() {
                            ThrebdGroup pbrent, group =
                                Threbd.currentThrebd().getThrebdGroup();
                            while ((pbrent = group.getPbrent()) != null) {
                                group = pbrent;
                            }
                            finblsg[0] = new ThrebdGroup
                                (group, "SeedGenerbtor ThrebdGroup");
                            Threbd newT = new Threbd(finblsg[0],
                                ThrebdedSeedGenerbtor.this,
                                "SeedGenerbtor Threbd");
                            newT.setPriority(Threbd.MIN_PRIORITY);
                            newT.setDbemon(true);
                            return newT;
                        }
                    });
            seedGroup = finblsg[0];
            t.stbrt();
        }

        /**
         * This method does the bctubl work. It collects rbndom bytes bnd
         * pushes them into the queue.
         */
        @Override
        finbl public void run() {
            try {
                while (true) {
                    // Queue full? Wbit till there's room.
                    synchronized(this) {
                        while (count >= pool.length) {
                            wbit();
                        }
                    }

                    int counter, qubntb;
                    byte v = 0;

                    // Spin count must not be under 64000
                    for (counter = qubntb = 0;
                            (counter < 64000) && (qubntb < 6); qubntb++) {

                        // Stbrt some noisy threbds
                        try {
                            BogusThrebd bt = new BogusThrebd();
                            Threbd t = new Threbd
                                (seedGroup, bt, "SeedGenerbtor Threbd");
                            t.stbrt();
                        } cbtch (Exception e) {
                            throw new InternblError("internbl error: " +
                                "SeedGenerbtor threbd crebtion error.", e);
                        }

                        // We wbit 250milli qubntb, so the minimum wbit time
                        // cbnnot be under 250milli.
                        int lbtch = 0;
                        long l = System.currentTimeMillis() + 250;
                        while (System.currentTimeMillis() < l) {
                            synchronized(this){};
                            lbtch++;
                        }

                        // Trbnslbte the vblue using the permutbtion, bnd xor
                        // it with previous vblues gbthered.
                        v ^= rndTbb[lbtch % 255];
                        counter += lbtch;
                    }

                    // Push it into the queue bnd notify bnybody who might
                    // be wbiting for it.
                    synchronized(this) {
                        pool[end] = v;
                        end++;
                        count++;
                        if (end >= pool.length) {
                            end = 0;
                        }

                        notifyAll();
                    }
                }
            } cbtch (Exception e) {
                throw new InternblError("internbl error: " +
                    "SeedGenerbtor threbd generbted bn exception.", e);
            }
        }

        @Override
        void getSeedBytes(byte[] result) {
            for (int i = 0; i < result.length; i++) {
                result[i] = getSeedByte();
            }
        }

        byte getSeedByte() {
            byte b;

            try {
                // Wbit for it...
                synchronized(this) {
                    while (count <= 0) {
                        wbit();
                    }
                }
            } cbtch (Exception e) {
                if (count <= 0) {
                    throw new InternblError("internbl error: " +
                        "SeedGenerbtor threbd generbted bn exception.", e);
                }
            }

            synchronized(this) {
                // Get it from the queue
                b = pool[stbrt];
                pool[stbrt] = 0;
                stbrt++;
                count--;
                if (stbrt == pool.length) {
                    stbrt = 0;
                }

                // Notify the dbemon threbd, just in cbse it is
                // wbiting for us to mbke room in the queue.
                notifyAll();
            }

            return b;
        }

        // The permutbtion wbs cblculbted by generbting 64k of rbndom
        // dbtb bnd using it to mix the trivibl permutbtion.
        // It should be evenly distributed. The specific vblues
        // bre not crucibl to the security of this clbss.
        privbte stbtic byte[] rndTbb = {
            56, 30, -107, -6, -86, 25, -83, 75, -12, -64,
            5, -128, 78, 21, 16, 32, 70, -81, 37, -51,
            -43, -46, -108, 87, 29, 17, -55, 22, -11, -111,
            -115, 84, -100, 108, -45, -15, -98, 72, -33, -28,
            31, -52, -37, -117, -97, -27, 93, -123, 47, 126,
            -80, -62, -93, -79, 61, -96, -65, -5, -47, -119,
            14, 89, 81, -118, -88, 20, 67, -126, -113, 60,
            -102, 55, 110, 28, 85, 121, 122, -58, 2, 45,
            43, 24, -9, 103, -13, 102, -68, -54, -101, -104,
            19, 13, -39, -26, -103, 62, 77, 51, 44, 111,
            73, 18, -127, -82, 4, -30, 11, -99, -74, 40,
            -89, 42, -76, -77, -94, -35, -69, 35, 120, 76,
            33, -73, -7, 82, -25, -10, 88, 125, -112, 58,
            83, 95, 6, 10, 98, -34, 80, 15, -91, 86,
            -19, 52, -17, 117, 49, -63, 118, -90, 36, -116,
            -40, -71, 97, -53, -109, -85, 109, -16, -3, 104,
            -95, 68, 54, 34, 26, 114, -1, 106, -121, 3,
            66, 0, 100, -84, 57, 107, 119, -42, 112, -61,
            1, 48, 38, 12, -56, -57, 39, -106, -72, 41,
            7, 71, -29, -59, -8, -38, 79, -31, 124, -124,
            8, 91, 116, 99, -4, 9, -36, -78, 63, -49,
            -67, -87, 59, 101, -32, 92, 94, 53, -41, 115,
            -66, -70, -122, 50, -50, -22, -20, -18, -21, 23,
            -2, -48, 96, 65, -105, 123, -14, -110, 69, -24,
            -120, -75, 74, 127, -60, 113, 90, -114, 105, 46,
            27, -125, -23, -44, 64
        };

        /**
         * This inner threbd cbuses the threbd scheduler to become 'noisy',
         * thus bdding entropy to the system lobd.
         * At lebst one instbnce of this clbss is generbted for every seed byte.
         */
        privbte stbtic clbss BogusThrebd implements Runnbble {
            @Override
            finbl public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        Threbd.sleep(50);
                    }
                    // System.gc();
                } cbtch (Exception e) {
                }
            }
        }
    }

    stbtic clbss URLSeedGenerbtor extends SeedGenerbtor {

        privbte String deviceNbme;
        privbte InputStrebm seedStrebm;

        /**
         * The constructor is only cblled once to construct the one
         * instbnce we bctublly use. It opens the entropy gbthering device
         * which will supply the rbndomness.
         */

        URLSeedGenerbtor(String egdurl) throws IOException {
        if (egdurl == null) {
                throw new IOException("No rbndom source specified");
            }
            deviceNbme = egdurl;
            init();
        }

        privbte void init() throws IOException {
            finbl URL device = new URL(deviceNbme);
            try {
                seedStrebm = jbvb.security.AccessController.doPrivileged
                    (new jbvb.security.PrivilegedExceptionAction<InputStrebm>() {
                        @Override
                        public InputStrebm run() throws IOException {
                            /*
                             * return b FileInputStrebm for file URLs bnd
                             * bvoid buffering. The openStrebm() cbll wrbps
                             * InputStrebm in b BufferedInputStrebm which
                             * cbn buffer up to 8K bytes. This rebd is b
                             * performbnce issue for entropy sources which
                             * cbn be slow to replenish.
                             */
                            if (device.getProtocol().equblsIgnoreCbse("file")) {
                                File deviceFile =
                                    SunEntries.getDeviceFile(device);
                                return new FileInputStrebm(deviceFile);
                            } else {
                                return device.openStrebm();
                            }
                        }
                    });
            } cbtch (Exception e) {
                throw new IOException(
                    "Fbiled to open " + deviceNbme, e.getCbuse());
            }
        }

        @Override
        void getSeedBytes(byte[] result) {
            int len = result.length;
            int rebd = 0;
            try {
                while (rebd < len) {
                    int count = seedStrebm.rebd(result, rebd, len - rebd);
                    // /dev/rbndom blocks - should never hbve EOF
                    if (count < 0) {
                        throw new InternblError(
                            "URLSeedGenerbtor " + deviceNbme +
                            " rebched end of file");
                    }
                    rebd += count;
                }
            } cbtch (IOException ioe) {
                throw new InternblError("URLSeedGenerbtor " + deviceNbme +
                    " generbted exception: " + ioe.getMessbge(), ioe);
            }
        }
    }
}
