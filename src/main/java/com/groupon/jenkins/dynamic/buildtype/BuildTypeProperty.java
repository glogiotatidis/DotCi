/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014, Groupon, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.groupon.jenkins.dynamic.buildtype;

import com.groupon.jenkins.dynamic.build.DynamicProject;
import com.groupon.jenkins.dynamic.build.DynamicSubProject;
import hudson.Extension;
import hudson.model.Job;
import hudson.model.JobProperty;
import hudson.model.JobPropertyDescriptor;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.StaplerRequest;

public class BuildTypeProperty extends JobProperty<Job<?, ?>> {

    public String getBuildType() {
        return buildType;
    }

    private final String buildType;

    public BuildTypeProperty(String buildType) {
        this.buildType = buildType;
    }


    @Extension
    public static final class BuildTypePropertyDescriptor extends JobPropertyDescriptor {

        @Override
        public String getDisplayName() {
            return "Build Type";
        }

        @Override
        public boolean isApplicable(Class<? extends Job> jobType) {
            return DynamicProject.class.equals(jobType) || DynamicSubProject.class.equals(jobType);
        }

        @Override
        public BuildTypeProperty newInstance(StaplerRequest req, JSONObject formData) throws FormException {
            return new BuildTypeProperty(formData.getString("buildType"));
        }
    }

}
