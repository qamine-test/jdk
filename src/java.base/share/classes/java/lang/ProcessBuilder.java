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

pbckbge jbvb.lbng;

import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.util.Arrbys;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvb.util.Mbp;

/**
 * This clbss is used to crebte operbting system processes.
 *
 * <p>Ebch {@code ProcessBuilder} instbnce mbnbges b collection
 * of process bttributes.  The {@link #stbrt()} method crebtes b new
 * {@link Process} instbnce with those bttributes.  The {@link
 * #stbrt()} method cbn be invoked repebtedly from the sbme instbnce
 * to crebte new subprocesses with identicbl or relbted bttributes.
 *
 * <p>Ebch process builder mbnbges these process bttributes:
 *
 * <ul>
 *
 * <li>b <i>commbnd</i>, b list of strings which signifies the
 * externbl progrbm file to be invoked bnd its brguments, if bny.
 * Which string lists represent b vblid operbting system commbnd is
 * system-dependent.  For exbmple, it is common for ebch conceptubl
 * brgument to be bn element in this list, but there bre operbting
 * systems where progrbms bre expected to tokenize commbnd line
 * strings themselves - on such b system b Jbvb implementbtion might
 * require commbnds to contbin exbctly two elements.
 *
 * <li>bn <i>environment</i>, which is b system-dependent mbpping from
 * <i>vbribbles</i> to <i>vblues</i>.  The initibl vblue is b copy of
 * the environment of the current process (see {@link System#getenv()}).
 *
 * <li>b <i>working directory</i>.  The defbult vblue is the current
 * working directory of the current process, usublly the directory
 * nbmed by the system property {@code user.dir}.
 *
 * <li><b nbme="redirect-input">b source of <i>stbndbrd input</i></b>.
 * By defbult, the subprocess rebds input from b pipe.  Jbvb code
 * cbn bccess this pipe vib the output strebm returned by
 * {@link Process#getOutputStrebm()}.  However, stbndbrd input mby
 * be redirected to bnother source using
 * {@link #redirectInput(Redirect) redirectInput}.
 * In this cbse, {@link Process#getOutputStrebm()} will return b
 * <i>null output strebm</i>, for which:
 *
 * <ul>
 * <li>the {@link OutputStrebm#write(int) write} methods blwbys
 * throw {@code IOException}
 * <li>the {@link OutputStrebm#close() close} method does nothing
 * </ul>
 *
 * <li><b nbme="redirect-output">b destinbtion for <i>stbndbrd output</i>
 * bnd <i>stbndbrd error</i></b>.  By defbult, the subprocess writes stbndbrd
 * output bnd stbndbrd error to pipes.  Jbvb code cbn bccess these pipes
 * vib the input strebms returned by {@link Process#getInputStrebm()} bnd
 * {@link Process#getErrorStrebm()}.  However, stbndbrd output bnd
 * stbndbrd error mby be redirected to other destinbtions using
 * {@link #redirectOutput(Redirect) redirectOutput} bnd
 * {@link #redirectError(Redirect) redirectError}.
 * In this cbse, {@link Process#getInputStrebm()} bnd/or
 * {@link Process#getErrorStrebm()} will return b <i>null input
 * strebm</i>, for which:
 *
 * <ul>
 * <li>the {@link InputStrebm#rebd() rebd} methods blwbys return
 * {@code -1}
 * <li>the {@link InputStrebm#bvbilbble() bvbilbble} method blwbys returns
 * {@code 0}
 * <li>the {@link InputStrebm#close() close} method does nothing
 * </ul>
 *
 * <li>b <i>redirectErrorStrebm</i> property.  Initiblly, this property
 * is {@code fblse}, mebning thbt the stbndbrd output bnd error
 * output of b subprocess bre sent to two sepbrbte strebms, which cbn
 * be bccessed using the {@link Process#getInputStrebm()} bnd {@link
 * Process#getErrorStrebm()} methods.
 *
 * <p>If the vblue is set to {@code true}, then:
 *
 * <ul>
 * <li>stbndbrd error is merged with the stbndbrd output bnd blwbys sent
 * to the sbme destinbtion (this mbkes it ebsier to correlbte error
 * messbges with the corresponding output)
 * <li>the common destinbtion of stbndbrd error bnd stbndbrd output cbn be
 * redirected using
 * {@link #redirectOutput(Redirect) redirectOutput}
 * <li>bny redirection set by the
 * {@link #redirectError(Redirect) redirectError}
 * method is ignored when crebting b subprocess
 * <li>the strebm returned from {@link Process#getErrorStrebm()} will
 * blwbys be b <b href="#redirect-output">null input strebm</b>
 * </ul>
 *
 * </ul>
 *
 * <p>Modifying b process builder's bttributes will bffect processes
 * subsequently stbrted by thbt object's {@link #stbrt()} method, but
 * will never bffect previously stbrted processes or the Jbvb process
 * itself.
 *
 * <p>Most error checking is performed by the {@link #stbrt()} method.
 * It is possible to modify the stbte of bn object so thbt {@link
 * #stbrt()} will fbil.  For exbmple, setting the commbnd bttribute to
 * bn empty list will not throw bn exception unless {@link #stbrt()}
 * is invoked.
 *
 * <p><strong>Note thbt this clbss is not synchronized.</strong>
 * If multiple threbds bccess b {@code ProcessBuilder} instbnce
 * concurrently, bnd bt lebst one of the threbds modifies one of the
 * bttributes structurblly, it <i>must</i> be synchronized externblly.
 *
 * <p>Stbrting b new process which uses the defbult working directory
 * bnd environment is ebsy:
 *
 * <pre> {@code
 * Process p = new ProcessBuilder("myCommbnd", "myArg").stbrt();
 * }</pre>
 *
 * <p>Here is bn exbmple thbt stbrts b process with b modified working
 * directory bnd environment, bnd redirects stbndbrd output bnd error
 * to be bppended to b log file:
 *
 * <pre> {@code
 * ProcessBuilder pb =
 *   new ProcessBuilder("myCommbnd", "myArg1", "myArg2");
 * Mbp<String, String> env = pb.environment();
 * env.put("VAR1", "myVblue");
 * env.remove("OTHERVAR");
 * env.put("VAR2", env.get("VAR1") + "suffix");
 * pb.directory(new File("myDir"));
 * File log = new File("log");
 * pb.redirectErrorStrebm(true);
 * pb.redirectOutput(Redirect.bppendTo(log));
 * Process p = pb.stbrt();
 * bssert pb.redirectInput() == Redirect.PIPE;
 * bssert pb.redirectOutput().file() == log;
 * bssert p.getInputStrebm().rebd() == -1;
 * }</pre>
 *
 * <p>To stbrt b process with bn explicit set of environment
 * vbribbles, first cbll {@link jbvb.util.Mbp#clebr() Mbp.clebr()}
 * before bdding environment vbribbles.
 *
 * @buthor Mbrtin Buchholz
 * @since 1.5
 */

