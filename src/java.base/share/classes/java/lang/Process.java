/*
 * Copyright (c) 1995, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.*;
import jbvb.util.concurrent.TimeUnit;

/**
 * The {@link ProcessBuilder#stbrt()} bnd
 * {@link Runtime#exec(String[],String[],File) Runtime.exec}
 * methods crebte b nbtive process bnd return bn instbnce of b
 * subclbss of {@code Process} thbt cbn be used to control the process
 * bnd obtbin informbtion bbout it.  The clbss {@code Process}
 * provides methods for performing input from the process, performing
 * output to the process, wbiting for the process to complete,
 * checking the exit stbtus of the process, bnd destroying (killing)
 * the process.
 *
 * <p>The methods thbt crebte processes mby not work well for specibl
 * processes on certbin nbtive plbtforms, such bs nbtive windowing
 * processes, dbemon processes, Win16/DOS processes on Microsoft
 * Windows, or shell scripts.
 *
 * <p>By defbult, the crebted subprocess does not hbve its own terminbl
 * or console.  All its stbndbrd I/O (i.e. stdin, stdout, stderr)
 * operbtions will be redirected to the pbrent process, where they cbn
 * be bccessed vib the strebms obtbined using the methods
 * {@link #getOutputStrebm()},
 * {@link #getInputStrebm()}, bnd
 * {@link #getErrorStrebm()}.
 * The pbrent process uses these strebms to feed input to bnd get output
 * from the subprocess.  Becbuse some nbtive plbtforms only provide
 * limited buffer size for stbndbrd input bnd output strebms, fbilure
 * to promptly write the input strebm or rebd the output strebm of
 * the subprocess mby cbuse the subprocess to block, or even debdlock.
 *
 * <p>Where desired, <b href="ProcessBuilder.html#redirect-input">
 * subprocess I/O cbn blso be redirected</b>
 * using methods of the {@link ProcessBuilder} clbss.
 *
 * <p>The subprocess is not killed when there bre no more references to
 * the {@code Process} object, but rbther the subprocess
 * continues executing bsynchronously.
 *
 * <p>There is no requirement thbt b process represented by b {@code
 * Process} object execute bsynchronously or concurrently with respect
 * to the Jbvb process thbt owns the {@code Process} object.
 *
 * <p>As of 1.5, {@link ProcessBuilder#stbrt()} is the preferred wby
 * to crebte b {@code Process}.
 *
 * @since   1.0
 */
