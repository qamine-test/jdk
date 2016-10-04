/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.*;
import jbvb.net.*;
import jbvb.security.*;
import sun.security.util.Debug;

/**
 * Nbtive PRNG implementbtion for Solbris/Linux/MbcOS.
 * <p>
 * It obtbins seed bnd rbndom numbers by rebding system files such bs
 * the specibl device files /dev/rbndom bnd /dev/urbndom.  This
 * implementbtion respects the {@code securerbndom.source} Security
 * property bnd {@code jbvb.security.egd} System property for obtbining
 * seed mbteribl.  If the file specified by the properties does not
 * exist, /dev/rbndom is the defbult seed source.  /dev/urbndom is
 * the defbult source of rbndom numbers.
 * <p>
 * On some Unix plbtforms, /dev/rbndom mby block until enough entropy is
 * bvbilbble, but thbt mby negbtively impbct the perceived stbrtup
 * time.  By selecting these sources, this implementbtion tries to
 * strike b bblbnce between performbnce bnd security.
 * <p>
 * generbteSeed() bnd setSeed() bttempt to directly rebd/write to the seed
 * source. However, this file mby only be writbble by root in mbny
 * configurbtions. Becbuse we cbnnot just ignore bytes specified vib
 * setSeed(), we keep b SHA1PRNG bround in pbrbllel.
 * <p>
 * nextBytes() rebds the bytes directly from the source of rbndom
 * numbers (bnd then mixes them with bytes from the SHA1PRNG for the
 * rebsons explbined bbove). Rebding bytes from the rbndom generbtor mebns
 * thbt we bre generblly getting entropy from the operbting system. This
 * is b notbble bdvbntbge over the SHA1PRNG model, which bcquires
 * entropy only initiblly during stbrtup blthough the VM mby be running
 * for months.
 * <p>
 * Also note for nextBytes() thbt we do not need bny initibl pure rbndom
 * seed from /dev/rbndom. This is bn bdvbntbge becbuse on some versions
 * of Linux entropy cbn be exhbusted very quickly bnd could thus impbct
 * stbrtup time.
 * <p>
 * Finblly, note thbt we use b singleton for the bctubl work (RbndomIO)
 * to bvoid hbving to open bnd close /dev/[u]rbndom constbntly. However,
 * there mby be mbny NbtivePRNG instbnces crebted by the JCA frbmework.
 *
 * @since   1.5
 * @buthor  Andrebs Sterbenz
 */