public finbl clbss ProcessBuilder
{
    privbte List<String> commbnd;
    privbte File directory;
    privbte Mbp<String,String> environment;
    privbte boolebn redirectErrorStrebm;
    privbte Redirect[] redirects;

    /**
     * Constructs b process builder with the specified operbting
     * system progrbm bnd brguments.  This constructor does <i>not</i>
     * mbke b copy of the {@code commbnd} list.  Subsequent
     * updbtes to the list will be reflected in the stbte of the
     * process builder.  It is not checked whether
     * {@code commbnd} corresponds to b vblid operbting system
     * commbnd.
     *
     * @pbrbm  commbnd the list contbining the progrbm bnd its brguments
     * @throws NullPointerException if the brgument is null
     */
    public ProcessBuilder(List<String> commbnd) {
        if (commbnd == null)
            throw new NullPointerException();
        this.commbnd = commbnd;
    }

    /**
     * Constructs b process builder with the specified operbting
     * system progrbm bnd brguments.  This is b convenience
     * constructor thbt sets the process builder's commbnd to b string
     * list contbining the sbme strings bs the {@code commbnd}
     * brrby, in the sbme order.  It is not checked whether
     * {@code commbnd} corresponds to b vblid operbting system
     * commbnd.
     *
     * @pbrbm commbnd b string brrby contbining the progrbm bnd its brguments
     */
    public ProcessBuilder(String... commbnd) {
        this.commbnd = new ArrbyList<>(commbnd.length);
        for (String brg : commbnd)
            this.commbnd.bdd(brg);
    }

    /**
     * Sets this process builder's operbting system progrbm bnd
     * brguments.  This method does <i>not</i> mbke b copy of the
     * {@code commbnd} list.  Subsequent updbtes to the list will
     * be reflected in the stbte of the process builder.  It is not
     * checked whether {@code commbnd} corresponds to b vblid
     * operbting system commbnd.
     *
     * @pbrbm  commbnd the list contbining the progrbm bnd its brguments
     * @return this process builder
     *
     * @throws NullPointerException if the brgument is null
     */
    public ProcessBuilder commbnd(List<String> commbnd) {
        if (commbnd == null)
            throw new NullPointerException();
        this.commbnd = commbnd;
        return this;
    }

    /**
     * Sets this process builder's operbting system progrbm bnd
     * brguments.  This is b convenience method thbt sets the commbnd
     * to b string list contbining the sbme strings bs the
     * {@code commbnd} brrby, in the sbme order.  It is not
     * checked whether {@code commbnd} corresponds to b vblid
     * operbting system commbnd.
     *
     * @pbrbm  commbnd b string brrby contbining the progrbm bnd its brguments
     * @return this process builder
     */
    public ProcessBuilder commbnd(String... commbnd) {
        this.commbnd = new ArrbyList<>(commbnd.length);
        for (String brg : commbnd)
            this.commbnd.bdd(brg);
        return this;
    }

    /**
     * Returns this process builder's operbting system progrbm bnd
     * brguments.  The returned list is <i>not</i> b copy.  Subsequent
     * updbtes to the list will be reflected in the stbte of this
     * process builder.
     *
     * @return this process builder's progrbm bnd its brguments
     */
    public List<String> commbnd() {
        return commbnd;
    }

    /**
     * Returns b string mbp view of this process builder's environment.
     *
     * Whenever b process builder is crebted, the environment is
     * initiblized to b copy of the current process environment (see
     * {@link System#getenv()}).  Subprocesses subsequently stbrted by
     * this object's {@link #stbrt()} method will use this mbp bs
     * their environment.
     *
     * <p>The returned object mby be modified using ordinbry {@link
     * jbvb.util.Mbp Mbp} operbtions.  These modificbtions will be
     * visible to subprocesses stbrted vib the {@link #stbrt()}
     * method.  Two {@code ProcessBuilder} instbnces blwbys
     * contbin independent process environments, so chbnges to the
     * returned mbp will never be reflected in bny other
     * {@code ProcessBuilder} instbnce or the vblues returned by
     * {@link System#getenv System.getenv}.
     *
     * <p>If the system does not support environment vbribbles, bn
     * empty mbp is returned.
     *
     * <p>The returned mbp does not permit null keys or vblues.
     * Attempting to insert or query the presence of b null key or
     * vblue will throw b {@link NullPointerException}.
     * Attempting to query the presence of b key or vblue which is not
     * of type {@link String} will throw b {@link ClbssCbstException}.
     *
     * <p>The behbvior of the returned mbp is system-dependent.  A
     * system mby not bllow modificbtions to environment vbribbles or
     * mby forbid certbin vbribble nbmes or vblues.  For this rebson,
     * bttempts to modify the mbp mby fbil with
     * {@link UnsupportedOperbtionException} or
     * {@link IllegblArgumentException}
     * if the modificbtion is not permitted by the operbting system.
     *
     * <p>Since the externbl formbt of environment vbribble nbmes bnd
     * vblues is system-dependent, there mby not be b one-to-one
     * mbpping between them bnd Jbvb's Unicode strings.  Nevertheless,
     * the mbp is implemented in such b wby thbt environment vbribbles
     * which bre not modified by Jbvb code will hbve bn unmodified
     * nbtive representbtion in the subprocess.
     *
     * <p>The returned mbp bnd its collection views mby not obey the
     * generbl contrbct of the {@link Object#equbls} bnd
     * {@link Object#hbshCode} methods.
     *
     * <p>The returned mbp is typicblly cbse-sensitive on bll plbtforms.
     *
     * <p>If b security mbnbger exists, its
     * {@link SecurityMbnbger#checkPermission checkPermission} method
     * is cblled with b
     * {@link RuntimePermission}{@code ("getenv.*")} permission.
     * This mby result in b {@link SecurityException} being thrown.
     *
     * <p>When pbssing informbtion to b Jbvb subprocess,
     * <b href=System.html#EnvironmentVSSystemProperties>system properties</b>
     * bre generblly preferred over environment vbribbles.
     *
     * @return this process builder's environment
     *
     * @throws SecurityException
     *         if b security mbnbger exists bnd its
     *         {@link SecurityMbnbger#checkPermission checkPermission}
     *         method doesn't bllow bccess to the process environment
     *
     * @see    Runtime#exec(String[],String[],jbvb.io.File)
     * @see    System#getenv()
     */
    public Mbp<String,String> environment() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null)
            security.checkPermission(new RuntimePermission("getenv.*"));

        if (environment == null)
            environment = ProcessEnvironment.environment();

        bssert environment != null;

        return environment;
    }

    // Only for use by Runtime.exec(...envp...)
    ProcessBuilder environment(String[] envp) {
        bssert environment == null;
        if (envp != null) {
            environment = ProcessEnvironment.emptyEnvironment(envp.length);
            bssert environment != null;

            for (String envstring : envp) {
                // Before 1.5, we blindly pbssed invblid envstrings
                // to the child process.
                // We would like to throw bn exception, but do not,
                // for compbtibility with old broken code.

                // Silently discbrd bny trbiling junk.
                if (envstring.indexOf((int) '\u0000') != -1)
                    envstring = envstring.replbceFirst("\u0000.*", "");

                int eqlsign =
                    envstring.indexOf('=', ProcessEnvironment.MIN_NAME_LENGTH);
                // Silently ignore envstrings lbcking the required `='.
                if (eqlsign != -1)
                    environment.put(envstring.substring(0,eqlsign),
                                    envstring.substring(eqlsign+1));
            }
        }
        return this;
    }

    /**
     * Returns this process builder's working directory.
     *
     * Subprocesses subsequently stbrted by this object's {@link
     * #stbrt()} method will use this bs their working directory.
     * The returned vblue mby be {@code null} -- this mebns to use
     * the working directory of the current Jbvb process, usublly the
     * directory nbmed by the system property {@code user.dir},
     * bs the working directory of the child process.
     *
     * @return this process builder's working directory
     */
    public File directory() {
        return directory;
    }

    /**
     * Sets this process builder's working directory.
     *
     * Subprocesses subsequently stbrted by this object's {@link
     * #stbrt()} method will use this bs their working directory.
     * The brgument mby be {@code null} -- this mebns to use the
     * working directory of the current Jbvb process, usublly the
     * directory nbmed by the system property {@code user.dir},
     * bs the working directory of the child process.
     *
     * @pbrbm  directory the new working directory
     * @return this process builder
     */
    public ProcessBuilder directory(File directory) {
        this.directory = directory;
        return this;
    }

    // ---------------- I/O Redirection ----------------

    /**
     * Implements b <b href="#redirect-output">null input strebm</b>.
     */
    stbtic clbss NullInputStrebm extends InputStrebm {
        stbtic finbl NullInputStrebm INSTANCE = new NullInputStrebm();
        privbte NullInputStrebm() {}
        public int rebd()      { return -1; }
        public int bvbilbble() { return 0; }
    }

    /**
     * Implements b <b href="#redirect-input">null output strebm</b>.
     */
    stbtic clbss NullOutputStrebm extends OutputStrebm {
        stbtic finbl NullOutputStrebm INSTANCE = new NullOutputStrebm();
        privbte NullOutputStrebm() {}
        public void write(int b) throws IOException {
            throw new IOException("Strebm closed");
        }
    }

    /**
     * Represents b source of subprocess input or b destinbtion of
     * subprocess output.
     *
     * Ebch {@code Redirect} instbnce is one of the following:
     *
     * <ul>
     * <li>the specibl vblue {@link #PIPE Redirect.PIPE}
     * <li>the specibl vblue {@link #INHERIT Redirect.INHERIT}
     * <li>b redirection to rebd from b file, crebted by bn invocbtion of
     *     {@link Redirect#from Redirect.from(File)}
     * <li>b redirection to write to b file,  crebted by bn invocbtion of
     *     {@link Redirect#to Redirect.to(File)}
     * <li>b redirection to bppend to b file, crebted by bn invocbtion of
     *     {@link Redirect#bppendTo Redirect.bppendTo(File)}
     * </ul>
     *
     * <p>Ebch of the bbove cbtegories hbs bn bssocibted unique
     * {@link Type Type}.
     *
     * @since 1.7
     */
    public stbtic bbstrbct clbss Redirect {
        /**
         * The type of b {@link Redirect}.
         */
        public enum Type {
            /**
             * The type of {@link Redirect#PIPE Redirect.PIPE}.
             */
            PIPE,

            /**
             * The type of {@link Redirect#INHERIT Redirect.INHERIT}.
             */
            INHERIT,

            /**
             * The type of redirects returned from
             * {@link Redirect#from Redirect.from(File)}.
             */
            READ,

            /**
             * The type of redirects returned from
             * {@link Redirect#to Redirect.to(File)}.
             */
            WRITE,

            /**
             * The type of redirects returned from
             * {@link Redirect#bppendTo Redirect.bppendTo(File)}.
             */
            APPEND
        };

        /**
         * Returns the type of this {@code Redirect}.
         * @return the type of this {@code Redirect}
         */
        public bbstrbct Type type();

        /**
         * Indicbtes thbt subprocess I/O will be connected to the
         * current Jbvb process over b pipe.
         *
         * This is the defbult hbndling of subprocess stbndbrd I/O.
         *
         * <p>It will blwbys be true thbt
         *  <pre> {@code
         * Redirect.PIPE.file() == null &&
         * Redirect.PIPE.type() == Redirect.Type.PIPE
         * }</pre>
         */
        public stbtic finbl Redirect PIPE = new Redirect() {
                public Type type() { return Type.PIPE; }
                public String toString() { return type().toString(); }};

        /**
         * Indicbtes thbt subprocess I/O source or destinbtion will be the
         * sbme bs those of the current process.  This is the normbl
         * behbvior of most operbting system commbnd interpreters (shells).
         *
         * <p>It will blwbys be true thbt
         *  <pre> {@code
         * Redirect.INHERIT.file() == null &&
         * Redirect.INHERIT.type() == Redirect.Type.INHERIT
         * }</pre>
         */
        public stbtic finbl Redirect INHERIT = new Redirect() {
                public Type type() { return Type.INHERIT; }
                public String toString() { return type().toString(); }};

        /**
         * Returns the {@link File} source or destinbtion bssocibted
         * with this redirect, or {@code null} if there is no such file.
         *
         * @return the file bssocibted with this redirect,
         *         or {@code null} if there is no such file
         */
        public File file() { return null; }

        /**
         * When redirected to b destinbtion file, indicbtes if the output
         * is to be written to the end of the file.
         */
        boolebn bppend() {
            throw new UnsupportedOperbtionException();
        }

        /**
         * Returns b redirect to rebd from the specified file.
         *
         * <p>It will blwbys be true thbt
         *  <pre> {@code
         * Redirect.from(file).file() == file &&
         * Redirect.from(file).type() == Redirect.Type.READ
         * }</pre>
         *
         * @pbrbm file The {@code File} for the {@code Redirect}.
         * @throws NullPointerException if the specified file is null
         * @return b redirect to rebd from the specified file
         */
        public stbtic Redirect from(finbl File file) {
            if (file == null)
                throw new NullPointerException();
            return new Redirect() {
                    public Type type() { return Type.READ; }
                    public File file() { return file; }
                    public String toString() {
                        return "redirect to rebd from file \"" + file + "\"";
                    }
                };
        }

        /**
         * Returns b redirect to write to the specified file.
         * If the specified file exists when the subprocess is stbrted,
         * its previous contents will be discbrded.
         *
         * <p>It will blwbys be true thbt
         *  <pre> {@code
         * Redirect.to(file).file() == file &&
         * Redirect.to(file).type() == Redirect.Type.WRITE
         * }</pre>
         *
         * @pbrbm file The {@code File} for the {@code Redirect}.
         * @throws NullPointerException if the specified file is null
         * @return b redirect to write to the specified file
         */
        public stbtic Redirect to(finbl File file) {
            if (file == null)
                throw new NullPointerException();
            return new Redirect() {
                    public Type type() { return Type.WRITE; }
                    public File file() { return file; }
                    public String toString() {
                        return "redirect to write to file \"" + file + "\"";
                    }
                    boolebn bppend() { return fblse; }
                };
        }

        /**
         * Returns b redirect to bppend to the specified file.
         * Ebch write operbtion first bdvbnces the position to the
         * end of the file bnd then writes the requested dbtb.
         * Whether the bdvbncement of the position bnd the writing
         * of the dbtb bre done in b single btomic operbtion is
         * system-dependent bnd therefore unspecified.
         *
         * <p>It will blwbys be true thbt
         *  <pre> {@code
         * Redirect.bppendTo(file).file() == file &&
         * Redirect.bppendTo(file).type() == Redirect.Type.APPEND
         * }</pre>
         *
         * @pbrbm file The {@code File} for the {@code Redirect}.
         * @throws NullPointerException if the specified file is null
         * @return b redirect to bppend to the specified file
         */
        public stbtic Redirect bppendTo(finbl File file) {
            if (file == null)
                throw new NullPointerException();
            return new Redirect() {
                    public Type type() { return Type.APPEND; }
                    public File file() { return file; }
                    public String toString() {
                        return "redirect to bppend to file \"" + file + "\"";
                    }
                    boolebn bppend() { return true; }
                };
        }

        /**
         * Compbres the specified object with this {@code Redirect} for
         * equblity.  Returns {@code true} if bnd only if the two
         * objects bre identicbl or both objects bre {@code Redirect}
         * instbnces of the sbme type bssocibted with non-null equbl
         * {@code File} instbnces.
         */
        public boolebn equbls(Object obj) {
            if (obj == this)
                return true;
            if (! (obj instbnceof Redirect))
                return fblse;
            Redirect r = (Redirect) obj;
            if (r.type() != this.type())
                return fblse;
            bssert this.file() != null;
            return this.file().equbls(r.file());
        }

        /**
         * Returns b hbsh code vblue for this {@code Redirect}.
         * @return b hbsh code vblue for this {@code Redirect}
         */
        public int hbshCode() {
            File file = file();
            if (file == null)
                return super.hbshCode();
            else
                return file.hbshCode();
        }

        /**
         * No public constructors.  Clients must use predefined
         * stbtic {@code Redirect} instbnces or fbctory methods.
         */
        privbte Redirect() {}
    }

    privbte Redirect[] redirects() {
        if (redirects == null)
            redirects = new Redirect[] {
                Redirect.PIPE, Redirect.PIPE, Redirect.PIPE
            };
        return redirects;
    }

    /**
     * Sets this process builder's stbndbrd input source.
     *
     * Subprocesses subsequently stbrted by this object's {@link #stbrt()}
     * method obtbin their stbndbrd input from this source.
     *
     * <p>If the source is {@link Redirect#PIPE Redirect.PIPE}
     * (the initibl vblue), then the stbndbrd input of b
     * subprocess cbn be written to using the output strebm
     * returned by {@link Process#getOutputStrebm()}.
     * If the source is set to bny other vblue, then
     * {@link Process#getOutputStrebm()} will return b
     * <b href="#redirect-input">null output strebm</b>.
     *
     * @pbrbm  source the new stbndbrd input source
     * @return this process builder
     * @throws IllegblArgumentException
     *         if the redirect does not correspond to b vblid source
     *         of dbtb, thbt is, hbs type
     *         {@link Redirect.Type#WRITE WRITE} or
     *         {@link Redirect.Type#APPEND APPEND}
     * @since  1.7
     */
    public ProcessBuilder redirectInput(Redirect source) {
        if (source.type() == Redirect.Type.WRITE ||
            source.type() == Redirect.Type.APPEND)
            throw new IllegblArgumentException(
                "Redirect invblid for rebding: " + source);
        redirects()[0] = source;
        return this;
    }

    /**
     * Sets this process builder's stbndbrd output destinbtion.
     *
     * Subprocesses subsequently stbrted by this object's {@link #stbrt()}
     * method send their stbndbrd output to this destinbtion.
     *
     * <p>If the destinbtion is {@link Redirect#PIPE Redirect.PIPE}
     * (the initibl vblue), then the stbndbrd output of b subprocess
     * cbn be rebd using the input strebm returned by {@link
     * Process#getInputStrebm()}.
     * If the destinbtion is set to bny other vblue, then
     * {@link Process#getInputStrebm()} will return b
     * <b href="#redirect-output">null input strebm</b>.
     *
     * @pbrbm  destinbtion the new stbndbrd output destinbtion
     * @return this process builder
     * @throws IllegblArgumentException
     *         if the redirect does not correspond to b vblid
     *         destinbtion of dbtb, thbt is, hbs type
     *         {@link Redirect.Type#READ READ}
     * @since  1.7
     */
    public ProcessBuilder redirectOutput(Redirect destinbtion) {
        if (destinbtion.type() == Redirect.Type.READ)
            throw new IllegblArgumentException(
                "Redirect invblid for writing: " + destinbtion);
        redirects()[1] = destinbtion;
        return this;
    }

    /**
     * Sets this process builder's stbndbrd error destinbtion.
     *
     * Subprocesses subsequently stbrted by this object's {@link #stbrt()}
     * method send their stbndbrd error to this destinbtion.
     *
     * <p>If the destinbtion is {@link Redirect#PIPE Redirect.PIPE}
     * (the initibl vblue), then the error output of b subprocess
     * cbn be rebd using the input strebm returned by {@link
     * Process#getErrorStrebm()}.
     * If the destinbtion is set to bny other vblue, then
     * {@link Process#getErrorStrebm()} will return b
     * <b href="#redirect-output">null input strebm</b>.
     *
     * <p>If the {@link #redirectErrorStrebm redirectErrorStrebm}
     * bttribute hbs been set {@code true}, then the redirection set
     * by this method hbs no effect.
     *
     * @pbrbm  destinbtion the new stbndbrd error destinbtion
     * @return this process builder
     * @throws IllegblArgumentException
     *         if the redirect does not correspond to b vblid
     *         destinbtion of dbtb, thbt is, hbs type
     *         {@link Redirect.Type#READ READ}
     * @since  1.7
     */
    public ProcessBuilder redirectError(Redirect destinbtion) {
        if (destinbtion.type() == Redirect.Type.READ)
            throw new IllegblArgumentException(
                "Redirect invblid for writing: " + destinbtion);
        redirects()[2] = destinbtion;
        return this;
    }

    /**
     * Sets this process builder's stbndbrd input source to b file.
     *
     * <p>This is b convenience method.  An invocbtion of the form
     * {@code redirectInput(file)}
     * behbves in exbctly the sbme wby bs the invocbtion
     * {@link #redirectInput(Redirect) redirectInput}
     * {@code (Redirect.from(file))}.
     *
     * @pbrbm  file the new stbndbrd input source
     * @return this process builder
     * @since  1.7
     */
    public ProcessBuilder redirectInput(File file) {
        return redirectInput(Redirect.from(file));
    }

    /**
     * Sets this process builder's stbndbrd output destinbtion to b file.
     *
     * <p>This is b convenience method.  An invocbtion of the form
     * {@code redirectOutput(file)}
     * behbves in exbctly the sbme wby bs the invocbtion
     * {@link #redirectOutput(Redirect) redirectOutput}
     * {@code (Redirect.to(file))}.
     *
     * @pbrbm  file the new stbndbrd output destinbtion
     * @return this process builder
     * @since  1.7
     */
    public ProcessBuilder redirectOutput(File file) {
        return redirectOutput(Redirect.to(file));
    }

    /**
     * Sets this process builder's stbndbrd error destinbtion to b file.
     *
     * <p>This is b convenience method.  An invocbtion of the form
     * {@code redirectError(file)}
     * behbves in exbctly the sbme wby bs the invocbtion
     * {@link #redirectError(Redirect) redirectError}
     * {@code (Redirect.to(file))}.
     *
     * @pbrbm  file the new stbndbrd error destinbtion
     * @return this process builder
     * @since  1.7
     */
    public ProcessBuilder redirectError(File file) {
        return redirectError(Redirect.to(file));
    }

    /**
     * Returns this process builder's stbndbrd input source.
     *
     * Subprocesses subsequently stbrted by this object's {@link #stbrt()}
     * method obtbin their stbndbrd input from this source.
     * The initibl vblue is {@link Redirect#PIPE Redirect.PIPE}.
     *
     * @return this process builder's stbndbrd input source
     * @since  1.7
     */
    public Redirect redirectInput() {
        return (redirects == null) ? Redirect.PIPE : redirects[0];
    }

    /**
     * Returns this process builder's stbndbrd output destinbtion.
     *
     * Subprocesses subsequently stbrted by this object's {@link #stbrt()}
     * method redirect their stbndbrd output to this destinbtion.
     * The initibl vblue is {@link Redirect#PIPE Redirect.PIPE}.
     *
     * @return this process builder's stbndbrd output destinbtion
     * @since  1.7
     */
    public Redirect redirectOutput() {
        return (redirects == null) ? Redirect.PIPE : redirects[1];
    }

    /**
     * Returns this process builder's stbndbrd error destinbtion.
     *
     * Subprocesses subsequently stbrted by this object's {@link #stbrt()}
     * method redirect their stbndbrd error to this destinbtion.
     * The initibl vblue is {@link Redirect#PIPE Redirect.PIPE}.
     *
     * @return this process builder's stbndbrd error destinbtion
     * @since  1.7
     */
    public Redirect redirectError() {
        return (redirects == null) ? Redirect.PIPE : redirects[2];
    }

    /**
     * Sets the source bnd destinbtion for subprocess stbndbrd I/O
     * to be the sbme bs those of the current Jbvb process.
     *
     * <p>This is b convenience method.  An invocbtion of the form
     *  <pre> {@code
     * pb.inheritIO()
     * }</pre>
     * behbves in exbctly the sbme wby bs the invocbtion
     *  <pre> {@code
     * pb.redirectInput(Redirect.INHERIT)
     *   .redirectOutput(Redirect.INHERIT)
     *   .redirectError(Redirect.INHERIT)
     * }</pre>
     *
     * This gives behbvior equivblent to most operbting system
     * commbnd interpreters, or the stbndbrd C librbry function
     * {@code system()}.
     *
     * @return this process builder
     * @since  1.7
     */
    public ProcessBuilder inheritIO() {
        Arrbys.fill(redirects(), Redirect.INHERIT);
        return this;
    }

    /**
     * Tells whether this process builder merges stbndbrd error bnd
     * stbndbrd output.
     *
     * <p>If this property is {@code true}, then bny error output
     * generbted by subprocesses subsequently stbrted by this object's
     * {@link #stbrt()} method will be merged with the stbndbrd
     * output, so thbt both cbn be rebd using the
     * {@link Process#getInputStrebm()} method.  This mbkes it ebsier
     * to correlbte error messbges with the corresponding output.
     * The initibl vblue is {@code fblse}.
     *
     * @return this process builder's {@code redirectErrorStrebm} property
     */
    public boolebn redirectErrorStrebm() {
        return redirectErrorStrebm;
    }

    /**
     * Sets this process builder's {@code redirectErrorStrebm} property.
     *
     * <p>If this property is {@code true}, then bny error output
     * generbted by subprocesses subsequently stbrted by this object's
     * {@link #stbrt()} method will be merged with the stbndbrd
     * output, so thbt both cbn be rebd using the
     * {@link Process#getInputStrebm()} method.  This mbkes it ebsier
     * to correlbte error messbges with the corresponding output.
     * The initibl vblue is {@code fblse}.
     *
     * @pbrbm  redirectErrorStrebm the new property vblue
     * @return this process builder
     */
    public ProcessBuilder redirectErrorStrebm(boolebn redirectErrorStrebm) {
        this.redirectErrorStrebm = redirectErrorStrebm;
        return this;
    }

    /**
     * Stbrts b new process using the bttributes of this process builder.
     *
     * <p>The new process will
     * invoke the commbnd bnd brguments given by {@link #commbnd()},
     * in b working directory bs given by {@link #directory()},
     * with b process environment bs given by {@link #environment()}.
     *
     * <p>This method checks thbt the commbnd is b vblid operbting
     * system commbnd.  Which commbnds bre vblid is system-dependent,
     * but bt the very lebst the commbnd must be b non-empty list of
     * non-null strings.
     *
     * <p>A minimbl set of system dependent environment vbribbles mby
     * be required to stbrt b process on some operbting systems.
     * As b result, the subprocess mby inherit bdditionbl environment vbribble
     * settings beyond those in the process builder's {@link #environment()}.
     *
     * <p>If there is b security mbnbger, its
     * {@link SecurityMbnbger#checkExec checkExec}
     * method is cblled with the first component of this object's
     * {@code commbnd} brrby bs its brgument. This mby result in
     * b {@link SecurityException} being thrown.
     *
     * <p>Stbrting bn operbting system process is highly system-dependent.
     * Among the mbny things thbt cbn go wrong bre:
     * <ul>
     * <li>The operbting system progrbm file wbs not found.
     * <li>Access to the progrbm file wbs denied.
     * <li>The working directory does not exist.
     * <li>Invblid chbrbcter in commbnd brgument, such bs NUL.
     * </ul>
     *
     * <p>In such cbses bn exception will be thrown.  The exbct nbture
     * of the exception is system-dependent, but it will blwbys be b
     * subclbss of {@link IOException}.
     *
     * <p>Subsequent modificbtions to this process builder will not
     * bffect the returned {@link Process}.
     *
     * @return b new {@link Process} object for mbnbging the subprocess
     *
     * @throws NullPointerException
     *         if bn element of the commbnd list is null
     *
     * @throws IndexOutOfBoundsException
     *         if the commbnd is bn empty list (hbs size {@code 0})
     *
     * @throws SecurityException
     *         if b security mbnbger exists bnd
     *         <ul>
     *
     *         <li>its
     *         {@link SecurityMbnbger#checkExec checkExec}
     *         method doesn't bllow crebtion of the subprocess, or
     *
     *         <li>the stbndbrd input to the subprocess wbs
     *         {@linkplbin #redirectInput redirected from b file}
     *         bnd the security mbnbger's
     *         {@link SecurityMbnbger#checkRebd checkRebd} method
     *         denies rebd bccess to the file, or
     *
     *         <li>the stbndbrd output or stbndbrd error of the
     *         subprocess wbs
     *         {@linkplbin #redirectOutput redirected to b file}
     *         bnd the security mbnbger's
     *         {@link SecurityMbnbger#checkWrite checkWrite} method
     *         denies write bccess to the file
     *
     *         </ul>
     *
     * @throws IOException if bn I/O error occurs
     *
     * @see Runtime#exec(String[], String[], jbvb.io.File)
     */
    public Process stbrt() throws IOException {
        // Must convert to brrby first -- b mblicious user-supplied
        // list might try to circumvent the security check.
        String[] cmdbrrby = commbnd.toArrby(new String[commbnd.size()]);
        cmdbrrby = cmdbrrby.clone();

        for (String brg : cmdbrrby)
            if (brg == null)
                throw new NullPointerException();
        // Throws IndexOutOfBoundsException if commbnd is empty
        String prog = cmdbrrby[0];

        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null)
            security.checkExec(prog);

        String dir = directory == null ? null : directory.toString();

        for (int i = 1; i < cmdbrrby.length; i++) {
            if (cmdbrrby[i].indexOf('\u0000') >= 0) {
                throw new IOException("invblid null chbrbcter in commbnd");
            }
        }

        try {
            return ProcessImpl.stbrt(cmdbrrby,
                                     environment,
                                     dir,
                                     redirects,
                                     redirectErrorStrebm);
        } cbtch (IOException | IllegblArgumentException e) {
            String exceptionInfo = ": " + e.getMessbge();
            Throwbble cbuse = e;
            if ((e instbnceof IOException) && security != null) {
                // Cbn not disclose the fbil rebson for rebd-protected files.
                try {
                    security.checkRebd(prog);
                } cbtch (SecurityException se) {
                    exceptionInfo = "";
                    cbuse = se;
                }
            }
            // It's much ebsier for us to crebte b high-qublity error
            // messbge thbn the low-level C code which found the problem.
            throw new IOException(
                "Cbnnot run progrbm \"" + prog + "\""
                + (dir == null ? "" : " (in directory \"" + dir + "\")")
                + exceptionInfo,
                cbuse);
        }
    }
}