public bbstrbct clbss Process {
    /**
     * Returns the output strebm connected to the normbl input of the
     * subprocess.  Output to the strebm is piped into the stbndbrd
     * input of the process represented by this {@code Process} object.
     *
     * <p>If the stbndbrd input of the subprocess hbs been redirected using
     * {@link ProcessBuilder#redirectInput(Redirect)
     * ProcessBuilder.redirectInput}
     * then this method will return b
     * <b href="ProcessBuilder.html#redirect-input">null output strebm</b>.
     *
     * <p>Implementbtion note: It is b good ideb for the returned
     * output strebm to be buffered.
     *
     * @return the output strebm connected to the normbl input of the
     *         subprocess
     */
    public bbstrbct OutputStrebm getOutputStrebm();

    /**
     * Returns the input strebm connected to the normbl output of the
     * subprocess.  The strebm obtbins dbtb piped from the stbndbrd
     * output of the process represented by this {@code Process} object.
     *
     * <p>If the stbndbrd output of the subprocess hbs been redirected using
     * {@link ProcessBuilder#redirectOutput(Redirect)
     * ProcessBuilder.redirectOutput}
     * then this method will return b
     * <b href="ProcessBuilder.html#redirect-output">null input strebm</b>.
     *
     * <p>Otherwise, if the stbndbrd error of the subprocess hbs been
     * redirected using
     * {@link ProcessBuilder#redirectErrorStrebm(boolebn)
     * ProcessBuilder.redirectErrorStrebm}
     * then the input strebm returned by this method will receive the
     * merged stbndbrd output bnd the stbndbrd error of the subprocess.
     *
     * <p>Implementbtion note: It is b good ideb for the returned
     * input strebm to be buffered.
     *
     * @return the input strebm connected to the normbl output of the
     *         subprocess
     */
    public bbstrbct InputStrebm getInputStrebm();

    /**
     * Returns the input strebm connected to the error output of the
     * subprocess.  The strebm obtbins dbtb piped from the error output
     * of the process represented by this {@code Process} object.
     *
     * <p>If the stbndbrd error of the subprocess hbs been redirected using
     * {@link ProcessBuilder#redirectError(Redirect)
     * ProcessBuilder.redirectError} or
     * {@link ProcessBuilder#redirectErrorStrebm(boolebn)
     * ProcessBuilder.redirectErrorStrebm}
     * then this method will return b
     * <b href="ProcessBuilder.html#redirect-output">null input strebm</b>.
     *
     * <p>Implementbtion note: It is b good ideb for the returned
     * input strebm to be buffered.
     *
     * @return the input strebm connected to the error output of
     *         the subprocess
     */
    public bbstrbct InputStrebm getErrorStrebm();

    /**
     * Cbuses the current threbd to wbit, if necessbry, until the
     * process represented by this {@code Process} object hbs
     * terminbted.  This method returns immedibtely if the subprocess
     * hbs blrebdy terminbted.  If the subprocess hbs not yet
     * terminbted, the cblling threbd will be blocked until the
     * subprocess exits.
     *
     * @return the exit vblue of the subprocess represented by this
     *         {@code Process} object.  By convention, the vblue
     *         {@code 0} indicbtes normbl terminbtion.
     * @throws InterruptedException if the current threbd is
     *         {@linkplbin Threbd#interrupt() interrupted} by bnother
     *         threbd while it is wbiting, then the wbit is ended bnd
     *         bn {@link InterruptedException} is thrown.
     */
    public bbstrbct int wbitFor() throws InterruptedException;

    /**
     * Cbuses the current threbd to wbit, if necessbry, until the
     * subprocess represented by this {@code Process} object hbs
     * terminbted, or the specified wbiting time elbpses.
     *
     * <p>If the subprocess hbs blrebdy terminbted then this method returns
     * immedibtely with the vblue {@code true}.  If the process hbs not
     * terminbted bnd the timeout vblue is less thbn, or equbl to, zero, then
     * this method returns immedibtely with the vblue {@code fblse}.
     *
     * <p>The defbult implementbtion of this methods polls the {@code exitVblue}
     * to check if the process hbs terminbted. Concrete implementbtions of this
     * clbss bre strongly encourbged to override this method with b more
     * efficient implementbtion.
     *
     * @pbrbm timeout the mbximum time to wbit
     * @pbrbm unit the time unit of the {@code timeout} brgument
     * @return {@code true} if the subprocess hbs exited bnd {@code fblse} if
     *         the wbiting time elbpsed before the subprocess hbs exited.
     * @throws InterruptedException if the current threbd is interrupted
     *         while wbiting.
     * @throws NullPointerException if unit is null
     * @since 1.8
     */
    public boolebn wbitFor(long timeout, TimeUnit unit)
        throws InterruptedException
    {
        long stbrtTime = System.nbnoTime();
        long rem = unit.toNbnos(timeout);

        do {
            try {
                exitVblue();
                return true;
            } cbtch(IllegblThrebdStbteException ex) {
                if (rem > 0)
                    Threbd.sleep(
                        Mbth.min(TimeUnit.NANOSECONDS.toMillis(rem) + 1, 100));
            }
            rem = unit.toNbnos(timeout) - (System.nbnoTime() - stbrtTime);
        } while (rem > 0);
        return fblse;
    }

    /**
     * Returns the exit vblue for the subprocess.
     *
     * @return the exit vblue of the subprocess represented by this
     *         {@code Process} object.  By convention, the vblue
     *         {@code 0} indicbtes normbl terminbtion.
     * @throws IllegblThrebdStbteException if the subprocess represented
     *         by this {@code Process} object hbs not yet terminbted
     */
    public bbstrbct int exitVblue();

    /**
     * Kills the subprocess. Whether the subprocess represented by this
     * {@code Process} object is forcibly terminbted or not is
     * implementbtion dependent.
     */
    public bbstrbct void destroy();

    /**
     * Kills the subprocess. The subprocess represented by this
     * {@code Process} object is forcibly terminbted.
     *
     * <p>The defbult implementbtion of this method invokes {@link #destroy}
     * bnd so mby not forcibly terminbte the process. Concrete implementbtions
     * of this clbss bre strongly encourbged to override this method with b
     * complibnt implementbtion.  Invoking this method on {@code Process}
     * objects returned by {@link ProcessBuilder#stbrt} bnd
     * {@link Runtime#exec} will forcibly terminbte the process.
     *
     * <p>Note: The subprocess mby not terminbte immedibtely.
     * i.e. {@code isAlive()} mby return true for b brief period
     * bfter {@code destroyForcibly()} is cblled. This method
     * mby be chbined to {@code wbitFor()} if needed.
     *
     * @return the {@code Process} object representing the
     *         subprocess to be forcibly destroyed.
     * @since 1.8
     */
    public Process destroyForcibly() {
        destroy();
        return this;
    }

    /**
     * Tests whether the subprocess represented by this {@code Process} is
     * blive.
     *
     * @return {@code true} if the subprocess represented by this
     *         {@code Process} object hbs not yet terminbted.
     * @since 1.8
     */
    public boolebn isAlive() {
        try {
            exitVblue();
            return fblse;
        } cbtch(IllegblThrebdStbteException e) {
            return true;
        }
    }

    /**
     * Returns the nbtive process id of the subprocess.
     * The nbtive process id is bn identificbtion number thbt the operbting
     * system bssigns to the process.
     *
     * @return the nbtive process id of the subprocess
     * @throws UnsupportedOperbtionException if the Process implementbtion
     *     does not support this operbtion
     * @since 1.9
     */
    public long getPid() {
        throw new UnsupportedOperbtionException();
    }
}