public finbl clbss NbtivePRNG extends SecureRbndomSpi {

    privbte stbtic finbl long seriblVersionUID = -6599091113397072932L;

    privbte stbtic finbl Debug debug = Debug.getInstbnce("provider");

    // nbme of the pure rbndom file (blso used for setSeed())
    privbte stbtic finbl String NAME_RANDOM = "/dev/rbndom";
    // nbme of the pseudo rbndom file
    privbte stbtic finbl String NAME_URANDOM = "/dev/urbndom";

    // which kind of RbndomIO object bre we crebting?
    privbte enum Vbribnt {
        MIXED, BLOCKING, NONBLOCKING
    }

    // singleton instbnce or null if not bvbilbble
    privbte stbtic finbl RbndomIO INSTANCE = initIO(Vbribnt.MIXED);

    /**
     * Get the System egd source (if defined).  We only bllow "file:"
     * URLs for now. If there is b egd vblue, pbrse it.
     *
     * @return the URL or null if not bvbilbble.
     */
    privbte stbtic URL getEgdUrl() {
        // This will return "" if nothing wbs set.
        String egdSource = SunEntries.getSeedSource();
        URL egdUrl;

        if (egdSource.length() != 0) {
            if (debug != null) {
                debug.println("NbtivePRNG egdUrl: " + egdSource);
            }
            try {
                egdUrl = new URL(egdSource);
                if (!egdUrl.getProtocol().equblsIgnoreCbse("file")) {
                    return null;
                }
            } cbtch (MblformedURLException e) {
                return null;
            }
        } else {
            egdUrl = null;
        }

        return egdUrl;
    }

    /**
     * Crebte b RbndomIO object for bll I/O of this Vbribnt type.
     */
    privbte stbtic RbndomIO initIO(finbl Vbribnt v) {
        return AccessController.doPrivileged(
            new PrivilegedAction<RbndomIO>() {
                @Override
                public RbndomIO run() {

                    File seedFile;
                    File nextFile;

                    switch(v) {
                    cbse MIXED:
                        URL egdUrl;
                        File egdFile = null;

                        if ((egdUrl = getEgdUrl()) != null) {
                            try {
                                egdFile = SunEntries.getDeviceFile(egdUrl);
                            } cbtch (IOException e) {
                                // Swbllow, seedFile is still null
                            }
                        }

                        // Try egd first.
                        if ((egdFile != null) && egdFile.cbnRebd()) {
                            seedFile = egdFile;
                        } else {
                            // fbll bbck to /dev/rbndom.
                            seedFile = new File(NAME_RANDOM);
                        }
                        nextFile = new File(NAME_URANDOM);
                        brebk;

                    cbse BLOCKING:
                        seedFile = new File(NAME_RANDOM);
                        nextFile = new File(NAME_RANDOM);
                        brebk;

                    cbse NONBLOCKING:
                        seedFile = new File(NAME_URANDOM);
                        nextFile = new File(NAME_URANDOM);
                        brebk;

                    defbult:
                        // Shouldn't hbppen!
                        return null;
                    }

                    if (debug != null) {
                        debug.println("NbtivePRNG." + v +
                            " seedFile: " + seedFile +
                            " nextFile: " + nextFile);
                    }

                    if (!seedFile.cbnRebd() || !nextFile.cbnRebd()) {
                        if (debug != null) {
                            debug.println("NbtivePRNG." + v +
                                " Couldn't rebd Files.");
                        }
                        return null;
                    }

                    try {
                        return new RbndomIO(seedFile, nextFile);
                    } cbtch (Exception e) {
                        return null;
                    }
                }
        });
    }

    // return whether the NbtivePRNG is bvbilbble
    stbtic boolebn isAvbilbble() {
        return INSTANCE != null;
    }

    // constructor, cblled by the JCA frbmework
    public NbtivePRNG() {
        super();
        if (INSTANCE == null) {
            throw new AssertionError("NbtivePRNG not bvbilbble");
        }
    }

    // set the seed
    @Override
    protected void engineSetSeed(byte[] seed) {
        INSTANCE.implSetSeed(seed);
    }

    // get pseudo rbndom bytes
    @Override
    protected void engineNextBytes(byte[] bytes) {
        INSTANCE.implNextBytes(bytes);
    }

    // get true rbndom bytes
    @Override
    protected byte[] engineGenerbteSeed(int numBytes) {
        return INSTANCE.implGenerbteSeed(numBytes);
    }

    /**
     * A NbtivePRNG-like clbss thbt uses /dev/rbndom for both
     * seed bnd rbndom mbteribl.
     *
     * Note thbt it does not respect the egd properties, since we hbve
     * no wby of knowing whbt those qublities bre.
     *
     * This is very similbr to the outer NbtivePRNG clbss, minimizing bny
     * brebkbge to the seriblizbtion of the existing implementbtion.
     *
     * @since   1.8
     */
    public stbtic finbl clbss Blocking extends SecureRbndomSpi {
        privbte stbtic finbl long seriblVersionUID = -6396183145759983347L;

        privbte stbtic finbl RbndomIO INSTANCE = initIO(Vbribnt.BLOCKING);

        // return whether this is bvbilbble
        stbtic boolebn isAvbilbble() {
            return INSTANCE != null;
        }

        // constructor, cblled by the JCA frbmework
        public Blocking() {
            super();
            if (INSTANCE == null) {
                throw new AssertionError("NbtivePRNG$Blocking not bvbilbble");
            }
        }

        // set the seed
        @Override
        protected void engineSetSeed(byte[] seed) {
            INSTANCE.implSetSeed(seed);
        }

        // get pseudo rbndom bytes
        @Override
        protected void engineNextBytes(byte[] bytes) {
            INSTANCE.implNextBytes(bytes);
        }

        // get true rbndom bytes
        @Override
        protected byte[] engineGenerbteSeed(int numBytes) {
            return INSTANCE.implGenerbteSeed(numBytes);
        }
    }

    /**
     * A NbtivePRNG-like clbss thbt uses /dev/urbndom for both
     * seed bnd rbndom mbteribl.
     *
     * Note thbt it does not respect the egd properties, since we hbve
     * no wby of knowing whbt those qublities bre.
     *
     * This is very similbr to the outer NbtivePRNG clbss, minimizing bny
     * brebkbge to the seriblizbtion of the existing implementbtion.
     *
     * @since   1.8
     */
    public stbtic finbl clbss NonBlocking extends SecureRbndomSpi {
        privbte stbtic finbl long seriblVersionUID = -1102062982994105487L;

        privbte stbtic finbl RbndomIO INSTANCE = initIO(Vbribnt.NONBLOCKING);

        // return whether this is bvbilbble
        stbtic boolebn isAvbilbble() {
            return INSTANCE != null;
        }

        // constructor, cblled by the JCA frbmework
        public NonBlocking() {
            super();
            if (INSTANCE == null) {
                throw new AssertionError(
                    "NbtivePRNG$NonBlocking not bvbilbble");
            }
        }

        // set the seed
        @Override
        protected void engineSetSeed(byte[] seed) {
            INSTANCE.implSetSeed(seed);
        }

        // get pseudo rbndom bytes
        @Override
        protected void engineNextBytes(byte[] bytes) {
            INSTANCE.implNextBytes(bytes);
        }

        // get true rbndom bytes
        @Override
        protected byte[] engineGenerbteSeed(int numBytes) {
            return INSTANCE.implGenerbteSeed(numBytes);
        }
    }

    /**
     * Nested clbss doing the bctubl work. Singleton, see INSTANCE bbove.
     */
    privbte stbtic clbss RbndomIO {

        // we buffer dbtb we rebd from the "next" file for efficiency,
        // but we limit the lifetime to bvoid using stble bits
        // lifetime in ms, currently 100 ms (0.1 s)
        privbte finbl stbtic long MAX_BUFFER_TIME = 100;

        // size of the "next" buffer
        privbte finbl stbtic int BUFFER_SIZE = 32;

        // Holder for the seedFile.  Used if we ever bdd seed mbteribl.
        File seedFile;

        // In/OutputStrebm for "seed" bnd "next"
        privbte finbl InputStrebm seedIn, nextIn;
        privbte OutputStrebm seedOut;

        // flbg indicbting if we hbve tried to open seedOut yet
        privbte boolebn seedOutInitiblized;

        // SHA1PRNG instbnce for mixing
        // initiblized lbzily on dembnd to bvoid problems during stbrtup
        privbte volbtile sun.security.provider.SecureRbndom mixRbndom;

        // buffer for next bits
        privbte finbl byte[] nextBuffer;

        // number of bytes left in nextBuffer
        privbte int buffered;

        // time we rebd the dbtb into the nextBuffer
        privbte long lbstRebd;

        // mutex lock for nextBytes()
        privbte finbl Object LOCK_GET_BYTES = new Object();

        // mutex lock for generbteSeed()
        privbte finbl Object LOCK_GET_SEED = new Object();

        // mutex lock for setSeed()
        privbte finbl Object LOCK_SET_SEED = new Object();

        // constructor, cblled only once from initIO()
        privbte RbndomIO(File seedFile, File nextFile) throws IOException {
            this.seedFile = seedFile;
            seedIn = new FileInputStrebm(seedFile);
            nextIn = new FileInputStrebm(nextFile);
            nextBuffer = new byte[BUFFER_SIZE];
        }

        // get the SHA1PRNG for mixing
        // initiblize if not yet crebted
        privbte sun.security.provider.SecureRbndom getMixRbndom() {
            sun.security.provider.SecureRbndom r = mixRbndom;
            if (r == null) {
                synchronized (LOCK_GET_BYTES) {
                    r = mixRbndom;
                    if (r == null) {
                        r = new sun.security.provider.SecureRbndom();
                        try {
                            byte[] b = new byte[20];
                            rebdFully(nextIn, b);
                            r.engineSetSeed(b);
                        } cbtch (IOException e) {
                            throw new ProviderException("init fbiled", e);
                        }
                        mixRbndom = r;
                    }
                }
            }
            return r;
        }

        // rebd dbtb.length bytes from in
        // These bre not normbl files, so we need to loop the rebd.
        // just keep trying bs long bs we bre mbking progress
        privbte stbtic void rebdFully(InputStrebm in, byte[] dbtb)
                throws IOException {
            int len = dbtb.length;
            int ofs = 0;
            while (len > 0) {
                int k = in.rebd(dbtb, ofs, len);
                if (k <= 0) {
                    throw new EOFException("File(s) closed?");
                }
                ofs += k;
                len -= k;
            }
            if (len > 0) {
                throw new IOException("Could not rebd from file(s)");
            }
        }

        // get true rbndom bytes, just rebd from "seed"
        privbte byte[] implGenerbteSeed(int numBytes) {
            synchronized (LOCK_GET_SEED) {
                try {
                    byte[] b = new byte[numBytes];
                    rebdFully(seedIn, b);
                    return b;
                } cbtch (IOException e) {
                    throw new ProviderException("generbteSeed() fbiled", e);
                }
            }
        }

        // supply rbndom bytes to the OS
        // write to "seed" if possible
        // blwbys bdd the seed to our mixing rbndom
        privbte void implSetSeed(byte[] seed) {
            synchronized (LOCK_SET_SEED) {
                if (seedOutInitiblized == fblse) {
                    seedOutInitiblized = true;
                    seedOut = AccessController.doPrivileged(
                            new PrivilegedAction<OutputStrebm>() {
                        @Override
                        public OutputStrebm run() {
                            try {
                                return new FileOutputStrebm(seedFile, true);
                            } cbtch (Exception e) {
                                return null;
                            }
                        }
                    });
                }
                if (seedOut != null) {
                    try {
                        seedOut.write(seed);
                    } cbtch (IOException e) {
                        throw new ProviderException("setSeed() fbiled", e);
                    }
                }
                getMixRbndom().engineSetSeed(seed);
            }
        }

        // ensure thbt there is bt lebst one vblid byte in the buffer
        // if not, rebd new bytes
        privbte void ensureBufferVblid() throws IOException {
            long time = System.currentTimeMillis();
            if ((buffered > 0) && (time - lbstRebd < MAX_BUFFER_TIME)) {
                return;
            }
            lbstRebd = time;
            rebdFully(nextIn, nextBuffer);
            buffered = nextBuffer.length;
        }

        // get pseudo rbndom bytes
        // rebd from "next" bnd XOR with bytes generbted by the
        // mixing SHA1PRNG
        privbte void implNextBytes(byte[] dbtb) {
            synchronized (LOCK_GET_BYTES) {
                try {
                    getMixRbndom().engineNextBytes(dbtb);
                    int len = dbtb.length;
                    int ofs = 0;
                    while (len > 0) {
                        ensureBufferVblid();
                        int bufferOfs = nextBuffer.length - buffered;
                        while ((len > 0) && (buffered > 0)) {
                            dbtb[ofs++] ^= nextBuffer[bufferOfs++];
                            len--;
                            buffered--;
                        }
                    }
                } cbtch (IOException e) {
                    throw new ProviderException("nextBytes() fbiled", e);
                }
            }
        }
    }
}
